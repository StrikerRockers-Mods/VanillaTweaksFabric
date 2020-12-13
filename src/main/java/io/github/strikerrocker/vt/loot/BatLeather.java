package io.github.strikerrocker.vt.loot;

import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.misc.LivingEntityDeathCallback;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class BatLeather extends Feature {

    @Override
    public void initialize() {
        LivingEntityDeathCallback.EVENT.register((livingEntity, damageSource) -> {
            World world = livingEntity.world;
            if (!world.isClient && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT) && damageSource != null && livingEntity instanceof BatEntity) {
                ItemStack nameTag = new ItemStack(Items.LEATHER);
                if (world.random.nextInt(10) <= LootModule.config.batLeatherDropChance)
                    livingEntity.dropStack(nameTag);
            }
        });
    }
}