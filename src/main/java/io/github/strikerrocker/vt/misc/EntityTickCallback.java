package io.github.strikerrocker.vt.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;

public interface EntityTickCallback {
    Event<EntityTickCallback> EVENT = EventFactory.createArrayBacked(EntityTickCallback.class,
            (listeners) -> (livingEntity -> {
                for (EntityTickCallback entityTickCallback : listeners) {
                    entityTickCallback.update(livingEntity);
                }
            })
    );

    void update(LivingEntity livingEntity);
}