package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

public class HomingEnchantment extends Enchantment {

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

    @Override
    public boolean isAvailableForRandomSelection() {
        return VanillaTweaks.config.enchanting.enableHoming;
    }

    @Override
    public boolean isTreasure() {
        return VanillaTweaks.config.enchanting.homingTreasureOnly;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return VanillaTweaks.config.enchanting.enableHoming;
    }
}
