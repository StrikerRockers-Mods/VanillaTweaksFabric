package io.github.strikerrocker.vt.misc.mixins.content;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.content.items.Items;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(at = @At("RETURN"), method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", cancellable = true)
    private void getZoomedFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> info) {
        double fov = info.getReturnValueD();
        PlayerEntity player = client.player;
        if (player != null && VanillaTweaks.config.content.binocularZoomAmount != 0) {
            ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);
            if (!helmet.isEmpty() && helmet.getItem() == Items.BINOCULARS) {
                info.setReturnValue(fov / VanillaTweaks.config.content.binocularZoomAmount);
            }
        }
    }
}