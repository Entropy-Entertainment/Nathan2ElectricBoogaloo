package net.entropyentertainment.nathan.datagen.provider;

import net.entropyentertainment.nathan.init.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/recipes">documentation</a>
     *
     * @param wrapperLookup  WrapperLookup
     * @param recipeExporter RecipeExporter
     * @return RecipeGenerator
     */
    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.TOOLS, ModItems.NETHERITE_DRILL)
                        .pattern("## ")
                        .pattern("#%-")
                        .pattern(" - ")
                        .input('#', Items.NETHERITE_INGOT)
                        .input('%', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                        .input('-', Items.STICK)
                        .criterion(hasItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(ModItems.NETHERITE_DRILL))
                        .offerTo(recipeExporter);
            }
        };
    }

    @Override
    public String getName() {
        return "NathanRecipeProvider";
    }
}
