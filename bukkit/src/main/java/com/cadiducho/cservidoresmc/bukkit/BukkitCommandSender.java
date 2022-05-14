package com.cadiducho.cservidoresmc.bukkit;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class BukkitCommandSender implements CSCommandSender {

    private final CommandSender commandSender;

    private final CSPlugin plugin;

    @Override
    public String TAG() {
        return plugin.getCSConfiguration().getTag();
    }

    @Override
    public void sendMessage(String string) {
        commandSender.spigot().sendMessage(TextComponent.fromLegacyText(toString()));
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
