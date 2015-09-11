package com.cadiducho.cservidoresmc.bukkit.util;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
/**
 *
 * @author Cadiducho
 */

public class Updater {

    String a, b, c;
    private static String versionInstalada, versionMinecraft;
    public static BukkitPlugin plugin;
    private final String readurl = "https://raw.githubusercontent.com/Cadiducho/40ServidoresMC/2.0/etc/v2.json"; //TODO Mantener ruta actualizada
    private static final JSONParser jsonParser = new JSONParser();

    public Updater(BukkitPlugin instance, String vInstalada, String vMinecraft) {
        plugin = instance;
        versionInstalada = vInstalada;
        versionMinecraft = vMinecraft;
    }


    public String checkearVersion() {
        String a = null;
        try {
            plugin.debugLog("Buscando nueva versión...");
            String url = Util.readUrl(readurl);
            Object parsedData = jsonParser.parse(url);
            
            if (parsedData instanceof JSONObject) {
                JSONObject jsonData = (JSONObject) parsedData;
                boolean online = (boolean) jsonData.get("online");
                if (!online) {
                    a = "El Updater ha sido remotamente desactivado debido a un mantenimiento";
                } else {
                    if (jsonData.containsKey(versionMinecraft)) {
                        JSONObject array = (JSONObject) jsonData.get(versionMinecraft);
                        String ultimaVersion = (String) array.get("lastVersion");
                        if (versionInstalada.matches(ultimaVersion)) {
                            a = "Versión actualizada";
                        } else {
                            String urlDescarga = (String) array.get("lastDownload");
                            String changelog = (String) array.get("cambiosBreves");
                            a = "Versión desactualizada. Nueva versión: "
                                    + ultimaVersion
                                    + "Changelog: " + changelog
                                    + "Descarga en: " + urlDescarga;
                        }    
                    } else {
                        a = "No se ha encontrado plugin para la versión de servidor que estas ejecutando";
                    }
                }
            }
        } catch (Exception ex) {
            a = "Error obteniendo la versión";
            plugin.debugLog("Error obteniendo la versión. Causa: ");
            plugin.debugLog(ex.getMessage());
        }
        return a;
    }   
    
}