package io.github.strikerrocker.vt.base;

import java.util.HashMap;
import java.util.Map;

public abstract class Module {
    private Map<String, Feature> features = new HashMap<>();

    public Module() {
        addFeatures();
    }

    public abstract void addFeatures();

    public void initialize() {
        features.values().forEach(Feature::initialize);
    }

    protected void registerFeature(String name, Feature feature) {
        features.put(name, feature);
        feature.setName(name);
        feature.setModule(this);
    }

}