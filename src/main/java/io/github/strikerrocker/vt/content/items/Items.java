package io.github.strikerrocker.vt.content.items;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.content.blocks.Blocks;
import io.github.strikerrocker.vt.content.items.craftingpad.CraftingPadItem;
import io.github.strikerrocker.vt.content.items.dynamite.DynamiteEntity;
import io.github.strikerrocker.vt.content.items.dynamite.DynamiteItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import static io.github.strikerrocker.vt.VanillaTweaks.MODID;

public class Items extends Feature {
    public static final EntityType<DynamiteEntity> DYNAMITE_TYPE = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MODID, "dynamite"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, DynamiteEntity::new).dimensions(EntityDimensions.fixed(0, 0))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );
    public static final Item CRAFTING_PAD = new CraftingPadItem();
    public static final Item DYNAMITE = new DynamiteItem();
    public static final Item SLIME_BUCKET = new SlimeBucketItem();
    private static final Item FRIED_EGG = new Item(new Item.Settings().food((new FoodComponent.Builder()).hunger(5).saturationModifier(0.6f).build()).group(ItemGroup.FOOD));

    @Override
    public void initialize() {
        if (VanillaTweaks.config.content.enableFriedEgg)
            Registry.register(Registry.ITEM, new Identifier(MODID, "fried_egg"), FRIED_EGG);
        if (VanillaTweaks.config.content.enableCraftingPad)
            Registry.register(Registry.ITEM, new Identifier(MODID, "crafting_pad"), CRAFTING_PAD);
        if (VanillaTweaks.config.content.enableDynamite) {
            Registry.register(Registry.ITEM, new Identifier(MODID, "dynamite"), DYNAMITE);
            DispenserBlock.registerBehavior(DYNAMITE, new ProjectileDispenserBehavior() {
                @Override
                protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                    return new DynamiteEntity(DYNAMITE_TYPE, world);
                }
            });

            if (VanillaTweaks.config.content.enableSlimeBucket)
                Registry.register(Registry.ITEM, new Identifier(MODID, "slime_bucket"), SLIME_BUCKET);
            if (VanillaTweaks.config.content.enableStorageBlocks) {
                Registry.register(Registry.ITEM, new Identifier(MODID, "charcoal_block"), new BlockItem(Blocks.CHARCOAL_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
                Registry.register(Registry.ITEM, new Identifier(MODID, "sugar_block"), new BlockItem(Blocks.SUGAR_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
                Registry.register(Registry.ITEM, new Identifier(MODID, "flint_block"), new BlockItem(Blocks.FLINT_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
            }
            if (VanillaTweaks.config.content.enablePedestal)
                Registry.register(Registry.ITEM, new Identifier(MODID, "pedestal"), new BlockItem(Blocks.PEDESTAL_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        }
    }
}