package net.entropyentertainment.nathan;

import net.entropyentertainment.nathan.init.items.ModItems;
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
        ModItems.Init();

        LOGGER.info("Finished initializing {}", MOD_ID);
    }
}
