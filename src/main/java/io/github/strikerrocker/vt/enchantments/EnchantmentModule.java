package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.events.EntityEquipmentChangeCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.github.strikerrocker.vt.VanillaTweaks.MODID;

public class EnchantmentModule extends Module {

    private static final UUID nimbleUUID = UUID.fromString("05b61a62-ae84-492e-8536-f365b7143296");
    private static final UUID vigorUUID = UUID.fromString("18339f34-6ab5-461d-a103-9b9a3ac3eec7");
    public static Map<String, Enchantment> enchantments = new HashMap<>();

    @Override
    public void initialize() {
        if (VanillaTweaks.config.enchanting.enableBlazing)
            enchantments.put("blazing", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "blazing"), new BlazingEnchantment()));
        if (VanillaTweaks.config.enchanting.enableHops)
            enchantments.put("hops", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "hops"), new HopsEnchantment()));
        if (VanillaTweaks.config.enchanting.enableNimble)
            enchantments.put("nimble", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "nimble"), new NimbleEnchantment()));
        if (VanillaTweaks.config.enchanting.enableSiphon)
            enchantments.put("siphon", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "siphon"), new SiphonEnchantment()));
        if (VanillaTweaks.config.enchanting.enableVeteran)
            enchantments.put("veteran", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "veteran"), new VeteranEnchantment()));
        if (VanillaTweaks.config.enchanting.enableVigor)
            enchantments.put("vigor", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "vigor"), new VigorEnchantment()));
        if (VanillaTweaks.config.enchanting.enableHoming)
            enchantments.put("homing", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "homing"), new HomingEnchantment()));
        super.initialize();
        EntityEquipmentChangeCallback.EVENT.register(((entity, slot, stack) -> {
            if (VanillaTweaks.config.enchanting.enableNimble) {
                int enchantmentLevel = EnchantmentHelper.getLevel(enchantments.get("nimble"), entity.getEquippedStack(EquipmentSlot.FEET));
                EntityAttributeInstance speedAttribute = entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                EntityAttributeModifier speedModifier = new EntityAttributeModifier(nimbleUUID, "Nimble",
                        (float) enchantmentLevel * 0.20000000298023224, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                if (enchantmentLevel > 0) {
                    if (speedAttribute.getModifier(nimbleUUID) == null) {
                        speedAttribute.addPersistentModifier(speedModifier);
                    }
                } else if (speedAttribute.getModifier(nimbleUUID) != null) {
                    speedAttribute.removeModifier(speedModifier);
                }
            }
            if (VanillaTweaks.config.enchanting.enableVigor) {
                int lvl = EnchantmentHelper.getLevel(enchantments.get("vigor"), entity.getEquippedStack(EquipmentSlot.CHEST));
                EntityAttributeInstance vigorAttribute = entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_MAX_HEALTH);
                EntityAttributeModifier vigorModifier = new EntityAttributeModifier(vigorUUID, "vigor", (float) lvl / 10, EntityAttributeModifier.Operation.MULTIPLY_BASE);
                if (lvl > 0) {
                    if (vigorAttribute.getModifier(vigorUUID) == null) {
                        vigorAttribute.addPersistentModifier(vigorModifier);
                    }
                } else {
                    if (vigorAttribute.getModifier(vigorUUID) != null) {
                        vigorAttribute.removeModifier(vigorModifier);
                        if (entity.getHealth() > entity.getMaxHealth())
                            entity.setHealth(entity.getMaxHealth());
                    }
                }
            }
        }));
        ServerTickEvents.START_WORLD_TICK.register(serverWorld -> {
            if (VanillaTweaks.config.enchanting.enableVeteran && serverWorld != null && !serverWorld.isClient) {
                serverWorld.getEntitiesByType(EntityType.EXPERIENCE_ORB, EntityPredicates.VALID_ENTITY).forEach(VeteranEnchantment::attemptToMove);
            }
        });
    }

    @Override
    public void addFeatures() {
    }
}