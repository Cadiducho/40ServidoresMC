package com.cadiducho.cservidoresMC.cmd;

import com.cadiducho.cservidoresMC.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Cadiducho
 */

public class UpdateCMD implements CommandExecutor {
    
    public static Main plugin;
    
    public UpdateCMD(Main instance) {
        plugin = instance;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("40servidores.actualizar")) { return true;}
        String act = plugin.getUpdater().checkearVersion();
        if (act != null) {
            sender.sendMessage(plugin.getMetodos().colorizar(plugin.getTag()+"&a"+act));
        }
        return true;
    }
    
}
