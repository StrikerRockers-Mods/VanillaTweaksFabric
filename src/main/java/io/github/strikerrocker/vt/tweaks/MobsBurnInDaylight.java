package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.events.LivingEntityTickCallback;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class MobsBurnInDaylight extends Feature {

    /**
     * Handles burning of baby zombie and creepers in daylight
     */
    @Override
    public void initialize() {
        LivingEntityTickCallback.EVENT.register(livingEntity -> {
            if (!livingEntity.world.isClient) {
                World world = livingEntity.world;
                if (((livingEntity instanceof CreeperEntity && VanillaTweaks.config.tweaks.creeperBurnInDaylight) || (livingEntity instanceof ZombieEntity && livingEntity.isBaby() &&
                        VanillaTweaks.config.tweaks.babyZombieBurnInDaylight)) && world.isDay()) {
                    float brightness = livingEntity.getBrightnessAtEyes();
                    Random random = world.random;
                    BlockPos blockPos = livingEntity.getBlockPos();
                    if (brightness > 0.5 && random.nextFloat() * 30 < (brightness - 0.4) * 2 && world.isSkyVisible(blockPos)) {
                        livingEntity.setOnFireFor(10);
                    }
                }
            }
        });
    }
}