package com.cadiducho.cservidoresmc.bukkit.util;

import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class Cooldown {

    private final int time;
    private final HashMap<String, Long> cooldowns;

    public Cooldown(int time) {
        this.time = time;
        this.cooldowns = new HashMap<>();
    }

    public int getTime() {
        return time;
    }

    private HashMap<String, Long> getCooldowns() {
        return cooldowns;
    }

    public int getTimeLeft(CommandSender player) {
        if (!isCoolingDown(player)) {
            return 0;
        }
        return (int) (((getCooldowns().get(player.getName()) - (System.currentTimeMillis() - (getTime() * 1000))) / 1000) + 1);
    }

    public void setOnCooldown(CommandSender player) {
        getCooldowns().put(player.getName(), System.currentTimeMillis());
    }

    public boolean isCoolingDown(CommandSender player) {
        return getCooldowns().containsKey(player.getName()) && getCooldowns().get(player.getName()) >= (System.currentTimeMillis() - (getTime() * 1000));
    }
}