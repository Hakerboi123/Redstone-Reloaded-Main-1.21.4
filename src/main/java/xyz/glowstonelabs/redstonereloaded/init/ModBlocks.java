package xyz.glowstonelabs.redstonereloaded.init;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import xyz.glowstonelabs.redstonereloaded.RedstoneReloaded;
import xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator.BubbleElevatorBlock;

import java.util.function.Function;

public class ModBlocks {
    public static final Block BUBBLE_ELEVATOR = registerBlock(
            "bubble_elevator",
            settings -> new BubbleElevatorBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .sounds(BlockSoundGroup.METAL)
                            .pistonBehavior(PistonBehavior.NORMAL)
            ),
            AbstractBlock.Settings.create()
    );





    public static <B extends Block> B registerBlock(String name, Function<AbstractBlock.Settings, B> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedstoneReloaded.MOD_ID, name));
        B block = factory.apply(settings.registryKey(key));

        return Registry.register(Registries.BLOCK, key, block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(RedstoneReloaded.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void loadRRModBlocks() {
        RedstoneReloaded.LOGGER.info("Loading Mod Blocks for " + RedstoneReloaded.MOD_ID + "...");
        RedstoneReloaded.LOGGER.info("Loaded!");
    }
}
