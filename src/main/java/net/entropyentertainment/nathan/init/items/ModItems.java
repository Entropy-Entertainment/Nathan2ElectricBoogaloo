package net.entropyentertainment.nathan.init.items;

import net.entropyentertainment.nathan.common.items.DrillTool;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import nl.nullptrexc.modcore.RegistryHelper;

public class ModItems {
    public static final RegistryKey<Item> NETHERITE_DRILL_KEY = RegistryHelper.getItemRegistryKey("netherite_drill");
    public static final DrillTool NETHERITE_DRILL = RegistryHelper.registerAndCreateItem(settings -> new DrillTool(ToolMaterial.NETHERITE, 1.0F, -2.8F, settings), NETHERITE_DRILL_KEY);

    public static void Init() {
    }
}
