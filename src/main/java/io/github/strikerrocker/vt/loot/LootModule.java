package io.github.strikerrocker.vt.loot;

import io.github.strikerrocker.vt.base.Module;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;

public class LootModule extends Module {

    public static LootConfig config;

    @Override
    public void initialize() {
        AutoConfig.register(LootConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(LootConfig.class).getConfig();
        super.initialize();
    }

    @Override
    public void addFeatures() {
        registerFeature("bat_leather", new BatLeather());
        registerFeature("mob_nametag", new MobNametag());
    }
}