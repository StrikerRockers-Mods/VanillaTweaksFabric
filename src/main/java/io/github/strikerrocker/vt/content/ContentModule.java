package io.github.strikerrocker.vt.content;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.content.blocks.Blocks;
import io.github.strikerrocker.vt.content.items.Items;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;

public class ContentModule extends Module {
    public static ContentConfig config;

    @Override
    public void initialize() {
        AutoConfig.register(ContentConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ContentConfig.class).getConfig();
        super.initialize();
    }

    @Override
    public void addFeatures() {
        registerFeature("blocks", new Blocks());
        registerFeature("items", new Items());
    }
}