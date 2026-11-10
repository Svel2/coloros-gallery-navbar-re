# Analisis Bottom Navigation — com.coloros.gallery3d 17.10.62

Metode: static analysis (jadx 1.5.6 pada `classes*.dex`, `aapt2 dump` pada `resources.arsc`).
Semua klaim di bawah diberi tag **terlihat** (ada di bytecode/resource) atau **inferensi**.

---

## 1. Apa yang disebut "navigation bottom" di app ini

**terlihat** — Layout utama tab: `res/4M5.xml` = `layout/main_tab_layout`

```
FrameLayout
├── ViewPager2 (id view_pager)
├── ImageView (id bottomTranslucentView)   ← scrim gradient di belakang bar
├── FrameLayout (id main_segment_wrapper_view)
├── ViewStub → layout/main_tab_bottom_segment_picked_layout (dX4.xml)
└── com.oplus.gallery.standard_lib.ui.bottomnavigation.BottomFloatingNavigationBar
```

**terlihat** — `com.coloros.gallery3d.app.tab.TabFragment` meng-inflate menu:

| Menu | Isi |
|---|---|
| `menu/main_tab_bottom_floating_nav_menu_three` (3 tab) | **Photos**, **Albums**, **Create** |
| `menu/main_tab_bottom_floating_nav_menu_two` (2 tab) | **Photos**, **Albums** (default; lihat 1.1) |

Plus dua tombol aksesori:
- start accessory: `main_ic_picked` atau `main_ic_segment_album_sort`
- end accessory: `main_ic_segment_filter_state` (ikon filter)

**terlihat** — rantai kelas:

```
BottomFloatingNavigationBar  (app)
  └─ COUIBottomFloatingAccessoryNavigationBar  (COUI, extends ViewGroup)
       ├─ getMenuView() → COUIBottomFloatingNavigationBarMenuView
       │                    └─ BaseBottomFloatingMenuView  ← blur/stroke/shadow/corner/spotlight
       ├─ COUIBottomFloatingAccessoryView (start/end accessory) ← melt-ball + alpha/scale
       └─ AccessorySideExpansionController (buka/tutup aksesori)
```

Jadi mesin efek ada di COUI (`com.coui.appcompat.*`); app hanya menambah layer kompatibilitas sendiri (`com.oplus.gallery.basebiz.widget.c`) yang bekerja pada menu view.

### 1.1 Kapan 3 tab (ada Create) dipakai

**terlihat** — `TabFragment.J3()` menentukan menu yang di-inflate:

```java
// qa90 (lazy Z0)
pab.c(6, "feature_is_creation_tab_enabled", false)   // default false
// ra90 (lazy a1)
pab.b("is_region_cn", false, false)

boolean J3() {
    if (!Z0) return false;                  // feature flag mati -> 2 tab
    if (a1) return pab.c(4, "show_creation_tab", true);  // region CN
    return true;                            // region lain -> 3 tab
}

inflateMenu(J3() ? R.menu.main_tab_bottom_floating_nav_menu_three
                 : R.menu.main_tab_bottom_floating_nav_menu_two);
```

Kesimpulan: tab Create **bukan** default build. Ia hanya muncul jika cloud config
`feature_is_creation_tab_enabled` = true (dan untuk region CN masih digate lagi oleh
`show_creation_tab`). Tanpa config tersebut, nav bar = **Photos + Albums**.

**terlihat** — aksesori ikut berubah per tab (`TabFragment.W3`, baris 1205–1230):

| Tab aktif | Start accessory | End accessory |
|---|---|---|
| Photos (index 0) | `main_ic_segment_album_sort` (jika `L3()` true) | `main_ic_segment_filter_state` |
| Albums (index 1) | `main_ic_segment_album_sort` | `main_ic_add_album` (`+`) |

### 1.2 Geometri nav bar (COUI)

**terlihat** — dimensi dari resource COUI di APK:

| Resource | Nilai |
|---|---|
| `coui_bottom_floating_navigation_bar_height` | 56 dp |
| `coui_bottom_floating_navigation_bar_item_height` | 48 dp |
| `coui_bottom_floating_navigation_bar_item_max_width` | 70 dp |
| `coui_bottom_floating_navigation_bar_item_padding` | 12 dp |
| `coui_bottom_floating_navigation_bar_item_space` | 8 dp |
| `coui_bottom_floating_navigation_bar_padding_horizontal` | 16 dp |
| `coui_bottom_floating_navigation_bar_min_height_without_padding_vertical` | 88 dp |
| `coui_bottom_floating_navigation_bar_menu_to_screen_navi_distance` | 8 dp |
| `coui_bottom_floating_navigation_bar_touch_effect_radius` | 40 dp |

**terlihat** — `BaseBottomFloatingMenuView.onMeasure`:

```
wMenu = min(available, 2*12 + 8*(n-1) + 70*n)
n=2 -> 172 dp ; n=3 -> 250 dp
setMeasuredDimension(wMenu, mFixedVisibleHeight /*56dp*/)
```

**terlihat** — `COUIBottomFloatingAccessoryNavigationBar`:
- tinggi bar = max(menu, 88 dp)
- layar kecil (`COUIResponsiveUtils.isSmallScreen`): padding horizontal 16 dp,
  aksesori 56 dp menempel tepi layar, menu tetap di tengah
- layar sedang: aksesori menempel di kiri/kanan menu (jarak `mSideViewSpacing`)

**terukur** — device realme RMX3630 (1080×2400 @480dpi), Gallery 15.77.1:

```
menu/kapsul : x 283..797 px  = 171 dp  (172 dp), y 2160..2327 = 56 dp
aksesori    : Ø 171 px = 57 dp, center 16 dp dari tepi kiri/kanan
bar         : 88 dp, bottom margin 8 dp (kapsul bawah 24 dp dari dasar layar)
warna       : #1E1E1E (dark fallback = couiColorBar)
```

Angka-angka ini yang dipakai PoC (`poc/`), sehingga bentuknya sama dengan nav bar asli.

---

## 2. Daftar efek yang ada di bottom navigation

### 2.1 Background blur (efek utama)

**terlihat** — `BaseBottomFloatingMenuView.updateBlurEffect()`:

```java
COUIMaterialEffect.setViewBlurEffect(this, BlurEffectType.TYPE_FRAMEWORK_BOTTOM_BAR);
setBackground(transparent);           // blur path
// atau
setBackground(coui_tab_navigation_view_bg);  // fallback path
```

**terlihat** — parameter `TYPE_FRAMEWORK_BOTTOM_BAR` di `COUIMaterialBlurEffect`:

| Mode | Blur radius | Layer 1 (blend) | Layer 2 (blend) |
|---|---|---|---|
| Light | 25 px | `#B3666666` COLOR_DODGE | `#99F5F5F5` LUMINOSITY |
| Dark | 25 px | `#80666666` OVERLAY | `#B3262626` LUMINOSITY |

Implementasi: `RenderEffect.createBlurEffect(25,25,MIRROR)` + 2× `BlendModeColorFilter`, dipasang lewat `OplusViewBackgroundRenderEffect.setBackgroundRenderEffect()` (bukan `View.setBackground`, jadi konten di belakang ikut di-blur).

Blur kedua (khusus animasi hide): **terlihat** — `COUIBottomFloatingNavigationBarMenuView` menganimasikan `mForegroundBlurRadius` 0 → 30 px lewat `RenderEffect` di view-nya sendiri saat menu hide/show.

### 2.2 Gradient stroke / edge rim

**terlihat** — `BaseBottomFloatingMenuView.updateStrokeEffect()`:
- material version + stroke aktif → `COUIMaterialStrokeEffect.StrokeEffectType.TYPE_FRAMEWORK_CAPSULE_3`
- material version + stroke mati → `TYPE_NO_EFFECT`
- non-material → `COUIShadowEdgeDrawable` (edge style 0, corner type 2)

Syarat: `Settings.System.system_material_stroke_enable == 1` + `materialVersionCheck()`.

### 2.3 Caustic shadow / shadow biasa

**terlihat** — `updateOuterShadow()`:
- caustic shadow (`COUIMaterialEffect.setOutlineCausticShadowParams`) bila `isCausticShadowEffectEnabled()` → butuh SDK ≥ **40.12**, stroke on, feature `com.oplus.appcompat.texture_effect.Enable` on.
- fallback: `ShadowUtils.setElevationToView(view, 2, support_shadow_size_level_three, coui_bottom_floating_navigation_bar_menu_shadow_color)`.
- bila efek material mati total: `COUIMaterialEffect.clearCausticShadow(view)`.

### 2.4 Bentuk kapsul + smooth corner

**terlihat** — outline provider memakai `Outline.setRoundRect(rect, height/2)`; kalau `RoundCornerUtil.getSmoothStyleType()==1` memakai `setPath()` (smooth corner).
**terlihat** — bila material version + blur aktif: `OplusMaterialCornerParams(radius, 1.0f)` + `COUIMaterialEffect.setCornerParams()`; kalau tidak: `setClipToOutline(true)`.

### 2.5 Efek sentuh (touch)

**terlihat** — `BaseBottomFloatingMenuView`:
- `COUISpotLightEffectDrawable` tipe `TYPE_BOTTOM_NAVIGATION` (spotlight/kilau mengikuti jari), syarat `isSpotLightEffectEnable()` = blur on + feature on.
- `COUISpringAnimation` untuk alpha kilau (response 0.2).
- `COUITouchMotionAnimator`: `setDeformationEnable(true)`, `setRubberEnable(true)`, `setPressScaleEnable(true)` — item mengecil/“karet” saat ditekan.
- item press scale + highlight di `COUIBottomFloatingNavigationBarMenuView`.

**terlihat** — isi paket `com.coui.appcompat.animation.touchanimation`:

| Unit / kelas | Fungsi |
|---|---|
| `COUIPressAnimatorUnit` + `PressPhysicsEngine` | scale saat ditekan (press scale tuning) |
| `COUIRubberAnimatorUnit` | rubber-band saat drag (posisi “kenyal”, balik dengan spring) |
| `COUIDeformAnimatorUnit` | deformasi bentuk (stretch/squash) saat drag |
| `COUIITouchMotionUnit` | pipeline properti (translation/scale/corner) |
| `TouchAnimatorParamProvider` (`OlivelinkDefault`) | set parameter per tier perangkat |
| `param/DragDirection`, `DeformRestDistanceMode` | arah drag & mode jarak deformasi |

Jadi saat nav bar / tombol aksesori **ditarik**, COUI mendeformasi bentuknya (bukan cuma
memindahkan highlight) lalu mengembalikannya dengan spring. Di PoC (`poc/`) ini
direplikasi dengan: rubber `f(x)=L·x/(x+L)` untuk offset, blob “stadium” untuk tombol yang
ditarik, `Path.Op.UNION` agar blob menyatu dengan kapsul, dan spring release
response 0.45 / bounce 0.45 sehingga terlihat goyang seperti jeli.

### 2.6 Melt-ball (metaball) transisi aksesori

**terlihat** — `COUIBottomFloatingNavigationBar` & `COUIBottomFloatingAccessoryNavigationBar`:
`OplusMaterialMetaBallParams(2/1, 0, extension, 1.5f, 2.0f)` + `setMetaBallChain()`, hanya bila `COUIMaterialStateManager.materialVersionCheck()`. Ini efek “menyatu seperti cairan” saat accessory kiri/kanan membuka/menutup.

**terlihat** — `AccessorySideExpansionController` (animasi buka/tutup aksesori):

| Parameter | Nilai |
|---|---|
| `EXPANSION_VIEW_MIN_SCALE` | 0.5 |
| alpha spring | response 0.35, bounce 0.0 |
| scale spring | response 0.55, bounce 0.35 |
| translationX spring | response 0.55, bounce 0.35 |
| posisi "tersembunyi" | `fakeBounds` = menempel di tepi menu (lebar 56 dp), jadi aksesori mulai dari dalam kapsul |
| alpha saat transisi | dihitung dari *overlap ratio* dengan menu (`alphaFromOverlapRatio`) — 0 saat menyatu penuh, 1 saat lepas |

Urutan: `setActive(true)` → `requestLayout()` (layout pindah ke posisi fake) →
translationX spring ke posisi visible + scale spring 0.5→1 + alpha ikut overlap.
Saat menutup, urutannya dibalik. Di PoC (`poc/`) urutan & parameter ini dipakai,
ditambah `Path.Op.UNION` supaya siluet kapsul+lingkaran menyatu tanpa garis sambung.

### 2.7 Animasi show/hide & scrim bawah

- **terlihat** — spring animation alpha, width change, horizontal move, scale; parameter high-end vs low-end berbeda.
- **terlihat** — `main_tab_layout` punya `bottomTranslucentView` (background `main_tab_bottom_nav_mask_repeat`: bitmap gradient diputar 180°, alpha 0.64, `tileModeX=repeat`, tint `couiColorBackgroundWithCard`). Alpha awalnya 0 lalu dianimasikan (`pua0` + `PageClickSwitchAlphaAnimator`) → scrim gradient muncul saat konten scroll di bawah bar.

---

## 3. Rantai fallback (dari paling kaya → paling sederhana)

### 3.1 Gate versi & setting sistem (COUI)

**terlihat** — `COUIEffectVersionUtil`:

| Konstanta | Nilai | Arti |
|---|---|---|
| `MATERIAL_MAJOR/SUB` | 40.2 | blur/stroke/corner/melt-ball butuh OPlusView ≥ 40.2 |
| `CAUSTIC_SHADOW` | 40.12 | caustic shadow |
| `BACKGROUND_BLUR` | 33.34 | RenderEffect blur |
| `CROSS_WINDOW_BLUR` | 34.2 | window blur |

**terlihat** — `COUIMaterialStateManager`:
- blur aktif = `system_material_blur_enable == 1` **dan** materialVersionCheck
- stroke aktif = `system_material_stroke_enable == 1`
- fitur `com.oplus.appcompat.texture_effect.Enable` → gate spotlight & caustic shadow
- anim level (`persist.sys.oplus.anim_level`) harus high/mid (1–2); level lain → **semua efek material dimatikan**
- low power / super power save → efek dipaksa mati
- theme check: `coui_blur_enable=true` dan `is_immersive_theme=false` (dua-duanya sesuai di APK ini) — kalau gagal, efek dipaksa mati
- registrasi observer di app: `AppModule` memanggil `registerMaterialStateObserver(context, 3)` (flag stroke|blur) setelah cek versi

### 3.2 Fallback di level COUI

**terlihat** — `BaseBottomFloatingMenuView.updateBlurEffect()`:

| Kondisi | Hasil visual |
|---|---|
| material version + blur on | background transparan + **blur 25 px** + 2 layer warna |
| material version + blur off | `setViewBlurEffect(TYPE_NO_EFFECT)` + background `drawable/coui_tab_navigation_view_bg` |
| non-material version | tidak ada blur; background `coui_tab_navigation_view_bg` |

`coui_tab_navigation_view_bg` = shape solid `?attr/couiColorBar`:

| Mode | Warna |
|---|---|
| Light | `#FFFAFAFA` (opaque) |
| Dark | `#FF1E1E1E` (opaque) |

Jadi fallback COUI = **kapsul solid opaque**, bukan translucent.

### 3.3 Fallback di level app (`com.oplus.gallery.basebiz.widget.c`)

**terlihat** — `FallbackPolicy(isSystemManaged, enableBlendBlur, enableGradientStroke)`:

```java
// fpj.java (lambda)
boolean sys = COUIMaterialStateManager.INSTANCE.materialVersionCheck();
return sys ? new FallbackPolicy(true,false,false)
           : new FallbackPolicy(false,
                 pab.b("feature_is_background_blur_supported", false, false),
                 pab.b("feature_is_gradient_stroke_supported", false, false));
```

Alur `BottomFloatingNavigationBar.f()`:

| Kondisi | Aksi |
|---|---|
| `isSystemManaged` (OPlusView ≥ 40.2) | `c.i()` → **hanya set alpha**, tampilan diserahkan ke COUI |
| tidak system-managed + `enableBlendBlur` | `c.i()` → blur app-level (render effect) + opsional gradient stroke |
| tidak system-managed + blur off | `c.b()` → `ColorDrawable(couiColorBar)` + outline bulat + clip |

`c.b()` = fallback paling sederhana: latar **solid `couiColorBar`** (`#FAFAFA` / `#1E1E1E`) dengan sudut membulat.

Blur app-level (`g9d0.j`):
- blur radius = `alpha × 30 px`, blend warna `base_blur_button_blend_color_first/second` (`#80B2B2B2`/`#CC333333` light, `#4D666666`/`#E6333333` dark), blend mode LUMINOSITY/COLOR_DODGE/OVERLAY tergantung night mode.
- **kalau `RenderEffect` tidak didukung** (`g9d0.d()`=`feature_is_background_blur_supported` false, atau versi < 33.34): background diganti `base_blur_disable_button_color` → **opaque** `#FFF1F1F2` (light) / `#FF000000` (dark). Ini fallback “blur palsu” = warna solid.

### 3.4 Fallback tombol blur di area bawah (`BlurButtonWrapperView` → delegate `u16`)

**terlihat** — default: `enableBlurEffect=true`, `enableStrokeEffect=true`, `enableOuterShadowEffect=false`, `enablePressLightEffect=false`, `enableDragReboundEffect=false`. Layout segment picker (`dX4.xml`) menyalakan press light + drag rebound.

Delegate `u16.k()`:

| Kondisi | Hasil |
|---|---|
| blur off | `TYPE_NO_EFFECT` + `GradientDrawable(couiColorBar)` dengan alpha |
| non-material, flag blur on | `g9d0.k()` (RenderEffect 30 px) |
| non-material, flag off | solid `couiColorBar` |
| material + blur off | `TYPE_NO_EFFECT` + solid `couiColorBar` |
| material + blur on | `TYPE_FRAMEWORK_BOTTOM_BAR` × alpha, background null |

Catatan: `u16.m()` → kalau blur off, elevation/translationZ di-set **0** (tombol jadi flat, tanpa shadow).

### 3.5 Gradient blur di daftar (timeline)

**terlihat** — `GradientBlurMaskView.f()`:
- material version → ikut `isBlurEffectEnable()`
- non-material → `g9d0.e()` = `feature_is_gradient_blur_supported`
- kalau tidak didukung → `setRenderEffect(null)` (tidak ada gradient blur, hanya gambar/fade statis)

---

## 4. Ringkasan visual

| Skenario | Tampilan bottom nav |
|---|---|
| ColorOS 17 + blur/stroke sistem ON | kapsul kaca: blur 25 px, tint abu/putih, rim stroke, caustic shadow, kilau saat disentuh, melt-ball antar tombol |
| ColorOS 17 + blur OFF | kapsul solid `#FAFAFA` / `#1E1E1E`, sudut membulat, shadow elevasi biasa, tanpa kilau/melt-ball |
| ColorOS lama (non-material) + flag blur app ON | blur RenderEffect 30 px + gradient stroke (kalau flag stroke ON), alpha-only untuk sistem |
| ColorOS lama + flag blur OFF | kapsul solid `couiColorBar` (clip outline), tombol-tombol blur jadi solid `#F1F1F2` / `#000000` |
| RenderEffect tidak ada / anim level rendah / hemat daya | efek material mati total → solid, flat, tanpa blur/kilau/caustic |

---

## 5. Cara verifikasi di device

```bash
# setting blur & stroke sistem
adb shell settings get system system_material_blur_enable
adb shell settings get system system_material_stroke_enable
# level animasi (harus 1/2 agar efek material hidup)
adb shell getprop persist.sys.oplus.anim_level
# versi OPlusView (>= 40.2 = material version)
adb shell getprop ro.oplus.version.oplusview   # nama prop bisa berbeda per build
adb shell dumpsys activity top | grep -i material
```

Flag app-level `feature_is_background_blur_supported` / `feature_is_gradient_stroke_supported` / `feature_is_gradient_blur_supported` dibaca dari *config ability* Oplus (`pab.b(...)`), default `false` bila config tidak tersedia.

---

## 6. Bukti (file hasil decompile)

- `analysis/java/BottomFloatingNavigationBar.java` — layer app, `f()`/FallbackPolicy
- `analysis/java/MaterialEffectHelper.java` — `c` helper, `FallbackPolicy`, `b()` fallback, `i()` blur
- `analysis/java/MaterialStateManager.java` — gate versi/setting/power/anim/theme
- `analysis/java/MaterialBlurEffect.java` — parameter `TYPE_FRAMEWORK_BOTTOM_BAR`
- `analysis/java/MaterialEffect.java` — `setViewBlurEffect` + warning versi
- `analysis/java/BaseBottomFloatingMenuView.java` — blur/stroke/shadow/corner/spotlight/melt-ball
- `analysis/java/MaterialBlurUtil.java` — `g9d0` RenderEffect + fallback warna
- `analysis/java/MaterialEffectDelegate.java` — `u16`, efek tombol blur
- `analysis/java/BlurButtonWrapperView.java` — wrapper tombol + default flag
- `analysis/java/TabFragment.java` — pemasangan nav bar, menu, accessory
- `analysis/res/4M5.xml.txt`, `dX4.xml.txt`, `05w.xml.txt`, `7xj.xml.txt` — layout/menu asli
