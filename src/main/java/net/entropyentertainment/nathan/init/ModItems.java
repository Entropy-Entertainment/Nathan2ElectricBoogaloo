package net.entropyentertainment.nathan.init;

import net.entropyentertainment.nathan.common.items.DrillTool;
import net.entropyentertainment.nathan.common.items.PushItem;
import net.minecraft.item.ToolMaterial;
import nl.nullptrexc.modcore.RegistryHelper;

public class ModItems {

    public static final DrillTool DIAMOND_DRILL = RegistryHelper.registerAndCreateItem(settings -> new DrillTool(ToolMaterial.DIAMOND, 1.0F, -2.8F, settings), "diamond_drill");
    public static final DrillTool NETHERITE_DRILL = RegistryHelper.registerAndCreateItem(settings -> new DrillTool(ToolMaterial.NETHERITE, 1.0F, -2.8F, settings), "netherite_drill");

    public static final PushItem RAW_RHODONITE_ORE = RegistryHelper.registerAndCreateItem(settings -> new PushItem(1.3F, settings.useCooldown(1.5F)), "raw_rhodonite_ore");

    public static void init() {
    }

}
