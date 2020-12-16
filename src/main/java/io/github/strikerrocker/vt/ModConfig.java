package io.github.strikerrocker.vt;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "vanillatweaks")
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public Content content = new Content();

    @ConfigEntry.Gui.CollapsibleObject
    public Enchanting enchanting = new Enchanting();

    @ConfigEntry.Gui.CollapsibleObject
    public Loot loot = new Loot();

    @ConfigEntry.Gui.CollapsibleObject
    public Tweaks tweaks = new Tweaks();

    @ConfigEntry.Gui.CollapsibleObject
    public World world = new World();

    @ConfigEntry.Gui.CollapsibleObject
    public Recipe recipe = new Recipe();

    public static class Content {
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableCraftingPad = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableDynamite = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableSlimeBucket = true;
        public double binocularZoomAmount = 4;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableFriedEgg = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableStorageBlocks = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enablePedestal = true;
    }

    public static class Enchanting {
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableBlazing = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableHops = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableNimble = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableSiphon = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableVeteran = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean enableVigor = true;
        @ConfigEntry.Gui.RequiresRestart
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

    public static class Recipe {
        @ConfigEntry.Gui.RequiresRestart
        public boolean betterChestRecipe = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean betterRepeater = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean betterTrappedChestRecipe = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean woolToString = true;
        @ConfigEntry.Gui.RequiresRestart
        public boolean nameTag = true;
    }
}
