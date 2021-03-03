package com.cadiducho.cservidoresmc.api;

import java.util.logging.Level;

public interface CSPlugin {

    void log(String text);
    void log(Level level, String text);

    boolean isDebug();
    default void debugLog(String s) {
        if (isDebug()){
            log(Level.INFO, "[Debug] " + s);
        }
    }
}
