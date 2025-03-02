package xyz.glowstonelabs.redstonereloaded;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import xyz.glowstonelabs.redstonereloaded.init.ModBlocks;

public class RedstoneReloadedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				ModBlocks.BUBBLE_ELEVATOR);
	}
}