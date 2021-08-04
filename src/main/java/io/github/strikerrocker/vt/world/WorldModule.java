package io.github.strikerrocker.vt.world;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.world.loot_conditions.KilledByFoxLootCondition;
import io.github.strikerrocker.vt.world.loot_conditions.KilledByOcelotLootCondition;
import io.github.strikerrocker.vt.world.loot_conditions.KilledByWolfLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.registry.Registry;

public class WorldModule extends Module {
    public static final LootConditionType KILLED_BY_WOLF = register("killed_by_wolf", new KilledByWolfLootCondition.Serializer());
    public static final LootConditionType KILLED_BY_OCELOT = register("killed_by_ocelot", new KilledByOcelotLootCondition.Serializer());
    public static final LootConditionType KILLED_BY_FOX = register("killed_by_fox", new KilledByFoxLootCondition.Serializer());

    /**
     * Registers LootConditionType
     */
    private static LootConditionType register(String id, JsonSerializer<? extends LootCondition> serializer) {
        return Registry.register(Registry.LOOT_CONDITION_TYPE, new Identifier(id), new LootConditionType(serializer));
    }

    @Override
    public void addFeatures() {
        registerFeature("realistic_relationship", new RealisticRelationship());
        registerFeature("lava_pocket", new NoMoreLavaPocketGen());
    }
}