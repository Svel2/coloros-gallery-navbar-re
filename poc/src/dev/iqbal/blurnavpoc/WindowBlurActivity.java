package dev.iqbal.blurnavpoc;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Varian "window blur" (API 31+): bar ditempatkan di window terpisah yang
 * memakai Window.setBackgroundBlurRadius(), jadi blur dilakukan oleh sistem
 * (cross-window blur), bukan snapshot.
 *
 * Keterbatasan: area blur mengikuti bounds window (persegi), tidak bisa
 * di-clip jadi kapsul; OEM juga bisa mematikan cross-window blur.
 */
public class WindowBlurActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean dark = getIntent().getIntExtra("dark", 0) == 1;

        Window w = getWindow();
        w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        w.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        w.setDimAmount(0f);
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        w.setGravity(Gravity.BOTTOM);
        w.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        boolean crossBlur = false;
        if (Build.VERSION.SDK_INT >= 31) {
            try {
                WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
                crossBlur = wm != null && wm.isCrossWindowBlurEnabled();
            } catch (Throwable ignored) {
            }
            try {
                w.addFlags(0x00000004 /* FLAG_BLUR_BEHIND */);
                w.setBackgroundBlurRadius(25);
            } catch (Throwable t) {
                crossBlur = false;
            }
        }

        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(Color.TRANSPARENT);

        MainActivity.NavBar bar = new MainActivity.NavBar(this);
        bar.setWindowMode(true, crossBlur);
        bar.setDark(dark);
        bar.setOnClickListener(v -> finish());

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(88));
        lp.gravity = Gravity.BOTTOM;
        root.addView(bar, lp);

        setContentView(root);
    }

    private int dp(float v) {
        return Math.round(v * getResources().getDisplayMetrics().density);
    }
}
