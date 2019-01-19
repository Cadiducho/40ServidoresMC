package com.cadiducho.cservidoresmc.bukkit.util;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase con utilidades
 * @author Cadiducho
 */
public class Util {

    private static BukkitPlugin plugin;

    private static final JSONParser jsonParser = new JSONParser();

    public Util(BukkitPlugin instance) {
        plugin = instance;
    }

    /**
     * Leer una URL en un hilo asíncrono
     * @param urlString URL a leer
     * @param callback Callback que se ejecutará en hilo principal
     */
    public static void readUrl(final String urlString, final ApiCallback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            ApiResponse result = new ApiResponse();
            try {
                URL obj = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");
                con.setReadTimeout(plugin.getConfig().getInt("readTimeOut"));

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                try {
                    Object ret = jsonParser.parse(response.toString());
                    result.setResult((JSONObject) ret);
                } catch (ParseException ex) {
                    result.setException(ex);
                }
            } catch (IOException ex) {
                result.setException(ex);
            }

            Bukkit.getScheduler().runTask(plugin, () -> callback.done(result));
        });
    }

}
