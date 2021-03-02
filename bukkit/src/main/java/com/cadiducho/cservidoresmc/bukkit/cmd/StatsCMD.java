package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.model.ServerStats;
import com.cadiducho.cservidoresmc.model.ServerVote;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.logging.Level;

/**
 * Comando para obtener las estadísticas de tu servidor en 40ServidoresMC
 * @author Cadiducho
 */
public class StatsCMD extends CommandBase {
    
    public StatsCMD() {
        super("stats40", "40servidores.stats", Collections.emptyList());
    }
    
    @Override
    public void run(CommandSender sender, String label, String[] args) {
        if (!perm(sender, getPermission(), true)) return;

        plugin.getApiClient().fetchServerStats().thenAccept((ServerStats serverStats) -> {
            if (serverStats.getServerName() == null) { //clave mal configurada
                plugin.sendMessage("&cClave incorrecta. Entra en &bhttps://40servidoresmc.es/miservidor.php &cy cambia esta.", sender);
                return;
            }

            plugin.sendMessage("&9==> &7" + serverStats.getServerName() + " &festá en el TOP &a" + serverStats.getPosition(), sender);
            plugin.sendMessage("&bVotos hoy: &6" + serverStats.getDayVotes(), sender);
            plugin.sendMessage("&bVotos premiados hoy: &6" + serverStats.getRewardedDayVotes(), sender);
            plugin.sendMessage("&bVotos semanales: &6" + serverStats.getWeekVotes(), sender);
            plugin.sendMessage("&bVotos premiados semanales: &6" + serverStats.getRewardedWeekVotes(), sender);

            if (serverStats.getLastVotes() != null) {
                StringBuilder usuarios = new StringBuilder();
                for (ServerVote vote : serverStats.getLastVotes()) {
                    String strellita = vote.isRewarded() ? "&a" : "&c";
                    usuarios.append(strellita).append(vote.getName()).append("&6, ");
                }
                usuarios = new StringBuilder(usuarios.substring(0, usuarios.length() - 2) + ".");
                plugin.sendMessage("&bÚltimos 20 votos: " + usuarios, sender);
            }
        }).exceptionally(ex -> {
            plugin.sendMessage("&cHa ocurrido una excepción. Revisa la consola o avisa a un administrador", sender);
            plugin.log(Level.SEVERE, "Excepción obteniendo estadisticas: " + ex.getMessage());
            return null;
        });
    }
}
