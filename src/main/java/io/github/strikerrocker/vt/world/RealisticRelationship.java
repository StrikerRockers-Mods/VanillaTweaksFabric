package io.github.strikerrocker.vt.world;

import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplier;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.util.Identifier;

import java.util.List;

public class RealisticRelationship extends Feature {
    private static final Identifier SHEEP_LOOT_TABLE_ID = new Identifier("minecraft", "entities/sheep");
  /*  private static final Identifier CHICKEN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/chicken");
    private static final Identifier RABBIT_LOOT_TABLE_ID = new Identifier("minecraft", "entities/rabbit");*/

    @Override
    public void initialize() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, identifier, supplierBuilder, lootTableSetter) -> {
            if (identifier.equals(SHEEP_LOOT_TABLE_ID)) {
                if (lootManager.getTable(SHEEP_LOOT_TABLE_ID) instanceof FabricLootSupplier) {
                    FabricLootSupplier supplier = (FabricLootSupplier) lootManager.getTable(SHEEP_LOOT_TABLE_ID);
                    LootContextType contextType = supplier.getType();
                    List<LootPool> pools = supplier.getPools();
                    List<LootFunction> functions = supplier.getFunctions();
                    FabricLootSupplierBuilder replacement = FabricLootSupplierBuilder.builder();
                    replacement.type(contextType);
                    for (LootPool pool : pools) {
                        FabricLootPoolBuilder modifiablePool = FabricLootPoolBuilder.of(pool);
                        modifiablePool.withCondition(KilledByWolfEntityLootCondition.builder().invert().build());
                        replacement.withPool(modifiablePool.build());
                    }
                    replacement.withFunctions(functions);
                    lootTableSetter.set(replacement.build());
                    System.out.println(replacement.toString());
                }
            }//TODO add chicken killed by ocelot,fox and rabbit by fox and wolf
        });
    }
}