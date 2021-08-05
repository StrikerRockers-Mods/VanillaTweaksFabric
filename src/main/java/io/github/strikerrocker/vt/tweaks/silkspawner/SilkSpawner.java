package io.github.strikerrocker.vt.tweaks.silkspawner;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.events.BlockBreakCallback;
import io.github.strikerrocker.vt.events.BlockPlaceCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;

public class SilkSpawner extends Feature {
    protected static final String SPAWNER_TAG = "SilkSpawnerData";

    @Override
    public void initialize() {
        // Handles spawner block entity placement
        BlockPlaceCallback.EVENT.register((world, pos, blockState, entity) -> {
            if (entity instanceof PlayerEntity playerEntity) {
                ItemStack stack = playerEntity.getActiveItem();
                if (VanillaTweaks.config.tweaks.enableSilkSpawner && !stack.isEmpty() && stack.hasNbt() && stack.getItem() == Items.SPAWNER) {
                    NbtCompound stackTag = stack.getNbt();
                    assert stackTag != null;
                    NbtCompound spawnerDataNBT = stackTag.getCompound(SPAWNER_TAG);
                    if (!spawnerDataNBT.isEmpty()) {
                        BlockEntity tile = world.getBlockEntity(pos);
                        if (tile instanceof MobSpawnerBlockEntity) {
                            ((MobSpawnerBlockEntity) tile).getLogic().readNbt(world, pos, spawnerDataNBT);
                        }
                    }
                }
            }
        });
        //Handles spawner blocking  logic
        BlockBreakCallback.EVENT.register((world, pos, blockState, playerEntity) -> {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            int lvl = EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, playerEntity.getMainHandStack());
            if (blockState.getBlock() instanceof SpawnerBlock && !world.isClient() && blockEntity instanceof MobSpawnerBlockEntity && VanillaTweaks.config.tweaks.enableSilkSpawner && lvl >= 1) {
                ItemStack drop = new ItemStack(Blocks.SPAWNER);
                NbtCompound spawnerData = ((MobSpawnerBlockEntity) blockEntity).getLogic().writeNbt(world, pos, new NbtCompound());
                NbtCompound stackTag = new NbtCompound();
                spawnerData.remove("Delay");
                stackTag.put(SPAWNER_TAG, spawnerData);
                drop.setNbt(stackTag);

                Block.dropStack(playerEntity.getEntityWorld(), pos, drop);
            }
        });
    }
}