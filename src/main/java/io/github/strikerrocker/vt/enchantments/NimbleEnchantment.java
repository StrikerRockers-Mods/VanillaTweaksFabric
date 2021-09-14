package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class NimbleEnchantment extends Enchantment {

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
        return VanillaTweaks.config.enchanting.enableNimble ? 3 : 0;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem armorItem && armorItem.getSlotType().equals(EquipmentSlot.FEET) &&
                VanillaTweaks.config.enchanting.enableNimble;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return VanillaTweaks.config.enchanting.enableNimble;
    }


    @Override
    public boolean isTreasure() {
        return VanillaTweaks.config.enchanting.nimbleTreasureOnly;
    }
}