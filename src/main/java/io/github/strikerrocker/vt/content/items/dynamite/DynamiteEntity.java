package io.github.strikerrocker.vt.content.items.dynamite;

import io.github.strikerrocker.vt.content.ClientContentModule;
import io.github.strikerrocker.vt.content.items.Items;
import io.github.strikerrocker.vt.misc.EntitySpawnPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class DynamiteEntity extends ThrownItemEntity {
    private static final int WET_TICKS = 20;
    private static final TrackedData<Integer> TICKS_WET;
    private static final TrackedData<Integer> TICKS_SINCE_WET;

    static {
        TICKS_WET = DataTracker.registerData(DynamiteEntity.class, TrackedDataHandlerRegistry.INTEGER);
        TICKS_SINCE_WET = DataTracker.registerData(DynamiteEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    public DynamiteEntity(EntityType<? extends ThrownItemEntity> type, World world) {
        super(type, world);
    }

    DynamiteEntity(World world, LivingEntity entity) {
        super(Items.DYNAMITE_TYPE, entity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.DYNAMITE;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.DYNAMITE);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(TICKS_WET, 0);
        dataTracker.startTracking(TICKS_SINCE_WET, WET_TICKS);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isClient) {
            if (this.isWet()) {
                getDataTracker().set(TICKS_WET, getDataTracker().get(TICKS_WET) + 1);
            } else
                getDataTracker().set(TICKS_WET, 0);
            if (getDataTracker().get(TICKS_WET) == 0)
                getDataTracker().set(TICKS_SINCE_WET, getDataTracker().get(TICKS_SINCE_WET) + 1);
            else
                getDataTracker().set(TICKS_SINCE_WET, 0);
        }
        if (getDataTracker().get(TICKS_SINCE_WET) < WET_TICKS && !this.isSubmergedInWater())
            for (int i = 0; i < 3; ++i) {
                float xOffset = (random.nextFloat() * 2 - 1) * getWidth() * 0.5F;
                float zOffset = (random.nextFloat() * 2 - 1) * getWidth() * 0.5F;
                BlockPos pos = getBlockPos();
                world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + xOffset, pos.getY(), pos.getZ() + zOffset, getVelocity().x, getVelocity().y, getVelocity().z);
            }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if (!world.isClient) {
            if (getDataTracker().get(TICKS_SINCE_WET) < WET_TICKS) {
                this.dropItem(Items.DYNAMITE);
                this.remove();
            } else {
                if (hitResult instanceof EntityHitResult && ((EntityHitResult) hitResult).getEntity() instanceof DynamiteEntity) {
                    return;
                } else {
                    BlockPos pos = getBlockPos();
                    world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 3F, Explosion.DestructionType.BREAK);
                }
            }
            this.remove();
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, ClientContentModule.PACKET_ID);
    }

    @Override
    public boolean shouldRender(double distance) {
        return true;
    }
}