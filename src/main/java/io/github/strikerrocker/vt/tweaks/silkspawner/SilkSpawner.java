package io.github.strikerrocker.vt.tweaks.silkspawner;

import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.misc.BlockPlaceCallback;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

public class SilkSpawner extends Feature {
    private static final String SPAWNER_TAG = "SilkSpawnerData";
    private static Item mobSpawnerItem = null;

    /*TODO
    @SubscribeEvent
    public static void onToolTipEvent(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.hasTag()) {
            CompoundNBT stackTag = stack.getTag();
            assert stackTag != null;
            CompoundNBT spawnerDataNBT = stackTag.getCompound(SPAWNER_TAG);
            if (!spawnerDataNBT.isEmpty()) {
                DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.read(spawnerDataNBT);
                Entity ent = DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.getCachedEntity();
                event.getToolTip().add(ent.getDisplayName());
            }
        }
    }*/


    /*@SubscribeEvent
    public void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof PlayerEntity && ((PlayerEntity) event.getEntity()).getActiveHand() != null) {
            PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
            ItemStack mainHand = playerEntity.getHeldItemMainhand();
            ItemStack offHand = playerEntity.getHeldItemOffhand();
            ItemStack stack = null;
            if (mainHand.getItem() == mobSpawnerItem)
                stack = mainHand;
            else if (offHand.getItem() == mobSpawnerItem)
                stack = offHand;
            if (enableSilkSpawner.get() && stack != null && stack.hasTag()) {
                CompoundNBT stackTag = stack.getTag();
                assert stackTag != null;
                CompoundNBT spawnerDataNBT = stackTag.getCompound(SPAWNER_TAG);
                if (!spawnerDataNBT.isEmpty()) {
                    TileEntity tile = event.getWorld().getTileEntity(event.getPos());
                    if (tile instanceof MobSpawnerTileEntity) {
                        ((MobSpawnerTileEntity) tile).getSpawnerBaseLogic().read(spawnerDataNBT);
                    }
                }
            }
        }
    }*/

    @Override
    public void initialize() {
        mobSpawnerItem = Blocks.SPAWNER.asItem();
        BlockPlaceCallback.EVENT.register((world, pos, blockState) -> {

        });
    }

    /*@SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onBreak(BlockEvent.BreakEvent event) {
        IWorld world = event.getWorld();
        TileEntity tile = world.getTileEntity(event.getPos());
        int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, event.getPlayer().getHeldItemMainhand());
        if (event.getState().getBlock() instanceof SpawnerBlock && !world.isRemote() && tile instanceof MobSpawnerTileEntity && enableSilkSpawner.get() && lvl >= 1) {
            event.setExpToDrop(0);
            ItemStack drop = new ItemStack(Blocks.SPAWNER);
            CompoundNBT spawnerData = ((MobSpawnerTileEntity) tile).getSpawnerBaseLogic().write(new CompoundNBT());
            CompoundNBT stackTag = new CompoundNBT();
            spawnerData.remove("Delay");
            stackTag.put(SPAWNER_TAG, spawnerData);
            drop.setTag(stackTag);

            Block.spawnAsEntity(tile.getWorld(), event.getPos(), drop);
            //Does this cause problems w/ block protection?
            tile.getWorld().removeTileEntity(tile.getPos());
            world.destroyBlock(event.getPos(), false);
            event.setCanceled(true);
        }
    }*/
    //TODO
}