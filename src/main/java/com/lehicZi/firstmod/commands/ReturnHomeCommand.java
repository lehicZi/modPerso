package com.lehicZi.firstmod.commands;

import com.lehicZi.firstmod.FirstMod;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ReturnHomeCommand {

    public ReturnHomeCommand (CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("home").then(Commands.literal("return").executes(command -> {
            return returnHome(command.getSource());
        })));

    }


    private int returnHome(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        boolean hasHompos = player.getPersistentData().getIntArray(FirstMod.MOD_ID + "homepos").length != 0;

        if(hasHompos){

            int[] playerPos = player.getPersistentData().getIntArray(FirstMod.MOD_ID + "homepos");
            player.setPositionAndUpdate(playerPos[0], playerPos[1], playerPos[2]);

            source.sendFeedback(new StringTextComponent("Welcome back to home !"), true);

        }
        else {

            source.sendFeedback(new StringTextComponent("No home position set"), true);
        }


        return -1;
    }

}
