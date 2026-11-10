package com.oplus.aiunit.vision;

import com.coloros.gallery3d.app.App;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
public final class pab {
    @JvmStatic
    public static final boolean a(String str) {
        App app = wib.a;
        if (app == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            app = null;
        }
        if (app == null) {
            app = null;
        }
        v8m v8mVarA = app != null ? app.a(v8m.class) : null;
        if (v8mVarA == null) {
            i3j.m("ConfigAbilityWrapper", "contains configAbility is null, configId:".concat(str), (Throwable) null);
            return false;
        }
        try {
            boolean zContains = v8mVarA.contains(str);
            AutoCloseableKt.closeFinally(v8mVarA, (Throwable) null);
            return zContains;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(v8mVarA, th);
                throw th2;
            }
        }
    }

    @JvmStatic
    public static final boolean b(String str, boolean z, boolean z2) {
        str.getClass();
        App app = wib.a;
        if (app == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            app = null;
        }
        if (app == null) {
            app = null;
        }
        v8m v8mVarA = app != null ? app.a(v8m.class) : null;
        if (v8mVarA == null) {
            i3j.m("ConfigAbilityWrapper", "getBoolean configAbility is null, configId:".concat(str), (Throwable) null);
            return z2;
        }
        try {
            Boolean boolW8 = v8mVarA.w8(str, z);
            if (boolW8 != null) {
                z = boolW8.booleanValue();
            }
            AutoCloseableKt.closeFinally(v8mVarA, (Throwable) null);
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(v8mVarA, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ boolean c(int i, String str, boolean z) {
        if ((i & 2) != 0) {
            z = false;
        }
        return b(str, z, false);
    }

    @JvmStatic
    public static final int d(String str, int i) {
        str.getClass();
        App app = wib.a;
        if (app == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            app = null;
        }
        if (app == null) {
            app = null;
        }
        v8m v8mVarA = app != null ? app.a(v8m.class) : null;
        if (v8mVarA != null) {
            try {
                Integer numZ5 = v8mVarA.z5(i, str);
                AutoCloseableKt.closeFinally(v8mVarA, (Throwable) null);
                if (numZ5 != null) {
                    return numZ5.intValue();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(v8mVarA, th);
                    throw th2;
                }
            }
        }
        i3j.m("ConfigAbilityWrapper", "getInt configAbility is null, configId:".concat(str), (Throwable) null);
        return i;
    }

    @JvmStatic
    public static final long e(String str, long j) {
        str.getClass();
        App app = wib.a;
        if (app == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            app = null;
        }
        if (app == null) {
            app = null;
        }
        v8m v8mVarA = app != null ? app.a(v8m.class) : null;
        if (v8mVarA != null) {
            try {
                Long lF9 = v8mVarA.F9(j, str);
                AutoCloseableKt.closeFinally(v8mVarA, (Throwable) null);
                if (lF9 != null) {
                    return lF9.longValue();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(v8mVarA, th);
                    throw th2;
                }
            }
        }
        i3j.m("ConfigAbilityWrapper", "getLong configAbility is null, configId:".concat(str), (Throwable) null);
        return j;
    }

    public static Object f(String str) {
        App app = wib.a;
        if (app == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            app = null;
        }
        if (app == null) {
            app = null;
        }
        v8m v8mVarA = app != null ? app.a(v8m.class) : null;
        if (v8mVarA != null) {
            try {
                Object objC8 = v8mVarA.C8(str);
                AutoCloseableKt.closeFinally(v8mVarA, (Throwable) null);
                if (objC8 != null) {
                    return objC8;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(v8mVarA, th);
                    throw th2;
                }
            }
        }
        i3j.m("ConfigAbilityWrapper", "getObject configAbility is null, configId:".concat(str), (Throwable) null);
        return null;
    }

    @JvmStatic
    public static final String g(String str, String str2) {
        str.getClass();
        App app = wib.a;
        if (app == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            app = null;
        }
        if (app == null) {
            app = null;
        }
        v8m v8mVarA = app != null ? app.a(v8m.class) : null;
        if (v8mVarA != null) {
            try {
                String strG9 = v8mVarA.g9(str, str2);
                AutoCloseableKt.closeFinally(v8mVarA, (Throwable) null);
                if (strG9 != null) {
                    return strG9;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(v8mVarA, th);
                    throw th2;
                }
            }
        }
        i3j.m("ConfigAbilityWrapper", "getString configAbility is null, configId:".concat(str), (Throwable) null);
        return str2;
    }

    @JvmStatic
    public static final void h(String str, y8m y8mVar) {
        str.getClass();
        y8mVar.getClass();
        App app = wib.a;
        if (app == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            app = null;
        }
        if (app == null) {
            app = null;
        }
        v8m v8mVarA = app != null ? app.a(v8m.class) : null;
        if (v8mVarA == null) {
            i3j.m("ConfigAbilityWrapper", "registerListener configAbility is null, configId:".concat(str), (Throwable) null);
            return;
        }
        try {
            v8mVarA.za(str, y8mVar);
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(v8mVarA, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(v8mVarA, th);
                throw th2;
            }
        }
    }

    @JvmStatic
    public static final void i(String str, y8m y8mVar) {
        str.getClass();
        y8mVar.getClass();
        App app = wib.a;
        if (app == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            app = null;
        }
        if (app == null) {
            app = null;
        }
        v8m v8mVarA = app != null ? app.a(v8m.class) : null;
        if (v8mVarA == null) {
            i3j.m("ConfigAbilityWrapper", "unregisterListener configAbility is null, configId:".concat(str), (Throwable) null);
            return;
        }
        try {
            v8mVarA.Ha(str, y8mVar);
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(v8mVarA, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(v8mVarA, th);
                throw th2;
            }
        }
    }
}
