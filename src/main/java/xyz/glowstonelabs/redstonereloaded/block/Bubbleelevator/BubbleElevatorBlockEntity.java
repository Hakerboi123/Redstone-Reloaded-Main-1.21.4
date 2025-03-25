package xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.glowstonelabs.redstonereloaded.block.ImplementedInventory;
import xyz.glowstonelabs.redstonereloaded.init.ModBlockEntities;

public class BubbleElevatorBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
    private float rotation = 0;

    public BubbleElevatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BUBBLE_ELEVATOR_BLOCK_BE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Bubble Elevator");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BubbleElevatorBlockScreenHandler(syncId, playerInventory, this.pos);
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public static void tick(World world, BlockPos pos, BlockState state, BubbleElevatorBlockEntity be) {
        if (world.isClient) return;

        // Try to pull items from below
        BlockPos belowPos = pos.down();
        Inventory belowInv = HopperBlockEntity.getInventoryAt(world, belowPos);
        if (belowInv != null) {
            moveItems(belowInv, be, Direction.UP);
        }

        // Try to push items above
        BlockPos abovePos = pos.up();
        Inventory aboveInv = HopperBlockEntity.getInventoryAt(world, abovePos);
        if (aboveInv != null) {
            moveItems(be, aboveInv, Direction.DOWN);
        }
    }

    private static void moveItems(Inventory from, Inventory to, Direction direction) {
        for (int i = 0; i < from.size(); i++) {
            ItemStack stack = from.getStack(i);
            if (!stack.isEmpty()) {
                ItemStack remaining = HopperBlockEntity.transfer(null, to, stack, direction); // Fixed argument count
                from.setStack(i, remaining);
                break;
            }
        }
    }
}
