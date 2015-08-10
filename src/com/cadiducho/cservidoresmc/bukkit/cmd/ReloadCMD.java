package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author _arhlex_
 */

public class ReloadCMD implements CommandExecutor {
    
    public static BukkitPlugin plugin;
    
    public ReloadCMD(BukkitPlugin instance) {
        plugin = instance;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
       if (sender.hasPermission("40servidores.recargar")) { 
        	
         plugin.reloadConfig();
        	
          String msg5 = plugin.getTag()
            +"&aConfiguración recargada correctamente\n&"
            +plugin.getTag()+"aFuncionando versión" + plugin.getPluginVersion();
          
          sender.sendMessage(plugin.getMetodos().colorizar(msg5));
          
          System.out.println("["+ plugin.getName() +"] Configuracion recargada");
        } else {
            String msg5 = plugin.getTag()+"&cNo tienes permiso para ejecutar este comando.";
            sender.sendMessage(plugin.getMetodos().colorizar(msg5));
        }
        return true;
    }
    
}
