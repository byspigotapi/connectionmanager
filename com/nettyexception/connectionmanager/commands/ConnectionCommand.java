package com.nettyexception.connectionmanager.commands;

import com.nettyexception.connectionmanager.ConnectionManager;
import com.nettyexception.connectionmanager.objectives.Objectives;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created on 27.11.2018 at 22:30 by NettyException | Sören!
 */

public class ConnectionCommand extends Command {

    public ConnectionCommand() {
        super("connection", (String)null, "connectionmanager", "cm");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer)commandSender;
            if (!proxiedPlayer.hasPermission("command.use.connection")) {
                proxiedPlayer.sendMessage(new ComponentBuilder(Objectives.getPrefix()).append("You do not have permission to execute this command!").color(ChatColor.RED).create());
                ConnectionManager.getConnectionManager().getLogger().info("Player " + proxiedPlayer.getName() + " try it to execute the command!");
                return;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    if (Objectives.getMembers().contains(proxiedPlayer)) {
                        proxiedPlayer.sendMessage(new ComponentBuilder(Objectives.getPrefix()).append("You are already logged!").color(ChatColor.RED).create());
                        return;
                    }
                    Objectives.getMembers().add(proxiedPlayer);
                    proxiedPlayer.sendMessage(new ComponentBuilder(Objectives.getPrefix()).append("You have now logged in, you will now receive the ServerPings of the last 2 minutes in 2 minutes").color(ChatColor.GREEN).create());
                } else if (args[0].equalsIgnoreCase("off")) {
                    if (!Objectives.getMembers().contains(proxiedPlayer)) {
                        proxiedPlayer.sendMessage(new ComponentBuilder(Objectives.getPrefix()).append("You are not logged in!").color(ChatColor.RED).create());
                        return;
                    }
                    Objectives.getMembers().remove(proxiedPlayer);
                    proxiedPlayer.sendMessage(new ComponentBuilder(Objectives.getPrefix()).append("You will no longer receive notifications").color(ChatColor.RED).create());
                } else {
                    proxiedPlayer.sendMessage(new ComponentBuilder(Objectives.getPrefix()).append("Wrong usage! ").color(ChatColor.GRAY).append("/connection <ON/OFF>").color(ChatColor.AQUA).create());
                    return;
                }
            } else {
                proxiedPlayer.sendMessage(new ComponentBuilder(Objectives.getPrefix()).append("Wrong usage! ").color(ChatColor.GRAY).append("/connection <ON/OFF>").color(ChatColor.AQUA).create());
                return;
            }
        }
    }
}
