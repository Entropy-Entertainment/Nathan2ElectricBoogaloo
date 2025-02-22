package net.entropyentertainment.nathan;

import net.entropyentertainment.nathan.world.DungeonChunkGenerator;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Nathan implements ModInitializer {
    public static final Identifier YOUR_CHUNK_GENERATOR_ID = Identifier.of("nathan", "dungeongenerator");

    @Override
    public void onInitialize() {
        Registry.register(Registries.CHUNK_GENERATOR, YOUR_CHUNK_GENERATOR_ID, DungeonChunkGenerator.CODEC);
    }
}
