package io.github.strikerrocker.vt.mixins.tweaks;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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

    /**
     * Counter to check and perform self planting logic only every 2 seconds
     */
    int lastChecked = 0;

    public MixinItemEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getStack();

    /**
     * Handles the planting logic
     */
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;tick()V"))
    public void tick(CallbackInfo callbackInfo) {
        ItemStack stack = this.getStack();
        if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof PlantBlock plantBlock && VanillaTweaks.config.world.selfPlanting && age > 20) {
            if (!(plantBlock instanceof TallPlantBlock)) {
                if (lastChecked > 40) {
                    lastChecked = 0;
                    BlockPos pos = getBlockPos();
                    if (world.getBlockState(pos).getBlock() instanceof FarmlandBlock)
                        pos = pos.offset(Direction.UP);
                    BlockState state = world.getBlockState(pos);
                    if (plantBlock.canPlaceAt(state, world, pos) && state.getBlock() instanceof AirBlock) {
                        world.setBlockState(pos, ((BlockItem) stack.getItem()).getBlock().getDefaultState());
                        stack.decrement(1);
                    }
                } else {
                    lastChecked++;
                }
            }
        }
    }
}