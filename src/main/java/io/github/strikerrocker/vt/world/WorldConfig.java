package io.github.strikerrocker.vt.world;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "vanillatweaks/world")
public class WorldConfig implements ConfigData {
    @Comment("Is realistic predator/prey relationships activated?")
    public boolean realisticRelationship = true;
    @Comment("Want seeds to auto-plant themselves when broken?")
    public boolean selfPlanting = true;
}
