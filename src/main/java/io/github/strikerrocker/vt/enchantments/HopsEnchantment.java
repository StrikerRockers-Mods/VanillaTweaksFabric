package io.github.strikerrocker.vt.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class HopsEnchantment extends Enchantment {

    HopsEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return level * 10 + 51;
    }

    @Override
    public int getMaxLevel() {
        return EnchantmentModule.config.enableHops ? 3 : 0;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getSlotType().equals(EquipmentSlot.FEET) && EnchantmentModule.config.enableHops;
    }

    /*@SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (EnchantmentFeature.enableHops.get() && !event.getEntity().world.isRemote()) {
            float lvl = (float) EnchantmentHelper.getEnchantmentLevel(this, event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET));
            if (lvl != 0) {
                entity.addVelocity(0, lvl / 10D, 0);
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity playerMP = (ServerPlayerEntity) entity;
                    playerMP.connection.sendPacket(new SEntityVelocityPacket(playerMP));
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (EnchantmentFeature.enableHops.get() && !event.getEntity().world.isRemote()) {
            event.setDistance(event.getDistance() - EnchantmentHelper.getEnchantmentLevel(this, event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET)));
        }
    }*/
}
