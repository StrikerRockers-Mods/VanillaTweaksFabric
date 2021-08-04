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
                NbtCompound stackTag = stack.getNbt();
                if (stackTag != null) {
                    NbtCompound spawnerDataNBT = stackTag.getCompound(SilkSpawner.SPAWNER_TAG);
                    if (!spawnerDataNBT.isEmpty()) {
                        DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.readNbt(MinecraftClient.getInstance().world, new BlockPos(0, 0, 0), spawnerDataNBT);
                        Entity ent = DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.getRenderedEntity(MinecraftClient.getInstance().world);
                        if (ent != null && ent.getDisplayName() != null)
                            list.add(ent.getDisplayName());
                    }
                }
            }
        });
    }
}
