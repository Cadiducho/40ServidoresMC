package com.cadiducho.cservidoresmc.impl;

import com.cadiducho.cservidoresMC.cmd.ReloadCMD;
import com.cadiducho.cservidoresMC.cmd.UpdateCMD;
import com.cadiducho.cservidoresMC.cmd.VoteCMD;
import com.cadiducho.cservidoresMC.util.Metodos;
import com.cadiducho.cservidoresMC.util.Updater;
import com.cadiducho.cservidoresmc.cServidoresMC;
import com.cadiducho.cservidoresmc.util.LevelLog;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Cadiducho
 * 
 * Implementación para Bukkit, Spigot y Glowstone
 */

public class IBukkit extends JavaPlugin implements cServidoresMC {
    
    private final Metodos met = new Metodos(this);
    private Updater updater;
    
    private final VoteCMD voteCMD = new VoteCMD(this);
    private final UpdateCMD updateCMD = new UpdateCMD(this);
    private final ReloadCMD reloadCMD = new ReloadCMD(this);
    
    private final String tag = "&8[&b40ServidoresMC&8]";
    public int configVer = 0;
    public final int configActual = 2;
    public boolean comandosCustom = true;
    public List<String> listaComandos;
    
    public static IBukkit instance;
    
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
        PluginManager pluginManager = this.getServer().getPluginManager();
        debugLog("Registrando comandos y eventos...");
        
        this.getCommand("vote40").setExecutor(voteCMD);
        this.getCommand("update40").setExecutor(updateCMD);
        this.getCommand("reload40").setExecutor(reloadCMD);
        
        /*
         * Finalizar...
         */
        updater = new Updater(this, this.getVersion(), this.getServer().getVersion().split(":")[1]);
        debugLog("Checkeando nuevas versiones...");
        String actualizacion = updater.checkearVersion();
        if (actualizacion != null) {
            if (!actualizacion.equalsIgnoreCase("version actualizada")) { 
                log(actualizacion);
            }
        }
        log("Plugin 40ServidoresMC v"+this.getVersion()+" cargado completamente");
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
            log(LevelLog.SEVERE, "Tu configuración es de una versión más antigua a la de este plugin!"
                + "Corrigelo o podrás tener errores..." );
        }
        comandosCustom = this.getConfig().getBoolean("comandosCustom.activado", comandosCustom);
        
        if (comandosCustom) {
            try {
                listaComandos = this.getConfig().getStringList("comandosCustom.comandos");
            } catch (NullPointerException e) {
                log(LevelLog.WARNING, "No se ha podido cargar los premios de comandos customizados! (Error Config)");
                comandosCustom = false;
            }    
        }        
    }
    
    @Override
    public boolean isDebug() {
        return this.getConfig().getBoolean("debug");
    }
    
    @Override
    public void debugLog(String s) {
        if (isDebug()){
            getLogger().log(Level.INFO, "[Debug] {0}", s);
        }
    }
    
    @Override
    public void log(String s) {
        getLogger().log(Level.INFO, s);
    }
    
    @Override
    public void log(LevelLog l, String s){
        if (l == LevelLog.INFO) getLogger().info(s);
        else if (l == LevelLog.SEVERE) getLogger().severe(s);
        else if (l == LevelLog.WARNING) getLogger().warning(s);
    }
    
    @Override
    public Metodos getMetodos() {
        return this.met;
    }
    
    @Override
    public String getVersion(){
        PluginDescriptionFile f = this.getDescription();
        return f.getVersion();
    }
    
    @Override
    public String getTag() {
        return this.tag;     
    }
    
    @Override
    public Updater getUpdater() {
        return this.updater;
    }

}
