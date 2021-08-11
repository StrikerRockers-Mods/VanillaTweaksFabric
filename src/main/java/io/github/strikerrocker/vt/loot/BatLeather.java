package io.github.strikerrocker.vt.loot;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.events.LivingEntityDeathCallback;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class BatLeather extends Feature {

    /**
     * Adds the chance of leather dropping when bat is killed
     */
    @Override
    public void initialize() {
        LivingEntityDeathCallback.EVENT.register((livingEntity, damageSource) -> {
            World world = livingEntity.world;
            if (!world.isClient && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT) && damageSource.getAttacker() instanceof PlayerEntity &&
                    livingEntity instanceof BatEntity && world.random.nextInt(10) <= VanillaTweaks.config.loot.batLeatherDropChance)
                livingEntity.dropItem(Items.LEATHER);
        });
    }
}