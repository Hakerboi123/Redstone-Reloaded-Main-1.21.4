package xyz.glowstonelabs.redstonereloaded.init;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xyz.glowstonelabs.redstonereloaded.RedstoneReloaded;

import java.util.function.Function;

public class ModItems {
    public static final Item BUBBLE_ELEVATOR = registerBlockItem("bubble_elevator", ModBlocks.BUBBLE_ELEVATOR);


    public static BlockItem registerBlockItem(String name, Block block) {
        return registerItem(name, settings -> new BlockItem(block, settings), new Item.Settings().useBlockPrefixedTranslationKey());
    }

    public static <I extends Item> I registerItem(String name, Function<Item.Settings, I> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(RedstoneReloaded.MOD_ID, name));
        I item = factory.apply(settings.registryKey(key));

        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
        }

        return Registry.register(Registries.ITEM, key, item);
    }

    public static Item registerItem(String id, Function<Item.Settings, Item> factory) {
        return registerItem(id, factory, new Item.Settings());
    }

    public static void loadRRModItems() {
        RedstoneReloaded.LOGGER.info("Loading Mod Items for " + RedstoneReloaded.MOD_ID + "...");
        RedstoneReloaded.LOGGER.info("Loaded!");
    }
}
