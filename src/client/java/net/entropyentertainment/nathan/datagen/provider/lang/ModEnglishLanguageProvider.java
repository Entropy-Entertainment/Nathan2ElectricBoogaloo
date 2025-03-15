package net.entropyentertainment.nathan.datagen.provider.lang;

import net.entropyentertainment.nathan.common.tags.ModItemTags;
import net.entropyentertainment.nathan.init.ModBlocks;
import net.entropyentertainment.nathan.init.ModItemGroups;
import net.entropyentertainment.nathan.init.ModItems;
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

        // Items
        translationBuilder.add(ModItems.NETHERITE_DRILL, "Netherite Drill");
        translationBuilder.add(ModItems.DIAMOND_DRILL, "Diamond Drill");
        translationBuilder.add(ModItems.RAW_RHODONITE_ORE, "Raw Rhodonite Ore");

        // Blocks
        translationBuilder.add(ModBlocks.RHODONITE_ORE.asItem(), "Rhodonite Ore");
        translationBuilder.add(ModBlocks.DEEPSLATE_RHODONITE_ORE.asItem(), "Deepslate Rhodonite Ore");

        // ItemGroups (this is the little inventory tab in creative mode)
        translationBuilder.add(ModItemGroups.DUNGEON_GROUP_KEY, "Dungeons");

        // ItemTags (this is mostly for JEI and alternatives support)
        translationBuilder.add(ModItemTags.DRILLS, "Drills");
    }
}
