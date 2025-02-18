package net.entropyentertainment.nathan.datagen.provider.lang;

import net.entropyentertainment.nathan.Nathan;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * An abstract data generator
 */
public abstract class AbstractLanguageProvider extends FabricLanguageProvider {
    private final String langCode;

    private static final Logger LOGGER = Nathan.LOGGER;

    public AbstractLanguageProvider(String langCode, FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
        this.langCode = langCode;
    }

    public void getExistingLangFile(TranslationBuilder builder) {
        try {
            Optional<Path> path = dataOutput.getModContainer().findPath("assets/lantern/lang/"+langCode+".existing.json");
            if(path.isPresent()) {
                builder.add(path.get());
            }
        } catch (IOException | NullPointerException e) {
            if(e instanceof IOException) {
                handleLanguageFileError((IOException) e);
            }
            else if (e instanceof NullPointerException) {
                handleMissingLangCode();
            }
            LOGGER.error("Failed to load language file", e);
        }
    }

    private void handleLanguageFileError(IOException e) {
        throw new RuntimeException("Failed to add existing "+langCode+" language file!", e);
    }

    private void handleMissingLangCode() {
        throw new NullPointerException("Your language code wasn't initialized! Please set it correctly in the language provider's constructor (\"en_us\" or \"nl_nl\" for example)!");
    }
}
