package io.github.strikerrocker.vt.world;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.JsonSerializer;

public class KilledByWolfEntityLootCondition implements LootCondition {
    public static final KilledByWolfEntityLootCondition INSTANCE = new KilledByWolfEntityLootCondition();

    private KilledByWolfEntityLootCondition() {
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

    public static class Serializer implements JsonSerializer<KilledByWolfEntityLootCondition> {
        public void toJson(JsonObject jsonObject, KilledByWolfEntityLootCondition killedByWolfLootCondition, JsonSerializationContext jsonSerializationContext) {
        }

        public KilledByWolfEntityLootCondition fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return INSTANCE;
        }
    }
}
