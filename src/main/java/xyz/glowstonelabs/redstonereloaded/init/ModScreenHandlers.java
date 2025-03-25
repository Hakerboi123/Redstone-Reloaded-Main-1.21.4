package xyz.glowstonelabs.redstonereloaded.init;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import xyz.glowstonelabs.redstonereloaded.RedstoneReloaded;
import xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator.BubbleElevatorBlockScreenHandler;

public class ModScreenHandlers {
    public static final ScreenHandlerType<BubbleElevatorBlockScreenHandler> BUBBLE_ELEVATOR_BLOCK_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(RedstoneReloaded.MOD_ID, "bubble_elevator_block_screen_handler"),
                    new ExtendedScreenHandlerType<>(BubbleElevatorBlockScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void load() {
        RedstoneReloaded.LOGGER.info("Registering ModScreenHandlers for " + RedstoneReloaded.MOD_ID);
    }
}
