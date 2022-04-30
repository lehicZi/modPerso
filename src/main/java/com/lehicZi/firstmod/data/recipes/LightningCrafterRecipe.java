package com.lehicZi.firstmod.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lehicZi.firstmod.block.ModBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Objects;

public class LightningCrafterRecipe implements ILightningCrafterRecipe {


    public enum Weather {
        CLEAR,
        RAIN,
        THUNDERING;

        public static Weather getWeatherByString(String s) {
            return Objects.equals(s, "thundering") ? THUNDERING : Objects.equals(s, "rain") ? RAIN : CLEAR;
        }
    }

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final Weather weather;

    public LightningCrafterRecipe(ResourceLocation id, ItemStack output,
                                    NonNullList<Ingredient> recipeItems, Weather weather) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.weather = weather;
    }


    @Override
    public boolean matches(IInventory inv, World worldIn) {
        // Checks for correct focus (Glass Pane)
        if(recipeItems.get(0).test(inv.getStackInSlot(0))) {
            return recipeItems.get(1).test(inv.getStackInSlot(1));
        }
        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.copy();
    }

    public Weather getWeather(){
        return this.weather;
    }

    public ItemStack getIcon(){
        return new ItemStack(ModBlocks.LIGHTNING_CRAFTER.get());
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.LIGHTNING_SERIALIZER.get();
    }

    public static class LightningRecipeType implements IRecipeType<LightningCrafterRecipe>{

        @Override
        public String toString() {
            return LightningCrafterRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<LightningCrafterRecipe> {

        @Override
        public LightningCrafterRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            String weather = JSONUtils.getString(json, "weather");

            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new LightningCrafterRecipe(recipeId, output,
                    inputs, Weather.getWeatherByString(weather));
        }

        @Nullable
        @Override
        public LightningCrafterRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.read(buffer));
            }

            ItemStack output = buffer.readItemStack();
            return new LightningCrafterRecipe(recipeId, output,
                    inputs, null);
        }

        @Override
        public void write(PacketBuffer buffer, LightningCrafterRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
        }
    }


}
