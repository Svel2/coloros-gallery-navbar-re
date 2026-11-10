package com.oplus.gallery.standard_lib.ui.bottomnavigation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.OneShotPreDrawListener;
import com.coui.appcompat.bottomfloatingedittoolbar.COUIBottomFloatingEditToolbar;
import com.coui.appcompat.bottomfloatingedittoolbar.COUIBottomFloatingEditToolbarMenuView;
import com.coui.appcompat.bottomfloatingnavigationbar.OnMenuViewShowHideAnimListener;
import com.coui.appcompat.bottomfloatingtoolbar.COUIBottomFloatingToolbarItemView;
import com.coui.appcompat.uiutil.ShadowUtils;
import com.oplus.aiunit.vision.fke0;
import com.oplus.aiunit.vision.kyq;
import com.oplus.aiunit.vision.oyq;
import com.oplus.aiunit.vision.ps4;
import com.oplus.aiunit.vision.su40;
import com.oplus.aiunit.vision.tx6;
import com.oplus.gallery.basebiz.widget.MaterialContentAlignment;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0007\u0010B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u001b\u0010\u000b\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/oplus/gallery/standard_lib/ui/bottomnavigation/BottomFloatingMenuBar;", "Lcom/coui/appcompat/bottomfloatingedittoolbar/COUIBottomFloatingEditToolbar;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/oplus/aiunit/vision/oyq;", "a", "Lkotlin/Lazy;", "getMPopupWindow", "()Lcom/oplus/aiunit/vision/oyq;", "mPopupWindow", "Lcom/coui/appcompat/bottomfloatingedittoolbar/COUIBottomFloatingEditToolbarMenuView;", "getMenuView", "()Lcom/coui/appcompat/bottomfloatingedittoolbar/COUIBottomFloatingEditToolbarMenuView;", "menuView", "AnimState", "basebiz_realmeDeviceallDomesticApilevelallDensityxxhdpiRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BottomFloatingMenuBar extends COUIBottomFloatingEditToolbar {
    public static Method g;

    /* JADX INFO: renamed from: a, reason: from kotlin metadata */
    public final Lazy mPopupWindow;
    public int b;
    public com.oplus.gallery.basebiz.widget.c.a c;
    public int d;
    public AnimState e;
    public final b f;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/oplus/gallery/standard_lib/ui/bottomnavigation/BottomFloatingMenuBar$AnimState;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "SHOW", "HIDE", "basebiz_realmeDeviceallDomesticApilevelallDensityxxhdpiRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum AnimState {
        NONE,
        SHOW,
        HIDE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<AnimState> getEntries() {
            return $ENTRIES;
        }
    }

    public static final class a {
        public COUIBottomFloatingToolbarItemView a;
    }

    public static final class b implements OnMenuViewShowHideAnimListener {
        public /* synthetic */ BottomFloatingMenuBar a;

        /* JADX WARN: Type inference failed for: r2v1, types: [android.view.View, com.oplus.gallery.standard_lib.ui.bottomnavigation.BottomFloatingMenuBar] */
        public final void onMenuViewShowHideAnimEnd() {
            ?? r2 = this.a;
            if (r2.e == AnimState.HIDE) {
                r2.setVisibility(8);
            }
            r2.e = AnimState.NONE;
        }

        public final void onMenuViewShowHideAnimStart(boolean z) {
            this.a.e = z ? AnimState.SHOW : AnimState.HIDE;
        }
    }

    public static final class c implements Runnable {
        public /* synthetic */ BottomFloatingMenuBar a;

        @Override // java.lang.Runnable
        public final void run() {
            BottomFloatingMenuBar bottomFloatingMenuBar = this.a;
            Method method = BottomFloatingMenuBar.g;
            COUIBottomFloatingEditToolbarMenuView menuView = bottomFloatingMenuBar.getMenuView();
            if (!bottomFloatingMenuBar.c.a && menuView != null && menuView.getWidth() > 0 && menuView.getHeight() > 0) {
                com.oplus.gallery.basebiz.widget.c cVar = com.oplus.gallery.basebiz.widget.c.a;
                su40 su40VarH = com.oplus.gallery.basebiz.widget.c.h(cVar, menuView, Integer.valueOf(menuView.getWidth()), Integer.valueOf(menuView.getHeight()), Float.valueOf(Math.min(menuView.getWidth(), menuView.getHeight()) / 2.0f), (MaterialContentAlignment) null, 16);
                if (!bottomFloatingMenuBar.c.a()) {
                    com.oplus.gallery.basebiz.widget.c.b(cVar, menuView, su40VarH, (MaterialContentAlignment) null, 0.0f, 28);
                } else {
                    com.oplus.gallery.basebiz.widget.c.i(cVar, menuView, 0.0f, su40VarH, 10);
                    ShadowUtils.clearShadow(menuView);
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomFloatingMenuBar(Context context) {
        super(context);
        context.getClass();
        tx6 tx6Var = new tx6();
        tx6Var.a = context;
        VarHandle.storeStoreFence();
        this.mPopupWindow = LazyKt.lazy(tx6Var);
        com.oplus.gallery.basebiz.widget.c.a.getClass();
        this.c = com.oplus.gallery.basebiz.widget.c.f();
        this.e = AnimState.NONE;
        b bVar = new b();
        bVar.a = this;
        VarHandle.storeStoreFence();
        this.f = bVar;
    }

    private final oyq getMPopupWindow() {
        return (oyq) this.mPopupWindow.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b() {
        c cVar = new c();
        cVar.a = this;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(this, cVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(int i, boolean z) {
        View viewFindViewById = findViewById(i);
        if (viewFindViewById != null) {
            int i2 = z ? 0 : 8;
            if (viewFindViewById.getVisibility() != i2) {
                viewFindViewById.setVisibility(i2);
                b();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d(int i, Function1<? super List<kyq>, Unit> function1, Function2<? super Integer, ? super kyq, Unit> function2) {
        getMPopupWindow().H(findViewById(i), function1, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final COUIBottomFloatingEditToolbarMenuView getMenuView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            COUIBottomFloatingEditToolbarMenuView childAt = getChildAt(i);
            if (childAt instanceof COUIBottomFloatingEditToolbarMenuView) {
                return childAt;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.view.View*/.onAttachedToWindow();
        setOnMenuViewShowHideAnimListener(this.f);
        com.oplus.gallery.basebiz.widget.c.a.getClass();
        this.c = com.oplus.gallery.basebiz.widget.c.f();
        b();
        CoordinatorLayout.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        ((ViewGroup.LayoutParams) layoutParams).width = -1;
        int i = this.d;
        if (i <= 0) {
            i = -2;
        }
        ((ViewGroup.LayoutParams) layoutParams).height = i;
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).gravity = 81;
        } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).addRule(14);
        } else if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            layoutParams.gravity = 81;
        } else {
            if (!(layoutParams instanceof ConstraintLayout.LayoutParams)) {
                fke0.a(ps4.a(layoutParams.getClass(), "BottomFloatingMenuBar don't support LayoutParams of "));
                return;
            }
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.leftToLeft = 0;
            layoutParams2.topToTop = -1;
            layoutParams2.rightToRight = 0;
            layoutParams2.bottomToBottom = 0;
        }
        setLayoutParams(layoutParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        if (getMPopupWindow().isShowing()) {
            getMPopupWindow().dismiss();
        }
        com.oplus.gallery.basebiz.widget.c.a aVar = this.c;
        if (!aVar.a && aVar.a()) {
            com.oplus.gallery.basebiz.widget.c cVar = com.oplus.gallery.basebiz.widget.c.a;
            COUIBottomFloatingEditToolbarMenuView menuView = getMenuView();
            cVar.getClass();
            com.oplus.gallery.basebiz.widget.c.c(menuView);
        }
        setOnMenuViewShowHideAnimListener((OnMenuViewShowHideAnimListener) null);
        super/*android.view.View*/.onDetachedFromWindow();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super/*android.view.View*/.onSizeChanged(i, i2, i3, i4);
        b();
    }
}
