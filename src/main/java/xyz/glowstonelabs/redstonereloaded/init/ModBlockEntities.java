package xyz.glowstonelabs.redstonereloaded.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.glowstonelabs.redstonereloaded.RedstoneReloaded;
import xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator.BubbleElevatorBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<BubbleElevatorBlockEntity> BUBBLE_ELEVATOR_BLOCK_BE =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(RedstoneReloaded.MOD_ID, "bubble_elevator_block_be"),
                    FabricBlockEntityTypeBuilder.create(
                            BubbleElevatorBlockEntity::new,
                            ModBlocks.BUBBLE_ELEVATOR_BLOCK
                    ).build()
            );

    public static void load() {
        RedstoneReloaded.LOGGER.info("Registering ModBlockEntities for " + RedstoneReloaded.MOD_ID);
    }
}