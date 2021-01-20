package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.tweaks.silkspawner.SilkSpawnerClient;

public class ClientTweaksModule extends Module {

    @Override
    public void addFeatures() {
        registerFeature("silk_spawner_client", new SilkSpawnerClient());
        registerFeature("beehive_tooltip", new BeehiveTooltips());
    }
}