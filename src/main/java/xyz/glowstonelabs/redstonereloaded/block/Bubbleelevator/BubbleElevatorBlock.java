package xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.glowstonelabs.redstonereloaded.init.ModBlocks;
import static net.minecraft.state.property.Properties.FACING;

public class BubbleElevatorBlock extends BlockWithEntity implements BlockEntityProvider{
    public static final BooleanProperty HAS_WATER = ModBlocks.HAS_WATER;

    public static final MapCodec<BubbleElevatorBlock> CODEC = BubbleElevatorBlock.createCodec(BubbleElevatorBlock::new);

    public BubbleElevatorBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(HAS_WATER, false));
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                // Bottom layer
                Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 2.0, 13.0),
                // Middle glass layer
                Block.createCuboidShape(4.0, 2.0, 4.0, 12.0, 13.0, 12.0),
                // Top layer
                Block.createCuboidShape(3.0, 14.0, 3.0, 13.0, 16.0, 13.0)
        );
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockState blockStatetrue = state.with(HAS_WATER, true);
        BlockState blockStatefalse = state.with(HAS_WATER, false);

        if(world.getBlockEntity(pos) instanceof BubbleElevatorBlockEntity bubbleElevatorBlockEntity) {
            if (stack.isOf(Items.WATER_BUCKET) && !state.get(HAS_WATER)) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.setBlockState(pos, blockStatetrue, Block.NOTIFY_ALL);

                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                    ItemStack emptyBucket = new ItemStack(Items.BUCKET);
                    if (!player.getInventory().insertStack(emptyBucket)) {
                        player.dropItem(emptyBucket, false);
                    }
                }
                return ActionResult.SUCCESS;
            }

            if (stack.isOf(Items.BUCKET) && state.get(HAS_WATER)) {
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.setBlockState(pos, blockStatefalse, Block.NOTIFY_ALL);

                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                    ItemStack filledBucket = new ItemStack(Items.WATER_BUCKET);
                    if (!player.getInventory().insertStack(filledBucket)) {
                        player.dropItem(filledBucket, false);
                    }
                }
                return ActionResult.SUCCESS;
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HAS_WATER);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BubbleElevatorBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return super.getRenderType(state);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof BubbleElevatorBlockEntity) {
                ItemScatterer.spawn(world, pos, ((BubbleElevatorBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity var7 = world.getBlockEntity(pos);
            if (var7 instanceof BubbleElevatorBlockEntity bubbleElevatorBlockEntity) {
                player.openHandledScreen(bubbleElevatorBlockEntity);
            }
        }

        return ActionResult.SUCCESS;
    }
}
