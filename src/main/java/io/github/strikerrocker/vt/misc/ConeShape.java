package io.github.strikerrocker.vt.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class ConeShape {
    private static double max5(double a, double b, double c, double d, double e) {
        return Math.max(Math.max(Math.max(Math.max(a, b), c), d), e);
    }

    private static double min5(double a, double b, double c, double d, double e) {
        return Math.min(Math.min(Math.min(Math.min(a, b), c), d), e);
    }

    public static Box getConeBounds(LivingEntity livingEntity, int enchantmentLvl) {
        double length = Math.pow(2, (double) enchantmentLvl - 1) * 16;// cone length
        float radius = 2;
        float hyp = (float) Math.sqrt(radius * radius + length * length);

        Vec3d p0 = livingEntity.getCameraPosVec(1.0F);

        float y = livingEntity.getYaw();
        float p = livingEntity.getPitch();
        float f = (float) Math.toDegrees(Math.atan2(radius, length));

        Vec3d v0 = (getVectorFromRPY(hyp, y, p, 0 - f));
        Vec3d v1 = (getVectorFromRPY(hyp, y, p, 0 + f));
        Vec3d v2 = (getVectorFromRPY(hyp, y, p - f, 0));
        Vec3d v3 = (getVectorFromRPY(hyp, y, p + f, 0));

        Vec3d q0 = p0.add(v0);
        Vec3d q1 = p0.add(v1);
        Vec3d q2 = p0.add(v2);
        Vec3d q3 = p0.add(v3);

        float mx = (float) max5(p0.x, q0.x, q1.x, q2.x, q3.x);
        float nx = (float) min5(p0.x, q0.x, q1.x, q2.x, q3.x);
        float my = (float) max5(p0.y, q0.y, q1.y, q2.y, q3.y);
        float ny = (float) min5(p0.y, q0.y, q1.y, q2.y, q3.y);
        float mz = (float) max5(p0.z, q0.z, q1.z, q2.z, q3.z);
        float nz = (float) min5(p0.z, q0.z, q1.z, q2.z, q3.z);

        return new Box(mx, my, mz, nx, ny, nz);
    }

    private static Vec3d getVectorFromRPY(double length, double y, double p, double r) {
        y = Math.toRadians(y + 90);
        p = Math.toRadians(180 - p);
        r = Math.toRadians(r + 180);
        double y2 = y + Math.toRadians(90);

        double ll = length * Math.cos(r);
        double ss = length * Math.sin(r);

        double xz = ll * Math.cos(p);
        double yy = ll * Math.sin(p);

        double xl = xz * Math.cos(y);
        double zl = xz * Math.sin(y);
        double xs = ss * Math.cos(y2);
        double zs = ss * Math.sin(y2);

        double xx = xl + xs;
        double zz = zl + zs;

        return new Vec3d(xx, yy, zz);
    }
}
