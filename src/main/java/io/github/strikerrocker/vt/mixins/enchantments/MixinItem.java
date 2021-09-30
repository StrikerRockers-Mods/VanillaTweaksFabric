package io.github.strikerrocker.vt.mixins.enchantments;

import io.github.strikerrocker.vt.enchantments.EnchantmentModule;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class MixinItem {
    //Adds glowing effect to the targeted entity.
    @Inject(method = "usageTick", at = @At("HEAD"))
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks, CallbackInfo ci) {
        if (!world.isClient()) {
            int lvl = EnchantmentHelper.getLevel(EnchantmentModule.HOMING, stack);
            if (lvl > 0) {
                LivingEntity target = EnchantmentModule.getTarget(world, user, lvl);
                if (target != null) {
                    target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 4, 1, true, false, false));
                }
            }
        }
    }
}
