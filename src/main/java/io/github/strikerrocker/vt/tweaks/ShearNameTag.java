package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class ShearNameTag extends Feature {

    /**
     * Make the nametag shear-able
     */
    @Override
    public void initialize() {
        UseEntityCallback.EVENT.register(((player, world, hand, target, entityHitResult) -> {
            ItemStack heldItem = !player.getMainHandStack().isEmpty() ? player.getMainHandStack() : player.getOffHandStack();
            if (VanillaTweaks.config.tweaks.shearOffNameTag && !heldItem.isEmpty() &&
                    heldItem.getItem() instanceof ShearsItem && target instanceof LivingEntity && target.hasCustomName() && !world.isClient) {
                target.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1, 1);
                ItemStack nameTag = new ItemStack(Items.NAME_TAG).setCustomName(target.getCustomName());
                if (nameTag.getNbt() != null) {
                    nameTag.getNbt().putInt("RepairCost", 0);
                }
                target.dropStack(nameTag);
                target.setCustomName(null);
                heldItem.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
            }
            return ActionResult.PASS;
        }));
    }
}