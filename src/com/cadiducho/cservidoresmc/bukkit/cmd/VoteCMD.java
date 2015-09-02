package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Cadiducho
 * @ToDo: Actualizar a la nueva API
 */

public class VoteCMD extends CommandBase {
      
    public VoteCMD(BukkitPlugin instance) {
        super("voto40", "40servidores.voto", Arrays.asList("votar40", "vote40", "mivoto40"));
    }

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!perm(sender, getPermission(), true)) return;
        if (!soloJugador(sender, true)) return;
        
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
    }
    
}
