package io.github.strikerrocker.vt.content.blocks.pedestal;

import io.github.strikerrocker.vt.content.blocks.Blocks;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class PedestalBlock extends BlockWithEntity implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final VoxelShape BASE = Block.createCuboidShape(0.5, 0.0, 0.5, 15.5, 1.0, 15.5);
    private final VoxelShape DECO1 = Block.createCuboidShape(2.0, 1.0, 2.0, 14.0, 2.0, 14.0);
    private final VoxelShape PILLAR = Block.createCuboidShape(4.5, 2.0, 4.5, 11.5, 12.0, 11.5);
    private final VoxelShape DECO2 = Block.createCuboidShape(2.0, 12.0, 2.0, 14.0, 13.0, 14.0);
    private final VoxelShape TOP = Block.createCuboidShape(1, 13.0, 1, 15.0, 15.0, 15.0);
    private final VoxelShape PEDESTAL_VOXEL_SHAPE = VoxelShapes.union(BASE, DECO1, PILLAR, DECO2, TOP);

    public PedestalBlock() {
        super(Block.Settings.of(Material.STONE, MaterialColor.GRAY_TERRACOTTA).strength(2.0f, 10.0f));
        this.setDefaultState(this.getStateManager().getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            ItemStack heldItem = player.getStackInHand(hand);
            PedestalBlockEntity blockEntity = (PedestalBlockEntity) world.getBlockEntity(pos);
            if (player.isSneaking()) {
                ContainerProviderRegistry.INSTANCE.openContainer(Blocks.PEDESTAL_IDENTIFIER, player, packetByteBuf -> packetByteBuf.writeBlockPos(pos));
            } else {
                ItemStack stack = blockEntity.getInvStack(0);
                if (heldItem.isEmpty()) {
                    player.inventory.offerOrDrop(world, stack);
                    blockEntity.removeInvStack(0);
                } else {
                    player.setStackInHand(hand, stack);
                    blockEntity.setInvStack(0, heldItem);
                }
                blockEntity.markDirty();
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PedestalBlockEntity) {
                ItemScatterer.spawn(world, pos, (PedestalBlockEntity) blockEntity);
                world.updateHorizontalAdjacent(pos, this);
            }
            super.onBlockRemoved(state, world, pos, newState, moved);
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return Container.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
        return PEDESTAL_VOXEL_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new PedestalBlockEntity();
    }

    @Override
    public boolean isSimpleFullBlock(BlockState state, BlockView view, BlockPos pos) {
        return false;
    }
}