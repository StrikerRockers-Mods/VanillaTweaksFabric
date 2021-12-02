package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.BeehiveBlock;

public class BeehiveTooltips extends Feature {

    /**
     * Shows the number of bees and honey level of bee hives
     */
    @Override
    public void initialize() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            if (context.isAdvanced() && stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof BeehiveBlock) {
                CompoundTag tag = stack.getOrCreateTag();
                CompoundTag beTag = tag.getCompound("BlockEntityTag");
                ListTag bees = beTag.getList("Bees", 10);
                CompoundTag blockStateTag = tag.getCompound("BlockStateTag");
                String honeyLvl = blockStateTag.getString("honey_level");
                lines.add(new TranslatableComponent("vanillatweaks.bees").append(String.format("%d", bees.size())));
                lines.add(new TranslatableComponent("vanillatweaks.honey.lvl").append(String.format("%s", honeyLvl)));
            }
        });
    }
}