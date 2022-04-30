package com.lehicZi.firstmod.fluid;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.block.ModBlocks;
import com.lehicZi.firstmod.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, FirstMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> REDWATER_FLUID
            = FLUIDS.register("redwater_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.REDWATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> REDWATER_FLOWING
            = FLUIDS.register("redwater_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.REDWATER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties REDWATER_PROPERTIES
            = new ForgeFlowingFluid.Properties(
            () -> REDWATER_FLUID.get(), () -> REDWATER_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(20).luminosity(4).viscosity(10).sound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK).overlay(WATER_OVERLAY_RL)
            .color(0xffff0000)).slopeFindDistance(1).levelDecreasePerBlock(1)
            .block(() -> ModFluids.REDWATER_BLOCK.get()).bucket(() -> ModItems.REDWATER_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> REDWATER_BLOCK = ModBlocks.BLOCKS.register("redwater",
            () -> new FlowingFluidBlock(() -> ModFluids.REDWATER_FLUID.get(), AbstractBlock.Properties.create(Material.WATER)
                    .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));


    public static void register(IEventBus eventBus){
        FLUIDS.register(eventBus);
    }

}
