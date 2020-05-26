package io.github.strikerrocker.vt.content.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkRandom;

public class SlimeBucketItem extends Item {
    SlimeBucketItem() {
        super(new Item.Settings().group(ItemGroup.MISC).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            BlockPos pos = user.getBlockPos();
            ChunkPos chunkpos = new ChunkPos(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
            boolean slime = ChunkRandom.create(chunkpos.x, chunkpos.z, world.getSeed(), 987234911L).nextInt(10) == 0;
            user.addChatMessage(new TranslatableText(slime ? "slime.chunk" : "slime.chunk.false"), true);
        }
        return new TypedActionResult<>(ActionResult.PASS, user.getStackInHand(hand));
    }
}