package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

public class SiphonEnchantment extends Enchantment {
    SiphonEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
