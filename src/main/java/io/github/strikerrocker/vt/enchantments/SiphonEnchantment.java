package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

import java.util.ArrayList;
import java.util.List;

public class SiphonEnchantment extends Enchantment {
    SiphonEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public static List<ItemStack> siphonLogic(Entity entity, List<ItemStack> dropList) {
        if (entity instanceof PlayerEntity) {
            ArrayList<ItemStack> newDropList = new ArrayList<>(dropList);
            newDropList.removeIf(((PlayerEntity) entity)::giveItemStack);
            return newDropList;
        }
        return dropList;
    }

    @Override
    public int getMinPower(int level) {
        return 15;
    }

    @Override
    public int getMaxPower(int level) {
        return 61;
    }

    @Override
    public int getMaxLevel() {
        return VanillaTweaks.config.enchanting.enableSiphon ? 1 : 0;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ToolItem && VanillaTweaks.config.enchanting.enableSiphon;
    }
}
