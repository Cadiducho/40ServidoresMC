package com.cadiducho.cservidoresmc.bukkit.cmd;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;
import com.cadiducho.cservidoresmc.bukkit.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Framework de comandos de Meriland.es
 * @author Cadiducho
 */
public abstract class CommandBase {
    
    private final transient String name;
    private final transient String perm;
    private final transient List<String> aliases;
    protected static transient BukkitPlugin plugin = BukkitPlugin.get();
    protected static transient Util metodos = BukkitPlugin.get().getMetodos();
      
    protected CommandBase(final String name, final String perm, final List<String> aliases) {
        this.name = name;
        this.perm = perm;
        this.aliases = aliases;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPermission() {
        return perm;
    }

    public List<String> getAliases() {
        return aliases;
    }
    
    public void run(ConsoleCommandSender sender, String label, String[] args) {
        run((CommandSender) sender, label, args);
    }
    
    public void run(CommandSender sender, String label, String[] args) {
        
    }
    
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return new ArrayList<>();
    }
    
    protected static boolean perm(CommandSender p, String perm, Boolean message) {
        if (!(p instanceof Player)) {
            return true;
        }
        Player pl = (Player) p;
        Boolean hasperm = perm(pl, perm);
        if (hasperm == false && message == true) {
            plugin.sendMessage("&cNo tienes permiso para usar este comando", pl);
        }
        return hasperm;
    }
    
    private static boolean perm(Player p, String perm) {
        if (p.isOp()) {
            return true;
        }
        return p.hasPermission(perm);    
    }
    
    protected static boolean soloJugador(CommandSender p, Boolean message) {
        if (!(p instanceof Player)) {
            if (message == true) {
                plugin.sendMessage("&cNo puedes usar este comando si no eres un jugador", p); 
            }
            return false;
        }
        return true;
    }
}
