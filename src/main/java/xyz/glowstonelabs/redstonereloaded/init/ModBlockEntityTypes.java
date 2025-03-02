package xyz.glowstonelabs.redstonereloaded.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import xyz.glowstonelabs.redstonereloaded.RedstoneReloaded;
import xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator.BubbleElevatorBlockEntity;

public class ModBlockEntityTypes {
    public static final BlockEntityType<BubbleElevatorBlockEntity> BUBBLE_ELEVATOR = register("bubble_elevator",
            FabricBlockEntityTypeBuilder.create(BubbleElevatorBlockEntity::new, ModBlocks.BUBBLE_ELEVATOR).build());

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, RedstoneReloaded.id(name), type);
    }

    public static void loadRRModBlockEntities() {
        RedstoneReloaded.LOGGER.info("Loading Mod Block Entities for " + RedstoneReloaded.MOD_ID + "...");
        RedstoneReloaded.LOGGER.info("Loaded!");
    }
}