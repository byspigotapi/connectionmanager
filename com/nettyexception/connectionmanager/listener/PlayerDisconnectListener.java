package com.nettyexception.connectionmanager.listener;

import com.nettyexception.connectionmanager.objectives.Objectives;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created on 27.11.2018 at 23:08 by NettyException | Sören!
 */

public class PlayerDisconnectListener implements Listener {

    @EventHandler(priority = -64)
    public void onHandle(PlayerDisconnectEvent playerDisconnectEvent) {
        if (Objectives.getMembers().contains(playerDisconnectEvent)) {
            Objectives.getMembers().remove(playerDisconnectEvent);
        } else {
            return;
        }
    }

}
