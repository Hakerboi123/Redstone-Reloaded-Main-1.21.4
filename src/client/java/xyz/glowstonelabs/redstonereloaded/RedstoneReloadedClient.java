package xyz.glowstonelabs.redstonereloaded;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import xyz.glowstonelabs.redstonereloaded.renderer.BubbleElevatorBlockEntityRenderer;
import xyz.glowstonelabs.redstonereloaded.init.ModBlockEntities;
import xyz.glowstonelabs.redstonereloaded.init.ModBlocks;
import xyz.glowstonelabs.redstonereloaded.init.ModScreenHandlers;
import xyz.glowstonelabs.redstonereloaded.screen.BubbleElevatorBlockScreen;


public class RedstoneReloadedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				ModBlocks.BUBBLE_ELEVATOR_BLOCK);

		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x3495eb, ModBlocks.BUBBLE_ELEVATOR_BLOCK);
		HandledScreens.register(ModScreenHandlers.BUBBLE_ELEVATOR_BLOCK_SCREEN_HANDLER, BubbleElevatorBlockScreen::new);

		BlockEntityRendererFactories.register(ModBlockEntities.BUBBLE_ELEVATOR_BLOCK_BE, BubbleElevatorBlockEntityRenderer::new);
	}
}