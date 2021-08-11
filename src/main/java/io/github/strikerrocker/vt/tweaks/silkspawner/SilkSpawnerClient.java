package io.github.strikerrocker.vt.tweaks.silkspawner;

import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class SilkSpawnerClient extends Feature {
    @Override
    public void initialize() {
        // Shows the name of the mob in tooltip
        ItemTooltipCallback.EVENT.register((stack, tooltipContext, list) -> {
            if (stack.hasNbt()) {
                NbtCompound spawnerDataNBT = stack.getOrCreateNbt().getCompound(SilkSpawner.SPAWNER_TAG);
                if (!spawnerDataNBT.isEmpty()) {
                    DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.readNbt(MinecraftClient.getInstance().world, BlockPos.ORIGIN, spawnerDataNBT);
                    Entity ent = DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.getRenderedEntity(MinecraftClient.getInstance().world);
                    if (ent != null)
                        list.add(ent.getDisplayName());
                }
            }
        });
    }
}
