//Author: Armaan Gomes
//Date: 5/23/22
//Rev:01
//Notes: Help text menu
package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JPanel;

import general.Constants;
import input.MouseInputParser;
import render.Main;

public class ReadMeMenu implements MouseWheelListener {
	// Fields
	private int shiftY = 275;
	private boolean hidden = true;
	private String text = "In a world overrun with monsters, the elite soar in their spacecrafts high above while the poor scramble at the bases of monoliths of protection, the last remaining sanctuaries of humanity. You are a scavenger from one of these havens. Your refuge is failing, the monolith crumbling. You must embark on a quest to find another monolith, and restore your sanctuary. Perhaps then, with the combined force of civilizations, the tide can change and humankind can once again reclaim their world. Explore the dungeon. Get powerful items. Learn new skills. Kill the monsters. Find the monolith. This is your quest. WASD for movement, right click for using your equipped item. Scroll to scroll through your arsenal. Go to the Arsenal Menu to swap items from your inventory to your arsenal and vica versa. Go to the inventory menu to view your inventory and construct blueprints. Go to the Skills menu to view your skills and activate or disable them. When you level up make sure to press Enter to gain a new skill. Kepp an eye on your Health(Red) and Shields(Blue) in the top left corner. Additionally keep an eye on your ability cooldowns in the bottom left corner. Use your abilities with the keys 1 2 3 and 4. Note that Ability 1 is unlocked at Level 0, Ability 2 is unlocked at Level 25, Ability 3 at 50, and Ability 4 at 75. Also you can go pause in Adventure through the escape button. Made by: Armaan Gomes, Peter Ferolito, and Adithya Giri. Additional credits to Makai Symphony for providing the sound track and various artists for providing textures. Finally, have fun!";
	private int margins = 275;

	// Constructor
	public ReadMeMenu(JPanel panel) {
		panel.addMouseWheelListener(this);
	}

	// Draws this menu
	public void draw(Graphics2D g, int JPanelX, int JPanelY) {
		if (!hidden) {
			g.drawImage(createGradient(JPanelX, JPanelY), 0, 0, null);

			Font font = null;
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			g.setFont(font.deriveFont(40f));
			g.setColor(Constants.TEXTCOLOR);

			FontMetrics metrics = g.getFontMetrics();
			String[] words = text.split(" ");
			String line = "";
			int lineNum = 1;
			for (String word : words) {
				if (margins + metrics.stringWidth(line + " " + word) <= Main.WIDTH - margins) {
					line += " " + word;

				} else {
					g.drawString(line, margins, shiftY + 60 * lineNum);
					line = word;
					lineNum++;
				}
			}
			g.drawString(line, margins, shiftY + 60 * lineNum);
			g.setColor(new Color(212 / 6, 175 / 6, 55 / 6));
			g.fillRect(0, 0, 2560, 266);
			g.setColor(Constants.TEXTCOLOR);
			g.setFont(font.deriveFont(120f));
			g.drawString("Monolith", 275, 175);

		}

	}

	// Gets hidden
	public boolean isHidden() {
		return hidden;
	}

	// Sets hidden
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	// Draws a gradient with bounds centered around the player mouse
	private static BufferedImage createGradient(int JPanelX, int JPanelY) {
		int width = (int) Main.WIDTH;
		int height = (int) Main.HEIGHT;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();

		Color[] colors = { new Color((212) / 3, (175) / 3, (55) / 3), new Color(212 / 6, 175 / 6, 55 / 6) };
		float[] ratio = { 0.0f, 0.6f };
		Point center = new Point((int) (MouseInputParser.getX() - JPanelX / MouseInputParser.getRatioX()),
				(int) (MouseInputParser.getY() - JPanelY / MouseInputParser.getRatioX()));
		if (MouseInputParser.getX() < 275 - JPanelX / MouseInputParser.getRatioX()
				|| MouseInputParser.getY() - JPanelY / MouseInputParser.getRatioX() < 266) {
			center = new Point(-200, -200);
		}
		RadialGradientPaint gradient = new RadialGradientPaint(center, 0.25f * width, ratio, colors);
		g.setPaint(gradient);
		g.fillRect(0, 0, width, height);
		g.dispose();

		return img;
	}

	@Override
	// Scrolls through the text
	public void mouseWheelMoved(MouseWheelEvent e) {
		shiftY -= 40 * e.getPreciseWheelRotation();

	}

}
