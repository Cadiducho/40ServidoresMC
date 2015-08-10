package com.cadiducho.cservidoresmc;

import com.cadiducho.cservidoresmc.bukkit.util.Util;
import com.cadiducho.cservidoresmc.bukkit.util.Updater;
import com.cadiducho.cservidoresmc.bukkit.util.LevelLog;

/**
 *
 * @author Cadiducho
 * 
 * Lista de métodos comunes a sobreescribir en cada implementación
 * Bukkit o Sponge
 */

public interface cServidoresMC {
    
    public Object getLogger(); //org.slf4j.Logger o java.util.logging.Logger

    public String getVersion();
    
    public boolean isDebug();
    
    public void debugLog(String s);
    
    public void log(String s);
    
    public void log(LevelLog l, String s);
    
    public String getTag();
    
    public Updater getUpdater();
    
    public Util getMetodos();
    
    public Object getInstance(); //IBukkit o ISponge
    
}
