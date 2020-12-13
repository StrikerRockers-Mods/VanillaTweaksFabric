package io.github.strikerrocker.vt.loot;

import io.github.strikerrocker.vt.base.Module;

public class LootModule extends Module {
    @Override
    public void addFeatures() {
        registerFeature("bat_leather", new BatLeather());
        registerFeature("mob_nametag", new MobNametag());
    }
}