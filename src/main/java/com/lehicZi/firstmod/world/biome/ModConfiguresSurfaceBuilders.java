package com.lehicZi.firstmod.world.biome;

import com.lehicZi.firstmod.FirstMod;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.*;

public class ModConfiguresSurfaceBuilders {

    public static ConfiguredSurfaceBuilder<?> GEM_LANDS_SURFACE = register("gem_lands_surface",
            SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(
                    Blocks.GRASS_BLOCK.getDefaultState(),
                    Blocks.DIRT.getDefaultState(),
                    Blocks.SAND.getDefaultState()
            )));

    private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> csb){

        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(FirstMod.MOD_ID, name), csb);
    }


}
