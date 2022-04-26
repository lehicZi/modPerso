package com.lehicZi.firstmod.world;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.world.gen.ModFlowerGeneration;
import com.lehicZi.firstmod.world.gen.ModOreGeneration;
import com.lehicZi.firstmod.world.gen.ModTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
        ModOreGeneration.generateOres(event);
        ModFlowerGeneration.generateFlowers(event);
        ModTreeGeneration.generateTrees(event);
    }

}
