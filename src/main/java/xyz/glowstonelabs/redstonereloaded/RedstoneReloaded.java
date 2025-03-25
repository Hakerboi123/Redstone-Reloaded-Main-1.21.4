package xyz.glowstonelabs.redstonereloaded;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.glowstonelabs.redstonereloaded.init.*;

public class RedstoneReloaded implements ModInitializer {
	//Unique identifier for this mod
	public static final String MOD_ID = "redstone-reloaded";
	//Logger
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		RedstoneReloaded.LOGGER.info("------ Loading Mod Content for " + RedstoneReloaded.MOD_ID + "... ------");
		//Start loading content
		ModBlockEntities.load();
		ModBlocks.load();
		ModItemGroups.load();
		ModItems.load();
		ModScreenHandlers.load();
		//All contents loaded
		RedstoneReloaded.LOGGER.info("------ Loaded Mod Content for " + RedstoneReloaded.MOD_ID + "! ------");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}