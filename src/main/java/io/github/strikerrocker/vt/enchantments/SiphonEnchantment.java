package io.github.strikerrocker.vt.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

public class SiphonEnchantment extends Enchantment {
    SiphonEnchantment() {
        super(Weight.UNCOMMON, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinimumPower(int level) {
        return 15;
    }

    @Override
    public int getMaximumPower(int level) {
        return 61;
    }

    @Override
    public int getMaximumLevel() {
        return EnchantmentModule.config.enableSiphon ? 1 : 0;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ToolItem && EnchantmentModule.config.enableSiphon;
    }

    /*private static class SiphonModifier extends LootModifier {
        public SiphonModifier(ILootCondition[] conditionsIn) {
            super(conditionsIn);
        }

        @Nonnull
        @Override
        public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
            Entity e = context.get(LootParameters.THIS_ENTITY);
            if (e instanceof PlayerEntity)
                generatedLoot.removeIf(((PlayerEntity) e)::addItemStackToInventory);
            return generatedLoot;
        }
    }

    public static class Serializer extends GlobalLootModifierSerializer<SiphonEnchantment.SiphonModifier> {
        @Override
        public SiphonEnchantment.SiphonModifier read(ResourceLocation name, JsonObject json, ILootCondition[] conditionsIn) {
            return new SiphonEnchantment.SiphonModifier(conditionsIn);
        }
    }*/
}
