package com.cadiducho.cservidoresmc.bukkit;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class BukkitCommandSender implements CSCommandSender {

    private final CommandSender commandSender;

    @Override
    public void sendMessage(String string) {
        commandSender.sendMessage(string);
    }

    @Override
    public String getName() {
        return commandSender.getName();
    }

    @Override
    public boolean hasPermission(String string) {
        return commandSender.hasPermission(string);
    }
}
