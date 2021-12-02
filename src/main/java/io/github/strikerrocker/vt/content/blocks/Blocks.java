package io.github.strikerrocker.vt.content.blocks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalBlock;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalBlockEntity;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalScreenHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import static io.github.strikerrocker.vt.VanillaTweaks.MOD_ID;

public class Blocks extends Feature {
    public static final Block CHARCOAL_BLOCK = new Block(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(5.0f, 10.0f));
    public static final Block SUGAR_BLOCK = new Block(FabricBlockSettings.of(Material.SAND, MaterialColor.TERRACOTTA_WHITE).strength(0.5f, 0.5f).sound(SoundType.SAND));
    public static final Block FLINT_BLOCK = new Block(Properties.of(Material.SAND, MaterialColor.COLOR_BROWN).strength(1.0f, 10.0f));
    public static final Block PEDESTAL_BLOCK = new PedestalBlock();
    public static final ResourceLocation PEDESTAL_IDENTIFIER = new ResourceLocation(MOD_ID, "pedestal");
    public static final MenuType<PedestalScreenHandler> PEDESTAL_SCREEN_HANDLER;
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_TYPE;

    static {
        PEDESTAL_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(PEDESTAL_IDENTIFIER, PedestalScreenHandler::new);
        PEDESTAL_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, PEDESTAL_IDENTIFIER, FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, PEDESTAL_BLOCK).build(null));
    }

    /**
     * Registers blocks
     */
    @Override
    public void initialize() {
        if (VanillaTweaks.config.content.enableStorageBlocks) {
            Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "charcoal_block"), CHARCOAL_BLOCK);
            Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "sugar_block"), SUGAR_BLOCK);
            Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "flint_block"), FLINT_BLOCK);
            FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 16000);
        }
        if (VanillaTweaks.config.content.enablePedestal) {
            Registry.register(Registry.BLOCK, PEDESTAL_IDENTIFIER, PEDESTAL_BLOCK);
        }
    }
}