package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.base.Module;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

import static io.github.strikerrocker.vt.VanillaTweaks.MODID;

public class EnchantmentModule extends Module {

    public static EnchantingConfig config;
    public static Map<String, Enchantment> enchantments = new HashMap<>();

    @Override
    public void initialize() {
        //TODO
        AutoConfig.register(EnchantingConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(EnchantingConfig.class).getConfig();
        if (config.enableBlazing)
            enchantments.put("blazing", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "blazing"), new BlazingEnchantment()));
        if (config.enableHops)
            enchantments.put("hops", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "hops"), new HopsEnchantment()));
        if (config.enableNimble)
            enchantments.put("nimble", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "nimble"), new NimbleEnchantment()));
        if (config.enableSiphon)
            enchantments.put("siphon", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "siphon"), new SiphonEnchantment()));
        if (config.enableVeteran)
            enchantments.put("veteran", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "veteran"), new VeteranEnchantment()));
        if (config.enableVigor)
            enchantments.put("vigor", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "vigor"), new VigorEnchantment()));
        if (config.enableHoming)
            enchantments.put("homing", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "homing"), new HomingEnchantment()));
        super.initialize();
    }

    @Override
    public void addFeatures() {
    }
}