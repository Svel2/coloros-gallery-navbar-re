package com.oplus.aiunit.vision;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.COUIMaterialBlurEffect;
import com.coui.appcompat.COUIMaterialEffect;
import com.coui.appcompat.COUIMaterialStateManager;
import com.coui.appcompat.COUIMaterialStrokeEffect;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.coui.appcompat.animation.touchanimation.COUITouchMotionAnimator;
import com.coui.appcompat.contextutil.COUIContextUtil;
import com.coui.appcompat.darkmode.COUIDarkModeUtil;
import com.coui.appcompat.spotlight.COUISpotLightEffect;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;
import com.coui.appcompat.uiutil.ShadowUtils;
import com.coui.appcompat.uiutil.UIUtil;
import com.oplus.view.material.OplusMaterialCornerParams;
import com.support.bottomnavigation.R;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
public final class u16 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1768a;
    public final o16 b;
    public Float e;
    public OplusMaterialCornerParams f;
    public COUITouchMotionAnimator g;
    public boolean h;
    public float i;
    public COUISpotLightEffectDrawable j;
    public COUISpringAnimation m;
    public final guk n;
    public final Lazy c = LazyKt.lazy(new r16());
    public final Lazy d = LazyKt.lazy(new s16());
    public final Path k = new Path();
    public final RectF l = new RectF();

    public u16(View view, o16 o16Var) {
        this.f1768a = view;
        this.b = o16Var;
        view.getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_touch_effect_radius);
        Context context = view.getContext();
        context.getClass();
        this.n = new guk(context);
    }

    public static void a(u16 u16Var, float f) {
        Integer numValueOf = Integer.valueOf(com.support.appcompat.R.color.coui_color_bar);
        View view = u16Var.f1768a;
        Float f2 = u16Var.e;
        float fFloatValue = f2 != null ? f2.floatValue() : view.getHeight() / 2.0f;
        int color = view.getContext().getResources().getColor(numValueOf.intValue());
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(fFloatValue);
        gradientDrawable.setColor(color);
        gradientDrawable.setAlpha((int) (f * 255.0f));
        view.setBackground(gradientDrawable);
    }

    public final void b() {
        OplusMaterialCornerParams oplusMaterialCornerParams;
        if (COUIMaterialStateManager.INSTANCE.isBlurEffectEnable()) {
            oplusMaterialCornerParams = new OplusMaterialCornerParams(0.0f, 2.0f);
            COUIMaterialEffect.setCornerParams(this.f1768a, oplusMaterialCornerParams);
        } else {
            oplusMaterialCornerParams = null;
        }
        this.f = oplusMaterialCornerParams;
    }

    public final void c() {
        this.n.b(1.0f, false);
        if (this.b.e) {
            COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.j;
            if (cOUISpotLightEffectDrawable != null) {
                cOUISpotLightEffectDrawable.setCallback(null);
            }
            COUISpotLightEffectDrawable cOUISpotLightEffectDrawable2 = this.j;
            if (cOUISpotLightEffectDrawable2 != null) {
                cOUISpotLightEffectDrawable2.cancel();
            }
            COUISpringAnimation cOUISpringAnimation = this.m;
            if (cOUISpringAnimation != null) {
                cOUISpringAnimation.cancel();
            }
        }
    }

    public final void d(Canvas canvas) {
        canvas.getClass();
        COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
        if (!cOUIMaterialStateManager.materialVersionCheck() && ((Boolean) this.d.getValue()).booleanValue()) {
            guk.a(this.n, canvas);
        }
        if (this.b.e) {
            if (cOUIMaterialStateManager.isSpotLightEffectEnable()) {
                Path path = this.k;
                path.reset();
                View view = this.f1768a;
                float measuredHeight = view.getMeasuredHeight() / 2.0f;
                float measuredWidth = view.getMeasuredWidth();
                float measuredHeight2 = view.getMeasuredHeight();
                RectF rectF = this.l;
                rectF.set(0.0f, 0.0f, measuredWidth, measuredHeight2);
                path.addRoundRect(rectF, measuredHeight, measuredHeight, Path.Direction.CCW);
                int iSave = canvas.save();
                canvas.clipPath(path);
                COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.j;
                if (cOUISpotLightEffectDrawable != null) {
                    cOUISpotLightEffectDrawable.draw(canvas);
                }
                canvas.restoreToCount(iSave);
            }
            cOUIMaterialStateManager.isSpotLightEffectEnable();
        }
    }

    public final void e() {
        b();
        m();
        n(1.0f);
        o16 o16Var = this.b;
        k(o16Var.f1300a, 1.0f);
        if (o16Var.e) {
            o();
            COUISpringAnimation cOUISpringAnimationA = go7.a(new FloatValueHolder(0.0f), new COUISpringForce().setBounce(0.0f).setResponse(0.2f));
            q16 q16Var = new q16();
            q16Var.f1464a = this;
            VarHandle.storeStoreFence();
            cOUISpringAnimationA.addUpdateListener(q16Var);
            this.m = cOUISpringAnimationA;
        }
        boolean z = o16Var.f;
        COUITouchMotionAnimator cOUITouchMotionAnimator = new COUITouchMotionAnimator(this.f1768a.getContext());
        COUITouchMotionAnimator pressScaleEnable = cOUITouchMotionAnimator.setDeformationEnable(z).setRubberEnable(z).setPressScaleEnable(true);
        t16 t16Var = new t16();
        t16Var.f1695a = this;
        t16Var.b = z;
        VarHandle.storeStoreFence();
        pressScaleEnable.addOnAnimationUpdateCallback(t16Var);
        this.g = cOUITouchMotionAnimator;
    }

    public final void f(int i) {
        if (i == 1) {
            n(1.0f);
            return;
        }
        o16 o16Var = this.b;
        if (i != 2) {
            if (i == 4 && o16Var.e) {
                o();
                return;
            }
            return;
        }
        m();
        k(o16Var.f1300a, 1.0f);
        b();
        View view = this.f1768a;
        l(((Number) q1q.n(new Integer[]{Integer.valueOf(view.getHeight()), Integer.valueOf(view.getWidth())})).floatValue() / 2.0f);
    }

    public final void g(int i, int i2) {
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable;
        float fMin = (i <= 0 || i2 <= 0) ? 0.0f : Math.min(i, i2) / 2.0f;
        if (!COUIMaterialStateManager.INSTANCE.materialVersionCheck() && ((Boolean) this.d.getValue()).booleanValue()) {
            this.n.d(i, i2, Float.valueOf(fMin));
        }
        COUITouchMotionAnimator cOUITouchMotionAnimator = this.g;
        if (cOUITouchMotionAnimator != null) {
            cOUITouchMotionAnimator.onSizeChange(i, i2);
        }
        if (this.b.e && (cOUISpotLightEffectDrawable = this.j) != null) {
            cOUISpotLightEffectDrawable.setBounds(0, 0, i, i2);
        }
        l(fMin);
    }

    public final void h(MotionEvent motionEvent) {
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable;
        motionEvent.getClass();
        if (motionEvent.getActionMasked() == 0) {
            this.i = this.f1768a.getTranslationX();
        }
        COUITouchMotionAnimator cOUITouchMotionAnimator = this.g;
        if (cOUITouchMotionAnimator != null) {
            cOUITouchMotionAnimator.handleTouchEvent(motionEvent);
        }
        if (this.b.e) {
            COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
            if (cOUIMaterialStateManager.isFeatureEnabled() && cOUIMaterialStateManager.isSpotLightEffectEnable() && (cOUISpotLightEffectDrawable = this.j) != null) {
                cOUISpotLightEffectDrawable.onTouchEvent(motionEvent);
            }
        }
    }

    public final void i(float f, boolean z) {
        o16 o16Var = this.b;
        boolean z2 = o16Var.b;
        guk gukVar = this.n;
        View view = this.f1768a;
        if (!z2 || !z) {
            COUIMaterialEffect.setViewBlurEffect(view, COUIMaterialBlurEffect.BlurEffectType.TYPE_NO_EFFECT);
            COUIMaterialEffect.setViewStrokeEffect(view, COUIMaterialStrokeEffect.StrokeEffectType.TYPE_NO_EFFECT);
            gukVar.b(1.0f, false);
            if (o16Var.e) {
                COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.j;
                if (cOUISpotLightEffectDrawable != null) {
                    cOUISpotLightEffectDrawable.setEnabled(false);
                }
                COUISpotLightEffectDrawable cOUISpotLightEffectDrawable2 = this.j;
                if (cOUISpotLightEffectDrawable2 != null) {
                    cOUISpotLightEffectDrawable2.cancel();
                }
                COUISpringAnimation cOUISpringAnimation = this.m;
                if (cOUISpringAnimation != null) {
                    cOUISpringAnimation.cancel();
                }
            }
            a(this, f);
            view.invalidate();
            return;
        }
        view.setBackground(null);
        b();
        m();
        n(f);
        k(o16Var.f1300a, f);
        COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
        if (cOUIMaterialStateManager.materialVersionCheck()) {
            view.setAlpha(f);
        } else if (!cOUIMaterialStateManager.materialVersionCheck() && ((Boolean) this.d.getValue()).booleanValue()) {
            p16 p16Var = new p16();
            p16Var.f1383a = f;
            VarHandle.storeStoreFence();
            i3j.e(p16Var, 3, "BaseMaterialEffectDelegate");
            gukVar.b(f, true);
        }
        view.invalidate();
    }

    public final void j() {
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = this.j;
        if (cOUISpotLightEffectDrawable != null) {
            cOUISpotLightEffectDrawable.setEnabled(COUIMaterialStateManager.INSTANCE.isSpotLightEffectEnable() && COUIDarkModeUtil.isCOUIDarkThemeOrNightMode(this.f1768a.getContext()));
        }
    }

    public final void k(COUIMaterialBlurEffect.BlurEffectType blurEffectType, float f) {
        boolean z = this.b.b;
        View view = this.f1768a;
        if (!z) {
            COUIMaterialEffect.setViewBlurEffect(view, COUIMaterialBlurEffect.BlurEffectType.TYPE_NO_EFFECT);
            a(this, f);
            return;
        }
        COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
        if (!cOUIMaterialStateManager.materialVersionCheck()) {
            if (!((Boolean) this.c.getValue()).booleanValue()) {
                a(this, f);
                return;
            } else {
                Lazy lazy = g9d0.f685a;
                g9d0.k(view, f, null, 30);
                return;
            }
        }
        if (!cOUIMaterialStateManager.isBlurEffectEnable()) {
            COUIMaterialEffect.setViewBlurEffect(view, COUIMaterialBlurEffect.BlurEffectType.TYPE_NO_EFFECT);
            a(this, f);
            return;
        }
        Lazy lazy2 = g9d0.f685a;
        blurEffectType.getClass();
        Context context = view.getContext();
        context.getClass();
        COUIMaterialBlurEffect.BlurEffectParams blurParamsByType = COUIMaterialEffect.getBlurParamsByType(context, blurEffectType);
        float fCoerceIn = RangesKt.coerceIn(f, 0.0f, 1.0f);
        Context context2 = view.getContext();
        context2.getClass();
        COUIMaterialEffect.setViewBlurEffect(view, new COUIMaterialBlurEffect.BlurEffectParams(blurParamsByType.getBlurRadius(context2) * fCoerceIn, g9d0.p(blurParamsByType.getColorLayer1(view.getContext())), blurParamsByType.getBlendModeLayer1(), g9d0.p(blurParamsByType.getColorLayer2(view.getContext())), blurParamsByType.getBlendModeLayer2()));
        view.setBackground(null);
    }

    public final void l(float f) {
        OplusMaterialCornerParams oplusMaterialCornerParams;
        boolean zIsBlurEffectEnable = COUIMaterialStateManager.INSTANCE.isBlurEffectEnable();
        View view = this.f1768a;
        if (zIsBlurEffectEnable && (oplusMaterialCornerParams = this.f) != null) {
            oplusMaterialCornerParams.setRadius(f);
            COUIMaterialEffect.setCornerParams(view, oplusMaterialCornerParams);
        }
        this.e = Float.valueOf(f);
        view.invalidate();
    }

    public final void m() {
        boolean z = this.b.d;
        View view = this.f1768a;
        if (!z) {
            UIUtil.setViewShadowClippingEnabled(view, false);
            view.setElevation(0.0f);
            view.setTranslationZ(0.0f);
            return;
        }
        UIUtil.setViewShadowClippingEnabled(view, true);
        if (COUIMaterialStateManager.INSTANCE.isBlurEffectEnable()) {
            ShadowUtils.setElevationToView(view, 2, view.getContext().getResources().getDimensionPixelOffset(com.support.appcompat.R.dimen.support_shadow_size_level_three), COUIContextUtil.getColor(view.getContext(), R.color.coui_bottom_floating_navigation_bar_menu_shadow_color));
            view.setOutlineProvider(new a());
        } else {
            view.setElevation(0.0f);
            view.setTranslationZ(0.0f);
        }
    }

    public final void n(float f) {
        boolean z = this.b.c;
        guk gukVar = this.n;
        View view = this.f1768a;
        if (!z) {
            COUIMaterialEffect.setViewStrokeEffect(view, COUIMaterialStrokeEffect.StrokeEffectType.TYPE_NO_EFFECT);
            gukVar.b(1.0f, false);
            view.invalidate();
            return;
        }
        COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
        if (cOUIMaterialStateManager.materialVersionCheck()) {
            gukVar.b(1.0f, false);
            if (cOUIMaterialStateManager.isStrokeEffectEnable()) {
                COUIMaterialEffect.setViewStrokeEffect(view, COUIMaterialStrokeEffect.StrokeEffectType.TYPE_FRAMEWORK_CAPSULE_3);
                return;
            } else {
                COUIMaterialEffect.setViewStrokeEffect(view, COUIMaterialStrokeEffect.StrokeEffectType.TYPE_NO_EFFECT);
                return;
            }
        }
        if (!((Boolean) this.d.getValue()).booleanValue()) {
            gukVar.b(1.0f, false);
            return;
        }
        gukVar.d(view.getWidth(), view.getHeight(), Float.valueOf((view.getWidth() <= 0 || view.getHeight() <= 0) ? 0.0f : Math.min(view.getWidth(), view.getHeight()) / 2.0f));
        gukVar.b(f, true);
        view.invalidate();
    }

    public final void o() {
        COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
        if (cOUIMaterialStateManager.isFeatureEnabled()) {
            cOUIMaterialStateManager.isSpotLightEffectEnable();
            if (this.j == null) {
                COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = new COUISpotLightEffectDrawable();
                Context context = this.f1768a.getContext();
                context.getClass();
                cOUISpotLightEffectDrawable.setSpotLightType(context, COUISpotLightEffect.SpotLightType.TYPE_BOTTOM_NAVIGATION);
                cOUISpotLightEffectDrawable.setLightColor(Color.red(-1) / 255.0f, Color.green(-1) / 255.0f, Color.blue(-1) / 255.0f);
                this.j = cOUISpotLightEffectDrawable;
            }
            j();
        }
    }
}
