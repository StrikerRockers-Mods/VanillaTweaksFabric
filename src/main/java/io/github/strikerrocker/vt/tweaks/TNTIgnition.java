package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.events.BlockPlaceCallback;
import net.minecraft.block.Blocks;
import net.minecraft.block.MagmaBlock;
import net.minecraft.block.Material;
import net.minecraft.block.TntBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class TNTIgnition extends Feature {
    /**
     * Explode TNT when it is beside lava or magma block
     */
    @Override
    public void initialize() {
        BlockPlaceCallback.EVENT.register((world, pos, blockState, entity, stack) -> {
            if (!world.isClient() && VanillaTweaks.config.tweaks.tntIgnition) {
                if (blockState.getBlock() instanceof TntBlock) {
                    for (Direction f : Direction.values()) {
                        if (world.getBlockState(pos.offset(f)).getBlock() instanceof MagmaBlock || world.getBlockState(pos.offset(f)).getMaterial() == Material.LAVA) {
                            TntBlock.primeTnt(world, pos);
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
                        }
                    }
                } else if (blockState.getBlock() instanceof MagmaBlock) {
                    for (Direction f : Direction.values()) {
                        BlockPos offsetPos = pos.offset(f);
                        if (world.getBlockState(offsetPos).getBlock() instanceof TntBlock) {
                            TntBlock.primeTnt(world, offsetPos);
                            world.setBlockState(offsetPos, Blocks.AIR.getDefaultState(), 11);
                        }
                    }
                }
            }
        });
    }
}
