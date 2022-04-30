package com.lehicZi.firstmod.data.recipes;

import com.lehicZi.firstmod.FirstMod;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FirstMod.MOD_ID);

    public static final RegistryObject<LightningCrafterRecipe.Serializer> LIGHTNING_SERIALIZER
            = RECIPE_SERIALIZER.register("lightning", LightningCrafterRecipe.Serializer::new);

    public static IRecipeType<LightningCrafterRecipe> LIGHTNING_RECIPE
            = new LightningCrafterRecipe.LightningRecipeType();


    public static void register(IEventBus eventBus){
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, LightningCrafterRecipe.TYPE_ID, LIGHTNING_RECIPE);
    }

}
