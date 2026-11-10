#!/usr/bin/env bash
set -euo pipefail
SDK="${ANDROID_HOME:-$HOME/Android/Sdk}"
BT="$SDK/build-tools/37.0.0"
JAR="$SDK/platforms/android-36/android.jar"
D8="$BT/d8"; [ -x "$D8" ] || D8="$SDK/cmdline-tools/latest/bin/d8"
cd "$(dirname "$0")"
rm -rf build && mkdir -p build/classes build/dex
"$BT/aapt2" link -o build/base.apk -I "$JAR" \
  --manifest AndroidManifest.xml --min-sdk-version 31 --target-sdk-version 34 \
  --version-code 1 --version-name 1.0
javac --release 17 -cp "$JAR" -d build/classes $(find src -name '*.java')
"$D8" --release --lib "$JAR" --min-api 31 --output build/dex $(find build/classes -name '*.class')
python3 - <<'PY'
import zipfile, shutil
shutil.copy('build/base.apk', 'build/unaligned.apk')
with zipfile.ZipFile('build/unaligned.apk', 'a', zipfile.ZIP_DEFLATED) as z:
    z.write('build/dex/classes.dex', 'classes.dex')
PY
"$BT/zipalign" -f 4 build/unaligned.apk build/aligned.apk
KS=keystore/debug.keystore
if [ ! -f "$KS" ]; then
  mkdir -p keystore
  keytool -genkeypair -keystore "$KS" -storepass android -keypass android \
    -alias androiddebugkey -keyalg RSA -keysize 2048 -validity 10000 \
    -dname "CN=Android Debug,O=Android,C=US" >/dev/null 2>&1
fi
"$BT/apksigner" sign --ks "$KS" --ks-pass pass:android --key-pass pass:android \
  --out build/blurnav-poc.apk build/aligned.apk
ls -la build/blurnav-poc.apk
