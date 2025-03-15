package net.entropyentertainment.nathan;

import net.entropyentertainment.nathan.init.ModBlocks;
import net.entropyentertainment.nathan.init.ModItemGroups;
import net.entropyentertainment.nathan.init.ModItems;
import net.entropyentertainment.nathan.init.ModPlacedFeatures;
import nl.nullptrexc.modcore.ModCore;
import org.apache.logging.log4j.LogManager;

public class Nathan extends ModCore {

    public Nathan() {
        MOD_ID = "nathan";
        LOGGER = LogManager.getLogger(MOD_ID);
    }

    public void onInitialize() {
        LOGGER.info("Started initializing {}", MOD_ID);

        /*
        TODO: Init code goes here
         */
        ModItemGroups.init();
        ModItems.init();
        ModBlocks.init();
        ModPlacedFeatures.init();

        LOGGER.info("Finished initializing {}", MOD_ID);
    }
}
