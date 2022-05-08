package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import GameObjects.Player.Player;
import general.Constants;
import input.MouseInputParser;
import input.PlayerInputParser;
import render.Tester;

public class MainMenu implements ActionListener {
	Player player;
	Button[] menuButtons = new Button[3];// 0=storageButton, 1=arsenalButton, 2==skillDisplayButton
	Button[] gameModeButtons = new Button[2];// 0=adventureButton 1==survivalButton
	Button settingsMenu;
	InventoryMenu inventoryMenu;
	ArsenalMenu arsenalMenu;
	SkillDisplayMenu skillMenu;
	private boolean hidden = false;
	private PlayerInputParser input;
	public MainMenu(Player player, JPanel panel,PlayerInputParser input) {
		this.player = player;
		this.input=input;
		Point[] menuPoints = { new Point(0, 266), new Point(0, 466), new Point(400, 466), new Point(400, 266) };
		for (int i = 0; i < menuButtons.length; i++) {
			String text = "";
			switch (i) {
			case 0:
				text = "Inventory";
				break;
			case 1:
				text = "Arsenal";
				break;
			case 2:
				text = "Skills";

			}
			menuButtons[i] = new Button(menuPoints, new Color((212) / 2, (175) / 2, (55) / 2, 0), text);
			menuButtons[i].setFontColor(Constants.textColor);
			panel.add(menuButtons[i]);
			menuButtons[i].addActionListener(this);
			for (int j = 0; j < menuPoints.length; j++) {
				menuPoints[j].y += 200;
			}
		}

		Point[] gamePoints = { new Point(2160, 266), new Point(2160, 466), new Point(2560, 466), new Point(2560, 266) };
		for (int i = 0; i < gameModeButtons.length; i++) {
			String text = "";
			switch (i) {
			case 0:
				text = "Adventure";
				break;
			case 1:
				text = "Survival";
			}
			gameModeButtons[i] = new Button(gamePoints, new Color((212) / 2, (175) / 2, (55) / 2, 0), text);
			gameModeButtons[i].setFontColor(Constants.textColor);
			panel.add(gameModeButtons[i]);
			gameModeButtons[i].addActionListener(this);
			System.out.println(text);
			for (int j = 0; j < gamePoints.length; j++) {
				gamePoints[j].y += 200;
			}
		}
		inventoryMenu = new InventoryMenu(player.getInventory(), panel);
		inventoryMenu.setHidden(true);
		arsenalMenu = new ArsenalMenu(player.getInventory(), panel);
		arsenalMenu.setHidden(true);
		skillMenu = new SkillDisplayMenu(player.getSkills(), panel);
		skillMenu.setActive(false);
		player.setCoordsMove(1280, 668);

	}

	public void draw(Graphics2D g, int JPanelX, int JPanelY) {
		if(input.isEscapePressed()) 
		{
			System.out.println("Here");
			inventoryMenu.setHidden(true);
			this.hidden=false;
			arsenalMenu.setHidden(true);
			skillMenu.setActive(false);
		}
		if (!hidden) {
			g.drawImage(createGradient(JPanelX, JPanelY), 0, 0, null);

			Font text = null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			g.setFont(text.deriveFont(240f));
			FontMetrics metrics = g.getFontMetrics();
			g.setColor(Constants.textColor);
			g.drawString("Monolith", (2560 - metrics.stringWidth("Monolith")) / 2, 200);
			for (int i = 0; i < menuButtons.length; i++) {
				menuButtons[i].draw(g, JPanelX, JPanelY);
			}
			for (int i = 0; i < gameModeButtons.length; i++) {
				gameModeButtons[i].draw(g, JPanelX, JPanelY);
			}
			player.render(g);
		}
		inventoryMenu.draw(g, JPanelX, JPanelY);
		arsenalMenu.draw(g, JPanelX, JPanelY);
		skillMenu.render(g, JPanelX, JPanelY);

	}

	private static BufferedImage createGradient(int JPanelX, int JPanelY) {
		int width = (int) Tester.WIDTH;
		int height = (int) Tester.HEIGHT;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();

		Color[] colors = { new Color((212) / 3, (175) / 3, (55) / 3), new Color(212 / 6, 175 / 6, 55 / 6) };
		float[] ratio = { 0.0f, 0.6f };
		Point center = new Point((int) (MouseInputParser.getX() - JPanelX / MouseInputParser.getRatioX()),
				(int) (MouseInputParser.getY() - JPanelY / MouseInputParser.getRatioX()));

		RadialGradientPaint gradient = new RadialGradientPaint(center, 0.25f * width, ratio, colors);
		g.setPaint(gradient);
		g.fillRect(0, 0, width, height);
		g.dispose();

		return img;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!hidden) {
			try {
				Button b = (Button) e.getSource();
				for (int i = 0; i < menuButtons.length; i++) {
					if (b == menuButtons[i]) {
						switch (i) {
						case 0:
							inventoryMenu.setHidden(false);
							this.hidden=true;
							arsenalMenu.setHidden(true);
							skillMenu.setActive(false);
							break;
						case 1:
							inventoryMenu.setHidden(true);
							this.hidden=true;
							arsenalMenu.setHidden(false);
							skillMenu.setActive(false);
							break;
						case 2:
							inventoryMenu.setHidden(true);
							this.hidden=true;
							arsenalMenu.setHidden(true);
							skillMenu.setActive(true);
							break;
						}
					}
				}

			} catch (Exception ex) {

			}
		}

	}



}
