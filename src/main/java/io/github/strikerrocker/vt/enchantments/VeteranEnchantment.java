package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class VeteranEnchantment extends Enchantment {
    VeteranEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

    public static void attemptToMove(Entity entity) {
        double range = 32;
        PlayerEntity closestPlayer = entity.world.getClosestPlayer(entity, range);
        if (closestPlayer != null && EnchantmentHelper.getLevel(EnchantmentModule.VETERAN, closestPlayer.getEquippedStack(EquipmentSlot.HEAD)) > 0) {
            double xDiff = (closestPlayer.getPos().getX() - entity.getPos().getX()) / range;
            double yDiff = (closestPlayer.getPos().getY() + closestPlayer.getStandingEyeHeight() - entity.getPos().getY()) / range;
            double zDiff = (closestPlayer.getPos().getZ() - entity.getPos().getZ()) / range;
            double movementFactor = Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
            double invertedMovementFactor = 1 - movementFactor;
            if (invertedMovementFactor > 0) {
                invertedMovementFactor *= invertedMovementFactor;
                Vec3d motion = entity.getVelocity();
                entity.setVelocity(motion.x + xDiff / movementFactor * invertedMovementFactor * 0.3,
                        motion.y + yDiff / movementFactor * invertedMovementFactor * 0.3,
                        motion.z + zDiff / movementFactor * invertedMovementFactor * 0.3);
            }
        }
    }

    @Override
    public int getMinPower(int level) {
        return 10;
    }

    @Override
    public int getMaxPower(int level) {
        return 40;
    }

    @Override
    public int getMaxLevel() {
        return VanillaTweaks.config.enchanting.enableVeteran ? 1 : 0;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem armorItem && armorItem.getSlotType().equals(EquipmentSlot.HEAD) &&
                VanillaTweaks.config.enchanting.enableVeteran;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return VanillaTweaks.config.enchanting.enableVeteran;
    }

    @Override
    public boolean isTreasure() {
        return VanillaTweaks.config.enchanting.veteranTreasureOnly;
    }
}
