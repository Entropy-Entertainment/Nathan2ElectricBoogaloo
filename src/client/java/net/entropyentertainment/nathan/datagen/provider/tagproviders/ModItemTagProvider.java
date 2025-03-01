package net.entropyentertainment.nathan.datagen.provider.tagproviders;

import net.entropyentertainment.nathan.common.tags.ModItemTags;
import net.entropyentertainment.nathan.init.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider<Item> {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModItemTags.DRILLS)
                .add(ModItems.DIAMOND_DRILL)
                .add(ModItems.NETHERITE_DRILL);

        getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE)
                .addTag(ModItemTags.DRILLS);

        getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(ModItemTags.DRILLS);

        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .addTag(ModItemTags.DRILLS);
    }
}
