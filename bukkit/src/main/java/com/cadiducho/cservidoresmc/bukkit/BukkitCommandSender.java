package com.cadiducho.cservidoresmc.bukkit;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class BukkitCommandSender implements CSCommandSender {

    private final CommandSender commandSender;

    @Override
    public void sendMessage(String string) {
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
    }

    @Override
    public String getName() {
        return commandSender.getName();
    }

    public CommandSender getSender() {
        return commandSender;
    }

    @Override
    public boolean hasPermission(String string) {
        return commandSender.hasPermission(string);
    }
}
