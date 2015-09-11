package com.cadiducho.cservidoresmc.bukkit.cmd;

import java.util.Arrays;
import org.bukkit.command.CommandSender;

/**
 *
 * @author _arhlex_, Cadiducho
 */

public class ReloadCMD extends CommandBase {
    
    public ReloadCMD() {
        super("reload40", "40servidores.recargar", Arrays.asList("recargar40", "config40"));
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!perm(sender, getPermission(), true)) return;
        
        plugin.reloadConfig();
        plugin.sendMessage("&aConfiguración recargada correctamente", sender);
        plugin.sendMessage("&aFuncionando la versión " + plugin.getPluginVersion(), sender);
          
        plugin.log("Configuracion recargada");
    }
    
}
