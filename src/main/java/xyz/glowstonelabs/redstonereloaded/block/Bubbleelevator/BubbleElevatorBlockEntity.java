package xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import xyz.glowstonelabs.redstonereloaded.init.ModBlockEntityTypes;

public class BubbleElevatorBlockEntity extends BlockEntity {
    public BubbleElevatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BUBBLE_ELEVATOR, pos, state);
    }
}
