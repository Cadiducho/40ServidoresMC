package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.bukkit.util.ApiResponse;
import com.cadiducho.cservidoresmc.bukkit.util.Util;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.logging.Level;

/**
 * Comando para votar
 *
 * @author Cadiducho
 */
public class VoteCMD extends CommandBase {

    public VoteCMD() {
        super("voto40", "40servidores.voto", Arrays.asList("votar40", "vote40", "mivoto40"));
    }
    
    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!perm(sender, getPermission(), true)) {
            return;
        }
        if (!soloJugador(sender, true)) {
            return;
        }

        Util.readUrl("https://40servidoresmc.es/api2.php?nombre=" + sender.getName() + "&clave=" + plugin.getConfig().getString("clave"), (ApiResponse response) -> {
            if (response.getException().isPresent()) {
                sender.sendMessage("&cHa ocurrido una excepción. Avisa a un administrador");
                plugin.log(Level.SEVERE, "Excepción intentando votar: " + response.getException().get().toString());
                return;
            }

            JSONObject jsonData = response.getResult();
            String web = (String) jsonData.get("web");
            int status = (int) ((long) jsonData.get("status"));
            
            switch (status) {
                case 0:
                    plugin.sendMessage("&6No has votado hoy! Puedes hacerlo en &a" + web, sender);
                    break;
                case 1:
                    plugin.sendMessage(plugin.getConfig().getString("mensaje"), sender);
                    plugin.listaComandos.stream()
                            .map(cmds -> cmds.replace("{0}", sender.getName()))
                            .forEach(comando -> plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), comando));

                    if (plugin.getConfig().getBoolean("broadcast.activado")) {
                        plugin.getServer().broadcastMessage(metodos.colorizar(plugin.getConfig().getString("broadcast.mensajeBroadcast").replace("{0}", sender.getName())));
                    }
                    break;
                case 2:
                    plugin.sendMessage("&aGracias por votar, pero ya has obtenido tu premio!", sender);
                    break;
                case 3:
                    plugin.sendMessage("&cClave incorrecta. Entra en &bhttps://40servidoresmc.es/miservidor.php &cy cambia esta.", sender);
                    break;
                default:
                    plugin.sendMessage("&7Ha ocurrido un error. Prueba más tarde o avisa a un adminsitrador", sender);
                    break;
            }
        });
    }
}
