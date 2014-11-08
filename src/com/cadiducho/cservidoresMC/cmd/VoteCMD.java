package com.cadiducho.cservidoresMC.cmd;

import com.cadiducho.cservidoresMC.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Cadiducho
 */

public class VoteCMD implements CommandExecutor {
    
    public static Main plugin;
    
    public VoteCMD(Main instance) {
        plugin = instance;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("40servidores.voto")) {
            if (sender instanceof Player) { 
                Player player = (Player)sender;
                switch (plugin.getMetodos().getVoto(player, plugin.getConfig().getString("clave"))) {
                    case 0:
                        String msg0 = plugin.getTag()+"&6No has votado hoy! Puedes hacerlo en &ahttp://www.40servidoresmc.es/";
                        sender.sendMessage(plugin.getMetodos().colorizar(msg0));
                        //No ha votado
                        break;
                    case 2:
                        if (plugin.getConfig().getBoolean("broadcast.activado")) {
                            String bc = plugin.getTag()+plugin.getConfig().getString("broadcast.mensajeBroadcast").replace("{0}", player.getDisplayName());
                            Bukkit.broadcastMessage(plugin.getMetodos().colorizar(bc));
                        }
                        String msg1 = plugin.getTag()+plugin.getConfig().getString("mensaje");
                        sender.sendMessage(plugin.getMetodos().colorizar(msg1));
                        //Ha votado
                        break;
                    case 1:
                        String msg2 = plugin.getTag()+"&aGracias por votar, pero ya has obtenido tu premio!";
                        sender.sendMessage(plugin.getMetodos().colorizar(msg2));
                        plugin.getInv().darPremio(player); //ToDO PREMIOS KIT
                        break;
                    default:
                        String msg3 = plugin.getTag()+"&7Ha ocurrido un error. Prueba más tarde o avisa a un adminsitrador";
                        sender.sendMessage(plugin.getMetodos().colorizar(msg3));
                        //Fallo
                        break;    
                }

            } else {
                String msg = plugin.getTag()+"&cNo puedes usar este comando si no eres un jugador";
                sender.sendMessage(plugin.getMetodos().colorizar(msg)); 
            }
        }
        return true;
    }
    
}
