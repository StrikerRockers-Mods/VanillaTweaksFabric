package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;

public class ShearNameTag extends Feature {

    /**
     * Make the nametag shear-able
     */
    @Override
    public void initialize() {
        UseEntityCallback.EVENT.register(((player, world, hand, target, entityHitResult) -> {
            ItemStack heldItem = player.getItemInHand(hand);
            if (VanillaTweaks.config.tweaks.shearOffNameTag && !heldItem.isEmpty() && heldItem.getItem() instanceof ShearsItem &&
                    target instanceof LivingEntity && target.hasCustomName() && !world.isClientSide) {
                target.playSound(SoundEvents.SHEEP_SHEAR, 1, 1);
                ItemStack nameTag = new ItemStack(Items.NAME_TAG).setHoverName(target.getCustomName());
                nameTag.getOrCreateTag().putInt("RepairCost", 0);
                target.spawnAtLocation(nameTag);
                target.setCustomName(null);
                heldItem.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(playerEntity.getUsedItemHand()));
            }
            return InteractionResult.PASS;
        }));
    }
}