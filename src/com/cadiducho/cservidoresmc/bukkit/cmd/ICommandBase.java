package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 *
 * @author Cadiducho. Framework de comandos de Meriland.es
 */
    
public abstract interface ICommandBase {
    String getName();

    String getPermission();

    List<String> getAliases();

    void run(CommandSender sender, String label, String[] args);
    
    void run(ConsoleCommandSender sender, String label, String[] args);
    
    List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn);
    
    BukkitPlugin plugin = BukkitPlugin.getInstance();

}
