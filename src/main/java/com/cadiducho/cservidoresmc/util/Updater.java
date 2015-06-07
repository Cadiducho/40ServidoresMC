package com.cadiducho.cservidoresMC.util;

import com.cadiducho.cservidoresmc.cServidoresMC;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
 
/**
 *
 * @author Cadiducho
 */

public class Updater {

    String a, b, c;
    private static String versionInstalada, versionMinecraft;
    public static cServidoresMC plugin;
    private final String readurl = "https://raw.githubusercontent.com/Cadiducho/40ServidoresMC/master/etc/version.v2"; //TODO Mantener ruta actualizada

    public Updater(cServidoresMC instance, String vInstalada, String vMinecraft) {
        plugin = instance;
        versionInstalada = vInstalada;
        versionMinecraft = vMinecraft;
    }


    public String checkearVersion() {
        String a = null;
        try {
            plugin.debugLog("Buscando nueva versión...");
            URL url = new URL(readurl);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String str;
                while ((str = br.readLine()) != null) {
                    String line = str;
                    String[] tokens = line.split(":");
                    if (versionMinecraft.contains(tokens[0])) {
                        
                        //Asignamos todos los valores obtenidos de la web
                        String vActualizada, tipoActualizacion, changelog, urlDescarga;
                        vActualizada = tokens[1];
                        tipoActualizacion = tokens[2];
                        changelog = tokens[3];
                        urlDescarga = tokens[4];
                                   
                        if (versionInstalada.matches(vActualizada)) a = "Versión actualizada";
                        else {
                            a = "Versión desactualizada. Nueva versión: "
                                    + "["+tipoActualizacion+"]"
                                    + vActualizada
                                    + "Changelog: " + changelog
                                    + "Descarga en: " + urlDescarga;
                        }
                    } 
                }
                br.close();
            } catch (IOException ex) {
                a = "Error obteniendo la versión";
                plugin.debugLog("Error obteniendo la versión. Causa: ");
                plugin.debugLog(ex.getMessage());
            }
        } catch (MalformedURLException ex) {
            a = "Error obteniendo la versión";
            plugin.debugLog("Error obteniendo la versión. Causa: ");
            plugin.debugLog(ex.getMessage());
        }
        return a;
    }   
    
}