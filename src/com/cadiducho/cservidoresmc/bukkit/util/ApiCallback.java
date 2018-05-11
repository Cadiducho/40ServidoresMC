package com.cadiducho.cservidoresmc.bukkit.util;

/**
 *
 * @author Cadiducho
 */
@FunctionalInterface
public interface ApiCallback {
    public void done(ApiResponse res);
} 