package io.github.strikerrocker.vt.content;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "vanillatweaks/content")
public class ContentConfig implements ConfigData {
    @Comment("Do you want a portable crafting table?")
    public boolean enableCraftingPad = true;
    @Comment("Want a less effective but throwable TNT?")
    public boolean enableDynamite = true;
    @Comment("Want to identity slime chunks in-game?")
    public boolean enableSlimeBucket = true;
    @Comment("How much do you want the binoculars to zoom? ( 0 = disabled)")
    public double binocularZoomAmount = 4;
    @Comment("Want a food which can be obtained with eggs and heals 2.5 hunger?")
    public boolean enableFriedEgg = true;
    @Comment("Want block forms of flint, charcoal and sugar?")
    public boolean enableStorageBlocks = true;
    @Comment("Want to showcase your treasure but item frame doesn't satisfy you?")
    public boolean enablePedestal = true;
}
