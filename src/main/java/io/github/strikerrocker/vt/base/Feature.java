package io.github.strikerrocker.vt.base;

public abstract class Feature {
    protected Module module;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void initialize() {
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
