package net.entropyentertainment.nathan;

import net.entropyentertainment.nathan.init.ModEssentials;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Nathan extends ModEssentials implements ModInitializer {

    public Nathan() {
        MOD_ID = "nathan";
        LOGGER.info("Test meow test");
    }

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Started initializing {}", MOD_ID);

        /*
        TODO: Init code goes here
         */

        LOGGER.info("Finished initializing {}", MOD_ID);
    }
}
