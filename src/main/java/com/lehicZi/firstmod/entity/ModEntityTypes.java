package com.lehicZi.firstmod.entity;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.entity.custom.LeleLeFouEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, FirstMod.MOD_ID);

    public static final RegistryObject<EntityType<LeleLeFouEntity>> LELE_LE_FOU =
            ENTITY_TYPES.register("lele_le_fou", () -> EntityType.Builder.create(LeleLeFouEntity::new,
                    EntityClassification.MONSTER)
                    .build(new ResourceLocation(FirstMod.MOD_ID, "lele_le_fou").toString()));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
