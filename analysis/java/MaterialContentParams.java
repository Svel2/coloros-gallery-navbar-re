package com.oplus.aiunit.vision;

/* JADX INFO: loaded from: /tmp/re/gallery/dex/classes.dex */
public final class su40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1678a;
    public final int b;
    public final float c;
    public final int d;
    public final int e;

    public su40(int i, int i2, float f, int i3, int i4) {
        this.f1678a = i;
        this.b = i2;
        this.c = f;
        this.d = i3;
        this.e = i4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof su40)) {
            return false;
        }
        su40 su40Var = (su40) obj;
        return this.f1678a == su40Var.f1678a && this.b == su40Var.b && Float.compare(this.c, su40Var.c) == 0 && this.d == su40Var.d && this.e == su40Var.e;
    }

    public final int hashCode() {
        return Integer.hashCode(this.e) + at4.a(this.d, z2b.a(this.c, at4.a(this.b, Integer.hashCode(this.f1678a) * 31, 31), 31), 31);
    }

    public final String toString() {
        StringBuilder sbA = npr.a(this.f1678a, this.b, "ShapeParams(width=", ", height=", ", cornerRadiusPx=");
        dl7.a(this.c, this.d, ", drawOffsetX=", ", drawOffsetY=", sbA);
        return d7q.a(")", this.e, sbA);
    }
}
