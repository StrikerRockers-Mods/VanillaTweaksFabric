package io.github.strikerrocker.vt.content.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;

public class CharcoalBlock extends Block {
    CharcoalBlock() {
        super(Block.Settings.of(Material.STONE, MaterialColor.BLACK).strength(5.0f, 10.0f));
    }

    /*@Override
    public boolean isFireSource(BlockState state, IBlockReader world, BlockPos pos, Direction side) {
        return true;
    }*/
}
