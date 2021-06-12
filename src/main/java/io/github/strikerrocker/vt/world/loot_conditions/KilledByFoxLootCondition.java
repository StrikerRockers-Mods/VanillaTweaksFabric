package io.github.strikerrocker.vt.world.loot_conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import io.github.strikerrocker.vt.world.WorldModule;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.JsonSerializer;

public class KilledByFoxLootCondition implements LootCondition {
    public static final KilledByFoxLootCondition INSTANCE = new KilledByFoxLootCondition();

    public static LootCondition.Builder builder() {
        return () -> INSTANCE;
    }

    @Override
    public LootConditionType getType() {
        return WorldModule.KILLED_BY_FOX;
    }

    @Override
    public boolean test(LootContext lootContext) {
        return lootContext.get(LootContextParameters.KILLER_ENTITY) instanceof FoxEntity;
    }

    public static class Serializer implements JsonSerializer<KilledByFoxLootCondition> {
        public void toJson(JsonObject jsonObject, KilledByFoxLootCondition killedByFoxLootCondition, JsonSerializationContext jsonSerializationContext) {
        }

        public KilledByFoxLootCondition fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return INSTANCE;
        }
    }
}
