package io.github.strikerrocker.vt.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class NimbleEnchantment extends Enchantment {

    private static final UUID nimbleUUID = UUID.fromString("05b61a62-ae84-492e-8536-f365b7143296");

    NimbleEnchantment() {
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
        return EnchantmentModule.config.enableNimble ? 3 : 0;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getSlotType().equals(EquipmentSlot.FEET) && EnchantmentModule.config.enableNimble;
    }

    /*@SubscribeEvent
    public void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        if (EnchantmentFeature.enableNimble.get()) {
            LivingEntity entity = event.getEntityLiving();
            int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(this, entity.getItemStackFromSlot(EquipmentSlotType.FEET));
            IAttributeInstance speedAttribute = entity.getAttributes().getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED);
            AttributeModifier speedModifier = new AttributeModifier(nimbleUUID, "Nimble", (float) enchantmentLevel * 0.20000000298023224, AttributeModifier.Operation.MULTIPLY_TOTAL);
            if (enchantmentLevel > 0) {
                if (speedAttribute.getModifier(nimbleUUID) == null) {
                    speedAttribute.applyModifier(speedModifier);
                }
            } else if (speedAttribute.getModifier(nimbleUUID) != null) {
                speedAttribute.removeModifier(speedModifier);
            }
        }
    }*/
}