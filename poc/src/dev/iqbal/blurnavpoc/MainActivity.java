package dev.iqbal.blurnavpoc;

import android.app.Activity;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.Gravity;
import android.view.MotionEvent;
import android.graphics.RecordingCanvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * PoC bottom navigation ala ColorOS Gallery 17 (shape mengikuti nav bar asli):
 *
 *  - 2 tab saja: Photos + Albums (menu main_tab_bottom_floating_nav_menu_two;
 *    varian "three" cuma dipakai kalau config feature_is_creation_tab_enabled = true)
 *  - kapsul 172x56dp (12dp padding + 2x70dp item + 8dp gap), di tengah layar
 *  - tombol aksesori 56dp di tepi kiri/kanan, margin 16dp
 *  - container setinggi 88dp, bottom margin 8dp (menu_to_screen_navi_distance)
 *
 * Efek  : backdrop direkam ke RenderNode + RenderEffect (blur 25 px + 2 tint layer COUI),
 *         fallback solid couiColorBar (#FAFAFA / #1E1E1E).
 * Gesture: swipe horizontal = ganti tab (konten ikut jari), drag/tap di kapsul.
 *          Press + jelly = port COUI: press scale 0.85-0.97 (area/aspect), rubber 28dp,
 *          deform area-conserving 150dp, spring drag 0.15 / hand-up 0.42-0.45.
 * Fluid  : spring physics untuk indikator melt (muncul saat interaksi, lalu
 *          memudar), squish bar saat fling, blur update tiap frame.
 */
public class MainActivity extends Activity {

    static final int MODE_SPLIT = 0, MODE_BLUR = 1, MODE_SOLID = 2;

    private int mode = MODE_BLUR;
    private boolean dark = false;
    private FrameLayout root;
    private FluidGalleryView gallery;
    private NavBar bar;
    private TextView modeBtn, themeBtn;
    /** Squish bar saat fling = tambahan PoC, bukan perilaku COUI; --ei squish 0 mematikan. */
    private boolean squishOnFling = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // edge-to-edge: konten & nav bar digambar sampai bawah layar, seperti Gallery asli
        android.view.Window win = getWindow();
        if (android.os.Build.VERSION.SDK_INT >= 30) {
            win.setDecorFitsSystemWindows(false);
        }
        win.setStatusBarColor(0x00000000);
        win.setNavigationBarColor(0x00000000);
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            win.setNavigationBarContrastEnforced(false);
        }

        root = new FrameLayout(this);

        gallery = new FluidGalleryView(this);
        root.addView(gallery, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        bar = new NavBar(this);
        FrameLayout.LayoutParams blp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(88));
        blp.gravity = Gravity.BOTTOM;
        blp.setMargins(0, 0, 0, dp(8));
        root.addView(bar, blp);
        bar.bindContent(gallery);
        bar.setTabListener(index -> gallery.snapToPage(index));

        gallery.setListener(new FluidGalleryView.Listener() {
            @Override
            public void onPageSettled(int page) {
                bar.setSelectedFromPage(page);
            }

            @Override
            public void onMotion(float vy) {
                bar.scheduleCapture();
                if (squishOnFling && Math.abs(vy) > 300f) bar.nudgeFromFling(vy);
            }

            @Override
            public void onPageProgress(float pagePos) {
                bar.setPageProgress(pagePos);
            }
        });

        LinearLayout top = new LinearLayout(this);
        top.setOrientation(LinearLayout.HORIZONTAL);
        modeBtn = chip("Mode: Blur");
        themeBtn = chip("Theme: Light");
        TextView windowBtn = chip("Window blur");
        TextView progBtn = chip("Prog: down");
        top.addView(modeBtn);
        top.addView(themeBtn);
        top.addView(windowBtn);
        top.addView(progBtn);
        FrameLayout.LayoutParams tlp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tlp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        tlp.topMargin = dp(42);
        root.addView(top, tlp);

        windowBtn.setOnClickListener(v -> {
            android.content.Intent i = new android.content.Intent(this, WindowBlurActivity.class);
            i.putExtra("dark", dark ? 1 : 0);
            startActivity(i);
        });
        progBtn.setOnClickListener(v -> {
            int m = (bar.getProgressiveMode() + 1) % 3;
            bar.setProgressiveMode(m);
            progBtn.setText("Prog: " + progName(m));
        });
        modeBtn.setOnClickListener(v -> {
            mode = (mode + 1) % 3;
            bar.setMode(mode);
            modeBtn.setText("Mode: " + modeName());
        });
        themeBtn.setOnClickListener(v -> {
            dark = !dark;
            themeBtn.setText("Theme: " + (dark ? "Dark" : "Light"));
            bar.setDark(dark);
            gallery.setDark(dark);
            applyChrome();
        });

        bar.setJellyMode(getIntent().getIntExtra("jelly", 1) == 2);
        bar.setProgressiveMode(getIntent().getIntExtra("prog", 1));
        progBtn.setText("Prog: " + progName(bar.getProgressiveMode()));
        Spring.tempo = Math.max(0.25, getIntent().getFloatExtra("speed", 1f));
        bar.setLightGain(getIntent().getFloatExtra("light", 1.5f));
        bar.setLeanGain(getIntent().getFloatExtra("lean", 1f));
        squishOnFling = getIntent().getIntExtra("squish", 1) != 0;
        // Default mati: di APK alpha bottomTranslucentView berawal 0 dan hanya dianimasikan,
        // dan scrim permanen membuat kaca bar hampir putih di tema terang.
        gallery.setScrimEnabled(getIntent().getIntExtra("scrim", 0) != 0);

        float slowmo = getIntent().getFloatExtra("slowmo", 1f);
        if (slowmo > 0f && slowmo != 1f) {
            bar.setTimeScale(slowmo);
            gallery.setTimeScale(slowmo);
        }

        if (getIntent().getBooleanExtra("hideBar", false)) {
            bar.setVisibility(View.GONE);
        }
        int startMode = getIntent().getIntExtra("mode", -1);
        if (startMode >= 0 && startMode <= 2) {
            mode = startMode;
            bar.setMode(mode);
            modeBtn.setText("Mode: " + modeName());
        }
        if (getIntent().getIntExtra("dark", 0) == 1) {
            dark = true;
            themeBtn.setText("Theme: Dark");
            bar.setDark(true);
            gallery.setDark(true);
        }

        setContentView(root);
        applyChrome();

        bar.post(bar::scheduleCapture);
        bar.postDelayed(bar::scheduleCapture, 400);
    }

    private String modeName() {
        return mode == MODE_SPLIT ? "Split" : mode == MODE_BLUR ? "Blur" : "Solid";
    }

    private static String progName(int mode) {
        return mode == 0 ? "off" : mode == 1 ? "down" : "up";
    }

    private TextView chip(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(11);
        tv.setTextColor(0xFFFFFFFF);
        tv.setSingleLine(true);
        tv.setPadding(dp(9), dp(7), dp(9), dp(7));
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(0xB3000000);
        bg.setCornerRadius(dp(18));
        tv.setBackground(bg);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = dp(3);
        lp.rightMargin = dp(3);
        tv.setLayoutParams(lp);
        return tv;
    }

    private void applyChrome() {
        root.setBackgroundColor(dark ? 0xFF111214 : 0xFFF7F7F9);
        if (android.os.Build.VERSION.SDK_INT >= 30) {
            android.view.WindowInsetsController c = getWindow().getInsetsController();
            if (c != null) {
                int mask = android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        | android.view.WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS;
                c.setSystemBarsAppearance(dark ? 0 : mask, mask);
            }
        }
    }

    int dp(float v) {
        return Math.round(v * getResources().getDisplayMetrics().density);
    }

    // ==================================================================
    // NavBar: kapsul 2 item + 2 tombol aksesori, blur backdrop di-clip
    // ke bentuk aslinya (bukan full-width seperti versi sebelumnya).
    // ==================================================================
    static class NavBar extends View {

        interface TabListener {
            void onTabSelected(int index);
        }

        private static final int N = 2;
        private static final String[] LABELS = {"Photos", "Albums"};

        private final Paint fill = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Paint iconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Path shapes = new Path();
        private final Path shapeA = new Path();
        private final Path shapeB = new Path();
        private final Path shapeC = new Path();
        private final Path path = new Path();
        private final RectF rect = new RectF();

        private View content;
        private TabListener tabListener;

        private int mode = MODE_BLUR;
        private boolean dark = false;
        private int selected = 0;
        private int prevSelected = 0;
        private boolean accInit = false;
        private float timeScale = 1f;

        /** 0 = diam, 1 = kapsul, 3 = aksesori kiri, 4 = aksesori kanan. */
        private int motionTarget = 0;
        private boolean touching = false;
        private boolean accDragged = false;
        private boolean jellyLoud = false;
        private float downX;
        private float downY;
        private final Matrix jelly = new Matrix();
        private final Matrix ink = new Matrix();
        private final Matrix accMatrix = new Matrix();

        /** Radius blur COUI TYPE_FRAMEWORK_BOTTOM_BAR (px, bukan dp). */
        private static final float BLUR_RADIUS = 25f;
        /** Tingkat tengah progressive blur: 0 → 10 → 25 px. */
        private static final float BLUR_RADIUS_MID = 10f;
        /** Ruang ekstra atas/bawah supaya sampel blur di tepi kapsul bukan piksel mirror. */
        private static final int BLUR_PAD = 40;

        /** Konten di belakang bar, direkam langsung (tanpa bitmap) di frame yang sama. */
        private RenderNode contentNode;
        private RenderNode blurNode;    // blur 25 px + tint
        private RenderNode midNode;     // blur 10 px + tint (progressive)
        private RenderNode tintNode;    // tint saja, radius 0 (progressive)
        private boolean contentDirty = true;
        private boolean backdropDark;
        private final int[] locContent = new int[2];
        private final int[] locSelf = new int[2];
        /** 0 = uniform (seperti APK), 1 = 0→25 px ke bawah, 2 = 25→0 px ke bawah. */
        private int progressiveMode = 1;
        private final Paint maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private Shader maskSharp;
        private Shader maskMid;
        private int maskMode = -1;
        private int maskHeight = -1;
        private RenderNode shadowNode;
        private RenderNode causticNode;
        private long shadowKey = -1;
        private long causticKey = -1;
        private final Paint shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Paint causticPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private Shader strokeShader;
        private int strokeShaderKey = -1;
        private RadialGradient lightShader;
        private int lightShaderAlpha = -1;
        /** Kontrol yang sedang menyala (1 kapsul, 3/4 aksesori); bertahan sampai glow padam. */
        private int lightTarget = 1;
        private final float[] meltPt = new float[2];

        private boolean windowMode = false;
        private boolean crossBlurEnabled = false;

        private final Spring indX = new Spring();
        private final Spring indW = new Spring();
        private final Spring indA = new Spring();
        private final Spring pressSw = new Spring();   // progress press kapsul (0..1)
        private final Spring accS = new Spring();      // progress press aksesori (0..1)
        private final Spring squish = new Spring();
        private final Spring selAnim = new Spring();
        private final Spring accLeft = new Spring();
        private final Spring accScale = new Spring();
        private final Spring jx = new Spring();        // deform scaleX (area-conserving)
        private final Spring jy = new Spring();        // deform scaleY
        private final Spring dragX = new Spring();     // rubber translateX
        private final Spring dragY = new Spring();     // rubber translateY

        // ---- port COUI: COUIPressAnimatorUnit + COUIITouchMotionUnit ----
        // Default dari Gallery 17.10.62 (PressParams, TouchMotionTierParams, CommonParams).
        private static final float PRESS_MIN_SCALE = 0.85f;
        private static final float PRESS_MAX_SCALE = 0.97f;
        private static final float PRESS_MIN_AREA_DP = 20f;
        private static final float PRESS_MAX_AREA_DP = 150f;
        private static final float PRESS_ASPECT_THRESHOLD = 2f;
        private static final float PRESS_ASPECT_EXTREME = 15f;
        private static final float PRESS_ASPECT_BASE_SCALE = 0.85f;
        private static final float PRESS_ASPECT_EXTREME_SCALE = 1.0f;
        private static final float PRESS_RESPONSE = 0.25f;
        private static final float PRESS_HANDUP_RESPONSE = 0.5f;
        private static final float PRESS_HANDUP_BOUNCE = 0.6f;

        private static final float RUBBER_MAX_DP = 28f;
        private static final float RUBBER_CURVE_RATIO = 0.05f;
        private static final float DEFORM_MAX_DP = 150f;
        private static final float DEFORM_CURVE_RATIO = 0.55f;
        private static final float DEFORM_MAX_SCALE = 1.2f;
        private static final float DRAG_RESPONSE = 0.15f;
        private static final float DRAG_BOUNCE = 0.15f;
        private static final float HANDUP_RESPONSE_CAPSULE = 0.45f;
        private static final float HANDUP_RESPONSE_ACCESSORY = 0.42f;
        private static final float HANDUP_BOUNCE = 0.65f;
        private static final float DRAG_DEAD_ZONE_DP = 5f;

        private static final float MELT_RESPONSE = 0.55f;
        private static final float MELT_BOUNCE = 0.35f;
        /** Leher metaball putus saat celah kapsul–aksesori melewati jarak ini. */
        private static final float MELT_MAX_GAP_DP = 12f;

        /** InteractiveHighlight: pool cahaya di bawah jari. */
        private static final float LIGHT_RADIUS_DP = 40f;
        private static final float LIGHT_LIFT = 0.055f;
        private static final float LIGHT_POOL = 0.07f;

        private final Spring glowSw = new Spring();
        private final Spring lightX = new Spring();
        private final Spring lightY = new Spring();
        private final Paint lightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /** Pengali pool cahaya; referensi 1.0 (MorphPress.LightPool = .07). */
        private float lightGain = 1.5f;
        /** Pengali efek jelly (1.0 = COUI). */
        private float leanGain = 1f;
        private float jSx = 1f, jSy = 1f, jTx, jTy;

        private float motionGain() {
            return Math.max(0f, leanGain) * (jellyLoud ? 3f : 1f);
        }

        private boolean animating = false;
        private long lastFrame;

        private final Runnable hideInd = () -> {
            indA.setTarget(0);
            startAnim();
        };

        NavBar(android.content.Context c) {
            super(c);
            setWillNotDraw(false);

            indX.configure(0.34, 0.30);
            indW.configure(0.34, 0.30);
            indA.configure(0.18, 0.0);
            indA.tolerance = 0.01;
            pressSw.configure(PRESS_RESPONSE, 0f);
            pressSw.tolerance = 0.002;
            pressSw.snapTo(0);
            accS.configure(PRESS_RESPONSE, 0f);
            accS.tolerance = 0.002;
            accS.snapTo(0);
            squish.configure(0.40, 0.25);
            squish.snapTo(0.0);
            selAnim.configure(0.22, 0.10);
            selAnim.tolerance = 0.004;
            selAnim.snapTo(1.0);
            // AccessorySideExpansionController: translationX & scale = response 0.55, bounce 0.35.
            accLeft.configure(MELT_RESPONSE, MELT_BOUNCE);
            accLeft.tolerance = 0.5;
            accScale.configure(MELT_RESPONSE, MELT_BOUNCE);
            accScale.tolerance = 0.002;
            accScale.snapTo(0.5);

            // Deform scale (area-conserving) dan rubber translate memakai drag spring COUI.
            jx.configure(DRAG_RESPONSE, DRAG_BOUNCE);
            jy.configure(DRAG_RESPONSE, DRAG_BOUNCE);
            jx.tolerance = 0.001;
            jy.tolerance = 0.001;
            jx.snapTo(1);
            jy.snapTo(1);
            dragX.configure(DRAG_RESPONSE, DRAG_BOUNCE);
            dragY.configure(DRAG_RESPONSE, DRAG_BOUNCE);
            dragX.tolerance = 0.1;
            dragY.tolerance = 0.1;
            dragX.snapTo(0);
            dragY.snapTo(0);

            // GlowIn = (1, .1) / GlowOut = (1, .5): damping penuh, tanpa overshoot.
            glowSw.configure(0.2, 0.0);
            glowSw.tolerance = 0.02;
            glowSw.snapTo(0);
            // LightFollow = (.8, .16): cahaya menyusul jari, tidak menempel kaku.
            lightX.configure(0.10, 0.20);
            lightY.configure(0.10, 0.20);
            lightX.tolerance = 0.3;
            lightY.tolerance = 0.3;
            lightX.snapTo(capW() / 2f);
            lightY.snapTo(capH() / 2f);

            textPaint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
            textPaint.setTextAlign(Paint.Align.CENTER);
            // Sama seperti item view COUI: teks tetap tajam saat seluruh kapsul ditransform.
            textPaint.setLinearText(true);
            textPaint.setSubpixelText(true);
            maskPaint.setBlendMode(BlendMode.DST_IN);
        }

        void bindContent(View v) {
            content = v;
        }

        void setTabListener(TabListener l) {
            tabListener = l;
        }

        void setJellyMode(boolean loud) {
            jellyLoud = loud;
            invalidate();
        }

        void setProgressiveMode(int mode) {
            int m = Math.max(0, Math.min(2, mode));
            if (m == progressiveMode) return;
            progressiveMode = m;
            invalidate();
        }

        int getProgressiveMode() {
            return progressiveMode;
        }

        void setLeanGain(float g) {
            leanGain = Math.max(0f, g);
            invalidate();
        }

        void setLightGain(float g) {
            lightGain = Math.max(0f, g);
            invalidate();
        }

        void setTimeScale(float f) {
            // >1 = slow motion (untuk capture), 1 = normal
            timeScale = Math.max(0.05f, f);
        }

        @Override
        protected void onSizeChanged(int w, int h, int ow, int oh) {
            super.onSizeChanged(w, h, ow, oh);
            if (!accInit && w > 0) {
                accInit = true;
                accLeft.snapTo(leftAccHiddenX());
                accScale.snapTo(0.5);
            }
        }

        void setMode(int m) {
            mode = m;
            if (m != MODE_SOLID) scheduleCapture();
            invalidate();
        }

        void setDark(boolean d) {
            dark = d;
            scheduleCapture();
            invalidate();
        }

        void setWindowMode(boolean on, boolean crossBlur) {
            windowMode = on;
            crossBlurEnabled = crossBlur;
            invalidate();
        }

        // ---------------- geometri (angka dari COUI / pengukuran device) ----------------

        private float capW() {
            return dp(12) * 2 + dp(70) * N + dp(8) * (N - 1);   // 172dp
        }

        private float capH() {
            return dp(56);
        }

        private float capTop() {
            return (getHeight() - capH()) / 2f;                  // container 88dp
        }

        private float capLeft() {
            return (getWidth() - capW()) / 2f;
        }

        private float itemW() {
            return dp(70);
        }

        private float itemStep() {
            return dp(70) + dp(8);
        }

        private float itemLeft(int i) {
            return capLeft() + dp(12) + i * itemStep();
        }

        private float itemCx(int i) {
            return itemLeft(i) + itemW() / 2f;
        }

        private float accR() {
            return dp(28);
        }

        private float leftAccCx() {
            return dp(16) + accR();
        }

        private float rightAccCx() {
            return getWidth() - dp(16) - accR();
        }

        private float accCy() {
            return capTop() + capH() / 2f;
        }

        private float accSizePx() {
            return accR() * 2f;
        }

        private float leftAccRestX() {
            return dp(16) + accR();
        }

        private float leftAccHiddenX() {
            return capLeft() + accR();      // menempel di tepi menu (seperti fake bounds COUI)
        }

        private float leftAccRadius() {
            return accR() * (float) accScale.value;
        }

        /** alpha mengikuti seberapa besar lingkaran keluar dari kapsul (melt). */
        private float leftAccAlpha() {
            if (!accInit) return 0f;
            float r = Math.max(1f, leftAccRadius());
            float outside = (capLeft() - ((float) accLeft.value - r)) / (2f * r);
            return clamp(outside * 1.15f, 0f, 1f);
        }

        private int nearestItem(float x) {
            int i = (int) ((x - capLeft() - dp(12)) / itemStep());
            return clamp(i, 0, N - 1);
        }

        private int hitTest(float x, float y) {
            float top = capTop();
            float bottom = top + capH();
            if (y < top - dp(2) || y > bottom + dp(2)) return 0;
            if (x >= capLeft() && x <= capLeft() + capW()) {
                return 1 + nearestItem(x);
            }
            if (leftAccAlpha() > 0.4f && !accLeft.active && !accScale.active) {
                float dx = x - (float) accLeft.value, dy = y - accCy();
                float r = leftAccRadius();
                if (dx * dx + dy * dy <= r * r) return 3;
            }
            float dx = x - rightAccCx(), dy = y - accCy();
            if (dx * dx + dy * dy <= accR() * accR()) return 4;
            return 0;
        }

        // ---------------- state / API dari Activity ----------------

        void setPageProgress(float pagePos) {
            if (getWidth() == 0) return;
            float p = clamp(pagePos, 0f, N - 1f);
            float frac = p - Math.round(p);
            boolean moving = Math.abs(frac) > 0.002f;

            int i0 = (int) Math.floor(p);
            int i1 = Math.min(i0 + 1, N - 1);
            float f = p - i0;
            float cx = itemCx(i0) + (itemCx(i1) - itemCx(i0)) * f;

            indX.snapTo(cx);
            indW.snapTo(itemW() - dp(6) + dp(10) * Math.min(f, 1f - f) * 2f);
            if (moving) indA.snapTo(1f);
            invalidate();
        }

        void setSelectedFromPage(int p) {
            applySelection(clamp(p, 0, N - 1));
            indX.setTarget(itemCx(selected));
            indW.setTarget(itemW() - dp(6));
            hideIndicatorSoon();
            startAnim();
            invalidate();
        }

        private void applySelection(int idx) {
            if (idx == selected) return;
            prevSelected = selected;
            selected = idx;
            selAnim.snapTo(0);
            selAnim.setTarget(1);
            accLeft.setTarget(selected == 1 ? leftAccRestX() : leftAccHiddenX());
            accScale.setTarget(selected == 1 ? 1f : 0.5f);
            startAnim();
        }

        private void select(int idx, boolean notify) {
            applySelection(clamp(idx, 0, N - 1));
            indX.setTarget(itemCx(selected));
            indW.setTarget(itemW() - dp(6));
            hideIndicatorSoon();
            if (notify && tabListener != null) tabListener.onTabSelected(selected);
            startAnim();
            invalidate();
        }

        private void hideIndicatorSoon() {
            removeCallbacks(hideInd);
            postDelayed(hideInd, 120);
        }

        private void showIndicatorAt(float x) {
            removeCallbacks(hideInd);
            float anchor = itemCx(selected);
            float mx = clamp(x, itemLeft(0) + itemW() * 0.5f,
                    itemLeft(N - 1) + itemW() * 0.5f);
            float w = Math.max(itemW() - dp(6), Math.abs(mx - anchor) + itemW() * 0.55f);
            indX.snapTo(mx);
            indW.snapTo(Math.min(w, itemW() + dp(26)));
            indA.snapTo(1f);
        }

        void nudgeFromFling(float vy) {
            float target = -Math.min(dp(2), Math.abs(vy) / 4000f);
            squish.setTarget(target);
            startAnim();
            removeCallbacks(squishReset);
            postDelayed(squishReset, 110);
        }

        private final Runnable squishReset = () -> {
            squish.setTarget(0);
            startAnim();
        };

        // ---------------- gesture ----------------

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            if (windowMode) return super.onTouchEvent(e);
            switch (e.getActionMasked()) {
                case MotionEvent.ACTION_DOWN: {
                    int hit = hitTest(e.getX(), e.getY());
                    if (hit == 0) return false;
                    touching = true;
                    accDragged = false;
                    downX = e.getX();
                    downY = e.getY();
                    lightTarget = hit <= N ? 1 : hit;
                    // LightFollow: pool cahaya menyala di titik sentuh.
                    glowSw.configure(0.2f, 0f);
                    glowSw.setTarget(1f);
                    if (glowSw.value < 0.02f) {
                        lightX.snapTo(e.getX() - capLeft());
                        lightY.snapTo(e.getY() - capTop());
                    } else {
                        lightX.setTarget(e.getX() - capLeft());
                        lightY.setTarget(e.getY() - capTop());
                    }
                    dragX.setTarget(0f);
                    dragY.setTarget(0f);
                    jx.setTarget(1f);
                    jy.setTarget(1f);
                    if (hit <= N) {
                        motionTarget = 1;
                        accS.setTarget(0f);
                        pressSw.configure(PRESS_RESPONSE, 0f);
                        pressSw.setTarget(1f);
                        showIndicatorAt(e.getX());
                    } else {
                        motionTarget = hit;
                        pressSw.setTarget(0f);
                        accS.configure(PRESS_RESPONSE, 0f);
                        accS.setTarget(1f);
                    }
                    startAnim();
                    return true;
                }
                case MotionEvent.ACTION_MOVE: {
                    if (!touching) return false;
                    float dx = e.getX() - downX;
                    float dy = e.getY() - downY;
                    // LightFollow: pool menyusul jari dengan pegas (.8, .16).
                    lightX.setTarget(e.getX() - capLeft());
                    lightY.setTarget(e.getY() - capTop());
                    if (motionTarget == 1) {
                        if (Math.abs(dx) > dp(3)) showIndicatorAt(e.getX());
                        applyMotion(dx, dy, capW(), capH());
                    } else {
                        if ((float) Math.hypot(dx, dy) > dp(DRAG_DEAD_ZONE_DP)) {
                            accDragged = true;
                        }
                        applyMotion(dx, dy, accSizePx(), accSizePx());
                    }
                    invalidate();
                    return true;
                }
                case MotionEvent.ACTION_UP: {
                    if (!touching) return false;
                    touching = false;
                    if (motionTarget == 1) {
                        select(nearestItem(e.getX()), true);
                    } else {
                        hideIndicatorSoon();
                        if (!accDragged) {
                            toast(motionTarget == 3 ? "Sort album"
                                    : (selected == 1 ? "Add album" : "Filter"));
                        }
                    }
                    releaseMotion();
                    accDragged = false;
                    return true;
                }
                case MotionEvent.ACTION_CANCEL: {
                    touching = false;
                    accDragged = false;
                    releaseMotion();
                    return true;
                }
            }
            return super.onTouchEvent(e);
        }

        /** PathInterpolator cubic-bezier(0,0,0,1) dari PressParams.bezier. */
        private static float pressEase(float t) {
            float x = clamp(t, 0f, 1f);
            double u = Math.cbrt(x);
            return (float) (3.0 * u * u - 2.0 * u * u * u);
        }

        /** COUIPressAnimatorUnit: scale 0.85..0.97, terkoreksi aspect ratio kontrol. */
        private float pressTargetScale(float w, float h) {
            float area = w * h;
            float minArea = dp(PRESS_MIN_AREA_DP) * dp(PRESS_MIN_AREA_DP);
            float maxArea = dp(PRESS_MAX_AREA_DP) * dp(PRESS_MAX_AREA_DP);
            float t = maxArea > minArea ? clamp((area - minArea) / (maxArea - minArea), 0f, 1f) : 1f;
            float areaScale = PRESS_MIN_SCALE + (PRESS_MAX_SCALE - PRESS_MIN_SCALE) * pressEase(t);
            float aspect = Math.max(w / h, h / w);
            if (aspect <= PRESS_ASPECT_THRESHOLD) return areaScale;
            float t2 = clamp((aspect - PRESS_ASPECT_THRESHOLD)
                    / (PRESS_ASPECT_EXTREME - PRESS_ASPECT_THRESHOLD), 0f, 1f);
            float extreme = PRESS_ASPECT_BASE_SCALE
                    + (PRESS_ASPECT_EXTREME_SCALE - PRESS_ASPECT_BASE_SCALE) * pressEase(t2);
            return (areaScale + extreme) * 0.5f;
        }

        /** TouchMotionRubberBand.apply(). */
        private static float rubberBand(float offset, float maxPx, float curve) {
            if (maxPx <= 0f) return 0f;
            float ratio = Math.abs(offset) * curve / maxPx;
            return Math.signum(offset) * (1f - 1f / (ratio + 1f)) * maxPx;
        }

        private static float deformRatio(float offset, float maxPx, float curve) {
            if (maxPx <= 0f) return 0f;
            return rubberBand(offset, maxPx, curve) / maxPx;
        }

        /** TouchAnimatorParamProvider.calculateScaleWithK(). */
        private float deformMaxScale(float w, float h) {
            float area = w * h;
            if (area <= 38416f) return DEFORM_MAX_SCALE;
            float fmax = Math.max(w, h);
            float fmin = Math.min(w, h);
            if (fmin <= 0f) return DEFORM_MAX_SCALE;
            float aspect = fmax / fmin;
            double k = (15.0 / 1000.0) * (aspect - 1.0)
                    + (350000.0 / 1000000.0) * Math.log(area / 38416.0);
            float term = Math.min(1f, (float) k);
            return Math.max((DEFORM_MAX_SCALE - 1.03f) * (1f - term) + 1.03f, 1.03f);
        }

        /**
         * Satu gesture = satu view COUI: rubber translate + deform scale area-conserving.
         * Deform: satu sumbu > 1, sumbu lain = 1/nya; arah diagonal diblend secara geometris.
         */
        private void applyMotion(float dx, float dy, float w, float h) {
            float gain = motionGain();
            if (Math.abs(dx) < dp(DRAG_DEAD_ZONE_DP)
                    && Math.abs(dy) < dp(DRAG_DEAD_ZONE_DP)) {
                dx = 0f;
                dy = 0f;
            }
            float mx = dx * gain;
            float my = dy * gain;

            float maxRubber = dp(RUBBER_MAX_DP);
            dragX.configure(DRAG_RESPONSE, DRAG_BOUNCE);
            dragY.configure(DRAG_RESPONSE, DRAG_BOUNCE);
            dragX.setTarget(rubberBand(mx, maxRubber, RUBBER_CURVE_RATIO));
            dragY.setTarget(rubberBand(my, maxRubber, RUBBER_CURVE_RATIO));

            float maxD = dp(DEFORM_MAX_DP);
            float rx = deformRatio(Math.abs(mx), maxD, DEFORM_CURVE_RATIO);
            float ry = deformRatio(Math.abs(my), maxD, DEFORM_CURVE_RATIO);
            float maxScale = deformMaxScale(w, h);
            float ax = 1f + (maxScale - 1f) * rx;   // drag X: scaleX naik, scaleY turun
            float ay = 1f + (maxScale - 1f) * ry;   // drag Y: scaleY naik, scaleX turun

            float targetX;
            float targetY;
            float axAbs = Math.abs(mx);
            float ayAbs = Math.abs(my);
            if (axAbs + ayAbs <= 0f) {
                targetX = 1f;
                targetY = 1f;
            } else if (ayAbs <= 0f) {
                targetX = ax;
                targetY = 1f / ax;
            } else if (axAbs <= 0f) {
                targetX = 1f / ay;
                targetY = ay;
            } else {
                float wx = axAbs / (axAbs + ayAbs);
                float wy = ayAbs / (axAbs + ayAbs);
                targetX = (float) Math.exp(Math.log(ax) * wx + Math.log(1f / ay) * wy);
                targetY = (float) Math.exp(Math.log(1f / ax) * wx + Math.log(ay) * wy);
            }
            jx.configure(DRAG_RESPONSE, DRAG_BOUNCE);
            jy.configure(DRAG_RESPONSE, DRAG_BOUNCE);
            jx.setTarget(targetX);
            jy.setTarget(targetY);
        }

        private void releaseMotion() {
            pressSw.configure(PRESS_HANDUP_RESPONSE, PRESS_HANDUP_BOUNCE);
            pressSw.setTarget(0f);
            accS.configure(PRESS_HANDUP_RESPONSE, PRESS_HANDUP_BOUNCE);
            accS.setTarget(0f);
            float response = motionTarget == 1
                    ? HANDUP_RESPONSE_CAPSULE : HANDUP_RESPONSE_ACCESSORY;
            jx.configure(response, HANDUP_BOUNCE);
            jy.configure(response, HANDUP_BOUNCE);
            dragX.configure(response, HANDUP_BOUNCE);
            dragY.configure(response, HANDUP_BOUNCE);
            jx.setTarget(1f);
            jy.setTarget(1f);
            dragX.setTarget(0f);
            dragY.setTarget(0f);
            // GlowOut = (1, .5): cahaya padam lebih pelan daripada saat menyala.
            glowSw.configure(0.2f, 0f);
            glowSw.setTarget(0f);
            startAnim();
        }

        private void toast(String s) {
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        }

        // ---------------- capture backdrop ----------------

        /** Konten di belakang bar berubah: rekam ulang di onDraw berikutnya (frame yang sama). */
        void scheduleCapture() {
            if (content == null || mode == MODE_SOLID || windowMode) return;
            contentDirty = true;
            invalidate();
        }

        /**
         * Rekam konten langsung ke RenderNode lalu blur di GPU. Dibanding snapshot bitmap:
         * tidak ada upload tekstur per frame dan tidak tertinggal satu frame dari konten.
         *
         * @return false kalau backdrop tidak bisa dibuat (bar jatuh ke fallback solid)
         */
        private boolean updateBackdrop() {
            if (content == null) return false;
            int w = getWidth();
            int nh = getHeight() + BLUR_PAD * 2;
            try {
                if (contentNode == null || contentNode.getWidth() != w
                        || contentNode.getHeight() != nh || backdropDark != dark) {
                    contentNode = new RenderNode("nav-content");
                    contentNode.setPosition(0, 0, w, nh);
                    blurNode = effectNode("nav-blur", w, nh, BLUR_RADIUS);
                    midNode = effectNode("nav-blur-mid", w, nh, BLUR_RADIUS_MID);
                    tintNode = effectNode("nav-tint", w, nh, 0f);
                    backdropDark = dark;
                    contentDirty = true;
                }
                if (contentDirty) {
                    content.getLocationInWindow(locContent);
                    getLocationInWindow(locSelf);
                    RecordingCanvas rc = contentNode.beginRecording();
                    try {
                        rc.translate(locContent[0] - locSelf[0],
                                locContent[1] - locSelf[1] + BLUR_PAD);
                        content.draw(rc);
                    } finally {
                        contentNode.endRecording();
                    }
                    recordEffect(blurNode);
                    recordEffect(midNode);
                    recordEffect(tintNode);
                    contentDirty = false;
                }
                return true;
            } catch (Throwable t) {
                contentNode = null;
                blurNode = null;
                midNode = null;
                tintNode = null;
                return false;
            }
        }

        private RenderNode effectNode(String name, int w, int h, float radius) {
            RenderNode node = new RenderNode(name);
            node.setPosition(0, 0, w, h);
            node.setRenderEffect(buildBlurEffect(radius,
                    dark ? 0x80666666 : 0xB3666666,
                    dark ? BlendMode.OVERLAY : BlendMode.COLOR_DODGE,
                    dark ? 0xB3262626 : 0x99F5F5F5));
            return node;
        }

        private void recordEffect(RenderNode node) {
            RecordingCanvas rc = node.beginRecording();
            try {
                rc.drawRenderNode(contentNode);
            } finally {
                node.endRecording();
            }
        }

        /** Blur (MIRROR, seperti COUI) + dua layer tint. Radius 0 = tint saja. */
        private RenderEffect buildBlurEffect(float radius, int c1, BlendMode m1, int c2) {
            BlendModeColorFilter f1 = new BlendModeColorFilter(c1, m1);
            RenderEffect effect = radius > 0.01f
                    ? RenderEffect.createColorFilterEffect(f1, RenderEffect.createBlurEffect(
                            radius, radius, Shader.TileMode.MIRROR))
                    : RenderEffect.createColorFilterEffect(f1);
            return RenderEffect.createColorFilterEffect(
                    new BlendModeColorFilter(c2, BlendMode.LUMINOSITY), effect);
        }

        private void drawBackdrop(Canvas canvas, RenderNode node) {
            int save = canvas.save();
            // Lawan squish: backdrop harus tetap sejajar dengan konten di belakangnya.
            canvas.translate(0, -BLUR_PAD - (float) squish.value);
            canvas.drawRenderNode(node);
            canvas.restoreToCount(save);
        }

        /** Layer backdrop yang alpha-nya dipotong gradient vertikal (DST_IN). */
        private void drawMaskedBackdrop(Canvas canvas, RenderNode node, Shader mask) {
            int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null);
            drawBackdrop(canvas, node);
            maskPaint.setShader(mask);
            canvas.drawRect(0, 0, getWidth(), getHeight(), maskPaint);
            canvas.restoreToCount(layer);
        }

        /**
         * Kaca: blur 25 px penuh; mode progressive menumpuk 10 px lalu 0 px di sisi tajamnya.
         * Ketiga tingkat memakai tint yang sama, jadi warna kaca rata dari atas ke bawah.
         */
        private void drawGlass(Canvas canvas) {
            drawBackdrop(canvas, blurNode);
            if (progressiveMode == 0) return;
            int height = getHeight();
            if (maskSharp == null || maskMode != progressiveMode || maskHeight != height) {
                float near = progressiveMode == 1 ? capTop() : capTop() + capH();
                float far = progressiveMode == 1 ? capTop() + capH() : capTop();
                int on = 0xFFFFFFFF;
                int off = 0x00FFFFFF;
                maskSharp = new LinearGradient(0, near, 0, far,
                        new int[]{on, off, off}, new float[]{0f, 0.45f, 1f},
                        Shader.TileMode.CLAMP);
                maskMid = new LinearGradient(0, near, 0, far,
                        new int[]{on, on, off, off}, new float[]{0f, 0.35f, 0.9f, 1f},
                        Shader.TileMode.CLAMP);
                maskMode = progressiveMode;
                maskHeight = height;
            }
            drawMaskedBackdrop(canvas, midNode, maskMid);
            drawMaskedBackdrop(canvas, tintNode, maskSharp);
        }

        // ---------------- anim loop ----------------

        private void startAnim() {
            if (animating) return;
            animating = true;
            lastFrame = 0;
            Choreographer.getInstance().postFrameCallback(frameCb);
        }

        private final Choreographer.FrameCallback frameCb = new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                // Waktu vsync, bukan System.nanoTime(): langkah pegas tidak ikut jitter callback.
                double dt = (lastFrame == 0 ? 1.0 / 120.0
                        : (frameTimeNanos - lastFrame) / 1e9) / timeScale;
                lastFrame = frameTimeNanos;

                boolean more = false;
                if (indX.update(dt)) more = true;
                if (indW.update(dt)) more = true;
                if (indA.update(dt)) more = true;
                if (accS.update(dt)) more = true;
                if (squish.update(dt)) more = true;
                if (selAnim.update(dt)) more = true;
                if (accLeft.update(dt)) more = true;
                if (accScale.update(dt)) more = true;
                if (jx.update(dt)) more = true;
                if (jy.update(dt)) more = true;
                if (dragX.update(dt)) more = true;
                if (dragY.update(dt)) more = true;
                if (pressSw.update(dt)) more = true;
                if (glowSw.update(dt)) more = true;
                if (lightX.update(dt)) more = true;
                if (lightY.update(dt)) more = true;
                if (!touching && !pressSw.active && !accS.active
                        && !jx.active && !jy.active && !dragX.active && !dragY.active) {
                    motionTarget = 0;
                }

                invalidate();
                if (more) {
                    Choreographer.getInstance().postFrameCallback(this);
                } else {
                    animating = false;
                }
            }
        };

        // ---------------- drawing ----------------

        private float capsulePressScale() {
            // Jangan di-clamp: hand-up bounce 0.6 memang overshoot sedikit di atas 1.0.
            float p = (float) pressSw.value;
            return 1f + (pressTargetScale(capW(), capH()) - 1f) * p;
        }

        private float accPressScale(int which) {
            if (motionTarget != which) return 1f;
            float p = (float) accS.value;
            float size = accSizePx();
            return 1f + (pressTargetScale(size, size) - 1f) * p;
        }

        private float motionScaleX(int target) {
            return motionTarget == target ? (float) jx.value : 1f;
        }

        private float motionScaleY(int target) {
            return motionTarget == target ? (float) jy.value : 1f;
        }

        private float motionTransX(int target) {
            return motionTarget == target ? (float) dragX.value : 0f;
        }

        private float motionTransY(int target) {
            return motionTarget == target ? (float) dragY.value : 0f;
        }

        /** Aksesori juga view sendiri: press scale + deform area-conserving. */
        private void addAccShape(float cx, float cy, float radius, int which) {
            shapeB.reset();
            shapeB.addCircle(0f, 0f, radius, Path.Direction.CW);
            float ps = accPressScale(which);
            accMatrix.reset();
            accMatrix.setScale(ps * motionScaleX(which), ps * motionScaleY(which));
            accMatrix.postTranslate(cx + motionTransX(which), cy + motionTransY(which));
            shapeB.transform(accMatrix);
            unionIntoShapeA();
        }

        private void unionIntoShapeA() {
            if (shapeC.op(shapeA, shapeB, Path.Op.UNION)) shapeA.set(shapeC);
        }

        private static double unit(double v) {
            return v < -1.0 ? -1.0 : (v > 1.0 ? 1.0 : v);
        }

        /**
         * Leher metaball antara dua lingkaran (ujung kiri kapsul dan aksesori): dua kurva
         * bezier yang menyinggung kedua lingkaran, sehingga sambungan melengkung seperti
         * cairan, bukan lipatan tajam hasil UNION polos. Lebar leher menyusut ke nol saat
         * celah mencapai MELT_MAX_GAP_DP, jadi putusnya halus. Ini pendekatan geometri untuk
         * OplusMaterialMetaBallParams (SDF) yang dipakai COUI.
         */
        private void addMeltNeck(float c1x, float c1y, float r1, float c2x, float c2y, float r2) {
            float dx = c2x - c1x;
            float dy = c2y - c1y;
            float d = (float) Math.hypot(dx, dy);
            if (r1 <= 0f || r2 <= 0f || d <= Math.abs(r1 - r2) + 0.5f) return;
            float gap = d - r1 - r2;
            float maxGap = dp(MELT_MAX_GAP_DP);
            if (gap >= maxGap) return;
            double v = 0.5 * (gap <= 0f ? 1.0 : 1.0 - gap / maxGap);
            if (v < 0.02) return;

            double u1 = 0.0;
            double u2 = 0.0;
            if (d < r1 + r2) {
                u1 = Math.acos(unit((r1 * r1 + d * d - r2 * r2) / (2.0 * r1 * d)));
                u2 = Math.acos(unit((r2 * r2 + d * d - r1 * r1) / (2.0 * r2 * d)));
            }
            double between = Math.atan2(dy, dx);
            double spread = Math.acos(unit((r1 - r2) / d));
            double a1 = between + u1 + (spread - u1) * v;
            double a2 = between - u1 - (spread - u1) * v;
            double a3 = between + Math.PI - u2 - (Math.PI - u2 - spread) * v;
            double a4 = between - Math.PI + u2 + (Math.PI - u2 - spread) * v;

            float p1x = c1x + r1 * (float) Math.cos(a1), p1y = c1y + r1 * (float) Math.sin(a1);
            float p2x = c1x + r1 * (float) Math.cos(a2), p2y = c1y + r1 * (float) Math.sin(a2);
            float p3x = c2x + r2 * (float) Math.cos(a3), p3y = c2y + r2 * (float) Math.sin(a3);
            float p4x = c2x + r2 * (float) Math.cos(a4), p4y = c2y + r2 * (float) Math.sin(a4);

            float total = r1 + r2;
            double handle = Math.min(v * 2.4, Math.hypot(p1x - p3x, p1y - p3y) / total)
                    * Math.min(1.0, d * 2.0 / total);
            float h1 = (float) (r1 * handle);
            float h2 = (float) (r2 * handle);
            double q = Math.PI / 2.0;

            shapeB.reset();
            shapeB.moveTo(p1x, p1y);
            shapeB.cubicTo(p1x + h1 * (float) Math.cos(a1 - q), p1y + h1 * (float) Math.sin(a1 - q),
                    p3x + h2 * (float) Math.cos(a3 + q), p3y + h2 * (float) Math.sin(a3 + q),
                    p3x, p3y);
            shapeB.lineTo(p4x, p4y);
            shapeB.cubicTo(p4x + h2 * (float) Math.cos(a4 - q), p4y + h2 * (float) Math.sin(a4 - q),
                    p2x + h1 * (float) Math.cos(a2 + q), p2y + h1 * (float) Math.sin(a2 + q),
                    p2x, p2y);
            shapeB.close();
            unionIntoShapeA();
        }

        private void buildShapes() {
            float r = capH() / 2f;
            float w = capW();
            float h = capH();
            rect.set(capLeft(), capTop(), capLeft() + w, capTop() + h);
            shapeA.reset();
            shapeA.addRoundRect(rect, r, r, Path.Direction.CW);

            buildCapsuleTransform(w, h);
            shapeA.transform(jelly);

            if (leftAccAlpha() > 0.01f) {
                float ar = Math.max(dp(2), leftAccRadius());
                addAccShape((float) accLeft.value, accCy(), ar, 3);
                // Ujung kiri kapsul = setengah lingkaran berpusat di (capLeft + r, tengah).
                // Radius dikali skala terkecil supaya titik singgung tidak keluar dari bentuk.
                meltPt[0] = capLeft() + r;
                meltPt[1] = capTop() + h / 2f;
                jelly.mapPoints(meltPt);
                float as = accPressScale(3) * Math.min(motionScaleX(3), motionScaleY(3));
                addMeltNeck(meltPt[0], meltPt[1], r * Math.min(jSx, jSy),
                        (float) accLeft.value + motionTransX(3), accCy() + motionTransY(3),
                        ar * as);
            }
            addAccShape(rightAccCx(), accCy(), accR(), 4);

            shapes.set(shapeA);
        }

        private void buildCapsuleTransform(float w, float h) {
            float ccx = capLeft() + w / 2f;
            float ccy = capTop() + h / 2f;
            float ps = capsulePressScale();
            float sx = ps * motionScaleX(1);
            float sy = ps * motionScaleY(1);
            float tx = motionTransX(1);
            float ty = motionTransY(1);
            jelly.reset();
            jelly.setScale(sx, sy, ccx, ccy);
            jelly.postTranslate(tx, ty);
            // Ikon + label ikut transform penuh, seperti children di menu view COUI.
            ink.set(jelly);
            jSx = sx;
            jSy = sy;
            jTx = tx;
            jTy = ty;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int w = getWidth();
            int h = getHeight();
            if (w == 0 || h == 0) return;

            canvas.save();
            canvas.translate(0, (float) squish.value);

            buildShapes();
            boolean hasBlur = !windowMode && mode != MODE_SOLID
                    && canvas.isHardwareAccelerated() && updateBackdrop();
            drawShadow(canvas, hasBlur);

            if (windowMode) {
                fill.setStyle(Paint.Style.FILL);
                fill.setColor(crossBlurEnabled
                        ? (dark ? 0x66101418 : 0x55F2F3F7)
                        : (dark ? 0xFF1E1E1E : 0xFFFAFAFA));
                canvas.drawPath(shapes, fill);
                drawItems(canvas);
                drawAccessories(canvas);
                textPaint.setColor(dark ? 0x99FFFFFF : 0x99000000);
                textPaint.setTextSize(dp(9));
                textPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText("window blur: "
                                + (crossBlurEnabled ? "ON (API 31)" : "OFF (hardware/OEM)"),
                        dp(16), dp(11), textPaint);
                canvas.restore();
                return;
            }

            if (!hasBlur) {
                fill.setStyle(Paint.Style.FILL);
                fill.setColor(dark ? 0xFF1E1E1E : 0xFFFAFAFA);
                canvas.drawPath(shapes, fill);
            } else if (mode == MODE_SPLIT) {
                int s1 = canvas.save();
                canvas.clipPath(shapes);
                canvas.clipRect(0, 0, w / 2f, h);
                drawBackdrop(canvas, blurNode);
                canvas.restoreToCount(s1);

                int s2 = canvas.save();
                canvas.clipPath(shapes);
                canvas.clipRect(w / 2f, 0, w, h);
                fill.setStyle(Paint.Style.FILL);
                fill.setColor(dark ? 0xFF1E1E1E : 0xFFFAFAFA);
                canvas.drawRect(0, 0, w, h, fill);
                canvas.restoreToCount(s2);
            } else {
                int s1 = canvas.save();
                canvas.clipPath(shapes);
                drawGlass(canvas);
                canvas.restoreToCount(s1);
            }

            if (hasBlur) drawStroke(canvas);

            drawPressLight(canvas);

            int cs = canvas.save();
            canvas.concat(ink);
            drawIndicator(canvas);
            drawItems(canvas);
            canvas.restoreToCount(cs);

            drawAccessories(canvas);

            canvas.restore();
        }

        /**
         * InteractiveHighlight: seluruh muka kaca terangkat tipis, lalu pool cahaya gaussian
         * menyusul jari. Digambar di atas kaca, di bawah tinta — sama seperti referensi.
         */
        private void drawPressLight(Canvas canvas) {
            float glow = clamp((float) glowSw.value, 0f, 1f);
            if (glow <= 0.01f) return;
            int save = canvas.save();
            canvas.clipPath(shapes);

            lightPaint.setStyle(Paint.Style.FILL);
            lightPaint.setBlendMode(BlendMode.PLUS);

            // 1) lift: 5.5% putih menaikkan muka kontrol yang ditekan (MorphPress.LightLift)
            lightPaint.setShader(null);
            lightPaint.setColor(0xFFFFFFFF);
            lightPaint.setAlpha(Math.round(255f * LIGHT_LIFT * glow));
            if (lightTarget == 1) {
                canvas.concat(jelly);
                canvas.drawRect(capLeft() - dp(8), capTop() - dp(8),
                        capLeft() + capW() + dp(8), capTop() + capH() + dp(8), lightPaint);
            } else {
                float acx = lightTarget == 3 ? (float) accLeft.value : rightAccCx();
                float reach = accR() + dp(RUBBER_MAX_DP);
                canvas.drawRect(acx - reach, accCy() - reach, acx + reach, accCy() + reach,
                        lightPaint);
            }

            // 2) pool: gaussian di bawah jari, radius 38dp (MorphPress.LightPool)
            float lx = capLeft() + (float) lightX.value;
            float ly = capTop() + (float) lightY.value;
            float r = Math.max(1f, dp(LIGHT_RADIUS_DP)) * 2.2f;
            // Alpha dibakar ke warna: Paint.setAlpha() tidak berefek pada shader di GPU ini
            // (Skia: alpha paint + shader + blend mode != SRC_OVER), jadi pakai warna langsung.
            // Shader berpusat di (0,0) dan hanya dibuat ulang saat alpha berubah.
            int a0 = Math.min(255, Math.round(255f * LIGHT_POOL * glow * lightGain));
            if (a0 > 1) {
                if (lightShader == null || lightShaderAlpha != a0) {
                    int a1 = Math.round(a0 * 0.62f);
                    int a2 = Math.round(a0 * 0.20f);
                    lightShader = new RadialGradient(0f, 0f, r,
                            new int[]{(a0 << 24) | 0xFFFFFF, (a1 << 24) | 0xFFFFFF,
                                    (a2 << 24) | 0xFFFFFF, 0x00FFFFFF},
                            new float[]{0f, 0.28f, 0.62f, 1f}, Shader.TileMode.CLAMP);
                    lightShaderAlpha = a0;
                }
                lightPaint.setAlpha(255);
                lightPaint.setShader(lightShader);
                canvas.translate(lx, ly);
                canvas.drawCircle(0f, 0f, r, lightPaint);
            }

            lightPaint.setShader(null);
            lightPaint.setBlendMode(BlendMode.SRC_OVER);
            canvas.restoreToCount(save);
        }

        private void drawShadow(Canvas canvas, boolean material) {
            // Kunci = semua yang mengubah siluet, termasuk posisi/skala aksesori saat melt
            // (dulu tidak ikut, jadi bayangan aksesori melompat di akhir animasi).
            long key = getWidth();
            key = key * 31 + (dark ? 1 : 0);
            key = key * 31 + motionTarget;
            key = key * 31 + Math.round((float) pressSw.value * 50);
            key = key * 31 + Math.round((float) accS.value * 50);
            key = key * 31 + Math.round((float) jx.value * 200);
            key = key * 31 + Math.round((float) jy.value * 200);
            key = key * 31 + Math.round((float) dragX.value);
            key = key * 31 + Math.round((float) dragY.value);
            key = key * 31 + Math.round((float) accLeft.value);
            key = key * 31 + Math.round((float) accScale.value * 100);
            if (shadowNode == null || key != shadowKey
                    || (material && (causticNode == null || key != causticKey))) {
                int w = getWidth();
                int h = getHeight();
                if (shadowNode == null || shadowNode.getWidth() != w
                        || shadowNode.getHeight() != h) {
                    shadowNode = new RenderNode("nav-shadow");
                    shadowNode.setPosition(0, 0, w, h);
                    shadowNode.setRenderEffect(RenderEffect.createBlurEffect(
                            dp(12), dp(12), Shader.TileMode.CLAMP));
                }
                // Fallback COUI: elevation 2, shadow size level 3 (12dp), #8A000000.
                shadowPaint.setColor(0x8A000000);
                RecordingCanvas rc = shadowNode.beginRecording();
                rc.drawPath(shapes, shadowPaint);
                shadowNode.endRecording();
                shadowKey = key;

                if (material) {
                    if (causticNode == null || causticNode.getWidth() != w
                            || causticNode.getHeight() != h) {
                        causticNode = new RenderNode("nav-caustic");
                        causticNode.setPosition(0, 0, w, h);
                        causticNode.setRenderEffect(RenderEffect.createBlurEffect(
                                dp(10.4f), dp(10.4f), Shader.TileMode.CLAMP));
                    }
                    // Caustic COUI: #42FFFFFF, layout level 4 (bottom 4.9dp, SDF 10.4dp).
                    causticPaint.setColor(0x42FFFFFF);
                    RecordingCanvas rc2 = causticNode.beginRecording();
                    rc2.drawPath(shapes, causticPaint);
                    causticNode.endRecording();
                    causticKey = key;
                }
            }
            int save = canvas.save();
            canvas.translate(0, dp(2));
            canvas.drawRenderNode(shadowNode);
            canvas.restoreToCount(save);

            if (material && causticNode != null) {
                save = canvas.save();
                // Caustic hanya di luar bentuk; kalau tidak, glow putih menembus kapsul
                // yang translucent dan membuat bar terlihat opaque.
                canvas.clipOutPath(shapes);
                canvas.translate(0, dp(4.9f));
                canvas.drawRenderNode(causticNode);
                canvas.restoreToCount(save);
            }
        }

        private void drawStroke(Canvas canvas) {
            // Approx TYPE_FRAMEWORK_CAPSULE_3: rim tipis + faint outer glow.
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setShader(null);
            strokePaint.setStrokeWidth(dp(3));
            strokePaint.setColor(dark ? 0x14FFFFFF : 0x1AFFFFFF);
            canvas.drawPath(shapes, strokePaint);

            // Garis 1 px: terang di atas, memudar ke bawah. Gradient vertikal setinggi kapsul
            // berlaku sama untuk kapsul dan kedua aksesori (tingginya sama), jadi tiap bentuk
            // punya highlight sendiri. Versi diagonal selebar kapsul membuat aksesori kiri
            // terang seluruhnya dan aksesori kanan gelap seluruhnya.
            strokePaint.setStrokeWidth(dp(1));
            int shaderKey = getHeight() * 2 + (dark ? 1 : 0);
            if (strokeShader == null || strokeShaderKey != shaderKey) {
                int c1 = dark ? 0x66FFFFFF : 0x70FFFFFF;
                int c2 = 0x1AFFFFFF;
                int c3 = dark ? 0x33000000 : 0x22000000;
                strokeShader = new LinearGradient(0, capTop(), 0, capTop() + capH(),
                        new int[]{c1, c2, c3}, new float[]{0f, 0.55f, 1f},
                        Shader.TileMode.CLAMP);
                strokeShaderKey = shaderKey;
            }
            strokePaint.setShader(strokeShader);
            canvas.drawPath(shapes, strokePaint);
            strokePaint.setShader(null);
        }

        private void drawIndicator(Canvas canvas) {
            float a = (float) indA.value;
            if (a <= 0.01f) return;
            float cx = (float) indX.value;
            float iw = (float) indW.value;
            float top = capTop() + dp(4);
            float bottom = capTop() + capH() - dp(4);
            float r = (bottom - top) / 2f;

            fill.setStyle(Paint.Style.FILL);
            fill.setColor(dark ? 0xFFFFFF : 0x000000);
            fill.setAlpha((int) ((dark ? 0x3A : 0x20) * a));
            canvas.drawRoundRect(cx - iw / 2f, top, cx + iw / 2f, bottom, r, r, fill);
            fill.setAlpha(255);
        }

        private int activeColor() {
            return dark ? 0xE8FFFFFF : 0xE6111111;
        }

        private int inactiveColor() {
            return dark ? 0xB8FFFFFF : 0x9E111111;
        }

        private int withAlpha(int color, float mult) {
            int a = Math.round(((color >>> 24) & 0xFF) * clamp(mult, 0f, 1f));
            return (color & 0x00FFFFFF) | (a << 24);
        }

        private int lerpColor(int c1, int c2, float t) {
            t = clamp(t, 0f, 1f);
            int a = (int) (((c1 >>> 24) & 0xFF) + ((((c2 >>> 24) & 0xFF) - ((c1 >>> 24) & 0xFF)) * t));
            int r = (int) (((c1 >> 16) & 0xFF) + ((((c2 >> 16) & 0xFF) - ((c1 >> 16) & 0xFF)) * t));
            int g = (int) (((c1 >> 8) & 0xFF) + ((((c2 >> 8) & 0xFF) - ((c1 >> 8) & 0xFF)) * t));
            int b = (int) ((c1 & 0xFF) + (((c2 & 0xFF) - (c1 & 0xFF)) * t));
            return (a << 24) | (r << 16) | (g << 8) | b;
        }

        private void drawItems(Canvas canvas) {
            float sel = clamp((float) selAnim.value, 0f, 1f);
            for (int i = 0; i < N; i++) {
                float t = i == selected ? sel : (i == prevSelected ? 1f - sel : 0f);
                float cx = itemCx(i);

                if (i == 0) drawPhotoIcon(canvas, cx, capTop() + dp(20), t);
                else drawAlbumIcon(canvas, cx, capTop() + dp(20), t);

                textPaint.setColor(lerpColor(inactiveColor(), activeColor(), t));
                textPaint.setTextSize(dp(12));
                canvas.drawText(LABELS[i], cx, capTop() + dp(47), textPaint);
            }
        }

        /** @param amount 0 = outline (pasif), 1 = filled (aktif); nilai antara = crossfade. */
        private void drawPhotoIcon(Canvas canvas, float cx, float cy, float amount) {
            float a = clamp(amount, 0f, 1f);
            float s = dp(10);
            rect.set(cx - s, cy - s, cx + s, cy + s);
            float r = dp(4.5f);

            if (a < 0.995f) {
                int col = withAlpha(inactiveColor(), 1f - a);
                iconPaint.setStyle(Paint.Style.STROKE);
                iconPaint.setStrokeWidth(dp(1.6f));
                iconPaint.setStrokeJoin(Paint.Join.ROUND);
                iconPaint.setColor(col);
                canvas.drawRoundRect(rect, r, r, iconPaint);

                iconPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(cx - s * 0.45f, cy - s * 0.42f, dp(1.9f), iconPaint);

                iconPaint.setStyle(Paint.Style.STROKE);
                path.reset();
                path.moveTo(cx - s * 0.92f, cy + s * 0.35f);
                path.lineTo(cx - s * 0.45f, cy - s * 0.18f);
                path.lineTo(cx - s * 0.05f, cy + s * 0.22f);
                path.lineTo(cx + s * 0.42f, cy - s * 0.30f);
                path.lineTo(cx + s * 0.92f, cy + s * 0.22f);
                canvas.drawPath(path, iconPaint);
            }

            if (a > 0.005f) {
                int layer = canvas.saveLayerAlpha(rect.left - dp(1), rect.top - dp(1),
                        rect.right + dp(1), rect.bottom + dp(1), Math.round(255 * a));
                iconPaint.setStyle(Paint.Style.FILL);
                iconPaint.setColor(activeColor());
                canvas.drawRoundRect(rect, r, r, iconPaint);

                iconPaint.setColor(0xFF000000);
                iconPaint.setBlendMode(BlendMode.CLEAR);
                canvas.drawCircle(cx - s * 0.45f, cy - s * 0.42f, dp(2.0f), iconPaint);

                path.reset();
                path.moveTo(cx - s * 0.84f, cy + s * 0.82f);
                path.lineTo(cx - s * 0.84f, cy + s * 0.10f);
                path.lineTo(cx - s * 0.48f, cy - s * 0.26f);
                path.lineTo(cx - s * 0.12f, cy + s * 0.18f);
                path.lineTo(cx + s * 0.30f, cy - s * 0.32f);
                path.lineTo(cx + s * 0.84f, cy + s * 0.22f);
                path.lineTo(cx + s * 0.84f, cy + s * 0.82f);
                path.close();
                canvas.drawPath(path, iconPaint);
                iconPaint.setBlendMode(BlendMode.SRC_OVER);
                canvas.restoreToCount(layer);
            }
        }

        /** @param amount 0 = outline, 1 = filled (crossfade). */
        private void drawAlbumIcon(Canvas canvas, float cx, float cy, float amount) {
            float a = clamp(amount, 0f, 1f);
            float s = dp(9.5f);
            RectF body = new RectF(cx - s, cy - s * 0.52f, cx + s, cy + s * 0.92f);
            RectF lid = new RectF(cx - s * 0.70f, cy - s * 0.96f, cx + s * 0.70f, cy - s * 0.80f);
            float r = dp(4.5f);
            float slotTop = cy + s * 0.48f;
            float slotBottom = cy + s * 0.64f;

            if (a < 0.995f) {
                iconPaint.setStyle(Paint.Style.STROKE);
                iconPaint.setStrokeWidth(dp(1.55f));
                iconPaint.setStrokeJoin(Paint.Join.ROUND);
                iconPaint.setColor(withAlpha(inactiveColor(), 1f - a));
                canvas.drawRoundRect(body, r, r, iconPaint);
                canvas.drawRoundRect(lid, dp(1.6f), dp(1.6f), iconPaint);
                rect.set(cx - s * 0.42f, slotTop, cx + s * 0.42f, slotBottom);
                canvas.drawRoundRect(rect, dp(2.6f), dp(2.6f), iconPaint);
            }

            if (a > 0.005f) {
                int layer = canvas.saveLayerAlpha(cx - s - dp(1), cy - s - dp(1),
                        cx + s + dp(1), cy + s + dp(1), Math.round(255 * a));
                iconPaint.setStyle(Paint.Style.FILL);
                iconPaint.setColor(activeColor());
                canvas.drawRoundRect(body, r, r, iconPaint);
                canvas.drawRoundRect(lid, dp(1.6f), dp(1.6f), iconPaint);

                iconPaint.setColor(0xFF000000);
                iconPaint.setBlendMode(BlendMode.CLEAR);
                rect.set(cx - s * 0.42f, slotTop, cx + s * 0.42f, slotBottom);
                canvas.drawRoundRect(rect, dp(2.6f), dp(2.6f), iconPaint);
                iconPaint.setBlendMode(BlendMode.SRC_OVER);
                canvas.restoreToCount(layer);
            }
        }

        private void drawAccessories(Canvas canvas) {
            float la = leftAccAlpha();
            if (la > 0.01f) {
                float lx = (float) accLeft.value + motionTransX(3);
                float ly = accCy() + motionTransY(3);
                float ps = accPressScale(3);
                float sx = ps * motionScaleX(3);
                float sy = ps * motionScaleY(3);
                int save = canvas.save();
                canvas.translate(lx, ly);
                canvas.scale(sx, sy);
                drawSortIcon(canvas, 0f, 0f, la);
                canvas.restoreToCount(save);
            }

            // crossfade ikon kanan: filter (Photos) <-> plus (Albums)
            float sel = clamp((float) selAnim.value, 0f, 1f);
            float plusA = selected == 1 ? sel : 1f - sel;
            float filterA = 1f - plusA;
            float rx = rightAccCx() + motionTransX(4);
            float ry = accCy() + motionTransY(4);
            float ps = accPressScale(4);
            float sx = ps * motionScaleX(4);
            float sy = ps * motionScaleY(4);
            int save = canvas.save();
            canvas.translate(rx, ry);
            canvas.scale(sx, sy);
            if (filterA > 0.005f) drawFilterIcon(canvas, 0f, 0f, filterA);
            if (plusA > 0.005f) drawPlusIcon(canvas, 0f, 0f, plusA);
            canvas.restoreToCount(save);
        }

        private void drawSortIcon(Canvas canvas, float cx, float cy, float alpha) {
            iconPaint.setStyle(Paint.Style.STROKE);
            iconPaint.setStrokeWidth(dp(1.7f));
            iconPaint.setStrokeCap(Paint.Cap.ROUND);
            iconPaint.setColor(withAlpha(dark ? 0xE0FFFFFF : 0xDD111111,
                    alpha * (float) accScale.value));

            float half = dp(9);
            float r = dp(3.3f);

            float ty = cy - dp(4.5f);
            float kx = cx + dp(4.2f);
            canvas.drawLine(cx - half, ty, kx - r, ty, iconPaint);
            canvas.drawLine(kx + r, ty, cx + half, ty, iconPaint);
            canvas.drawCircle(kx, ty, r, iconPaint);

            float by = cy + dp(4.5f);
            float kx2 = cx - dp(4.2f);
            canvas.drawLine(cx - half, by, kx2 - r, by, iconPaint);
            canvas.drawLine(kx2 + r, by, cx + half, by, iconPaint);
            canvas.drawCircle(kx2, by, r, iconPaint);
        }

        private void drawFilterIcon(Canvas canvas, float cx, float cy, float alpha) {
            iconPaint.setStyle(Paint.Style.STROKE);
            iconPaint.setStrokeWidth(dp(2f));
            iconPaint.setStrokeCap(Paint.Cap.ROUND);
            iconPaint.setColor(withAlpha(dark ? 0xE0FFFFFF : 0xDD111111, alpha));

            canvas.drawLine(cx - dp(10), cy - dp(6.5f), cx + dp(10), cy - dp(6.5f), iconPaint);
            canvas.drawLine(cx - dp(6.5f), cy, cx + dp(6.5f), cy, iconPaint);
            canvas.drawLine(cx - dp(3.5f), cy + dp(6.5f), cx + dp(3.5f), cy + dp(6.5f), iconPaint);
        }

        private void drawPlusIcon(Canvas canvas, float cx, float cy, float alpha) {
            iconPaint.setStyle(Paint.Style.STROKE);
            iconPaint.setStrokeWidth(dp(1.9f));
            iconPaint.setStrokeCap(Paint.Cap.ROUND);
            iconPaint.setColor(withAlpha(dark ? 0xE0FFFFFF : 0xDD111111, alpha));
            canvas.drawLine(cx - dp(9), cy, cx + dp(9), cy, iconPaint);
            canvas.drawLine(cx, cy - dp(9), cx, cy + dp(9), iconPaint);
        }

        int dp(float v) {
            return Math.round(v * getResources().getDisplayMetrics().density);
        }
    }

    private static int clamp(int v, int min, int max) {
        return v < min ? min : (v > max ? max : v);
    }

    private static float clamp(float v, float min, float max) {
        return v < min ? min : (v > max ? max : v);
    }
}
