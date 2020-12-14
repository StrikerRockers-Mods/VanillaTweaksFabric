package io.github.strikerrocker.vt.loot;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import io.github.strikerrocker.vt.events.LivingEntityDeathCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class MobNametag extends Feature {
    @Override
    public void initialize() {
        LivingEntityDeathCallback.EVENT.register((livingEntity, damageSource) -> {
            World world = livingEntity.world;
            if (!world.isClient && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT) && damageSource != null && VanillaTweaks.config.loot.namedMobsDropNameTag && livingEntity.hasCustomName()) {
                ItemStack nameTag = new ItemStack(Items.NAME_TAG);
                nameTag.setCustomName(livingEntity.getCustomName());
                nameTag.getTag().putInt("RepairCost", 0);
                livingEntity.dropStack(nameTag);
                livingEntity.setCustomName(null);
            }
        });
    }
}