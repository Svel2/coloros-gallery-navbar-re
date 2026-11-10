package com.oplus.gallery.basebiz.widget;

import android.content.Context;
import android.graphics.RenderEffect;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.coui.appcompat.COUIMaterialStateManager;
import com.coui.appcompat.OnMaterialStateChangeListener;
import com.oplus.aiunit.vision.g9d0;
import com.oplus.aiunit.vision.vau;
import com.oplus.backup.sdk.common.utils.Constants;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes9.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B'\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/oplus/gallery/basebiz/widget/GradientBlurMaskView;", "Landroidx/appcompat/widget/AppCompatImageView;", "Lcom/coui/appcompat/OnMaterialStateChangeListener;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Lcom/oplus/aiunit/vision/vau$a;", Constants.MessagerConstants.CONFIG_KEY, "", "setBlurConfig", "(Lcom/oplus/aiunit/vision/vau$a;)V", "basebiz_realmeDeviceallDomesticApilevelallDensityxxhdpiRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GradientBlurMaskView extends AppCompatImageView implements OnMaterialStateChangeListener {
    public static final vau.a c;
    public boolean a;
    public vau.a b;

    static {
        vau.a aVar = new vau.a();
        VarHandle.storeStoreFence();
        c = aVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GradientBlurMaskView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        context.getClass();
        this.b = c;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0026  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void f(boolean z) {
        boolean zE;
        if (this.a) {
            COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
            if (cOUIMaterialStateManager.materialVersionCheck()) {
                zE = cOUIMaterialStateManager.isBlurEffectEnable();
            } else {
                Lazy lazy = g9d0.a;
                zE = g9d0.e();
            }
            if (zE) {
                RenderEffect renderEffectA = vau.a(this.b);
                if (renderEffectA != null) {
                    vau.b(renderEffectA, this);
                }
            } else {
                vau.b((RenderEffect) null, this);
            }
        } else {
            vau.b((RenderEffect) null, this);
        }
        if (z && isAttachedToWindow()) {
            Lazy lazy2 = g9d0.a;
            g9d0.c(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.view.View*/.onAttachedToWindow();
        COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
        if (cOUIMaterialStateManager.materialVersionCheck()) {
            cOUIMaterialStateManager.addOnMaterialStateChangedListener(this);
        }
        f(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
        if (cOUIMaterialStateManager.materialVersionCheck()) {
            cOUIMaterialStateManager.removeOnMaterialStateChangedListener(this);
        }
        vau.b((RenderEffect) null, this);
        super/*android.view.View*/.onDetachedFromWindow();
    }

    public final CharSequence onDump() {
        String string = toString();
        string.getClass();
        return string;
    }

    public final void onMaterialStateChange(int i, boolean z) {
        if (i != 2) {
            return;
        }
        f(true);
    }

    public final void setBlurConfig(vau.a config) {
        config.getClass();
        if (Intrinsics.areEqual(this.b, config)) {
            return;
        }
        this.b = config;
        f(true);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GradientBlurMaskView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 4, 0);
        context.getClass();
    }

    public /* synthetic */ GradientBlurMaskView(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, (i & 2) != 0 ? null : attributeSet, 0);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GradientBlurMaskView(Context context) {
        this(context, null, 6, 0);
        context.getClass();
    }
}
