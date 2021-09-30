package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BlazingEnchantment extends Enchantment {
    BlazingEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public static List<ItemStack> blazingLogic(ServerWorld world, Entity entity, ItemStack tool, List<ItemStack> dropList) {
        RecipeManager recipeManager = world.getRecipeManager();
        Inventory simpleInv = new SimpleInventory(1);
        ItemStack itemToBeChecked;
        Optional<SmeltingRecipe> smeltingResult;
        ArrayList<ItemStack> newDropList = new ArrayList<>(dropList);
        if (!newDropList.isEmpty())
            for (int i = 0; i < newDropList.size(); i++) {
                itemToBeChecked = newDropList.get(i);
                simpleInv.setStack(0, itemToBeChecked);
                smeltingResult = recipeManager.getFirstMatch(RecipeType.SMELTING, simpleInv, entity.world);
                if (smeltingResult.isPresent() && entity instanceof PlayerEntity playerEntity) {
                    int count = itemToBeChecked.getCount();
                    if (EnchantmentHelper.getLevel(Enchantments.FORTUNE, tool) > 0) {
                        count = getFortuneCount(world.getRandom(), count, EnchantmentHelper.getLevel(Enchantments.FORTUNE, tool));
                        tool.damage(Math.max(count - 1, 3), playerEntity, player -> player.sendToolBreakStatus(player.getActiveHand()));
                    }
                    newDropList.set(i, new ItemStack(smeltingResult.get().getOutput().getItem(), count));
                    playerEntity.addExperience((int) (smeltingResult.get().getExperience() * itemToBeChecked.getCount()));
                }
            }
        return newDropList;
    }

    public static int getFortuneCount(Random random, int initialCount, int lvl) {
        if (lvl > 0) {
            int i = random.nextInt(lvl + 2) - 1;
            if (i < 0) {
                i = 0;
            }
            return initialCount * (i + 1);
        } else {
            return initialCount;
        }
    }

    @Override
    public int getMaxLevel() {
        return VanillaTweaks.config.enchanting.enableBlazing ? 1 : 0;
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
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.SILK_TOUCH;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ToolItem && VanillaTweaks.config.enchanting.enableBlazing;
    }

    @Override
    public boolean isTreasure() {
        return VanillaTweaks.config.enchanting.blazingTreasureOnly;
    }
}