package com.oplus.gallery.standard_lib.ui.bottomnavigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.OneShotPreDrawListener;
import com.coui.appcompat.bottomfloatingaccessorynavigationbar.COUIBottomFloatingAccessoryNavigationBar;
import com.coui.appcompat.bottomfloatingaccessorynavigationbar.COUIBottomFloatingAccessoryView;
import com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBarMenuView;
import com.oplus.aiunit.vision.hpj;
import com.oplus.aiunit.vision.su40;
import com.oplus.aiunit.vision.vx6;
import com.oplus.gallery.basebiz.widget.MaterialContentAlignment;
import com.oplus.gallery.basebiz.widget.c;
import com.support.chip.R$styleable;
import java.lang.invoke.VarHandle;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0002¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/oplus/gallery/standard_lib/ui/bottomnavigation/BottomFloatingNavigationBar;", "Lcom/coui/appcompat/bottomfloatingaccessorynavigationbar/COUIBottomFloatingAccessoryNavigationBar;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Lkotlin/Pair;", HttpUrl.FRAGMENT_ENCODE_SET, "getMenuViewWH", "()Lkotlin/Pair;", "basebiz_realmeDeviceallDomesticApilevelallDensityxxhdpiRelease"}, k = 1, mv = {2, 2, 0}, xi = R$styleable.COUIChip_useMaterialEffect)
public final class BottomFloatingNavigationBar extends COUIBottomFloatingAccessoryNavigationBar {
    public static final MaterialContentAlignment h = MaterialContentAlignment.ALIGN_LAYOUT_START;
    public c.a a;
    public float b;
    public float c;
    public float d;
    public int e;
    public int f;
    public vx6 g;

    public static final class a implements Runnable {
        public /* synthetic */ BottomFloatingNavigationBar a;

        @Override // java.lang.Runnable
        public final void run() {
            BottomFloatingNavigationBar bottomFloatingNavigationBar = this.a;
            MaterialContentAlignment materialContentAlignment = BottomFloatingNavigationBar.h;
            bottomFloatingNavigationBar.f();
            if (bottomFloatingNavigationBar.a.a()) {
                COUIBottomFloatingAccessoryView startAccessoryView = bottomFloatingNavigationBar.getStartAccessoryView();
                float f = bottomFloatingNavigationBar.c;
                if (startAccessoryView != null && startAccessoryView.getVisibility() == 0) {
                    c.i(c.a, startAccessoryView, f, (su40) null, 12);
                }
                COUIBottomFloatingAccessoryView endAccessoryView = bottomFloatingNavigationBar.getEndAccessoryView();
                float f2 = bottomFloatingNavigationBar.d;
                if (endAccessoryView != null && endAccessoryView.getVisibility() == 0) {
                    c.i(c.a, endAccessoryView, f2, (su40) null, 12);
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomFloatingNavigationBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context.getClass();
        c.a.getClass();
        this.a = c.f();
        this.b = 1.0f;
        this.c = 1.0f;
        this.d = 1.0f;
    }

    private final Pair<Integer, Integer> getMenuViewWH() {
        COUIBottomFloatingNavigationBarMenuView menuView = getMenuView();
        if (menuView != null && menuView.getWidth() > 0 && menuView.getHeight() > 0) {
            Integer numValueOf = Integer.valueOf(menuView.getMeasuredWidth());
            if (numValueOf.intValue() <= 0) {
                numValueOf = null;
            }
            if (numValueOf != null) {
                Integer numValueOf2 = Integer.valueOf(menuView.getMeasuredHeight());
                if (numValueOf2.intValue() <= 0) {
                    numValueOf2 = null;
                }
                if (numValueOf2 != null) {
                    return TuplesKt.to(numValueOf, numValueOf2);
                }
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        if (isEnabled()) {
            return super/*android.view.View*/.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void f() {
        COUIBottomFloatingNavigationBarMenuView menuView = getMenuView();
        if (menuView == null) {
            return;
        }
        menuView.setLayoutDirection(getLayoutDirection());
        if (this.a.a) {
            c.i(c.a, menuView, this.b, (su40) null, 12);
            return;
        }
        Pair<Integer, Integer> menuViewWH = getMenuViewWH();
        if (menuViewWH == null) {
            return;
        }
        int iIntValue = menuViewWH.component1().intValue();
        int iIntValue2 = menuViewWH.component2().intValue();
        c cVar = c.a;
        Integer numValueOf = Integer.valueOf(iIntValue);
        Integer numValueOf2 = Integer.valueOf(iIntValue2);
        Float fValueOf = Float.valueOf(Math.min(iIntValue, iIntValue2) / 2.0f);
        cVar.getClass();
        MaterialContentAlignment materialContentAlignment = h;
        su40 su40VarG = c.g(menuView, numValueOf, numValueOf2, fValueOf, materialContentAlignment);
        boolean zA = this.a.a();
        float f = this.b;
        if (zA) {
            c.i(cVar, menuView, f, su40VarG, 8);
        } else {
            c.b(cVar, menuView, su40VarG, materialContentAlignment, f, 8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void g() {
        a aVar = new a();
        aVar.a = this;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(this, aVar);
    }

    public final float getAccessoryViewAlpha(View view) {
        if (this.a.a) {
            return super.getAccessoryViewAlpha(view);
        }
        if (Intrinsics.areEqual(view, getStartAccessoryView())) {
            return this.c;
        }
        if (Intrinsics.areEqual(view, getEndAccessoryView())) {
            return this.d;
        }
        return 1.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        COUIBottomFloatingNavigationBarMenuView menuView;
        super/*android.view.View*/.onAttachedToWindow();
        c.a.getClass();
        this.a = c.f();
        COUIBottomFloatingNavigationBarMenuView menuView2 = getMenuView();
        if (menuView2 != null) {
            menuView2.setLayoutDirection(getLayoutDirection());
        }
        COUIBottomFloatingNavigationBarMenuView menuView3 = getMenuView();
        if (menuView3 != null) {
            vx6 vx6Var = this.g;
            if (vx6Var != null && (menuView = getMenuView()) != null) {
                menuView.removeOnLayoutChangeListener(vx6Var);
            }
            vx6 vx6Var2 = new vx6();
            vx6Var2.a = this;
            VarHandle.storeStoreFence();
            this.g = vx6Var2;
            menuView3.addOnLayoutChangeListener(vx6Var2);
        }
        g();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        COUIBottomFloatingNavigationBarMenuView menuView;
        vx6 vx6Var = this.g;
        if (vx6Var != null && (menuView = getMenuView()) != null) {
            menuView.removeOnLayoutChangeListener(vx6Var);
        }
        this.g = null;
        this.c = 1.0f;
        this.d = 1.0f;
        this.e = 0;
        this.f = 0;
        c.a aVar = this.a;
        if (!aVar.a && aVar.a()) {
            c cVar = c.a;
            COUIBottomFloatingNavigationBarMenuView menuView2 = getMenuView();
            cVar.getClass();
            c.c(menuView2);
        }
        super/*android.view.View*/.onDetachedFromWindow();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onRtlPropertiesChanged(int i) {
        super/*android.view.View*/.onRtlPropertiesChanged(i);
        g();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super/*android.view.View*/.onSizeChanged(i, i2, i3, i4);
        g();
    }

    public final void setAccessoryViewAlpha(View view, float f) {
        if (this.a.a) {
            super.setAccessoryViewAlpha(view, f);
            return;
        }
        if (Intrinsics.areEqual(view, getStartAccessoryView())) {
            this.c = f;
        } else if (Intrinsics.areEqual(view, getEndAccessoryView())) {
            this.d = f;
        }
        if (view != null) {
            hpj.a(f, view);
        }
    }

    public final void setEndAccessoryIcon(int i, boolean z) {
        if (i == 0 || i == this.f) {
            return;
        }
        this.f = i;
        super.setEndAccessoryIcon(i, z);
    }

    public final void setStartAccessoryIcon(int i, boolean z) {
        if (i == 0 || i == this.e) {
            return;
        }
        this.e = i;
        super.setStartAccessoryIcon(i, z);
    }
}
