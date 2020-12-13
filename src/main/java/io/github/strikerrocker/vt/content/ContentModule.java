package io.github.strikerrocker.vt.content;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.content.blocks.Blocks;
import io.github.strikerrocker.vt.content.items.Items;

public class ContentModule extends Module {
    @Override
    public void addFeatures() {
        registerFeature("blocks", new Blocks());
        registerFeature("items", new Items());
    }
}