package com.cadiducho.cservidoresmc.cmd;

import com.cadiducho.cservidoresmc.Cooldown;
import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;
import com.cadiducho.cservidoresmc.model.VoteResponse;
import com.cadiducho.cservidoresmc.model.VoteStatus;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * Comando para validar el voto en 40ServidoresMC
 */
public class VoteCMD extends CSCommand {

    protected VoteCMD() {
        super("voto40", "40servidores.voto", Arrays.asList("votar40", "vote40", "mivoto40"));
    }

    final Cooldown cooldown = new Cooldown(60);

    @Override
    public CommandResult execute(CSPlugin plugin, CSCommandSender sender, String label, List<String> args) {
        if (sender.isConsole()) {
            return CommandResult.ONLY_PLAYER;
        }

        if (cooldown.isCoolingDown(sender.getName())) {
            return CommandResult.COOLDOWN;
        }

        cooldown.setOnCooldown(sender.getName());

        sender.sendMessageWithTag("&7Obteniendo voto...");
        plugin.getApiClient().validateVote(sender.getName()).thenAccept((VoteResponse voteResponse) -> {
            String web = voteResponse.getWeb();
            VoteStatus status = voteResponse.getStatus();

            switch (status) {
                case NOT_VOTED:
                    sender.sendMessageWithTag("&6No has votado hoy! Puedes hacerlo en &a" + web);
                    break;
                case SUCCESS:
                    sender.sendMessageWithTag(plugin.getCSConfiguration().getString("mensaje"));

                    plugin.getCSConfiguration().customCommandsList().stream()
                    .map(cmds -> cmds.replace("{0}", sender.getName()))
                    .forEach(cmd -> plugin.dispatchCommand(cmd));

                    if (plugin.getCSConfiguration().getBoolean("broadcast.activado")) {
                        plugin.broadcastMessage(plugin.getCSConfiguration().getString("broadcast.mensajeBroadcast").replace("{0}", sender.getName()));
                    }

                    plugin.dispatchEvent(sender);
                    
                    break;
                case ALREADY_VOTED:
                    sender.sendMessageWithTag("&aGracias por votar, pero ya has obtenido tu premio!");
                    break;
                case INVALID_kEY:
                    sender.sendMessageWithTag("&cClave incorrecta. Entra en &bhttps://40servidoresmc.es/miservidor.php &cy cambia esta.");
                    break;
                default:
                    sender.sendMessageWithTag("&7Ha ocurrido un error. Prueba más tarde o avisa a un adminsitrador");
                    break;
            }
        }).exceptionally(e -> {
            sender.sendMessageWithTag("&cHa ocurrido una excepción. Avisa a un administrador");
            plugin.logError("Excepción intentando votar: " + e.getMessage());
            return null;
        });

        return CommandResult.SUCCESS;
    }
}
