package com.lehicZi.firstmod.events;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.commands.ReturnHomeCommand;
import com.lehicZi.firstmod.commands.SetHomeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event){
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());


        ConfigCommand.register(event.getDispatcher());


    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event){
        if (!event.getOriginal().getEntityWorld().isRemote){
            event.getPlayer().getPersistentData().putIntArray(FirstMod.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(FirstMod.MOD_ID + "homepos"));

        }

    }
}
