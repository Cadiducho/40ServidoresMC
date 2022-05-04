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

    void reload();

    default List<String> customCommandsList() {
        return getStringList("comandosCustom");
    }

    String getString(String key, String defValue);

    default String getString(String key) {
        return getString(key, "");
    }

    int getInt(String key, int defValue);

    default int getInt(String key) {
        return getInt(key, 0);
    }

    boolean getBoolean(String key, boolean defValue);

    default boolean getBoolean(String key) {
        return getBoolean(key, true);
    }

    default List<String> getStringList(String key) {
        return getStringList(key, new ArrayList<>());
    }

    List<String> getStringList(String path, List<String> def);

    default Map<String, String> getStringMap(String path) {
        return getStringMap(path, new HashMap<>());
    }
    Map<String, String> getStringMap(String path, Map<String, String> def);

    CSPlugin getPlugin();
}
