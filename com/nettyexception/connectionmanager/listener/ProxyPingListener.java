package com.nettyexception.connectionmanager.listener;

import com.nettyexception.connectionmanager.ConnectionManager;
import com.nettyexception.connectionmanager.objectives.Objectives;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.score.Objective;
import net.md_5.bungee.event.EventHandler;

import java.net.InetSocketAddress;

/**
 * Created on 27.11.2018 at 22:29 by NettyException | Sören!
 */

public class ProxyPingListener implements Listener {

    @EventHandler(priority = -64)
    public void onHandle(ProxyPingEvent proxyPingEvent) {
        InetSocketAddress inetSocketAddress = proxyPingEvent.getConnection().getAddress();
        if (!Objectives.getIpAddresses().contains(inetSocketAddress.toString())) {
            Objectives.getIpAddresses().add(inetSocketAddress.toString());
        }
    }

}
