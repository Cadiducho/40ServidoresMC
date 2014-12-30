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
                        if (plugin.premioFisico) {
                            plugin.getInv().darPremio(player);
                            System.out.println("["+ plugin.getName() + player + "Ha obtenido un premio por votar");

                        }
                        if (plugin.comandosCustom) {
                            for (String cmds : plugin.listaComandos) {
                                String comando = cmds.replace("{0}", player.getName());
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando);
                            }
                        }
                        //Ha votado
                        break;
                    case 1:
                        String msg2 = plugin.getTag()+"&aGracias por votar, pero ya has obtenido tu premio!";
                        sender.sendMessage(plugin.getMetodos().colorizar(msg2));
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
        } else {
            String msg4 = plugin.getTag()+"&cNo tienes permiso para ejecutar este comando.";
            sender.sendMessage(plugin.getMetodos().colorizar(msg4));
        }
        return true;
    }
    
}
