package com.cadiducho.cservidoresMC.util;

import com.cadiducho.cservidoresMC.Main;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Cadiducho
 */
public class Inventario {
    
    public static Main plugin;
    public Inventory[] premioInv = new Inventory[9999999];
    
    public Inventario(Main instance) {
        plugin = instance;
    }
    
    public Inventory getKit(Player player) {
        premioInv[player.getEntityId()] = Bukkit.createInventory(player, 36);
        
        @SuppressWarnings("unchecked")
	List<String> kitlist = (List<String>) plugin.getConfig().getList("premio");
	Iterator<String> iterator = kitlist.iterator();
	while (iterator.hasNext()) {
            ItemStack is = Metodos.setItemStack(iterator.next());
            premioInv[player.getEntityId()].addItem(is);
        }	
	return premioInv[player.getEntityId()];   
    }

    public void darPremio(Player player) {
	player.getInventory().setContents(getKit(player).getContents());
    }
}
