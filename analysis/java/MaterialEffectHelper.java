package com.oplus.gallery.basebiz.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import com.coui.appcompat.bottomfloatingaccessorynavigationbar.COUIBottomFloatingAccessoryView;
import com.coui.appcompat.bottomfloatingnavigationbar.BaseBottomFloatingMenuView;
import com.coui.appcompat.bottomfloatingtoolbar.COUIBottomFloatingToolbarItemView;
import com.coui.appcompat.contextutil.COUIContextUtil;
import com.coui.appcompat.toolbar.COUIActionMenuItemView;
import com.coui.appcompat.uiutil.ShadowUtils;
import com.oplus.aiunit.vision.dr4;
import com.oplus.aiunit.vision.fbo;
import com.oplus.aiunit.vision.fpj;
import com.oplus.aiunit.vision.g9d0;
import com.oplus.aiunit.vision.guk;
import com.oplus.aiunit.vision.rh50;
import com.oplus.aiunit.vision.ss4;
import com.oplus.aiunit.vision.su40;
import com.oplus.gallery.basebiz.R$color;
import com.support.appcompat.R;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.WeakHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.ArraysKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f2402a = new c();
    public static final WeakHashMap<TextView, Integer> b = new WeakHashMap<>();
    public static final Lazy c = LazyKt.lazy(new fpj());
    public static final WeakHashMap<View, guk> d = new WeakHashMap<>();

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f2403a;
        public final boolean b;
        public final boolean c;

        public a(boolean z, boolean z2, boolean z3) {
            this.f2403a = z;
            this.b = z2;
            this.c = z3;
        }

        public final boolean a() {
            return !this.f2403a && this.b;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f2403a == aVar.f2403a && this.b == aVar.b && this.c == aVar.c;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.c) + ss4.a(Boolean.hashCode(this.f2403a) * 31, 31, this.b);
        }

        public final String toString() {
            return dr4.a(rh50.a("FallbackPolicy(isSystemManaged=", this.f2403a, ", enableBlendBlur=", this.b, ", enableGradientStroke="), this.c, ")");
        }
    }

    /* JADX INFO: renamed from: com.oplus.gallery.basebiz.widget.c$c, reason: collision with other inner class name */
    public static final /* synthetic */ class C0013c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2404a;

        static {
            int[] iArr = new int[MaterialContentAlignment.values().length];
            try {
                iArr[MaterialContentAlignment.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MaterialContentAlignment.ALIGN_LAYOUT_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f2404a = iArr;
        }
    }

    public static void a(View view, su40 su40Var) {
        int i;
        int i2 = su40Var.f1678a;
        if (i2 <= 0 || (i = su40Var.b) <= 0) {
            return;
        }
        view.setOutlineProvider(new d(su40Var, RangesKt.coerceIn(su40Var.c, 0.0f, Math.min(i2, i) / 2.0f)));
        view.setClipToOutline(true);
    }

    public static void b(c cVar, View view, su40 su40Var, MaterialContentAlignment materialContentAlignment, float f, int i) {
        if ((i & 4) != 0) {
            materialContentAlignment = MaterialContentAlignment.CENTER;
        }
        if ((i & 16) != 0) {
            f = 1.0f;
        }
        cVar.getClass();
        materialContentAlignment.getClass();
        if (view == null) {
            return;
        }
        float fCoerceIn = RangesKt.coerceIn(f, 0.0f, 1.0f);
        c(view);
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            view.setAlpha(fCoerceIn);
            return;
        }
        int iCoerceIn = RangesKt.coerceIn(su40Var.f1678a, 1, width);
        int iCoerceIn2 = RangesKt.coerceIn(su40Var.b, 1, height);
        su40 su40Var2 = new su40(iCoerceIn, iCoerceIn2, RangesKt.coerceIn(su40Var.c, 0.0f, Math.min(iCoerceIn, iCoerceIn2) / 2.0f), d(view, iCoerceIn, materialContentAlignment), e(view, iCoerceIn2, materialContentAlignment));
        view.setBackground(new ColorDrawable(COUIContextUtil.getAttrColor(view.getContext(), R.attr.couiColorBar)));
        a(view, su40Var2);
        view.setAlpha(fCoerceIn);
        view.invalidate();
    }

    public static void c(View view) {
        if (view != null) {
            f2402a.getClass();
            guk gukVarRemove = d.remove(view);
            if (gukVarRemove != null) {
                gukVarRemove.b(1.0f, false);
            }
            Lazy lazy = g9d0.f685a;
            g9d0.a(view);
            view.setBackground(null);
            view.setForeground(null);
            ShadowUtils.clearShadow(view);
            view.setOutlineProvider(null);
        }
    }

    public static int d(View view, int i, MaterialContentAlignment materialContentAlignment) {
        view.getClass();
        materialContentAlignment.getClass();
        int i2 = C0013c.f2404a[materialContentAlignment.ordinal()];
        if (i2 == 1) {
            return RangesKt.coerceAtLeast((view.getWidth() - i) / 2, 0);
        }
        if (i2 != 2) {
            fbo.a();
            return 0;
        }
        if (view.getLayoutDirection() == 1) {
            return RangesKt.coerceAtLeast(view.getWidth() - i, 0);
        }
        return 0;
    }

    public static int e(View view, int i, MaterialContentAlignment materialContentAlignment) {
        view.getClass();
        materialContentAlignment.getClass();
        int i2 = C0013c.f2404a[materialContentAlignment.ordinal()];
        if (i2 == 1) {
            return RangesKt.coerceAtLeast((view.getHeight() - i) / 2, 0);
        }
        if (i2 != 2) {
            fbo.a();
            return 0;
        }
        if (view.getLayoutDirection() == 1) {
            return RangesKt.coerceAtLeast(view.getHeight() - i, 0);
        }
        return 0;
    }

    public static a f() {
        return (a) c.getValue();
    }

    public static su40 g(View view, Integer num, Integer num2, Float f, MaterialContentAlignment materialContentAlignment) {
        view.getClass();
        materialContentAlignment.getClass();
        int iIntValue = num != null ? num.intValue() : view.getWidth();
        int iIntValue2 = num2 != null ? num2.intValue() : view.getHeight();
        int iD = d(view, iIntValue, materialContentAlignment);
        int iE = e(view, iIntValue2, materialContentAlignment);
        float fCoerceIn = 0.0f;
        if (iIntValue > 0 && iIntValue2 > 0) {
            fCoerceIn = RangesKt.coerceIn(f != null ? f.floatValue() : Math.min(iIntValue, iIntValue2) / 2.0f, 0.0f, Math.min(iIntValue, iIntValue2) / 2.0f);
        }
        return new su40(iIntValue, iIntValue2, fCoerceIn, iD, iE);
    }

    public static /* synthetic */ su40 h(c cVar, View view, Integer num, Integer num2, Float f, MaterialContentAlignment materialContentAlignment, int i) {
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            num2 = null;
        }
        if ((i & 8) != 0) {
            f = null;
        }
        if ((i & 16) != 0) {
            materialContentAlignment = MaterialContentAlignment.CENTER;
        }
        cVar.getClass();
        return g(view, num, num2, f, materialContentAlignment);
    }

    public static void i(c cVar, View view, float f, su40 su40Var, int i) {
        View view2;
        Drawable drawable;
        boolean z;
        if ((i & 2) != 0) {
            f = 1.0f;
        }
        if ((i & 4) != 0) {
            su40Var = null;
        }
        MaterialContentAlignment materialContentAlignment = MaterialContentAlignment.CENTER;
        cVar.getClass();
        view.getClass();
        materialContentAlignment.getClass();
        a aVarF = f();
        float fCoerceIn = RangesKt.coerceIn(f, 0.0f, 1.0f);
        if (su40Var == null) {
            view2 = view;
            su40Var = h(cVar, view2, null, null, null, materialContentAlignment, 14);
        } else {
            view2 = view;
        }
        int i2 = su40Var.b;
        int i3 = su40Var.f1678a;
        if (aVarF.f2403a) {
            view2.setAlpha(fCoerceIn);
            return;
        }
        if (!aVarF.a()) {
            view2.setAlpha(fCoerceIn);
            return;
        }
        if (aVarF.b && i3 > 0 && i2 > 0) {
            Lazy lazy = g9d0.f685a;
            g9d0.j(view2, fCoerceIn, R$color.base_blur_button_color, R$color.base_blur_disable_button_color, false);
        }
        if (aVarF.c) {
            int iCoerceIn = (int) (RangesKt.coerceIn(fCoerceIn, 0.0f, 1.0f) * 255.0f);
            if (i3 <= 0 || i2 <= 0) {
                b foreground = view2.getForeground();
                b bVar = foreground instanceof b ? foreground : null;
                if (bVar != null) {
                    bVar.setAlpha(iCoerceIn);
                }
            } else {
                WeakHashMap<View, guk> weakHashMap = d;
                guk gukVar = weakHashMap.get(view2);
                if (gukVar == null) {
                    Context context = view2.getContext();
                    context.getClass();
                    gukVar = new guk(context);
                    weakHashMap.put(view2, gukVar);
                }
                guk gukVar2 = gukVar;
                gukVar2.d(i3, i2, Float.valueOf(su40Var.c));
                gukVar2.b(RangesKt.coerceIn(fCoerceIn, 0.0f, 1.0f), true);
                b foreground2 = view2.getForeground();
                b bVar2 = foreground2 instanceof b ? foreground2 : null;
                if (bVar2 != null) {
                    bVar2.b = su40Var;
                    bVar2.invalidateSelf();
                } else {
                    bVar2 = new b();
                    bVar2.a = gukVar2;
                    bVar2.b = su40Var;
                    VarHandle.storeStoreFence();
                    view2.setForeground(bVar2);
                }
                bVar2.setAlpha(iCoerceIn);
            }
        }
        float fCoerceIn2 = RangesKt.coerceIn(fCoerceIn, 0.0f, 1.0f);
        if (view2 instanceof COUIActionMenuItemView) {
            COUIActionMenuItemView cOUIActionMenuItemView = (COUIActionMenuItemView) view2;
            if (cOUIActionMenuItemView.isTextMenuItem()) {
                float fCoerceIn3 = RangesKt.coerceIn(fCoerceIn2, 0.0f, 1.0f);
                int currentTextColor = cOUIActionMenuItemView.getCurrentTextColor();
                WeakHashMap<TextView, Integer> weakHashMap2 = b;
                if (fCoerceIn3 >= 1.0f) {
                    weakHashMap2.put(cOUIActionMenuItemView, Integer.valueOf(currentTextColor));
                    cOUIActionMenuItemView.setAlpha(1.0f);
                } else {
                    Integer num = weakHashMap2.get(cOUIActionMenuItemView);
                    if (num != null) {
                        currentTextColor = num.intValue();
                    } else {
                        weakHashMap2.put(cOUIActionMenuItemView, Integer.valueOf(currentTextColor));
                    }
                    cOUIActionMenuItemView.setTextColor(Color.argb(RangesKt.coerceIn((int) (fCoerceIn3 * 255.0f), 0, 255), Color.red(currentTextColor), Color.green(currentTextColor), Color.blue(currentTextColor)));
                }
            } else {
                int iCoerceIn2 = RangesKt.coerceIn((int) (RangesKt.coerceIn(fCoerceIn2, 0.0f, 1.0f) * 255.0f), 0, 255);
                Drawable[] compoundDrawablesRelative = cOUIActionMenuItemView.getCompoundDrawablesRelative();
                compoundDrawablesRelative.getClass();
                int length = compoundDrawablesRelative.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        z = false;
                        break;
                    } else {
                        if (compoundDrawablesRelative[i4] != null) {
                            z = true;
                            break;
                        }
                        i4++;
                    }
                }
                if (!z) {
                    compoundDrawablesRelative = cOUIActionMenuItemView.getCompoundDrawables();
                    compoundDrawablesRelative.getClass();
                }
                int length2 = compoundDrawablesRelative.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length2) {
                        i5 = -1;
                        break;
                    } else if (compoundDrawablesRelative[i5] != null) {
                        break;
                    } else {
                        i5++;
                    }
                }
                Drawable drawable2 = (Drawable) ArraysKt.getOrNull(compoundDrawablesRelative, i5);
                if (drawable2 == null) {
                    cOUIActionMenuItemView.setAlpha(fCoerceIn2);
                } else {
                    Drawable drawableMutate = drawable2.mutate();
                    drawableMutate.getClass();
                    drawableMutate.setAlpha(iCoerceIn2);
                    if (drawableMutate == drawable2) {
                        cOUIActionMenuItemView.invalidate();
                    } else {
                        Drawable[] drawableArr = (Drawable[]) Arrays.copyOf(compoundDrawablesRelative, compoundDrawablesRelative.length);
                        drawableArr[i5] = drawableMutate;
                        Drawable drawable3 = drawableArr[0];
                        Drawable drawable4 = drawableArr[1];
                        Drawable drawable5 = drawableArr[2];
                        Drawable drawable6 = drawableArr[3];
                        if (z) {
                            cOUIActionMenuItemView.setCompoundDrawablesRelative(drawable3, drawable4, drawable5, drawable6);
                        } else {
                            cOUIActionMenuItemView.setCompoundDrawables(drawable3, drawable4, drawable5, drawable6);
                        }
                    }
                }
            }
        } else {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if ((layoutParams instanceof ActionMenuView.LayoutParams) && ((ActionMenuView.LayoutParams) layoutParams).isOverflowButton) {
                int iCoerceIn3 = RangesKt.coerceIn((int) (RangesKt.coerceIn(fCoerceIn2, 0.0f, 1.0f) * 255.0f), 0, 255);
                ImageView imageView = view2 instanceof ImageView ? (ImageView) view2 : null;
                if (imageView == null || (drawable = imageView.getDrawable()) == null) {
                    view2.setAlpha(fCoerceIn2);
                } else {
                    Drawable drawableMutate2 = drawable.mutate();
                    drawableMutate2.getClass();
                    drawableMutate2.setAlpha(iCoerceIn3);
                    if (drawableMutate2 != drawable) {
                        ((ImageView) view2).setImageDrawable(drawableMutate2);
                    } else {
                        view2.invalidate();
                    }
                }
            } else if (view2 instanceof BaseBottomFloatingMenuView) {
                BaseBottomFloatingMenuView baseBottomFloatingMenuView = (BaseBottomFloatingMenuView) view2;
                float fCoerceIn4 = RangesKt.coerceIn(fCoerceIn2, 0.0f, 1.0f);
                int childCount = baseBottomFloatingMenuView.getChildCount();
                for (int i6 = 0; i6 < childCount; i6++) {
                    View childAt = baseBottomFloatingMenuView.getChildAt(i6);
                    if (childAt instanceof COUIBottomFloatingToolbarItemView) {
                        COUIBottomFloatingToolbarItemView cOUIBottomFloatingToolbarItemView = (COUIBottomFloatingToolbarItemView) childAt;
                        if (cOUIBottomFloatingToolbarItemView.getVisibility() == 0) {
                            cOUIBottomFloatingToolbarItemView.setAlpha(fCoerceIn4);
                        }
                    }
                }
            } else if (view2 instanceof COUIBottomFloatingAccessoryView) {
                COUIBottomFloatingAccessoryView cOUIBottomFloatingAccessoryView = (COUIBottomFloatingAccessoryView) view2;
                int iCoerceIn4 = RangesKt.coerceIn((int) (RangesKt.coerceIn(fCoerceIn2, 0.0f, 1.0f) * 255.0f), 0, 255);
                Drawable iconDrawable = cOUIBottomFloatingAccessoryView.getIconDrawable();
                if (iconDrawable != null) {
                    iconDrawable.setAlpha(iCoerceIn4);
                }
                cOUIBottomFloatingAccessoryView.invalidate();
            } else {
                view2.setAlpha(fCoerceIn2);
            }
        }
        a(view2, su40Var);
    }
}
