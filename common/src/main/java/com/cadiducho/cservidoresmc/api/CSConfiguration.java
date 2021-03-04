package com.cadiducho.cservidoresmc.api;

import java.util.List;

public interface CSConfiguration {

    void load();
    void reload();

    List<String> customCommandsList();

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

    List<String> getStringList(String key);
}
