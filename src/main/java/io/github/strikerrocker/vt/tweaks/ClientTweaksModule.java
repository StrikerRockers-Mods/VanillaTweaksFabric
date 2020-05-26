package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.tweaks.sit.SitEntity;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;

import static io.github.strikerrocker.vt.tweaks.sit.Sit.SIT_ENTITY_TYPE;

public class ClientTweaksModule extends Module {

    @Override
    public void addFeatures() {

    }

    @Override
    public void initialize() {
        EntityRendererRegistry.INSTANCE.register(SIT_ENTITY_TYPE, ((entityRenderDispatcher, context) -> new EmptyRenderer(entityRenderDispatcher)));
    }

    private static class EmptyRenderer extends EntityRenderer<SitEntity> {
        protected EmptyRenderer(EntityRenderDispatcher entityRenderDispatcher) {
            super(entityRenderDispatcher);
        }

        @Override
        public boolean shouldRender(SitEntity entity, Frustum visibleRegion, double cameraX, double cameraY, double cameraZ) {
            return false;
        }

        @Override
        public Identifier getTexture(SitEntity entity) {
            return null;
        }
    }
}
