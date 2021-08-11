package io.github.strikerrocker.vt.content.items.dynamite;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DynamiteItem extends Item {
    public DynamiteItem() {
        super(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemstack = user.getStackInHand(hand);
        if (!user.isCreative())
            itemstack.decrement(1);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, VanillaTweaks.config.content.dynamiteCooldown);
        if (!world.isClient) {
            DynamiteEntity dynamite = new DynamiteEntity(world, user);
            dynamite.setItem(itemstack);
            dynamite.setProperties(user, user.getPitch(), user.getYaw(), 0, 1.5F, 0);
            world.spawnEntity(dynamite);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemstack, world.isClient);
    }
}