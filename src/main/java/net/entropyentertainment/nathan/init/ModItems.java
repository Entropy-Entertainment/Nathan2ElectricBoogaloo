package net.entropyentertainment.nathan.init;

import net.entropyentertainment.nathan.common.items.DrillTool;
import net.minecraft.item.ToolMaterial;
import nl.nullptrexc.modcore.RegistryHelper;

public class ModItems {

    public static final DrillTool DIAMOND_DRILL = RegistryHelper.registerAndCreateItem(settings -> new DrillTool(ToolMaterial.DIAMOND, 1.0F, -2.8F, settings), "diamond_drill");
    public static final DrillTool NETHERITE_DRILL = RegistryHelper.registerAndCreateItem(settings -> new DrillTool(ToolMaterial.NETHERITE, 1.0F, -2.8F, settings), "netherite_drill");

    public static void init() {
        RegistryHelper.registerToItemGroup(
                ModItemGroups.DUNGEON_GROUP_KEY, // The ItemGroup to modify
                /* Items to add belowe here */
                NETHERITE_DRILL,
                DIAMOND_DRILL
        );
    }
}
