package com.cadiducho.cservidoresMC.util;

import com.cadiducho.cservidoresMC.Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
 
/**
 *
 * @author Cadiducho
 */

public class Version {

    private String versionRecomendada;
    private static String versionInstalada;
    public static Main plugin;
    private final String readurl = "https://raw.githubusercontent.com/Cadiducho/40ServidoresMC/master/etc/version.txt";

    public Version(Main instance, String version) {
        plugin = instance;
        versionInstalada = version;
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
                    String[] versionMC = plugin.getServer().getVersion().split(":");
                    if (versionMC[1].contains(tokens[0])) {
                        versionRecomendada = tokens[1];
                                   
                        if (versionInstalada.matches(tokens[1])) {
                            a = "Version actualizada";
                        } else {
                            a = "Version desactualizada. Nueva version recomendada: {0} Changelog: {1}".replace("{0}", versionRecomendada).replace("{1}", tokens[2]);
                        }
                    } 
                }
                br.close();
            } catch (IOException ex) {
                a = plugin.getTag()+"Error obteniendo la versión";
                plugin.debugLog("Error obteniendo la versión. Causa: ");
                plugin.debugLog(ex.getMessage());
            }
        } catch (MalformedURLException ex) {
            a = plugin.getTag()+"Error obteniendo la versión";
            plugin.debugLog("Error obteniendo la versión. Causa: ");
            plugin.debugLog(ex.getMessage());
        }
        return a;
    }   
    
}