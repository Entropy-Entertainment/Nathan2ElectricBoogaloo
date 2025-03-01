package net.entropyentertainment.nathan.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(at = @At("HEAD"), method = "calculateDamage", cancellable = true)
    private void calculateDamage(int baseDamage, ServerWorld world, ServerPlayerEntity player, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
        cir.cancel();
    }
}
