package net.entropyentertainment.nathan.datagen.provider.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class NathanEnglishLanguageProvider extends AbstractLanguageProvider {
    private static final String langCode = "en_us";

    public NathanEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(langCode, dataOutput, registryLookup);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/setup">documentation</a>
     *
     * @param wrapperLookup      WrapperLookup
     * @param translationBuilder TranslationBuilder
     */
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        getExistingLangFile(translationBuilder);
    }
}
