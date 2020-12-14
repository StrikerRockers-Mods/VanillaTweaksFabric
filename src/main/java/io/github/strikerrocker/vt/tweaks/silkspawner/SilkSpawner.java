package io.github.strikerrocker.vt.tweaks.silkspawner;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.misc.BlockBreakCallback;
import io.github.strikerrocker.vt.misc.BlockPlaceCallback;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class SilkSpawner extends Feature {
    private static final String SPAWNER_TAG = "SilkSpawnerData";
    private static Item mobSpawnerItem = null;

    @Override
    public void initialize() {
        mobSpawnerItem = Blocks.SPAWNER.asItem();
        BlockPlaceCallback.EVENT.register((world, pos, blockState, entity) -> {
            if (entity instanceof PlayerEntity && ((PlayerEntity) entity).getActiveHand() != null) {
                PlayerEntity playerEntity = (PlayerEntity) entity;
                ItemStack mainHand = playerEntity.getMainHandStack();
                ItemStack offHand = playerEntity.getOffHandStack();
                ItemStack stack = null;
                if (mainHand.getItem() == mobSpawnerItem)
                    stack = mainHand;
                else if (offHand.getItem() == mobSpawnerItem)
                    stack = offHand;
                if (VanillaTweaks.config.tweaks.enableSilkSpawner && stack != null && stack.hasTag()) {
                    CompoundTag stackTag = stack.getTag();
                    assert stackTag != null;
                    CompoundTag spawnerDataNBT = stackTag.getCompound(SPAWNER_TAG);
                    if (!spawnerDataNBT.isEmpty()) {
                        BlockEntity tile = world.getBlockEntity(pos);
                        if (tile instanceof MobSpawnerBlockEntity) {
                            ((MobSpawnerBlockEntity) tile).getLogic().fromTag(spawnerDataNBT);
                        }
                    }
                }
            }
        });
        BlockBreakCallback.EVENT.register((world, pos, blockState, playerEntity) -> {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            int lvl = EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, playerEntity.getMainHandStack());
            if (blockState.getBlock() instanceof SpawnerBlock && !world.isClient() && blockEntity instanceof MobSpawnerBlockEntity && VanillaTweaks.config.tweaks.enableSilkSpawner && lvl >= 1) {
                ItemStack drop = new ItemStack(Blocks.SPAWNER);
                CompoundTag spawnerData = ((MobSpawnerBlockEntity) blockEntity).getLogic().toTag(new CompoundTag());
                CompoundTag stackTag = new CompoundTag();
                spawnerData.remove("Delay");
                stackTag.put(SPAWNER_TAG, spawnerData);
                drop.setTag(stackTag);

                Block.dropStack(blockEntity.getWorld(), pos, drop);
            }
        });
        ItemTooltipCallback.EVENT.register((stack, tooltipContext, list) -> {
            if (stack.hasTag()) {
                CompoundTag stackTag = stack.getTag();
                assert stackTag != null;
                CompoundTag spawnerDataNBT = stackTag.getCompound(SPAWNER_TAG);
                if (!spawnerDataNBT.isEmpty()) {
                    DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.fromTag(spawnerDataNBT);
                    Entity ent = DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.getRenderedEntity();
                    if (ent != null && ent.getDisplayName() != null)
                        list.add(ent.getDisplayName());
                }
            }
        });
    }
}