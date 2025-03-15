package net.entropyentertainment.nathan.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class PushItem extends Item {
    private final float pushPower;

    public PushItem(float pushPower, Settings settings) {
        super(settings);
        this.pushPower = pushPower;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) return ActionResult.PASS;

        float pitch = user.getYaw();
        float yaw = user.getPitch();
        float xVelocity = MathHelper.sin(pitch * (float) (Math.PI / 180.0)) * MathHelper.cos(yaw * (float) (Math.PI / 180.0));
        float yVelocity = MathHelper.sin(yaw * (float) (Math.PI / 180.0));
        float zVelocity = -MathHelper.cos(pitch * (float) (Math.PI / 180.0)) * MathHelper.cos(yaw * (float) (Math.PI / 180.0));
        float sqrtVelocity = MathHelper.sqrt(xVelocity * xVelocity + yVelocity * yVelocity + zVelocity * zVelocity);

        if (user.isOnGround() || user.isSubmergedInWater()) {
            user.resetLerp();
            xVelocity *= pushPower / sqrtVelocity;
            yVelocity *= pushPower / sqrtVelocity;
            zVelocity *= pushPower / sqrtVelocity;
        } else {
            user.fallDistance = 0;
        }

        user.addVelocity(xVelocity, yVelocity, zVelocity);
        user.velocityModified = true;

        return ActionResult.SUCCESS_SERVER;
    }
}
