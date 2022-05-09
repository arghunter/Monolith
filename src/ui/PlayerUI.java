package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import GameObjects.Player.Player;

public class PlayerUI {
	Player player;
	PlayerUIArsenalItem[] arsenalItems=new PlayerUIArsenalItem[16];
	public PlayerUI(Player player)
	{
		this.player=player;
		arsenalItems[0]=new PlayerUIArsenalItem(player.getInventory().getHelemet(), 1248, 1181);
		arsenalItems[1]=new PlayerUIArsenalItem(player.getInventory().getChestplate(), 1280, 1181);
		arsenalItems[2]=new PlayerUIArsenalItem(player.getInventory().getHelemet(), 1280, 1213);
		arsenalItems[3]=new PlayerUIArsenalItem(player.getInventory().getHelemet(), 1248, 1213);
		arsenalItems[4]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[4], 1216, 1149);
		arsenalItems[5]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[5], 1248, 1149);
		arsenalItems[6]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[6], 1280, 1149);
		arsenalItems[7]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[7], 1312, 1149);
		arsenalItems[8]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[8], 1312, 1181);
		arsenalItems[9]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[9], 1312, 1213);
		arsenalItems[10]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[10], 1312, 1245);
		arsenalItems[11]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[11], 1280, 1245);
		arsenalItems[12]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[12], 1248, 1245);
		arsenalItems[13]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[13], 1216, 1245);
		arsenalItems[14]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[14], 1216, 1213);
		arsenalItems[15]=new PlayerUIArsenalItem(player.getInventory().getArsenal()[15], 1216, 1181);






		
		
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
	


}
