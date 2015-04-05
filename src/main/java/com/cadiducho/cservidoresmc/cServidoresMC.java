package com.cadiducho.cservidoresmc;

import com.cadiducho.cservidoresMC.util.Inventario;
import com.cadiducho.cservidoresMC.util.Metodos;
import com.cadiducho.cservidoresMC.util.Updater;
import com.cadiducho.cservidoresmc.util.LevelLog;

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

    public Inventario getInv();
    
    public Metodos getMetodos();
    
}
