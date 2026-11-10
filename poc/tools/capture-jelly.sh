#!/usr/bin/env bash
# Rekam jejak jelly di nav bar: down -> geser -> tahan -> lepas.
# Pemakaian: tools/capture-jelly.sh <outdir> [x0] [y0] [dx] [dy]
# Butuh device sudah terpasang APK; slow-mo 6x supaya frame screencap tidak melewatkan rebound.
set -euo pipefail

SERIAL=${SERIAL:-192.168.1.8:45675}
OUT=${1:?outdir}
X0=${2:-423}
Y0=${3:-2205}
DX=${4:-190}
DY=${5:-25}
FPS_DELAY=${FPS_DELAY:-0.10}

mkdir -p "$OUT"
rm -f "$OUT"/f*.png

adb -s "$SERIAL" shell am force-stop dev.iqbal.blurnavpoc
adb -s "$SERIAL" shell am start -n dev.iqbal.blurnavpoc/.MainActivity \
  --ei mode 1 --ei dark 0 --ef slowmo ${SLOWMO:-6} --ei jelly "${JELLY:-1}" >/dev/null
sleep 2.2

(
  i=0
  while [ "$i" -lt "${FRAMES:-34}" ]; do
    i=$((i+1))
    adb -s "$SERIAL" exec-out screencap -p > "$OUT/$(printf 'f%02d' "$i").png" 2>/dev/null || true
  done
) &
CAP=$!

ev() { adb -s "$SERIAL" shell input motionevent "$1" "$2" "$3" >/dev/null; }

ev DOWN "$X0" "$Y0"
sleep 0.55
for s in 1 2 3 4 5 6 7 8; do
  ev MOVE "$((X0 + DX * s / 8))" "$((Y0 - DY * s / 8))"
  sleep 0.16
done
sleep 0.85                      # tahan: jelly diam di titik tarik
ev UP "$((X0 + DX))" "$((Y0 - DY))"
sleep 0.40                      # sisa rebound ikut terekam

wait "$CAP" || true
echo "frames: $(ls "$OUT"/f*.png | wc -l)"
