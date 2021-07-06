package io.github.strikerrocker.vt.content.blocks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalBlock;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalBlockEntity;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalScreenHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static io.github.strikerrocker.vt.VanillaTweaks.MODID;

public class Blocks extends Feature {
    public static final Block CHARCOAL_BLOCK = new Block(Block.Settings.of(Material.STONE, MapColor.BLACK).strength(5.0f, 10.0f));
    public static final Block SUGAR_BLOCK = new Block(FabricBlockSettings.of(Material.AGGREGATE, MapColor.TERRACOTTA_WHITE).strength(0.5f, 0.5f).sounds(BlockSoundGroup.SAND));
    public static final Block FLINT_BLOCK = new Block(Block.Settings.of(Material.AGGREGATE, MapColor.BROWN).strength(1.0f, 10.0f));
    public static final Block PEDESTAL_BLOCK = new PedestalBlock();
    public static final Identifier PEDESTAL_IDENTIFIER = new Identifier(MODID, "pedestal");
    public static final ScreenHandlerType<PedestalScreenHandler> PEDESTAL_SCREEN_HANDLER;
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_TYPE;

    static {
        PEDESTAL_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(PEDESTAL_IDENTIFIER, PedestalScreenHandler::new);
        PEDESTAL_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, PEDESTAL_IDENTIFIER, BlockEntityType.Builder.create(PedestalBlockEntity::new, PEDESTAL_BLOCK).build(null));
    }

    @Override
    public void initialize() {
        if (VanillaTweaks.config.content.enableStorageBlocks) {
            Registry.register(Registry.BLOCK, new Identifier(MODID, "charcoal_block"), CHARCOAL_BLOCK);
            Registry.register(Registry.BLOCK, new Identifier(MODID, "sugar_block"), SUGAR_BLOCK);
            Registry.register(Registry.BLOCK, new Identifier(MODID, "flint_block"), FLINT_BLOCK);
            FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 16000);
        }
        if (VanillaTweaks.config.content.enablePedestal) {
            Registry.register(Registry.BLOCK, PEDESTAL_IDENTIFIER, PEDESTAL_BLOCK);
        }
    }
}