package com.cadiducho.cservidoresmc.cmd;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;
import com.cadiducho.cservidoresmc.model.ServerStats;
import com.cadiducho.cservidoresmc.model.ServerVote;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/**
 * Comando para obtener las estadísticas de tu servidor en 40ServidoresMC
 * @author Cadiducho
 */
public class StatsCMD extends CSCommand {

    protected StatsCMD() {
        super("stats40", "40servidores.stats", Collections.emptyList());
    }

    @Override
    public CommandResult execute(CSPlugin plugin, CSCommandSender sender, String label, List<String> args) {
        plugin.getApiClient().fetchServerStats().thenAccept((ServerStats serverStats) -> {
            if (serverStats.getServerName() == null) { //clave mal configurada
                sender.sendMessageWithTag("&cClave incorrecta. Entra en &bhttps://40servidoresmc.es/miservidor.php &cy cambia esta.");
                return;
            }

            sender.sendMessageWithTag("&9==> &7" + serverStats.getServerName() + " &festá en el TOP &a" + serverStats.getPosition());
            sender.sendMessageWithTag("&bVotos hoy: &6" + serverStats.getDayVotes());
            sender.sendMessageWithTag("&bVotos premiados hoy: &6" + serverStats.getRewardedDayVotes());
            sender.sendMessageWithTag("&bVotos semanales: &6" + serverStats.getWeekVotes());
            sender.sendMessageWithTag("&bVotos premiados semanales: &6" + serverStats.getRewardedWeekVotes());

            if (serverStats.getLastVotes() != null) {
                StringBuilder usuarios = new StringBuilder();
                for (ServerVote vote : serverStats.getLastVotes()) {
                    String color = vote.isRewarded() ? "&a" : "&c";
                    usuarios.append(color).append(vote.getName()).append("&6, ");
                }
                usuarios = new StringBuilder(usuarios.substring(0, usuarios.length() - 2) + ".");
                sender.sendMessageWithTag("&bÚltimos 20 votos: " + usuarios.toString());
            }
        }).exceptionally(ex -> {
            sender.sendMessageWithTag("&cHa ocurrido una excepción. Revisa la consola o avisa a un administrador");
            plugin.log(Level.SEVERE, "Excepción obteniendo estadisticas: " + ex.getMessage());
            return null;
        });
        return CommandResult.SUCCESS;
    }
}
