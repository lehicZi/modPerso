package com.lehicZi.firstmod.world.gen;

import com.lehicZi.firstmod.block.custom.trees.GemwoodTree;
import com.lehicZi.firstmod.world.biome.ModBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class ModTreeGeneration {

    public static void generateTrees(final BiomeLoadingEvent event){
        GemwoodTree gemwoodTree = new GemwoodTree();
        Random random = new Random();

        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        RegistryKey<Biome> gemland_key = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(ModBiomes.GEM_LANDS_BIOME.get())));

        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if (key.equals(gemland_key)){
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> ModConfiguredFeatures.GEMWOOD
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.1f, 1))));
        }

    }
}
