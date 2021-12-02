package io.github.strikerrocker.vt.content.items;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.content.blocks.Blocks;
import io.github.strikerrocker.vt.content.items.craftingpad.CraftingPadItem;
import io.github.strikerrocker.vt.content.items.dynamite.DynamiteEntity;
import io.github.strikerrocker.vt.content.items.dynamite.DynamiteItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Position;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

import static io.github.strikerrocker.vt.VanillaTweaks.MOD_ID;

public class Items extends Feature {
    public static final EntityType<DynamiteEntity> DYNAMITE_TYPE = Registry.register(Registry.ENTITY_TYPE,
            new ResourceLocation(MOD_ID, "dynamite"),
            FabricEntityTypeBuilder.create(MobCategory.MISC, DynamiteEntity::new).dimensions(EntityDimensions.fixed(0, 0))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );
    public static final Item CRAFTING_PAD = new CraftingPadItem();
    public static final Item DYNAMITE = new DynamiteItem();
    public static final Item SLIME_BUCKET = new SlimeBucketItem();
    private static final Item FRIED_EGG = new Item(new Item.Properties().food((new FoodProperties.Builder()).nutrition(5).saturationMod(0.6f).build()).tab(CreativeModeTab.TAB_FOOD));

    /**
     * Register ItemBlocks and Items
     */
    @Override
    public void initialize() {
        if (VanillaTweaks.config.content.enableFriedEgg)
            Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "fried_egg"), FRIED_EGG);
        if (VanillaTweaks.config.content.enableCraftingPad)
            Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "crafting_pad"), CRAFTING_PAD);
        if (VanillaTweaks.config.content.enableDynamite) {
            Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "dynamite"), DYNAMITE);
            DispenserBlock.registerBehavior(DYNAMITE, new AbstractProjectileDispenseBehavior() {
                @Override
                protected Projectile getProjectile(Level world, Position position, ItemStack stack) {
                    return new DynamiteEntity(DYNAMITE_TYPE, world);
                }
            });
        }
        if (VanillaTweaks.config.content.enableSlimeBucket)
            Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "slime_bucket"), SLIME_BUCKET);
        if (VanillaTweaks.config.content.enableStorageBlocks) {
            Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "charcoal_block"), new BlockItem(Blocks.CHARCOAL_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
            Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "sugar_block"), new BlockItem(Blocks.SUGAR_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
            Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "flint_block"), new BlockItem(Blocks.FLINT_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
        }
        if (VanillaTweaks.config.content.enablePedestal)
            Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "pedestal"), new BlockItem(Blocks.PEDESTAL_BLOCK, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }
}