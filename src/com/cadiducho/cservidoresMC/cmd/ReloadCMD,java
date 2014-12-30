package com.cadiducho.cservidoresMC.cmd;

import com.cadiducho.cservidoresMC.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author _arhlex_
 */

public class ReloadCMD implements CommandExecutor {
    
    public static Main plugin;
    
    public ReloadCMD(Main instance) {
        plugin = instance;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
       if (sender.hasPermission("40servidores.recargar")) { 
        	
         plugin.reloadConfig();
        	
          String msg5 = plugin.getTag()
            +"&aConfiguracion del plugin:\n &r"
             +  plugin.getName()
              + "\n &aVersion &r"
        	   +  plugin.getVersion()
        	    + "\n &aCreado por &r" 
                 + "Cadiducho" 
        	      + "\n&aRecargada correctamente";
          
          sender.sendMessage(plugin.getMetodos().colorizar(msg5));
          
          System.out.println("["+ plugin.getName() +"] Configuracion recargada");
        }
         else {
            String msg5 = plugin.getTag()+"&cNo tienes permiso para ejecutar este comando.";
            sender.sendMessage(plugin.getMetodos().colorizar(msg5));
        }
        return true;
    }
    
}
