package net.entropyentertainment.nathan.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import nl.nullptrexc.modcore.RegistryHelper;

public class ModBlocks {

    public static final Block RHODONITE_ORE = RegistryHelper.registerAndCreateBlock(
            "rhodonite_ore",
            settings -> new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY)
                    .requiresTool()
                    .strength(4F, 4F)
    );
    public static final Block DEEPSLATE_RHODONITE_ORE = RegistryHelper.registerAndCreateBlock(
            "deepslate_rhodonite_ore",
            settings -> new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), settings),
            AbstractBlock.Settings.copy(RHODONITE_ORE)
                    .strength(5.5F, 6F)
                    .sounds(BlockSoundGroup.DEEPSLATE)
    );

    public static void init() {
    }
}
