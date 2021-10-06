package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class VigorEnchantment extends Enchantment {

    VigorEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    @Override
    public int getMinPower(int level) {
        return 10 + (level - 1) * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return level * 10 + 51;
    }

    @Override
    public int getMaxLevel() {
        return VanillaTweaks.config.enchanting.enableVigor ? 3 : 0;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem armorItem && armorItem.getSlotType().equals(EquipmentSlot.CHEST) &&
                VanillaTweaks.config.enchanting.enableVigor;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return VanillaTweaks.config.enchanting.enableVigor;
    }

    @Override
    public boolean isTreasure() {
        return VanillaTweaks.config.enchanting.vigorTreasureOnly;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return VanillaTweaks.config.enchanting.enableVigor;
    }
}
