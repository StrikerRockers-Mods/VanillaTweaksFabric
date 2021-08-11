package io.github.strikerrocker.vt.mixins.events;

import io.github.strikerrocker.vt.events.BlockBreakCallback;
import io.github.strikerrocker.vt.events.BlockPlaceCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class MixinBlock {
    /**
     * Fires block place callback
     */
    @Inject(method = "onPlaced", at = @At("RETURN"))
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo callbackInfo) {
        BlockPlaceCallback.EVENT.invoker().onPlaced(world, pos, state, placer, itemStack);
    }

    /**
     * Fires block break callback
     */
    @Inject(method = "onBreak", at = @At("RETURN"))
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo callbackInfo) {
        BlockBreakCallback.EVENT.invoker().onBreak(world, pos, state, player);
    }
}
