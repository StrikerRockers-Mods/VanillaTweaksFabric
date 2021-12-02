package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ArmorStandSwap extends Feature {

    /**
     * Swaps the items in given slot of Player and ArmorStand
     */
    private static void swapSlot(Player player, ArmorStand armorStand, EquipmentSlot slot) {
        ItemStack playerItem = player.getItemBySlot(slot);
        ItemStack armorStandItem = armorStand.getItemBySlot(slot);
        player.setItemSlot(slot, armorStandItem);
        armorStand.setItemSlot(slot, playerItem);
    }

    @Override
    public void initialize() {
        //Swaps all the armor slots of armor stand and player when shift right clicked
        UseEntityCallback.EVENT.register(((player, world, hand, target, entityHitResult) -> {
            if (player.isShiftKeyDown() && VanillaTweaks.config.tweaks.enableArmorStandSwapping && !world.isClientSide() && !player.isSpectator()
                    && target instanceof ArmorStand armorStand) {
                for (EquipmentSlot equipmentSlotType : EquipmentSlot.values()) {
                    if (equipmentSlotType.getType() == EquipmentSlot.Type.ARMOR) {
                        swapSlot(player, armorStand, equipmentSlotType);
                    }
                }
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }));
    }
}