package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class NimbleEnchantment extends Enchantment {

    NimbleEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    public int getMinCost(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxCost(int level) {
        return level * 10 + 51;
    }

    @Override
    public int getMaxLevel() {
        return VanillaTweaks.config.enchanting.enableNimble ? 3 : 0;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem armorItem && armorItem.getSlot().equals(EquipmentSlot.FEET) &&
                VanillaTweaks.config.enchanting.enableNimble;
    }

    @Override
    public boolean isDiscoverable() {
        return VanillaTweaks.config.enchanting.enableNimble;
    }


    @Override
    public boolean isTreasureOnly() {
        return VanillaTweaks.config.enchanting.nimbleTreasureOnly;
    }

    @Override
    public boolean isTradeable() {
        return VanillaTweaks.config.enchanting.enableNimble;
    }
}