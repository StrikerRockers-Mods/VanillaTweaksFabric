package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.events.EntityEquipmentChangeCallback;
import io.github.strikerrocker.vt.misc.ConeShape;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.github.strikerrocker.vt.VanillaTweaks.MOD_ID;

public class EnchantmentModule extends Module {

    public static final Map<String, Enchantment> enchantments = new HashMap<>();
    private static final UUID nimbleUUID = UUID.fromString("05b61a62-ae84-492e-8536-f365b7143296");
    private static final UUID vigorUUID = UUID.fromString("18339f34-6ab5-461d-a103-9b9a3ac3eec7");

    @Override
    public void initialize() {
        enchantments.put("blazing", Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "blazing"), new BlazingEnchantment()));
        enchantments.put("hops", Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "hops"), new HopsEnchantment()));
        enchantments.put("nimble", Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "nimble"), new NimbleEnchantment()));
        enchantments.put("siphon", Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "siphon"), new SiphonEnchantment()));
        enchantments.put("veteran", Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "veteran"), new VeteranEnchantment()));
        enchantments.put("vigor", Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "vigor"), new VigorEnchantment()));
        enchantments.put("homing", Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "homing"), new HomingEnchantment()));
        super.initialize();
        //Nimble, Vigor and Hops functionality
        EntityEquipmentChangeCallback.EVENT.register(((entity, slot, from, to) -> {
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
            if (VanillaTweaks.config.enchanting.enableHops) {
                int lvl = EnchantmentHelper.getLevel(enchantments.get("hops"), entity.getEquippedStack(EquipmentSlot.FEET));
                if (lvl > 0) {
                    if (!entity.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, Integer.MAX_VALUE, lvl, true, false, false));
                    }
                } else {
                    if (entity.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
                        entity.removeStatusEffect(StatusEffects.JUMP_BOOST);
                    }
                }
            }
        }));
        //Veteran functionality
        ServerTickEvents.START_WORLD_TICK.register(serverWorld -> {
            if (VanillaTweaks.config.enchanting.enableVeteran && serverWorld != null && !serverWorld.isClient) {
                serverWorld.getEntitiesByType(EntityType.EXPERIENCE_ORB, EntityPredicates.VALID_ENTITY).forEach(VeteranEnchantment::attemptToMove);
            }
        });
        //Homing functionality
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ArrowEntity arrow && VanillaTweaks.config.enchanting.enableHoming) {
                if (arrow.getOwner() instanceof PlayerEntity player) {
                    ItemStack stack = player.getActiveItem();
                    int lvl = EnchantmentHelper.getLevel(EnchantmentModule.enchantments.get("homing"), stack);
                    if (lvl > 0) {
                        Box coneBound = ConeShape.getConeBounds(player, lvl);
                        List<Entity> entities = world.getOtherEntities(null, coneBound);
                        LivingEntity target = null;
                        for (Entity entity1 : entities) {
                            if (entity1 instanceof LivingEntity livingEntity && player.canSee(entity1)) {
                                target = livingEntity;
                            }
                        }
                        if (target != null) {
                            double x1 = target.getX() - arrow.getX();
                            double y1 = target.getEyeY() - arrow.getY();
                            double z1 = target.getZ() - arrow.getZ();
                            arrow.setVelocity(x1, y1, z1, (float) arrow.getVelocity().length(), 0);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void addFeatures() {
    }
}