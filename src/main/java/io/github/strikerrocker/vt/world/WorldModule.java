package io.github.strikerrocker.vt.world;

import io.github.strikerrocker.vt.base.Module;

public class WorldModule extends Module {

    @Override
    public void addFeatures() {
        registerFeature("realistic_relationship", new RealisticRelationship());
    }
}