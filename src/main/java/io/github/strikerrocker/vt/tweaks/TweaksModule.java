package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.base.Module;
import io.github.strikerrocker.vt.tweaks.silkspawner.SilkSpawner;
import io.github.strikerrocker.vt.tweaks.sit.Sit;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;

public class TweaksModule extends Module {
    public static TweaksConfig config;

    @Override
    public void addFeatures() {
        registerFeature("sit", new Sit());
        registerFeature("sign_editing", new SignEditing());
        registerFeature("tnt_ignition", new TNTIgnition());
        registerFeature("sickle", new Sickle());
        registerFeature("silk_spawner", new SilkSpawner());
        registerFeature("armor_stand_swap", new ArmorStandSwap());
        registerFeature("shear_nametag", new ShearNameTag());
        registerFeature("mobs_burn_daylight", new MobsBurnInDaylight());
        registerFeature("item_frame_reverse_rotate", new ItemFrameReverse());
        registerFeature("squishy_sponges", new SquishySponges());
    }

    @Override
    public void initialize() {
        AutoConfig.register(TweaksConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(TweaksConfig.class).getConfig();
        super.initialize();
    }
}