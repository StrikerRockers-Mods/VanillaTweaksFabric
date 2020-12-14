package io.github.strikerrocker.vt.mixins.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.enchantments.EnchantmentModule;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * Homing Functionality
 */
@Mixin(ProjectileEntity.class)
public abstract class MixinProjectileEntity extends Entity {
    public MixinProjectileEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    @Nullable
    public abstract Entity getOwner();

    @Inject(at = @At(value = "INVOKE", target = "net/minecraft/entity/projectile/ProjectileEntity.setVelocity(Lnet/minecraft/util/math/Vec3d;)V", shift = At.Shift.AFTER), method = "setVelocity(DDDFF)V", locals = LocalCapture.CAPTURE_FAILHARD)
    public void homing(double x, double y, double z, float speed, float divergence, CallbackInfo ci,
                       Vec3d vec3d) {
        Vec3d newVec = vec3d;
        if (((ProjectileEntity) (Object) this) instanceof ArrowEntity && VanillaTweaks.config.enchanting.enableHoming) {
            for (ItemStack stack : getOwner().getItemsHand()) {
                int lvl = EnchantmentHelper.getLevel(EnchantmentModule.enchantments.get("homing"), stack);
                if (lvl > 0) {
                    double accuracy = (lvl == 1 ? 0.3D : lvl == 2 ? 0.6D : 1);
                    double magicNumber = 0.007499999832361937D * accuracy;
                    newVec = new Vec3d(x, y, z).normalize()
                            .add(this.random.nextGaussian() * magicNumber * divergence,
                                    this.random.nextGaussian() * magicNumber * divergence,
                                    this.random.nextGaussian() * magicNumber * divergence)
                            .multiply(speed);
                    return;
                }
            }
            this.setVelocity(newVec);
        }
    }
}