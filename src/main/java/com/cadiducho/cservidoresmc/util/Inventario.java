package com.cadiducho.cservidoresMC.util;

import com.cadiducho.cservidoresmc.impl.IBukkit;
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
    
    public static IBukkit plugin;
    public Inventory[] invOriginal = new Inventory[9999999];
    public Inventory[] invPremio = new Inventory[9999999];
    public Inventory[] invFinal = new Inventory[9999999];
    
    public Inventario(IBukkit instance) {
        plugin = instance;
    }
    
    public Inventory getKit(Player player) {
        invOriginal[player.getEntityId()] = Bukkit.createInventory(player, 36);
        invPremio[player.getEntityId()] = Bukkit.createInventory(player, 36);
        invFinal[player.getEntityId()] = Bukkit.createInventory(player, 36);
        
        invOriginal[player.getEntityId()].setContents(player.getInventory().getContents());
        invFinal = invOriginal;
            
        @SuppressWarnings("unchecked")
	List<String> kitlist = plugin.listaItems;
	Iterator<String> iterator = kitlist.iterator();
	while (iterator.hasNext()) {
            ItemStack is = Metodos.setItemStack(iterator.next());
            invFinal[player.getEntityId()].addItem(is);
        }	
	return invFinal[player.getEntityId()];   
    }

    public void darPremio(Player player) {
	player.getInventory().setContents(getKit(player).getContents());
    }
}
