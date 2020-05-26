package io.github.strikerrocker.vt.content.items;

import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.content.ContentModule;
import io.github.strikerrocker.vt.content.blocks.Blocks;
import io.github.strikerrocker.vt.content.items.craftingpad.CraftingPadItem;
import io.github.strikerrocker.vt.content.items.dynamite.DynamiteEntity;
import io.github.strikerrocker.vt.content.items.dynamite.DynamiteItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.projectile.Projectile;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import static io.github.strikerrocker.vt.VanillaTweaks.MODID;
import static net.minecraft.item.Items.IRON_INGOT;

public class Items extends Feature {
    public static final EntityType<DynamiteEntity> DYNAMITE_TYPE = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MODID, "dynamite"),
            FabricEntityTypeBuilder.create(EntityCategory.MISC, DynamiteEntity::new).dimensions(EntityDimensions.fixed(0, 0)).build()
    );
    private static final Item LENS = new Item(new Item.Settings().group(ItemGroup.MISC));
    private static final Item FRIED_EGG = new Item(new Item.Settings().food((new FoodComponent.Builder()).hunger(5).saturationModifier(0.6f).build()).group(ItemGroup.FOOD));
    public static ArmorMaterial binocular_material = new BasicArmorMaterial("binoculars", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, () -> Ingredient.ofItems(IRON_INGOT));
    private static final Item BINOCULARS = new ArmorItem(binocular_material, EquipmentSlot.HEAD, new Item.Settings().maxCount(1).group(ItemGroup.TOOLS));
    public static Item CRAFTING_PAD = new CraftingPadItem();
    public static Item DYNAMITE = new DynamiteItem();
    public static Item SLIME_BUCKET = new SlimeBucketItem();

    /*@SubscribeEvent
    public void onFOVChange(FOVUpdateEvent event) {
        if (event.getEntity() != null && binocularZoomAmount.get() != 0) {
            ItemStack helmet = event.getEntity().getItemStackFromSlot(EquipmentSlotType.HEAD);
            if ((!helmet.isEmpty() && helmet.getItem() == BINOCULARS)) {
                event.setNewfov((float) (event.getFov() / binocularZoomAmount.get()));
            } else if (ModList.get().isLoaded("curios")) {
                if (CuriosCompat.isStackInCuriosSlot(new ItemStack(Items.BINOCULARS), event.getEntity())) {
                    event.setNewfov((float) (event.getFov() / binocularZoomAmount.get()));
                }
            }
        }
    }*/

    @Override
    public void initialize() {
        if (ContentModule.config.binocularZoomAmount > 0) {
            Registry.register(Registry.ITEM, new Identifier(MODID, "binoculars"), BINOCULARS);
            Registry.register(Registry.ITEM, new Identifier(MODID, "lens"), LENS);
        }
        if (ContentModule.config.enableFriedEgg)
            Registry.register(Registry.ITEM, new Identifier(MODID, "fried_egg"), FRIED_EGG);
        if (ContentModule.config.enableCraftingPad)
            Registry.register(Registry.ITEM, new Identifier(MODID, "crafting_pad"), CRAFTING_PAD);
        if (ContentModule.config.enableDynamite) {
            Registry.register(Registry.ITEM, new Identifier(MODID, "dynamite"), DYNAMITE);
            DispenserBlock.registerBehavior(DYNAMITE, new ProjectileDispenserBehavior() {
                @Override
                protected Projectile createProjectile(World world, Position position, ItemStack itemStack) {
                    return new DynamiteEntity(DYNAMITE_TYPE, world);
                }
            });
        }
        if (ContentModule.config.enableSlimeBucket)
            Registry.register(Registry.ITEM, new Identifier(MODID, "slime_bucket"), SLIME_BUCKET);
        if (ContentModule.config.enableStorageBlocks) {
            Registry.register(Registry.ITEM, new Identifier(MODID, "charcoal_block"), new BlockItem(Blocks.CHARCOAL_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
            Registry.register(Registry.ITEM, new Identifier(MODID, "sugar_block"), new BlockItem(Blocks.SUGAR_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
            Registry.register(Registry.ITEM, new Identifier(MODID, "flint_block"), new BlockItem(Blocks.FLINT_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        }
        if (ContentModule.config.enablePedestal)
            Registry.register(Registry.ITEM, new Identifier(MODID, "pedestal"), new BlockItem(Blocks.PEDESTAL_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
    }
}