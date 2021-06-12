package io.github.strikerrocker.vt.tweaks.silkspawner;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;

public class DummySpawnerLogic extends MobSpawnerLogic {
    static final DummySpawnerLogic DUMMY_SPAWNER_LOGIC = new DummySpawnerLogic();

    @Override
    public void sendStatus(World world, BlockPos pos, int i) {

    }
}
