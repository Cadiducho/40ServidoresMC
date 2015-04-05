package com.cadiducho.cservidoresmc.impl;

import com.cadiducho.cservidoresMC.util.Inventario;
import com.cadiducho.cservidoresMC.util.Metodos;
import com.cadiducho.cservidoresMC.util.Updater;
import com.cadiducho.cservidoresmc.cServidoresMC;
import com.cadiducho.cservidoresmc.util.LevelLog;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.plugin.PluginManager;
/**
 *
 * @author Cadiducho
 * 
 * Implementación para Sponge y Granite
 */
@Plugin(id = "40ServidoresMC", name = "40ServidoresMC", version = "2.0")
public class ISponge implements cServidoresMC {
    
    @Inject private PluginManager pluginManager;
    public static ISponge instance;
    @Inject public static Logger logger;
    @Inject Game game;
    private final String tag = "&8[&b40ServidoresMC&8]";
    private final PluginContainer plugin = pluginManager.getPlugin("40ServidoresMC").orNull();
    
    private Updater updater;
    
    @Subscribe
    public void onEnable(InitializationEvent event) {
        instance = this;
        updater = new Updater(this, this.getVersion() ,this.game.getMinecraftVersion().getName());
    }
    
    @Subscribe
    public void onDisable(ServerStoppingEvent ev) {

    }
    
    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public String getVersion() {
        return plugin.getVersion();
    }

    @Override
    public boolean isDebug() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void debugLog(String s) {
        if (isDebug()) {
            logger.debug(s);
        }
    }

    @Override
    public void log(String s) {
        logger.info(s);
    }

    @Override
    public void log(LevelLog l, String s) {
        if (l == LevelLog.INFO) logger.info(s);
        else if (l == LevelLog.SEVERE) logger.error(s);
        else if (l == LevelLog.WARNING) logger.warn(s);
    }

    @Override
    public Updater getUpdater() {
        return IBukkit.instance.getUpdater(); //nope
    }

    @Override
    public Inventario getInv() {
        return IBukkit.instance.getInv(); //nope
    }

    @Override
    public Metodos getMetodos() {
        return IBukkit.instance.getMetodos(); //nope
    }

    @Override
    public String getTag() {
        return this.tag;
    }
    
}
