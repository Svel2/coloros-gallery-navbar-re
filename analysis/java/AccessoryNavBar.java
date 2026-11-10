package com.coui.appcompat.bottomfloatingaccessorynavigationbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import com.coui.appcompat.COUIMaterialEffect;
import com.coui.appcompat.bottomfloatingnavigationbar.BaseBottomFloatingItem;
import com.coui.appcompat.bottomfloatingnavigationbar.BaseBottomFloatingMenuView;
import com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBarItem;
import com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBarMenuView;
import com.coui.appcompat.bottomfloatingnavigationbar.MenuXmlParserUtils;
import com.coui.appcompat.darkmode.COUIDarkModeUtil;
import com.coui.appcompat.grid.COUIResponsiveUtils;
import com.coui.appcompat.uiutil.UIUtil;
import com.oplus.aiunit.vision.ug7;
import com.oplus.aiunit.vision.vg7;
import com.oplus.aiunit.vision.wg7;
import com.oplus.aiunit.vision.zf0;
import com.oplus.view.material.OplusMaterialMetaBallParams;
import com.support.bottomnavigation.R;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
public class COUIBottomFloatingAccessoryNavigationBar extends ViewGroup implements AccessorySideExpansionController.Host {
    private static final int MELT_BALL_ACCESSORY_VIEW_INDEX = 2;
    private static final float MELT_BALL_DRAW_ORDER_ELEVATION = 10000.0f;
    private static final float MELT_BALL_EXTENSION_FACTOR_X = 1.5f;
    private static final float MELT_BALL_EXTENSION_FACTOR_Y = 2.0f;
    private static final int MELT_BALL_MENU_VIEW_INDEX = 1;
    private static final int MELT_BALL_MODE = 0;
    private static final int SHADOW_CLIP_EXPAND_SIZE_PX = 120;
    private final int mAccessorySize;
    private final COUIBottomFloatingAccessoryView mEndAccessoryView;
    private final AccessorySideExpansionController mEndExpansionController;
    private int mLastOrientation;
    private int mLastScreenWidthDp;
    private ArraySet<View> mMeltBallChainViews;
    private final int mMeltBallParamExtension;
    private final MenuBuilder mMenuBuilder;
    private MenuInflater mMenuInflater;
    private final COUIBottomFloatingNavigationBarMenuView mMenuView;
    private final int mMinHeightWithoutPaddingVertical;
    private final ViewOutlineProvider mNoDropShadowOutline;
    private final int[] mOnLayoutChildLefts;
    private final int mPaddingHorizontal;
    private final int mSideViewSpacing;
    private boolean mSkipNextLayoutAnimation;
    private final COUIBottomFloatingAccessoryView mStartAccessoryView;
    private final AccessorySideExpansionController mStartExpansionController;

    public interface OnAccessoryClickListener {
        void onAccessoryClick(View view);
    }

    public interface OnItemSelectedListener {
        void onNavigationItemSelected(View view, COUIBottomFloatingNavigationBarItem cOUIBottomFloatingNavigationBarItem, int i);
    }

    public COUIBottomFloatingAccessoryNavigationBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mNoDropShadowOutline = new ViewOutlineProvider() { // from class: com.coui.appcompat.bottomfloatingaccessorynavigationbar.COUIBottomFloatingAccessoryNavigationBar.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setEmpty();
            }
        };
        this.mLastOrientation = 0;
        this.mOnLayoutChildLefts = new int[3];
        this.mMenuBuilder = new MenuBuilder(context);
        this.mMinHeightWithoutPaddingVertical = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_min_height_without_padding_vertical);
        this.mPaddingHorizontal = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_padding_horizontal);
        this.mSideViewSpacing = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_Minimum_spacing_with_slide_view);
        this.mAccessorySize = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_height);
        this.mMeltBallParamExtension = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_accessory_navigation_bar_melt_ball_param_extension);
        COUIBottomFloatingNavigationBarMenuView cOUIBottomFloatingNavigationBarMenuView = new COUIBottomFloatingNavigationBarMenuView(context);
        this.mMenuView = cOUIBottomFloatingNavigationBarMenuView;
        COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView = new COUIBottomFloatingAccessoryView(context);
        this.mStartAccessoryView = cOUIBottomFloatingAccessoryView;
        COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView2 = new COUIBottomFloatingAccessoryView(context);
        this.mEndAccessoryView = cOUIBottomFloatingAccessoryView2;
        h hVar = new h();
        hVar.f50a = this;
        VarHandle.storeStoreFence();
        cOUIBottomFloatingAccessoryView.setEffectCapabilityHost(hVar);
        i iVar = new i();
        iVar.f51a = this;
        VarHandle.storeStoreFence();
        cOUIBottomFloatingAccessoryView2.setEffectCapabilityHost(iVar);
        cOUIBottomFloatingAccessoryView.setVisibility(8);
        cOUIBottomFloatingAccessoryView2.setVisibility(8);
        this.mStartExpansionController = new AccessorySideExpansionController(true, this, createAccessoryAlphaDelegate(), cOUIBottomFloatingAccessoryView);
        this.mEndExpansionController = new AccessorySideExpansionController(false, this, createAccessoryAlphaDelegate(), cOUIBottomFloatingAccessoryView2);
        vg7 vg7Var = new vg7();
        vg7Var.f1854a = this;
        VarHandle.storeStoreFence();
        cOUIBottomFloatingNavigationBarMenuView.setOnDrawingBoundsChangedListener(vg7Var);
        addView(cOUIBottomFloatingAccessoryView);
        addView(cOUIBottomFloatingAccessoryView2);
        addView(cOUIBottomFloatingNavigationBarMenuView);
        setClipChildren(false);
        setClipToPadding(false);
        UIUtil.setViewClipExpandSize(this, 0, 120, 0, 0);
        COUIDarkModeUtil.setForceDarkAllow(this, false);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.COUIBottomFloatingAccessoryNavigationBar, i, i2);
        int i3 = R.styleable.COUIBottomFloatingAccessoryNavigationBar_couiNavigationBarMenu;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            inflateMenu(typedArrayObtainStyledAttributes.getResourceId(i3, 0));
        }
        int i4 = R.styleable.COUIBottomFloatingAccessoryNavigationBar_couiStartAccessoryIcon;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            setStartAccessoryIcon(typedArrayObtainStyledAttributes.getResourceId(i4, 0));
        }
        int i5 = R.styleable.COUIBottomFloatingAccessoryNavigationBar_couiEndAccessoryIcon;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            setEndAccessoryIcon(typedArrayObtainStyledAttributes.getResourceId(i5, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        Configuration configuration = getResources().getConfiguration();
        this.mLastOrientation = configuration.orientation;
        this.mLastScreenWidthDp = configuration.screenWidthDp;
    }

    private void applyExpansionViewMeltBallState(boolean z) {
        boolean z2 = this.mStartExpansionController.isActive() || this.mEndExpansionController.isActive();
        if (lambda$new$1() && (z2 || z)) {
            setMeltBallDrawOrder();
            setMeltBallParams();
        } else {
            clearMeltBallParams();
            resetMeltBallDrawOrder();
        }
    }

    private void changePadding(int i) {
        int i2 = COUIResponsiveUtils.isSmallScreen(getContext(), i) ? this.mPaddingHorizontal : 0;
        if (i2 == getPaddingLeft() && i2 == getPaddingRight()) {
            return;
        }
        setPadding(i2, getPaddingTop(), i2, getPaddingBottom());
    }

    private void clearMeltBallParams() {
        COUIMaterialEffect.setMetaBallParams(this.mStartAccessoryView, null);
        COUIMaterialEffect.setMetaBallParams(this.mEndAccessoryView, null);
        COUIMaterialEffect.setMetaBallParams(this.mMenuView, null);
    }

    private int computeSideReserveForConfiguredIcons(boolean z, boolean z2) {
        int i = z ? this.mAccessorySize + this.mSideViewSpacing : 0;
        return z2 ? this.mAccessorySize + this.mSideViewSpacing + i : i;
    }

    private AccessorySideExpansionController.AccessoryAlphaDelegate createAccessoryAlphaDelegate() {
        return new AccessorySideExpansionController.AccessoryAlphaDelegate() { // from class: com.coui.appcompat.bottomfloatingaccessorynavigationbar.COUIBottomFloatingAccessoryNavigationBar.2
            @Override // com.coui.appcompat.bottomfloatingaccessorynavigationbar.AccessorySideExpansionController.AccessoryAlphaDelegate
            public float getAccessoryViewAlpha(View view) {
                return COUIBottomFloatingAccessoryNavigationBar.this.getAccessoryViewAlpha(view);
            }

            @Override // com.coui.appcompat.bottomfloatingaccessorynavigationbar.AccessorySideExpansionController.AccessoryAlphaDelegate
            public void setAccessoryViewAlpha(View view, float f) {
                COUIBottomFloatingAccessoryNavigationBar.this.setAccessoryViewAlpha(view, f);
            }
        };
    }

    private MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new MenuInflater(getContext());
        }
        return this.mMenuInflater;
    }

    private boolean isAccessoryIconConfigured(COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView) {
        return cOUIBottomFloatingAccessoryView.getIconDrawable() != null;
    }

    private boolean isAccessoryVisible(COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView) {
        if (!isAccessoryIconConfigured(cOUIBottomFloatingAccessoryView)) {
            return false;
        }
        if (cOUIBottomFloatingAccessoryView == this.mStartAccessoryView) {
            return this.mStartExpansionController.isActive();
        }
        if (cOUIBottomFloatingAccessoryView == this.mEndAccessoryView) {
            return this.mEndExpansionController.isActive();
        }
        return false;
    }

    private void layoutAccessoryIfVisible(COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        if (isAccessoryVisible(cOUIBottomFloatingAccessoryView)) {
            int i5 = this.mAccessorySize;
            int iA = zf0.a(i4 - i2, i5, 2, i2);
            if (!z ? !z2 : z2) {
                i = i3 - i5;
            }
            layoutAccessoryView(cOUIBottomFloatingAccessoryView, i, iA, i5 + iA);
        }
    }

    private void layoutAccessoryView(COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView, int i, int i2, int i3) {
        if (isAccessoryVisible(cOUIBottomFloatingAccessoryView)) {
            cOUIBottomFloatingAccessoryView.layout(i, i2, this.mAccessorySize + i, i3);
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0048 A[PHI: r5 r8
      0x0048: PHI (r5v4 int) = (r5v2 int), (r5v9 int) binds: [B:20:0x0042, B:14:0x002a] A[DONT_GENERATE, DONT_INLINE]
      0x0048: PHI (r8v7 int) = (r8v2 int), (r8v10 int) binds: [B:20:0x0042, B:14:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    private void layoutPositionsMediumScreen(int i, int i2, int i3, boolean z, boolean z2, boolean z3) {
        int i4;
        int i5;
        int i6 = z2 ? this.mAccessorySize + this.mSideViewSpacing + i3 : i3;
        if (z3) {
            i6 += this.mSideViewSpacing + this.mAccessorySize;
        }
        int iA = zf0.a(i2, i6, 2, i);
        if (z) {
            int i7 = iA + i6;
            if (z2) {
                int i8 = i7 - this.mAccessorySize;
                i4 = i8;
                i7 = i8 - this.mSideViewSpacing;
            } else {
                i4 = 0;
            }
            iA = i7 - i3;
            if (z3) {
                i5 = (iA - this.mSideViewSpacing) - this.mAccessorySize;
            } else {
                i5 = 0;
            }
        } else {
            if (z2) {
                i4 = iA;
                iA = this.mAccessorySize + this.mSideViewSpacing + iA;
            } else {
                i4 = 0;
            }
            int i9 = i3 + iA;
            if (z3) {
                i5 = i9 + this.mSideViewSpacing;
            } else {
                i5 = 0;
            }
        }
        int[] iArr = this.mOnLayoutChildLefts;
        iArr[0] = iA;
        iArr[1] = i4;
        iArr[2] = i5;
    }

    private void layoutPositionsSmallScreen(int i, int i2, int i3, boolean z, boolean z2, boolean z3) {
        int i4;
        int i5;
        int i6 = this.mAccessorySize;
        if (z) {
            i5 = i2 - i6;
            i4 = i;
        } else {
            i4 = i2 - i6;
            i5 = i;
        }
        if (!z2) {
            i5 = 0;
        }
        if (!z3) {
            i4 = 0;
        }
        boolean z4 = z ? z3 : z2;
        if (!z) {
            z2 = z3;
        }
        if (z4) {
            i = i + this.mAccessorySize + this.mSideViewSpacing;
        }
        if (z2) {
            i2 = (i2 - this.mAccessorySize) - this.mSideViewSpacing;
        }
        int iMax = ((Math.max(0, i2 - i) - i3) / 2) + i;
        int[] iArr = this.mOnLayoutChildLefts;
        iArr[0] = iMax;
        iArr[1] = i5;
        iArr[2] = i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMenuDrawingBoundsChangedForAlpha() {
        this.mStartExpansionController.onGeometryChangedForAlpha();
        this.mEndExpansionController.onGeometryChangedForAlpha();
    }

    private Drawable prepareAccessoryDrawable(Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        return constantState != null ? constantState.newDrawable().mutate() : drawable.mutate();
    }

    private void resetMeltBallDrawOrder() {
        setElevation(0.0f);
        setOutlineProvider(ViewOutlineProvider.BACKGROUND);
    }

    private void setMeltBallDrawOrder() {
        setElevation(10000.0f);
        setOutlineProvider(this.mNoDropShadowOutline);
    }

    private void setMeltBallParams() {
        OplusMaterialMetaBallParams oplusMaterialMetaBallParams = new OplusMaterialMetaBallParams(2, 0, this.mMeltBallParamExtension, 1.5f, 2.0f);
        OplusMaterialMetaBallParams oplusMaterialMetaBallParams2 = new OplusMaterialMetaBallParams(1, 0, this.mMeltBallParamExtension, 1.5f, 2.0f);
        COUIMaterialEffect.setMetaBallParams(this.mStartAccessoryView, oplusMaterialMetaBallParams);
        COUIMaterialEffect.setMetaBallParams(this.mEndAccessoryView, oplusMaterialMetaBallParams);
        COUIMaterialEffect.setMetaBallParams(this.mMenuView, oplusMaterialMetaBallParams2);
        if (this.mMeltBallChainViews == null) {
            ArraySet<View> arraySet = new ArraySet<>(3);
            this.mMeltBallChainViews = arraySet;
            arraySet.add(this.mStartAccessoryView);
            this.mMeltBallChainViews.add(this.mEndAccessoryView);
            this.mMeltBallChainViews.add(this.mMenuView);
        }
        COUIMaterialEffect.setMetaBallChain(this.mMeltBallChainViews);
    }

    public void addItems(List<COUIBottomFloatingNavigationBarItem> list) {
        this.mMenuView.addItems(list);
    }

    @Override // com.coui.appcompat.bottomfloatingaccessorynavigationbar.AccessorySideExpansionController.Host
    public void clearSideMeltBallParams(boolean z) {
        if (z) {
            COUIMaterialEffect.setMetaBallParams(this.mStartAccessoryView, null);
        } else {
            COUIMaterialEffect.setMetaBallParams(this.mEndAccessoryView, null);
        }
    }

    @Override // com.coui.appcompat.bottomfloatingaccessorynavigationbar.AccessorySideExpansionController.Host
    public int getAccessorySize() {
        return this.mAccessorySize;
    }

    public float getAccessoryViewAlpha(View view) {
        if (view != null) {
            return view.getAlpha();
        }
        return 1.0f;
    }

    public COUIBottomFloatingAccessoryView getEndAccessoryView() {
        return this.mEndAccessoryView;
    }

    @Override // com.coui.appcompat.bottomfloatingaccessorynavigationbar.AccessorySideExpansionController.Host
    public RectF getMenuDrawingBounds() {
        return this.mMenuView.getDrawingBounds();
    }

    public COUIBottomFloatingNavigationBarMenuView getMenuView() {
        return this.mMenuView;
    }

    public int getSelectedItemId() {
        return this.mMenuView.getSelectedItemId();
    }

    public COUIBottomFloatingAccessoryView getStartAccessoryView() {
        return this.mStartAccessoryView;
    }

    public void hideEndAccessoryView(boolean z) {
        if (this.mEndExpansionController.isActive()) {
            if (!z) {
                this.mSkipNextLayoutAnimation = true;
            }
            this.mEndExpansionController.setActive(false);
            applyExpansionViewMeltBallState(z);
            requestLayout();
            this.mEndExpansionController.applyAlphaScaleState(false, z);
        }
    }

    public void hideStartAccessoryView(boolean z) {
        if (this.mStartExpansionController.isActive()) {
            if (!z) {
                this.mSkipNextLayoutAnimation = true;
            }
            this.mStartExpansionController.setActive(false);
            applyExpansionViewMeltBallState(z);
            requestLayout();
            this.mStartExpansionController.applyAlphaScaleState(false, z);
        }
    }

    public final void inflateMenu(int i) {
        if (i == 0) {
            return;
        }
        Map<Integer, MenuXmlParserUtils.MenuItemAttrs> menuItemAttrs = MenuXmlParserUtils.parseMenuItemAttrs(getContext(), i);
        this.mMenuBuilder.clear();
        getMenuInflater().inflate(i, this.mMenuBuilder);
        if (this.mMenuBuilder.getNonActionItems().isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        COUIBottomFloatingNavigationBarItem.Builder builder = new COUIBottomFloatingNavigationBarItem.Builder();
        for (MenuItemImpl menuItemImpl : this.mMenuBuilder.getNonActionItems()) {
            int itemId = menuItemImpl.getItemId();
            MenuXmlParserUtils.MenuItemAttrs menuItemAttrs2 = menuItemAttrs.get(Integer.valueOf(itemId));
            ((COUIBottomFloatingNavigationBarItem.Builder) ((COUIBottomFloatingNavigationBarItem.Builder) ((COUIBottomFloatingNavigationBarItem.Builder) builder.reset().setId(itemId)).setTitle(menuItemImpl.getTitle() == null ? "" : menuItemImpl.getTitle().toString())).setIcon(menuItemImpl.getIcon())).setDescription(menuItemImpl.getContentDescription());
            if (menuItemAttrs2 != null) {
                ((COUIBottomFloatingNavigationBarItem.Builder) ((COUIBottomFloatingNavigationBarItem.Builder) builder.setIconResId(menuItemAttrs2.iconResId)).setLargeIcon(menuItemAttrs2.isLargeIcon).setRedDotType(menuItemAttrs2.redDotType)).setRedDotNumber(menuItemAttrs2.redDotNumber);
            }
            arrayList.add(builder.build());
        }
        addItems(arrayList);
    }

    @Override // com.coui.appcompat.bottomfloatingaccessorynavigationbar.AccessorySideExpansionController.Host
    /* JADX INFO: renamed from: isSupportMeltBall, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public boolean lambda$new$1() {
        return this.mMenuView.isSupportMeltBall();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z = this.mLastOrientation != configuration.orientation;
        boolean z2 = this.mLastScreenWidthDp != configuration.screenWidthDp;
        if (z || z2) {
            this.mSkipNextLayoutAnimation = true;
            this.mStartExpansionController.cancelTranslationAnimIfRunning();
            this.mEndExpansionController.cancelTranslationAnimIfRunning();
        }
        this.mLastOrientation = configuration.orientation;
        this.mLastScreenWidthDp = configuration.screenWidthDp;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int iA;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i9 = i3 - i;
        int paddingRight = i9 - getPaddingRight();
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        int i10 = paddingRight - paddingLeft;
        int i11 = paddingBottom - paddingTop;
        boolean z2 = getLayoutDirection() == 1;
        boolean zIsSmallScreen = COUIResponsiveUtils.isSmallScreen(getContext(), i9);
        if (this.mMenuView.getVisibility() == 8) {
            boolean z3 = z2;
            layoutAccessoryIfVisible(this.mStartAccessoryView, paddingLeft, paddingTop, paddingRight, paddingBottom, z3, true);
            layoutAccessoryIfVisible(this.mEndAccessoryView, paddingLeft, paddingTop, paddingRight, paddingBottom, z3, false);
            return;
        }
        int measuredWidth = this.mMenuView.getMeasuredWidth();
        int measuredHeight = this.mMenuView.getMeasuredHeight();
        int iA2 = zf0.a(i11, measuredHeight, 2, paddingTop);
        int i12 = this.mAccessorySize;
        int iA3 = zf0.a(i11, i12, 2, paddingTop);
        int i13 = iA3 + i12;
        boolean zIsAccessoryIconConfigured = isAccessoryIconConfigured(this.mStartAccessoryView);
        boolean zIsAccessoryIconConfigured2 = isAccessoryIconConfigured(this.mEndAccessoryView);
        if (zIsAccessoryIconConfigured || zIsAccessoryIconConfigured2) {
            if (zIsSmallScreen) {
                layoutPositionsSmallScreen(paddingLeft, paddingRight, measuredWidth, z2, zIsAccessoryIconConfigured, zIsAccessoryIconConfigured2);
                int[] iArr = this.mOnLayoutChildLefts;
                i5 = iArr[0];
                i6 = iArr[1];
                i7 = iArr[2];
            } else {
                layoutPositionsMediumScreen(paddingLeft, i10, measuredWidth, z2, zIsAccessoryIconConfigured, zIsAccessoryIconConfigured2);
                int[] iArr2 = this.mOnLayoutChildLefts;
                i5 = iArr2[0];
                i6 = iArr2[1];
                i7 = iArr2[2];
            }
            int i14 = i5;
            i8 = i7;
            iA = i14;
        } else {
            iA = zf0.a(i10, measuredWidth, 2, paddingLeft);
            i8 = 0;
            i6 = 0;
        }
        int i15 = iA + measuredWidth;
        int i16 = measuredHeight + iA2;
        this.mMenuView.layout(iA, iA2, i15, i16);
        int i17 = iA;
        this.mMenuView.updateRealBounds(i17, iA2, i15, i16, false);
        boolean z4 = lambda$new$1() && !this.mSkipNextLayoutAnimation;
        if (zIsAccessoryIconConfigured) {
            this.mStartExpansionController.setVisibleLayoutLeft(i6);
            if (lambda$new$1()) {
                this.mStartExpansionController.computeInactiveFakeBounds(i17, measuredWidth, iA3, i13, z2);
            }
            this.mStartExpansionController.layoutChildAndApplyTranslation(iA3, i13, z4);
        }
        if (zIsAccessoryIconConfigured2) {
            this.mEndExpansionController.setVisibleLayoutLeft(i8);
            if (lambda$new$1()) {
                this.mEndExpansionController.computeInactiveFakeBounds(i17, measuredWidth, iA3, i13, z2);
            }
            this.mEndExpansionController.layoutChildAndApplyTranslation(iA3, i13, z4);
        }
        this.mSkipNextLayoutAnimation = false;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int iMax;
        int iCombineMeasuredStates;
        int size = View.MeasureSpec.getSize(i);
        changePadding(size);
        boolean zIsAccessoryIconConfigured = isAccessoryIconConfigured(this.mStartAccessoryView);
        boolean zIsAccessoryIconConfigured2 = isAccessoryIconConfigured(this.mEndAccessoryView);
        boolean zIsAccessoryVisible = isAccessoryVisible(this.mStartAccessoryView);
        boolean zIsAccessoryVisible2 = isAccessoryVisible(this.mEndAccessoryView);
        int iComputeSideReserveForConfiguredIcons = computeSideReserveForConfiguredIcons(zIsAccessoryIconConfigured, zIsAccessoryIconConfigured2);
        int iMax2 = 0;
        if (this.mMenuView.getVisibility() != 8) {
            this.mMenuView.measure(iComputeSideReserveForConfiguredIcons > 0 ? View.MeasureSpec.makeMeasureSpec(Math.max(0, ((size - getPaddingLeft()) - getPaddingRight()) - iComputeSideReserveForConfiguredIcons), Integer.MIN_VALUE) : i, i2);
            iMax = this.mMenuView.getMeasuredWidth();
            iMax2 = this.mMenuView.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.mMenuView.getMeasuredState());
        } else {
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (zIsAccessoryVisible) {
            measureChild(this.mStartAccessoryView, i, i2);
            iMax2 = Math.max(iMax2, this.mStartAccessoryView.getMeasuredHeight());
        }
        if (zIsAccessoryVisible2) {
            measureChild(this.mEndAccessoryView, i, i2);
            iMax2 = Math.max(iMax2, this.mEndAccessoryView.getMeasuredHeight());
        }
        if (iComputeSideReserveForConfiguredIcons > 0) {
            iMax = Math.max(iMax, this.mMenuView.getMeasuredWidth() + iComputeSideReserveForConfiguredIcons);
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax, getSuggestedMinimumWidth()), i, iCombineMeasuredStates), View.resolveSizeAndState(Math.max(Math.max(getPaddingBottom() + getPaddingTop() + iMax2, getSuggestedMinimumHeight()), getPaddingBottom() + getPaddingTop() + this.mMinHeightWithoutPaddingVertical), i2, iCombineMeasuredStates << 16));
    }

    public void refresh() {
        this.mMenuView.refreshThemeAppearance();
        invalidate();
    }

    public void setAccessoryViewAlpha(View view, float f) {
        if (view != null) {
            view.setAlpha(f);
        }
    }

    public void setDispatchItemClickWhenAlreadySelected(boolean z) {
        this.mMenuView.setDispatchItemClickWhenAlreadySelected(z);
    }

    public void setEndAccessoryBackgroundColor(int i) {
        this.mEndAccessoryView.setAccessoryBackgroundColor(i);
    }

    public void setEndAccessoryDrawable(Drawable drawable, boolean z) {
        if (drawable == null) {
            return;
        }
        this.mEndAccessoryView.setIconDrawable(prepareAccessoryDrawable(drawable), z);
    }

    public void setEndAccessoryIcon(int i, boolean z) {
        if (i == 0) {
            return;
        }
        setEndAccessoryDrawable(AppCompatResources.getDrawable(getContext(), i), z);
    }

    public void setItemsTitleTextColor(ColorStateList colorStateList) {
        this.mMenuView.setItemsTitleTextColor(colorStateList);
    }

    public void setOnEndAccessoryClickListener(OnAccessoryClickListener onAccessoryClickListener) {
        ug7 ug7Var;
        COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView = this.mEndAccessoryView;
        if (onAccessoryClickListener == null) {
            ug7Var = null;
        } else {
            ug7 ug7Var2 = new ug7();
            ug7Var2.f1790a = onAccessoryClickListener;
            VarHandle.storeStoreFence();
            ug7Var = ug7Var2;
        }
        cOUIBottomFloatingAccessoryView.setOnClickListener(ug7Var);
    }

    public void setOnItemSelectedListener(final OnItemSelectedListener onItemSelectedListener) {
        this.mMenuView.setOnItemClickListener(new BaseBottomFloatingMenuView.OnItemClickListener() { // from class: com.coui.appcompat.bottomfloatingaccessorynavigationbar.COUIBottomFloatingAccessoryNavigationBar.3
            @Override // com.coui.appcompat.bottomfloatingnavigationbar.BaseBottomFloatingMenuView.OnItemClickListener
            public void onItemClick(View view, BaseBottomFloatingItem baseBottomFloatingItem, int i) {
                OnItemSelectedListener onItemSelectedListener2 = onItemSelectedListener;
                if (onItemSelectedListener2 == null || !(baseBottomFloatingItem instanceof COUIBottomFloatingNavigationBarItem)) {
                    return;
                }
                onItemSelectedListener2.onNavigationItemSelected(view, (COUIBottomFloatingNavigationBarItem) baseBottomFloatingItem, i);
            }
        });
    }

    public void setOnStartAccessoryClickListener(OnAccessoryClickListener onAccessoryClickListener) {
        wg7 wg7Var;
        COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView = this.mStartAccessoryView;
        if (onAccessoryClickListener == null) {
            wg7Var = null;
        } else {
            wg7 wg7Var2 = new wg7();
            wg7Var2.f1930a = onAccessoryClickListener;
            VarHandle.storeStoreFence();
            wg7Var = wg7Var2;
        }
        cOUIBottomFloatingAccessoryView.setOnClickListener(wg7Var);
    }

    public void setRedDot(int i, int i2, int i3) {
        this.mMenuView.setRedDot(i, i2, i3);
    }

    public void setSelectedItemId(int i) {
        this.mMenuView.setSelectedItem(i);
    }

    public void setStartAccessoryBackgroundColor(int i) {
        this.mStartAccessoryView.setAccessoryBackgroundColor(i);
    }

    public void setStartAccessoryDrawable(Drawable drawable, boolean z) {
        if (drawable == null) {
            return;
        }
        this.mStartAccessoryView.setIconDrawable(prepareAccessoryDrawable(drawable), z);
    }

    public void setStartAccessoryIcon(int i, boolean z) {
        if (i == 0) {
            return;
        }
        setStartAccessoryDrawable(AppCompatResources.getDrawable(getContext(), i), z);
    }

    public void showEndAccessoryView(boolean z) {
        if (isAccessoryIconConfigured(this.mEndAccessoryView) && !this.mEndExpansionController.isActive()) {
            if (!z) {
                this.mSkipNextLayoutAnimation = true;
            }
            this.mEndExpansionController.setActive(true);
            applyExpansionViewMeltBallState(z);
            requestLayout();
            this.mEndExpansionController.applyAlphaScaleState(true, z);
        }
    }

    public void showStartAccessoryView(boolean z) {
        if (isAccessoryIconConfigured(this.mStartAccessoryView) && !this.mStartExpansionController.isActive()) {
            if (!z) {
                this.mSkipNextLayoutAnimation = true;
            }
            this.mStartExpansionController.setActive(true);
            applyExpansionViewMeltBallState(z);
            requestLayout();
            this.mStartExpansionController.applyAlphaScaleState(true, z);
        }
    }

    public void setEndAccessoryDrawable(Drawable drawable) {
        setEndAccessoryDrawable(drawable, false);
    }

    public void setStartAccessoryDrawable(Drawable drawable) {
        setStartAccessoryDrawable(drawable, false);
    }

    public void setEndAccessoryIcon(int i) {
        setEndAccessoryIcon(i, false);
    }

    public void setStartAccessoryIcon(int i) {
        setStartAccessoryIcon(i, false);
    }

    public COUIBottomFloatingAccessoryNavigationBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.couiBottomFloatingAccessoryNavigationBarStyle);
    }

    public COUIBottomFloatingAccessoryNavigationBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public COUIBottomFloatingAccessoryNavigationBar(Context context) {
        this(context, null);
    }
}
