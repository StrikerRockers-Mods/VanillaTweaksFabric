package io.github.strikerrocker.vt.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockPlaceCallback {
    Event<BlockPlaceCallback> EVENT = EventFactory.createArrayBacked(BlockPlaceCallback.class,
            (listeners) -> ((world, pos, blockState, entity) -> {
                for (BlockPlaceCallback blockPlaceCallback : listeners) {
                    blockPlaceCallback.onPlaced(world, pos, blockState, entity);
                }
            })
    );

    void onPlaced(World world, BlockPos pos, BlockState blockState, LivingEntity entity);
}