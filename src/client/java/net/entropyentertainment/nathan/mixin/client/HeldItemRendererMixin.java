package net.entropyentertainment.nathan.mixin.client;

import net.entropyentertainment.nathan.Nathan;
import net.entropyentertainment.nathan.common.tags.ModItemTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.resource.language.TranslationStorage;
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
    protected abstract void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress);

    @Inject(at = @At("HEAD"), method = "renderFirstPersonItem", cancellable = true)
    private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!player.isUsingSpyglass()) {
            boolean isMainHand = hand == Hand.MAIN_HAND;


            Arm arm = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
            matrices.push();

            if (item.isIn(ModItemTags.DRILLS)) {
                boolean isRightArm = arm == Arm.RIGHT;

                // Potentionally bad names, though I don't know better ones. I just copied this code from the crossbow section inside of this method
                int directionMultiplier = isRightArm ? 1 : -1;

                this.applyEquipOffset(matrices, arm, equipProgress);
                this.applyDrillSwingOffset(matrices, swingProgress);
                if (isMainHand) {
                    matrices.translate((float) directionMultiplier * -0.641864F, 0.0F, 0.0F); // Location in front of player
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) directionMultiplier * 10.0F)); //Left/Right rotation modifier
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
                ci.cancel();
            } else if (!isMainHand && player.getMainHandStack().isIn(ModItemTags.DRILLS)) {
                ci.cancel();
            }
        }
    }

    @Unique
    public void applyDrillSwingOffset(MatrixStack matrices, float swingProgress) {
        // Thanks for the math ChatGPT! Too tired to do this myself
        // The sine curve peaks at swingProgress = 0.5 and returns to 0 at 0 and 1, Multiply by -1/4.5F to scale the offset.
        matrices.translate(0, 0, -MathHelper.sin(swingProgress * (float) Math.PI) / 4.5F);
    }
}
