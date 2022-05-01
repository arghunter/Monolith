package ui;

import java.util.ArrayList;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Graphics2D;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;
import GameObjects.Player.items.weapons.MeleeWeapon;

public class InventoryMenu implements MouseWheelListener {
	Inventory inventory;
	ArrayList<RenderableMenuItem> items;
	public InventoryMenu(Inventory inventory,JPanel panel) 
	{
		
		this.inventory=inventory;
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		inventory.addToStorage(new MeleeWeapon("Spider",1,1, 0, 0));
		
		panel.addMouseWheelListener(this);
		items= new ArrayList<RenderableMenuItem>();
		inventory.getStorage().trimToSize();
		for(int i=0;i<inventory.getStorage().size();i++) 
		{
			if(inventory.getStorage().get(i)!=null)
				items.add(new RenderableMenuItem(inventory.getStorage().get(i),256*(i%6)+275,((i/6)+1)*256,panel));
		}
		System.out.println(inventory.getStorage());
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(int i=0;i<items.size();i++) 
		{
			items.get(i).translate(0, 4*(int)(e.getUnitsToScroll()));
		}
	}
	public void draw(Graphics2D g, int JPanelX,int JPanelY) 
	{
		for(int i=0;i<items.size();i++) 
		{
			items.get(i).draw(g, JPanelY, JPanelY);
		}
	}

}
