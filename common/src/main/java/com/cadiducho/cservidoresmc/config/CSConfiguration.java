package com.cadiducho.cservidoresmc.config;

import com.cadiducho.cservidoresmc.api.CSPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interfaz que cualquier tipo de servidor tendrá que implementar y devolver los datos correctos de configuración
 */
public interface CSConfiguration {

    /**
     * Recargar la configuración
     */
    void reload();

    /**
     * Devuelve la lista de comandos custom
     * @return Lista de comandos custom
     */
    default List<String> customCommandsList() {
        return getStringList("comandosCustom");
    }

    /**
     * Devuelve el tag del plugin para votación
     * @return El tag del plugin
     */
    default String getTag() {
        return getString("tag", "&8[&b40ServidoresMC&8]");
    }

    /**
     * Devuelve una string de la configuración
     * @param key La clave
     * @param defValue El valor por defecto
     * @return El valor de la clave en la configuración
     */
    String getString(String key, String defValue);

    /**
     * Devuelve una string de la configuración
     * @param key La clave
     * @return El valor de la clave en la configuración
     */
    default String getString(String key) {
        return getString(key, "");
    }

    /**
     * Devuelve un int de la configuración
     * @param key La clave
     * @param defValue El valor por defecto
     * @return El valor de la clave en la configuración
     */
    int getInt(String key, int defValue);

    /**
     * Devuelve un int de la configuración
     * @param key La clave
     * @return El valor de la clave en la configuración
     */
    default int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * Devuelve un boolean de la configuración
     * @param key La clave
     * @param defValue El valor por defecto
     * @return El valor de la clave en la configuración
     */
    boolean getBoolean(String key, boolean defValue);

    /**
     * Devuelve un boolean de la configuración
     * @param key La clave
     * @return El valor de la clave en la configuración
     */
    default boolean getBoolean(String key) {
        return getBoolean(key, true);
    }

    /**
     * Devuelve una lista de strings de la configuración
     * @param key La clave
     * @return El valor de la clave en la configuración
     */
    default List<String> getStringList(String key) {
        return getStringList(key, new ArrayList<>());
    }

    /**
     * Devuelve una lista de strings de la configuración
     * @param path La clave
     * @param def El valor por defecto
     * @return El valor de la clave en la configuración
     */
    List<String> getStringList(String path, List<String> def);

    /**
     * Devuelve un mapa de strings de la configuración
     * @param path La clave
     * @return El valor de la clave en la configuración
     */
    default Map<String, String> getStringMap(String path) {
        return getStringMap(path, new HashMap<>());
    }

    /**
     * Devuelve un mapa de strings de la configuración
     * @param path La clave
     * @param def El valor por defecto
     * @return El valor de la clave en la configuración
     */
    Map<String, String> getStringMap(String path, Map<String, String> def);

    /**
     * Devuelve el plugin de CServidoresMC
     * @return El plugin
     */
    CSPlugin getPlugin();
}
