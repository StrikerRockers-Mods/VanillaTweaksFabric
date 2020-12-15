package io.github.strikerrocker.vt.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface EntityEquipmentChangeCallback {
    Event<EntityEquipmentChangeCallback> EVENT = EventFactory.createArrayBacked(EntityEquipmentChangeCallback.class,
            (listeners) -> ((LivingEntity entity, EquipmentSlot slot, ItemStack from, ItemStack to) -> {
                for (EntityEquipmentChangeCallback playerEquipmentChangeCallback : listeners) {
                    playerEquipmentChangeCallback.onEntityEquipmentChange(entity, slot, from, to);
                }
            })
    );

    void onEntityEquipmentChange(LivingEntity entity, EquipmentSlot slot, ItemStack from, ItemStack to);
}
