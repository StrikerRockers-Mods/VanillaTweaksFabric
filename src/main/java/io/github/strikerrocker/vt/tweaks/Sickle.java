package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.*;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class Sickle extends Feature {

    private static boolean canHarvest(BlockState state) {
        Block block = state.getBlock();
        return (block instanceof PlantBlock && !(block instanceof LilyPadBlock)) || block instanceof SugarCaneBlock;
    }

    @Override
    public void initialize() {
        AttackBlockCallback.EVENT.register(((player, world, hand, blockPos, direction) -> {
            ItemStack stack = player.getStackInHand(hand);
            if (!stack.isEmpty() && stack.getItem() instanceof HoeItem && canHarvest(world.getBlockState(blockPos)) && VanillaTweaks.config.tweaks.hoeActsAsSickle) {
                int range = 1;
                if (stack.getItem() == Items.DIAMOND_HOE)
                    range++;
                if (stack.getItem() == Items.NETHERITE_HOE)
                    range += 2;
                for (int i = -range; i < range + 1; i++) {
                    for (int k = -range; k < range + 1; k++) {
                        if (i == 0 && k == 0)
                            continue;
                        BlockPos pos = blockPos.add(i, 0, k);
                        BlockState state = world.getBlockState(pos);
                        if (canHarvest(state)) {
                            Block block = state.getBlock();
                            if (player.canHarvest(state))
                                block.afterBreak(world, player, pos, state, world.getBlockEntity(pos), stack);
                            world.setBlockState(pos, Blocks.AIR.getDefaultState());
                            world.playSound(player, player.getBlockPos(), block.getSoundGroup(state).getBreakSound(), SoundCategory.BLOCKS, 1f, 1f);
                        }
                    }
                }
                stack.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
            }
            return ActionResult.PASS;
        }));
    }
}
