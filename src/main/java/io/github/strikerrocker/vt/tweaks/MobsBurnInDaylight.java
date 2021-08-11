package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.events.LivingEntityTickCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MobsBurnInDaylight extends Feature {

    /**
     * Handles burning of baby zombie and creepers in daylight
     */
    @Override
    public void initialize() {
        LivingEntityTickCallback.EVENT.register(livingEntity -> {
            if (((livingEntity instanceof CreeperEntity && VanillaTweaks.config.tweaks.creeperBurnInDaylight) ||
                    (livingEntity instanceof ZombieEntity && livingEntity.isBaby() && VanillaTweaks.config.tweaks.babyZombieBurnInDaylight))) {
                boolean flag = canBurn(livingEntity);
                if (flag) {
                    ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
                    // Damages the helmet if its present
                    if (!itemStack.isEmpty()) {
                        if (itemStack.isDamageable()) {
                            itemStack.setDamage(itemStack.getDamage() + livingEntity.getRandom().nextInt(2));
                            if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                                livingEntity.sendEquipmentBreakStatus(EquipmentSlot.HEAD);
                                livingEntity.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
                            }
                        }
                        flag = false;
                    }
                    if (flag) {
                        livingEntity.setOnFireFor(8);
                    }
                }
            }
        });
    }

    /**
     * Returns if entity can burn
     */
    boolean canBurn(LivingEntity entity) {
        World world = entity.world;
        if (world.isDay() && !world.isClient) {
            float f = entity.getBrightnessAtEyes();
            boolean bl = entity.isWet() || entity.inPowderSnow || entity.wasInPowderSnow;
            return f > 0.5F && entity.getRandom().nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !bl && world.isSkyVisible(entity.getBlockPos());
        }
        return false;
    }
}