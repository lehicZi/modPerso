package com.lehicZi.firstmod.integration.jei;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.data.recipes.LightningCrafterRecipe;
import com.lehicZi.firstmod.data.recipes.ModRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class FirstmodJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(FirstMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new LightningCrafterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(rm.getRecipesForType(ModRecipeTypes.LIGHTNING_RECIPE).stream()
                        .filter(r -> r instanceof LightningCrafterRecipe).collect(Collectors.toList()),
                LightningCrafterRecipeCategory.UID);
    }
}
