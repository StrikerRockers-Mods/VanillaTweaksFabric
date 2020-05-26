package io.github.strikerrocker.vt.content.items.craftingpad;

import net.minecraft.container.BlockContext;
import net.minecraft.container.CraftingTableContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;

public class CraftingPadContainer extends CraftingTableContainer {

    CraftingPadContainer(int id, PlayerInventory playerInventory, BlockContext blockContext) {
        super(id, playerInventory, blockContext);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
