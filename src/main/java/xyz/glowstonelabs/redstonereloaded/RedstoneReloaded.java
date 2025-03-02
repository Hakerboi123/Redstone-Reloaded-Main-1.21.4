package xyz.glowstonelabs.redstonereloaded;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.glowstonelabs.redstonereloaded.init.ModBlockEntityTypes;
import xyz.glowstonelabs.redstonereloaded.init.ModBlocks;
import xyz.glowstonelabs.redstonereloaded.init.ModItemGroups;
import xyz.glowstonelabs.redstonereloaded.init.ModItems;

public class RedstoneReloaded implements ModInitializer {
	public static final String MOD_ID = "redstone-reloaded";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		LOGGER.info("Loading...");
		ModBlocks.loadRRModBlocks();
		ModItemGroups.loadRRModItemGroups();
		ModBlockEntityTypes.loadRRModBlockEntities();
		ModItems.loadRRModItems();

		LOGGER.info("Loaded!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}