package io.github.strikerrocker.vt.world.loot_conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import io.github.strikerrocker.vt.world.WorldModule;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.JsonSerializer;

public class KilledByWolfLootCondition implements LootCondition {
    public static final KilledByWolfLootCondition INSTANCE = new KilledByWolfLootCondition();

    private KilledByWolfLootCondition() {
    }

    public static LootCondition.Builder builder() {
        return () -> INSTANCE;
    }

    @Override
    public LootConditionType getType() {
        return WorldModule.KILLED_BY_WOLF;
    }

    @Override
    public boolean test(LootContext lootContext) {
        return lootContext.get(LootContextParameters.KILLER_ENTITY) instanceof WolfEntity;
    }

    public static class Serializer implements JsonSerializer<KilledByWolfLootCondition> {
        public void toJson(JsonObject jsonObject, KilledByWolfLootCondition killedByWolfLootCondition, JsonSerializationContext jsonSerializationContext) {
        }

        public KilledByWolfLootCondition fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return INSTANCE;
        }
    }
}
