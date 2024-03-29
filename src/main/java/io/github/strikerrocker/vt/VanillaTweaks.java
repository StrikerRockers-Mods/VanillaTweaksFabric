package io.github.strikerrocker.vt;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.content.ContentModule;
import io.github.strikerrocker.vt.enchantments.EnchantmentModule;
import io.github.strikerrocker.vt.loot.LootModule;
import io.github.strikerrocker.vt.tweaks.TweaksModule;
import io.github.strikerrocker.vt.world.WorldModule;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VanillaTweaks implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final List<Module> modules = new ArrayList<>();
    /**
     * Vanilla Tweaks mod ID
     */
    public static final String MOD_ID = "vanillatweaks";
    public static ModConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        Collections.addAll(modules, new ContentModule(), new EnchantmentModule(), new LootModule(), new TweaksModule(), new WorldModule(), new RecipeModule());
        modules.forEach(Module::initialize);
        LOGGER.info("Setup Complete");
    }
}