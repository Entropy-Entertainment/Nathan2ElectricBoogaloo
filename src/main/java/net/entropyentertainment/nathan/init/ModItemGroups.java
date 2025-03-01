package net.entropyentertainment.nathan.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import nl.nullptrexc.modcore.RegistryHelper;

public class ModItemGroups {

    public static final RegistryKey<ItemGroup> DUNGEON_GROUP_KEY = RegistryHelper.getGroupRegistryKey("dungeon");
    public static final ItemGroup DUNGEON_GROUP = RegistryHelper.registerItemGroup(FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.NETHERITE_DRILL))
            .displayName(Text.translatable("itemGroup.nathan"))
            .build(), DUNGEON_GROUP_KEY
    );

    public static void init() {
    }
}
