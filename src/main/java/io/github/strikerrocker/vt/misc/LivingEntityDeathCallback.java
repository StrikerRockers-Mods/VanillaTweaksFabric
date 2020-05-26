package io.github.strikerrocker.vt.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public interface LivingEntityDeathCallback {
    Event<LivingEntityDeathCallback> EVENT = EventFactory.createArrayBacked(LivingEntityDeathCallback.class,
            (listeners) -> ((livingEntity, damageSource) -> {
                for (LivingEntityDeathCallback livingEntityDeathCallback : listeners) {
                    livingEntityDeathCallback.onDeath(livingEntity, damageSource);
                }
            })
    );

    void onDeath(LivingEntity livingEntity, DamageSource damageSource);
}
