package net.entropyentertainment.nathan.datagen;

import net.entropyentertainment.nathan.datagen.provider.NathanModelProvider;
import net.entropyentertainment.nathan.datagen.provider.NathanRecipeProvider;
import net.entropyentertainment.nathan.datagen.provider.lang.NathanEnglishLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class NathanDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Asset providers
        pack.addProvider(NathanModelProvider::new);
        pack.addProvider(NathanRecipeProvider::new);

        // Lang providers
        pack.addProvider(NathanEnglishLanguageProvider::new);
    }
}
