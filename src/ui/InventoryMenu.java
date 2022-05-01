package ui;

import java.util.ArrayList;

import javax.swing.JPanel;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;

public class InventoryMenu {
	Inventory inventory;
	ArrayList<RenderableMenuItem> items;
	public InventoryMenu(Inventory inventory,JPanel panel) 
	{
		
		this.inventory=inventory;
		items= new ArrayList<RenderableMenuItem>();
		for(int i=0;i<inventory.getStorage().size();i++) 
		{
			//items.add(new RenderableMenuItem())
		}
	}

}
