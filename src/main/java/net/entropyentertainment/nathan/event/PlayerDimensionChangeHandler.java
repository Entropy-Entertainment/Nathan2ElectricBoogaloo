package net.entropyentertainment.nathan.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Set;

public class PlayerDimensionChangeHandler {
    private static final Identifier CUSTOM_DIMENSION_ID = Identifier.of("nathan", "dungeon_dimention");

    public static void register() {
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            if(destination.getRegistryKey().getValue().equals(CUSTOM_DIMENSION_ID)){
                teleportPlayer(player, destination);
            }
        });
    }

    private static void teleportPlayer(ServerPlayerEntity player, ServerWorld world) {
        BlockPos targetPos = new BlockPos(0, 65, 0);
        Set<PositionFlag> flags = Set.of(PositionFlag.X, PositionFlag.Y, PositionFlag.Z);
        player.teleport(world, targetPos.getX(), targetPos.getY(), targetPos.getZ(), flags, player.getYaw(),  player.getPitch(), true);
    }
}
