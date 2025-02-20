package nl.nullptrexc.modcore;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Logger;

/**
 * The core mod class that should be extended by your mod's entrypoint <br />
 *
 * @author &lt;null/&gt;
 */
public abstract class ModCore implements ModInitializer {
    public static String MOD_ID;
    public static Logger LOGGER;

    /**
     * Runs when the mod initializes. <br/>
     * From {@link net.fabricmc.api.ModInitializer#onInitialize()}
     */
    @Override
    public abstract void onInitialize();
}
