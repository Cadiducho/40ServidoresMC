package com.cadiducho.cservidoresmc.bukkit.cmd;

import org.bukkit.command.CommandSender;

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
        
        plugin.getUpdater().checkearVersion(sender, false);
    }
}
