package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public class ArmorStandSwap extends Feature {

    /**
     * Swaps the items in given slot of Player and ArmorStand
     */
    private static void swapSlot(PlayerEntity player, ArmorStandEntity armorStand, EquipmentSlot slot) {
        ItemStack playerItem = player.getEquippedStack(slot);
        ItemStack armorStandItem = armorStand.getEquippedStack(slot);
        player.equipStack(slot, armorStandItem);
        armorStand.equipStack(slot, playerItem);
    }

    @Override
    public void initialize() {
        //Swaps all the armor slots of armor stand and player when shift right clicked
        UseEntityCallback.EVENT.register(((player, world, hand, target, entityHitResult) -> {
            if (player.isSneaking() && VanillaTweaks.config.tweaks.enableArmorStandSwapping && !world.isClient() && !player.isSpectator()
                    && !player.isCreative() && target instanceof ArmorStandEntity armorStand) {
                for (EquipmentSlot equipmentSlotType : EquipmentSlot.values()) {
                    if (equipmentSlotType.getType() == EquipmentSlot.Type.ARMOR) {
                        swapSlot(player, armorStand, equipmentSlotType);
                    }
                }
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }));
    }
}