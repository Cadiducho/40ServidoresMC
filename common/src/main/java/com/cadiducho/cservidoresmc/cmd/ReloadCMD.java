package com.cadiducho.cservidoresmc.cmd;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * Comando para recargar la configuración y sus recompensas
 * @author _arhlex_, Cadiducho
 */
public class ReloadCMD extends CSCommand {

    protected ReloadCMD() {
        super("reload40", "40servidores.recargar", Arrays.asList("recargar40", "config40"));
    }

    @Override
    public CommandResult execute(CSPlugin plugin, CSCommandSender sender, String label, List<String> args) {
        plugin.getCSConfiguration().reload();

        sender.sendMessageWithTag("&aConfiguración recargada correctamente");
        sender.sendMessageWithTag("&aFuncionando la versión " + plugin.getPluginVersion());

        plugin.log("Configuracion recargada");
        return CommandResult.SUCCESS;
    }
}
