package io.github.strikerrocker.vt.world.loot_conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import io.github.strikerrocker.vt.world.WorldModule;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.JsonSerializer;

public class KilledByOcelotLootCondition implements LootCondition {
    public static final KilledByOcelotLootCondition INSTANCE = new KilledByOcelotLootCondition();

    public static LootCondition.Builder builder() {
        return () -> INSTANCE;
    }

    @Override
    public LootConditionType getType() {
        return WorldModule.KILLED_BY_OCELOT;
    }

    @Override
    public boolean test(LootContext lootContext) {
        return lootContext.get(LootContextParameters.KILLER_ENTITY) instanceof OcelotEntity;
    }

    public static class Serializer implements JsonSerializer<KilledByOcelotLootCondition> {
        public void toJson(JsonObject jsonObject, KilledByOcelotLootCondition killedByOcelotLootCondition, JsonSerializationContext jsonSerializationContext) {
        }

        public KilledByOcelotLootCondition fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return INSTANCE;
        }
    }
}