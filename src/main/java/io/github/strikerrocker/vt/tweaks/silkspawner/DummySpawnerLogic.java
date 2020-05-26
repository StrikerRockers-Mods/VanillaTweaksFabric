package io.github.strikerrocker.vt.tweaks.silkspawner;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;

public class DummySpawnerLogic extends MobSpawnerLogic {
    static final DummySpawnerLogic DUMMY_SPAWNER_LOGIC = new DummySpawnerLogic();

    @Override
    public void sendStatus(int status) {

    }

    @Override
    public World getWorld() {
        return MinecraftClient.getInstance().world;
    }

    @Override
    public BlockPos getPos() {
        return new BlockPos(0, 0, 0);
    }
}
