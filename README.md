# ColorOS 17 Gallery — Bottom Navigation RE + PoC

Static analysis and a dependency-free Android PoC of the bottom navigation bar in
`com.coloros.gallery3d` **17.10.62** (ColorOS 17).

- Full analysis: [LAPORAN-navigation-bottom.md](LAPORAN-navigation-bottom.md)
- Decompiled excerpts / resources: [analysis/](analysis/)
- PoC: [poc/](poc/) — build with `poc/build.sh`, no Gradle.

## What is covered

- Layout/menu: `main_tab_layout`, `menu_two` (Photos + Albums), `menu_three` (Create
  gated by `feature_is_creation_tab_enabled`).
- Geometry: capsule 172×56 dp, item 70 dp, accessory 56 dp, container 88 dp.
- Material effects: blur 25 px + two tint layers, stroke `TYPE_FRAMEWORK_CAPSULE_3`,
  caustic shadow, spotlight, melt-ball.
- Fallbacks: solid `couiColorBar`, non-material app-level blur, no `RenderEffect`.
- Touch motion: press scale 0.85–0.97 (area/aspect), rubber 28 dp/0.05, deform
  150 dp/0.55 area-conserving, spring 0.15 / hand-up 0.42–0.45.
- Provenance: every claim in the report is marked **terlihat** (bytecode/resource) or
  **inferensi**.

## PoC

```bash
cd poc
./build.sh                    # needs Android SDK build-tools + JDK
adb install -r build/blurnav-poc.apk
adb shell am start -n dev.iqbal.blurnavpoc/.MainActivity --ei mode 1 --ei dark 0 --ei prog 1
```

PoC controls:

- `mode`: `0` split, `1` blur, `2` solid
- `dark`: `0/1`
- `prog`: `0` uniform 25 px, `1` 0→25 px down, `2` 25→0 px down
- `jelly`: `2` = 3× motion boost
- `--ef light 3`, `--ef lean 2`, `--ef speed 1.4`, `--ef slowmo 10`
- `--ei scrim 1` (bottom scrim, off by default), `--ei squish 0` (no bar squish on fling)

`poc/README.md` and `poc/jelly-spec.md` contain the measured/verified numbers.
`poc/keystore/` is generated automatically by `build.sh` (debug key only).

## APK

The analysis target is **not committed to git** because it is 376 MB (GitHub limits
files to 100 MB) and is proprietary OPPO software. It is attached to the release:

**https://github.com/Svel2/coloros-gallery-navbar-re/releases/tag/v17.10.62**

To reproduce the analysis, put the APK at the repo root as
`com.coloros.gallery3d_17.10.62.apk`; the report/analysis commands expect that name.

## Disclaimer

Unofficial research for interoperability and education. Not affiliated with or
endorsed by OPPO/ColorOS. “ColorOS”, “OPPO”, “Gallery”, and the APK are property of
their respective owners. The APK is provided as-is for analysis of the shipped
artifact; remove it if you are not allowed to redistribute it. No warranty.
