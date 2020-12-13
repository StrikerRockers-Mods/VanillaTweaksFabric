package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class VeteranEnchantment extends Enchantment {
    VeteranEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
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
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getSlotType().equals(EquipmentSlot.HEAD) && VanillaTweaks.config.enchanting.enableVeteran;
    }

    /*@SubscribeEvent
    public void onTick(TickEvent.WorldTickEvent event) {
        if (EnchantmentFeature.enableVeteran.get() && event.world != null && !event.world.isRemote()) {
            ServerWorld world = (ServerWorld) event.world;
            world.getEntities(EntityType.EXPERIENCE_ORB, EntityPredicates.IS_ALIVE).forEach(this::attemptToMove);
        }
    }

    private void attemptToMove(Entity entity) {
        double range = 32;
        PlayerEntity closestPlayer = entity.world.getClosestPlayer(entity, range);
        if (closestPlayer != null && EnchantmentHelper.getEnchantmentLevel(this, closestPlayer.getItemStackFromSlot(EquipmentSlotType.HEAD)) > 0) {
            double xDiff = (closestPlayer.getPosition().getX() - entity.getPosition().getX()) / range;
            double yDiff = (closestPlayer.getPosition().getY() + closestPlayer.getEyeHeight() - entity.getPosition().getY()) / range;
            double zDiff = (closestPlayer.getPosition().getZ() - entity.getPosition().getZ()) / range;
            double movementFactor = Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
            double invertedMovementFactor = 1 - movementFactor;
            if (invertedMovementFactor > 0) {
                invertedMovementFactor *= invertedMovementFactor;
                Vec3d motion = entity.getMotion();
                entity.setMotion(motion.x + xDiff / movementFactor * invertedMovementFactor * 0.1, motion.y + yDiff / movementFactor * invertedMovementFactor * 0.1, motion.z + zDiff / movementFactor * invertedMovementFactor * 0.1);
            }
        }
    }*/
}
