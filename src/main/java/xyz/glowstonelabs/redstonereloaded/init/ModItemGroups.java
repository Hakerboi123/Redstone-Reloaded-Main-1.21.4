package xyz.glowstonelabs.redstonereloaded.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.glowstonelabs.redstonereloaded.RedstoneReloaded;

public class ModItemGroups {
    public static final ItemGroup REDSTONE_RELOADED = Registry.register(Registries.ITEM_GROUP, Identifier.of(RedstoneReloaded.MOD_ID, "redstone_reloaded"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.redstone_reloaded"))
                    .icon(() -> new ItemStack(Items.REDSTONE.asItem())).entries((displayContext, entries) -> {
                        entries.add(ModItems.BUBBLE_ELEVATOR_BLOCK);
                    }).build());

    public static void load() {RedstoneReloaded.LOGGER.info("Registering ModItemGroups for " + RedstoneReloaded.MOD_ID);}

}
