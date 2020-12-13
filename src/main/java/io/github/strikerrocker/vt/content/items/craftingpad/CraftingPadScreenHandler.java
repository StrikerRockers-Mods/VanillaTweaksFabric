package io.github.strikerrocker.vt.content.items.craftingpad;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class CraftingPadScreenHandler extends CraftingScreenHandler {

    CraftingPadScreenHandler(int id, PlayerInventory playerInventory, ScreenHandlerContext screenHandlerContext) {
        super(id, playerInventory, screenHandlerContext);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
