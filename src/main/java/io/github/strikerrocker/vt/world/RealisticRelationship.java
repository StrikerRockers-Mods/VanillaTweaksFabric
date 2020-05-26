package io.github.strikerrocker.vt.world;

import io.github.strikerrocker.vt.base.Feature;
import net.minecraft.util.Identifier;

public class RealisticRelationship extends Feature {
    private static final Identifier SHEEP_LOOT_TABLE_ID = new Identifier("minecraft", "entities/sheep");
    private static final Identifier CHICKEN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/chicken");

    /*TODO
    @SubscribeEvent
    public void onLivingDrop(LivingDropsEvent event) {
        Entity entity = event.getEntity();
        List<ItemEntity> dropsCopy = new ArrayList<>(event.getDrops());
        for (ItemEntity dropEntity : dropsCopy) {
            ItemStack dropItem = dropEntity.getItem();
            if (event.getSource().getImmediateSource() != null) {
                Item drop = dropItem.getItem();
                Entity source = event.getSource().getImmediateSource();
                if (realisticRelationship.get() &&
                        ((source instanceof WolfEntity && entity instanceof SheepEntity && (drop == Items.MUTTON || drop == Items.COOKED_MUTTON))
                                || (source instanceof OcelotEntity && entity instanceof ChickenEntity && (drop == Items.CHICKEN || drop == Items.COOKED_CHICKEN)))) {
                    event.getDrops().remove(dropEntity);
                }
            }
        }
    }*/

    @Override
    public void initialize() {

    }
}