//Author: Armaan Gomes 
//Date: 5/12/22
//Rev: 01
// Notes: Renders and draws the player ui
package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import GameObjects.Player.Player;
import render.GameStatus;
import render.Main;

public class PlayerUI implements MouseWheelListener,ActionListener {
	// Fields
	Player player;
	PlayerUIArsenalItem[] arsenalItems = new PlayerUIArsenalItem[16];
	Timer deathTimer;
	// Constructor
	public PlayerUI(Player player, JPanel panel) {
		panel.addMouseWheelListener(this);
		this.player = player;
		arsenalItems[0] = new PlayerUIArsenalItem(player.getInventory().getHelemet(), 1184 + 1070, 989 + 96);
		arsenalItems[1] = new PlayerUIArsenalItem(player.getInventory().getChestplate(), 1280 + 1070, 989 + 96);
		arsenalItems[2] = new PlayerUIArsenalItem(player.getInventory().getLeggings(), 1280 + 1070, 1085 + 96);
		arsenalItems[3] = new PlayerUIArsenalItem(player.getInventory().getBoots(), 1184 + 1070, 1085 + 96);
		arsenalItems[4] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[4], 1088 + 1070, 893 + 96);
		arsenalItems[5] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[5], 1184 + 1070, 893 + 96);
		arsenalItems[6] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[6], 1280 + 1070, 893 + 96);
		arsenalItems[7] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[7], 1376 + 1070, 893 + 96);
		arsenalItems[8] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[8], 1376 + 1070, 989 + 96);
		arsenalItems[9] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[9], 1376 + 1070, 1181);
		arsenalItems[10] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[10], 1376 + 1070, 1277);
		arsenalItems[11] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[11], 1280 + 1070, 1277);
		arsenalItems[12] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[12], 1184 + 1070, 1277);
		arsenalItems[13] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[13], 1088 + 1070, 1277);
		arsenalItems[14] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[14], 1088 + 1070, 1181);
		arsenalItems[15] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[15], 1088 + 1070, 989 + 96);
		arsenalItems[player.getInventory().getEquipped()].setSelected(true);

	}

	// Draws the UI
	public void draw(Graphics2D g) {
		Font text = null;
		try {
			text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		g.setFont(text.deriveFont(60f));
		g.setColor(new Color(213, 13, 13));

		g.drawString("" + Integer.toString(player.getCurrentHealth()), 100, 100);
		g.fillRect(100, 100, Math.max((int) (66 * Math.log10(player.getCurrentHealth())), 0), 5);
		g.setColor(new Color(0, 219, 227));
		g.drawString("" + Integer.toString(player.getCurrentShields()),
				100 + (int) (66 * Math.log10(player.getCurrentHealth())), 100);
		g.fillRect(100 + (int) (66 * Math.log10(player.getCurrentHealth())), 100,
				Math.max((int) (66 * Math.log10(player.getCurrentShields())), 0), 5);
		g.setColor(new Color(100, 100, 100));
		g.fillRect(100, 120, 250, 5);
		g.setColor(new Color(200, 200, 200));
		g.fillRect(100, 120, 250 * player.getCurrentXP() / player.getXpToNextLevel(), 5);
		g.setFont(text.deriveFont(30f));
		g.drawString("Level:" + Integer.toString(player.getLevel()), 100, 150);
		for (int j = 0; j < arsenalItems.length; j++) {
			arsenalItems[j].draw(g);
		}
		if(player.isDead()&&deathTimer==null) 
		{
			deathTimer=new Timer(5000,this);
			deathTimer.start();
			
		}
		g.setColor(new Color(126,254,255,200));
		g.fillOval(100, 1275, 38, 38);
		g.fillRoundRect(100+38/2, 1275+34,200 ,5,10, 2);
		g.setFont(text.deriveFont(20f));
		g.drawString(((int)(((player.getAbilities()[0].getCooldown()+player.getAbilities()[0].getDuration())*1000-(System.currentTimeMillis()-player.getAbilities()[0].getStartTime()))/1000)>0?(int)(((player.getAbilities()[0].getCooldown()+player.getAbilities()[0].getDuration())*1000-(System.currentTimeMillis()-player.getAbilities()[0].getStartTime()))/1000):0)+"    "+((int)(((player.getAbilities()[1].getCooldown()+player.getAbilities()[1].getDuration())*1000-(System.currentTimeMillis()-player.getAbilities()[1].getStartTime()))/1000)>0?(int)(((player.getAbilities()[1].getCooldown()+player.getAbilities()[1].getDuration())*1000-(System.currentTimeMillis()-player.getAbilities()[1].getStartTime()))/1000):0)+"    "+((int)(((player.getAbilities()[2].getCooldown()+player.getAbilities()[2].getDuration())*1000-(System.currentTimeMillis()-player.getAbilities()[2].getStartTime()))/1000)>0?(int)(((player.getAbilities()[2].getCooldown()+player.getAbilities()[2].getDuration())*1000-(System.currentTimeMillis()-player.getAbilities()[2].getStartTime()))/1000):0)+"    "+((int)(((player.getAbilities()[3].getCooldown()+player.getAbilities()[3].getDuration())*1000-(System.currentTimeMillis()-player.getAbilities()[3].getStartTime()))/1000)>0?(int)(((player.getAbilities()[3].getCooldown()+player.getAbilities()[3].getDuration())*1000-(System.currentTimeMillis()-player.getAbilities()[3].getStartTime()))/1000):0)+"    ", 150, 1275+30);

	}

	@Override
	// Selects a different item in the arsenal
	public void mouseWheelMoved(MouseWheelEvent e) {

		int dif = 1;

		if (e.getWheelRotation() != 0) {
			if (e.getPreciseWheelRotation() < 0) {
				dif = -1;
			}
			arsenalItems[player.getInventory().getEquipped()].setSelected(false);
			int selected = player.getInventory().getEquipped() + dif;
			if (selected > 15) {
				player.getInventory().setEquipped(4);
			} else if (selected < 4) {
				player.getInventory().setEquipped(15);
			} else {
				player.getInventory().setEquipped(selected);

			}
			arsenalItems[player.getInventory().getEquipped()].setSelected(true);

		}

	}

	// Updates the menu
	public void update() {

		arsenalItems[0] = new PlayerUIArsenalItem(player.getInventory().getHelemet(), 1184 + 1070, 989 + 96);
		arsenalItems[1] = new PlayerUIArsenalItem(player.getInventory().getChestplate(), 1280 + 1070, 989 + 96);
		arsenalItems[2] = new PlayerUIArsenalItem(player.getInventory().getLeggings(), 1280 + 1070, 1085 + 96);
		arsenalItems[3] = new PlayerUIArsenalItem(player.getInventory().getBoots(), 1184 + 1070, 1085 + 96);
		arsenalItems[4] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[4], 1088 + 1070, 893 + 96);
		arsenalItems[5] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[5], 1184 + 1070, 893 + 96);
		arsenalItems[6] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[6], 1280 + 1070, 893 + 96);
		arsenalItems[7] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[7], 1376 + 1070, 893 + 96);
		arsenalItems[8] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[8], 1376 + 1070, 989 + 96);
		arsenalItems[9] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[9], 1376 + 1070, 1181);
		arsenalItems[10] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[10], 1376 + 1070, 1277);
		arsenalItems[11] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[11], 1280 + 1070, 1277);
		arsenalItems[12] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[12], 1184 + 1070, 1277);
		arsenalItems[13] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[13], 1088 + 1070, 1277);
		arsenalItems[14] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[14], 1088 + 1070, 1181);
		arsenalItems[15] = new PlayerUIArsenalItem(player.getInventory().getArsenal()[15], 1088 + 1070, 989 + 96);
		arsenalItems[player.getInventory().getEquipped()].setSelected(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==deathTimer) 
		{
			Main.setStatus(GameStatus.MAIN_MENU);
			player.revive();
			deathTimer.stop();
			
			deathTimer=null;
		}
		
	}

}
