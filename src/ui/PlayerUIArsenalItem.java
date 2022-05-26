//Author: Armaan Gomes 
//Date: 5/12/22
//Rev: 01
// Notes: Represents an item for te player UI
package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.armor.Armor;
import GameObjects.Player.items.consumables.Buff;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.weapons.Weapon;
import general.Constants;
import general.ImageSystem;
import input.MouseInputParser;
import render.GameStatus;
import render.Main;

public class PlayerUIArsenalItem {
	// Fields
	private Item item;
	private ImageSystem image;
	private int x;
	private int y;
	private boolean selected = false;;

	// Constructor
	public PlayerUIArsenalItem(Item item, int x, int y) {
		this.item = item;

		this.x = x;
		this.y = y;
		BufferedImage img;
		ImageIcon iconImg = null;
		if (item != null) {
			iconImg = (new ImageIcon(
					"imgs/" + item.getName().replace(" ", "") + "/" + item.getName().replace(" ", "") + 0 + ".png"));

			if (iconImg.getIconWidth() == -1) {
				throw new IllegalArgumentException("Image not found " + item.getName());
			}
			img = new BufferedImage(iconImg.getIconWidth(), iconImg.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		} else {
			img = new BufferedImage(96, 96, BufferedImage.TYPE_INT_ARGB);
		}

		Graphics2D g = img.createGraphics();
		g.setColor(new Color(0.2f, 0.2f, 0.2f, 0.2f));
		g.fillRect(0, 0, img.getWidth(), img.getHeight());

		if (item != null) {

			g.drawImage(iconImg.getImage(), 0, 0, null);
		}

		image = new ImageSystem(x + 5, y + 5, img);
		image.move(image.getWidth() / 2, image.getHeight() / 2);
		image.setScale(96.0 / image.getWidth(), 96.0 / image.getHeight());

	}

	// Draws this item
	public void draw(Graphics2D g) {
		if (this.item != null && this.item.getType() == ItemType.CONSUMABLE)

		{
			if (((Consumable) item).getCount() == 0) {
				item = null;
				BufferedImage img = new BufferedImage(96, 96, BufferedImage.TYPE_INT_ARGB);
				Graphics2D gi = img.createGraphics();
				gi.setColor(new Color(0.2f, 0.2f, 0.2f, 0.2f));
				gi.fillRect(0, 0, img.getWidth(), img.getHeight());

				image = new ImageSystem(x + 5, y + 5, img);
				image.move(image.getWidth() / 2, image.getHeight() / 2);
				image.setScale(96.0 / image.getWidth(), 96.0 / image.getHeight());
			} else {
				Font text = null;
				try {
					text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
				} catch (FontFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}

				g.setColor(Constants.TEXTCOLOR);
				g.setFont(text.deriveFont(30f));
				g.drawString("" + ((Consumable) item).getCount(), x + 15, y + 35);
			}

		}
		image.drawImage(g);

		if (selected) {
			g.setStroke(new BasicStroke(10f));
			g.setColor(new Color(0.4f, 0.4f, 0.4f, 0.4f));
			g.drawRect(x + 10, y + 10, 96 - 5, 96 - 5);
			g.setColor(Constants.TEXTCOLOR);
			if (item != null && Main.status == GameStatus.RUNNING) {
				Font text = null;
				try {
					text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
				} catch (FontFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				g.setFont(text.deriveFont(60f));
				FontMetrics metrics = g.getFontMetrics();
				g.drawString(item.getName(), 2280 - metrics.stringWidth(item.getName()) / 2, 200);
				g.setFont(text.deriveFont(35f));
				metrics = g.getFontMetrics();
				g.drawString("" + item.getType(), 2280 - metrics.stringWidth("" + item.getType()) / 2, 250);
				if (item.getType() == ItemType.HELMET || item.getType() == ItemType.CHESTPLATE
						|| item.getType() == ItemType.LEGGINGS || item.getType() == ItemType.BOOTS) {
					Armor armor = (Armor) item;

					g.drawString(armor.getSet() + "", 2180 - metrics.stringWidth("" + armor.getSet()) / 2, 300);
					g.drawString("+" + armor.getHealth() + " Health",
							2280 - metrics.stringWidth("+" + armor.getHealth() + " Health") / 2, 350);
					g.drawString("+" + armor.getShields() + " Shields",
							2280 - metrics.stringWidth("+" + armor.getShields() + " Shields") / 2, 400);
					g.drawString("+" + armor.getArmor() + " Armor",
							2280 - metrics.stringWidth("+" + armor.getArmor() + " Armor") / 2, 450);

				} else if (item.getType() == ItemType.WEAPON) {
					Weapon weapon = (Weapon) item;
					g.drawString(weapon.getDamage() + " Damage",
							2280 - metrics.stringWidth(weapon.getDamage() + " Damage") / 2, 300);
					g.drawString(weapon.getAttackSpeed() + " APM",
							2280 - metrics.stringWidth(weapon.getAttackSpeed() + " APM") / 2, 350);
					g.drawString(weapon.getRange() + " Range",
							2280 - metrics.stringWidth(weapon.getRange() + " Range") / 2, 400);
					g.drawString("" + weapon.getEffect(), 2280 - metrics.stringWidth("" + weapon.getEffect()) / 2, 450);
					g.drawString((weapon.getChance() * 100) + "% Chance",
							2280 - metrics.stringWidth((weapon.getChance() * 100) + "% Chance") / 2, 500);
					g.drawString((weapon.getDuration()) + " Duration",
							2280 - metrics.stringWidth((weapon.getDuration()) + " Duration") / 2, 550);

				} else if (item.getType() == ItemType.CONSUMABLE) {
					Consumable consumable = (Consumable) item;
					Buff buff = consumable.getBuff();
					g.drawString(buff.getDuration() + " Seconds",
							2280 - metrics.stringWidth(buff.getDuration() + " Seconds") / 2, 300);

					for (int i = 0; i < buff.getTypes().length; i++) {
						g.drawString("+" + buff.getBuffs()[i] + " " + buff.getTypes()[i],
								2280 - metrics.stringWidth("+" + buff.getBuffs()[i] + " " + buff.getTypes()[i]) / 2,
								350 + i * 50);

					}
				}
			}
		}
	}

	// Returns the item this ui item represents
	public Item getItem() {
		return item;
	}

	// Sets the item this ui item represents
	public void setItem(Item item) {
		this.item = item;
	}

	// Returns the image of the item
	public ImageSystem getImage() {
		return image;
	}

	// Sets the image
	public void setImage(ImageSystem image) {
		this.image = image;
	}

	// Returns the x location of this item
	public int getX() {
		return x;
	}

	// Sets the x location of this item
	public void setX(int x) {
		this.x = x;
	}

	// Returns the y location of this item
	public int getY() {
		return y;
	}

	// Sets the y of this item 
	public void setY(int y) {
		this.y = y;
	}

	// returns the isSelected boolean
	public boolean isSelected() {
		return selected;
	}

	// Sets the selected boolean
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
