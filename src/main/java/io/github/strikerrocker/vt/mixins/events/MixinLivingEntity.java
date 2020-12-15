package io.github.strikerrocker.vt.mixins.events;

import io.github.strikerrocker.vt.events.EntityEquipmentChangeCallback;
import io.github.strikerrocker.vt.events.LivingEntityDeathCallback;
import io.github.strikerrocker.vt.events.LivingEntityTickCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(at = @At(value = "INVOKE_STRING",
            target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V",
            args = "ldc=livingEntityBaseTick"
    ), method = "baseTick")
    public void updateLogic(CallbackInfo info) {
        LivingEntityTickCallback.EVENT.invoker().update((LivingEntity) (Object) this);
    }

    @Inject(method = "onDeath", at = @At("RETURN"))
    public void onDeath(DamageSource damageSource, CallbackInfo callbackInfo) {
        LivingEntityDeathCallback.EVENT.invoker().onDeath((LivingEntity) (Object) this, damageSource);
    }

    @Inject(method = "method_30129", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;areEqual(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z", shift = At.Shift.BY, by = 2),
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void entityEquip(CallbackInfoReturnable<Map<EquipmentSlot, ItemStack>> cir, Map map, EquipmentSlot[] var2, int var3, int var4, EquipmentSlot equipmentSlot, ItemStack itemStack3, ItemStack itemStack4) {
        EntityEquipmentChangeCallback.EVENT.invoker().onEntityEquipmentChange((LivingEntity) (Object) this, equipmentSlot, itemStack3, itemStack4);
    }
}