package com.cadiducho.cservidoresmc.bukkit.util;

import com.cadiducho.cservidoresmc.bukkit.BukkitPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Cadiducho
 */
public class Util {
    
    private static BukkitPlugin plugin;

    public Util(BukkitPlugin instance) {
        plugin = instance;
    }

    
    public int getVoto(Player p, String clave) {
        try {
            String web = "http://www.40servidoresmc.es/api.php?nombre=" + p.getName() + "&clave=" + clave;
            URL url = new URL(web);
            InputStream in = url.openStream();
            Scanner scan = new Scanner(in);
            if (scan.hasNext()) {
                String n = scan.next();
                plugin.debugLog("Voto de " + p.getName() + " obtenido correctamente");
                return Integer.parseInt(n);
            }
            plugin.debugLog("Ha ocurrido un error desconocido obteniendo el voto de " + p.getName());
            return -1;
        } catch (MalformedURLException ex) {
            plugin.log("Ha ocurrido un error al obtener la URL de los votos");
            plugin.debugLog("Causa: MalformedURLException");
            return -1;
        } catch (IOException iex) {
            plugin.log("Ha ocurrido un error obteniendo la URL de los votos (IOExeception)");
            plugin.debugLog("Causa: " + iex.getMessage());
        }
        return -1;
    }
    
    
    public String colorizar(String s) { //TODO: ¿Compatible con Sponge?
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
    
    public static String readUrl(String urlString) throws IOException {
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
        
        return response.toString();
    }

}
