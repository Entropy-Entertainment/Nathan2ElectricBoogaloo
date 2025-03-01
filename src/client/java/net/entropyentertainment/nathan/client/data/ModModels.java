package net.entropyentertainment.nathan.client.data;

import net.entropyentertainment.nathan.Nathan;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModels extends Models {
    public static final Model DRILL = item("drill", TextureKey.LAYER0);

    private static Model item(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.of(Nathan.MOD_ID, "item/" + parent)), Optional.empty(), requiredTextureKeys);
    }
}
