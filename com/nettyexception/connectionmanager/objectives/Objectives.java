package com.nettyexception.connectionmanager.objectives;

import jdk.nashorn.internal.objects.annotations.Getter;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 27.11.2018 at 22:30 by NettyException | Sören!
 */

public class Objectives {

    public static String prefix;

    @Getter
    public static String getPrefix() {
        return prefix;
    }

    /**
     * All IP addresses are entered in this ArrayList.
     */

    public static List<String> ipAddresses = new ArrayList<>();

    @Getter
    public static List<String> getIpAddresses() {
        return ipAddresses;
    }

    public static List<ProxiedPlayer> members = new ArrayList<>();

    @Getter
    public static List<ProxiedPlayer> getMembers() {
        return members;
    }

    static {
        prefix = "§8[§6ConnectionManager§8] §7";
    }

}
