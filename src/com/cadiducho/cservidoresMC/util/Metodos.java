package com.cadiducho.cservidoresMC.util;

import com.cadiducho.cservidoresMC.Main;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Cadiducho
 */
public class Metodos {
    
    public static Main plugin;
    
    public Metodos(Main instance) {
        plugin = instance;
    }
    @SuppressWarnings("resource")
	public int getVoto(Player p, String clave) {
    	try
        {
          String web = "http://www.40servidoresmc.es/api.php?nombre=" + p.getName() + "&clave=" + clave;
          URL url = new URL(web);
          InputStream in = url.openStream();
          Scanner scan = new Scanner(in);
          if (scan.hasNext())
          {
            String n = scan.next();
            plugin.debugLog("Voto de " + p.getName() + " obtenido correctamente");
            return Integer.parseInt(n);
          }
          plugin.debugLog("Ha ocurrido un error desconocido obteniendo el voto de " + p.getName());
          return -1;
        }
        catch (MalformedURLException ex)
        {
          Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
          plugin.log("Ha ocurrido un error al obtener la URL de los votos");
          plugin.debugLog("Causa: MalformedURLException");
          return -1;
        }
        catch (IOException iex)
        {
          Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, iex);
          plugin.log("Ha ocurrido un error obteniendo la URL de los votos (IO)");
          plugin.debugLog("Causa: " + iex.getMessage());
        }
        return -1;
    }
	
    @SuppressWarnings("deprecation")
	public static ItemStack setItemStack(String string) {
	ItemStack is;
	try {
            String values[] = string.split(",");
            if (values.length > 2) {
                if (values.length > 8) {
                    is = new ItemStack(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Short.parseShort(values[2]));
                    is = addEnchantment(is, values[3], values[4]);
                    is = addEnchantment(is, values[5], values[6]);
                    is = addEnchantment(is, values[7], values[8]);
		} else if (values.length > 6) {
                    is = new ItemStack(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Short.parseShort(values[2]));
                    is = addEnchantment(is, values[3], values[4]);
                    is = addEnchantment(is, values[5], values[6]);
		} else if (values.length > 4) {
                    is = new ItemStack(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Short.parseShort(values[2]));
                    is = addEnchantment(is, values[3], values[4]);
		} else {
                    is = new ItemStack(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Short.parseShort(values[2]));
		}
            } else {
		is = new ItemStack(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
            }
	} catch (NumberFormatException e) {
            is = new ItemStack(0, 1);
            System.out.println("No se ha podido parsear un ItemStack! con.cadiducho.cservidoresMC.util.Metodos.setItemStack(String string)");
	}
        return is;
    }

    public static ItemStack addEnchantment(ItemStack is, String name, String level) {
	switch (name.toLowerCase()) {
            case "power":
		is.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, Integer.parseInt(level)); break;
            case "flame":
            	is.addUnsafeEnchantment(Enchantment.ARROW_FIRE, Integer.parseInt(level)); break;
            case "infinity":
            	is.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, Integer.parseInt(level)); break;
            case "punch":
            	is.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, Integer.parseInt(level)); break;
            case "sharpness":
            	is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, Integer.parseInt(level)); break;
            case "arthropods":
            	is.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, Integer.parseInt(level)); break;
            case "smite":
            	is.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, Integer.parseInt(level)); break;
            case "efficiency":
            	is.addUnsafeEnchantment(Enchantment.DIG_SPEED, Integer.parseInt(level)); break;
            case "unbreaking":
            	is.addUnsafeEnchantment(Enchantment.DURABILITY, Integer.parseInt(level)); break;
            case "fireaspect":
            	is.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, Integer.parseInt(level)); break;
            case "knockback":
            	is.addUnsafeEnchantment(Enchantment.KNOCKBACK, Integer.parseInt(level)); break;
            case "fortune":
            	is.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, Integer.parseInt(level)); break;
            case "looting":
            	is.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, Integer.parseInt(level)); break;
            case "respiration":
            	is.addUnsafeEnchantment(Enchantment.OXYGEN, Integer.parseInt(level)); break;
            case "protection":
            	is.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Integer.parseInt(level)); break;
            case "blastresistance":
            	is.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, Integer.parseInt(level)); break;
            case "featherfalling":
            	is.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, Integer.parseInt(level)); break;
            case "fireprotection":
            	is.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, Integer.parseInt(level)); break;
            case "projectileprotection":
            	is.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, Integer.parseInt(level)); break;
            case "silktouch":
            	is.addUnsafeEnchantment(Enchantment.SILK_TOUCH, Integer.parseInt(level)); break;
            case "thorns":
            	is.addUnsafeEnchantment(Enchantment.THORNS, Integer.parseInt(level)); break;
            case "aquaaffinity":
            	is.addUnsafeEnchantment(Enchantment.WATER_WORKER, Integer.parseInt(level)); break;
            }
	return is;
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
    
}
