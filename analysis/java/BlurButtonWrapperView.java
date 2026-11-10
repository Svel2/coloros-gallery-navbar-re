package com.oplus.gallery.basebiz.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.exifinterface.media.ExifInterface;
import com.coui.appcompat.COUIMaterialStateManager;
import com.coui.appcompat.OnMaterialStateChangeListener;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;
import com.oplus.aiunit.vision.gad0;
import com.oplus.aiunit.vision.kv6;
import com.oplus.aiunit.vision.lv6;
import com.oplus.aiunit.vision.mv6;
import com.oplus.aiunit.vision.u16;
import com.oplus.gallery.basebiz.R$color;
import com.oplus.gallery.basebiz.R$styleable;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u00012\u00020\u0002J\u001e\u0010\u0005\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0003H\u0086\b¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rR\u001b\u0010\u0013\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0019\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/oplus/gallery/basebiz/widget/BlurButtonWrapperView;", "Landroid/widget/FrameLayout;", "Lcom/coui/appcompat/OnMaterialStateChangeListener;", "Landroid/view/View;", ExifInterface.GPS_DIRECTION_TRUE, "getButton", "()Landroid/view/View;", "", "translationX", "", "setTranslationX", "(F)V", "getButtonViewAlpha", "()F", "Lcom/oplus/aiunit/vision/u16;", "h", "Lkotlin/Lazy;", "getMaterialEffectDelegate", "()Lcom/oplus/aiunit/vision/u16;", "materialEffectDelegate", "i", "Landroid/view/View;", "getButtonView", "setButtonView", "(Landroid/view/View;)V", "buttonView", "basebiz_realmeDeviceallDomesticApilevelallDensityxxhdpiRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BlurButtonWrapperView extends FrameLayout implements OnMaterialStateChangeListener {
    public static final /* synthetic */ int j = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2388a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public boolean f;
    public final Float g;

    /* JADX INFO: renamed from: h, reason: from kotlin metadata */
    public final Lazy materialEffectDelegate;

    /* JADX INFO: renamed from: i, reason: from kotlin metadata */
    public View buttonView;

    /* JADX WARN: Illegal instructions before constructor call */
    public BlurButtonWrapperView(Context context, AttributeSet attributeSet, int i, int i2) {
        attributeSet = (i2 & 2) != 0 ? null : attributeSet;
        i = (i2 & 4) != 0 ? 0 : i;
        context.getClass();
        super(context, attributeSet, i);
        this.f2388a = true;
        this.b = true;
        this.c = false;
        this.d = false;
        this.e = false;
        kv6 kv6Var = new kv6();
        kv6Var.f1052a = this;
        VarHandle.storeStoreFence();
        this.materialEffectDelegate = LazyKt.lazy(kv6Var);
        int[] iArr = R$styleable.BlurButtonWrapperView;
        iArr.getClass();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        this.f2388a = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BlurButtonWrapperView_blurWrapper_enableBlurEffect, this.f2388a);
        this.b = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BlurButtonWrapperView_blurWrapper_enableStrokeEffect, this.b);
        this.c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BlurButtonWrapperView_blurWrapper_enableOuterShadowEffect, this.c);
        this.d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BlurButtonWrapperView_blurWrapper_enablePressLightEffect, this.d);
        this.e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BlurButtonWrapperView_blurWrapper_enableDragReboundEffect, this.e);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BlurButtonWrapperView_blurWrapper_buttonLayout, 0);
        if (resourceId != 0) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(resourceId, (ViewGroup) null);
            this.buttonView = viewInflate;
            addView(viewInflate, new FrameLayout.LayoutParams(-1, -1));
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.BlurButtonWrapperView_blurWrapper_hasRoundOutline, true)) {
            float dimension = typedArrayObtainStyledAttributes.getDimension(R$styleable.BlurButtonWrapperView_blurWrapper_roundOutlineRadius, 0.0f);
            Float fValueOf = dimension > 0.0f ? Float.valueOf(dimension) : null;
            this.g = fValueOf;
            View view = this.buttonView;
            if (view != null) {
                gad0.m(view, fValueOf);
            }
            gad0.m(this, fValueOf);
        }
        View view2 = this.buttonView;
        if (view2 != null) {
            lv6 lv6Var = new lv6();
            lv6Var.f1132a = this;
            VarHandle.storeStoreFence();
            view2.addOnLayoutChangeListener(lv6Var);
        }
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BlurButtonWrapperView_blurWrapper_buttonBg, 0);
        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.BlurButtonWrapperView_blurWrapper_buttonSrc, 0);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BlurButtonWrapperView_blurWrapper_buttonIconVisible, true);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BlurButtonWrapperView_blurWrapper_butttonMaskVisible, false);
        if (resourceId3 != 0) {
            View view3 = this.buttonView;
            ImageView imageView = view3 instanceof ImageView ? (ImageView) view3 : null;
            if (imageView != null) {
                Drawable drawable = AppCompatResources.getDrawable(getContext(), resourceId3);
                imageView.setImageDrawable(drawable != null ? drawable.mutate() : null);
            }
        }
        if (resourceId2 != 0) {
            Drawable drawable2 = AppCompatResources.getDrawable(getContext(), resourceId2);
            setBackground(drawable2 != null ? drawable2.mutate() : null);
        }
        View view4 = this.buttonView;
        MultiLayerImageView multiLayerImageView = view4 instanceof MultiLayerImageView ? (MultiLayerImageView) view4 : null;
        if (multiLayerImageView != null) {
            multiLayerImageView.setIconLayerVisible(z);
            multiLayerImageView.setLayerMaskVisible(z2);
        }
        typedArrayObtainStyledAttributes.recycle();
        getMaterialEffectDelegate().e = this.g;
        getMaterialEffectDelegate().e();
        mv6 mv6Var = new mv6();
        mv6Var.f1212a = this;
        VarHandle.storeStoreFence();
        addOnLayoutChangeListener(mv6Var);
    }

    public static void a(BlurButtonWrapperView blurButtonWrapperView, int i, int i2) {
        blurButtonWrapperView.getMaterialEffectDelegate().l((i2 - i) / 2.0f);
    }

    public static void b(BlurButtonWrapperView blurButtonWrapperView, float f, int i) {
        int i2 = R$color.base_button_color_not_immersive;
        blurButtonWrapperView.getClass();
        c(blurButtonWrapperView, true, f, i2, 20);
        View view = blurButtonWrapperView.buttonView;
        if (view != null) {
            view.setAlpha(f);
        }
    }

    public static void c(BlurButtonWrapperView blurButtonWrapperView, boolean z, float f, int i, int i2) {
        if ((i2 & 2) != 0) {
            f = 1.0f;
        }
        blurButtonWrapperView.getMaterialEffectDelegate().i(f, z);
        blurButtonWrapperView.invalidate();
    }

    private final u16 getMaterialEffectDelegate() {
        return (u16) this.materialEffectDelegate.getValue();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        canvas.getClass();
        super.dispatchDraw(canvas);
        getMaterialEffectDelegate().d(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        getMaterialEffectDelegate().h(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public final /* synthetic */ <T extends View> T getButton() {
        View buttonView = getButtonView();
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        if (buttonView == null) {
            return null;
        }
        T t = (T) getButtonView();
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return t;
    }

    public final View getButtonView() {
        return this.buttonView;
    }

    public final float getButtonViewAlpha() {
        View view = this.buttonView;
        if (view != null) {
            return view.getAlpha();
        }
        return 1.0f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.f) {
            COUIMaterialStateManager.INSTANCE.addOnMaterialStateChangedListener(this);
            this.f = true;
        }
        u16 materialEffectDelegate = getMaterialEffectDelegate();
        if (materialEffectDelegate.b.e) {
            COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = materialEffectDelegate.j;
            if (cOUISpotLightEffectDrawable != null) {
                cOUISpotLightEffectDrawable.setCallback(materialEffectDelegate.f1768a);
            }
            materialEffectDelegate.j();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f) {
            COUIMaterialStateManager.INSTANCE.removeOnMaterialStateChangedListener(this);
            this.f = false;
        }
        getMaterialEffectDelegate().c();
    }

    @Override // com.coui.appcompat.OnMaterialStateChangeListener
    public final CharSequence onDump() {
        return toString();
    }

    @Override // com.coui.appcompat.OnMaterialStateChangeListener
    public final void onMaterialStateChange(int i, boolean z) {
        getMaterialEffectDelegate().f(i);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        getMaterialEffectDelegate().g(i, i2);
    }

    public final void setButtonView(View view) {
        this.buttonView = view;
    }

    @Override // android.view.View
    public void setTranslationX(float translationX) {
        super.setTranslationX(translationX);
        u16 materialEffectDelegate = getMaterialEffectDelegate();
        if (materialEffectDelegate.h) {
            return;
        }
        materialEffectDelegate.i = translationX;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BlurButtonWrapperView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 252);
        context.getClass();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BlurButtonWrapperView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 248);
        context.getClass();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BlurButtonWrapperView(Context context) {
        this(context, null, 0, 254);
        context.getClass();
    }
}
