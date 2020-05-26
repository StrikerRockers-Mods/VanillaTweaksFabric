package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.base.Feature;


public class SquishySponges extends Feature {
    /*TODO
    private ForgeConfigSpec.BooleanValue squishySponge;

    private static void turnIntoWater(World worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos).getMaterial().isReplaceable()) {
            if (worldIn.dimension.doesWaterVaporize()) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            } else {
                worldIn.setBlockState(pos, Fluids.WATER.getDefaultState().getBlockState(), 11);
                worldIn.neighborChanged(pos, Blocks.WATER, pos);
            }
        }
    }

    @Override
    public boolean usesEvents() {
        return true;
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        squishySponge = builder
                .translation("config.vanillatweaks:squishySponge")
                .comment("Do you want to neglect fall damage and get water spilled when you fall on a wet sponge?")
                .define("squishySponge", true);
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        World world = event.getEntity().getEntityWorld();
        LivingEntity entity = event.getEntityLiving();
        BlockPos pos = entity.getPosition().down();
        if (entity instanceof PlayerEntity && squishySponge.get() && world.getBlockState(pos).getBlock() instanceof WetSpongeBlock) {
            turnIntoWater(world, pos.offset(Direction.Plane.HORIZONTAL.random(world.rand)));
            world.setBlockState(pos, Blocks.SPONGE.getDefaultState());
            event.setCanceled(true);
        }
    }*/
}