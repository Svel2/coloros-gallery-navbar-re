package com.coui.appcompat.bottomfloatingnavigationbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.COUIMaterialEffect;
import com.coui.appcompat.COUIMaterialStateManager;
import com.coui.appcompat.IMeltBallView;
import com.coui.appcompat.animation.IExpansionViewTouchMotionAnimHost;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.coui.appcompat.bottomfloatingtoolbar.COUIBottomFloatingToolbarItemView;
import com.coui.appcompat.darkmode.COUIDarkModeUtil;
import com.coui.appcompat.grid.COUIResponsiveUtils;
import com.coui.appcompat.poplist.PopupMenuConfigRule;
import com.coui.appcompat.uiutil.AnimLevel;
import com.coui.appcompat.uiutil.UIUtil;
import com.oplus.aiunit.vision.gh7;
import com.oplus.aiunit.vision.hh7;
import com.oplus.aiunit.vision.ih7;
import com.oplus.aiunit.vision.jh7;
import com.oplus.aiunit.vision.kh7;
import com.oplus.aiunit.vision.lh7;
import com.oplus.aiunit.vision.mh7;
import com.oplus.aiunit.vision.zf0;
import com.oplus.view.material.OplusMaterialMetaBallParams;
import com.support.bottomnavigation.R;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes6.dex */
public class COUIBottomFloatingNavigationBar extends ViewGroup implements PopupMenuConfigRule {
    private static final float DEFAULT_SPRING_ANIMATION_FACTOR = 10000.0f;
    private static final float EXPANSION_VIEW_ALPHA_SPRING_BOUNCE = 0.0f;
    private static final float EXPANSION_VIEW_DEFAULT_MIN_VISIBLE_CHANGE = 0.001f;
    private static final float EXPANSION_VIEW_MIN_SCALE = 0.5f;
    private static final float HIGH_END_EXPANSION_VIEW_ALPHA_SPRING_RESPONSE = 0.35f;
    private static final float HIGH_END_EXPANSION_VIEW_SCALE_SPRING_BOUNCE = 0.35f;
    private static final float HIGH_END_EXPANSION_VIEW_SCALE_SPRING_RESPONSE = 0.55f;
    private static final float HIGH_END_EXPANSION_VIEW_TRANSLATION_X_SPRING_BOUNCE = 0.35f;
    private static final float HIGH_END_EXPANSION_VIEW_TRANSLATION_X_SPRING_RESPONSE = 0.55f;
    private static final float LOW_END_EXPANSION_VIEW_ALPHA_SPRING_RESPONSE = 0.2f;
    private static final float LOW_END_EXPANSION_VIEW_SCALE_SPRING_BOUNCE = 0.3f;
    private static final float LOW_END_EXPANSION_VIEW_SCALE_SPRING_RESPONSE = 0.5f;
    private static final float LOW_END_EXPANSION_VIEW_TRANSLATION_X_SPRING_BOUNCE = 0.3f;
    private static final float LOW_END_EXPANSION_VIEW_TRANSLATION_X_SPRING_RESPONSE = 0.5f;
    private static final float MELT_BALL_DRAW_ORDER_ELEVATION = 10000.0f;
    private static final int MELT_BALL_EXPANSION_VIEW_INDEX = 2;
    private static final float MELT_BALL_EXTENSION_FACTOR_X = 1.5f;
    private static final float MELT_BALL_EXTENSION_FACTOR_Y = 2.0f;
    private static final int MELT_BALL_MENU_VIEW_INDEX = 1;
    private static final int MELT_BALL_MODE = 0;
    private static final int MENU_VIEW_CENTER_ITEM_COUNT = 2;
    private static final int SHADOW_CLIP_EXPAND_SIZE_PX = 120;
    private Rect mDisplayFrame;
    private Rect mDisplayFrameOutsets;
    private View mExpansionView;
    private COUISpringAnimation mExpansionViewAlphaAnim;
    private final View.OnLayoutChangeListener mExpansionViewLayoutChangeListener;
    private COUISpringAnimation mExpansionViewScaleAnim;
    private COUISpringAnimation mExpansionViewTranslationXAnim;
    private float mExpansionViewTranslationXAnimTarget;
    private final Rect mFakeBounds;
    private final int mFakeViewSize;
    private ImageView mFloatingIconView;
    private boolean mIsExpansionViewActive;
    private boolean mIsMenuViewVisible;
    private COUIBottomFloatingToolbarItemView mLargeIconItemView;
    private final int mLargeIconOverflow;
    private final int mLargeIconSize;
    private int mLastFakeBoundsLeft;
    private int mLastOrientation;
    private int mLastScreenWidthDp;
    private View mMainView;
    private ArraySet<View> mMeltBallChainViews;
    private MeltBallHideTransitionState mMeltBallHideTransitionState;
    private final int mMeltBallParamExtension;
    private final MenuBuilder mMenuBuilder;
    private MenuInflater mMenuInflater;
    private final COUIBottomFloatingNavigationBarMenuView mMenuView;
    private boolean mMenuViewCentered;
    private boolean mMenuViewEverRemoveExpansionView;
    private final int mMinHeightWithoutPaddingVertical;
    private final ViewOutlineProvider mNoDropShadowOutline;
    private Runnable mOverlapAlphaGeometryCallback;
    private final int mPaddingHorizontal;
    private boolean mPopupRuleEnable;
    private final int mSideViewSpacing;
    private boolean mSkipNextLayoutAnimation;
    private final int[] mTmpExpansionHorizontalPositions;
    private final int[] mTmpLocationOnScreen;
    private Rect mWindowFrame;

    public enum MeltBallHideTransitionState {
        IDLE,
        WAITING_FOR_LAYOUT,
        WAITING_FOR_ANIMATIONS
    }

    public interface OnItemSelectedListener {
        void onNavigationItemSelected(View view, COUIBottomFloatingNavigationBarItem cOUIBottomFloatingNavigationBarItem, int i);
    }

    public COUIBottomFloatingNavigationBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mNoDropShadowOutline = new ViewOutlineProvider() { // from class: com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBar.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setEmpty();
            }
        };
        this.mFakeBounds = new Rect();
        this.mTmpExpansionHorizontalPositions = new int[2];
        this.mTmpLocationOnScreen = new int[2];
        this.mIsExpansionViewActive = false;
        this.mSkipNextLayoutAnimation = false;
        this.mMenuViewEverRemoveExpansionView = false;
        this.mLastOrientation = 0;
        this.mLastScreenWidthDp = 0;
        this.mLastFakeBoundsLeft = Integer.MIN_VALUE;
        this.mMeltBallHideTransitionState = MeltBallHideTransitionState.IDLE;
        gh7 gh7Var = new gh7();
        gh7Var.a = this;
        VarHandle.storeStoreFence();
        this.mExpansionViewLayoutChangeListener = gh7Var;
        this.mIsMenuViewVisible = true;
        this.mMenuViewCentered = false;
        this.mPopupRuleEnable = true;
        this.mDisplayFrame = null;
        this.mWindowFrame = null;
        this.mDisplayFrameOutsets = null;
        this.mMenuBuilder = new MenuBuilder(context);
        this.mMinHeightWithoutPaddingVertical = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_min_height_without_padding_vertical);
        this.mPaddingHorizontal = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_padding_horizontal);
        this.mSideViewSpacing = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_Minimum_spacing_with_slide_view);
        this.mFakeViewSize = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_height);
        this.mLargeIconSize = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_large_icon_size);
        this.mLargeIconOverflow = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_large_icon_overflow);
        this.mMeltBallParamExtension = getResources().getDimensionPixelSize(R.dimen.coui_bottom_floating_navigation_bar_melt_ball_param_extension);
        COUIBottomFloatingNavigationBarMenuView cOUIBottomFloatingNavigationBarMenuView = new COUIBottomFloatingNavigationBarMenuView(context);
        this.mMenuView = cOUIBottomFloatingNavigationBarMenuView;
        addView(cOUIBottomFloatingNavigationBarMenuView);
        hh7 hh7Var = new hh7();
        hh7Var.a = this;
        VarHandle.storeStoreFence();
        cOUIBottomFloatingNavigationBarMenuView.setOnDrawingBoundsChangedListener(hh7Var);
        ih7 ih7Var = new ih7();
        ih7Var.a = this;
        VarHandle.storeStoreFence();
        cOUIBottomFloatingNavigationBarMenuView.setOnBoundsAnimationsEndAction(ih7Var);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.COUIBottomFloatingNavigationBar, i, i2);
        int i3 = R.styleable.COUIBottomFloatingNavigationBar_couiNavigationBarMenu;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            inflateMenu(typedArrayObtainStyledAttributes.getResourceId(i3, 0));
        }
        this.mMenuViewCentered = typedArrayObtainStyledAttributes.getBoolean(R.styleable.COUIBottomFloatingNavigationBar_couiMenuViewCentered, false);
        typedArrayObtainStyledAttributes.recycle();
        setClipChildren(false);
        setClipToPadding(false);
        UIUtil.setViewClipExpandSize(this, 0, 120, 0, 0);
        COUIDarkModeUtil.setForceDarkAllow(this, false);
        initConfigInfo();
    }

    private void applyExpansionViewAlphaScaleState(boolean z, boolean z2) {
        if (this.mExpansionView == null || this.mMainView == null) {
            return;
        }
        if (z2) {
            if (z) {
                prepareExpansionViewForShowAnim();
            }
            doExpansionViewAlphaScaleAnim(z);
            return;
        }
        clearExpansionViewAlphaPolicy();
        cancelExpansionViewAlphaScaleAnimIfRunning();
        View view = this.mMainView;
        if (z) {
            view.setScaleX(1.0f);
            this.mMainView.setScaleY(1.0f);
            setExpansionViewAlpha(this.mMainView, 1.0f);
        } else {
            view.setScaleX(0.5f);
            this.mMainView.setScaleY(0.5f);
            setExpansionViewAlpha(this.mMainView, 0.0f);
            this.mExpansionView.setVisibility(4);
        }
    }

    private void applyExpansionViewMeltBallState(boolean z) {
        if (shouldDoTranslationAnim() && (this.mIsExpansionViewActive || z)) {
            setMeltBallDrawOrder();
            setMeltBallParams();
        } else {
            clearMeltBallParams();
            resetMeltBallDrawOrder();
            this.mMeltBallHideTransitionState = MeltBallHideTransitionState.IDLE;
        }
    }

    private void applyFakeBoundsOrTranslationAnim(int i, int i2, int i3, int i4) {
        if (shouldDoTranslationAnim() && !this.mSkipNextLayoutAnimation) {
            handleExpansionViewTranslationXAnim(i, i2, i3, i4);
            return;
        }
        cancelExpansionViewSetXAnimIfRunning();
        this.mFakeBounds.set(i, i2, i3, i4);
        updateTranslationXViewPosition();
    }

    private boolean areExpansionShowHideAnimIdle() {
        COUISpringAnimation cOUISpringAnimation = this.mExpansionViewAlphaAnim;
        if (cOUISpringAnimation != null && cOUISpringAnimation.isRunning()) {
            return false;
        }
        COUISpringAnimation cOUISpringAnimation2 = this.mExpansionViewScaleAnim;
        return cOUISpringAnimation2 == null || !cOUISpringAnimation2.isRunning();
    }

    private void assignExpansionViews(View view) {
        detachExpansionViewLayoutListener();
        if (view instanceof IMeltBallView) {
            IMeltBallView iMeltBallView = (IMeltBallView) view;
            this.mExpansionView = iMeltBallView.getExpansionView();
            this.mMainView = iMeltBallView.getMainView();
        } else {
            this.mExpansionView = view;
            this.mMainView = view;
        }
        View view2 = this.mExpansionView;
        if (view2 != null) {
            view2.addOnLayoutChangeListener(this.mExpansionViewLayoutChangeListener);
        }
    }

    private void attachOverlapAlphaPolicy() {
        COUISpringAnimation cOUISpringAnimation = this.mExpansionViewAlphaAnim;
        if (cOUISpringAnimation != null && cOUISpringAnimation.isRunning()) {
            this.mExpansionViewAlphaAnim.cancel();
        }
        lh7 lh7Var = new lh7();
        lh7Var.a = this;
        VarHandle.storeStoreFence();
        this.mOverlapAlphaGeometryCallback = lh7Var;
    }

    private void attachSpringAlphaPolicy(boolean z) {
        this.mOverlapAlphaGeometryCallback = null;
        initExpansionViewAlphaAnim();
        this.mExpansionViewAlphaAnim.setStartValue(getExpansionViewAlpha(this.mMainView) * 10000.0f);
        this.mExpansionViewAlphaAnim.animateToFinalPosition(z ? 10000.0f : 0.0f);
    }

    private void cancelExpansionViewAlphaScaleAnimIfRunning() {
        COUISpringAnimation cOUISpringAnimation = this.mExpansionViewAlphaAnim;
        if (cOUISpringAnimation != null && cOUISpringAnimation.isRunning()) {
            this.mExpansionViewAlphaAnim.cancel();
        }
        COUISpringAnimation cOUISpringAnimation2 = this.mExpansionViewScaleAnim;
        if (cOUISpringAnimation2 == null || !cOUISpringAnimation2.isRunning()) {
            return;
        }
        this.mExpansionViewScaleAnim.cancel();
    }

    private void cancelExpansionViewSetXAnimIfRunning() {
        COUISpringAnimation cOUISpringAnimation = this.mExpansionViewTranslationXAnim;
        if (cOUISpringAnimation == null || !cOUISpringAnimation.isRunning()) {
            return;
        }
        this.mExpansionViewTranslationXAnim.cancel();
    }

    private void changePadding(int i) {
        int i2 = COUIResponsiveUtils.isSmallScreen(getContext(), i) ? this.mPaddingHorizontal : 0;
        if (i2 == getPaddingLeft() && i2 == getPaddingRight()) {
            return;
        }
        setPadding(i2, getPaddingTop(), i2, getPaddingBottom());
    }

    private void clearExpansionViewAlphaPolicy() {
        this.mOverlapAlphaGeometryCallback = null;
    }

    private void clearMeltBallParams() {
        View view = this.mMainView;
        if (view == null || this.mMenuView == null) {
            return;
        }
        COUIMaterialEffect.setMetaBallParams(view, (OplusMaterialMetaBallParams) null);
        COUIMaterialEffect.setMetaBallParams(this.mMenuView, (OplusMaterialMetaBallParams) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x000a A[PHI: r5
      0x000a: PHI (r5v9 int) = (r5v6 int), (r5v10 int) binds: [B:12:0x0015, B:5:0x0007] A[DONT_GENERATE, DONT_INLINE]] */
    private void computeExpansionActiveHorizontalPositions(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3) {
        int iA;
        if (z3) {
            iA = zf0.a(i3, i4, 2, i);
            if (!z2) {
                i = i2 - i5;
            }
        } else if (z) {
            iA = z2 ? i2 - i4 : i;
            if (!z2) {
                i = i2 - i5;
            }
        } else {
            int i6 = this.mSideViewSpacing;
            iA = ((i3 - ((i4 + i6) + i5)) / 2) + i;
            if (z2) {
                iA = i5 + iA + i6;
                i = iA;
            } else {
                i = i4 + iA + i6;
            }
        }
        int[] iArr = this.mTmpExpansionHorizontalPositions;
        iArr[0] = iA;
        iArr[1] = i;
    }

    private float computeExpansionOverlapRatioWithMenu() {
        return BottomFloatingOverlapHelper.computeScaledViewOverlapRatio(this.mMainView, this, this.mMenuView.getDrawingBounds(), this.mTmpLocationOnScreen);
    }

    private BaseBottomFloatingMenuView$OnAnimationSyncListener createFloatingIconAnimationSyncListener() {
        return new BaseBottomFloatingMenuView$OnAnimationSyncListener() { // from class: com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBar.7
            @Override // com.coui.appcompat.bottomfloatingnavigationbar.BaseBottomFloatingMenuView$OnAnimationSyncListener
            public void onAlphaAnimUpdate(float f) {
                if (COUIBottomFloatingNavigationBar.this.isFloatingIconVisible()) {
                    COUIBottomFloatingNavigationBar.this.mFloatingIconView.setAlpha(f);
                }
            }

            @Override // com.coui.appcompat.bottomfloatingnavigationbar.BaseBottomFloatingMenuView$OnAnimationSyncListener
            public void onForegroundBlurRadiusAnimUpdate(float f) {
                if (COUIBottomFloatingNavigationBar.this.isFloatingIconVisible()) {
                    COUIBottomFloatingNavigationBar.this.mFloatingIconView.setRenderEffect(RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP));
                }
            }

            @Override // com.coui.appcompat.bottomfloatingnavigationbar.BaseBottomFloatingMenuView$OnAnimationSyncListener
            public void onScaleAnimUpdate(float f) {
                if (COUIBottomFloatingNavigationBar.this.isFloatingIconVisible()) {
                    COUIBottomFloatingNavigationBar.this.mFloatingIconView.setScaleX(f);
                    COUIBottomFloatingNavigationBar.this.mFloatingIconView.setScaleY(f);
                }
            }

            @Override // com.coui.appcompat.bottomfloatingnavigationbar.BaseBottomFloatingMenuView$OnAnimationSyncListener
            public void onTouchAnimUpdate(float f, float f2, float f3, float f4) {
                if (COUIBottomFloatingNavigationBar.this.isFloatingIconVisible()) {
                    COUIBottomFloatingNavigationBar.this.mFloatingIconView.setTranslationX(f);
                    COUIBottomFloatingNavigationBar.this.mFloatingIconView.setTranslationY(f2);
                    COUIBottomFloatingNavigationBar.this.mFloatingIconView.setScaleX(f3);
                    COUIBottomFloatingNavigationBar.this.mFloatingIconView.setScaleY(f4);
                }
            }
        };
    }

    private void createFloatingIconView(Drawable drawable) {
        ImageView imageView = new ImageView(getContext());
        this.mFloatingIconView = imageView;
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.mFloatingIconView.setImageDrawable(drawable);
        ImageView imageView2 = this.mFloatingIconView;
        kh7 kh7Var = new kh7();
        kh7Var.a = this;
        VarHandle.storeStoreFence();
        imageView2.setOnClickListener(kh7Var);
    }

    private void detachExpansionViewLayoutListener() {
        View view = this.mExpansionView;
        if (view != null) {
            view.removeOnLayoutChangeListener(this.mExpansionViewLayoutChangeListener);
        }
    }

    private void doExpansionViewAlphaScaleAnim(boolean z) {
        if (this.mMainView == null) {
            return;
        }
        initExpansionViewScaleAnim();
        this.mExpansionViewScaleAnim.setStartValue(this.mMainView.getScaleX() * 10000.0f);
        this.mExpansionViewScaleAnim.animateToFinalPosition(z ? 10000.0f : 5000.0f);
        if (willRunExpansionViewTranslationAnim(z)) {
            attachOverlapAlphaPolicy();
        } else {
            attachSpringAlphaPolicy(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishCollapseTranslationIfFullyOverlapped() {
        COUISpringAnimation cOUISpringAnimation;
        if (this.mIsExpansionViewActive || this.mExpansionView == null || (cOUISpringAnimation = this.mExpansionViewTranslationXAnim) == null || !cOUISpringAnimation.isRunning() || computeExpansionOverlapRatioWithMenu() < 1.0f) {
            return;
        }
        float f = this.mExpansionViewTranslationXAnimTarget;
        this.mExpansionViewTranslationXAnim.cancel();
        this.mExpansionView.setX(Math.round(f));
        onExpansionViewGeometryChangedForAlpha();
    }

    private MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new MenuInflater(getContext());
        }
        return this.mMenuInflater;
    }

    private void handleExpansionViewTranslationXAnim(int i, int i2, int i3, int i4) {
        float x;
        if (this.mExpansionView == null) {
            return;
        }
        initExpansionViewTranslationXAnim();
        if (this.mLastFakeBoundsLeft == Integer.MIN_VALUE) {
            x = this.mFakeBounds.left;
            this.mExpansionView.setX(x);
        } else {
            x = this.mExpansionView.getX();
        }
        this.mFakeBounds.set(i, i2, i3, i4);
        this.mLastFakeBoundsLeft = this.mFakeBounds.left;
        this.mExpansionViewTranslationXAnim.setStartValue(x);
        int i5 = this.mFakeBounds.left;
        this.mExpansionViewTranslationXAnimTarget = i5;
        this.mExpansionViewTranslationXAnim.animateToFinalPosition(i5);
    }

    private void initConfigInfo() {
        Configuration configuration = getResources().getConfiguration();
        this.mLastOrientation = configuration.orientation;
        this.mLastScreenWidthDp = configuration.screenWidthDp;
    }

    private void initExpansionViewAlphaAnim() {
        if (this.mExpansionViewAlphaAnim != null) {
            return;
        }
        FloatValueHolder floatValueHolder = new FloatValueHolder(0.0f);
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(0.0f);
        cOUISpringForce.setResponse(isSupportMeltBall() ? 0.35f : 0.2f);
        COUISpringAnimation spring = new COUISpringAnimation(floatValueHolder).setSpring(cOUISpringForce);
        this.mExpansionViewAlphaAnim = spring;
        spring.addUpdateListener(new COUIDynamicAnimation.OnAnimationUpdateListener() { // from class: com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBar.5
            public void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
                float f3 = f / 10000.0f;
                if (COUIBottomFloatingNavigationBar.this.mMainView != null) {
                    COUIBottomFloatingNavigationBar cOUIBottomFloatingNavigationBar = COUIBottomFloatingNavigationBar.this;
                    cOUIBottomFloatingNavigationBar.setExpansionViewAlpha(cOUIBottomFloatingNavigationBar.mMainView, f3);
                }
            }
        });
        COUISpringAnimation cOUISpringAnimation = this.mExpansionViewAlphaAnim;
        mh7 mh7Var = new mh7();
        mh7Var.a = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(mh7Var);
    }

    private void initExpansionViewScaleAnim() {
        if (this.mExpansionViewScaleAnim != null) {
            return;
        }
        FloatValueHolder floatValueHolder = new FloatValueHolder(0.0f);
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(isSupportMeltBall() ? 0.35f : 0.3f);
        cOUISpringForce.setResponse(isSupportMeltBall() ? 0.55f : 0.5f);
        COUISpringAnimation spring = new COUISpringAnimation(floatValueHolder).setSpring(cOUISpringForce);
        this.mExpansionViewScaleAnim = spring;
        spring.addUpdateListener(new COUIDynamicAnimation.OnAnimationUpdateListener() { // from class: com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBar.4
            public void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
                float f3 = f / 10000.0f;
                if (COUIBottomFloatingNavigationBar.this.mMainView != null) {
                    COUIBottomFloatingNavigationBar cOUIBottomFloatingNavigationBar = COUIBottomFloatingNavigationBar.this;
                    cOUIBottomFloatingNavigationBar.setScaleSnapped(cOUIBottomFloatingNavigationBar.mMainView, f3);
                    COUIBottomFloatingNavigationBar.this.onExpansionViewGeometryChangedForAlpha();
                }
            }
        });
        COUISpringAnimation cOUISpringAnimation = this.mExpansionViewScaleAnim;
        jh7 jh7Var = new jh7();
        jh7Var.a = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(jh7Var);
    }

    private void initExpansionViewTranslationXAnim() {
        if (this.mExpansionViewTranslationXAnim != null) {
            return;
        }
        FloatValueHolder floatValueHolder = new FloatValueHolder(0.0f);
        COUISpringForce cOUISpringForce = new COUISpringForce();
        cOUISpringForce.setBounce(isSupportMeltBall() ? 0.35f : 0.3f);
        cOUISpringForce.setResponse(isSupportMeltBall() ? 0.55f : 0.5f);
        COUISpringAnimation spring = new COUISpringAnimation(floatValueHolder).setSpring(cOUISpringForce);
        this.mExpansionViewTranslationXAnim = spring;
        spring.setMinimumVisibleChange(0.001f);
        this.mExpansionViewTranslationXAnim.addUpdateListener(new COUIDynamicAnimation.OnAnimationUpdateListener() { // from class: com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBar.2
            public void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
                if (COUIBottomFloatingNavigationBar.this.mExpansionView != null) {
                    COUIBottomFloatingNavigationBar.this.mExpansionView.setX(Math.round(f));
                    COUIBottomFloatingNavigationBar.this.onExpansionViewGeometryChangedForAlpha();
                    COUIBottomFloatingNavigationBar.this.finishCollapseTranslationIfFullyOverlapped();
                }
            }
        });
        this.mExpansionViewTranslationXAnim.addEndListener(new COUIDynamicAnimation.OnAnimationEndListener() { // from class: com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBar.3
            public void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
                boolean z2 = COUIBottomFloatingNavigationBar.this.mIsExpansionViewActive;
                COUIBottomFloatingNavigationBar cOUIBottomFloatingNavigationBar = COUIBottomFloatingNavigationBar.this;
                if (z2) {
                    cOUIBottomFloatingNavigationBar.setExpansionViewTouchMotionAnimEnabled(true);
                } else {
                    cOUIBottomFloatingNavigationBar.tryFinishMeltBallHideTransition();
                }
            }
        });
    }

    private boolean isExpansionViewTranslationAnimRunning() {
        COUISpringAnimation cOUISpringAnimation = this.mExpansionViewTranslationXAnim;
        return cOUISpringAnimation != null && cOUISpringAnimation.isRunning();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFloatingIconVisible() {
        ImageView imageView = this.mFloatingIconView;
        return imageView != null && imageView.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createFloatingIconView$3(View view) {
        COUIBottomFloatingToolbarItemView cOUIBottomFloatingToolbarItemView = this.mLargeIconItemView;
        if (cOUIBottomFloatingToolbarItemView != null) {
            cOUIBottomFloatingToolbarItemView.performClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initExpansionViewAlphaAnim$2(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        onExpansionShowHideAnimEnded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initExpansionViewScaleAnim$1(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        onExpansionShowHideAnimEnded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if ((i == i5 && i2 == i6 && i3 == i7 && i4 == i8) || this.mFakeBounds.isEmpty()) {
            return;
        }
        COUISpringAnimation cOUISpringAnimation = this.mExpansionViewTranslationXAnim;
        if (cOUISpringAnimation == null || !cOUISpringAnimation.isRunning()) {
            updateTranslationXViewPosition();
        }
    }

    private void layoutMenuViewForExpansionActive(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        int iMax = (z2 || this.mSkipNextLayoutAnimation) ? i3 : Math.max(i3, this.mMenuView.getWidth());
        COUIBottomFloatingNavigationBarMenuView cOUIBottomFloatingNavigationBarMenuView = this.mMenuView;
        if (!z) {
            cOUIBottomFloatingNavigationBarMenuView.layout(i, i2, iMax + i, i4 + i2);
        } else {
            int i5 = i + i3;
            cOUIBottomFloatingNavigationBarMenuView.layout(i5 - iMax, i2, i5, i4 + i2);
        }
    }

    private void layoutWhenExpansionViewActive(int i, int i2, int i3, int i4, boolean z, int i5) {
        int measuredWidth = this.mMenuView.getMeasuredWidth();
        int measuredHeight = this.mMenuView.getMeasuredHeight();
        int i6 = this.mFakeViewSize;
        boolean zIsSmallScreen = COUIResponsiveUtils.isSmallScreen(getContext(), i5);
        boolean zShouldLayoutMenuViewCentered = shouldLayoutMenuViewCentered(i5);
        int iA = zf0.a(i4, measuredHeight, 2, i2);
        int iA2 = zf0.a(i4, i6, 2, i2);
        computeExpansionActiveHorizontalPositions(i, i3, i3 - i, measuredWidth, i6, zIsSmallScreen, z, zShouldLayoutMenuViewCentered);
        int[] iArr = this.mTmpExpansionHorizontalPositions;
        boolean z2 = false;
        int i7 = iArr[0];
        int i8 = iArr[1];
        layoutMenuViewForExpansionActive(i7, iA, measuredWidth, measuredHeight, z, zShouldLayoutMenuViewCentered);
        if (!this.mSkipNextLayoutAnimation && !zShouldLayoutMenuViewCentered) {
            z2 = true;
        }
        this.mMenuView.updateRealBounds(i7, iA, i7 + measuredWidth, iA + measuredHeight, z2);
        applyFakeBoundsOrTranslationAnim(i8, iA2, i8 + i6, iA2 + i6);
    }

    private void layoutWhenExpansionViewInactive(int i, int i2, int i3, int i4, boolean z, int i5) {
        if (this.mMenuView.getVisibility() == 8) {
            return;
        }
        int measuredWidth = this.mMenuView.getMeasuredWidth();
        int measuredHeight = this.mMenuView.getMeasuredHeight();
        boolean zShouldLayoutMenuViewCentered = shouldLayoutMenuViewCentered(i5);
        int iA = zf0.a(i3, measuredWidth, 2, i);
        int iA2 = zf0.a(i4, measuredHeight, 2, i2);
        boolean z2 = (zShouldLayoutMenuViewCentered || !this.mMenuViewEverRemoveExpansionView || this.mSkipNextLayoutAnimation) ? false : true;
        int i6 = measuredHeight + iA2;
        this.mMenuView.layout((z && z2) ? getLeft() : iA, iA2, (z || !z2) ? iA + measuredWidth : getRight(), i6);
        int i7 = iA + measuredWidth;
        this.mMenuView.updateRealBounds(iA, iA2, i7, i6, (this.mSkipNextLayoutAnimation || zShouldLayoutMenuViewCentered) ? false : true);
        if (shouldDoTranslationAnim()) {
            int i8 = this.mFakeViewSize;
            int iA3 = zf0.a(i4, i8, 2, i2);
            if (!z) {
                iA = i7 - i8;
            }
            int i9 = iA + i8;
            int i10 = i8 + iA3;
            if (!this.mFakeBounds.isEmpty() && !this.mSkipNextLayoutAnimation) {
                setExpansionViewTouchMotionAnimEnabled(false);
                handleExpansionViewTranslationXAnim(iA, iA3, i9, i10);
            } else {
                cancelExpansionViewSetXAnimIfRunning();
                this.mFakeBounds.set(iA, iA3, i9, i10);
                updateTranslationXViewPosition();
            }
        }
    }

    private void measureMenuViewWithCenteredExpansionReserve(int i, int i2) {
        if (this.mMenuView.getVisibility() == 8) {
            return;
        }
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int i3 = this.mFakeViewSize + this.mSideViewSpacing;
        this.mMenuView.measure(View.MeasureSpec.makeMeasureSpec(Math.max(0, (size - i3) - i3), Integer.MIN_VALUE), i2);
    }

    private void onExpansionShowHideAnimEnded() {
        View view;
        if (!areExpansionShowHideAnimIdle() || (view = this.mExpansionView) == null || this.mIsExpansionViewActive) {
            return;
        }
        view.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onExpansionViewGeometryChangedForAlpha() {
        Runnable runnable = this.mOverlapAlphaGeometryCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    private void prepareExpansionViewForShowAnim() {
        COUISpringAnimation cOUISpringAnimation = this.mExpansionViewAlphaAnim;
        if (cOUISpringAnimation == null || !cOUISpringAnimation.isRunning()) {
            COUISpringAnimation cOUISpringAnimation2 = this.mExpansionViewScaleAnim;
            if (cOUISpringAnimation2 == null || !cOUISpringAnimation2.isRunning()) {
                this.mMainView.setScaleX(0.5f);
                this.mMainView.setScaleY(0.5f);
                setExpansionViewAlpha(this.mMainView, 0.0f);
            }
        }
    }

    private void prepareMeltBallHideTransition(boolean z) {
        this.mMeltBallHideTransitionState = (z && shouldDoTranslationAnim()) ? MeltBallHideTransitionState.WAITING_FOR_LAYOUT : MeltBallHideTransitionState.IDLE;
    }

    private void removeFloatingIcon() {
        if (this.mFloatingIconView != null) {
            this.mMenuView.setOnAnimationSyncListener((BaseBottomFloatingMenuView$OnAnimationSyncListener) null);
            getOverlay().remove(this.mFloatingIconView);
            this.mFloatingIconView = null;
        }
        this.mLargeIconItemView = null;
    }

    private void resetMeltBallDrawOrder() {
        setElevation(0.0f);
        setOutlineProvider(ViewOutlineProvider.BACKGROUND);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExpansionViewTouchMotionAnimEnabled(boolean z) {
        IExpansionViewTouchMotionAnimHost iExpansionViewTouchMotionAnimHost = this.mExpansionView;
        if (iExpansionViewTouchMotionAnimHost instanceof IExpansionViewTouchMotionAnimHost) {
            iExpansionViewTouchMotionAnimHost.setExpansionViewTouchMotionAnimEnabled(z);
        }
    }

    private void setMeltBallDrawOrder() {
        setElevation(10000.0f);
        setOutlineProvider(this.mNoDropShadowOutline);
    }

    private void setMeltBallParams() {
        if (this.mMainView == null || this.mMenuView == null || !COUIMaterialStateManager.INSTANCE.materialVersionCheck()) {
            return;
        }
        COUIMaterialEffect.setMetaBallParams(this.mMainView, new OplusMaterialMetaBallParams(2, 0, this.mMeltBallParamExtension, MELT_BALL_EXTENSION_FACTOR_X, 2.0f));
        COUIMaterialEffect.setMetaBallParams(this.mMenuView, new OplusMaterialMetaBallParams(1, 0, this.mMeltBallParamExtension, MELT_BALL_EXTENSION_FACTOR_X, 2.0f));
        if (this.mMeltBallChainViews == null) {
            ArraySet<View> arraySet = new ArraySet<>(2);
            this.mMeltBallChainViews = arraySet;
            arraySet.add(this.mMainView);
            this.mMeltBallChainViews.add(this.mMenuView);
        }
        COUIMaterialEffect.setMetaBallChain(this.mMeltBallChainViews);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScaleSnapped(View view, float f) {
        float fRound;
        int width = view.getWidth();
        int height = view.getHeight();
        if (width > 0) {
            float f2 = width;
            fRound = Math.round(f2 * f) / f2;
        } else {
            fRound = f;
        }
        view.setScaleX(fRound);
        if (height > 0) {
            float f3 = height;
            f = Math.round(f * f3) / f3;
        }
        view.setScaleY(f);
    }

    private void setupFloatingIcon() {
        Drawable iconDrawable;
        removeFloatingIcon();
        COUIBottomFloatingToolbarItemView largeIconItemView = this.mMenuView.getLargeIconItemView();
        this.mLargeIconItemView = largeIconItemView;
        if (largeIconItemView == null || (iconDrawable = largeIconItemView.getIconDrawable()) == null) {
            return;
        }
        createFloatingIconView(iconDrawable);
        this.mMenuView.setOnAnimationSyncListener(createFloatingIconAnimationSyncListener());
    }

    private boolean shouldDoTranslationAnim() {
        return isSupportMeltBall() || UIUtil.confirmLevelAnim(AnimLevel.LOW_END);
    }

    private boolean shouldLayoutMenuViewCentered(int i) {
        return this.mMenuViewCentered && this.mMenuView.getVisibleChildCount() == 2 && COUIResponsiveUtils.isSmallScreen(getContext(), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryFinishMeltBallHideTransition() {
        if (this.mMeltBallHideTransitionState != MeltBallHideTransitionState.WAITING_FOR_ANIMATIONS || this.mIsExpansionViewActive || isExpansionViewTranslationAnimRunning() || this.mMenuView.isBoundsAnimationRunning()) {
            return;
        }
        this.mMeltBallHideTransitionState = MeltBallHideTransitionState.IDLE;
        clearMeltBallParams();
        resetMeltBallDrawOrder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateExpansionViewAlphaByOverlap() {
        if (this.mMainView == null) {
            return;
        }
        setExpansionViewAlpha(this.mMainView, BottomFloatingOverlapHelper.alphaFromOverlapRatio(computeExpansionOverlapRatioWithMenu(), 50.0f));
    }

    private void updateFloatingIconPosition() {
        if (this.mFloatingIconView == null || this.mLargeIconItemView == null) {
            return;
        }
        ViewGroupOverlay overlay = getOverlay();
        overlay.remove(this.mFloatingIconView);
        overlay.add(this.mFloatingIconView);
        int left = this.mLargeIconItemView.getLeft();
        int top = this.mLargeIconItemView.getTop();
        int width = (this.mLargeIconItemView.getWidth() / 2) + left;
        int left2 = this.mMenuView.getLeft();
        int top2 = (this.mMenuView.getTop() + top) - this.mLargeIconOverflow;
        int i = this.mLargeIconSize;
        int i2 = (left2 + width) - (i / 2);
        this.mFloatingIconView.layout(i2, top2, i2 + i, i + top2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTranslationXViewPosition() {
        if (this.mExpansionView == null || this.mFakeBounds.isEmpty()) {
            return;
        }
        int width = this.mExpansionView.getWidth();
        int height = this.mExpansionView.getHeight();
        if (width == 0 || height == 0) {
            post(new Runnable() { // from class: com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBar.8
                @Override // java.lang.Runnable
                public void run() {
                    COUIBottomFloatingNavigationBar.this.updateTranslationXViewPosition();
                }
            });
            return;
        }
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int iCenterX = this.mFakeBounds.centerX() + iArr[0];
        int left = this.mExpansionView.getLeft();
        View view = (View) this.mExpansionView.getParent();
        int[] iArr2 = new int[2];
        if (view != null) {
            view.getLocationOnScreen(iArr2);
        }
        this.mExpansionView.setTranslationX(iCenterX - ((width / 2) + (iArr2[0] + left)));
        onExpansionViewGeometryChangedForAlpha();
    }

    private boolean willRunExpansionViewTranslationAnim(boolean z) {
        if (!shouldDoTranslationAnim() || this.mSkipNextLayoutAnimation) {
            return false;
        }
        return z || !this.mFakeBounds.isEmpty();
    }

    @Deprecated
    public void addExpansionView(View view) {
        bindExpansionView(view);
        showExpansionView(true);
    }

    public void addItems(List<COUIBottomFloatingNavigationBarItem> list) {
        this.mMenuView.addItems(list);
        setupFloatingIcon();
    }

    public void bindExpansionView(View view) {
        assignExpansionViews(view);
    }

    public int getBarrierDirection() {
        if (getDisplayFrame().isEmpty()) {
            return -1;
        }
        if (this.mWindowFrame == null) {
            this.mWindowFrame = new Rect();
        }
        getRootView().getGlobalVisibleRect(this.mWindowFrame);
        return this.mWindowFrame.height() <= getContext().getResources().getDimensionPixelSize(com.support.poplist.R.dimen.coui_popup_list_window_min_window_height_to_apply_vertical_barrier) ? -1 : 3;
    }

    public Rect getDisplayFrame() {
        if (this.mDisplayFrame == null) {
            this.mDisplayFrame = new Rect();
        }
        if (this.mMenuView.getVisibility() == 0 && this.mMenuView.getGlobalVisibleRect(this.mDisplayFrame) && !this.mDisplayFrame.isEmpty()) {
            return this.mDisplayFrame;
        }
        getGlobalVisibleRect(this.mDisplayFrame);
        return this.mDisplayFrame;
    }

    public float getExpansionViewAlpha(View view) {
        if (view != null) {
            return view.getAlpha();
        }
        return 1.0f;
    }

    public ImageView getFloatingIconView() {
        return this.mFloatingIconView;
    }

    public COUIBottomFloatingToolbarItemView getLargeIconItemView() {
        return this.mLargeIconItemView;
    }

    public Rect getOutsets() {
        if (this.mDisplayFrameOutsets == null) {
            this.mDisplayFrameOutsets = new Rect(0, getContext().getResources().getDimensionPixelOffset(R.dimen.coui_popup_list_window_gap_to_navigation_view), 0, 0);
        }
        return this.mDisplayFrameOutsets;
    }

    public boolean getPopupMenuRuleEnabled() {
        return this.mPopupRuleEnable;
    }

    public int getSelectedItemId() {
        return this.mMenuView.getSelectedItemId();
    }

    public int getType() {
        return 2;
    }

    public void hideExpansionView(boolean z) {
        if (this.mIsExpansionViewActive) {
            if (!z) {
                this.mSkipNextLayoutAnimation = true;
            }
            this.mIsExpansionViewActive = false;
            prepareMeltBallHideTransition(z);
            applyExpansionViewMeltBallState(z);
            this.mMenuViewEverRemoveExpansionView = true;
            requestLayout();
            applyExpansionViewAlphaScaleState(false, z);
        }
    }

    public void hideMenuView() {
        if (this.mIsMenuViewVisible) {
            this.mIsMenuViewVisible = false;
            this.mMenuView.doMenuViewShowOrHideAnim(false);
        }
    }

    public final void inflateMenu(int i) {
        if (i == 0) {
            return;
        }
        Map menuItemAttrs = MenuXmlParserUtils.parseMenuItemAttrs(getContext(), i);
        this.mMenuBuilder.clear();
        getMenuInflater().inflate(i, this.mMenuBuilder);
        if (this.mMenuBuilder.getNonActionItems().isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        COUIBottomFloatingNavigationBarItem.Builder builder = new COUIBottomFloatingNavigationBarItem.Builder();
        for (MenuItemImpl menuItemImpl : this.mMenuBuilder.getNonActionItems()) {
            int itemId = menuItemImpl.getItemId();
            MenuXmlParserUtils.MenuItemAttrs menuItemAttrs2 = (MenuXmlParserUtils.MenuItemAttrs) menuItemAttrs.get(Integer.valueOf(itemId));
            builder.reset().setId(itemId).setTitle(menuItemImpl.getTitle() == null ? "" : menuItemImpl.getTitle().toString()).setIcon(menuItemImpl.getIcon()).setDescription(menuItemImpl.getContentDescription());
            if (menuItemAttrs2 != null) {
                builder.setIconResId(menuItemAttrs2.iconResId).setLargeIcon(menuItemAttrs2.isLargeIcon).setRedDotType(menuItemAttrs2.redDotType).setRedDotNumber(menuItemAttrs2.redDotNumber);
            }
            arrayList.add(builder.build());
        }
        addItems(arrayList);
    }

    public boolean isSupportMeltBall() {
        return this.mMenuView.isSupportMeltBall();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z = this.mLastOrientation != configuration.orientation;
        boolean z2 = this.mLastScreenWidthDp != configuration.screenWidthDp;
        if (z || z2) {
            this.mSkipNextLayoutAnimation = true;
            if (this.mIsExpansionViewActive) {
                cancelExpansionViewSetXAnimIfRunning();
            }
        }
        this.mLastOrientation = configuration.orientation;
        this.mLastScreenWidthDp = configuration.screenWidthDp;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        COUIBottomFloatingNavigationBar cOUIBottomFloatingNavigationBar;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i5 = i3 - i;
        int paddingRight = i5 - getPaddingRight();
        int i6 = paddingRight - paddingLeft;
        int paddingBottom = ((i4 - i2) - getPaddingBottom()) - paddingTop;
        boolean z2 = getLayoutDirection() == 1;
        if (this.mIsExpansionViewActive) {
            cOUIBottomFloatingNavigationBar = this;
            cOUIBottomFloatingNavigationBar.layoutWhenExpansionViewActive(paddingLeft, paddingTop, paddingRight, paddingBottom, z2, i5);
        } else {
            cOUIBottomFloatingNavigationBar = this;
            cOUIBottomFloatingNavigationBar.layoutWhenExpansionViewInactive(paddingLeft, paddingTop, i6, paddingBottom, z2, i5);
        }
        cOUIBottomFloatingNavigationBar.mSkipNextLayoutAnimation = false;
        cOUIBottomFloatingNavigationBar.updateFloatingIconPosition();
        if (cOUIBottomFloatingNavigationBar.mIsExpansionViewActive || cOUIBottomFloatingNavigationBar.mMeltBallHideTransitionState != MeltBallHideTransitionState.WAITING_FOR_LAYOUT) {
            return;
        }
        cOUIBottomFloatingNavigationBar.mMeltBallHideTransitionState = MeltBallHideTransitionState.WAITING_FOR_ANIMATIONS;
        cOUIBottomFloatingNavigationBar.tryFinishMeltBallHideTransition();
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int iMax;
        int iMax2;
        int iCombineMeasuredStates;
        int size = View.MeasureSpec.getSize(i);
        changePadding(size);
        int i4 = 0;
        if (shouldLayoutMenuViewCentered(size)) {
            measureMenuViewWithCenteredExpansionReserve(i, i2);
            iMax2 = this.mMenuView.getMeasuredWidth();
            iMax = this.mMenuView.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.mMenuView.getMeasuredState());
            if (this.mIsExpansionViewActive) {
                iMax2 = Math.max(iMax2, this.mMenuView.getMeasuredWidth() + this.mSideViewSpacing + this.mFakeViewSize);
                iMax = Math.max(iMax, this.mFakeViewSize);
            }
        } else {
            if (!this.mIsExpansionViewActive) {
                if (this.mMenuView.getVisibility() != 8) {
                    measureChild(this.mMenuView, i, i2);
                    iMax2 = Math.max(0, this.mMenuView.getMeasuredWidth());
                    iMax = Math.max(0, this.mMenuView.getMeasuredHeight());
                    iCombineMeasuredStates = View.combineMeasuredStates(0, this.mMenuView.getMeasuredState());
                } else {
                    i3 = 0;
                    iMax = 0;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + i4, getSuggestedMinimumWidth()), i, i3), View.resolveSizeAndState(Math.max(Math.max(getPaddingBottom() + getPaddingTop() + iMax, getSuggestedMinimumHeight()), getPaddingBottom() + getPaddingTop() + this.mMinHeightWithoutPaddingVertical), i2, i3 << 16));
            }
            int i5 = this.mFakeViewSize;
            int iMax3 = Math.max(0, i5);
            this.mMenuView.measure(View.MeasureSpec.makeMeasureSpec(Math.max(0, (((size - getPaddingLeft()) - getPaddingRight()) - i5) - this.mSideViewSpacing), Integer.MIN_VALUE), i2);
            iMax2 = Math.max(0, this.mMenuView.getMeasuredWidth() + this.mSideViewSpacing + i5);
            iMax = Math.max(iMax3, this.mMenuView.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.mMenuView.getMeasuredState());
        }
        int i6 = iCombineMeasuredStates;
        i4 = iMax2;
        i3 = i6;
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + i4, getSuggestedMinimumWidth()), i, i3), View.resolveSizeAndState(Math.max(Math.max(getPaddingBottom() + getPaddingTop() + iMax, getSuggestedMinimumHeight()), getPaddingBottom() + getPaddingTop() + this.mMinHeightWithoutPaddingVertical), i2, i3 << 16));
    }

    public void refresh() {
        this.mMenuView.refreshThemeAppearance();
        setupFloatingIcon();
        invalidate();
    }

    @Deprecated
    public void removeExpansionView() {
        hideExpansionView(true);
    }

    public void setDispatchItemClickWhenAlreadySelected(boolean z) {
        this.mMenuView.setDispatchItemClickWhenAlreadySelected(z);
    }

    public void setExpansionViewAlpha(View view, float f) {
        if (view != null) {
            view.setAlpha(f);
        }
    }

    public void setIsDrawableMaterialEffect(boolean z) {
        this.mMenuView.setIsDrawableMaterialEffect(z);
    }

    public void setItemsTitleTextColor(ColorStateList colorStateList) {
        this.mMenuView.setItemsTitleTextColor(colorStateList);
    }

    public void setMenuViewCentered(boolean z) {
        if (this.mMenuViewCentered == z) {
            return;
        }
        this.mMenuViewCentered = z;
        requestLayout();
    }

    public void setOnItemSelectedListener(final OnItemSelectedListener onItemSelectedListener) {
        this.mMenuView.setOnItemClickListener(new BaseBottomFloatingMenuView.OnItemClickListener() { // from class: com.coui.appcompat.bottomfloatingnavigationbar.COUIBottomFloatingNavigationBar.6
            public void onItemClick(View view, BaseBottomFloatingItem baseBottomFloatingItem, int i) {
                OnItemSelectedListener onItemSelectedListener2 = onItemSelectedListener;
                if (onItemSelectedListener2 == null || !(baseBottomFloatingItem instanceof COUIBottomFloatingNavigationBarItem)) {
                    return;
                }
                onItemSelectedListener2.onNavigationItemSelected(view, (COUIBottomFloatingNavigationBarItem) baseBottomFloatingItem, i);
            }
        });
    }

    public void setOnMenuViewShowHideAnimListener(OnMenuViewShowHideAnimListener onMenuViewShowHideAnimListener) {
        this.mMenuView.setOnMenuViewShowHideAnimListener(onMenuViewShowHideAnimListener);
    }

    public void setPopupMenuRuleEnabled(boolean z) {
        this.mPopupRuleEnable = z;
    }

    public void setRedDot(int i, int i2, int i3) {
        this.mMenuView.setRedDot(i, i2, i3);
    }

    public void setSelectedItemId(int i) {
        this.mMenuView.setSelectedItem(i);
    }

    public void setUseMaterialEffectAttr(BaseBottomFloatingMenuView.MaterialEffectState materialEffectState) {
        this.mMenuView.setUseMaterialEffectAttr(materialEffectState);
    }

    public void showExpansionView(boolean z) {
        if (this.mIsExpansionViewActive || this.mExpansionView == null || this.mMainView == null) {
            return;
        }
        this.mMeltBallHideTransitionState = MeltBallHideTransitionState.IDLE;
        if (!z) {
            this.mSkipNextLayoutAnimation = true;
        }
        this.mIsExpansionViewActive = true;
        applyExpansionViewMeltBallState(z);
        this.mExpansionView.setVisibility(0);
        this.mExpansionView.setEnabled(true);
        requestLayout();
        applyExpansionViewAlphaScaleState(true, z);
    }

    public void showMenuView() {
        if (this.mIsMenuViewVisible) {
            return;
        }
        this.mIsMenuViewVisible = true;
        this.mMenuView.doMenuViewShowOrHideAnim(true);
    }

    public void unbindExpansionView() {
        this.mMeltBallHideTransitionState = MeltBallHideTransitionState.IDLE;
        cancelExpansionViewAlphaScaleAnimIfRunning();
        cancelExpansionViewSetXAnimIfRunning();
        clearExpansionViewAlphaPolicy();
        clearMeltBallParams();
        resetMeltBallDrawOrder();
        detachExpansionViewLayoutListener();
        this.mExpansionView = null;
        this.mMainView = null;
        this.mMeltBallChainViews = null;
        this.mLastFakeBoundsLeft = Integer.MIN_VALUE;
    }

    public COUIBottomFloatingNavigationBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.couiBottomFloatingNavigationBarStyle);
    }

    public COUIBottomFloatingNavigationBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public COUIBottomFloatingNavigationBar(Context context) {
        this(context, null);
    }
}
