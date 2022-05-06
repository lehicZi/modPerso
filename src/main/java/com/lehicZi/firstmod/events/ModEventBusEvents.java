package com.lehicZi.firstmod.events;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.entity.ModEntityTypes;
import com.lehicZi.firstmod.entity.custom.LeleLeFouEntity;
import com.lehicZi.firstmod.events.loot.HopAdditionModifier;
import com.lehicZi.firstmod.events.loot.RubyHorseArmorStructureAdditionModifier;
import com.lehicZi.firstmod.item.custom.ModSpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.LELE_LE_FOU.get(), LeleLeFouEntity.setCustomAttributes().create());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new HopAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(FirstMod.MOD_ID, "hop_from_grass")),
                new RubyHorseArmorStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(FirstMod.MOD_ID, "ruby_horse_armor_in_dungeons"))
        );
    }
}
