package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.bukkit.util.Util;
import java.util.Arrays;
import java.util.logging.Level;

import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Cadiducho
 */

public class VoteCMD extends CommandBase {
      
    public VoteCMD() {
        super("voto40", "40servidores.voto", Arrays.asList("votar40", "vote40", "mivoto40"));
    }

    private static final JSONParser jsonParser = new JSONParser();

    @Override
    public void run(CommandSender sender, String label, String[] args) {
        try {
            String url = Util.readUrl("http://40servidoresmc.es/api2.php?nombre="+sender.getName()+"&clave="+plugin.getConfig().getString("clave"));
            Object parsedData = jsonParser.parse(url);
            if (parsedData instanceof JSONObject) {
                JSONObject jsonData = (JSONObject) parsedData;
                String web = (String) jsonData.get("web");
                int status = (int)((long) jsonData.get("status"));
                
                switch (status) {
                    case 0:
                        plugin.sendMessage("&6No has votado hoy! Puedes hacerlo en &a"+web, sender);
                        break;
                    case 1:
                        plugin.sendMessage(plugin.getConfig().getString("mensaje"), sender);
                        for (String cmds : plugin.listaComandos) {
                            String comando = cmds.replace("{0}", sender.getName());
                            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), comando);
                        }
                        break;
                    case 2:
                        plugin.sendMessage("&aGracias por votar, pero ya has obtenido tu premio!", sender);
                        break;
                    case 3:
                        plugin.sendMessage("&cClave incorrecta. Entra en &bhttp://40servidoresmc.es/miservidor.php &cy cambia esta.", sender);
                        break;
                    default:
                        plugin.sendMessage("&7Ha ocurrido un error. Prueba más tarde o avisa a un adminsitrador", sender);
                        break;
                }
            }
        } catch (Exception ex) {
            sender.sendMessage("&cHa ocurrido una excepción. Revisa la consola o avisa a un administrador");
            plugin.log(Level.SEVERE, "Excepción obteniendo estadisticas: "+ex.toString());
        }
    }
    
}
