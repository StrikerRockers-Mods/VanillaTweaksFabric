package io.github.strikerrocker.vt.loot;

import io.github.strikerrocker.vt.base.Feature;

public class MobNametag extends Feature {
    //TODO
    /*@SubscribeEvent
    public void onLivingDrop(LivingDropsEvent event) {
        Entity entity = event.getEntity();
        World world = entity.world;
        if (!world.isRemote && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT) && event.getSource().damageType != null && namedMobsDropNameTag.get() && entity.hasCustomName()) {
            ItemStack nameTag = new ItemStack(Items.NAME_TAG);
            nameTag.setDisplayName(entity.getCustomName());
            nameTag.getTag().putInt("RepairCost", 0);
            entity.entityDropItem(nameTag, 0);
            entity.setCustomName(null);
        }
    }*/
}