package com.cadiducho.cservidoresmc.bukkit.cmd;

import java.util.Arrays;

import org.bukkit.command.CommandSender;

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
        
        String act = plugin.getUpdater().checkearVersion();
        if (act != null) {
            sender.sendMessage(plugin.getMetodos().colorizar(plugin.getTag()+"&a"+act));
            System.out.println("["+ plugin.getName() +"] Version: " + act);
        }
    }
    
}
