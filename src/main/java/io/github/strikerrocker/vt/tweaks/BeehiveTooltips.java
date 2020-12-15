package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.base.Feature;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.TranslatableText;

public class BeehiveTooltips extends Feature {
    @Override
    public void initialize() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            if (context.isAdvanced()) {
                if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof BeehiveBlock) {
                    CompoundTag tag = stack.getTag();
                    if (tag != null) {
                        CompoundTag beTag = tag.getCompound("BlockEntityTag");
                        ListTag bees = beTag.getList("Bees", 10);
                        int numBees = bees.size();
                        CompoundTag blockStateTag = tag.getCompound("BlockStateTag");
                        String honeyLvl = blockStateTag.getString("honey_level");
                        lines.add(new TranslatableText("vanillatweaks.bees").append(String.format("%d", numBees)));
                        lines.add(new TranslatableText("vanillatweaks.honey.lvl").append(String.format("%s", honeyLvl)));
                    }
                }
            }
        });
    }
}