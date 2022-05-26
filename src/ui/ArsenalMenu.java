//Author: Armaan Gomes
//Date: 5/12/22
//Rev: 01
// Notes: Represents an arsenal menu
package ui;

import java.awt.Color;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.armor.Armor;
import general.Constants;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import GameObjects.Player.ArsenalFullException;
import GameObjects.Player.Inventory;
import input.MouseInputParser;
import render.Main;

public class ArsenalMenu implements MouseWheelListener, ActionListener, MouseMotionListener, MouseListener {

	// Fields
	private Inventory inventory;
	private ArsenalMenuItem[] arsenalItems = new ArsenalMenuItem[16];
	private boolean hidden = false;
	private ArrayList<RenderableMenuItem> items;
	private RenderableMenuItem selectedItem;
	private RenderableMenuItem dragItem;
	private JPanel panel;
	private int mouseP1X;
	private int mouseP1Y;

	// Constructor
	public ArsenalMenu(Inventory inventory, JPanel panel) {
		panel.addMouseWheelListener(this);
		this.panel = panel;
		panel.addMouseMotionListener(this);
		panel.addMouseListener(this);
		int count = 0;
		this.inventory = inventory;

		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 541, 532, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(541, 532, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 807, 532, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(807, 532, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 807, 798, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(807, 798, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 541, 798, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(541, 798, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 275, 266, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(275, 266, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 541, 266, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(541, 266, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 807, 266, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(807, 266, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 1073, 266, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(1073, 266, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 1073, 532, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(1073, 532, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 1073, 798, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(1073, 798, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 1073, 1064, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(1073, 1064, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 807, 1064, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(807, 1064, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 541, 1064, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(541, 1064, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 275, 1064, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(275, 1064, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 275, 798, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(275, 798, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 275, 532, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(275, 532, panel);

		}
		for (int i = 0; i < arsenalItems.length; i++) {
			arsenalItems[i].addActionListener(this);
		}
		items = new ArrayList<RenderableMenuItem>();
		for (int i = 0; i < inventory.getStorage().size(); i++) {
			if (inventory.getStorage().get(i) != null && (inventory.getStorage().get(i).getType() == ItemType.WEAPON
					|| (inventory.getStorage().get(i).getType() == ItemType.CHESTPLATE)
					|| (inventory.getStorage().get(i).getType() == ItemType.BOOTS)
					|| (inventory.getStorage().get(i).getType() == ItemType.LEGGINGS
							|| (inventory.getStorage().get(i).getType() == ItemType.HELMET
									|| (inventory.getStorage().get(i).getType() == ItemType.CONSUMABLE))))) {

				items.add(new RenderableMenuItem(inventory.getStorage().get(i), 1400 + (i % 2 * 266),
						(((i / 2) + 1) * 266), panel));
				System.out.println(inventory.getStorage().get(i));
				items.get(items.size() - 1).addActionListener(this);
			}

		}

	}

	// Retursn if this menu is hidden
	public boolean isHidden() {
		return hidden;
	}

	// Sets the is hidden boolean
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	// Draws this arsenal menu
	public void draw(Graphics2D g, int JPanelX, int JPanelY) {
		if (!hidden) {
			g.drawImage(createGradient(), 0, 0, null);
			Font text = null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			g.setColor(Constants.TEXTCOLOR);
			g.setFont(text.deriveFont(120f));
			g.drawString("Arsenal", 275, 175);

			for (int i = 0; i < arsenalItems.length; i++) {

				if (arsenalItems[i] != null)
					arsenalItems[i].draw(g, JPanelX, JPanelY);
			}

			boolean tripped = false;
			for (int i = 0; i < items.size(); i++) {
				if (selectedItem != null) {
					if (items.get(i).isHovering()) {
						tripped = true;
						selectedItem.setIsSelected(false);
					}
				}

				items.get(i).draw(g, JPanelX, JPanelY);
			}
			for (int i = 0; i < arsenalItems.length; i++) {
				if (selectedItem != null) {
					if (arsenalItems[i].isHovering() && !(arsenalItems[i].getItem() == null)) {
						tripped = true;
						selectedItem.setIsSelected(false);
					}
				}
			}
			if (!tripped && selectedItem != null) {
				selectedItem.setIsSelected(true);
			}
		}

	}

	// Creates a nice looking gradient centered around the mouse
	private static BufferedImage createGradient() {
		int width = (int) Main.WIDTH;
		int height = (int) Main.HEIGHT;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();

		Color[] colors = { new Color((212) / 3, (175) / 3, (55) / 3), new Color(212 / 6, 175 / 6, 55 / 6) };
		float[] ratio = { 0.0f, 0.6f };
		Point center = new Point((int) MouseInputParser.getX(), (int) MouseInputParser.getY());
		RadialGradientPaint gradient = new RadialGradientPaint(center, 0.25f * width, ratio, colors);
		g.setPaint(gradient);
		g.fillRect(0, 0, width, height);
		g.dispose();

		return img;
	}

	// Updates this menu
	public void update() {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).dispose();
		}

		int count = 0;
		for (int i = 0; i < arsenalItems.length; i++) {
			arsenalItems[i].dispose();
		}
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 541, 532, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(541, 532, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 807, 532, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(807, 532, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 807, 798, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(807, 798, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 541, 798, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(541, 798, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 275, 266, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(275, 266, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 541, 266, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(541, 266, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 807, 266, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(807, 266, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 1073, 266, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(1073, 266, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 1073, 532, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(1073, 532, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 1073, 798, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(1073, 798, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 1073, 1064, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(1073, 1064, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 807, 1064, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(807, 1064, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 541, 1064, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(541, 1064, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 275, 1064, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(275, 1064, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 275, 798, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(275, 798, panel);

		}
		count++;
		if (inventory.getArsenal()[count] != null) {
			arsenalItems[count] = new ArsenalMenuItem(inventory.getArsenal()[count], 275, 532, panel);
		} else {
			arsenalItems[count] = new ArsenalMenuItem(275, 532, panel);

		}
		for (int i = 0; i < arsenalItems.length; i++) {
			arsenalItems[i].addActionListener(this);
		}
		items = new ArrayList<RenderableMenuItem>();
		int shift = 0;
		for (int i = 0; i < inventory.getStorage().size(); i++) {
			if (inventory.getStorage().get(i) != null && (inventory.getStorage().get(i).getType() == ItemType.WEAPON
					|| (inventory.getStorage().get(i).getType() == ItemType.CHESTPLATE)
					|| (inventory.getStorage().get(i).getType() == ItemType.BOOTS)
					|| (inventory.getStorage().get(i).getType() == ItemType.LEGGINGS
							|| (inventory.getStorage().get(i).getType() == ItemType.HELMET
									|| (inventory.getStorage().get(i).getType() == ItemType.CONSUMABLE))))) {

				items.add(new RenderableMenuItem(inventory.getStorage().get(i), 1400 + ((i + shift) % 2 * 266),
						((((i + shift) / 2) + 1) * 266), panel));
				items.get(items.size() - 1).addActionListener(this);
			} else {
				shift--;
			}

		}

	}

	@Override
	// Checks if the mouse Wheel has been moved
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (!hidden) {
			for (int i = 0; i < items.size(); i++) {
				items.get(i).translate(0, 100 * (int) (e.getWheelRotation()));
			}
		}

	}

	@Override
	// Selected the clicked item
	public void actionPerformed(ActionEvent e) {
		if (!hidden) {

			if (e.getActionCommand().equals("ItemClicked") && !items.contains(e.getSource())) {
				if (selectedItem != null) {
					selectedItem.setIsSelected(false);
				}
				selectedItem = (RenderableMenuItem) e.getSource();
				selectedItem.setIsSelected(true);

			}

		}

	}

	@Override
	// Translation for drag and drop
	public void mouseDragged(MouseEvent e) {
		if (dragItem != null) {
			dragItem.translate((int) (MouseInputParser.getX() - mouseP1X), (int) (MouseInputParser.getY() - mouseP1Y));
			mouseP1X = (int) MouseInputParser.getX();
			mouseP1Y = (int) MouseInputParser.getY();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	// Gets the selected item and preps for translation
	public void mousePressed(MouseEvent e) {
		mouseP1X = (int) MouseInputParser.getX();
		mouseP1Y = (int) MouseInputParser.getY();
		for (int i = 0; i < arsenalItems.length; i++) {
			if (arsenalItems[i].isHovering()) {
				dragItem = arsenalItems[i];
				return;
			}
		}
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).isHovering()) {
				dragItem = items.get(i);
				return;
			}
		}
	}

	@Override
	// Drops the selected item in its respective position and updates the menu
	public void mouseReleased(MouseEvent e) {
		if (dragItem != null) {
			if (dragItem.getClass() == ArsenalMenuItem.class && dragItem.getItem() != null) {
				if (dragItem.getX() > 1300) {
					inventory.removeFromArsenal(dragItem.getItem());
					inventory.addToStorage(dragItem.getItem());
					for (int i = 0; i < items.size(); i++) {
						if (items.get(i).isHovering()) {
							try {
								inventory.addToArsenal(items.get(i).getItem());
							} catch (ArsenalFullException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					update();

					dragItem = null;
				} else {
					if (dragItem.getItem() != null) {
						int pos = -1;
						for (int i = 0; i < arsenalItems.length; i++) {
							if (dragItem == arsenalItems[i]) {
								pos = i;
								break;
							}
						}
						for (int i = 0; i < arsenalItems.length; i++) {
							if (arsenalItems[i].isHovering()) {
								if (arsenalItems[i].getItem() == null) {
									inventory.removeFromArsenal(dragItem.getItem());

									inventory.addToArsenal(dragItem.getItem(), i);
								} else {
									inventory.addToArsenal(arsenalItems[i].getItem(), pos);
									inventory.addToArsenal(dragItem.getItem(), i);

								}
								break;
							}
						}
					}

				}
			} else if (dragItem.getClass() != ArsenalMenuItem.class) {
				for (int i = 0; i < arsenalItems.length; i++) {
					if (arsenalItems[i].isHovering()) {
						if (i < 4) {
							if (dragItem.getItem().getClass() == Armor.class) {
								inventory.addToArsenal((Armor) (dragItem.getItem()));
							}
							break;

						}
						if (arsenalItems[i].getItem() == null) {
							inventory.addToArsenal(dragItem.getItem(), i);
						} else {
							inventory.addToStorage(arsenalItems[i].getItem());
							inventory.addToArsenal(dragItem.getItem(), i);

						}
					}
				}
			}
		}
		mouseP1X = -1;
		mouseP1Y = -1;
		dragItem = null;
		update();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
