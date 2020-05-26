package io.github.strikerrocker.vt.loot;

import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class BatLeather extends Feature {
    private static final Identifier BAT_LOOT_TABLE_ID = new Identifier("minecraft", "entities/bat");

    @Override
    public void initialize() {
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, setter) -> {
            if (BAT_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(ConstantLootTableRange.create(1))
                        .withCondition(KilledByPlayerLootCondition.builder())
                        .withCondition(RandomChanceLootCondition.builder(LootModule.config.batLeatherDropChance))
                        .withEntry(ItemEntry.builder(Items.LEATHER));
                supplier.withPool(poolBuilder);
            }
        }));
    }
}
