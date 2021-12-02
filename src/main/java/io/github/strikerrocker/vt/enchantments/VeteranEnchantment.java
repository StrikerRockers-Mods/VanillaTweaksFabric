package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;

public class VeteranEnchantment extends Enchantment {
    VeteranEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

    public static void attemptToMove(Entity entity) {
        double range = 32;
        Player closestPlayer = entity.level.getNearestPlayer(entity, range);
        if (closestPlayer != null && EnchantmentHelper.getItemEnchantmentLevel(EnchantmentModule.VETERAN, closestPlayer.getItemBySlot(EquipmentSlot.HEAD)) > 0) {
            double xDiff = (closestPlayer.position().x() - entity.position().x()) / range;
            double yDiff = (closestPlayer.position().y() + closestPlayer.getEyeHeight() - entity.position().y()) / range;
            double zDiff = (closestPlayer.position().z() - entity.position().z()) / range;
            double movementFactor = Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
            double invertedMovementFactor = 1 - movementFactor;
            if (invertedMovementFactor > 0) {
                invertedMovementFactor *= invertedMovementFactor;
                Vec3 motion = entity.getDeltaMovement();
                entity.setDeltaMovement(motion.x + xDiff / movementFactor * invertedMovementFactor * 0.3,
                        motion.y + yDiff / movementFactor * invertedMovementFactor * 0.3,
                        motion.z + zDiff / movementFactor * invertedMovementFactor * 0.3);
            }
        }
    }

    @Override
    public int getMinCost(int level) {
        return 10;
    }

    @Override
    public int getMaxCost(int level) {
        return 40;
    }

    @Override
    public int getMaxLevel() {
        return VanillaTweaks.config.enchanting.enableVeteran ? 1 : 0;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem armorItem && armorItem.getSlot().equals(EquipmentSlot.HEAD) &&
                VanillaTweaks.config.enchanting.enableVeteran;
    }

    @Override
    public boolean isDiscoverable() {
        return VanillaTweaks.config.enchanting.enableVeteran;
    }

    @Override
    public boolean isTreasureOnly() {
        return VanillaTweaks.config.enchanting.veteranTreasureOnly;
    }

    @Override
    public boolean isTradeable() {
        return VanillaTweaks.config.enchanting.enableVeteran;
    }
}
