package com.nettyexception.connectionmanager;

import com.nettyexception.connectionmanager.commands.ConnectionCommand;
import com.nettyexception.connectionmanager.listener.PlayerDisconnectListener;
import com.nettyexception.connectionmanager.listener.ProxyPingListener;
import com.nettyexception.connectionmanager.objectives.Objectives;
import jdk.nashorn.internal.objects.annotations.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.concurrent.TimeUnit;

/**
 * Created on 27.11.2018 at 22:29 by NettyException | Sören!
 * All rights reserved | Made with <3
 */

public class ConnectionManager extends Plugin {

    public static ConnectionManager connectionManager;

    @Getter
    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public ConnectionManager() {
    }

    @Override
    public void onEnable() {
        connectionManager = this;

        this.initListener();
        this.initCommands();

        this.broadcast();

        this.getLogger().info("Plugin successfully activated!");
        this.getLogger().info("Coded by NettyException");
        this.getLogger().info(">> ConnectionManager");
    }

    private void broadcast() {
        this.getProxy().getScheduler().schedule(this, () -> {
            for (ProxiedPlayer notifyedPlayer : this.getProxy().getPlayers()) {
                if (Objectives.getMembers().contains(notifyedPlayer)) {
                    notifyedPlayer.sendMessage(new ComponentBuilder(Objectives.getPrefix()).append(Objectives.getIpAddresses().size() + " Pings").color(ChatColor.YELLOW).append(" were documendted in 2 minutes.").color(ChatColor.GRAY).create());
                } else {
                    return;
                }
                Objectives.getIpAddresses().clear();

            }
        },2L, TimeUnit.MINUTES);
    }

    private void initCommands() {
        PluginManager pluginManager = this.getProxy().getPluginManager();
        pluginManager.registerCommand(this, new ConnectionCommand());
    }

    private void initListener() {
        PluginManager pluginManager = this.getProxy().getPluginManager();
        pluginManager.registerListener(this, new ProxyPingListener());
        pluginManager.registerListener(this, new PlayerDisconnectListener());
    }

    @Override
    public void onDisable() {
        connectionManager = null;
        this.getLogger().info("Plugin successfully deactivated!");
        this.getLogger().info("Coded by NettyException");
        this.getLogger().info(">> ConnectionManager");
    }
}
