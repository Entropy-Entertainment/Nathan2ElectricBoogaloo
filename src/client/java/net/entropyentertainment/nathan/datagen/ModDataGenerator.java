package net.entropyentertainment.nathan.datagen;

import net.entropyentertainment.nathan.datagen.provider.ModModelProvider;
import net.entropyentertainment.nathan.datagen.provider.ModRecipeProvider;
import net.entropyentertainment.nathan.datagen.provider.lang.ModEnglishLanguageProvider;
import net.entropyentertainment.nathan.datagen.provider.tagproviders.ModBlockTagProvider;
import net.entropyentertainment.nathan.datagen.provider.tagproviders.ModItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Asset providers
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);

        // Tag providers
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);

        // Lang providers
        pack.addProvider(ModEnglishLanguageProvider::new);
    }
}
