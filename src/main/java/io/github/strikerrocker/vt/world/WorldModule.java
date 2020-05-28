package io.github.strikerrocker.vt.world;

import io.github.strikerrocker.vt.base.Module;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;

public class WorldModule extends Module {

    public static WorldConfig config;

    @Override
    public void addFeatures() {
        registerFeature("realistic_relationship", new RealisticRelationship());
    }

    @Override
    public void initialize() {
        AutoConfig.register(WorldConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(WorldConfig.class).getConfig();
        super.initialize();
    }
}