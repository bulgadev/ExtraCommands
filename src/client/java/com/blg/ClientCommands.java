package com.blg;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public  class ClientCommands {
    private static boolean dispcords = false;
    private static boolean secret = false;
    private static boolean near = false;

    public static boolean disp() {
        return dispcords;
    }
    public static boolean secret1() {
        return secret;
    }
    public static boolean near1() {
        return near;
    }
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                literal("cords")
                        .executes(context -> {
                            dispcords = !dispcords;
                            context.getSource().getPlayer().sendMessage(Text.literal("dispcords" + dispcords), false);
                            return 1;
                        })
        );
        //register the command
        dispatcher.register(
                literal("test")
                        .executes(context -> {
                            System.out.println("Hello World!");
                            return 1;
                        })
        );
        dispatcher.register(
                literal("secret")
                        .executes (context -> {
                            secret = !secret;
                            context.getSource().getPlayer().sendMessage(Text.literal("Secret" + secret), false);
                            return 1;
                        })
        );
        dispatcher.register(
                literal("near")
                        .executes(context -> {
                            near = !near;
                            context.getSource().getPlayer().sendMessage(Text.literal("Near" + near), false);
                            return 1;
                        })
        );
    }

}