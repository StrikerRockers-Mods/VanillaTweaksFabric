package io.github.strikerrocker.vt.world;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.world.loot_conditions.KilledByFoxLootCondition;
import io.github.strikerrocker.vt.world.loot_conditions.KilledByOcelotLootCondition;
import io.github.strikerrocker.vt.world.loot_conditions.KilledByWolfLootCondition;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplier;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.util.Identifier;

import java.util.List;

public class RealisticRelationship extends Feature {
    private static final Identifier SHEEP_LOOT_TABLE_ID = new Identifier("minecraft", "entities/sheep");
    private static final Identifier CHICKEN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/chicken");
    private static final Identifier RABBIT_LOOT_TABLE_ID = new Identifier("minecraft", "entities/rabbit");

    @Override
    public void initialize() {
        if (VanillaTweaks.config.world.realisticRelationship)
            LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, identifier, supplierBuilder, lootTableSetter) -> {
                addLootCondition(KilledByWolfLootCondition.builder().invert().build(), SHEEP_LOOT_TABLE_ID, lootManager, identifier, lootTableSetter);
                addLootCondition(KilledByFoxLootCondition.builder().or(KilledByOcelotLootCondition.builder()).invert().build(),
                        CHICKEN_LOOT_TABLE_ID, lootManager, identifier, lootTableSetter);
                addLootCondition(KilledByFoxLootCondition.builder().or(KilledByWolfLootCondition.builder()).invert().build(),
                        RABBIT_LOOT_TABLE_ID, lootManager, identifier, lootTableSetter);
            });
    }

    public void addLootCondition(LootCondition condition, Identifier id_to_replace, LootManager manager, Identifier loot_table_id, LootTableLoadingCallback.LootTableSetter setter) {
        if (loot_table_id.equals(id_to_replace) && manager.getTable(id_to_replace) instanceof FabricLootSupplier) {
            FabricLootSupplier supplier = (FabricLootSupplier) manager.getTable(id_to_replace);
            LootContextType contextType = supplier.getType();
            List<LootPool> pools = supplier.getPools();
            List<LootFunction> functions = supplier.getFunctions();
            FabricLootSupplierBuilder replacement = FabricLootSupplierBuilder.builder();
            replacement.type(contextType);
            for (LootPool pool : pools) {
                FabricLootPoolBuilder modifiablePool = FabricLootPoolBuilder.of(pool);
                modifiablePool.withCondition(condition);
                replacement.withPool(modifiablePool.build());
            }
            replacement.withFunctions(functions);
            setter.set(replacement.build());
        }
    }
}