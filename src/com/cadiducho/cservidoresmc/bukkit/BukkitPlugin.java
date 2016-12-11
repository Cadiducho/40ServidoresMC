package com.cadiducho.cservidoresmc.bukkit;

import com.cadiducho.cservidoresmc.bukkit.util.Util;
import com.cadiducho.cservidoresmc.bukkit.util.Updater;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Cadiducho
 * 
 * Implementación para Bukkit, Spigot y Glowstone
 */

public class BukkitPlugin extends JavaPlugin {
    
    private final Util met = new Util(this);
    private Updater updater;
    
    private final String tag = "&8[&b40ServidoresMC&8]";
    public int configVer = 0;
    public final int configActual = 3;
    public boolean comandosCustom = true;
    public List<String> listaComandos;
    
    public static BukkitPlugin instance;
    
    @Override
    public void onEnable() {
        instance = this;
        debugLog("Modo Debug activado en el plugin");
        
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
        
        /*
         * Finalizar...
         */
        updater = new Updater(this, this.getPluginVersion(), this.getServer().getBukkitVersion().split("-")[0]);
        debugLog("Checkeando nuevas versiones...");
        String actualizacion = updater.checkearVersion();
        if (actualizacion != null) {
            if (!actualizacion.equalsIgnoreCase("Versión actualizada")) { 
                log(actualizacion);
            }
        }
        log("Plugin 40ServidoresMC v"+this.getPluginVersion()+" cargado completamente");
    }
    
    public void cargarConfig() {
        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
            try {
                getConfig().options().copyDefaults(true);
                saveConfig();
                log("Generando archivo config.yml correctamente");
            } catch (Exception e) {
                this.getLogger().info("Fallo al generar el config.yml!");
                debugLog("Causa: "+e.toString());
            }
        }
        configVer = this.getConfig().getInt("configVer", configVer);
        if (configVer == 0) { 
        } else if (configVer < configActual) {
            log(Level.SEVERE, "Tu configuración es de una versión más antigua a la de este plugin!"
                + "Corrigelo o podrás tener errores..." );
        }
        comandosCustom = this.getConfig().getBoolean("comandosCustom.activado", comandosCustom);
        
        if (comandosCustom) {
            try {
                listaComandos = this.getConfig().getStringList("comandosCustom");
            } catch (NullPointerException e) {
                log(Level.WARNING, "No se ha podido cargar los premios de comandos customizados! (Error Config)");
                comandosCustom = false;
            }    
        }        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            CommandManager.onCmd(sender, cmd, label, args);
        } catch (Exception ex) {
            log(Level.SEVERE, "Error al ejecutar el comando '" + label + Arrays.toString(args)+"'");
            debugLog(ex.getMessage());
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
       getLogger().log(l, tag);
    }
    
    public void sendMessage(String str, CommandSender sender) {
        sender.sendMessage(met.colorizar(tag+" "+str));
    }
    
    public Util getMetodos() {
        return this.met;
    }
    
    public String getPluginVersion(){
        PluginDescriptionFile f = this.getDescription();
        return f.getVersion();
    }
    
    public String getTag() {
        return this.tag;     
    }
    
    public Updater getUpdater() {
        return this.updater;
    }

    public static BukkitPlugin getInstance() {
        return BukkitPlugin.instance;
    }

}
