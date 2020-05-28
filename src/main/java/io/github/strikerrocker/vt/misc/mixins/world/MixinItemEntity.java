package io.github.strikerrocker.vt.misc.mixins.world;

import io.github.strikerrocker.vt.world.WorldModule;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.PlantBlock;
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

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity extends Entity {

    public MixinItemEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getStack();

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;tick()V"))
    public void tick(CallbackInfo callbackInfo) {
        if (WorldModule.config.selfPlanting && age > 20 && getStack().getItem() instanceof BlockItem) {

            ItemStack stack = this.getStack();
            BlockPos pos = getBlockPos();
            if (world.getBlockState(pos).getBlock() instanceof FarmlandBlock)
                pos = pos.add(0, 1, 0);
            BlockState state = world.getBlockState(pos);
            if (((BlockItem) stack.getItem()).getBlock() instanceof PlantBlock) {
                System.out.println("pos=" + pos);
                System.out.println("state=" + state);
                System.out.println("Block=" + ((BlockItem) stack.getItem()).getBlock());
                PlantBlock plantBlock = (PlantBlock) ((BlockItem) stack.getItem()).getBlock();
                if (plantBlock.canPlaceAt(state, world, pos) && state.getBlock() instanceof AirBlock) {
                    world.setBlockState(pos, ((BlockItem) stack.getItem()).getBlock().getDefaultState());
                    stack.decrement(1);
                }
            }
        }
    }
}