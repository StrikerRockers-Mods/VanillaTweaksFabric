package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.events.BlockPlaceCallback;
import net.minecraft.block.Blocks;
import net.minecraft.block.MagmaBlock;
import net.minecraft.block.Material;
import net.minecraft.block.TntBlock;
import net.minecraft.util.math.Direction;

public class TNTIgnition extends Feature {
    @Override
    public void initialize() {
        BlockPlaceCallback.EVENT.register((world, pos, blockState, entity) -> {
            if (!world.isClient() && blockState.getBlock() instanceof TntBlock && VanillaTweaks.config.tweaks.tntIgnition) {
                for (Direction f : Direction.values()) {
                    if (world.getBlockState(pos.offset(f, 1)).getBlock() instanceof MagmaBlock || world.getBlockState(pos.offset(f, 1)).getMaterial() == Material.LAVA) {
                        TntBlock.primeTnt(world, pos);
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
                    }
                }
            }
        });
    }
}
