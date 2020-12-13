package io.github.strikerrocker.vt;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "vanillatweaks")
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public Content content = new Content();

    @ConfigEntry.Gui.CollapsibleObject
    @ConfigEntry.Gui.Excluded
    public Enchanting enchanting = new Enchanting();

    @ConfigEntry.Gui.CollapsibleObject
    public Loot loot = new Loot();

    @ConfigEntry.Gui.CollapsibleObject
    public Tweaks tweaks = new Tweaks();

    @ConfigEntry.Gui.CollapsibleObject
    public World world = new World();

    public static class Content {
        public boolean enableCraftingPad = true;
        public boolean enableDynamite = true;
        public boolean enableSlimeBucket = true;
        public double binocularZoomAmount = 4;
        public boolean enableFriedEgg = true;
        public boolean enableStorageBlocks = true;
        public boolean enablePedestal = true;
    }

    public static class Enchanting {
        public boolean enableBlazing = true;
        public boolean enableHops = true;
        public boolean enableNimble = true;
        public boolean enableSiphon = true;
        public boolean enableVeteran = true;
        public boolean enableVigor = true;
        public boolean enableHoming = true;
    }

    public static class Loot {
        @ConfigEntry.BoundedDiscrete(max = 10)
        public float batLeatherDropChance = 1f;
        public boolean namedMobsDropNameTag = true;
    }

    public static class Tweaks {
        public boolean enableArmorStandSwapping = true;
        public boolean itemFrameRotateBackwards = true;
        public boolean babyZombieBurnInDaylight = true;
        public boolean creeperBurnInDaylight = true;
        public boolean shearOffNameTag = true;
        public boolean hoeActsAsSickle = true;
        public boolean enableSignEditing = true;
        public boolean tntIgnition = true;
        public boolean enableSilkSpawner = true;
    }

    public static class World {
        /*@Comment("Is realistic predator/prey relationships activated?")
        public boolean realisticRelationship = true;*/
        public boolean selfPlanting = true;
    }
}
