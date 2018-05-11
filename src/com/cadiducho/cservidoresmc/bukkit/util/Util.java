package com.cadiducho.cservidoresmc.bukkit.util;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Cadiducho
 */
public class Util {

    private static BukkitPlugin plugin;

    private static final JSONParser jsonParser = new JSONParser();

    public Util(BukkitPlugin instance) {
        plugin = instance;
    }

    public String colorizar(String s) {
        return s.
                replace("&0", ChatColor.BLACK + "").
                replace("&1", ChatColor.DARK_BLUE + "").
                replace("&2", ChatColor.DARK_GREEN + "").
                replace("&3", ChatColor.DARK_AQUA + "").
                replace("&4", ChatColor.DARK_RED + "").
                replace("&5", ChatColor.DARK_PURPLE + "").
                replace("&6", ChatColor.GOLD + "").
                replace("&7", ChatColor.GRAY + "").
                replace("&8", ChatColor.DARK_GRAY + "").
                replace("&9", ChatColor.BLUE + "").
                replace("&a", ChatColor.GREEN + "").
                replace("&b", ChatColor.AQUA + "").
                replace("&c", ChatColor.RED + "").
                replace("&d", ChatColor.LIGHT_PURPLE + "").
                replace("&e", ChatColor.YELLOW + "").
                replace("&f", ChatColor.WHITE + "").
                replace("&k", ChatColor.MAGIC + "").
                replace("&l", ChatColor.BOLD + "").
                replace("&m", ChatColor.STRIKETHROUGH + "").
                replace("&n", ChatColor.UNDERLINE + "").
                replace("&o", ChatColor.ITALIC + "").
                replace("&r", ChatColor.RESET + "");
    }

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

            Bukkit.getScheduler().runTask(plugin, () -> {
                callback.done(result);
            });
        });
    }

}
