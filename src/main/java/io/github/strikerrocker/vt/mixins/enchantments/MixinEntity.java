package io.github.strikerrocker.vt.mixins.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.enchantments.EnchantmentModule;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Hops Functionality
 */
@Mixin(Entity.class)
public abstract class MixinEntity {

    @Inject(method = "getJumpVelocityMultiplier", at = @At(value = "RETURN"), cancellable = true)
    private void hops(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof LivingEntity && VanillaTweaks.config.enchanting.enableHops) {
            int lvl = EnchantmentHelper.getLevel(EnchantmentModule.enchantments.get("hops"), ((LivingEntity) (Object) (this)).getEquippedStack(EquipmentSlot.FEET));
            if (lvl > 0) {
                switch (lvl) {
                    case 1:
                        cir.setReturnValue(1.5F);
                        break;
                    case 2:
                        cir.setReturnValue(1.75f);
                        break;
                    case 3:
                        cir.setReturnValue(2f);
                        break;
                    default:
                        break;
                }
                //cir.setReturnValue((float) lvl);
            }
        }
    }
}