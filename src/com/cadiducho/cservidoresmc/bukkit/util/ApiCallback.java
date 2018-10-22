package com.cadiducho.cservidoresmc.bukkit.util;

/**
 * Callback
 * @author Cadiducho
 */
@FunctionalInterface
public interface ApiCallback {
    
    /**
     * Código a ejecutar cuando se realice el callback
     * @param res {@link ApiResponse} de la API
     */
    void done(ApiResponse res);
} 