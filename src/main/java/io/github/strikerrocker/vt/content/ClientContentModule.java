package io.github.strikerrocker.vt.content;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.content.blocks.Blocks;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalBlockEntityRenderer;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalScreen;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

import static io.github.strikerrocker.vt.content.blocks.Blocks.PEDESTAL_TYPE;
import static io.github.strikerrocker.vt.content.items.Items.DYNAMITE_TYPE;

public class ClientContentModule extends Module {


    @Override
    public void addFeatures() {

    }

    @Override
    public void initialize() {
        BlockEntityRendererRegistry.INSTANCE.register(PEDESTAL_TYPE, PedestalBlockEntityRenderer::new);
        ScreenRegistry.register(Blocks.PEDESTAL_SCREEN_HANDLER, PedestalScreen::new);
        EntityRendererRegistry.INSTANCE.register(DYNAMITE_TYPE, ((entityRenderDispatcher, context) -> new FlyingItemEntityRenderer<>(entityRenderDispatcher, MinecraftClient.getInstance().getItemRenderer())));
        //TODO fix pedestal and dynamite renderer
    }
}