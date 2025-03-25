package xyz.glowstonelabs.redstonereloaded.init;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import xyz.glowstonelabs.redstonereloaded.RedstoneReloaded;
import xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator.BubbleElevatorBlock;

import java.util.function.Function;

public class ModBlocks {
    public static final BooleanProperty HAS_WATER = BooleanProperty.of("has_water");

    public static final BubbleElevatorBlock BUBBLE_ELEVATOR_BLOCK = registerBlock(
            "bubble_elevator_block",
            BubbleElevatorBlock::new,AbstractBlock.Settings.copy(Blocks.GLASS)
                    .strength(2.0f, 2.0f)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque());

    //Main Bits

    public static <B extends Block> B registerBlock(String name, Function<AbstractBlock.Settings, B> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(RedstoneReloaded.MOD_ID, name));
        B block = factory.apply(settings.registryKey(key));

        return Registry.register(Registries.BLOCK, key, block);
    }

    public static void load() {RedstoneReloaded.LOGGER.info("Registering ModBlocks for " + RedstoneReloaded.MOD_ID);}


}
