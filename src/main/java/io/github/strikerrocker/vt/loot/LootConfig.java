package io.github.strikerrocker.vt.loot;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "vanillatweaks/loot")
public class LootConfig implements ConfigData {
    @Comment("The chance of bat dropping leather, out of 1.")
    public float batLeatherDropChance = 0.1f;
    @Comment("Does a nametag drop when named mob dies?")
    public boolean namedMobsDropNameTag = true;
}
