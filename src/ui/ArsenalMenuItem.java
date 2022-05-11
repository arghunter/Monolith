package ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.armor.Armor;
import general.Constants;

public class ArsenalMenuItem extends RenderableMenuItem {

	private Inventory inventory;
	public ArsenalMenuItem(Item item, int x, int y, JPanel panel,Inventory inventory) {
		super(item, x, y, panel);
		super.setShowDescription(false);
		
	}
	public ArsenalMenuItem(int x, int y, JPanel panel,Inventory inventory) {
		super(x, y, panel);
		super.setShowDescription(false);

		
	}
	@Override
	public void draw(Graphics2D g, int JPanelX,int JPanelY) 
	{
		super.draw(g, JPanelX, JPanelY);
		
		if(super.isHovering()&&!super.isSelected()) 
		{
			Font text=null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			g.setColor(Constants.textColor);
			if(item!=null) 
			{
				if(item.getType()==ItemType.HELMET||item.getType()==ItemType.CHESTPLATE||item.getType()==ItemType.LEGGINGS||item.getType()==ItemType.BOOTS) 
				{
					Armor armor=(Armor)item;
					g.setFont(text.deriveFont(60f));
					FontMetrics metrics=g.getFontMetrics();
					g.drawString(item.getName(),2180-metrics.stringWidth(item.getName())/2,200);
					g.setFont(text.deriveFont(35f));
					metrics=g.getFontMetrics();
					g.drawString(""+item.getType(),2180-metrics.stringWidth(""+item.getType())/2,250);
					g.drawString(armor.getSet()+"", 2180-metrics.stringWidth(""+armor.getSet())/2, 300);
				}
			}
		}

		
		
	}



}
