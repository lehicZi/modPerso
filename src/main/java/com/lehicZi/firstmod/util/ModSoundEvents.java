package com.lehicZi.firstmod.util;

import com.lehicZi.firstmod.FirstMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FirstMod.MOD_ID);

    public static RegistryObject<SoundEvent> SMALL_EXPLOSION =
            registerSoundEvent("small_explosion");
    public static RegistryObject<SoundEvent> LELE_LE_FOU_WESH =
            registerSoundEvent("lele_le_fou_wesh");

    public static RegistryObject<SoundEvent> PEOPLE_EQUALS_SHIT =
            registerSoundEvent("people_equals_shit");


    public static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(FirstMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
