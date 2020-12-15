package io.github.strikerrocker.vt.mixins.world;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * AutoPlant Functionality
 */
@Mixin(ItemEntity.class)
public abstract class MixinItemEntity extends Entity {

    public MixinItemEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getStack();

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;tick()V"))
    public void tick(CallbackInfo callbackInfo) {
        if (getStack().getItem() instanceof BlockItem && ((BlockItem) getStack().getItem()).getBlock() instanceof PlantBlock && VanillaTweaks.config.world.selfPlanting && age > 20) {
            ItemStack stack = this.getStack();
            BlockPos pos = getBlockPos();
            if (world.getBlockState(pos).getBlock() instanceof FarmlandBlock)
                pos = pos.add(0, 1, 0);
            BlockState state = world.getBlockState(pos);
            PlantBlock plantBlock = (PlantBlock) ((BlockItem) stack.getItem()).getBlock();
            if (plantBlock instanceof TallPlantBlock) {

            } else if (plantBlock.canPlaceAt(state, world, pos) && state.getBlock() instanceof AirBlock) {
                world.setBlockState(pos, ((BlockItem) stack.getItem()).getBlock().getDefaultState());
                stack.decrement(1);
            }
        }
    }
}