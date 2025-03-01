package net.entropyentertainment.nathan.common.items;

import net.entropyentertainment.nathan.common.tags.ModBlockTags;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;

public class DrillTool extends MiningToolItem {

    public DrillTool(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, ModBlockTags.DRILL_MINABLE, attackDamage, attackSpeed, settings);
    }
}
