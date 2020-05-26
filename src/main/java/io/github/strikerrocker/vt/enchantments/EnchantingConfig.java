package io.github.strikerrocker.vt.enchantments;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "vanillatweaks/enchanting")
public class EnchantingConfig implements ConfigData {
    @Comment("Want to smelt things when you mine them?")
    public boolean enableBlazing = true;
    @Comment("Want to jump more than a block high with an enchantment?")
    public boolean enableHops = true;
    @Comment("Want more speed with an enchantment?")
    public boolean enableNimble = true;
    @Comment("Don't want the zombies stealing your items when you are mining?")
    public boolean enableSiphon = true;
    @Comment("Want all the experience in the nearby area?")
    public boolean enableVeteran = true;
    @Comment("Want more health with an enchant?")
    public boolean enableVigor = true;
    @Comment("Don't want to aim but love shooting arrows?")
    public boolean enableHoming = true;
}
