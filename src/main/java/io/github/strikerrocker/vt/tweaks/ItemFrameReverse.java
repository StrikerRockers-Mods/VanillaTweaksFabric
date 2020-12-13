package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.util.ActionResult;

public class ItemFrameReverse extends Feature {

    @Override
    public void initialize() {
        UseEntityCallback.EVENT.register(((player, world, hand, target, entityHitResult) -> {
            if (VanillaTweaks.config.tweaks.itemFrameRotateBackwards && target instanceof ItemFrameEntity && player.isSneaking()) {
                ItemFrameEntity frame = (ItemFrameEntity) target;
                int rotation = frame.getRotation() - 1;
                if (rotation < 0)
                    rotation = 7;
                frame.setRotation(rotation);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }));
    }
}
