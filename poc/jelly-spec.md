# Jelly motion spec — port COUI dari Gallery 17.10.62

Angka di dokumen ini disalin dari implementasi COUI di APK
`com.coloros.gallery3d 17.10.62` (default tier; ColorOS bisa override per device):

- `com.coui.appcompat.animation.touchanimation.COUIPressAnimatorUnit`
- `com.coui.appcompat.animation.touchanimation.COUIDeformAnimatorUnit`
- `com.coui.appcompat.animation.touchanimation.COUIRubberAnimatorUnit`
- `PressParams`, `TouchMotionTierParams`, `CommonParams`
- `TouchMotionRubberBand`, `PressCalc`, `TouchAnimatorParamProvider.calculateScaleWithK`

Yang dipakai PoC sekarang: press scale COUI + rubber 28 dp + deform area-conserving.
Light pool masih port Swell (lihat bagian “Yang masih approximation”).

## 1. Press (`COUIPressAnimatorUnit`)

Scale target bergantung luas dan aspect ratio view, bukan angka tetap:

```text
area  = w * h                                  // px
t     = clamp((area - (20dp)²) / ((150dp)² - (20dp)²), 0, 1)
ease  = cubic-bezier(0,0,0,1)(t)               // PathInterpolator PressParams.bezier

areaScale = 0.85 + (0.97 - 0.85) * ease
aspect    = max(w/h, h/w)

if aspect <= 2:
    pressScale = areaScale
else:
    t2         = clamp((aspect - 2) / (15 - 2), 0, 1)
    extreme    = 0.85 + (1.0 - 0.85) * cubic-bezier(0,0,0,1)(t2)
    pressScale = (areaScale + extreme) / 2
```

`cubic-bezier(0,0,0,1)` untuk input `x` = `3u² - 2u³` dengan `u = ∛x`.

| Kontrol | Ukuran | pressScale |
|---|---|---|
| Kapsul | 172 × 56 dp | **≈ 0.93** |
| Tombol aksesori | 56 × 56 dp | **≈ 0.91** |

Spring:

| Fase | Response | Bounce |
|---|---|---|
| Down | **0.25** | 0.0 |
| Up / cancel | **0.5** | **0.6** |

Press diterapkan ke **seluruh view** (kapsul atau tombol aksesori), bukan per item.
Item tab hanya mengubah state selected/pressed; di OS 17 pressed-nya di-strip agar
animasi dipegang container.

## 2. Rubber translate (`COUIRubberAnimatorUnit`)

```text
apply(d, curve, max) =
    sign(d) * (1 - 1 / ((|d| * curve) / max + 1)) * max

translationX = apply(dx, 0.05, 28dp)
translationY = apply(dy, 0.05, 28dp)
```

| Parameter | Nilai |
|---|---|
| Max stretch | **28 dp** |
| Curve ratio | **0.05** |
| Drag spring | response **0.15**, bounce **0.15** |
| Release spring | response **0.42–0.45**, bounce **0.65** |
| Dead zone | **5 dp** |

Kurva ini sangat “berat”: tarikan 65 dp hanya menggeser ±2.9 dp.

## 3. Deform (`COUIDeformAnimatorUnit`)

Scale maksimum dari luas/aspect view:

```text
area = w * h
if area <= 38416 px²:
    maxScale = 1.2
else:
    aspect   = max(w,h) / min(w,h)
    k        = (15/1000)*(aspect - 1) + (350000/1000000)*ln(area / 38416)
    maxScale = max((1.2 - 1.03) * (1 - min(k, 1)) + 1.03, 1.03)
```

| Kontrol | maxScale |
|---|---|
| Kapsul 172 × 56 dp | **≈ 1.15** |
| Tombol 56 × 56 dp | **≈ 1.20** |

Deform per sumbu **area-conserving**:

```text
ratio = TouchMotionRubberBand.apply(|d|, 0.55, 150dp) / 150dp
along = 1 + (maxScale - 1) * ratio
perp  = 1 / along
```

Drag X → `scaleX = along`, `scaleY = perp`.
Drag Y → `scaleX = perp`, `scaleY = along`.
Diagonal diblend di ruang log dengan bobot `|dx| : |dy|`.

| Parameter | Nilai |
|---|---|
| Max deform distance | **150 dp** |
| Deform curve ratio | **0.55** |
| Drag spring | response **0.15**, bounce **0.15** |
| Release (kapsul) | response **0.45**, bounce **0.65** |
| Release (tombol) | response **0.42**, bounce **0.65** |

Press dan deform dikalikan: `scale = pressScale × deformScale`.

## 4. Contoh numerik (device 480 dpi)

Kapsul 516 × 168 px, tarikan 197 px (65.7 dp):

```text
pressScale = 0.930
maxScale   = 1.146
ratio      = 0.194
deformX    = 1.028, deformY = 0.973
composite  = 0.956 × 0.905
translation= 8.8 px (2.9 dp)
```

Tombol 168 × 168 px, tarikan 82 px:

```text
pressScale = 0.910
maxScale   = 1.200
deformX    = 1.018, deformY = 0.982
translation= 3.9 px (1.3 dp)
```

## 5. Timing

`response` = periode natural (`ω = 2π/response`). Implementasi PoC memakai solusi
analitik per frame, bukan Euler eksplisit, karena response 0.15 s tidak stabil di 60 fps
dengan Euler (`dt·2ζω` bisa > 2).

`bounce` = 1 − damping ratio. Bounce 0.65 menghasilkan overshoot yang terlihat seperti
COUI; press hand-up bounce 0.6.

## 6. Yang masih approximation

| Bagian | Status |
|---|---|
| Light pool | masih model Swell: radius 40 dp, lift 5.5%, pool 7% × gain 1.5, blend PLUS. COUI memakai `COUISpotLightEffectDrawable` (`TYPE_OPAQUE_MEDIUM_2` untuk menu, `TYPE_BOTTOM_NAVIGATION` untuk tombol), alpha spring 0.2 |
| Melt-ball | `Path.Op.UNION` + leher bezier singgung (putus di celah 12 dp); COUI memakai metaball OPlus (SDF) |
| Blur | konten direkam ke `RenderNode` + RenderEffect 25 px `MIRROR`; COUI memakai `OplusViewBackgroundRenderEffect` live |
| Drop shadow | approximation fallback COUI: `#8A000000`, blur 12 dp, offset 2 dp |
| Caustic shadow | approximation `#42FFFFFF`, blur 10.4 dp, offset 4.9 dp (layout level 4). COUI tombol aksesori level 5 (blur 20 dp, offset 11.2 dp) belum dipisah per view |
| Stroke `CAPSULE_3` | approximation: faint 3 dp rim + 1 px gradient vertikal; bukan `TYPE_FRAMEWORK_CAPSULE_3` native |
| Smooth corner | belum diport (OPlus `OplusMaterialCornerParams`) |
| Scrim | gradient alpha 0.64 di dasar konten; tinggi dan pemicu fade perkiraan PoC |
| Hide/show menu | belum ada di PoC |

## 7. Alat tuning

```bash
# default = port COUI
adb shell am start -n dev.iqbal.blurnavpoc/.MainActivity --ei mode 1 --ei dark 0

... --ef lean 2      # gain efek jelly (2×, tetap memakai rumus COUI)
... --ef light 3     # pool cahaya 0.07 -> 0.21
... --ei jelly 2     # demo boost 3×, bukan nilai COUI
... --ef speed 1.4   # percepat semua spring
```

## 8. Yang harus dihindari

| Properti | Nilai yang salah |
|---|---|
| Press yang membesar | +4 dp / 1.03× (itu bahasa Swell, bukan COUI) |
| Press per item | item saja yang mengecil; seharusnya seluruh kapsul/tombol |
| Rotasi / tilt | 2°+ (COUI tidak memiringkan view) |
| Blob stadium | tombol aksesori ditarik jadi stadium; COUI memakai ellipse area-conserving |
| Stretch dua sumbu | `scaleX > 1` dan `scaleY > 1`; COUI selalu `scaleX·scaleY ≈ 1` |
| Rubber 1:1 | offset mengikuti jari tanpa kurva; COUI memakai 28 dp curve 0.05 |
| Rebound drag | memakai spring drag 0.15/0.15 saat lepas; COUI memakai hand-up 0.42–0.45/0.65 |
