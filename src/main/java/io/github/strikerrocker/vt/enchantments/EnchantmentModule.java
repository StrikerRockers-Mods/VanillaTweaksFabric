package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.base.Module;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static io.github.strikerrocker.vt.VanillaTweaks.MODID;

public class EnchantmentModule extends Module {

    public static EnchantingConfig config;

    @Override
    public void initialize() {
        //TODO
        AutoConfig.register(EnchantingConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(EnchantingConfig.class).getConfig();
        if (config.enableBlazing)
            Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "blazing"), new BlazingEnchantment());
        if (config.enableHops)
            Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "hops"), new HopsEnchantment());
        if (config.enableNimble)
            Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "nimble"), new NimbleEnchantment());
        if (config.enableSiphon)
            Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "siphon"), new SiphonEnchantment());
        if (config.enableVeteran)
            Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "veteran"), new VeteranEnchantment());
        if (config.enableVigor)
            Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "vigor"), new VigorEnchantment());
        if (config.enableHoming)
            Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "homing"), new HomingEnchantment());
    }

    @Override
    public void addFeatures() {
    }
}