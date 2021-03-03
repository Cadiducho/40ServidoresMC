package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.bukkit.BukkitCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Arrays;

/**
 *
 * @author Cadiducho
 */

public class UpdateCMD extends CommandBase {
    
    public UpdateCMD() {
        super("update40", "40servidores.actualizar", Arrays.asList("actualizar40", "upd40"));
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!perm(sender, getPermission(), true)) return;

        final CSCommandSender csSender = new BukkitCommandSender(sender);
        plugin.getUpdater().checkearVersion(csSender);
    }
}
