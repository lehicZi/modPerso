package com.lehicZi.firstmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

//Allows to add creative tab windows
public class ModItemGroup {

    // Adds the "firstmod" window
    public static final ItemGroup FIRSTMOD_GROUP = new ItemGroup("firstModTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.BICEPS.get());
        }
    };
}
