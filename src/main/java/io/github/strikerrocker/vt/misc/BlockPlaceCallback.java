package io.github.strikerrocker.vt.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockPlaceCallback {
    Event<BlockPlaceCallback> EVENT = EventFactory.createArrayBacked(BlockPlaceCallback.class,
            (listeners) -> ((world, pos, blockState) -> {
                for (BlockPlaceCallback blockPlaceCallback : listeners) {
                    blockPlaceCallback.onPlaced(world, pos, blockState);
                }
            })
    );

    void onPlaced(World world, BlockPos pos, BlockState blockState);
}
