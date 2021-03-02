package com.cadiducho.cservidoresmc.bukkit;

import com.cadiducho.cservidoresmc.ApiClient;
import com.cadiducho.cservidoresmc.bukkit.util.Updater;
import com.google.gson.Gson;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * Implementación para Bukkit, Spigot y Glowstone
 * @author Cadiducho
 */
public class BukkitPlugin extends JavaPlugin {

    @Getter private ApiClient apiClient;
    @Getter private Updater updater;
    
    private final String tag = "&8[&b40ServidoresMC&8]";
    private int configVer = 0;
    private final int configActual = 3;
    private boolean comandosCustom = true;
    public List<String> listaComandos;
    
    private static BukkitPlugin instance;
    
    public static BukkitPlugin get() {
        return instance;
    }
    
    @Override
    public void onEnable() {
        instance = this;
        debugLog("Modo Debug activado en el plugin");

        apiClient = new ApiClient(new Gson());

        /*
         * Generar y cargar Config.yml
         */
        debugLog("Cargando configuración...");
        cargarConfig();
        
        /*
         * Comandos y eventos
         */
        debugLog("Registrando comandos y eventos...");
        CommandManager.load();

        installPlaceholderAPI();
        
        Metrics metrics = new Metrics(instance, 3909);
        /*
         * Finalizar...
         */
        updater = new Updater(this, getPluginVersion(), getServer().getBukkitVersion().split("-")[0]);
        debugLog("Checkeando nuevas versiones...");
        updater.checkearVersion(null, true);
        log("Plugin 40ServidoresMC v" + getPluginVersion() + " cargado completamente");
    }

    private void cargarConfig() {
        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
            try {
                getConfig().options().copyDefaults(true);
                saveConfig();
                log("Generando archivo config.yml correctamente");
            } catch (Exception e) {
                this.getLogger().info("Fallo al generar el config.yml!");
                debugLog("Causa: " + e.toString());
            }
        }
        configVer = this.getConfig().getInt("configVer", configVer);
        if (configVer < configActual) {
            log(Level.SEVERE, "Tu configuración es de una versión más antigua a la de este plugin!"
                + "Corrígelo o podrás tener errores..." );
        }
        reloadComandosCustom();      
    }
    
    /**
     * Recargar el array de comandos custom desde la config
     */
    public void reloadComandosCustom() {
        comandosCustom = getConfig().getBoolean("comandosCustom.activado", comandosCustom);
        
        if (comandosCustom) {
            try {
                listaComandos = getConfig().getStringList("comandosCustom");
            } catch (NullPointerException e) {
                log(Level.WARNING, "No se ha podido cargar los premios de comandos customizados! (Error Config)");
                comandosCustom = false;
            }    
        }  
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
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            CommandManager.onCmd(sender, cmd, label, args);
        } catch (Exception ex) {
            log(Level.SEVERE, "Error al ejecutar el comando '" + label + Arrays.toString(args)+"'");
            debugLog(ex.getMessage());
            if (ex.getCause() != null) debugLog(ex.getCause().getMessage());
        }
        return true;
    }
    
    public boolean isDebug() {
        return this.getConfig().getBoolean("debug");
    }
    
    public void debugLog(String s) {
        if (isDebug()){
            getLogger().log(Level.INFO, "[Debug] {0}", s);
        }
    }
    
    public void log(String s) {
        getLogger().log(Level.INFO, s);
    }
    
    public void log(Level l, String s){
       getLogger().log(l, s);
    }
    
    public void sendMessage(String str, CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', tag + " " + str));
    }
    
    public String getPluginVersion(){
        return this.getDescription().getVersion();
    }

}
