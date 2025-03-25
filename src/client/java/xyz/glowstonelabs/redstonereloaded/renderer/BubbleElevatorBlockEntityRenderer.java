package xyz.glowstonelabs.redstonereloaded.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ModelTransformationMode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator.BubbleElevatorBlock;
import xyz.glowstonelabs.redstonereloaded.block.Bubbleelevator.BubbleElevatorBlockEntity;

public class BubbleElevatorBlockEntityRenderer implements BlockEntityRenderer<BubbleElevatorBlockEntity> {
    public BubbleElevatorBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(BubbleElevatorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack[] stacks = new ItemStack[5];

        for (int i = 0; i < 5; i++) {
            stacks[i] = entity.getStack(i);
        }

        ItemStack stackToRender = getStackToRender(stacks);

        if (stackToRender != null) {
            float rotation = entity.getRenderingRotation() % 360f;
            float offset = (rotation / 360f) * 16;

            if (offset < 16) { // Only render if offset is less than 16
                matrices.push();
                matrices.translate(0.5f, 0.0f + offset / 16f, 0.5f);
                matrices.scale(0.5f, 0.5f, 0.5f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));

                itemRenderer.renderItem(stackToRender, ModelTransformationMode.GUI, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
            }
        }
    }

    private ItemStack getStackToRender(ItemStack[] stacks) {
        int maxCount = 0;
        ItemStack stackToRender = null;

        for (ItemStack stack : stacks) {
            if (!stack.isEmpty() && stack.getCount() > maxCount) {
                maxCount = stack.getCount();
                stackToRender = stack;
            }
        }

        if (stackToRender == null) {
            for (ItemStack stack : stacks) {
                if (!stack.isEmpty()) {
                    stackToRender = stack;
                    break;
                }
            }
        }

        return stackToRender;
    }
}