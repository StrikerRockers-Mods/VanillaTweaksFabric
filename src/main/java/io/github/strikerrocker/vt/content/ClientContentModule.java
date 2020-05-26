package io.github.strikerrocker.vt.content;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalBlockEntityRenderer;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalContainer;
import io.github.strikerrocker.vt.content.blocks.pedestal.PedestalScreen;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.text.TranslatableText;

import static io.github.strikerrocker.vt.content.blocks.Blocks.PEDESTAL_IDENTIFIER;
import static io.github.strikerrocker.vt.content.blocks.Blocks.PEDESTAL_TYPE;
import static io.github.strikerrocker.vt.content.items.Items.DYNAMITE_TYPE;

public class ClientContentModule extends Module {

    @Override
    public void addFeatures() {

    }

    @Override
    public void initialize() {
        BlockEntityRendererRegistry.INSTANCE.register(PEDESTAL_TYPE, PedestalBlockEntityRenderer::new);
        ScreenProviderRegistry.INSTANCE.<PedestalContainer>registerFactory(PEDESTAL_IDENTIFIER, container -> new PedestalScreen(container, MinecraftClient.getInstance().player.inventory, new TranslatableText("block.vanillatweaks.pedestal")));
        EntityRendererRegistry.INSTANCE.register(DYNAMITE_TYPE, ((entityRenderDispatcher, context) -> new FlyingItemEntityRenderer<>(entityRenderDispatcher, MinecraftClient.getInstance().getItemRenderer())));
        //TODO fix pedestal and dynamite renderer
    }
}