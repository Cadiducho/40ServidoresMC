package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.Cooldown;
import com.cadiducho.cservidoresmc.model.VoteResponse;
import com.cadiducho.cservidoresmc.model.VoteStatus;
import com.google.gson.Gson;
import org.bukkit.command.CommandSender;

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
    
    final Cooldown cooldown = new Cooldown(60);
    
    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!perm(sender, getPermission(), true)) {
            return;
        }
        if (!soloJugador(sender, true)) {
            return;
        }
        
        if (cooldown.isCoolingDown(sender.getName())) {
            plugin.sendMessage("&6No puedes ejecutar este comando tantas veces seguidas!", sender);
            return;
        }
        
        cooldown.setOnCooldown(sender.getName());
        
        plugin.sendMessage("&7Obteniendo voto...", sender);
        plugin.getApiClient().validateVote(sender.getName()).thenAccept((VoteResponse voteResponse) -> {
            System.out.println(new Gson().toJson(voteResponse));
            String web = voteResponse.getWeb();
            VoteStatus status = voteResponse.getStatus();

            switch (status) {
                case NOT_VOTED:
                    plugin.sendMessage("&6No has votado hoy! Puedes hacerlo en &a" + web, sender);
                    break;
                case SUCCESS:
                    plugin.sendMessage(plugin.getConfig().getString("mensaje"), sender);
                    plugin.getServer().getScheduler().runTask(plugin, () -> {
                        plugin.listaComandos.stream()
                                .map(cmds -> cmds.replace("{0}", sender.getName()))
                                .forEach(comando -> plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), comando));

                        if (plugin.getConfig().getBoolean("broadcast.activado")) {
                            plugin.getServer().getOnlinePlayers()
                                    .forEach(p -> plugin.sendMessage(plugin.getConfig().getString("broadcast.mensajeBroadcast").replace("{0}", sender.getName()), p));
                        }
                    });
                    break;
                case ALREADY_VOTED:
                    plugin.sendMessage("&aGracias por votar, pero ya has obtenido tu premio!", sender);
                    break;
                case INVALID_kEY:
                    plugin.sendMessage("&cClave incorrecta. Entra en &bhttps://40servidoresmc.es/miservidor.php &cy cambia esta.", sender);
                    break;
                default:
                    plugin.sendMessage("&7Ha ocurrido un error. Prueba más tarde o avisa a un adminsitrador", sender);
                    break;
            }
        }).exceptionally(e -> {
            plugin.sendMessage("&cHa ocurrido una excepción. Avisa a un administrador", sender);
            plugin.log(Level.SEVERE, "Excepción intentando votar: " + e.getMessage());
            return null;
        });
    }
}
