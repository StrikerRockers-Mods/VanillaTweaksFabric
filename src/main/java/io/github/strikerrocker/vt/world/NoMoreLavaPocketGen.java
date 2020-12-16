package io.github.strikerrocker.vt.world;

import io.github.strikerrocker.vt.VanillaTweaks;
import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class NoMoreLavaPocketGen extends Feature {
    @Override
    public void initialize() {
        if (VanillaTweaks.config.world.disableLavaPockets) {
            BiomeModifications.create(new Identifier(VanillaTweaks.MODID, "lava_pocket_removal_double")).
                    add(ModificationPhase.REMOVALS,
                            BiomeSelectors.foundInTheNether(),
                            biomeModificationContext ->
                                    biomeModificationContext.getGenerationSettings().removeFeature(RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("spring_closed"))));
            BiomeModifications.create(new Identifier(VanillaTweaks.MODID, "lava_pocket_removal")).
                    add(ModificationPhase.REMOVALS,
                            BiomeSelectors.foundInTheNether(),
                            biomeModificationContext ->
                                    biomeModificationContext.getGenerationSettings().removeFeature(RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("spring_closed_double"))));
        }
    }
}