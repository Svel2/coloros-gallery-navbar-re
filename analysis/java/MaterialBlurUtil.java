package com.oplus.aiunit.vision;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.view.View;
import androidx.core.view.OneShotPreDrawListener;
import com.coui.appcompat.darkmode.COUIDarkModeUtil;
import com.oplus.gallery.basebiz.R$color;
import com.oplus.graphics.OplusRenderEffect;
import com.oplus.view.OplusViewBackgroundRenderEffect;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
public final class g9d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Lazy f685a = LazyKt.lazy(new d9d0());
    public static final Lazy b = LazyKt.lazy(new e9d0());

    public static final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public /* synthetic */ View f686a;
        public /* synthetic */ View b;
        public /* synthetic */ Ref.BooleanRef c;

        @Override // java.lang.Runnable
        public final void run() {
            this.b.setVisibility(0);
            this.c.element = true;
        }
    }

    public static void a(View view) {
        view.getClass();
        if (d() || e()) {
            OplusViewBackgroundRenderEffect.setBackgroundRenderEffect((RenderEffect) null, view);
        }
    }

    public static void b(View view) {
        OplusViewBackgroundRenderEffect.setBackgroundRenderEffect((RenderEffect) null, view);
    }

    public static void c(View view) {
        view.getClass();
        if (view.getVisibility() == 0) {
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            view.setVisibility(4);
            a aVar = new a();
            aVar.f686a = view;
            aVar.b = view;
            aVar.c = booleanRef;
            VarHandle.storeStoreFence();
            OneShotPreDrawListener.add(view, aVar);
            f9d0 f9d0Var = new f9d0();
            f9d0Var.f610a = view;
            f9d0Var.b = booleanRef;
            VarHandle.storeStoreFence();
            view.postOnAnimation(f9d0Var);
        }
    }

    public static boolean d() {
        return ((Boolean) b.getValue()).booleanValue();
    }

    public static boolean e() {
        return ((Boolean) f685a.getValue()).booleanValue();
    }

    public static void f(View view, float f, int i, BlendMode blendMode, int i2, BlendMode blendMode2) {
        view.getClass();
        blendMode.getClass();
        blendMode2.getClass();
        if (d()) {
            RenderEffect renderEffectCreateBlurEffect = RenderEffect.createBlurEffect(f, f, Shader.TileMode.MIRROR);
            renderEffectCreateBlurEffect.getClass();
            RenderEffect renderEffectCreateColorFilterEffect = RenderEffect.createColorFilterEffect(new BlendModeColorFilter(i, blendMode), renderEffectCreateBlurEffect);
            renderEffectCreateColorFilterEffect.getClass();
            if (i2 != 0) {
                renderEffectCreateColorFilterEffect = RenderEffect.createColorFilterEffect(new BlendModeColorFilter(i2, blendMode2), renderEffectCreateColorFilterEffect);
                renderEffectCreateColorFilterEffect.getClass();
            }
            OplusViewBackgroundRenderEffect.setBackgroundRenderEffect(renderEffectCreateColorFilterEffect, view);
        }
    }

    public static /* synthetic */ void g(View view, int i, float f) {
        f(view, f, i, BlendMode.COLOR_DODGE, 0, BlendMode.SRC_OVER);
    }

    public static void h(View view, int i, BlendMode blendMode) {
        BlendMode blendMode2 = BlendMode.SRC_OVER;
        view.getClass();
        blendMode.getClass();
        blendMode2.getClass();
        if (d()) {
            RenderEffect renderEffectCreateColorFilterEffect = RenderEffect.createColorFilterEffect(new BlendModeColorFilter(i, blendMode));
            renderEffectCreateColorFilterEffect.getClass();
            OplusViewBackgroundRenderEffect.setBackgroundRenderEffect(renderEffectCreateColorFilterEffect, view);
        }
    }

    public static void i(float f, View view) {
        if (d()) {
            RenderEffect renderEffectCreateBlurEffect = RenderEffect.createBlurEffect(f, f, Shader.TileMode.MIRROR);
            renderEffectCreateBlurEffect.getClass();
            OplusViewBackgroundRenderEffect.setBackgroundRenderEffect(renderEffectCreateBlurEffect, view);
        }
    }

    public static void j(View view, float f, int i, int i2, boolean z) {
        view.getClass();
        Context context = view.getContext();
        context.getClass();
        if (z) {
            Configuration configuration = new Configuration(context.getResources().getConfiguration());
            configuration.uiMode = (configuration.uiMode & (-49)) | 16;
            context = context.createConfigurationContext(configuration);
            context.getClass();
        }
        boolean z2 = !z && COUIDarkModeUtil.isNightMode(view.getContext());
        BlendMode blendMode = z2 ? BlendMode.OVERLAY : BlendMode.LUMINOSITY;
        int iA = uib.a(context, d() ? R$color.base_blur_button_blend_color_first : R$color.base_blur_disable_button_blend_color, f);
        BlendMode blendMode2 = z2 ? BlendMode.LUMINOSITY : BlendMode.COLOR_DODGE;
        int iA2 = d() ? uib.a(context, R$color.base_blur_button_blend_color_second, f) : 0;
        if (!d()) {
            i = i2;
        }
        view.setBackgroundColor(uib.a(context, i, f));
        if (d()) {
            f(view, f * 30.0f, iA, blendMode, iA2, blendMode2);
            return;
        }
        a(view);
        BlendMode blendMode3 = BlendMode.SRC_OVER;
        h(view, iA, blendMode);
    }

    public static void k(View view, float f, Float f2, int i) {
        int i2 = R$color.base_blur_button_color;
        int i3 = R$color.base_blur_disable_button_color;
        if ((i & 8) != 0) {
            f2 = null;
        }
        j(view, f, i2, i3, (i & 16) == 0);
        gad0.m(view, f2);
    }

    public static void l(View view) {
        view.getClass();
        if (!d()) {
            a(view);
            return;
        }
        Context context = view.getContext();
        context.getClass();
        view.setBackgroundColor(uib.a(context, R$color.base_blur_common_bar_bg_color, 1.0f));
        Context context2 = view.getContext();
        context2.getClass();
        int iA = uib.a(context2, R$color.base_blur_common_bar_bg_blend_color, 1.0f);
        BlendMode blendMode = BlendMode.COLOR_DODGE;
        g(view, iA, 120.0f);
    }

    public static void m(View view, int i, int i2, int i3, float f, float f2, boolean z, float f3) {
        view.getClass();
        if (e()) {
            OplusViewBackgroundRenderEffect.setBackgroundRenderEffect(OplusRenderEffect.createGradientBlurEffect(f, f2, z, f3, i3, i, i2), view);
        }
    }

    public static /* synthetic */ void n(View view, int i, int i2, int i3, float f, float f2, boolean z, float f3, int i4) {
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = 1;
        }
        if ((i4 & 64) != 0) {
            f3 = 0.4f;
        }
        m(view, i, i2, i3, f, f2, z, f3);
    }

    public static void o(View view) {
        RenderEffect renderEffectCreateBlurEffect = RenderEffect.createBlurEffect(200.0f, 200.0f, Shader.TileMode.MIRROR);
        renderEffectCreateBlurEffect.getClass();
        OplusViewBackgroundRenderEffect.setBackgroundRenderEffect(renderEffectCreateBlurEffect, view);
    }

    public static String p(int i) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        return String.format(Locale.US, "#%08X", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
    }
}
