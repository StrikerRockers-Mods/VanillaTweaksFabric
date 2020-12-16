package io.github.strikerrocker.vt.world;

import io.github.strikerrocker.vt.base.Module;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.registry.Registry;

public class WorldModule extends Module {
    public static final LootConditionType KILLED_BY_WOLF = register("killed_by_wolf", new KilledByWolfEntityLootCondition.Serializer());

    private static LootConditionType register(String id, JsonSerializer<? extends LootCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new Identifier(id), new LootConditionType(serializer));
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void addFeatures() {
        registerFeature("realistic_relationship", new RealisticRelationship());
        registerFeature("lava_pocket", new NoMoreLavaPocketGen());
    }
}