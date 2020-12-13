package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

public class HomingEnchantment extends Enchantment {
    /*private AxisAlignedBB ZERO_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);*/

    HomingEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return (level - 1) * 10 + 10;
    }

    @Override
    public int getMaxPower(int level) {
        return level * 10 + 51;
    }

    @Override
    public int getMaxLevel() {
        return VanillaTweaks.config.enchanting.enableHoming ? 3 : 0;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof BowItem && VanillaTweaks.config.enchanting.enableHoming;
    }

    /*@SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.world != null && !event.world.isRemote() && EnchantmentFeature.enableHoming.get()) {
            ServerWorld world = (ServerWorld) event.world;
            world.getEntities(EntityType.ARROW, EntityPredicates.IS_ALIVE).forEach(entity -> attemptToMove(entity, world));
        }
    }

    private void attemptToMove(Entity arrowEntity, ServerWorld world) {
        AbstractArrowEntity arrow = (AbstractArrowEntity) arrowEntity;
        LivingEntity shooter = (LivingEntity) arrow.getShooter();
        if (shooter != null && EnchantmentHelper.getEnchantmentLevel(this, shooter.getHeldItemMainhand()) > 0) {
            int homingLevel = EnchantmentHelper.getEnchantmentLevel(this, shooter.getHeldItemMainhand());
            double distance = Math.pow(2, (double) homingLevel - 1) * 32;
            List<Entity> livingEntities = world.getEntities().collect(Collectors.toList());
            LivingEntity target = null;
            for (Entity entity : livingEntities) {
                double distanceToArrow = entity.getDistance(arrow);
                if (entity instanceof LivingEntity && distanceToArrow < distance && shooter.canEntityBeSeen(entity) && !entity.getUniqueID().equals(shooter.getUniqueID())) {
                    distance = distanceToArrow;
                    target = (LivingEntity) entity;
                }
            }
            if (target != null) {
                double x = target.getPosition().getX() - arrow.getPosition().getX();
                double y = target.getBoundingBox().minY + target.getHeight() / 2 - (arrow.getPosition().getY() + arrow.getHeight() / 2);
                double z = target.getPosition().getZ() - arrow.getPosition().getZ();
                arrow.shoot(x, y, z, (float) Math.sqrt(Math.pow(2, arrow.getMotion().x) + Math.pow(2, arrow.getMotion().y) + Math.pow(2, arrow.getMotion().z)), 0);
            }
        }
    }*/
}
