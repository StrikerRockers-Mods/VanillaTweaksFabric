package io.github.strikerrocker.vt.tweaks;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "vanillatweaks/tweaks")
public class TweaksConfig implements ConfigData {
    @Comment("Want an way to swap armor with armor stand?")
    public boolean enableArmorStandSwapping = true;
    @Comment("Want to shift click item frame to rotate in the reverse direction?")
    public boolean itemFrameRotateBackwards = true;
    @Comment("Want baby zombies to burn by the light of the day?")
    public boolean babyZombieBurnInDaylight = true;
    @Comment("Want creeper's to burn in daylight?")
    public boolean creeperBurnInDaylight = true;
    @Comment("Want to shear nametag of named entities?")
    public boolean shearOffNameTag = true;
    @Comment("Want hoe to act like a sickle?")
    public boolean hoeActsAsSickle = true;
    @Comment("Want a way to clear text in signs?")
    public boolean enableSignEditing = true;
    @Comment("Want TNT to ignite when next to lava or magma block?")
    public boolean tntIgnition = true;
    @Comment("Want the ability to move spawners with silk touch?")
    public boolean enableSilkSpawner = true;
    @Comment("Want to be able to sit on slabs and stairs?")
    public boolean enableSit = true;
}
