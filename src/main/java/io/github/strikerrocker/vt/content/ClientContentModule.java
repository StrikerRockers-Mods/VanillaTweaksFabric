package io.github.strikerrocker.vt.content;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.content.blocks.Blocks;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalBlockEntityRenderer;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalScreen;
import io.github.strikerrocker.vt.misc.EntitySpawnPacket;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

import static io.github.strikerrocker.vt.content.blocks.Blocks.PEDESTAL_TYPE;
import static io.github.strikerrocker.vt.content.items.Items.DYNAMITE_TYPE;

public class ClientContentModule extends Module {

    public static final Identifier PACKET_ID = new Identifier(VanillaTweaks.MODID, "dynamite");

    @Override
    public void addFeatures() {

    }

    @Override
    public void initialize() {
        ScreenRegistry.register(Blocks.PEDESTAL_SCREEN_HANDLER, PedestalScreen::new);
        BlockEntityRendererRegistry.INSTANCE.register(PEDESTAL_TYPE, PedestalBlockEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(DYNAMITE_TYPE, ((entityRenderDispatcher, context) -> new FlyingItemEntityRenderer<>(entityRenderDispatcher, context.getItemRenderer())));
        receiveEntityPacket();
    }

    public void receiveEntityPacket() {
        ClientSidePacketRegistry.INSTANCE.register(PACKET_ID, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.pitch = pitch;
                e.yaw = yaw;
                e.setEntityId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }
}