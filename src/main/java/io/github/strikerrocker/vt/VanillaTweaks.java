package io.github.strikerrocker.vt;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.content.ContentModule;
import io.github.strikerrocker.vt.enchantments.EnchantmentModule;
import io.github.strikerrocker.vt.loot.LootModule;
import io.github.strikerrocker.vt.tweaks.TweaksModule;
import io.github.strikerrocker.vt.world.WorldModule;
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
     * Vanilla Tweaks's mod ID
     */
    public static final String MODID = "vanillatweaks";

    static {
        Collections.addAll(modules, new ContentModule(), new EnchantmentModule(), new LootModule(), new TweaksModule(), new WorldModule());
    }

    @Override
    public void onInitialize() {
        modules.forEach(Module::initialize);
        LOGGER.info("Setup Complete");
    }
}