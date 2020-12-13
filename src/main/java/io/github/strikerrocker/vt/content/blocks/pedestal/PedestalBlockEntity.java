package io.github.strikerrocker.vt.content.blocks.pedestal;

import io.github.strikerrocker.vt.content.blocks.Blocks;
import io.github.strikerrocker.vt.misc.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

public class PedestalBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public PedestalBlockEntity() {
        super(Blocks.PEDESTAL_TYPE);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new PedestalScreenHandler(syncId, inv, this);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("block.vanillatweaks.pedestal");
    }

    @Override
    public CompoundTag toInitialChunkDataTag() {
        return toTag(new CompoundTag());
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return new BlockEntityUpdateS2CPacket(pos, 0, toInitialChunkDataTag());
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        inventory = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }
}
