package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.events.EntityEquipmentChangeCallback;
import io.github.strikerrocker.vt.misc.ConeShape;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.UUID;

import static io.github.strikerrocker.vt.VanillaTweaks.MOD_ID;

public class EnchantmentModule extends Module {

    public static final Enchantment BLAZING = Registry.register(Registry.ENCHANTMENT, new ResourceLocation(MOD_ID, "blazing"), new BlazingEnchantment());
    public static final Enchantment HOPS = Registry.register(Registry.ENCHANTMENT, new ResourceLocation(MOD_ID, "hops"), new HopsEnchantment());
    public static final Enchantment NIMBLE = Registry.register(Registry.ENCHANTMENT, new ResourceLocation(MOD_ID, "nimble"), new NimbleEnchantment());
    public static final Enchantment SIPHON = Registry.register(Registry.ENCHANTMENT, new ResourceLocation(MOD_ID, "siphon"), new SiphonEnchantment());
    public static final Enchantment VETERAN = Registry.register(Registry.ENCHANTMENT, new ResourceLocation(MOD_ID, "veteran"), new VeteranEnchantment());
    public static final Enchantment VIGOR = Registry.register(Registry.ENCHANTMENT, new ResourceLocation(MOD_ID, "vigor"), new VigorEnchantment());
    public static final Enchantment HOMING = Registry.register(Registry.ENCHANTMENT, new ResourceLocation(MOD_ID, "homing"), new HomingEnchantment());
    private static final UUID nimbleUUID = UUID.fromString("05b61a62-ae84-492e-8536-f365b7143296");
    private static final UUID vigorUUID = UUID.fromString("18339f34-6ab5-461d-a103-9b9a3ac3eec7");

    public static LivingEntity getTarget(Level world, LivingEntity shooter, int homingLevel) {
        AABB coneBound = ConeShape.getConeBoundApprox(shooter, homingLevel);
        List<Entity> potentialTargets = world.getEntities(shooter, coneBound);
        LivingEntity target = null;
        for (Entity potentialTarget : potentialTargets) {
            if (potentialTarget instanceof LivingEntity livingEntity && shooter.hasLineOfSight(potentialTarget)) {
                if (livingEntity instanceof OwnableEntity tamed && tamed.getOwnerUUID() == shooter.getUUID()) continue;
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
                int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(NIMBLE, entity.getItemBySlot(EquipmentSlot.FEET));
                AttributeInstance speedAttribute = entity.getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
                AttributeModifier speedModifier = new AttributeModifier(nimbleUUID, "Nimble",
                        (float) enchantmentLevel * 0.20000000298023224, AttributeModifier.Operation.MULTIPLY_TOTAL);
                if (speedAttribute != null) {
                    if (enchantmentLevel > 0) {
                        if (speedAttribute.getModifier(nimbleUUID) == null) {
                            speedAttribute.addPermanentModifier(speedModifier);
                        }
                    } else if (speedAttribute.getModifier(nimbleUUID) != null) {
                        speedAttribute.removeModifier(speedModifier);
                    }
                }
            }
            //Vigor Functionality
            if (VanillaTweaks.config.enchanting.enableVigor) {
                int lvl = EnchantmentHelper.getItemEnchantmentLevel(VIGOR, entity.getItemBySlot(EquipmentSlot.CHEST));
                AttributeInstance vigorAttribute = entity.getAttributes().getInstance(Attributes.MAX_HEALTH);
                AttributeModifier vigorModifier = new AttributeModifier(vigorUUID, "vigor", (float) lvl / 10, AttributeModifier.Operation.MULTIPLY_BASE);
                if (vigorAttribute != null) {
                    if (lvl > 0) {
                        if (vigorAttribute.getModifier(vigorUUID) == null) {
                            vigorAttribute.addPermanentModifier(vigorModifier);
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
                int lvl = EnchantmentHelper.getItemEnchantmentLevel(HOPS, entity.getItemBySlot(EquipmentSlot.FEET));
                if (lvl > 0) {
                    if (!entity.hasEffect(MobEffects.JUMP)) {
                        entity.addEffect(new MobEffectInstance(MobEffects.JUMP, Integer.MAX_VALUE, lvl, true, false, false));
                    }
                } else {
                    if (entity.hasEffect(MobEffects.JUMP)) {
                        entity.removeEffect(MobEffects.JUMP);
                    }
                }
            }
        }));
        //Veteran functionality
        ServerTickEvents.START_WORLD_TICK.register(serverWorld -> {
            if (VanillaTweaks.config.enchanting.enableVeteran && serverWorld != null && !serverWorld.isClientSide) {
                serverWorld.getEntities(EntityType.EXPERIENCE_ORB, EntitySelector.ENTITY_STILL_ALIVE).forEach(VeteranEnchantment::attemptToMove);
            }
        });
        //Homing functionality
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof Arrow arrow && VanillaTweaks.config.enchanting.enableHoming &&
                    arrow.getOwner() instanceof LivingEntity shooter) {
                ItemStack stack = shooter.getUseItem();
                int lvl = EnchantmentHelper.getItemEnchantmentLevel(HOMING, stack);
                if (lvl > 0) {
                    LivingEntity target = getTarget(world, shooter, lvl);
                    if (target != null) {
                        double x = target.getX() - arrow.getX();
                        double y = target.getEyeY() - arrow.getY();
                        double z = target.getZ() - arrow.getZ();
                        arrow.setNoGravity(true);
                        arrow.shoot(x, y, z, (float) arrow.getDeltaMovement().length(), 0);
                    }
                }
            }
        });
    }

    @Override
    public void addFeatures() {
    }
}