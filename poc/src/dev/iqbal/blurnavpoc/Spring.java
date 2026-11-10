package dev.iqbal.blurnavpoc;

/**
 * Spring teredam dengan solusi analitik per frame (bukan Euler), jadi stabil untuk
 * response cepat sekalipun — modelnya sama dengan `MorphSpring` di app Swell:
 *
 *   ω = 2π / response,  ζ = 1 − bounce,  damping = 2ζω
 *
 * Dengan Euler eksplisit, spring ber-response 0.10 s di 60 fps (dt·2ζω = 2.0) langsung
 * meledak; karena itu dipakai bentuk tertutup di bawah.
 */
final class Spring {
    /** Pengali tempo global: >1 = semua pegas lebih cepat (response dibagi tempo). */
    static double tempo = 1.0;

    double value;
    double velocity;
    double target;
    boolean active;
    /** ambang "dianggap selesai" (px untuk gerak, kecil untuk alpha 0..1) */
    double tolerance = 0.4;
    /** ambang kecepatan "dianggap selesai" (satuan per detik) */
    double velocityTolerance = 8;

    private double omega = 20;      // rad/s
    private double zeta = 0.8;      // damping ratio

    void configure(double response, double bounce) {
        double r = Math.max(0.02, response / tempo);
        omega = 2.0 * Math.PI / r;
        zeta = Math.max(0.0, Math.min(1.0, 1.0 - bounce));
    }

    void snapTo(double v) {
        value = v;
        target = v;
        velocity = 0;
        active = false;
    }

    void setTarget(double t) {
        target = t;
        active = true;
    }

    /** @return true kalau masih bergerak */
    boolean update(double dt) {
        if (!active) return false;
        dt = Math.max(0.001, Math.min(0.05, dt));

        double displacement = value - target;
        double decay = Math.exp(-zeta * omega * dt);
        if (zeta >= 0.9999) {
            // redaman kritis: v' = (v + ω·x)·e^{-ωt} - ω·x'
            double c = velocity + omega * displacement;
            double nextDisplacement = decay * (displacement + c * dt);
            velocity = decay * (velocity - omega * c * dt);
            value = target + nextDisplacement;
        } else {
            double wd = omega * Math.sqrt(1.0 - zeta * zeta);
            double cos = Math.cos(wd * dt);
            double sin = Math.sin(wd * dt);
            double c = (velocity + zeta * omega * displacement) / wd;
            double nextDisplacement = decay * (displacement * cos + c * sin);
            velocity = decay * (
                    -zeta * omega * (displacement * cos + c * sin)
                            + wd * (-displacement * sin + c * cos));
            value = target + nextDisplacement;
        }

        if (Math.abs(value - target) < tolerance && Math.abs(velocity) < velocityTolerance) {
            value = target;
            velocity = 0;
            active = false;
        }
        return active;
    }
}
