package com.cadiducho.cservidoresmc.bukkit;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceholderHook extends PlaceholderExpansion {

    private final BukkitPlugin bukkitPlugin;

    public PlaceholderHook(BukkitPlugin bukkitPlugin) {
        this.bukkitPlugin = bukkitPlugin;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return "Cadiducho";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "40servidoresmc";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
/*
        // %example_placeholder1%
        if (identifier.equals("placeholder1")){
            return "placeholder1 works";
        }

        // %example_placeholder2%
        if (identifier.equals("placeholder2")){
            return "placeholder2 works";
        }
*/
        return null;
    }
}
