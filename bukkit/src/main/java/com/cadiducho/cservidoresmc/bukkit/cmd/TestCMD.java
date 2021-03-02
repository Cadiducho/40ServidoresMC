package com.cadiducho.cservidoresmc.bukkit.cmd;

import org.bukkit.command.CommandSender;

import java.util.Collections;

/**
 * Comando para probar las recompensas sin votar realmente
 * @author Cadiducho
 */
public class TestCMD extends CommandBase {
    
    public TestCMD() {
        super("test40", "40servidores.test", Collections.emptyList());
    }
    
    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!perm(sender, getPermission(), true)) return;
        if (!soloJugador(sender, true)) return;
        
        plugin.sendMessage("&bPlataforma de test para 40ServidoresMC:", sender);
        sender.sendMessage("");
        plugin.sendMessage(plugin.getConfig().getString("mensaje"), sender);
        for (String cmds : plugin.listaComandos) {
            String comando = cmds.replace("{0}", sender.getName());
            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), comando);
        }
    }
    
    
}
