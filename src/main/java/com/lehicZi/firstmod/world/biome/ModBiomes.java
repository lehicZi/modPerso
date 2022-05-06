package com.lehicZi.firstmod.world.biome;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.entity.ModEntityTypes;
import com.lehicZi.firstmod.world.gen.ModConfiguredFeatures;
import com.lehicZi.firstmod.world.gen.ModTreeGeneration;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

public class ModBiomes {

    public static DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, FirstMod.MOD_ID);

    public static RegistryObject<Biome> GEM_LANDS_BIOME = BIOMES.register("gem_land_biome", () ->
            makeGemLandsBiome(() -> ModConfiguresSurfaceBuilders.GEM_LANDS_SURFACE, 0.125f, 0.05f));


    private static Biome makeGemLandsBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale){
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.withPassiveMobs(mobspawninfo$builder);
        DefaultBiomeFeatures.withBatsAndHostiles(mobspawninfo$builder);
        mobspawninfo$builder.withSpawner(EntityClassification.MONSTER,
                new MobSpawnInfo.Spawners(ModEntityTypes.LELE_LE_FOU.get(), 100, 1, 1));
        BiomeGenerationSettings.Builder biomegenerationsettings$builder =
                (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);

        biomegenerationsettings$builder.withStructure(StructureFeatures.MINESHAFT);
        biomegenerationsettings$builder.withStructure(StructureFeatures.RUINED_PORTAL_SWAMP);
        biomegenerationsettings$builder.withStructure(StructureFeatures.BURIED_TREASURE);

        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);

        DefaultBiomeFeatures.withLavaAndWaterLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withMonsterRoom(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withCommonOverworldBlocks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withOverworldOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withClayDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withDesertVegetation(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withLavaAndWaterSprings(biomegenerationsettings$builder);


        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.DESERT).depth(depth).scale(scale)
                .temperature(1.5F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()
                        .setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(getSkyColorWithTemperatureModifier(0.95f)))
                        .setParticle(new ParticleEffectAmbience(ParticleTypes.CRIT, 0.003f))
                        .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                        .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D))
                        .setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST))
                        .build())
                .withMobSpawnSettings(mobspawninfo$builder.build()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }


    public static void register(IEventBus eventBus){
        BIOMES.register(eventBus);
    }

}
