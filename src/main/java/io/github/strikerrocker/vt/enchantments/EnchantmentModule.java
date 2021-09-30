package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.events.EntityEquipmentChangeCallback;
import io.github.strikerrocker.vt.misc.ConeShape;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

import static io.github.strikerrocker.vt.VanillaTweaks.MOD_ID;

public class EnchantmentModule extends Module {

    public static final Enchantment BLAZING = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "blazing"), new BlazingEnchantment());
    public static final Enchantment HOPS = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "hops"), new HopsEnchantment());
    public static final Enchantment NIMBLE = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "nimble"), new NimbleEnchantment());
    public static final Enchantment SIPHON = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "siphon"), new SiphonEnchantment());
    public static final Enchantment VETERAN = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "veteran"), new VeteranEnchantment());
    public static final Enchantment VIGOR = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "vigor"), new VigorEnchantment());
    public static final Enchantment HOMING = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "homing"), new HomingEnchantment());
    private static final UUID nimbleUUID = UUID.fromString("05b61a62-ae84-492e-8536-f365b7143296");
    private static final UUID vigorUUID = UUID.fromString("18339f34-6ab5-461d-a103-9b9a3ac3eec7");

    public static LivingEntity getTarget(World world, LivingEntity shooter, int homingLevel) {
        Box coneBound = ConeShape.getConeBoundApprox(shooter, homingLevel);
        List<Entity> potentialTargets = world.getOtherEntities(shooter, coneBound);
        LivingEntity target = null;
        for (Entity potentialTarget : potentialTargets) {
            if (potentialTarget instanceof LivingEntity livingEntity && shooter.canSee(potentialTarget)) {
                if (livingEntity instanceof Tameable tameable && tameable.getOwnerUuid() == shooter.getUuid()) continue;
                target = livingEntity;
            }
        }
        VanillaTweaks.LOGGER.debug(coneBound);
        VanillaTweaks.LOGGER.debug(target);
        return target;
    }

    @Override
    public void initialize() {
        EntityEquipmentChangeCallback.EVENT.register(((entity, slot, from, to) -> {
            //Nimble functionality
            if (VanillaTweaks.config.enchanting.enableNimble) {
                int enchantmentLevel = EnchantmentHelper.getLevel(NIMBLE, entity.getEquippedStack(EquipmentSlot.FEET));
                EntityAttributeInstance speedAttribute = entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                EntityAttributeModifier speedModifier = new EntityAttributeModifier(nimbleUUID, "Nimble",
                        (float) enchantmentLevel * 0.20000000298023224, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                if (speedAttribute != null) {
                    if (enchantmentLevel > 0) {
                        if (speedAttribute.getModifier(nimbleUUID) == null) {
                            speedAttribute.addPersistentModifier(speedModifier);
                        }
                    } else if (speedAttribute.getModifier(nimbleUUID) != null) {
                        speedAttribute.removeModifier(speedModifier);
                    }
                }
            }
            //Vigor Functionality
            if (VanillaTweaks.config.enchanting.enableVigor) {
                int lvl = EnchantmentHelper.getLevel(VIGOR, entity.getEquippedStack(EquipmentSlot.CHEST));
                EntityAttributeInstance vigorAttribute = entity.getAttributes().getCustomInstance(EntityAttributes.GENERIC_MAX_HEALTH);
                EntityAttributeModifier vigorModifier = new EntityAttributeModifier(vigorUUID, "vigor", (float) lvl / 10, EntityAttributeModifier.Operation.MULTIPLY_BASE);
                if (vigorAttribute != null) {
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
            }
            //Hops functionality
            if (VanillaTweaks.config.enchanting.enableHops) {
                int lvl = EnchantmentHelper.getLevel(HOPS, entity.getEquippedStack(EquipmentSlot.FEET));
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
            if (entity instanceof ArrowEntity arrow && VanillaTweaks.config.enchanting.enableHoming &&
                    arrow.getOwner() instanceof LivingEntity shooter) {
                ItemStack stack = shooter.getActiveItem();
                int lvl = EnchantmentHelper.getLevel(HOMING, stack);
                if (lvl > 0) {
                    LivingEntity target = getTarget(world, shooter, lvl);
                    if (target != null) {
                        double x = target.getX() - arrow.getX();
                        double y = target.getEyeY() - arrow.getY();
                        double z = target.getZ() - arrow.getZ();
                        arrow.setNoGravity(true);
                        arrow.setVelocity(x, y, z, (float) arrow.getVelocity().length(), 0);
                    }
                }
            }
        });
    }

    @Override
    public void addFeatures() {
    }
}