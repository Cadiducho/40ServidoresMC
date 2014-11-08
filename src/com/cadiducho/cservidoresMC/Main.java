package com.cadiducho.cservidoresMC;

import com.cadiducho.cservidoresMC.cmd.UpdateCMD;
import com.cadiducho.cservidoresMC.cmd.VoteCMD;
import com.cadiducho.cservidoresMC.util.Inventario;
import com.cadiducho.cservidoresMC.util.Metodos;
import com.cadiducho.cservidoresMC.util.Version;
import java.io.File;
import java.util.logging.Level;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Cadiducho
 */

public class Main extends JavaPlugin {
    
    private final Metodos met = new Metodos(this);
    private Version updater;
    private final Inventario inv = new Inventario(this);
    
    private final VoteCMD voteCMD = new VoteCMD(this);
    private final UpdateCMD updateCMD = new UpdateCMD(this);
    
    private final String tag = "&8[&b40ServidoresMC&8]";
    
    @Override
    public void onEnable() {
        debugLog("Modo Debug activado en el plugin");
        /*
         * Generar y cargar Config.yml
         */
        debugLog("Cargando configuración...");
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
        /*
         * Comandos y eventos
         */
        PluginManager pluginManager = this.getServer().getPluginManager();
        debugLog("Registrando comandos y eventos...");
        
        this.getCommand("vote40").setExecutor(voteCMD);
        this.getCommand("update40").setExecutor(updateCMD);
        
        /*
         * Finalizar...
         */
        updater = new Version(this, this.getVersion());
        debugLog("Checkeando nuevas versiones...");
        String actualizacion = updater.checkearVersion();
        if (actualizacion != null) {
            if (!actualizacion.equalsIgnoreCase("version actualizada")) { 
                log(actualizacion);
            }
        }
        log("Plugin 40ServidoresMC v"+this.getVersion()+" cargado completamente");
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
    
    public Metodos getMetodos() {
        return this.met;
    }
    
    public String getVersion(){
        PluginDescriptionFile f = this.getDescription();
        return f.getVersion();
    }
    
    public String getTag() {
        return this.tag;     
    }
    
    public Version getUpdater() {
        return this.updater;
    }
    
    public Inventario getInv() {
        return this.inv;
    }
    
}
