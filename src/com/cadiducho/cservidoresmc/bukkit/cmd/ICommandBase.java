package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 *
 * @author Cadiducho
 */
    
public abstract interface ICommandBase {
    //Hay que rayarse mucho para implementar las dos APIs

    String getName();

    String getPermission();

    List<String> getAliases();

    void run(CommandSender sender, String label, String[] args);
    
    void run(ConsoleCommandSender sender, String label, String[] args);
    
    BukkitPlugin plugin = BukkitPlugin.getInstance();

}
