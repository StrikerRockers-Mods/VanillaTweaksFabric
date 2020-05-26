package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.base.Feature;

public class StackSizes extends Feature {

   /*TODO
   private ForgeConfigSpec.IntValue boatStackSize;
    private ForgeConfigSpec.IntValue enderPearlStackSize;

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        boatStackSize = builder
                .translation("config.vanillatweaks:boatStackSize")
                .comment("Stack size for boat")
                .defineInRange("boatStackSize", 4, 1, 64);
        enderPearlStackSize = builder
                .translation("config.vanillatweaks:enderPearlStackSize")
                .comment("Stack size for ender pearls")
                .defineInRange("enderPearlStackSize", 64, 1, 64);

    }

    @Override
    public void configChanged(ModConfig.ModConfigEvent event) {
        if (event.getConfig().getSpec() == module.getConfigSpec()) {
            ObfuscationReflectionHelper.setPrivateValue(Item.class, Items.ACACIA_BOAT, boatStackSize.get(), "field_77777_bU");
            ObfuscationReflectionHelper.setPrivateValue(Item.class, Items.BIRCH_BOAT, boatStackSize.get(), "field_77777_bU");
            ObfuscationReflectionHelper.setPrivateValue(Item.class, Items.OAK_BOAT, boatStackSize.get(), "field_77777_bU");
            ObfuscationReflectionHelper.setPrivateValue(Item.class, Items.DARK_OAK_BOAT, boatStackSize.get(), "field_77777_bU");
            ObfuscationReflectionHelper.setPrivateValue(Item.class, Items.JUNGLE_BOAT, boatStackSize.get(), "field_77777_bU");
            ObfuscationReflectionHelper.setPrivateValue(Item.class, Items.SPRUCE_BOAT, boatStackSize.get(), "field_77777_bU");
            ObfuscationReflectionHelper.setPrivateValue(Item.class, Items.ENDER_PEARL, enderPearlStackSize.get(), "field_77777_bU");
        }
    }*/
}