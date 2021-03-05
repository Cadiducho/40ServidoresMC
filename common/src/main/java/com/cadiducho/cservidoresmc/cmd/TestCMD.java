package com.cadiducho.cservidoresmc.cmd;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;

import java.util.Collections;
import java.util.List;

/**
 * Comando para probar las recompensas sin votar realmente
 * @author Cadiducho
 */
public class TestCMD extends CSCommand {

    protected TestCMD() {
        super("test40", "40servidores.test", Collections.emptyList());
    }

    @Override
    public CommandResult execute(CSPlugin plugin, CSCommandSender sender, String label, List<String> args) {
        if (sender.isConsole()) {
            return CommandResult.ONLY_PLAYER;
        }

        sender.sendMessageWithTag("&bPlataforma de test para 40ServidoresMC:");
        sender.sendMessage("");
        sender.sendMessageWithTag(plugin.getCSConfiguration().getString("mensaje"));
        for (String cmds : plugin.getCSConfiguration().customCommandsList()) {
            String comando = cmds.replace("{0}", sender.getName());
            plugin.dispatchCommand(comando);
        }

        return CommandResult.SUCCESS;
    }
}
