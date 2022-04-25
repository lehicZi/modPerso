package com.lehicZi.firstmod.world.gen;

import com.lehicZi.firstmod.block.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures {

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GEMWOOD = register("gemwood", Feature.TREE.withConfiguration((
            new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlocks.GEMWOOD_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(ModBlocks.GEMWOOD_LEAVES.get().getDefaultState()),
                    new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
                    new StraightTrunkPlacer(1, 2, 0),
                    new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build()));
}
