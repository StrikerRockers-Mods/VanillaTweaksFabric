package io.github.strikerrocker.vt.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockBreakCallback {
    Event<BlockBreakCallback> EVENT = EventFactory.createArrayBacked(BlockBreakCallback.class,
            (listeners) -> ((world, pos, blockState, player) -> {
                for (BlockBreakCallback blockBreakCallback : listeners) {
                    blockBreakCallback.onBreak(world, pos, blockState, player);
                }
            })
    );

    void onBreak(World world, BlockPos pos, BlockState blockState, PlayerEntity playerEntity);
}