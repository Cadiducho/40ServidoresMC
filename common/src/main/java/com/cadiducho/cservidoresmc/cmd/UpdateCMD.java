package com.cadiducho.cservidoresmc.cmd;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * Comando para verificar si hay actualizaciones del plugin
 */
public class UpdateCMD extends CSCommand {

    protected UpdateCMD() {
        super("update40", "40servidores.actualizar", Arrays.asList("actualizar40", "upd40"),
                "Comprueba si hay actualizaciones del plugin",
                "Usa /update40 para comprobar si hay actualizaciones");
    }

    @Override
    public CommandResult execute(CSPlugin plugin, CSCommandSender sender, String label, List<String> args) {
        plugin.getUpdater().checkearVersion(sender, true);
        return CommandResult.SUCCESS;
    }
}
