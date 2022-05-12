package ui;

import java.awt.Color;
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
import GameObjects.Player.items.consumables.Buff;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.weapons.Weapon;
import general.Constants;

public class ArsenalMenuItem extends RenderableMenuItem {

	private Inventory inventory;
	
	public ArsenalMenuItem(Item item, int x, int y, JPanel panel,Inventory inventory) {
		super(item, x, y, panel);
		super.setShowDescription(false);
		super.getButton().setOutlineColor(Color.WHITE);

	}
	public ArsenalMenuItem(int x, int y, JPanel panel,Inventory inventory) {
		super(x, y, panel);
		super.setShowDescription(false);
		super.getButton().setOutlineColor(Color.WHITE);
		
	}
	@Override
	public void draw(Graphics2D g, int JPanelX,int JPanelY) 
	{
		super.draw(g, JPanelX, JPanelY);
		
		if(super.isHovering()||super.isSelected()) 
		{
			Font text=null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			g.setColor(Constants.TEXTCOLOR);
			if(item!=null) 
			{
				g.setFont(text.deriveFont(60f));
				FontMetrics metrics=g.getFontMetrics();
				g.drawString(item.getName(),2180-metrics.stringWidth(item.getName())/2,200);
				g.setFont(text.deriveFont(35f));
				metrics=g.getFontMetrics();
				g.drawString(""+item.getType(),2180-metrics.stringWidth(""+item.getType())/2,250);
				if(item.getType()==ItemType.HELMET||item.getType()==ItemType.CHESTPLATE||item.getType()==ItemType.LEGGINGS||item.getType()==ItemType.BOOTS) 
				{
					Armor armor=(Armor)item;

					g.drawString(armor.getSet()+"", 2180-metrics.stringWidth(""+armor.getSet())/2, 300);
					g.drawString("+" +armor.getHealth()+" Health",  2180-metrics.stringWidth("+" +armor.getHealth()+" Health")/2, 350);
					g.drawString("+" +armor.getShields()+" Shields",  2180-metrics.stringWidth("+" +armor.getShields()+" Shields")/2, 400);
					g.drawString("+" +armor.getArmor()+" Armor",  2180-metrics.stringWidth("+" +armor.getArmor()+" Armor")/2, 450);
					

				}else if(item.getType()==ItemType.WEAPON)
				{
					Weapon weapon=(Weapon) item;
					g.drawString(weapon.getDamage()+" Damage", 2180-metrics.stringWidth("+" +weapon.getDamage()+" Damage")/2, 300);
					g.drawString(weapon.getAttackSpeed()+" APM", 2180-metrics.stringWidth("+" +weapon.getAttackSpeed()+" APM")/2, 350);
					g.drawString(weapon.getRange()+" Range", 2180-metrics.stringWidth("+" +weapon.getRange()+" Range")/2, 400);
								
				}else if(item.getType()==ItemType.CONSUMABLE) 
				{
					Consumable consumable=(Consumable)item;
					Buff buff =consumable.getBuff();
					g.drawString(buff.getDuration()+" Seconds", 2180-metrics.stringWidth(buff.getDuration()+" Seconds")/2, 300);

					for(int i=0;i<buff.getTypes().length;i++) 
					{
						g.drawString("+"+buff.getBuffs()[i]+" "+buff.getTypes()[i], 2180-metrics.stringWidth("+"+buff.getBuffs()[i]+" "+buff.getTypes()[i])/2, 350+i*50);

					}
				}
			}

		}
		

		
		
	}



}
