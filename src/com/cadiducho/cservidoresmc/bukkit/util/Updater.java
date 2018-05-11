package com.cadiducho.cservidoresmc.bukkit.util;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;
import java.util.Optional;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
/**
 * Clase para comprobar las actualizaciones a través de Github
 * @author Cadiducho
 */
public class Updater {

    private static String versionInstalada, versionMinecraft;
    public static BukkitPlugin plugin;
    private final String readurl = "https://raw.githubusercontent.com/Cadiducho/40ServidoresMC/2.0/etc/v2.json"; //TODO Mantener ruta actualizada
    private static final JSONParser jsonParser = new JSONParser();

    public Updater(BukkitPlugin instance, String vInstalada, String vMinecraft) {
        plugin = instance;
        versionInstalada = vInstalada;
        versionMinecraft = vMinecraft;
    }
    
    private final String ERROR = "Error obteniendo la versión.";
    private final String DISABLED = "El Updater ha sido remotamente desactivado debido a un mantenimiento";
    private final String UPDATED = "Versión actualizada";
    private final String NOVERSION = "No se ha encontrado plugin para la versión de servidor que estas ejecutando";
    private final String NEWVERSION = "Versión desactualizada. Nueva versión: %s. Changelog: %s. Descarga en: %s";

    /**
     * Comprobar si hay nueva versión
     * @param send Opcional. Jugador al que se avisará
     * @param boot Si es al iniciar o no. Avisará de que está actualizado depende de eso
     */
    public void checkearVersion(CommandSender send, boolean boot) {
        plugin.debugLog("Buscando nueva versión...");
        Optional<CommandSender> sender = Optional.ofNullable(send);
        Util.readUrl(readurl, (ApiResponse response) -> {
            if (response.getException().isPresent()) {
                plugin.log(ERROR);
                sender.ifPresent(s -> plugin.sendMessage(ERROR, s));
                plugin.debugLog("Causa: " + response.getException().get().getMessage());
            }
            
            JSONObject jsonData = (JSONObject) response.getResult();
            boolean online = (boolean) jsonData.get("online");
            if (!online) {
                plugin.log(DISABLED);
                sender.ifPresent(s -> plugin.sendMessage(DISABLED, s));
            } else {
                if (jsonData.containsKey(versionMinecraft)) {
                    JSONObject array = (JSONObject) jsonData.get(versionMinecraft);
                    String ultimaVersion = "" + array.get("lastVersion");
                    if (versionInstalada.matches(ultimaVersion)) {
                        if (boot) plugin.log(UPDATED);
                        sender.ifPresent(s -> plugin.sendMessage(UPDATED, s));
                    } else {
                        String urlDescarga = (String) array.get("lastDownload");
                        String changelog = (String) array.get("cambiosBreves");
                        String str = String.format(NEWVERSION, ultimaVersion, changelog, urlDescarga);
                        plugin.log(str);
                        sender.ifPresent(s -> plugin.sendMessage(NEWVERSION, s));
                    }
                } else {
                    plugin.log(NOVERSION);
                    sender.ifPresent(s -> plugin.sendMessage(NOVERSION, s));
                }
            }
        });
    }

}
