package com.coui.appcompat;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.view.View;
import com.oplus.aiunit.vision.fbo;
import com.oplus.aiunit.vision.hs4;
import com.oplus.aiunit.vision.xd7;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00062\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0007"}, d2 = {"Lcom/coui/appcompat/COUIMaterialBlurEffect;", "", "()V", "BaseBlurEffectParams", "BlurEffectParams", "BlurEffectType", "Companion", "coui-support-effect_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class COUIMaterialBlurEffect {
    private static final BlurEffectParams CONTENT_SECONDARY_BUTTON_PARAMS_DARK;
    private static final BlurEffectParams CONTENT_SECONDARY_BUTTON_PARAMS_LIGHT;
    private static final BlurEffectParams CONTENT_SEGMENT_BUTTON_PARAMS_DARK;
    private static final BlurEffectParams CONTENT_SEGMENT_BUTTON_PARAMS_LIGHT;
    private static final BlurEffectParams CONTENT_TRANSPARENT_BUTTON_PARAMS_DARK;
    private static final BlurEffectParams CONTENT_TRANSPARENT_BUTTON_PARAMS_LIGHT;
    private static final BlurEffectParams FRAMEWORK_BOTTOM_BAR_PARAMS_DARK;
    private static final BlurEffectParams FRAMEWORK_BOTTOM_BAR_PARAMS_LIGHT;
    private static final BlurEffectParams FRAMEWORK_SEARCH_BAR_NO_MATERIAL_DARK;
    private static final BlurEffectParams FRAMEWORK_SEARCH_BAR_NO_MATERIAL_LIGHT;
    private static final BlurEffectParams FRAMEWORK_TOP_BAR_BLUR_PARAMS_DARK;
    private static final BlurEffectParams FRAMEWORK_TOP_BAR_BLUR_PARAMS_LIGHT;
    private static final BlurEffectParams FRAMEWORK_TOP_BAR_PARAMS1;
    private static final BlurEffectParams FRAMEWORK_TOP_BAR_PARAMS_DARK;
    private static final BlurEffectParams FRAMEWORK_TOP_BAR_PARAMS_LIGHT;
    private static final BlurEffectParams NO_EFFECT;
    private static final BlurEffectParams TYPE_CONTENT_CHIP_TAB_NO_BLUR_PARAMS_DARK;
    private static final BlurEffectParams TYPE_CONTENT_CHIP_TAB_NO_BLUR_PARAMS_LIGHT;
    private static final BlurEffectParams TYPE_CONTENT_CHIP_TAB_PARAMS_DARK;
    private static final BlurEffectParams TYPE_CONTENT_CHIP_TAB_PARAMS_LIGHT;
    private static final BlurEffectParams TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR_PARAMS_DARK;
    private static final BlurEffectParams TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR_PARAMS_LIGHT;
    private static final BlurEffectParams TYPE_CONTENT_CHIP_TAB_SELECTED_PARAMS_DARK;
    private static final BlurEffectParams TYPE_CONTENT_CHIP_TAB_SELECTED_PARAMS_LIGHT;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0014\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH&R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/coui/appcompat/COUIMaterialBlurEffect$BaseBlurEffectParams;", "", "blurRadius", "", "blendModeLayer1", "Landroid/graphics/BlendMode;", "blendModeLayer2", "(FLandroid/graphics/BlendMode;Landroid/graphics/BlendMode;)V", "getBlendModeLayer1", "getBlendModeLayer2", "getBlurRadius", "context", "Landroid/content/Context;", "getColorLayer1", "", "getColorLayer2", "coui-support-effect_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class BaseBlurEffectParams {
        private final BlendMode blendModeLayer1;
        private final BlendMode blendModeLayer2;
        private final float blurRadius;

        public BaseBlurEffectParams(float f, BlendMode blendMode, BlendMode blendMode2) {
            blendMode.getClass();
            blendMode2.getClass();
            this.blurRadius = f;
            this.blendModeLayer1 = blendMode;
            this.blendModeLayer2 = blendMode2;
        }

        public static /* synthetic */ int getColorLayer1$default(BaseBlurEffectParams baseBlurEffectParams, Context context, int i, Object obj) {
            if (obj != null) {
                xd7.a("Super calls with default arguments not supported in this target, function: getColorLayer1");
                return 0;
            }
            if ((i & 1) != 0) {
                context = null;
            }
            return baseBlurEffectParams.getColorLayer1(context);
        }

        public static /* synthetic */ int getColorLayer2$default(BaseBlurEffectParams baseBlurEffectParams, Context context, int i, Object obj) {
            if (obj != null) {
                xd7.a("Super calls with default arguments not supported in this target, function: getColorLayer2");
                return 0;
            }
            if ((i & 1) != 0) {
                context = null;
            }
            return baseBlurEffectParams.getColorLayer2(context);
        }

        public final BlendMode getBlendModeLayer1() {
            return this.blendModeLayer1;
        }

        public final BlendMode getBlendModeLayer2() {
            return this.blendModeLayer2;
        }

        public final float getBlurRadius(Context context) {
            context.getClass();
            return this.blurRadius;
        }

        public abstract int getColorLayer1(Context context);

        public abstract int getColorLayer2(Context context);
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u000b\u001a\u00020\u0003HÂ\u0003J\t\u0010\f\u001a\u00020\u0005HÂ\u0003J\t\u0010\r\u001a\u00020\u0007HÂ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÂ\u0003J\t\u0010\u000f\u001a\u00020\u0007HÂ\u0003J;\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\t\u0010\u001a\u001a\u00020\u0016HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/coui/appcompat/COUIMaterialBlurEffect$BlurEffectParams;", "Lcom/coui/appcompat/COUIMaterialBlurEffect$BaseBlurEffectParams;", "blurRadius", "", "colorLayer1", "", "blendModeLayer1", "Landroid/graphics/BlendMode;", "colorLayer2", "blendModeLayer2", "(FLjava/lang/String;Landroid/graphics/BlendMode;Ljava/lang/String;Landroid/graphics/BlendMode;)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "getColorLayer1", "", "context", "Landroid/content/Context;", "getColorLayer2", "hashCode", "toString", "coui-support-effect_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BlurEffectParams extends BaseBlurEffectParams {
        private final BlendMode blendModeLayer1;
        private final BlendMode blendModeLayer2;
        private final float blurRadius;
        private final String colorLayer1;
        private final String colorLayer2;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BlurEffectParams(float f, String str, BlendMode blendMode, String str2, BlendMode blendMode2) {
            super(f, blendMode, blendMode2);
            str.getClass();
            blendMode.getClass();
            str2.getClass();
            blendMode2.getClass();
            this.blurRadius = f;
            this.colorLayer1 = str;
            this.blendModeLayer1 = blendMode;
            this.colorLayer2 = str2;
            this.blendModeLayer2 = blendMode2;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final float getBlurRadius() {
            return this.blurRadius;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final String getColorLayer1() {
            return this.colorLayer1;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        private final BlendMode getBlendModeLayer1() {
            return this.blendModeLayer1;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        private final String getColorLayer2() {
            return this.colorLayer2;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        private final BlendMode getBlendModeLayer2() {
            return this.blendModeLayer2;
        }

        public static /* synthetic */ BlurEffectParams copy$default(BlurEffectParams blurEffectParams, float f, String str, BlendMode blendMode, String str2, BlendMode blendMode2, int i, Object obj) {
            if ((i & 1) != 0) {
                f = blurEffectParams.blurRadius;
            }
            if ((i & 2) != 0) {
                str = blurEffectParams.colorLayer1;
            }
            if ((i & 4) != 0) {
                blendMode = blurEffectParams.blendModeLayer1;
            }
            if ((i & 8) != 0) {
                str2 = blurEffectParams.colorLayer2;
            }
            if ((i & 16) != 0) {
                blendMode2 = blurEffectParams.blendModeLayer2;
            }
            BlendMode blendMode3 = blendMode2;
            BlendMode blendMode4 = blendMode;
            return blurEffectParams.copy(f, str, blendMode4, str2, blendMode3);
        }

        public final BlurEffectParams copy(float blurRadius, String colorLayer1, BlendMode blendModeLayer1, String colorLayer2, BlendMode blendModeLayer2) {
            colorLayer1.getClass();
            blendModeLayer1.getClass();
            colorLayer2.getClass();
            blendModeLayer2.getClass();
            return new BlurEffectParams(blurRadius, colorLayer1, blendModeLayer1, colorLayer2, blendModeLayer2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BlurEffectParams)) {
                return false;
            }
            BlurEffectParams blurEffectParams = (BlurEffectParams) other;
            return Float.compare(this.blurRadius, blurEffectParams.blurRadius) == 0 && Intrinsics.areEqual(this.colorLayer1, blurEffectParams.colorLayer1) && this.blendModeLayer1 == blurEffectParams.blendModeLayer1 && Intrinsics.areEqual(this.colorLayer2, blurEffectParams.colorLayer2) && this.blendModeLayer2 == blurEffectParams.blendModeLayer2;
        }

        @Override // com.coui.appcompat.COUIMaterialBlurEffect.BaseBlurEffectParams
        public int getColorLayer1(Context context) {
            return Color.parseColor(this.colorLayer1);
        }

        @Override // com.coui.appcompat.COUIMaterialBlurEffect.BaseBlurEffectParams
        public int getColorLayer2(Context context) {
            return Color.parseColor(this.colorLayer2);
        }

        public int hashCode() {
            return this.blendModeLayer2.hashCode() + hs4.a((this.blendModeLayer1.hashCode() + hs4.a(Float.hashCode(this.blurRadius) * 31, 31, this.colorLayer1)) * 31, 31, this.colorLayer2);
        }

        public String toString() {
            return "BlurEffectParams(blurRadius=" + this.blurRadius + ", colorLayer1=" + this.colorLayer1 + ", blendModeLayer1=" + this.blendModeLayer1 + ", colorLayer2=" + this.colorLayer2 + ", blendModeLayer2=" + this.blendModeLayer2 + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/coui/appcompat/COUIMaterialBlurEffect$BlurEffectType;", "", "(Ljava/lang/String;I)V", "TYPE_NO_EFFECT", "TYPE_FRAMEWORK_TOP_BAR", "TYPE_SEARCH_BAR_NO_MATERIAL", "TYPE_FRAMEWORK_TOP_BAR_BLUR", "TYPE_FRAMEWORK_TOP_BAR1", "TYPE_FRAMEWORK_BOTTOM_BAR", "TYPE_CONTENT_SECONDARY_BUTTON", "TYPE_CONTENT_TRANSPARENT_BUTTON", "TYPE_CONTENT_SEGMENT_BUTTON", "TYPE_CONTENT_CHIP_TAB", "TYPE_CONTENT_CHIP_TAB_SELECTED", "TYPE_CONTENT_CHIP_TAB_NO_BLUR", "TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR", "coui-support-effect_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public enum BlurEffectType {
        TYPE_NO_EFFECT,
        TYPE_FRAMEWORK_TOP_BAR,
        TYPE_SEARCH_BAR_NO_MATERIAL,
        TYPE_FRAMEWORK_TOP_BAR_BLUR,
        TYPE_FRAMEWORK_TOP_BAR1,
        TYPE_FRAMEWORK_BOTTOM_BAR,
        TYPE_CONTENT_SECONDARY_BUTTON,
        TYPE_CONTENT_TRANSPARENT_BUTTON,
        TYPE_CONTENT_SEGMENT_BUTTON,
        TYPE_CONTENT_CHIP_TAB,
        TYPE_CONTENT_CHIP_TAB_SELECTED,
        TYPE_CONTENT_CHIP_TAB_NO_BLUR,
        TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<BlurEffectType> getEntries() {
            return $ENTRIES;
        }
    }

    static {
        BlendMode blendMode = BlendMode.SRC_OVER;
        NO_EFFECT = new BlurEffectParams(0.0f, "#00000000", blendMode, "#00000000", blendMode);
        BlendMode blendMode2 = BlendMode.COLOR_DODGE;
        BlendMode blendMode3 = BlendMode.LUMINOSITY;
        FRAMEWORK_TOP_BAR_PARAMS_LIGHT = new BlurEffectParams(0.0f, "#66333333", blendMode2, "#99F5F5F5", blendMode3);
        FRAMEWORK_SEARCH_BAR_NO_MATERIAL_LIGHT = new BlurEffectParams(0.0f, "#D6333333", blendMode2, "#66F5F5F5", blendMode3);
        FRAMEWORK_TOP_BAR_BLUR_PARAMS_LIGHT = new BlurEffectParams(20.0f, "#66333333", blendMode2, "#99F5F5F5", blendMode3);
        FRAMEWORK_BOTTOM_BAR_PARAMS_LIGHT = new BlurEffectParams(25.0f, "#B3666666", blendMode2, "#99F5F5F5", blendMode3);
        FRAMEWORK_TOP_BAR_PARAMS1 = new BlurEffectParams(25.0f, "#1A6E6E6E", blendMode2, "#24F1F1F1", blendMode3);
        CONTENT_SECONDARY_BUTTON_PARAMS_LIGHT = new BlurEffectParams(25.0f, "#CCFFFFFF", blendMode, "#00FFFFFF", blendMode);
        BlendMode blendMode4 = BlendMode.OVERLAY;
        CONTENT_TRANSPARENT_BUTTON_PARAMS_LIGHT = new BlurEffectParams(25.0f, "#3F333333", blendMode4, "#3FFFFFFF", blendMode3);
        CONTENT_SEGMENT_BUTTON_PARAMS_LIGHT = new BlurEffectParams(25.0f, "#7F333333", blendMode2, "#99EFEFEF", blendMode3);
        TYPE_CONTENT_CHIP_TAB_SELECTED_PARAMS_LIGHT = new BlurEffectParams(40.0f, "#66A3A1A1", blendMode2, "#99F5F5F5", blendMode3);
        TYPE_CONTENT_CHIP_TAB_PARAMS_LIGHT = new BlurEffectParams(40.0f, "#144E4E4E", blendMode2, "#14606060", blendMode3);
        TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR_PARAMS_LIGHT = new BlurEffectParams(0.0f, "#66A3A3A3", blendMode2, "#CEF5F5F5", blendMode3);
        TYPE_CONTENT_CHIP_TAB_NO_BLUR_PARAMS_LIGHT = new BlurEffectParams(0.0f, "#9A9B9B9B", blendMode2, "#9ACECECE", blendMode3);
        FRAMEWORK_TOP_BAR_PARAMS_DARK = new BlurEffectParams(0.0f, "#CC262626", blendMode, "#B37F7F7F", blendMode4);
        FRAMEWORK_SEARCH_BAR_NO_MATERIAL_DARK = new BlurEffectParams(0.0f, "#CC262626", blendMode, "#B37F7F7F", blendMode4);
        FRAMEWORK_TOP_BAR_BLUR_PARAMS_DARK = new BlurEffectParams(20.0f, "#CC262626", blendMode, "#B37F7F7F", blendMode4);
        FRAMEWORK_BOTTOM_BAR_PARAMS_DARK = new BlurEffectParams(25.0f, "#80666666", blendMode4, "#B3262626", blendMode3);
        CONTENT_SECONDARY_BUTTON_PARAMS_DARK = new BlurEffectParams(25.0f, "#33FFFFFF", blendMode, "#00FFFFFF", blendMode);
        CONTENT_TRANSPARENT_BUTTON_PARAMS_DARK = new BlurEffectParams(25.0f, "#3F333333", blendMode4, "#3FFFFFFF", blendMode3);
        CONTENT_SEGMENT_BUTTON_PARAMS_DARK = new BlurEffectParams(25.0f, "#7F7F7F7F", blendMode4, "#99262626", blendMode3);
        TYPE_CONTENT_CHIP_TAB_SELECTED_PARAMS_DARK = new BlurEffectParams(40.0f, "#A6505050", blendMode3, "#4CA7A7A7", blendMode4);
        TYPE_CONTENT_CHIP_TAB_PARAMS_DARK = new BlurEffectParams(40.0f, "#33B2B2B2", blendMode3, "#33303030", blendMode4);
        TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR_PARAMS_DARK = new BlurEffectParams(0.0f, "#DA888888", blendMode3, "#80A8A8A8", blendMode4);
        TYPE_CONTENT_CHIP_TAB_NO_BLUR_PARAMS_DARK = new BlurEffectParams(0.0f, "#EE474747", blendMode3, "#CE8D8D8D", blendMode4);
    }

    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"J\u001d\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0000¢\u0006\u0002\b(J\u0010\u0010)\u001a\u00020*2\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010+\u001a\u00020*2\u0006\u0010$\u001a\u00020%2\u0006\u0010,\u001a\u00020-H\u0002J\u001a\u0010.\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010/\u001a\u0004\u0018\u000100H\u0002J\u001d\u00101\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010,\u001a\u00020-H\u0000¢\u0006\u0002\b2J7\u00101\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u00103\u001a\u00020-2\u0006\u00104\u001a\u00020-2\u0006\u00105\u001a\u00020-2\b\b\u0001\u00106\u001a\u000207H\u0000¢\u0006\u0002\b2J\u001d\u00101\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010&\u001a\u00020'H\u0000¢\u0006\u0002\b2J=\u00101\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020:2\u0006\u0010>\u001a\u00020<H\u0000¢\u0006\u0002\b2R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/coui/appcompat/COUIMaterialBlurEffect$Companion;", "", "()V", "CONTENT_SECONDARY_BUTTON_PARAMS_DARK", "Lcom/coui/appcompat/COUIMaterialBlurEffect$BlurEffectParams;", "CONTENT_SECONDARY_BUTTON_PARAMS_LIGHT", "CONTENT_SEGMENT_BUTTON_PARAMS_DARK", "CONTENT_SEGMENT_BUTTON_PARAMS_LIGHT", "CONTENT_TRANSPARENT_BUTTON_PARAMS_DARK", "CONTENT_TRANSPARENT_BUTTON_PARAMS_LIGHT", "FRAMEWORK_BOTTOM_BAR_PARAMS_DARK", "FRAMEWORK_BOTTOM_BAR_PARAMS_LIGHT", "FRAMEWORK_SEARCH_BAR_NO_MATERIAL_DARK", "FRAMEWORK_SEARCH_BAR_NO_MATERIAL_LIGHT", "FRAMEWORK_TOP_BAR_BLUR_PARAMS_DARK", "FRAMEWORK_TOP_BAR_BLUR_PARAMS_LIGHT", "FRAMEWORK_TOP_BAR_PARAMS1", "FRAMEWORK_TOP_BAR_PARAMS_DARK", "FRAMEWORK_TOP_BAR_PARAMS_LIGHT", "NO_EFFECT", "TYPE_CONTENT_CHIP_TAB_NO_BLUR_PARAMS_DARK", "TYPE_CONTENT_CHIP_TAB_NO_BLUR_PARAMS_LIGHT", "TYPE_CONTENT_CHIP_TAB_PARAMS_DARK", "TYPE_CONTENT_CHIP_TAB_PARAMS_LIGHT", "TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR_PARAMS_DARK", "TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR_PARAMS_LIGHT", "TYPE_CONTENT_CHIP_TAB_SELECTED_PARAMS_DARK", "TYPE_CONTENT_CHIP_TAB_SELECTED_PARAMS_LIGHT", "argbEvaluator", "Landroid/animation/ArgbEvaluator;", "clearBlurEffect", "", "view", "Landroid/view/View;", "clearBlurEffect$coui_support_effect_release", "getBlurParamsByType", "context", "Landroid/content/Context;", "type", "Lcom/coui/appcompat/COUIMaterialBlurEffect$BlurEffectType;", "getBlurParamsByType$coui_support_effect_release", "isNightMode", "", "isNoEffect", "params", "Lcom/coui/appcompat/COUIMaterialBlurEffect$BaseBlurEffectParams;", "setBackgroundRenderEffect", "effect", "Landroid/graphics/RenderEffect;", "setViewBlurEffect", "setViewBlurEffect$coui_support_effect_release", "startParams", "endParams", "blendModeParams", "progress", "", "blurRadius", "colorLayer1", "", "blendModeLayer1", "Landroid/graphics/BlendMode;", "colorLayer2", "blendModeLayer2", "coui-support-effect_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[BlurEffectType.values().length];
                try {
                    iArr[BlurEffectType.TYPE_FRAMEWORK_TOP_BAR.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[BlurEffectType.TYPE_SEARCH_BAR_NO_MATERIAL.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[BlurEffectType.TYPE_FRAMEWORK_TOP_BAR_BLUR.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[BlurEffectType.TYPE_FRAMEWORK_TOP_BAR1.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[BlurEffectType.TYPE_FRAMEWORK_BOTTOM_BAR.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[BlurEffectType.TYPE_CONTENT_SECONDARY_BUTTON.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[BlurEffectType.TYPE_CONTENT_TRANSPARENT_BUTTON.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[BlurEffectType.TYPE_CONTENT_SEGMENT_BUTTON.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[BlurEffectType.TYPE_CONTENT_CHIP_TAB.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[BlurEffectType.TYPE_CONTENT_CHIP_TAB_SELECTED.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[BlurEffectType.TYPE_CONTENT_CHIP_TAB_NO_BLUR.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr[BlurEffectType.TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR.ordinal()] = 12;
                } catch (NoSuchFieldError unused12) {
                }
                try {
                    iArr[BlurEffectType.TYPE_NO_EFFECT.ordinal()] = 13;
                } catch (NoSuchFieldError unused13) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final boolean isNightMode(Context context) {
            return COUIDarkModeUtil.isCOUIDarkThemeOrNightMode(context);
        }

        private final boolean isNoEffect(Context context, BaseBlurEffectParams params) {
            return params.getBlurRadius(context) == 0.0f && params.getColorLayer1(context) == 0 && params.getColorLayer2(context) == 0;
        }

        private final void setBackgroundRenderEffect(View view, RenderEffect effect) {
            COUIMaterialEffect.setBackgroundRenderEffect(effect, view);
        }

        public final void clearBlurEffect$coui_support_effect_release(View view) {
            view.getClass();
            setBackgroundRenderEffect(view, null);
        }

        public final BlurEffectParams getBlurParamsByType$coui_support_effect_release(Context context, BlurEffectType type) {
            context.getClass();
            type.getClass();
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    return isNightMode(context) ? COUIMaterialBlurEffect.FRAMEWORK_TOP_BAR_PARAMS_DARK : COUIMaterialBlurEffect.FRAMEWORK_TOP_BAR_PARAMS_LIGHT;
                case 2:
                    return isNightMode(context) ? COUIMaterialBlurEffect.FRAMEWORK_SEARCH_BAR_NO_MATERIAL_DARK : COUIMaterialBlurEffect.FRAMEWORK_SEARCH_BAR_NO_MATERIAL_LIGHT;
                case 3:
                    return isNightMode(context) ? COUIMaterialBlurEffect.FRAMEWORK_TOP_BAR_BLUR_PARAMS_DARK : COUIMaterialBlurEffect.FRAMEWORK_TOP_BAR_BLUR_PARAMS_LIGHT;
                case 4:
                    return COUIMaterialBlurEffect.FRAMEWORK_TOP_BAR_PARAMS1;
                case 5:
                    return isNightMode(context) ? COUIMaterialBlurEffect.FRAMEWORK_BOTTOM_BAR_PARAMS_DARK : COUIMaterialBlurEffect.FRAMEWORK_BOTTOM_BAR_PARAMS_LIGHT;
                case 6:
                    return isNightMode(context) ? COUIMaterialBlurEffect.CONTENT_SECONDARY_BUTTON_PARAMS_DARK : COUIMaterialBlurEffect.CONTENT_SECONDARY_BUTTON_PARAMS_LIGHT;
                case 7:
                    return isNightMode(context) ? COUIMaterialBlurEffect.CONTENT_TRANSPARENT_BUTTON_PARAMS_DARK : COUIMaterialBlurEffect.CONTENT_TRANSPARENT_BUTTON_PARAMS_LIGHT;
                case 8:
                    return isNightMode(context) ? COUIMaterialBlurEffect.CONTENT_SEGMENT_BUTTON_PARAMS_DARK : COUIMaterialBlurEffect.CONTENT_SEGMENT_BUTTON_PARAMS_LIGHT;
                case 9:
                    return isNightMode(context) ? COUIMaterialBlurEffect.TYPE_CONTENT_CHIP_TAB_PARAMS_DARK : COUIMaterialBlurEffect.TYPE_CONTENT_CHIP_TAB_PARAMS_LIGHT;
                case 10:
                    return isNightMode(context) ? COUIMaterialBlurEffect.TYPE_CONTENT_CHIP_TAB_SELECTED_PARAMS_DARK : COUIMaterialBlurEffect.TYPE_CONTENT_CHIP_TAB_SELECTED_PARAMS_LIGHT;
                case 11:
                    return isNightMode(context) ? COUIMaterialBlurEffect.TYPE_CONTENT_CHIP_TAB_NO_BLUR_PARAMS_DARK : COUIMaterialBlurEffect.TYPE_CONTENT_CHIP_TAB_NO_BLUR_PARAMS_LIGHT;
                case 12:
                    return isNightMode(context) ? COUIMaterialBlurEffect.TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR_PARAMS_DARK : COUIMaterialBlurEffect.TYPE_CONTENT_CHIP_TAB_SELECTED_NO_BLUR_PARAMS_LIGHT;
                case 13:
                    return COUIMaterialBlurEffect.NO_EFFECT;
                default:
                    fbo.a();
                    return null;
            }
        }

        /* JADX WARN: Code duplicated, block: B:6:0x001e  */
        /* JADX WARN: Code duplicated, block: B:8:0x0024  */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x002b, code lost:
        
            if (isNoEffect(r0, r13) != false) goto L10;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void setViewBlurEffect$coui_support_effect_release(android.view.View r11, com.coui.appcompat.COUIMaterialBlurEffect.BaseBlurEffectParams r12, com.coui.appcompat.COUIMaterialBlurEffect.BaseBlurEffectParams r13, com.coui.appcompat.COUIMaterialBlurEffect.BaseBlurEffectParams r14, float r15) {
            /*
                r10 = this;
                r11.getClass()
                r12.getClass()
                r13.getClass()
                r14.getClass()
                android.content.Context r0 = r11.getContext()
                r1 = 0
                int r1 = (r15 > r1 ? 1 : (r15 == r1 ? 0 : -1))
                if (r1 != 0) goto L1e
                r0.getClass()
                boolean r1 = r10.isNoEffect(r0, r12)
                if (r1 != 0) goto L2d
            L1e:
                r1 = 1065353216(0x3f800000, float:1.0)
                int r1 = (r15 > r1 ? 1 : (r15 == r1 ? 0 : -1))
                if (r1 != 0) goto L32
                r0.getClass()
                boolean r1 = r10.isNoEffect(r0, r13)
                if (r1 == 0) goto L32
            L2d:
                r12 = 0
                r10.setBackgroundRenderEffect(r11, r12)
                return
            L32:
                r0.getClass()
                float r1 = r12.getBlurRadius(r0)
                float r2 = r13.getBlurRadius(r0)
                float r3 = r12.getBlurRadius(r0)
                float r2 = r2 - r3
                float r2 = r2 * r15
                float r5 = r2 + r1
                android.animation.ArgbEvaluator r1 = com.coui.appcompat.COUIMaterialBlurEffect.access$getArgbEvaluator$cp()
                int r2 = r12.getColorLayer1(r0)
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                int r3 = r13.getColorLayer1(r0)
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                java.lang.Object r1 = r1.evaluate(r15, r2, r3)
                r1.getClass()
                java.lang.Integer r1 = (java.lang.Integer) r1
                int r6 = r1.intValue()
                android.animation.ArgbEvaluator r1 = com.coui.appcompat.COUIMaterialBlurEffect.access$getArgbEvaluator$cp()
                int r12 = r12.getColorLayer2(r0)
                java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
                int r13 = r13.getColorLayer2(r0)
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                java.lang.Object r12 = r1.evaluate(r15, r12, r13)
                r12.getClass()
                java.lang.Integer r12 = (java.lang.Integer) r12
                int r8 = r12.intValue()
                android.graphics.BlendMode r7 = r14.getBlendModeLayer1()
                android.graphics.BlendMode r9 = r14.getBlendModeLayer2()
                r3 = r10
                r4 = r11
                r3.setViewBlurEffect$coui_support_effect_release(r4, r5, r6, r7, r8, r9)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coui.appcompat.COUIMaterialBlurEffect.Companion.setViewBlurEffect$coui_support_effect_release(android.view.View, com.coui.appcompat.COUIMaterialBlurEffect$BaseBlurEffectParams, com.coui.appcompat.COUIMaterialBlurEffect$BaseBlurEffectParams, com.coui.appcompat.COUIMaterialBlurEffect$BaseBlurEffectParams, float):void");
        }

        private Companion() {
        }

        public final void setViewBlurEffect$coui_support_effect_release(View view, BaseBlurEffectParams params) {
            view.getClass();
            params.getClass();
            Context context = view.getContext();
            context.getClass();
            if (isNoEffect(context, params)) {
                setBackgroundRenderEffect(view, null);
                return;
            }
            Context context2 = view.getContext();
            context2.getClass();
            setViewBlurEffect$coui_support_effect_release(view, params.getBlurRadius(context2), params.getColorLayer1(view.getContext()), params.getBlendModeLayer1(), params.getColorLayer2(view.getContext()), params.getBlendModeLayer2());
        }

        public final void setViewBlurEffect$coui_support_effect_release(View view, BlurEffectType type) {
            view.getClass();
            type.getClass();
            Context context = view.getContext();
            context.getClass();
            setViewBlurEffect$coui_support_effect_release(view, getBlurParamsByType$coui_support_effect_release(context, type));
        }

        public final void setViewBlurEffect$coui_support_effect_release(View view, float blurRadius, int colorLayer1, BlendMode blendModeLayer1, int colorLayer2, BlendMode blendModeLayer2) {
            view.getClass();
            blendModeLayer1.getClass();
            blendModeLayer2.getClass();
            if (COUIMaterialEffect.canApplyBackgroundRenderEffect()) {
                RenderEffect renderEffectCreateBlurEffect = RenderEffect.createBlurEffect(blurRadius, blurRadius, Shader.TileMode.MIRROR);
                renderEffectCreateBlurEffect.getClass();
                RenderEffect renderEffectCreateColorFilterEffect = RenderEffect.createColorFilterEffect(new BlendModeColorFilter(colorLayer1, blendModeLayer1), renderEffectCreateBlurEffect);
                renderEffectCreateColorFilterEffect.getClass();
                RenderEffect renderEffectCreateColorFilterEffect2 = RenderEffect.createColorFilterEffect(new BlendModeColorFilter(colorLayer2, blendModeLayer2), renderEffectCreateColorFilterEffect);
                renderEffectCreateColorFilterEffect2.getClass();
                setBackgroundRenderEffect(view, renderEffectCreateColorFilterEffect2);
            }
        }
    }
}
