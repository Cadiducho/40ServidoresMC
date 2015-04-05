package com.cadiducho.cservidoresmc.impl;

import com.cadiducho.cservidoresMC.util.Inventario;
import com.cadiducho.cservidoresMC.util.Metodos;
import com.cadiducho.cservidoresMC.util.Updater;
import com.cadiducho.cservidoresmc.cServidoresMC;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
/**
 *
 * @author Cadiducho
 * 
 * Implementación para Sponge y Granite
 */
@Plugin(id = "40ServidoresMC", name = "40ServidoresMC", version = "2.0")
public class ISponge implements cServidoresMC {
    
    public static ISponge instance;
    @Inject
    public static Logger logger;
    
    @Subscribe
    public void onEnable(InitializationEvent event) {
        instance = this;
    }
    
    @Subscribe
    public void onDisable(ServerStoppingEvent ev) {

    }
    
    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public ISponge getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDebug() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void debugLog(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void log(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void log(Level l, String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTag() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Updater getUpdater() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Inventario getInv() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metodos getMetodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
