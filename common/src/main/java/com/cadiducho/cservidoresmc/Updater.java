package com.cadiducho.cservidoresmc;

import com.cadiducho.cservidoresmc.api.CSCommandSender;
import com.cadiducho.cservidoresmc.api.CSConsoleSender;
import com.cadiducho.cservidoresmc.api.CSPlugin;
import com.cadiducho.cservidoresmc.model.updater.UpdaterInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Clase para comprobar las actualizaciones a través de Github
 * @author Cadiducho
 */
public class Updater {

    private static String versionInstalada, versionMinecraft;
    private static CSPlugin plugin;

    public Updater(CSPlugin instance, String vInstalada, String vMinecraft) {
        plugin = instance;
        versionInstalada = vInstalada;
        versionMinecraft = vMinecraft;
    }
    
    private final String ERROR = "Error obteniendo la versión.";
    private final String UPDATED = "Versión actualizada";
    private final String NEW_VERSION = "Versión desactualizada. Nueva versión: %s. Changelog: %s. Descarga en: %s";

    /**
     * Comprobar si hay nueva versión
     * @param sender Jugador al que se avisará
     */
    public void checkearVersion(CSCommandSender sender) {
        if (sender == null) {
            // Se hace al iniciar el plugin
            sender = new CSConsoleSender(plugin);
        }
        plugin.debugLog("Buscando nueva versión...");

        final CSCommandSender finalSender = sender;
        fetchUpdate().thenAccept((UpdaterInfo updaterInfo) -> {
            Optional<Map.Entry<String, String>> recommendedVersion = updaterInfo.getPluginForMinecraft(versionMinecraft);
            if (recommendedVersion.isPresent()) {
                String updaterVersion = recommendedVersion.get().getKey();
                String updateDescription = recommendedVersion.get().getValue();

                // Si existe versión recomendada para esa versión de minecraft, pero no es la que está instalada, avisar
                if (!updaterVersion.equals(versionInstalada)) {
                    String link = String.format("https://github.com/Cadiducho/40ServidoresMC/releases/tag/v%s", updaterVersion);
                    String format = String.format(NEW_VERSION, updaterVersion, updateDescription, link);
                    finalSender.sendMessage(format);
                } else {
                    finalSender.sendMessage(UPDATED);
                }
            }
        }).exceptionally(e -> {
            plugin.log(ERROR);
            plugin.debugLog("Causa: " + e.getMessage());
            return null;
        });
    }

    private CompletableFuture<UpdaterInfo> fetchUpdate() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL("https://github.com/Cadiducho/40ServidoresMC/blob/development/etc/v3.json"); //ToDo: cambiar el branch
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                try (Reader reader = new InputStreamReader(connection.getInputStream())) {
                    return new Gson().fromJson(reader, UpdaterInfo.class);
                }
            } catch (IOException e) {
                throw new IllegalStateException("Cannot execute Updater fetch", e);
            }
        });

    }

}
