package io.github.strikerrocker.vt.world;

import io.github.strikerrocker.vt.base.Feature;

public class SelfPlanting extends Feature {

    /*TODO
    private ForgeConfigSpec.IntValue despawnTime;
    private ForgeConfigSpec.IntValue chanceToPlant;
    private ForgeConfigSpec.BooleanValue selfPlanting;

    @Override
    public boolean usesEvents() {
        return true;
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        selfPlanting = builder
                .translation("config.vanillatweaks:selfPlanting")
                .comment("Want seeds to auto-plant themselves when broken?")
                .define("selfPlanting", true);
        despawnTime = builder
                .translation("config.vanillatweaks:despawnTime")
                .comment("How long a plant should take to despawn (and attempt to plant) Default Minecraft is 6000.")
                .defineInRange("despawnTime", 1000, 0, Integer.MAX_VALUE);
        chanceToPlant = builder
                .translation("config.vanillatweaks:chanceToPlant")
                .comment("Percentage chance to plant")
                .defineInRange("chanceToPlant", 100, 0, 100);
    }

    @SubscribeEvent
    public void itemDecay(ItemExpireEvent event) {
        plant(event.getEntityItem());
    }

    @SubscribeEvent
    public void itemToss(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote && event.getEntity() instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) event.getEntity();
            Item item = itemEntity.getItem().getItem();
            Block block = Block.getBlockFromItem(item);
            if (selfPlanting.get() && item instanceof BlockItem && block instanceof IPlantable && !(block instanceof FlowerBlock)) {
                itemEntity.lifespan = despawnTime.get();
            }
        }
    }


    public void plant(ItemEntity entity) {
        World world = entity.getEntityWorld();
        ItemStack stack = entity.getItem().copy();
        Item item = stack.getItem();
        Block block = Block.getBlockFromItem(item);
        BlockPos entityPos = new BlockPos(entity);
        if (selfPlanting.get() && item instanceof BlockItem && block instanceof IPlantable && !(block instanceof FlowerBlock)) {
            if (world.rand.nextInt() > chanceToPlant.get()) {
                FakePlayer player = Utils.getFakePlayer(world);
                Vec3d entityVec = new Vec3d(entityPos.getX(), entityPos.getY(), entityPos.getZ());
                BlockRayTraceResult rayTraceResult = entity.world.rayTraceBlocks(
                        new RayTraceContext(entityVec.add(0, 2, 0), entityVec.add(0, -1, 0), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity));
                ActionResultType result = item.onItemUse(new ItemUseContext(player, Hand.MAIN_HAND, rayTraceResult));
                if (result == ActionResultType.SUCCESS) {
                    if (stack.getCount() > 0) {
                        stack.shrink(1);
                    }
                }
                if (stack.getCount() > 0) {
                    world.addEntity(new ItemEntity(world, entityPos.getX(), entityPos.getY() + 1, entityPos.getZ(), stack));
                }
            }
        }
    }*/
}