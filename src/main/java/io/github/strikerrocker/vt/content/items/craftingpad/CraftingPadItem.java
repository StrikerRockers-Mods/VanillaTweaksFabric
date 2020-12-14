package io.github.strikerrocker.vt.content.items.craftingpad;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CraftingPadItem extends Item {
    public CraftingPadItem() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            user.openHandledScreen(createScreenHandlerFactory(world, user.getBlockPos()));
            return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient);
    }

    private NamedScreenHandlerFactory createScreenHandlerFactory(World world_1, BlockPos blockPos_1) {
        return new SimpleNamedScreenHandlerFactory(
                (int_1, playerInventory_1, playerEntity_1)
                        ->
                        new CraftingPadScreenHandler(int_1, playerInventory_1,
                                ScreenHandlerContext.create(world_1, blockPos_1)),
                new TranslatableText("item.vanillatweaks.crafting_pad")
        );
    }

}
