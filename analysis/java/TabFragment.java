package com.coloros.gallery3d.app.tab;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.coloros.gallery3d.app.anim.PageClickSwitchAlphaAnimator;
import com.coui.appcompat.bottomfloatingaccessorynavigationbar.COUIBottomFloatingAccessoryView;
import com.coui.appcompat.bottomfloatingedittoolbar.COUIBottomFloatingEditToolbarMenuView;
import com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBarMenuView;
import com.coui.appcompat.darkmode.COUIDarkModeUtil;
import com.coui.appcompat.segmentbutton.COUISegmentButtonLayout;
import com.oplus.aiunit.vision.aa90;
import com.oplus.aiunit.vision.ba90;
import com.oplus.aiunit.vision.bfm;
import com.oplus.aiunit.vision.c990;
import com.oplus.aiunit.vision.ca90;
import com.oplus.aiunit.vision.cb90;
import com.oplus.aiunit.vision.d990;
import com.oplus.aiunit.vision.da90;
import com.oplus.aiunit.vision.db90;
import com.oplus.aiunit.vision.dum;
import com.oplus.aiunit.vision.e990;
import com.oplus.aiunit.vision.ea90;
import com.oplus.aiunit.vision.efm;
import com.oplus.aiunit.vision.f990;
import com.oplus.aiunit.vision.fa90;
import com.oplus.aiunit.vision.ffm;
import com.oplus.aiunit.vision.fhi;
import com.oplus.aiunit.vision.fjj;
import com.oplus.aiunit.vision.fum;
import com.oplus.aiunit.vision.g990;
import com.oplus.aiunit.vision.ga90;
import com.oplus.aiunit.vision.gad0;
import com.oplus.aiunit.vision.gpa0;
import com.oplus.aiunit.vision.gq7;
import com.oplus.aiunit.vision.h990;
import com.oplus.aiunit.vision.hpj;
import com.oplus.aiunit.vision.hze0;
import com.oplus.aiunit.vision.i3j;
import com.oplus.aiunit.vision.i690;
import com.oplus.aiunit.vision.i990;
import com.oplus.aiunit.vision.ia90;
import com.oplus.aiunit.vision.ibk;
import com.oplus.aiunit.vision.ium;
import com.oplus.aiunit.vision.iwm;
import com.oplus.aiunit.vision.j67;
import com.oplus.aiunit.vision.j990;
import com.oplus.aiunit.vision.ja90;
import com.oplus.aiunit.vision.k67;
import com.oplus.aiunit.vision.k990;
import com.oplus.aiunit.vision.ka90;
import com.oplus.aiunit.vision.l990;
import com.oplus.aiunit.vision.la90;
import com.oplus.aiunit.vision.m990;
import com.oplus.aiunit.vision.ma90;
import com.oplus.aiunit.vision.n67;
import com.oplus.aiunit.vision.n990;
import com.oplus.aiunit.vision.o990;
import com.oplus.aiunit.vision.oh30;
import com.oplus.aiunit.vision.p990;
import com.oplus.aiunit.vision.pa90;
import com.oplus.aiunit.vision.pab;
import com.oplus.aiunit.vision.pp10;
import com.oplus.aiunit.vision.ptm;
import com.oplus.aiunit.vision.pua0;
import com.oplus.aiunit.vision.pwm;
import com.oplus.aiunit.vision.q67;
import com.oplus.aiunit.vision.q990;
import com.oplus.aiunit.vision.qa90;
import com.oplus.aiunit.vision.qd90;
import com.oplus.aiunit.vision.qvc;
import com.oplus.aiunit.vision.r990;
import com.oplus.aiunit.vision.ra90;
import com.oplus.aiunit.vision.rtm;
import com.oplus.aiunit.vision.s990;
import com.oplus.aiunit.vision.sa90;
import com.oplus.aiunit.vision.scm;
import com.oplus.aiunit.vision.seo;
import com.oplus.aiunit.vision.sti;
import com.oplus.aiunit.vision.t5m;
import com.oplus.aiunit.vision.t990;
import com.oplus.aiunit.vision.ta90;
import com.oplus.aiunit.vision.u990;
import com.oplus.aiunit.vision.ua90;
import com.oplus.aiunit.vision.uvc;
import com.oplus.aiunit.vision.v990;
import com.oplus.aiunit.vision.wa90;
import com.oplus.aiunit.vision.wwm;
import com.oplus.aiunit.vision.x990;
import com.oplus.aiunit.vision.xa90;
import com.oplus.aiunit.vision.y990;
import com.oplus.aiunit.vision.z990;
import com.oplus.gallery.albumsetpage.allalbum.ui.MainTabAlbumSetFragment;
import com.oplus.gallery.albumsetpage.map.DynamicMapCover;
import com.oplus.gallery.basebiz.R$drawable;
import com.oplus.gallery.basebiz.R$id;
import com.oplus.gallery.basebiz.R$layout;
import com.oplus.gallery.basebiz.R$string;
import com.oplus.gallery.basebiz.sidepane.SidePaneState;
import com.oplus.gallery.basebiz.uikit.fragment.TemplateFragment;
import com.oplus.gallery.basebiz.widget.BlurButtonWrapperView;
import com.oplus.gallery.basebiz.widget.SpaceView;
import com.oplus.gallery.business_lib.timeline.viewmodel.TimelineViewModel;
import com.oplus.gallery.foundation.uikit.responsiveui.AppUiResponder;
import com.oplus.gallery.foundation.util.systemcore.ResourceUtils;
import com.oplus.gallery.framework.app.GalleryApplication;
import com.oplus.gallery.standard_lib.scene.limit.SceneDispatcher;
import com.oplus.gallery.standard_lib.ui.bottomnavigation.BottomFloatingNavigationBar;
import com.oplus.gallery.timelinepage.TimelineTabFragment;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007B\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/coloros/gallery3d/app/tab/TabFragment;", "Lcom/oplus/gallery/basebiz/uikit/fragment/TemplateFragment;", "Lcom/oplus/aiunit/vision/rtm;", "Lcom/oplus/aiunit/vision/scm;", "Lcom/oplus/aiunit/vision/ium;", "Lcom/oplus/aiunit/vision/efm;", "Lcom/oplus/aiunit/vision/ffm;", "Lcom/oplus/aiunit/vision/bfm;", "<init>", "()V", "OppoGallery2_realmeDeviceallDomesticApilevelallDensityxxhdpiRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TabFragment extends TemplateFragment implements rtm, scm, ium, efm, ffm, bfm {
    public static final /* synthetic */ int g1 = 0;
    public final ArrayList A0;
    public final Lazy B0;
    public final Lazy C0;
    public final Lazy D0;
    public final Lazy E0;
    public boolean F0;
    public boolean G0;
    public boolean H0;
    public boolean I0;
    public boolean J0;
    public boolean K0;
    public boolean L0;
    public final Lazy M0;
    public final Lazy N0;
    public int O0;
    public a P0;
    public boolean Q0;
    public int R0;
    public boolean S0;
    public wwm T0;
    public boolean U0;
    public final Lazy V0;
    public final Lazy W0;
    public final Lazy X0;
    public final Lazy Y0;
    public final Lazy Z0;
    public final Lazy a1;
    public final sa90 b1;
    public final b c1;
    public boolean d1;
    public ViewPager2 e0;
    public final TabFragment$onPageChangeListener$1 e1;
    public TabFragmentAdapter f0;
    public final ta90 f1;
    public ViewStub g0;
    public ConstraintLayout h0;
    public BottomFloatingNavigationBar i0;
    public COUIBottomFloatingNavigationBarMenuView j0;
    public SpaceView k0;
    public BlurButtonWrapperView l0;
    public BlurButtonWrapperView m0;
    public boolean o0;
    public ptm q0;
    public j67 s0;
    public boolean t0;
    public boolean u0;
    public final n67 v0;
    public final q67 w0;
    public boolean x0;
    public final Lazy y0;
    public final Lazy z0;
    public final String b0 = "Tab";
    public final Lazy c0 = LazyKt.lazy(new m990());
    public List<Integer> d0 = CollectionsKt.emptyList();
    public int n0 = -1;
    public final ArrayList p0 = new ArrayList();
    public final j67 r0 = new j67();

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public /* synthetic */ TabFragment f33a;
    }

    public static final class b implements t5m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public /* synthetic */ TabFragment f34a;

        @Override // com.oplus.aiunit.vision.t5m
        public final void a(View view, boolean z) {
            view.getClass();
            if (view.getId() != 2131430074) {
                return;
            }
            TabFragment tabFragment = this.f34a;
            int i = TabFragment.g1;
            SpaceView spaceView = tabFragment.k0;
            if (spaceView != null) {
                spaceView.setVisibility(z ? 0 : 4);
                spaceView.setClickable(z);
            }
        }
    }

    public static final class c extends sti {
        public /* synthetic */ TabFragment b;

        public final void a() {
            TabFragment tabFragment = this.b;
            tabFragment.R0(true);
            tabFragment.T3();
        }

        public final void b(WindowInsetsCompat windowInsetsCompat, boolean z) {
            TabFragment tabFragment = this.b;
            tabFragment.y(0);
            tabFragment.T3();
            Insets insetsF = i690.f(windowInsetsCompat, true);
            tabFragment.u0(insetsF.left, 0, insetsF.right, 0);
            int iI2 = tabFragment.I2();
            tabFragment.Z3(iI2);
            BottomFloatingNavigationBar bottomFloatingNavigationBar = tabFragment.i0;
            if (bottomFloatingNavigationBar != null) {
                bottomFloatingNavigationBar.setPadding(bottomFloatingNavigationBar.getPaddingLeft(), bottomFloatingNavigationBar.getPaddingTop(), bottomFloatingNavigationBar.getPaddingRight(), iI2);
            }
        }
    }

    public TabFragment() {
        n67 n67Var = new n67();
        n67Var.b = CollectionsKt.emptyList();
        n67Var.d = true;
        n67Var.h = 0.98f;
        k67 k67Var = new k67();
        k67Var.f991a = n67Var;
        VarHandle.storeStoreFence();
        n67Var.i = LazyKt.lazy(k67Var);
        VarHandle.storeStoreFence();
        this.v0 = n67Var;
        this.w0 = new q67();
        this.x0 = true;
        ua90 ua90Var = new ua90();
        ua90Var.f1781a = this;
        VarHandle.storeStoreFence();
        this.y0 = LazyKt.lazy(ua90Var);
        c990 c990Var = new c990();
        c990Var.f380a = this;
        VarHandle.storeStoreFence();
        this.z0 = LazyKt.lazy(c990Var);
        this.A0 = new ArrayList();
        d990 d990Var = new d990();
        d990Var.f456a = this;
        VarHandle.storeStoreFence();
        this.B0 = LazyKt.lazy(d990Var);
        e990 e990Var = new e990();
        e990Var.f525a = this;
        VarHandle.storeStoreFence();
        this.C0 = LazyKt.lazy(e990Var);
        f990 f990Var = new f990();
        f990Var.f608a = this;
        VarHandle.storeStoreFence();
        this.D0 = LazyKt.lazy(f990Var);
        g990 g990Var = new g990();
        g990Var.f684a = this;
        VarHandle.storeStoreFence();
        this.E0 = LazyKt.lazy(g990Var);
        this.G0 = true;
        this.H0 = true;
        this.M0 = LazyKt.lazy(new h990());
        this.N0 = LazyKt.lazy(new i990());
        this.O0 = -1;
        a aVar = new a();
        aVar.f33a = this;
        VarHandle.storeStoreFence();
        this.P0 = aVar;
        this.R0 = -1;
        this.S0 = true;
        this.V0 = LazyKt.lazy(new j990());
        this.W0 = LazyKt.lazy(new x990());
        this.X0 = LazyKt.lazy(new ia90());
        this.Y0 = LazyKt.lazy(new pa90());
        this.Z0 = LazyKt.lazy(new qa90());
        this.a1 = LazyKt.lazy(new ra90());
        sa90 sa90Var = new sa90();
        sa90Var.f1629a = this;
        VarHandle.storeStoreFence();
        this.b1 = sa90Var;
        b bVar = new b();
        bVar.f34a = this;
        VarHandle.storeStoreFence();
        this.c1 = bVar;
        this.e1 = new TabFragment$onPageChangeListener$1(this);
        ta90 ta90Var = new ta90();
        ta90Var.f1711a = this;
        VarHandle.storeStoreFence();
        this.f1 = ta90Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void U3(TabFragment tabFragment) {
        n67 n67Var = tabFragment.v0;
        BottomFloatingNavigationBar bottomFloatingNavigationBar = tabFragment.i0;
        if (bottomFloatingNavigationBar != null) {
            bottomFloatingNavigationBar.setVisibility(0);
        }
        List<View> listD3 = tabFragment.D3();
        View view = tabFragment.getView();
        n67Var.getClass();
        listD3.getClass();
        n67Var.c = view;
        n67Var.b = listD3;
        v990 v990Var = new v990();
        VarHandle.storeStoreFence();
        if (n67Var.d) {
            Unit unit = Unit.INSTANCE;
        } else if (listD3.isEmpty()) {
            n67Var.d = true;
            Unit unit2 = Unit.INSTANCE;
        } else {
            n67Var.e = v990Var;
            n67Var.f1231a = 1.0f;
            n67Var.a(1.0f);
            n67Var.f(true);
            n67Var.d = true;
            n67Var.c().setStartValue(n67Var.f1231a);
            n67Var.c().animateToFinalPosition(0.0f);
        }
        iwm iwmVarB3 = tabFragment.B3();
        if ((iwmVarB3 != null ? iwmVarB3.k() : false) && !tabFragment.M3()) {
            tabFragment.y3();
            q67 q67Var = tabFragment.w0;
            ImageView imageView = q67Var.f1472a;
            if (imageView == null) {
                return;
            }
            imageView.setVisibility(0);
            q67Var.d(350L, q67Var.b(), 1.0f, true);
        }
    }

    public final int A3() {
        ViewPager2 viewPager2 = this.e0;
        if (viewPager2 == null) {
            return -1;
        }
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        return viewPager2.getCurrentItem();
    }

    public final iwm B3() {
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter = null;
        }
        com.oplus.gallery.foundation.uikit.responsiveui.b bVarM = tabFragmentAdapter.m(this.n0);
        if (bVarM == null || !bVarM.isAdded() || bVarM.getContext() == null) {
            bVarM = null;
        }
        if (bVarM instanceof iwm) {
            return (iwm) bVarM;
        }
        return null;
    }

    @Override // com.oplus.gallery.basebiz.uikit.fragment.TemplateFragment
    public final ium C2() {
        return this;
    }

    public final void C3() {
        Object obj;
        if (this.Q0) {
            this.Q0 = false;
            int i = this.R0;
            this.R0 = -1;
            if (i < 0 || i >= this.d0.size()) {
                return;
            }
            try {
                Result.Companion companion = Result.Companion;
                ViewPager2 viewPager2 = this.e0;
                if (viewPager2 != null && viewPager2.getCurrentItem() != i) {
                    ViewPager2 viewPager3 = this.e0;
                    if (viewPager3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                        viewPager3 = null;
                    }
                    viewPager3.setCurrentItem(i, false);
                }
                this.e1.onPageSelected(i);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                n990 n990Var = new n990();
                n990Var.f1238a = th2;
                VarHandle.storeStoreFence();
                i3j.p("TabFragment", 3, (Throwable) null, n990Var, 4);
            }
        }
    }

    @Override // com.oplus.gallery.basebiz.uikit.fragment.TemplateFragment
    /* JADX INFO: renamed from: D2 */
    public final boolean getN() {
        iwm iwmVarB3 = B3();
        if (iwmVarB3 != null) {
            return iwmVarB3.u();
        }
        return false;
    }

    public final List<View> D3() {
        COUIBottomFloatingAccessoryView startAccessoryView;
        COUIBottomFloatingAccessoryView endAccessoryView;
        COUIBottomFloatingNavigationBarMenuView cOUIBottomFloatingNavigationBarMenuView = this.j0;
        BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
        COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView = null;
        if (bottomFloatingNavigationBar == null || (startAccessoryView = bottomFloatingNavigationBar.getStartAccessoryView()) == null || !this.I0) {
            startAccessoryView = null;
        }
        BottomFloatingNavigationBar bottomFloatingNavigationBar2 = this.i0;
        if (bottomFloatingNavigationBar2 != null && (endAccessoryView = bottomFloatingNavigationBar2.getEndAccessoryView()) != null && this.J0) {
            cOUIBottomFloatingAccessoryView = endAccessoryView;
        }
        return CollectionsKt.listOfNotNull(new View[]{cOUIBottomFloatingNavigationBarMenuView, startAccessoryView, cOUIBottomFloatingAccessoryView});
    }

    public final COUISegmentButtonLayout E3() {
        BlurButtonWrapperView blurButtonWrapperView = this.l0;
        if (blurButtonWrapperView != null && (blurButtonWrapperView.getButtonView() instanceof COUISegmentButtonLayout)) {
            View buttonView = blurButtonWrapperView.getButtonView();
            if (buttonView != null) {
                return (COUISegmentButtonLayout) buttonView;
            }
            hze0.a("null cannot be cast to non-null type com.coui.appcompat.segmentbutton.COUISegmentButtonLayout");
        }
        return null;
    }

    @Override // com.oplus.aiunit.vision.jum
    public final void F(SidePaneState sidePaneState) {
        sidePaneState.getClass();
        if (!w3()) {
            V3(sidePaneState);
            this.K0 = false;
            return;
        }
        boolean zIsClose = sidePaneState.isClose();
        n67 n67Var = this.v0;
        if (zIsClose) {
            if (!this.K0 && !n67Var.d) {
                this.K0 = true;
                U3(this);
            }
            this.K0 = false;
            return;
        }
        if (sidePaneState.isOpen()) {
            n67Var.d(false);
            this.w0.c(false);
            this.F0 = false;
            X3(A3());
        }
    }

    public final PageClickSwitchAlphaAnimator F3() {
        return (PageClickSwitchAlphaAnimator) this.y0.getValue();
    }

    @Override // com.oplus.aiunit.vision.efm
    public final boolean G() {
        return this.n0 == 2;
    }

    public final void G3() {
        BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
        if (bottomFloatingNavigationBar == null) {
            return;
        }
        boolean zC = pab.c(6, "feature_is_support_safe_box_album", false);
        Iterator<Integer> it = this.d0.iterator();
        while (it.hasNext()) {
            View viewFindViewById = bottomFloatingNavigationBar.findViewById(it.next().intValue());
            if (viewFindViewById != null) {
                ca90 ca90Var = new ca90();
                ca90Var.f382a = this;
                VarHandle.storeStoreFence();
                viewFindViewById.setOnTouchListener(ca90Var);
                if (zC) {
                    viewFindViewById.setHapticFeedbackEnabled(true);
                    da90 da90Var = new da90();
                    da90Var.f457a = this;
                    VarHandle.storeStoreFence();
                    viewFindViewById.setOnLongClickListener(da90Var);
                }
            }
        }
    }

    public final void H3() {
        Object obj;
        BlurButtonWrapperView blurButtonWrapperView;
        BlurButtonWrapperView blurButtonWrapperView2;
        COUISegmentButtonLayout cOUISegmentButtonLayout;
        if (this.h0 == null || this.m0 == null || this.l0 == null) {
            BlurButtonWrapperView blurButtonWrapperView3 = null;
            try {
                Result.Companion companion = Result.Companion;
                ViewStub viewStub = this.g0;
                View viewInflate = viewStub != null ? viewStub.inflate() : null;
                viewInflate.getClass();
                this.h0 = (ConstraintLayout) viewInflate;
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                ea90 ea90Var = new ea90();
                ea90Var.f527a = th2;
                VarHandle.storeStoreFence();
                i3j.D(ea90Var, 3, "TabFragment");
                ViewStub viewStub2 = this.g0;
                if (viewStub2 != null) {
                    viewStub2.setVisibility(0);
                }
            }
            ConstraintLayout constraintLayout = this.h0;
            if (constraintLayout == null || (blurButtonWrapperView = (BlurButtonWrapperView) constraintLayout.findViewById(R$id.picked_segment_wrapper_view)) == null) {
                blurButtonWrapperView = null;
            } else {
                if (blurButtonWrapperView.getButtonView() instanceof COUISegmentButtonLayout) {
                    View buttonView = blurButtonWrapperView.getButtonView();
                    if (buttonView == null) {
                        hze0.a("null cannot be cast to non-null type com.coui.appcompat.segmentbutton.COUISegmentButtonLayout");
                        return;
                    }
                    cOUISegmentButtonLayout = (COUISegmentButtonLayout) buttonView;
                } else {
                    cOUISegmentButtonLayout = null;
                }
                if (cOUISegmentButtonLayout != null) {
                    cOUISegmentButtonLayout.setDragAnimationEnabled(false);
                    cOUISegmentButtonLayout.setSegmentButtons(new String[]{blurButtonWrapperView.getResources().getString(R$string.main_timeline_tab_button_year), blurButtonWrapperView.getResources().getString(R$string.main_timeline_tab_button_month), blurButtonWrapperView.getResources().getString(R$string.main_timeline_tab_button_day)});
                    fa90 fa90Var = new fa90();
                    fa90Var.f611a = this;
                    VarHandle.storeStoreFence();
                    cOUISegmentButtonLayout.setOnSelectedSegmentChangeListener(fa90Var);
                    int segmentCount = cOUISegmentButtonLayout.getSegmentCount();
                    if (segmentCount > 0) {
                        for (int i = 0; i < segmentCount; i++) {
                            TextView segmentAt = cOUISegmentButtonLayout.getSegmentAt(i);
                            segmentAt.getClass();
                            la90 la90Var = new la90();
                            VarHandle.storeStoreFence();
                            segmentAt.setOnTouchListener(la90Var);
                            ma90 ma90Var = new ma90();
                            ma90Var.f1158a = this;
                            VarHandle.storeStoreFence();
                            segmentAt.setOnClickListener(ma90Var);
                        }
                    }
                }
            }
            this.l0 = blurButtonWrapperView;
            ConstraintLayout constraintLayout2 = this.h0;
            this.k0 = constraintLayout2 != null ? (SpaceView) constraintLayout2.findViewById(R$id.picked_exit_button_space_right) : null;
            ConstraintLayout constraintLayout3 = this.h0;
            if (constraintLayout3 != null && (blurButtonWrapperView2 = (BlurButtonWrapperView) constraintLayout3.findViewById(R$id.picked_exit_button_wrapper_view)) != null) {
                ga90 ga90Var = new ga90();
                ga90Var.f687a = this;
                VarHandle.storeStoreFence();
                blurButtonWrapperView2.setOnClickListener(ga90Var);
                blurButtonWrapperView3 = blurButtonWrapperView2;
            }
            this.m0 = blurButtonWrapperView3;
            a4();
            Z3(I2());
            x3(true, false);
        }
    }

    public final boolean I3() {
        j67 j67Var;
        return this.r0.e() || ((j67Var = this.s0) != null && j67Var.e());
    }

    public final boolean J3() {
        if (!((Boolean) this.Z0.getValue()).booleanValue()) {
            return false;
        }
        if (((Boolean) this.a1.getValue()).booleanValue()) {
            return pab.c(4, "show_creation_tab", true);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.gallery.basebiz.uikit.fragment.TemplateFragment, com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final void K1(View view, Bundle bundle) {
        BottomFloatingNavigationBar bottomFloatingNavigationBar;
        dum dumVarR2;
        DynamicMapCover dynamicMapCover;
        super.K1(view, bundle);
        this.d0 = v3();
        FragmentManager childFragmentManager = getChildFragmentManager();
        childFragmentManager.getClass();
        Lifecycle lifecycle = getLifecycle();
        lifecycle.getClass();
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(childFragmentManager, lifecycle);
        tabFragmentAdapter.n(u3());
        this.f0 = tabFragmentAdapter;
        View viewFindViewById = view.findViewById(R$id.view_pager);
        viewFindViewById.getClass();
        ViewPager2 viewPager2 = (ViewPager2) viewFindViewById;
        this.e0 = viewPager2;
        viewPager2.registerOnPageChangeCallback(this.e1);
        TabFragmentAdapter tabFragmentAdapter2 = this.f0;
        if (tabFragmentAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter2 = null;
        }
        viewPager2.setAdapter(tabFragmentAdapter2);
        viewPager2.setSaveEnabled(false);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        fragmentActivityRequireActivity.getClass();
        s990 s990Var = new s990();
        s990Var.f1626a = this;
        VarHandle.storeStoreFence();
        gq7.a(fragmentActivityRequireActivity, s990Var, 4);
        int i = this.n0;
        if (i != -1) {
            viewPager2.setCurrentItem(i, false);
            this.n0 = -1;
        }
        View childAt = viewPager2.getChildAt(0);
        RecyclerView recyclerView = childAt instanceof RecyclerView ? (RecyclerView) childAt : null;
        if (recyclerView != null) {
            recyclerView.setOverScrollMode(2);
            t990 t990Var = new t990();
            t990Var.f1710a = this;
            VarHandle.storeStoreFence();
            recyclerView.addOnLayoutChangeListener(t990Var);
        }
        TabFragmentAdapter tabFragmentAdapter3 = this.f0;
        if (tabFragmentAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter3 = null;
        }
        TemplateFragment templateFragmentM = tabFragmentAdapter3.m(1);
        if (templateFragmentM instanceof MainTabAlbumSetFragment) {
            MainTabAlbumSetFragment mainTabAlbumSetFragment = (MainTabAlbumSetFragment) templateFragmentM;
            a aVar = this.P0;
            mainTabAlbumSetFragment.L0 = aVar;
            if (aVar != null) {
                DynamicMapCover dynamicMapCover2 = mainTabAlbumSetFragment.C0;
                if ((dynamicMapCover2 != null ? dynamicMapCover2.h : null) == null && (dynamicMapCover = mainTabAlbumSetFragment.C0) != null) {
                    dynamicMapCover.h = mainTabAlbumSetFragment.L0;
                }
            }
        }
        if (Y2() && (dumVarR2 = R2()) != null) {
            if (view.isAttachedToWindow()) {
                W0(!fum.h(dumVarR2));
            } else {
                xa90 xa90Var = new xa90();
                xa90Var.a = view;
                xa90Var.b = this;
                xa90Var.c = dumVarR2;
                VarHandle.storeStoreFence();
                view.addOnAttachStateChangeListener(xa90Var);
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        View view2 = getView();
        BottomFloatingNavigationBar bottomFloatingNavigationBar2 = view2 != null ? (BottomFloatingNavigationBar) view2.findViewById(R$id.main_segment_wrapper_view) : null;
        this.i0 = bottomFloatingNavigationBar2;
        if (bottomFloatingNavigationBar2 != null) {
            bottomFloatingNavigationBar2.inflateMenu(J3() ? 2131755054 : 2131755055);
            this.j0 = bottomFloatingNavigationBar2.getMenuView();
        }
        BottomFloatingNavigationBar bottomFloatingNavigationBar3 = this.i0;
        if (bottomFloatingNavigationBar3 != null) {
            ja90 ja90Var = new ja90();
            ja90Var.f920a = this;
            VarHandle.storeStoreFence();
            bottomFloatingNavigationBar3.setOnStartAccessoryClickListener(ja90Var);
            ka90 ka90Var = new ka90();
            ka90Var.f997a = this;
            VarHandle.storeStoreFence();
            bottomFloatingNavigationBar3.setOnEndAccessoryClickListener(ka90Var);
        }
        int i2 = L3() ? R$drawable.main_ic_picked : R$drawable.main_ic_segment_album_sort;
        BottomFloatingNavigationBar bottomFloatingNavigationBar4 = this.i0;
        if (bottomFloatingNavigationBar4 != null) {
            bottomFloatingNavigationBar4.setStartAccessoryIcon(i2, false);
        }
        BottomFloatingNavigationBar bottomFloatingNavigationBar5 = this.i0;
        if (bottomFloatingNavigationBar5 != null) {
            bottomFloatingNavigationBar5.setEndAccessoryIcon(2131233398, false);
        }
        ViewPager2 viewPager3 = this.e0;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager3 = null;
        }
        W3(viewPager3.getCurrentItem(), false);
        TabFragmentAdapter tabFragmentAdapter4 = this.f0;
        if (tabFragmentAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter4 = null;
        }
        com.oplus.gallery.foundation.uikit.responsiveui.b bVarM = tabFragmentAdapter4.m(0);
        this.T0 = bVarM instanceof wwm ? (wwm) bVarM : null;
        BottomFloatingNavigationBar bottomFloatingNavigationBar6 = this.i0;
        if (bottomFloatingNavigationBar6 != null) {
            ba90 ba90Var = new ba90();
            ba90Var.f312a = this;
            VarHandle.storeStoreFence();
            bottomFloatingNavigationBar6.post(ba90Var);
        }
        BottomFloatingNavigationBar bottomFloatingNavigationBar7 = this.i0;
        if (bottomFloatingNavigationBar7 != null) {
            int size = this.d0.size();
            ViewPager2 viewPager4 = this.e0;
            if (viewPager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager4 = null;
            }
            int currentItem = viewPager4.getCurrentItem();
            if (currentItem >= 0 && currentItem < size) {
                List<Integer> list = this.d0;
                ViewPager2 viewPager5 = this.e0;
                if (viewPager5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                    viewPager5 = null;
                }
                bottomFloatingNavigationBar7.setSelectedItemId(list.get(viewPager5.getCurrentItem()).intValue());
            }
            bottomFloatingNavigationBar7.setDispatchItemClickWhenAlreadySelected(true);
            wa90 wa90Var = new wa90();
            wa90Var.f1922a = this;
            VarHandle.storeStoreFence();
            bottomFloatingNavigationBar7.setOnItemSelectedListener(wa90Var);
            G3();
        }
        if (pp10.a && (bottomFloatingNavigationBar = this.i0) != null) {
            ViewTreeObserver viewTreeObserver = bottomFloatingNavigationBar.getViewTreeObserver();
            cb90 cb90Var = new cb90();
            cb90Var.a = this;
            cb90Var.b = bottomFloatingNavigationBar;
            VarHandle.storeStoreFence();
            viewTreeObserver.addOnDrawListener(cb90Var);
        }
        z990 z990Var = new z990();
        z990Var.f2123a = jCurrentTimeMillis;
        VarHandle.storeStoreFence();
        i3j.e(z990Var, 3, "TabFragment");
        if (L3()) {
            View view3 = getView();
            this.g0 = view3 != null ? (ViewStub) view3.findViewById(R$id.main_timeline_picked_view_stub) : null;
        }
        ImageView imageView = (ImageView) this.z0.getValue();
        if (imageView != null) {
            imageView.setAlpha(0.0f);
            this.w0.f1472a = imageView;
        }
    }

    public final boolean K3(Integer num) {
        return (num != null ? num.intValue() : A3()) == 0;
    }

    public final boolean L3() {
        return ((Boolean) this.W0.getValue()).booleanValue();
    }

    public final boolean M3() {
        return fum.g(R2()) && fum.h(R2()) && fum.e(R2());
    }

    @Override // com.oplus.aiunit.vision.efm
    public final void N0(boolean z, boolean z2) {
        this.G0 = (z || z2) ? false : true;
        this.H0 = !z || z2;
        S3(A3());
    }

    @Override // com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final int N1() {
        return R$layout.main_tab_layout;
    }

    public final boolean N3() {
        wwm wwmVar = this.T0;
        if (wwmVar != null) {
            return wwmVar.v();
        }
        return false;
    }

    public final void O3() {
        BottomFloatingNavigationBar bottomFloatingNavigationBar;
        List<Integer> listV3 = v3();
        if (Intrinsics.areEqual(listV3, this.d0)) {
            return;
        }
        k990 k990Var = new k990();
        k990Var.f996a = this;
        k990Var.b = listV3;
        VarHandle.storeStoreFence();
        i3j.e(k990Var, 3, "TabFragment");
        int size = listV3.size();
        this.d0.size();
        this.d0 = listV3;
        ViewPager2 viewPager2 = null;
        if (this.n0 >= size) {
            ViewPager2 viewPager3 = this.e0;
            if (viewPager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager3 = null;
            }
            viewPager3.setCurrentItem(0, false);
        }
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter = null;
        }
        tabFragmentAdapter.n(u3());
        BottomFloatingNavigationBar bottomFloatingNavigationBar2 = this.i0;
        if (bottomFloatingNavigationBar2 != null) {
            bottomFloatingNavigationBar2.inflateMenu(J3() ? 2131755054 : 2131755055);
        }
        BottomFloatingNavigationBar bottomFloatingNavigationBar3 = this.i0;
        if (bottomFloatingNavigationBar3 != null) {
            this.j0 = bottomFloatingNavigationBar3.getMenuView();
            G3();
            ViewPager2 viewPager4 = this.e0;
            int iCoerceIn = viewPager4 != null ? RangesKt.coerceIn(viewPager4.getCurrentItem(), CollectionsKt.getIndices(this.d0)) : -1;
            r990 r990Var = new r990();
            r990Var.f1546a = iCoerceIn;
            r990Var.b = this;
            r990Var.c = bottomFloatingNavigationBar3;
            VarHandle.storeStoreFence();
            bottomFloatingNavigationBar3.post(r990Var);
        }
        ViewPager2 viewPager5 = this.e0;
        if (viewPager5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        } else {
            viewPager2 = viewPager5;
        }
        int iCoerceIn2 = RangesKt.coerceIn(viewPager2.getCurrentItem(), CollectionsKt.getIndices(this.d0));
        if (iCoerceIn2 >= 0 && iCoerceIn2 < this.d0.size() && (bottomFloatingNavigationBar = this.i0) != null) {
            bottomFloatingNavigationBar.setSelectedItemId(this.d0.get(iCoerceIn2).intValue());
        }
        X3(iCoerceIn2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void P3(int i) {
        if (i < 0 || i >= this.d0.size()) {
            return;
        }
        if (this.o0) {
            exitSelectionMode();
        }
        ViewPager2 viewPager2 = this.e0;
        if (viewPager2 != null) {
            if (viewPager2.getCurrentItem() != i) {
                ViewPager2 viewPager3 = this.e0;
                if (viewPager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                    viewPager3 = null;
                }
                viewPager3.setCurrentItem(i, false);
            }
            if (i >= 0 && i < this.d0.size()) {
                BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
                if (bottomFloatingNavigationBar != null) {
                    bottomFloatingNavigationBar.setSelectedItemId(this.d0.get(i).intValue());
                }
                X3(i);
                if (this.n0 == -1) {
                    this.n0 = i;
                }
            }
        } else {
            this.n0 = i;
        }
        this.R0 = i;
        this.Q0 = true;
        if (!isResumed() || this.e0 == null) {
            return;
        }
        C3();
    }

    public final void Q3(boolean z) {
        ViewPager2 viewPager2 = this.e0;
        ViewPager2 viewPager3 = null;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        int offscreenPageLimit = viewPager2.getOffscreenPageLimit();
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter = null;
        }
        int offscreenPageLimit2 = 1;
        if (offscreenPageLimit >= tabFragmentAdapter.getItemCount() - 1) {
            return;
        }
        if (!z) {
            ViewPager2 viewPager4 = this.e0;
            if (viewPager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager4 = null;
            }
            if (viewPager4.getOffscreenPageLimit() == -1) {
                return;
            }
        }
        ViewPager2 viewPager5 = this.e0;
        if (viewPager5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager5 = null;
        }
        ViewPager2 viewPager6 = this.e0;
        if (viewPager6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager6 = null;
        }
        if (viewPager6.getOffscreenPageLimit() > 0) {
            ViewPager2 viewPager7 = this.e0;
            if (viewPager7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                viewPager7 = null;
            }
            offscreenPageLimit2 = 1 + viewPager7.getOffscreenPageLimit();
        }
        viewPager5.setOffscreenPageLimit(offscreenPageLimit2);
        y990 y990Var = new y990();
        y990Var.f2061a = this;
        VarHandle.storeStoreFence();
        i3j.e(y990Var, 3, "TabFragment");
        ViewPager2 viewPager8 = this.e0;
        if (viewPager8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        } else {
            viewPager3 = viewPager8;
        }
        viewPager3.postDelayed(this.f1, 250L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void R3(int i, boolean z) {
        q990 q990Var = new q990();
        q990Var.f1479a = this;
        q990Var.b = i;
        q990Var.c = z;
        VarHandle.storeStoreFence();
        i3j.e(q990Var, 3, "TabFragment");
        if (this.e0 == null) {
            this.n0 = i;
            return;
        }
        ViewPager2 viewPager2 = null;
        if (this.n0 != i) {
            exitSelectionMode();
            ViewPager2 viewPager3 = this.e0;
            if (viewPager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            } else {
                viewPager2 = viewPager3;
            }
            viewPager2.setCurrentItem(i, false);
            return;
        }
        Context context = getContext();
        if (context == null || z) {
            return;
        }
        boolean z2 = fum.h(R2()) && fum.e(R2());
        boolean zM = oh30.m(context);
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter = null;
        }
        com.oplus.gallery.foundation.uikit.responsiveui.b bVarM = tabFragmentAdapter.m(this.n0);
        boolean zIsVisible = bVarM != null ? bVarM.isVisible() : false;
        if (z2 && zIsVisible && zM) {
            TabFragmentAdapter tabFragmentAdapter2 = this.f0;
            if (tabFragmentAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
                tabFragmentAdapter2 = null;
            }
            TemplateFragment templateFragmentM = tabFragmentAdapter2.m(this.n0);
            TemplateFragment templateFragment = templateFragmentM != null ? templateFragmentM : null;
            if (templateFragment != null) {
                templateFragment.b0();
            }
        }
    }

    public final void S3(int i) {
        COUIBottomFloatingAccessoryView startAccessoryView;
        BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
        if (bottomFloatingNavigationBar == null) {
            return;
        }
        if (i == 0) {
            if (L3() && !N3() && (startAccessoryView = bottomFloatingNavigationBar.getStartAccessoryView()) != null) {
                startAccessoryView.setEnabled(this.G0 && !this.o0);
            }
            COUIBottomFloatingAccessoryView endAccessoryView = bottomFloatingNavigationBar.getEndAccessoryView();
            if (endAccessoryView != null) {
                endAccessoryView.setEnabled(this.H0 && !this.o0);
                return;
            }
            return;
        }
        if (i != 1) {
            return;
        }
        COUIBottomFloatingAccessoryView startAccessoryView2 = bottomFloatingNavigationBar.getStartAccessoryView();
        if (startAccessoryView2 != null) {
            startAccessoryView2.setEnabled(true);
        }
        COUIBottomFloatingAccessoryView endAccessoryView2 = bottomFloatingNavigationBar.getEndAccessoryView();
        if (endAccessoryView2 != null) {
            endAccessoryView2.setEnabled(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void T3() {
        if (isResumed()) {
            TabFragmentAdapter tabFragmentAdapter = this.f0;
            if (tabFragmentAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
                tabFragmentAdapter = null;
            }
            if (tabFragmentAdapter.m(this.n0) instanceof TimelineTabFragment) {
                D1(0);
                return;
            }
            Context context = getContext();
            if (context != null) {
                D1(this.o0 ? context.getColor(2131101722) : 0);
            }
        }
    }

    public final void V3(SidePaneState sidePaneState) {
        BottomFloatingNavigationBar bottomFloatingNavigationBar;
        boolean z = false;
        boolean z2 = (sidePaneState.isOpen() || this.o0 || N3()) ? false : true;
        if (z2 && (bottomFloatingNavigationBar = this.i0) != null) {
            bottomFloatingNavigationBar.setVisibility(0);
        }
        this.v0.d(z2);
        boolean zIsOpen = sidePaneState.isOpen();
        q67 q67Var = this.w0;
        if (zIsOpen) {
            q67Var.c(false);
            this.F0 = false;
            return;
        }
        iwm iwmVarB3 = B3();
        if ((iwmVarB3 != null ? iwmVarB3.k() : false) && !M3()) {
            z = true;
        }
        if (z) {
            q67Var.c(true);
            this.F0 = true;
        }
    }

    @Override // com.oplus.aiunit.vision.kum
    public final void W(int i) {
    }

    @Override // com.oplus.aiunit.vision.efm
    public final void W0(boolean z) {
        this.x0 = z;
        b4();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void W3(int i, boolean z) {
        COUIBottomFloatingAccessoryView endAccessoryView;
        COUIBottomFloatingAccessoryView startAccessoryView;
        BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
        if (bottomFloatingNavigationBar == null) {
            return;
        }
        boolean zK3 = K3(Integer.valueOf(i));
        boolean z2 = i == 1;
        boolean z3 = (zK3 && L3()) || z2;
        boolean z4 = zK3 || z2;
        boolean z5 = this.o0 || (zK3 && N3());
        COUIBottomFloatingAccessoryView startAccessoryView2 = bottomFloatingNavigationBar.getStartAccessoryView();
        if (startAccessoryView2 != null) {
            if (M3()) {
                startAccessoryView2.setVisibility(4);
            } else if (z3 && this.I0) {
                startAccessoryView2.setVisibility(z5 ? 4 : 0);
            } else if (!z3 && this.I0) {
                bottomFloatingNavigationBar.hideStartAccessoryView(z);
                this.I0 = false;
            } else if (z3 && !this.I0) {
                if (z5) {
                    bottomFloatingNavigationBar.showStartAccessoryView(false);
                    this.I0 = true;
                    startAccessoryView2.setVisibility(4);
                } else {
                    startAccessoryView2.setVisibility(0);
                    bottomFloatingNavigationBar.showStartAccessoryView(z);
                    this.I0 = true;
                }
            }
        }
        COUIBottomFloatingAccessoryView endAccessoryView2 = bottomFloatingNavigationBar.getEndAccessoryView();
        if (endAccessoryView2 != null) {
            if (M3()) {
                endAccessoryView2.setVisibility(4);
            } else if (z4 && this.J0) {
                endAccessoryView2.setVisibility(z5 ? 4 : 0);
            } else if (!z4 && this.J0) {
                bottomFloatingNavigationBar.hideEndAccessoryView(z);
                this.J0 = false;
            } else if (z4 && !this.J0) {
                if (z5) {
                    bottomFloatingNavigationBar.showEndAccessoryView(false);
                    this.J0 = true;
                    endAccessoryView2.setVisibility(4);
                } else {
                    endAccessoryView2.setVisibility(0);
                    bottomFloatingNavigationBar.showEndAccessoryView(z);
                    this.J0 = true;
                }
            }
        }
        if (!I3()) {
            if (z3 && this.I0 && (startAccessoryView = bottomFloatingNavigationBar.getStartAccessoryView()) != null) {
                if (startAccessoryView.getVisibility() != 0) {
                    startAccessoryView = null;
                }
                if (startAccessoryView != null) {
                    hpj.a(1.0f, startAccessoryView);
                }
            }
            if (z4 && this.J0 && (endAccessoryView = bottomFloatingNavigationBar.getEndAccessoryView()) != null) {
                COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView = endAccessoryView.getVisibility() == 0 ? endAccessoryView : null;
                if (cOUIBottomFloatingAccessoryView != null) {
                    hpj.a(1.0f, cOUIBottomFloatingAccessoryView);
                }
            }
        }
        if (zK3 || z2) {
            if (zK3) {
                if (L3()) {
                    bottomFloatingNavigationBar.setStartAccessoryIcon(R$drawable.main_ic_picked, this.I0);
                    COUIBottomFloatingAccessoryView startAccessoryView3 = bottomFloatingNavigationBar.getStartAccessoryView();
                    if (startAccessoryView3 != null) {
                        startAccessoryView3.setContentDescription(getString(2132021755));
                    }
                }
                Y3();
                COUIBottomFloatingAccessoryView endAccessoryView3 = bottomFloatingNavigationBar.getEndAccessoryView();
                if (endAccessoryView3 != null) {
                    endAccessoryView3.setContentDescription(getString(R$string.talkback_main_tab_photos_filter));
                }
            } else if (z2) {
                bottomFloatingNavigationBar.setStartAccessoryIcon(R$drawable.main_ic_segment_album_sort, this.I0);
                COUIBottomFloatingAccessoryView startAccessoryView4 = bottomFloatingNavigationBar.getStartAccessoryView();
                if (startAccessoryView4 != null) {
                    startAccessoryView4.setContentDescription(getString(R$string.talkback_main_tab_album_sort));
                }
                bottomFloatingNavigationBar.setEndAccessoryIcon(R$drawable.main_ic_add_album, this.J0);
                COUIBottomFloatingAccessoryView endAccessoryView4 = bottomFloatingNavigationBar.getEndAccessoryView();
                if (endAccessoryView4 != null) {
                    endAccessoryView4.setContentDescription(getString(R$string.talkback_main_tab_album_add_new));
                }
            }
        }
        S3(i);
    }

    public final void X3(int i) {
        if (i < 0 || i >= this.d0.size()) {
            return;
        }
        boolean zK3 = K3(Integer.valueOf(i));
        boolean zK4 = K3(Integer.valueOf(i));
        j67 j67Var = this.s0;
        boolean z = j67Var != null && j67Var.e();
        n67 n67Var = this.v0;
        if (zK4) {
            if (z || this.r0.e() || n67Var.c().isRunning()) {
                return;
            }
        } else if (z) {
            j67 j67Var2 = this.s0;
            if (j67Var2 != null) {
                j67Var2.c();
            }
            COUISegmentButtonLayout cOUISegmentButtonLayoutE3 = E3();
            if (cOUISegmentButtonLayoutE3 != null) {
                cOUISegmentButtonLayoutE3.setEnabled(true);
            }
            BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
            if (bottomFloatingNavigationBar != null) {
                bottomFloatingNavigationBar.setEnabled(true);
            }
        }
        boolean z2 = ((K3(Integer.valueOf(i)) && (N3() || this.o0)) || (fum.g(R2()) && fum.h(R2()) && fum.e(R2()))) ? false : true;
        W3(i, true);
        if (!n67Var.c().isRunning()) {
            BottomFloatingNavigationBar bottomFloatingNavigationBar2 = this.i0;
            if (bottomFloatingNavigationBar2 != null) {
                bottomFloatingNavigationBar2.setVisibility(z2 ? 0 : 4);
            }
            COUIBottomFloatingNavigationBarMenuView cOUIBottomFloatingNavigationBarMenuView = this.j0;
            if (cOUIBottomFloatingNavigationBarMenuView != null) {
                cOUIBottomFloatingNavigationBarMenuView.setVisibility(z2 ? 0 : 4);
                if (z2) {
                    hpj.a(1.0f, cOUIBottomFloatingNavigationBarMenuView);
                }
            }
        }
        if (!zK3) {
            SpaceView spaceView = this.k0;
            if (spaceView != null) {
                spaceView.setVisibility(4);
                spaceView.setClickable(false);
            }
            BlurButtonWrapperView blurButtonWrapperView = this.m0;
            if (blurButtonWrapperView != null) {
                blurButtonWrapperView.setVisibility(4);
            }
            BlurButtonWrapperView blurButtonWrapperView2 = this.l0;
            if (blurButtonWrapperView2 != null) {
                blurButtonWrapperView2.setVisibility(4);
            }
            ConstraintLayout constraintLayout = this.h0;
            if (constraintLayout != null) {
                constraintLayout.setVisibility(8);
                return;
            }
            return;
        }
        boolean z3 = N3() && !this.o0;
        u990 u990Var = new u990();
        u990Var.f1779a = this;
        VarHandle.storeStoreFence();
        i3j.e(u990Var, 3, "TabFragment");
        SpaceView spaceView2 = this.k0;
        if (spaceView2 != null) {
            spaceView2.setVisibility(z3 ? 0 : 4);
            spaceView2.setClickable(z3);
        }
        BlurButtonWrapperView blurButtonWrapperView3 = this.m0;
        if (blurButtonWrapperView3 != null) {
            blurButtonWrapperView3.setVisibility(z3 ? 0 : 4);
        }
        BlurButtonWrapperView blurButtonWrapperView4 = this.l0;
        if (blurButtonWrapperView4 != null) {
            blurButtonWrapperView4.setVisibility(z3 ? 0 : 4);
        }
        ConstraintLayout constraintLayout2 = this.h0;
        if (constraintLayout2 != null) {
            constraintLayout2.setVisibility(z3 ? 0 : 8);
        }
    }

    @Override // com.oplus.gallery.basebiz.uikit.fragment.TemplateFragment
    public final boolean Y2() {
        return fum.g(R2());
    }

    public final void Y3() {
        int iB;
        BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
        if (bottomFloatingNavigationBar == null) {
            return;
        }
        if (K3(null)) {
            TabFragmentAdapter tabFragmentAdapter = this.f0;
            if (tabFragmentAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
                tabFragmentAdapter = null;
            }
            TimelineTabFragment timelineTabFragmentM = tabFragmentAdapter.m(0);
            TimelineTabFragment timelineTabFragment = timelineTabFragmentM instanceof TimelineTabFragment ? timelineTabFragmentM : null;
            if (timelineTabFragment == null || !timelineTabFragment.isAdded()) {
                iB = 2131233398;
            } else if (timelineTabFragment.isAdded()) {
                TimelineViewModel timelineViewModelY3 = timelineTabFragment.Y3();
                if (timelineViewModelY3 == null) {
                    timelineViewModelY3 = null;
                }
                Map mapT = timelineViewModelY3 != null ? timelineViewModelY3.t() : null;
                if (mapT == null) {
                    mapT = MapsKt.emptyMap();
                }
                iB = fhi.b(fhi.a(mapT), R$drawable.main_ic_segment_filter_state, timelineTabFragment.x5());
            } else {
                iB = R$drawable.main_ic_segment_filter_state;
            }
            bottomFloatingNavigationBar.setEndAccessoryIcon(iB, this.J0);
        }
    }

    @Override // com.oplus.aiunit.vision.efm
    public final View Z0() {
        BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
        if (bottomFloatingNavigationBar != null) {
            return bottomFloatingNavigationBar.getEndAccessoryView();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Z3(int i) {
        ConstraintLayout constraintLayout = this.h0;
        if (constraintLayout == null) {
            return;
        }
        constraintLayout.setMinHeight(getResources().getDimensionPixelSize(2131170443) + i);
        constraintLayout.setPadding(constraintLayout.getPaddingLeft(), constraintLayout.getPaddingTop(), constraintLayout.getPaddingRight(), i);
    }

    @Override // com.oplus.gallery.basebiz.uikit.fragment.TemplateFragment, com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final void a(AppUiResponder.a aVar) {
        aVar.getClass();
        super.a(aVar);
        if (aVar.a.b()) {
            if (this.U0) {
                z3("onAppUiStateChanged");
            }
            a4();
        }
    }

    @Override // com.oplus.aiunit.vision.efm
    public final void a0() {
        if (K3(null) && !N3() && L3()) {
            H3();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a4() {
        Context context;
        int iIntValue;
        ViewGroup.LayoutParams layoutParams;
        if (L3() && fum.g(R2()) && (context = getContext()) != null) {
            if (fum.h(R2())) {
                iIntValue = oh30.q(context) ? ((Number) this.C0.getValue()).intValue() : ((Number) this.D0.getValue()).intValue();
            } else {
                iIntValue = ((Number) this.B0.getValue()).intValue();
            }
            BlurButtonWrapperView blurButtonWrapperView = this.l0;
            if (blurButtonWrapperView != null) {
                int i = BlurButtonWrapperView.j;
                blurButtonWrapperView.getLayoutParams().width = iIntValue;
                View view = blurButtonWrapperView.buttonView;
                if (view == null || (layoutParams = view.getLayoutParams()) == null) {
                    return;
                }
                layoutParams.width = iIntValue;
            }
        }
    }

    public final void b4() {
        ViewPager2 viewPager2 = this.e0;
        if (viewPager2 == null) {
            return;
        }
        viewPager2.setUserInputEnabled(this.x0 && !F3().e);
    }

    @Override // com.oplus.aiunit.vision.efm
    public final void c0(boolean z) {
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter = null;
        }
        com.oplus.gallery.foundation.uikit.responsiveui.b bVarM = tabFragmentAdapter.m(1);
        if (bVarM != null) {
            iwm iwmVar = bVarM instanceof iwm ? (iwm) bVarM : null;
            if (iwmVar != null) {
                iwmVar.m0(z);
            }
        }
        TabFragmentAdapter tabFragmentAdapter2 = this.f0;
        if (tabFragmentAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter2 = null;
        }
        com.oplus.gallery.foundation.uikit.responsiveui.b bVarM2 = tabFragmentAdapter2.m(2);
        if (bVarM2 != null) {
            iwm iwmVar2 = bVarM2 instanceof iwm ? (iwm) bVarM2 : null;
            if (iwmVar2 != null) {
                iwmVar2.m0(z);
            }
        }
    }

    @Override // com.oplus.aiunit.vision.efm
    public final void c1(boolean z, boolean z2) {
        fjj.c("TabFragment.checkPageImmersiveState");
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter = null;
        }
        com.oplus.gallery.foundation.uikit.responsiveui.b bVarM = tabFragmentAdapter.m(this.n0);
        if (bVarM == null || bVarM.isAdded()) {
            dum dumVarR2 = R2();
            boolean z3 = false;
            if (dumVarR2 != null ? dumVarR2.k() : false) {
                return;
            }
            if (z2 && !this.L0 && !this.S0) {
                z3 = true;
            }
            x3(z, z3);
            fjj.f();
        }
    }

    public final void e1() {
        R3(1, false);
    }

    @Override // com.oplus.aiunit.vision.rtm
    public final void exitSelectionMode() {
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter = null;
        }
        com.oplus.gallery.foundation.uikit.responsiveui.b bVarM = tabFragmentAdapter.m(this.n0);
        rtm rtmVar = bVarM instanceof rtm ? (rtm) bVarM : null;
        if (rtmVar != null) {
            rtmVar.exitSelectionMode();
        }
        h(false);
    }

    @Override // com.oplus.aiunit.vision.ium, com.oplus.aiunit.vision.kum
    public final void f(SidePaneState sidePaneState, SidePaneState sidePaneState2) {
        sidePaneState.getClass();
        sidePaneState2.getClass();
        W0((this.o0 || sidePaneState.isVisible()) ? false : true);
        z3("onSidePaneStateChanged");
        T3();
        if (w3()) {
            dum dumVarR2 = R2();
            if (!(dumVarR2 != null ? dumVarR2.k() : false)) {
                if (this.v0.c().isRunning()) {
                    return;
                }
                X3(A3());
                return;
            }
        }
        V3(sidePaneState);
        X3(A3());
    }

    @Override // com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final boolean g2() {
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter == null) {
            return true;
        }
        ViewPager2 viewPager2 = null;
        if (tabFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter = null;
        }
        ViewPager2 viewPager3 = this.e0;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        } else {
            viewPager2 = viewPager3;
        }
        TemplateFragment templateFragmentM = tabFragmentAdapter.m(viewPager2.getCurrentItem());
        templateFragmentM.getClass();
        return templateFragmentM.g2();
    }

    public final Set<String> getAffinities() {
        return null;
    }

    public final String getContainer() {
        return null;
    }

    /* JADX INFO: renamed from: getId, reason: from getter */
    public final String getB0() {
        return this.b0;
    }

    public final Lifecycle.Event getLifeCycleEvent() {
        return this.g;
    }

    public final Set<String> getLinkages() {
        return null;
    }

    public final qd90 getTaskConfigStrategy() {
        return (qd90) this.c0.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.aiunit.vision.efm
    public final void h(boolean z) {
        List listFilterNotNull;
        COUIBottomFloatingEditToolbarMenuView menuView;
        o990 o990Var = new o990();
        o990Var.f1315a = this;
        o990Var.b = z;
        VarHandle.storeStoreFence();
        i3j.h(o990Var, 2, "TabFragment");
        BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
        if (bottomFloatingNavigationBar != null) {
            bottomFloatingNavigationBar.setEnabled(!z);
        }
        if (this.o0 == z || B3() == null) {
            return;
        }
        this.o0 = z;
        S3(A3());
        T3();
        E1(z);
        W0((z || fum.h(R2())) ? false : true);
        boolean zIsInForeground = super.isInForeground();
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            tabFragmentAdapter = null;
        }
        ViewPager2 viewPager2 = this.e0;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        TemplateFragment templateFragmentM = tabFragmentAdapter.m(viewPager2.getCurrentItem());
        if (templateFragmentM == 0 || !(templateFragmentM instanceof iwm)) {
            return;
        }
        ConstraintLayout constraintLayout = (L3() && N3()) ? this.h0 : this.i0;
        if (constraintLayout == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        boolean zM3 = M3();
        if (K3(null)) {
            if (L3() && N3()) {
                arrayList.add(this.l0);
                arrayList.add(this.m0);
            }
            if (!N3() && !zM3) {
                arrayList.addAll(D3());
            }
            listFilterNotNull = CollectionsKt.filterNotNull(arrayList);
        } else {
            listFilterNotNull = CollectionsKt.filterNotNull(arrayList);
        }
        List list = listFilterNotNull;
        templateFragmentM.B2(((iwm) templateFragmentM).r1());
        View viewJ2 = templateFragmentM.J2();
        if (viewJ2 == null || (menuView = viewJ2.getMenuView()) == null) {
            return;
        }
        b bVar = this.c1;
        j67 j67Var = this.r0;
        j67Var.l = bVar;
        p990 p990Var = new p990();
        p990Var.f1397a = this;
        VarHandle.storeStoreFence();
        j67Var.m = p990Var;
        if (zIsInForeground) {
            j67Var.m(z, list, constraintLayout, CollectionsKt.listOfNotNull(menuView), viewJ2);
        } else {
            j67Var.g(z, list, constraintLayout, CollectionsKt.listOfNotNull(menuView), viewJ2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.aiunit.vision.efm
    public final void h1(boolean z, boolean z2) {
        int currentItem;
        boolean z3;
        Context context;
        boolean z4 = false;
        if (L3() && this.t0 != z) {
            this.t0 = z;
            if (this.s0 == null) {
                this.s0 = new j67();
            }
            j67 j67Var = this.s0;
            if (j67Var != null) {
                List<View> listEmptyList = (fum.g(R2()) && fum.h(R2()) && fum.e(R2())) ? CollectionsKt.emptyList() : D3();
                List listListOfNotNull = CollectionsKt.listOfNotNull(new BlurButtonWrapperView[]{this.m0, this.l0});
                BottomFloatingNavigationBar bottomFloatingNavigationBar = !listEmptyList.isEmpty() ? this.i0 : null;
                ConstraintLayout constraintLayout = this.h0;
                COUISegmentButtonLayout cOUISegmentButtonLayoutE3 = E3();
                if (cOUISegmentButtonLayoutE3 != null) {
                    cOUISegmentButtonLayoutE3.setEnabled(z);
                }
                BottomFloatingNavigationBar bottomFloatingNavigationBar2 = this.i0;
                if (bottomFloatingNavigationBar2 != null) {
                    bottomFloatingNavigationBar2.setEnabled(!z);
                }
                j67Var.l = this.c1;
                aa90 aa90Var = new aa90();
                aa90Var.f229a = this;
                VarHandle.storeStoreFence();
                j67Var.m = aa90Var;
                if (z2) {
                    z3 = z;
                    j67Var.m(z3, listEmptyList, bottomFloatingNavigationBar, listListOfNotNull, constraintLayout);
                } else {
                    z3 = z;
                    j67Var.g(z3, listEmptyList, bottomFloatingNavigationBar, listListOfNotNull, constraintLayout);
                }
                if (z3 && !this.u0) {
                    COUISegmentButtonLayout cOUISegmentButtonLayoutE4 = E3();
                    if (cOUISegmentButtonLayoutE4 != null && (context = getContext()) != null) {
                        db90 db90Var = new db90();
                        db90Var.f461a = this;
                        db90Var.b = context;
                        VarHandle.storeStoreFence();
                        cOUISegmentButtonLayoutE4.setSegmentButtonDrawDelegate(db90Var);
                        cOUISegmentButtonLayoutE4.invalidate();
                    }
                    iwm iwmVarB3 = B3();
                    boolean z5 = !((iwmVarB3 == null || iwmVarB3.k()) ? false : true);
                    BlurButtonWrapperView blurButtonWrapperView = this.m0;
                    if (blurButtonWrapperView != null) {
                        BlurButtonWrapperView.c(blurButtonWrapperView, z5, 0.0f, 0, 30);
                    }
                    BlurButtonWrapperView blurButtonWrapperView2 = this.l0;
                    if (blurButtonWrapperView2 != null) {
                        BlurButtonWrapperView.c(blurButtonWrapperView2, z5, 0.0f, 0, 30);
                    }
                    this.u0 = true;
                }
            }
        }
        ViewPager2 viewPager2 = this.e0;
        if (viewPager2 == null || (currentItem = viewPager2.getCurrentItem()) == -1) {
            return;
        }
        boolean zK3 = K3(Integer.valueOf(currentItem));
        if (!z2 && this.o0) {
            z4 = true;
        }
        if (!zK3 || z4) {
            X3(currentItem);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final sti i() {
        c cVar = new c();
        cVar.b = this;
        ((sti) cVar).a = this;
        VarHandle.storeStoreFence();
        return cVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.aiunit.vision.jum
    public final void k1(SidePaneState sidePaneState, float f) {
        sidePaneState.getClass();
        if (isResumed() && getContext() != null && ResourceUtils.f(getContext())) {
            z3("onSidePaneSliding");
        }
        if (w3() && sidePaneState.isClose() && !this.K0) {
            View view = getView();
            ViewParent parent = view != null ? view.getParent() : null;
            View view2 = parent instanceof View ? (View) parent : null;
            if ((view2 != null ? view2.getPaddingStart() : 0) > 0) {
                return;
            }
            this.K0 = true;
            U3(this);
        }
    }

    @Override // com.oplus.aiunit.vision.scm
    public final void n0() {
        if (this.o0) {
            exitSelectionMode();
        }
    }

    @Override // com.oplus.aiunit.vision.efm
    public final List<View> o() {
        if (!K3(null) || this.o0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!N3()) {
            arrayList.addAll(D3());
            return CollectionsKt.filterNotNull(arrayList);
        }
        arrayList.add(this.m0);
        arrayList.add(this.l0);
        return CollectionsKt.filterNotNull(arrayList);
    }

    @Override // com.oplus.aiunit.vision.jum
    public final void o0(SidePaneState sidePaneState) {
        sidePaneState.getClass();
        sidePaneState.getClass();
        if (sidePaneState.isClose()) {
            this.K0 = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onActivityResult(int i, int i2, Intent intent) {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        fragments.getClass();
        Iterator<T> it = fragments.iterator();
        while (it.hasNext()) {
            ((Fragment) it.next()).onActivityResult(i, i2, intent);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final void onCreate(Bundle bundle) {
        Context context = getContext();
        if (context != null) {
            GalleryApplication applicationContext = context.getApplicationContext();
            GalleryApplication galleryApplication = applicationContext instanceof GalleryApplication ? applicationContext : null;
            pwm pwmVar = (pwm) (galleryApplication != null ? galleryApplication.a(pwm.class) : null);
            if (pwmVar != null) {
                try {
                    pwmVar.J6(this);
                    Unit unit = Unit.INSTANCE;
                    AutoCloseableKt.closeFinally(pwmVar, (Throwable) null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        AutoCloseableKt.closeFinally(pwmVar, th);
                        throw th2;
                    }
                }
            }
        }
        super.onCreate(bundle);
        this.M = true;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.L0 = (activity.getResources().getConfiguration().uiMode & 48) == 32;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.gallery.basebiz.uikit.fragment.TemplateFragment, com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final void onDestroy() {
        super.onDestroy();
        Context context = getContext();
        if (context != null) {
            GalleryApplication applicationContext = context.getApplicationContext();
            GalleryApplication galleryApplication = applicationContext instanceof GalleryApplication ? applicationContext : null;
            pwm pwmVar = (pwm) (galleryApplication != null ? galleryApplication.a(pwm.class) : null);
            if (pwmVar != null) {
                try {
                    pwmVar.s5(this);
                    Unit unit = Unit.INSTANCE;
                    AutoCloseableKt.closeFinally(pwmVar, (Throwable) null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        AutoCloseableKt.closeFinally(pwmVar, th);
                        throw th2;
                    }
                }
            }
        }
        this.r0.c();
        j67 j67Var = this.s0;
        if (j67Var != null) {
            j67Var.c();
        }
        COUISegmentButtonLayout cOUISegmentButtonLayoutE3 = E3();
        if (cOUISegmentButtonLayoutE3 != null) {
            cOUISegmentButtonLayoutE3.setEnabled(true);
        }
        BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
        if (bottomFloatingNavigationBar != null) {
            bottomFloatingNavigationBar.setEnabled(true);
        }
        n67 n67Var = this.v0;
        n67Var.c().cancel();
        n67Var.e = null;
        n67Var.f = null;
        int i = 0;
        n67Var.g = false;
        n67Var.c = null;
        n67Var.b = CollectionsKt.emptyList();
        q67 q67Var = this.w0;
        ValueAnimator valueAnimator = q67Var.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        q67Var.b = null;
        q67Var.f1472a = null;
        F3().a();
        y3();
        Lazy lazy = this.M0;
        ((seo) lazy.getValue()).d();
        ((seo) lazy.getValue()).c();
        Lazy lazy2 = this.N0;
        ((qvc) lazy2.getValue()).d();
        ((qvc) lazy2.getValue()).c();
        TabFragmentAdapter tabFragmentAdapter = this.f0;
        if (tabFragmentAdapter != null) {
            Lazy lazy3 = tabFragmentAdapter.g;
            if (tabFragmentAdapter.b.isEmpty()) {
                TemplateFragment[] templateFragmentArr = (TemplateFragment[]) lazy3.getValue();
                int length = templateFragmentArr.length;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 + 1;
                    if (templateFragmentArr[i] != null) {
                        ((TemplateFragment[]) lazy3.getValue())[i2] = null;
                    }
                    i++;
                    i2 = i3;
                }
            } else {
                TemplateFragment[] templateFragmentArr2 = tabFragmentAdapter.c;
                int length2 = templateFragmentArr2.length;
                int i4 = 0;
                while (i < length2) {
                    int i5 = i4 + 1;
                    if (templateFragmentArr2[i] != null) {
                        tabFragmentAdapter.c[i4] = null;
                    }
                    i++;
                    i4 = i5;
                }
            }
        }
        this.P0 = null;
        pab.i("show_creation_tab", this.b1);
    }

    @Override // com.oplus.gallery.basebiz.uikit.fragment.TemplateFragment, com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final void onDestroyView() {
        View view;
        F3().a();
        View view2 = this.w;
        ViewPager2 viewPager2 = null;
        if ((view2 != null ? view2.getForeground() : null) != null && (view = this.w) != null) {
            view.setForeground(null);
        }
        q67 q67Var = this.w0;
        ValueAnimator valueAnimator = q67Var.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        q67Var.b = null;
        q67Var.f1472a = null;
        n67 n67Var = this.v0;
        n67Var.c().cancel();
        n67Var.e = null;
        n67Var.f = null;
        n67Var.g = false;
        n67Var.c = null;
        n67Var.b = CollectionsKt.emptyList();
        ViewPager2 viewPager3 = this.e0;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        } else {
            viewPager2 = viewPager3;
        }
        viewPager2.removeCallbacks(this.f1);
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final void onPause() {
        super.onPause();
        pab.i("show_creation_tab", this.b1);
        this.S0 = false;
        ViewPager2 viewPager2 = this.e0;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        viewPager2.removeCallbacks(this.f1);
        ViewPager2 viewPager3 = this.e0;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager3 = null;
        }
        viewPager3.setOffscreenPageLimit(-1);
        Context context = getContext();
        if (context != null) {
            GalleryApplication applicationContext = context.getApplicationContext();
            GalleryApplication galleryApplication = applicationContext instanceof GalleryApplication ? applicationContext : null;
            pwm pwmVar = (pwm) (galleryApplication != null ? galleryApplication.a(pwm.class) : null);
            if (pwmVar != null) {
                try {
                    pwmVar.a9(this);
                    Unit unit = Unit.INSTANCE;
                    AutoCloseableKt.closeFinally(pwmVar, (Throwable) null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        AutoCloseableKt.closeFinally(pwmVar, th);
                        throw th2;
                    }
                }
            }
        }
        View view = getView();
        TemplateFragment.E2(view instanceof ViewGroup ? (ViewGroup) view : null, this.p0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final void onResume() {
        super.onResume();
        pab.h("show_creation_tab", this.b1);
        O3();
        TabFragmentAdapter tabFragmentAdapter = null;
        if (!this.S0) {
            boolean zBooleanValue = ((Boolean) this.V0.getValue()).booleanValue();
            ViewPager2 viewPager2 = this.e0;
            if (zBooleanValue) {
                if (viewPager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                    viewPager2 = null;
                }
                viewPager2.setOffscreenPageLimit(1);
            } else {
                if (viewPager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                    viewPager2 = null;
                }
                TabFragmentAdapter tabFragmentAdapter2 = this.f0;
                if (tabFragmentAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
                    tabFragmentAdapter2 = null;
                }
                viewPager2.setOffscreenPageLimit(tabFragmentAdapter2.getItemCount());
            }
        }
        Context context = getContext();
        if (context != null) {
            GalleryApplication applicationContext = context.getApplicationContext();
            GalleryApplication galleryApplication = applicationContext instanceof GalleryApplication ? applicationContext : null;
            pwm pwmVar = (pwm) (galleryApplication != null ? galleryApplication.a(pwm.class) : null);
            if (pwmVar != null) {
                try {
                    pwmVar.Y8(this);
                    Unit unit = Unit.INSTANCE;
                    AutoCloseableKt.closeFinally(pwmVar, (Throwable) null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        AutoCloseableKt.closeFinally(pwmVar, th);
                        throw th2;
                    }
                }
            }
        }
        Context context2 = getContext();
        if (context2 != null) {
            BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new TabFragment$downloadRestrictWatermark$1$1(context2, null), 2, (Object) null);
        }
        TemplateFragment.y2(this);
        T3();
        E1(this.o0);
        TemplateFragment.l3(this.p0);
        if (gpa0.b == 2) {
            gpa0.g = oh30.a(ibk.e("debug.check.extra.width.height.dp", 50));
        }
        SceneDispatcher.a(1);
        this.U0 = false;
        int i = this.n0;
        if (i >= 0 && i < this.d0.size()) {
            TabFragmentAdapter tabFragmentAdapter3 = this.f0;
            if (tabFragmentAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
            } else {
                tabFragmentAdapter = tabFragmentAdapter3;
            }
            com.oplus.gallery.foundation.uikit.responsiveui.b bVarM = tabFragmentAdapter.m(i);
            if (bVarM != null && bVarM.isAdded() && bVarM.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                getChildFragmentManager().beginTransaction().setMaxLifecycle(bVarM, Lifecycle.State.RESUMED).commitNowAllowingStateLoss();
            }
        }
        C3();
    }

    @Override // com.oplus.gallery.basebiz.uikit.fragment.BaseFragment
    public final void onStop() {
        super.onStop();
        this.U0 = true;
    }

    @Override // com.oplus.aiunit.vision.efm
    public final void q1() {
        Y3();
        S3(A3());
    }

    @Override // com.oplus.aiunit.vision.rtm
    /* JADX INFO: renamed from: s, reason: from getter */
    public final boolean getO0() {
        return this.o0;
    }

    public final List<TabFragmentAdapter.a> u3() {
        TabFragmentAdapter.a aVar = new TabFragmentAdapter.a();
        aVar.f37a = R$string.main_fragment_title_timeline;
        aVar.b = "router://main/timeline_tab_fragment";
        VarHandle.storeStoreFence();
        TabFragmentAdapter.a aVar2 = new TabFragmentAdapter.a();
        aVar2.f37a = 2132021670;
        aVar2.b = "router://main/main_tab_album_set_fragment";
        VarHandle.storeStoreFence();
        List<TabFragmentAdapter.a> listMutableListOf = CollectionsKt.mutableListOf(new TabFragmentAdapter.a[]{aVar, aVar2});
        if (J3()) {
            TabFragmentAdapter.a aVar3 = new TabFragmentAdapter.a();
            aVar3.f37a = R$string.main_fragment_title_creation;
            aVar3.b = "router://main/creation_tab_fragment";
            VarHandle.storeStoreFence();
            listMutableListOf.add(aVar3);
        }
        return listMutableListOf;
    }

    public final List<Integer> v3() {
        List<Integer> listMutableListOf = CollectionsKt.mutableListOf(new Integer[]{2131431157, 2131427975});
        if (J3()) {
            listMutableListOf.add(2131428609);
        }
        return listMutableListOf;
    }

    @Override // com.oplus.aiunit.vision.efm
    public final int w() {
        BottomFloatingNavigationBar bottomFloatingNavigationBar = this.i0;
        if (bottomFloatingNavigationBar != null) {
            return gad0.c(bottomFloatingNavigationBar, false).top;
        }
        return 0;
    }

    public final boolean w3() {
        return Y2() && fum.h(R2()) && !this.o0 && !N3();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void x3(boolean z, boolean z2) {
        COUISegmentButtonLayout cOUISegmentButtonLayoutE3;
        Context context;
        iwm iwmVarB3 = B3();
        if (iwmVarB3 == null) {
            return;
        }
        boolean zK = iwmVarB3.k();
        boolean z3 = zK && !M3();
        if ((this.F0 != z3 || z) && !I3()) {
            ValueAnimator valueAnimator = this.w0.b;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                y3();
                this.F0 = z3;
                return;
            }
            this.F0 = z3;
            boolean zI3 = I3();
            Lazy lazy = this.N0;
            if (!zI3 && getContext() != null) {
                y3();
                boolean z4 = zK && !M3();
                ImageView imageView = (ImageView) this.z0.getValue();
                if (imageView != null) {
                    if (z4) {
                        imageView.setVisibility(0);
                    }
                    pua0 pua0Var = new pua0(imageView, (qvc) lazy.getValue(), z2 && !this.o0, z4);
                    pua0Var.f();
                    this.A0.add(pua0Var);
                }
            }
            if (K3(null) && N3() && (cOUISegmentButtonLayoutE3 = E3()) != null && (context = getContext()) != null) {
                db90 db90Var = new db90();
                db90Var.f461a = this;
                db90Var.b = context;
                VarHandle.storeStoreFence();
                cOUISegmentButtonLayoutE3.setSegmentButtonDrawDelegate(db90Var);
                cOUISegmentButtonLayoutE3.invalidate();
            }
            Context context2 = getContext();
            if (context2 != null) {
                seo seoVar = (seo) this.M0.getValue();
                boolean z5 = !zK;
                seoVar.getClass();
                if (COUIDarkModeUtil.isNightMode(context2)) {
                    seoVar.g(false, false);
                } else {
                    seoVar.g(z5, z2);
                }
                ((qvc) lazy.getValue()).g(z5, z2);
            }
        }
    }

    public final void y3() {
        ArrayList arrayList = this.A0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((uvc) it.next()).g();
        }
        arrayList.clear();
    }

    public final void z3(String str) {
        ViewPager2 viewPager2 = this.e0;
        ViewPager2 viewPager3 = null;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            viewPager2 = null;
        }
        View childAt = viewPager2.getChildAt(0);
        RecyclerView recyclerView = childAt instanceof RecyclerView ? (RecyclerView) childAt : null;
        l990 l990Var = new l990();
        l990Var.f1084a = this;
        l990Var.b = str;
        l990Var.c = recyclerView;
        VarHandle.storeStoreFence();
        i3j.e(l990Var, 3, "TabFragment");
        if (recyclerView != null) {
            ViewPager2 viewPager4 = this.e0;
            if (viewPager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            } else {
                viewPager3 = viewPager4;
            }
            recyclerView.scrollToPosition(viewPager3.getCurrentItem());
        }
    }
}
