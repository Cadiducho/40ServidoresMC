package com.cadiducho.cservidoresmc.bukkit;

import com.cadiducho.cservidoresmc.ApiClient;
import com.cadiducho.cservidoresmc.Updater;
import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSConfiguration;
import com.cadiducho.cservidoresmc.api.CSConsoleSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;
import com.cadiducho.cservidoresmc.cmd.CSCommandManager;
import com.google.gson.Gson;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;

import com.cadiducho.cservidoresmc.bukkit.Vote40Event;

/**
 * Implementación para Bukkit, Spigot y Glowstone
 * @author Cadiducho
 */
public class BukkitPlugin extends JavaPlugin implements CSPlugin {

    @Getter private ApiClient apiClient;
    @Getter private Updater updater;
    
    private static BukkitPlugin instance;
    private CSConfiguration csConfiguration;
    private CSCommandManager commandManager;
    
    @Override
    public void onEnable() {
        instance = this;

        /*
         * Generar y cargar Config.yml
         */
        csConfiguration = new CSBukkitConfig(instance, getDataFolder() + File.separator + "config.yml");
        csConfiguration.load();

        apiClient = new ApiClient(instance, new Gson());

        /*
         * Comandos y eventos
         */
        debugLog("Registrando comandos y eventos...");
        registerCommands();

        installPlaceholderAPI();
        
        Metrics metrics = new Metrics(instance, 3909);

        /*
         * Finalizar...
         */
        updater = new Updater(instance, getPluginVersion(), getServer().getBukkitVersion().split("-")[0]);
        debugLog("Checkeando nuevas versiones...");
        updater.checkearVersion(null);
        log("Plugin 40ServidoresMC v" + getPluginVersion() + " cargado completamente");
    }

    @Override
    public void registerCommands() {
        this.commandManager = new CSCommandManager(instance);
    }

    /**
     * Comprobar si el plugin PlaceholderAPI está activo, y si es así registrar la extensión
     */
    private void installPlaceholderAPI() {
        if (this.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderHook(this).register();
        }
    }
    
    @Override
    public boolean onCommand(CommandSender bukkitSender, Command cmd, String label, String[] args) {
        if (label.startsWith(("40ServidoresMC:").toLowerCase())) {
            label = label.substring(("40ServidoresMC:").length());
        }
        CSCommandSender csCommandSender;
        if (bukkitSender instanceof ConsoleCommandSender) {
            csCommandSender = new CSConsoleSender(instance);
        } else {
            csCommandSender = new BukkitCommandSender(bukkitSender);
        }

        try {
            commandManager.executeCommand(csCommandSender, label, Arrays.asList(args));
        } catch (Exception ex) {
            logError("Error al ejecutar el comando '/" + label + Arrays.toString(args)+"'");
            debugLog(ex.getMessage());
            if (ex.getCause() != null) debugLog(ex.getCause().getMessage());
        }
        return true;
    }

    @Override
    public CSConfiguration getCSConfiguration() {
        return csConfiguration;
    }

    @Override
    public void log(String s) {
        getLogger().log(Level.INFO, s);
    }

    @Override
    public void logError(String s){
       getLogger().log(Level.SEVERE, s);
    }

    @Override
    public String getPluginVersion() {
        return this.getDescription().getVersion();
    }

    @Override
    public void dispatchCommand(String command) {
        try {
            Bukkit.getScheduler().callSyncMethod( this, () -> Bukkit.dispatchCommand( getServer().getConsoleSender(), command ) ).get();
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void dispatchEvent(CSCommandSender sender) {
        Vote40Event event = new Vote40Event((Player)sender);
        getServer().getPluginManager().callEvent(event);
    }

    @Override
    public void broadcastMessage(String message) {
        getServer().getScheduler().runTask(instance, () -> {
            getServer().getOnlinePlayers().forEach(p -> p.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
        });
    }

}
