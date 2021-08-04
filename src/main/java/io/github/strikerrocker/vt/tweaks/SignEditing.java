package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.util.ActionResult;

public class SignEditing extends Feature {

    /**
     * Open sign editor even when its already placed
     */
    @Override
    public void initialize() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            BlockEntity te = world.getBlockEntity(blockHitResult.getBlockPos());
            if (te instanceof SignBlockEntity sign && VanillaTweaks.config.tweaks.enableSignEditing && !world.isClient) {
                playerEntity.openEditSignScreen(sign);
                playerEntity.swingHand(hand);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });
    }
}
