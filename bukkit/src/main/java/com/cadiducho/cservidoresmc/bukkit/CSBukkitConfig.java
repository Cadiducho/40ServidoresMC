package com.cadiducho.cservidoresmc.bukkit;

import com.cadiducho.cservidoresmc.api.CSConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CSBukkitConfig implements CSConfiguration {

    private final int configActual = 3;
    private boolean comandosCustom = true;
    public List<String> listaComandos = new ArrayList<>();

    private final BukkitPlugin bukkitPlugin;
    private final String path;

    public CSBukkitConfig(BukkitPlugin plugin, String path) {
        this.bukkitPlugin = plugin;
        this.path = path;
    }

    @Override
    public void load() {
        File file = new File(path);
        if (!file.exists()) {
            try {
                bukkitPlugin.getConfig().options().copyDefaults(true);
                bukkitPlugin.saveConfig();
                bukkitPlugin.log("Generando archivo config.yml correctamente");
            } catch (Exception e) {
                bukkitPlugin.log("Fallo al generar el config.yml!");
                bukkitPlugin.debugLog("Causa: " + e.toString());
            }
        }

        int configVer = getInt("configVer");
        if (configVer < configActual) {
            bukkitPlugin.logError("Tu configuración es de una versión más antigua a la de este plugin!"
                    + "Corrígelo o podrás tener errores..." );
        }
    }

    @Override
    public void reload() {
        bukkitPlugin.reloadConfig();
        reloadCustomCommands();
    }

    /**
     * Recargar el array de comandos custom desde la config
     */
    private void reloadCustomCommands() {
        comandosCustom = bukkitPlugin.getConfig().getBoolean("comandosCustom.activado", comandosCustom);

        if (comandosCustom) {
            try {
                listaComandos = getStringList("comandosCustom");
            } catch (NullPointerException e) {
                bukkitPlugin.logError("No se ha podido cargar los premios de comandos customizados! (Error Config)");
                comandosCustom = false;
            }
        }
    }

    @Override
    public List<String> customCommandsList() {
        return this.listaComandos;
    }

    @Override
    public String getString(String key, String defValue) {
        return bukkitPlugin.getConfig().getString(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return bukkitPlugin.getConfig().getInt(key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return bukkitPlugin.getConfig().getBoolean(key, defValue);
    }

    @Override
    public List<String> getStringList(String key) {
        return bukkitPlugin.getConfig().getStringList(key);
    }
}
