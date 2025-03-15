package net.entropyentertainment.nathan.init;

import net.entropyentertainment.nathan.Nathan;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> RHODONITE_ORE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Nathan.MOD_ID, "ore_rhodonite"));

    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RHODONITE_ORE_KEY);
    }
}
