package io.github.strikerrocker.vt.enchantments;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Module;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

import static io.github.strikerrocker.vt.VanillaTweaks.MODID;

public class EnchantmentModule extends Module {

    public static Map<String, Enchantment> enchantments = new HashMap<>();

    @Override
    public void initialize() {
        //TODO
        if (VanillaTweaks.config.enchanting.enableBlazing)
            enchantments.put("blazing", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "blazing"), new BlazingEnchantment()));
        if (VanillaTweaks.config.enchanting.enableHops)
            enchantments.put("hops", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "hops"), new HopsEnchantment()));
        if (VanillaTweaks.config.enchanting.enableNimble)
            enchantments.put("nimble", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "nimble"), new NimbleEnchantment()));
        if (VanillaTweaks.config.enchanting.enableSiphon)
            enchantments.put("siphon", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "siphon"), new SiphonEnchantment()));
        if (VanillaTweaks.config.enchanting.enableVeteran)
            enchantments.put("veteran", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "veteran"), new VeteranEnchantment()));
        if (VanillaTweaks.config.enchanting.enableVigor)
            enchantments.put("vigor", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "vigor"), new VigorEnchantment()));
        if (VanillaTweaks.config.enchanting.enableHoming)
            enchantments.put("homing", Registry.register(Registry.ENCHANTMENT, new Identifier(MODID, "homing"), new HomingEnchantment()));
        super.initialize();
    }

    @Override
    public void addFeatures() {
    }
}