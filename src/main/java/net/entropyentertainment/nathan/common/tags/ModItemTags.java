package net.entropyentertainment.nathan.common.tags;

import net.entropyentertainment.nathan.Nathan;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> DRILLS = of("drills");
    public static final TagKey<Item> MINING_ENCHANTABLE = of("enchantable/mining");

    private static TagKey<Item> of(String ID) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(Nathan.MOD_ID, ID));
    }
}
