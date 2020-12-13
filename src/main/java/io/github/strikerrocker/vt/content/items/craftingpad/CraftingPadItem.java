package io.github.strikerrocker.vt.content.items.craftingpad;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CraftingPadItem extends Item {
    public CraftingPadItem() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            user.openHandledScreen(new SimpleNamedScreenHandlerFactory(((syncId, inv, player) -> new CraftingPadScreenHandler(syncId, player.inventory, ScreenHandlerContext.EMPTY)), new TranslatableText("item.vanillatweaks.crafting_pad")));
            return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
    }
}
