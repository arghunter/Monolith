package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import GameObjects.Player.Player;

public class PlayerUI implements MouseWheelListener {
	Player player;
	PlayerUIArsenalItem[] arsenalItems=new PlayerUIArsenalItem[16];
	
	private int scrollCount=0;
	public PlayerUI(Player player,JPanel panel)
	{
		panel.addMouseWheelListener(this);
		this.player=player;		
		arsenalItems[0]=new PlayerUIArsenalItem(player.getInventory().getHelemet(), 1184, 989+96);
		arsenalItems[1]=new PlayerUIArsenalItem(player.getInventory().getChestplate(), 1280, 989+96);
		arsenalItems[2]=new PlayerUIArsenalItem(player.getInventory().getHelemet(), 1280, 1085+96);
		arsenalItems[3]=new PlayerUIArsenalItem(player.getInventory().getHelemet(), 1184, 1085+96);
		arsenalItems[4]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[4], 1088, 893+96);
		arsenalItems[5]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[5], 1184, 893+96);
		arsenalItems[6]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[6], 1280, 893+96);
		arsenalItems[7]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[7], 1376, 893+96);
		arsenalItems[8]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[8], 1376, 989+96);
		arsenalItems[9]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[9], 1376, 1181);
		arsenalItems[10]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[10], 1376, 1277);
		arsenalItems[11]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[11], 1280, 1277);
		arsenalItems[12]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[12], 1184, 1277);
		arsenalItems[13]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[13], 1088, 1277);
		arsenalItems[14]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[14], 1088, 1181);
		arsenalItems[15]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[15], 1088, 989+96);
		arsenalItems[player.getInventory().getEquipped()].setSelected(true);
		






		
		
	}
	public void draw(Graphics2D g) 
	{
		Font text=null;
		try {
			text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		g.setFont(text.deriveFont(60f));
		g.setColor(new Color(213, 13, 13));
		g.drawString(""+player.getCurrentHealth(), 100, 100);
		g.fillRect(100, 100,(int)(66*Math.log10(player.getCurrentHealth())) , 5);
		g.setColor(new Color(0, 219, 227));
		g.drawString(""+player.getCurrentShields(), 100+(int)(66*Math.log10(player.getCurrentHealth())), 100);
		g.fillRect(100+(int)(66*Math.log10(player.getCurrentHealth())), 100,(int)(66*Math.log10(player.getCurrentShields())) , 5);
		for(int i=0;i<arsenalItems.length;i++) 
		{
			arsenalItems[i].draw(g);
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		
			scrollCount++;
			int dif=1;

			if(scrollCount>4) 
			{
				if(e.getPreciseWheelRotation()<0) 
				{
					dif=-1;
				}
				arsenalItems[player.getInventory().getEquipped()].setSelected(false);
				int selected=player.getInventory().getEquipped()+dif;
				if(selected>15) 
				{
					player.getInventory().setEquipped(4);
				}else
				if(selected<4) 
				{
					player.getInventory().setEquipped(15);
				}else
				{
					player.getInventory().setEquipped(selected);

				}
				arsenalItems[player.getInventory().getEquipped()].setSelected(true);
				scrollCount=0;
			}

	}

		
	


}
