package io.github.strikerrocker.vt.content.blocks;

import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.content.ContentModule;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalBlock;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalBlockEntity;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import static io.github.strikerrocker.vt.VanillaTweaks.MODID;

public class Blocks extends Feature {
    public static final CharcoalBlock CHARCOAL_BLOCK = new CharcoalBlock();
    public static final Block SUGAR_BLOCK = new Block(FabricBlockSettings.of(Material.SAND, MaterialColor.WHITE_TERRACOTTA).strength(0.5f, 0.5f).sounds(BlockSoundGroup.SAND));
    public static final Block FLINT_BLOCK = new Block(Block.Settings.of(Material.SAND, MaterialColor.BROWN).strength(1.0f, 10.0f));
    public static final Block PEDESTAL_BLOCK = new PedestalBlock();
    public static final Identifier PEDESTAL_IDENTIFIER = new Identifier(MODID, "pedestal");
    public static BlockEntityType<PedestalBlockEntity> PEDESTAL_TYPE;

    @Override
    public void initialize() {
        if (ContentModule.config.enableStorageBlocks) {
            Registry.register(Registry.BLOCK, new Identifier(MODID, "charcoal_block"), CHARCOAL_BLOCK);
            Registry.register(Registry.BLOCK, new Identifier(MODID, "sugar_block"), SUGAR_BLOCK);
            Registry.register(Registry.BLOCK, new Identifier(MODID, "flint_block"), FLINT_BLOCK);
            FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 16000);
        }
        if (ContentModule.config.enablePedestal) {
            PEDESTAL_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, PEDESTAL_IDENTIFIER, BlockEntityType.Builder.create(PedestalBlockEntity::new).build(null));
            Registry.register(Registry.BLOCK, PEDESTAL_IDENTIFIER, PEDESTAL_BLOCK);
            ContainerProviderRegistry.INSTANCE.registerFactory(PEDESTAL_IDENTIFIER, (syncId, identifier, playerEntity, buf) -> {
                World world = playerEntity.world;
                BlockPos pos = buf.readBlockPos();
                return world.getBlockState(pos).createContainerFactory(playerEntity.world, pos).createMenu(syncId, playerEntity.inventory, playerEntity);
            });
        }
    }
}