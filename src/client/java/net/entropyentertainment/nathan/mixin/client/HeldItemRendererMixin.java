package net.entropyentertainment.nathan.mixin.client;

import net.entropyentertainment.nathan.common.tags.ModItemTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ModelTransformationMode;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

    @Shadow
    public abstract void renderItem(LivingEntity entity, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

    @Shadow
    protected abstract void applySwingOffset(MatrixStack matrices, Arm arm, float swingProgress);

    @Shadow
    protected abstract void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress);

    @Inject(at = @At("HEAD"), method = "renderFirstPersonItem", cancellable = true)
    private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!player.isUsingSpyglass()) {
            boolean isMainHand = hand == Hand.MAIN_HAND;
            Arm arm = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
            boolean isRightArm = arm == Arm.RIGHT;
            int directionMultiplier = isRightArm ? 1 : -1;

            matrices.push();

            if (item.isIn(ModItemTags.FRONTALLY_HELD)) {
                applyFrontallyHeldItemEquipProgress(matrices, equipProgress);
                applyFrontallyHeldItemSwingProgress(matrices, swingProgress);
                renderFrontallyHeldItem(isMainHand, directionMultiplier, matrices, player, item, isRightArm, vertexConsumers, light);
                ci.cancel();
            } else if (!isMainHand && player.getMainHandStack().isIn(ModItemTags.DRILLS)) {
                ci.cancel();
            }
        }
    }

    @Unique
    private void renderFrontallyHeldItem(boolean isMainHand, int directionMultiplier, MatrixStack matrices, AbstractClientPlayerEntity player, ItemStack item, boolean isRightArm, VertexConsumerProvider vertexConsumers, int light) {
        if (isMainHand) {
            matrices.translate((float) directionMultiplier * -0.641864F, 0.0F, 0.0F);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) directionMultiplier * 10.0F));
            this.renderItem(
                    player,
                    item,
                    isRightArm ? ModelTransformationMode.FIRST_PERSON_RIGHT_HAND : ModelTransformationMode.FIRST_PERSON_LEFT_HAND,
                    !isRightArm,
                    matrices,
                    vertexConsumers,
                    light
            );
        }
    }

    @Unique
    private void applyFrontallyHeldItemEquipProgress(MatrixStack matrices, float equipProgress) {
        matrices.translate(0.56F, -0.52F + equipProgress * -0.15F, -0.72F);
    }

    @Unique
    public void applyFrontallyHeldItemSwingProgress(MatrixStack matrices, float swingProgress) {
        matrices.translate(0, 0, -MathHelper.sin(swingProgress * (float) Math.PI) / 4.5F);
    }
}
