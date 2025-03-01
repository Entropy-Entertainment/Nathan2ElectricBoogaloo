package net.entropyentertainment.nathan.datagen.provider.lang;

import net.entropyentertainment.nathan.init.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEnglishLanguageProvider extends AbstractLanguageProvider {
    private static final String langCode = "en_us";

    public ModEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
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

        translationBuilder.add(ModItems.NETHERITE_DRILL, "Netherite Drill");
    }
}
