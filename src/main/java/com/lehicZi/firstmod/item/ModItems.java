package com.lehicZi.firstmod.item;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.block.ModBlocks;
import com.lehicZi.firstmod.item.custom.MagicRuby;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    // List of all items in this class
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    // New Items
    public static final RegistryObject<Item> BICEPS = ITEMS.register("biceps", () ->
            new Item(new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () ->
            new Item(new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> MAGIC_RUBY = ITEMS.register("magic_ruby", () ->
            new MagicRuby(new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP).maxDamage(200)));

    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword", () ->
            new SwordItem(ModItemTier.RUBY, 3, -2.4f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () ->
            new PickaxeItem(ModItemTier.RUBY, 1, -2.8f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe", () ->
            new AxeItem(ModItemTier.RUBY, 6, -3f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () ->
            new ShovelItem(ModItemTier.RUBY, 1.5F, -3f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe", () ->
            new HoeItem(ModItemTier.RUBY, -4, -3f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> EMERALD_SWORD = ITEMS.register("emerald_sword", () ->
            new SwordItem(ModItemTier.EMERALD, 3, -2.4f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe", () ->
        new PickaxeItem(ModItemTier.EMERALD, 1, -2.8f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> EMERALD_AXE = ITEMS.register("emerald_axe", () ->
            new AxeItem(ModItemTier.EMERALD, 5, -3f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel", () ->
            new ShovelItem(ModItemTier.EMERALD, 1.5F, -3f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> EMERALD_HOE = ITEMS.register("emerald_hoe", () ->
            new HoeItem(ModItemTier.EMERALD, -3, -3f,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY_BOOTS = ITEMS.register("ruby_boots", () ->
            new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () ->
            new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () ->
            new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet", () ->
            new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> EMERALD_BOOTS = ITEMS.register("emerald_boots", () ->
            new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> EMERALD_CHESTPLATE = ITEMS.register("emerald_chestplate", () ->
            new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> EMERALD_LEGGINGS = ITEMS.register("emerald_leggings", () ->
            new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> EMERALD_HELMET = ITEMS.register("emerald_helmet", () ->
            new ArmorItem(ModArmorMaterial.EMERALD, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> HOP = ITEMS.register("hop", () ->
            new BlockItem(ModBlocks.HOP.get(),
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> BEER = ITEMS.register("beer", () ->
            new Item(new Item.Properties().
                    food(new Food.Builder().hunger(6).saturation(0.9F).
                            effect( new EffectInstance(Effects.NAUSEA, 200, 3), 1f).
                            effect(new EffectInstance(Effects.STRENGTH, 500, 3), 0.5F).
                            effect(new EffectInstance(Effects.SPEED, 500, 2), 0.5F).
                            effect(new EffectInstance(Effects.REGENERATION, 500, 1), 0.25F).
                            effect(new EffectInstance(Effects.RESISTANCE, 500, 1), 0.5F).
                            build()).
                    group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> RUBY_HORSE_ARMOR = ITEMS.register("ruby_horse_armor", () ->
            new HorseArmorItem(13,"ruby" ,
                    new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));

    public static final RegistryObject<Item> GEMWOOD_SIGN = ITEMS.register("gemwood_sign", () ->
            new SignItem(new Item.Properties().maxStackSize(16).group(ModItemGroup.FIRSTMOD_GROUP),
                    ModBlocks.GEMWOOD_SIGN.get(), ModBlocks.GEMWOOD_WALL_SIGN.get()));


    // registers method call in mod's main class
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
