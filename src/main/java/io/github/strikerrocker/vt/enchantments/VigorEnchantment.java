package io.github.strikerrocker.vt.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class VigorEnchantment extends Enchantment {
    private static final UUID vigorUUID = UUID.fromString("18339f34-6ab5-461d-a103-9b9a3ac3eec7");

    VigorEnchantment() {
        super(Weight.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    /*@SubscribeEvent
    public void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        if (EnchantmentFeature.enableVigor.get()) {
            int lvl = EnchantmentHelper.getEnchantmentLevel(this, event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.CHEST));
            IAttributeInstance vigorAttribute = event.getEntityLiving().getAttributes().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
            AttributeModifier vigorModifier = new AttributeModifier(vigorUUID, "vigor", (float) lvl / 10, AttributeModifier.Operation.MULTIPLY_BASE);
            if (lvl > 0) {
                if (vigorAttribute.getModifier(vigorUUID) == null) {
                    vigorAttribute.applyModifier(vigorModifier);
                }
            } else {
                if (vigorAttribute.getModifier(vigorUUID) != null) {
                    vigorAttribute.removeModifier(vigorModifier);
                    if (event.getEntityLiving().getHealth() > event.getEntityLiving().getMaxHealth())
                        event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());
                }
            }
        }
    }*/

    @Override
    public int getMinimumPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaximumPower(int level) {
        return level * 10 + 51;
    }

    @Override
    public int getMaximumLevel() {
        return EnchantmentModule.config.enableVigor ? 3 : 0;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getSlotType().equals(EquipmentSlot.CHEST) && EnchantmentModule.config.enableVigor;
    }
}
