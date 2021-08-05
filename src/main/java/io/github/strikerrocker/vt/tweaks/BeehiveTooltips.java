package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.TranslatableText;

public class BeehiveTooltips extends Feature {

    /**
     * Shows the number of bees and honey level of bee hives
     */
    @Override
    public void initialize() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            if (context.isAdvanced() && stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof BeehiveBlock) {
                NbtCompound tag = stack.getNbt();
                if (tag != null) {
                    NbtCompound beTag = tag.getCompound("BlockEntityTag");
                    NbtList bees = beTag.getList("Bees", 10);
                    int numBees = bees.size();
                    NbtCompound blockStateTag = tag.getCompound("BlockStateTag");
                    String honeyLvl = blockStateTag.getString("honey_level");
                    lines.add(new TranslatableText("vanillatweaks.bees").append(String.format("%d", numBees)));
                    lines.add(new TranslatableText("vanillatweaks.honey.lvl").append(String.format("%s", honeyLvl)));
                }
            }
        });
    }
}