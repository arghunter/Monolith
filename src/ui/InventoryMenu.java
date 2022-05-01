package ui;

import java.util.ArrayList;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;

public class InventoryMenu implements MouseWheelListener {
	Inventory inventory;
	ArrayList<RenderableMenuItem> items;
	public InventoryMenu(Inventory inventory,JPanel panel) 
	{
		
		this.inventory=inventory;
		items= new ArrayList<RenderableMenuItem>();
		for(int i=0;i<inventory.getStorage().size();i++) 
		{
			items.add(new RenderableMenuItem(inventory.getStorage().get(i),256*(i%6)+275,((i/6)+1)*256,panel));
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(int i=0;i<items.size();i++) 
		{
			items.get(i).translate(0, (int)(e.getPreciseWheelRotation()*e.getScrollAmount()));
		}
		
	}

}
