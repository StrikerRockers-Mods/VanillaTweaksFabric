package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class Sickle extends Feature {

    /**
     * Returns whether the given BlockState is considered for sickle feature
     */
    private static boolean canHarvest(BlockState state) {
        Block block = state.getBlock();
        return (block instanceof BushBlock && !(block instanceof WaterlilyBlock)) || block instanceof SugarCaneBlock;
    }

    /**
     * Get the range for sickle harvesting based on type of hoe
     */
    private int getRange(Item item) {
        if (item == Items.DIAMOND_HOE)
            return 2;
        else if (item == Items.NETHERITE_HOE)
            return 3;
        else return 1;
    }

    /**
     * Handles crop harvesting more than one block when using hoe
     */
    @Override
    public void initialize() {
        AttackBlockCallback.EVENT.register(((player, world, hand, blockPos, direction) -> {
            ItemStack stack = player.getItemInHand(hand);
            if (!stack.isEmpty() && stack.getItem() instanceof HoeItem && canHarvest(world.getBlockState(blockPos)) &&
                    VanillaTweaks.config.tweaks.hoeActsAsSickle) {
                int range = getRange(stack.getItem());
                for (int i = -range; i < range + 1; i++) {
                    for (int k = -range; k < range + 1; k++) {
                        if (i == 0 && k == 0)
                            continue;
                        BlockPos pos = blockPos.offset(i, 0, k);
                        BlockState state = world.getBlockState(pos);
                        if (canHarvest(state)) {
                            Block block = state.getBlock();
                            if (player.hasCorrectToolForDrops(state))
                                block.playerDestroy(world, player, pos, state, world.getBlockEntity(pos), stack);
                            world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                            world.playSound(player, player.blockPosition(), block.getSoundType(state).getBreakSound(), SoundSource.BLOCKS, 1f, 1f);
                        }
                    }
                }
                stack.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(playerEntity.getUsedItemHand()));
            }
            return InteractionResult.PASS;
        }));
    }
}
