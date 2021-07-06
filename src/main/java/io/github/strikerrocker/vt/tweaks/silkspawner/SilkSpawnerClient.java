package io.github.strikerrocker.vt.tweaks.silkspawner;

import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

public class SilkSpawnerClient extends Feature {
    @Override
    public void initialize() {
        ItemTooltipCallback.EVENT.register((stack, tooltipContext, list) -> {
            if (stack.hasTag()) {
                NbtCompound stackTag = stack.getTag();
                assert stackTag != null;
                NbtCompound spawnerDataNBT = stackTag.getCompound(SilkSpawner.SPAWNER_TAG);
                if (!spawnerDataNBT.isEmpty()) {
                    DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.fromTag(spawnerDataNBT);
                    Entity ent = DummySpawnerLogic.DUMMY_SPAWNER_LOGIC.getRenderedEntity();
                    if (ent != null && ent.getDisplayName() != null)
                        list.add(ent.getDisplayName());
                }
            }
        });
    }
}
