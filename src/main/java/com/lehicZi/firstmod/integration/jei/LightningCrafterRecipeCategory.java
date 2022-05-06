package com.lehicZi.firstmod.integration.jei;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.block.ModBlocks;
import com.lehicZi.firstmod.data.recipes.LightningCrafterRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

public class LightningCrafterRecipeCategory implements IRecipeCategory<LightningCrafterRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(FirstMod.MOD_ID, "lightning");
    public static final ResourceLocation TEXTURE = new ResourceLocation(FirstMod.MOD_ID, "textures/gui/lightning_crafter_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic lightningBolt;

    public LightningCrafterRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.LIGHTNING_CRAFTER.get()));
        this.lightningBolt = helper.createDrawable(TEXTURE, 176, 0, 13, 17);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends LightningCrafterRecipe> getRecipeClass() {
        return LightningCrafterRecipe.class;
    }

    @Override
    public String getTitle() {
        return ModBlocks.LIGHTNING_CRAFTER.get().getTranslatedName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(LightningCrafterRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, LightningCrafterRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 79, 30);
        recipeLayout.getItemStacks().init(1, true, 79, 52);

        recipeLayout.getItemStacks().init(2, false, 102, 42);

        recipeLayout.getItemStacks().set(ingredients);

    }

    @Override
    public void draw(LightningCrafterRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        if (recipe.getWeather() == LightningCrafterRecipe.Weather.THUNDERING){
            this.lightningBolt.draw(matrixStack, 82, 9);
        }
    }
}
