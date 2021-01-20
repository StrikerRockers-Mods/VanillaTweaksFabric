package io.github.strikerrocker.vt.mixins.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.enchantments.BlazingEnchantment;
import io.github.strikerrocker.vt.enchantments.EnchantmentModule;
import io.github.strikerrocker.vt.enchantments.SiphonEnchantment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * Blazing and Siphon Functionality
 */
@Mixin(Block.class)
public class MixinBlock {
    @Inject(
            at = @At("RETURN"),
            method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
            cancellable = true)
    private static void dropLoot(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack tool, CallbackInfoReturnable<List<ItemStack>> ci) {
        if (EnchantmentHelper.getLevel(EnchantmentModule.enchantments.get("blazing"), tool) != 0 && VanillaTweaks.config.enchanting.enableBlazing) {
            BlazingEnchantment.blazingLogic(world, entity, tool, ci.getReturnValue());
        }
        if (EnchantmentHelper.getLevel(EnchantmentModule.enchantments.get("siphon"), tool) != 0 && VanillaTweaks.config.enchanting.enableSiphon) {
            ci.setReturnValue(SiphonEnchantment.siphonLogic(entity, ci.getReturnValue()));
        }
    }
}