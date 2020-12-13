package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

public class BlazingEnchantment extends Enchantment {
    BlazingEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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

    /*private static class BlazingModifier extends LootModifier {
        public BlazingModifier(ILootCondition[] conditionsIn) {
            super(conditionsIn);
        }

        private static ItemStack smelt(ItemStack stack, LootContext context) {
            return context.getWorld().getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(stack), context.getWorld())
                    .map(FurnaceRecipe::getRecipeOutput)
                    .filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);
        }

        @Nonnull
        @Override
        public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(context.get(LootParameters.TOOL));
            if (enchantments.containsKey(Enchantments.FORTUNE) && enchantments.containsKey(EnchantmentFeature.enchantments.get("blazing").getA())) {
                return generatedLoot;
            }
            ArrayList<ItemStack> ret = new ArrayList<>();
            generatedLoot.forEach((stack) -> ret.add(smelt(stack, context)));
            return ret;
        }
    }

    public static class Serializer extends GlobalLootModifierSerializer<BlazingModifier> {
        @Override
        public BlazingModifier read(ResourceLocation name, JsonObject json, ILootCondition[] conditionsIn) {
            return new BlazingModifier(conditionsIn);
        }
    }*/
}
