package net.entropyentertainment.nathan.datagen.provider;

import net.entropyentertainment.nathan.client.data.ModModels;
import net.entropyentertainment.nathan.init.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * <a href="https://wiki.fabricmc.net/tutorial:datagen_model">documentation</a>
     *
     * @param blockstateModelGen BlockStateModelGenerator
     */
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockstateModelGen) {

    }

    /**
     * <a href="https://wiki.fabricmc.net/tutorial:datagen_model">documentation</a>
     *
     * @param modelGen ItemModelGenerator
     */
    @Override
    public void generateItemModels(ItemModelGenerator modelGen) {
        modelGen.register(ModItems.DIAMOND_DRILL, ModModels.DRILL);
        modelGen.register(ModItems.NETHERITE_DRILL, ModModels.DRILL);
    }
}
