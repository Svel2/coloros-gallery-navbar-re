package com.oplus.aiunit.vision;

import com.coui.appcompat.COUIMaterialStateManager;
import com.oplus.gallery.basebiz.widget.c;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
public final /* synthetic */ class fpj implements Function0 {
    public final Object invoke() {
        boolean zMaterialVersionCheck = COUIMaterialStateManager.INSTANCE.materialVersionCheck();
        c.a aVar = zMaterialVersionCheck ? new c.a(true, false, false) : new c.a(false, pab.b("feature_is_background_blur_supported", false, false), pab.b("feature_is_gradient_stroke_supported", false, false));
        gpj gpjVar = new gpj();
        gpjVar.f733a = zMaterialVersionCheck;
        gpjVar.b = aVar;
        VarHandle.storeStoreFence();
        i3j.e(gpjVar, 3, "COUIMaterialFallback");
        return aVar;
    }
}
