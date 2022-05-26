//Author: Armaan Gomes
//Date: 5/21/22
//Rev: 01
//Notes displays all rooms the player has explored on the screen. Can draw 496000 images without crashing
package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import general.Constants;
import input.MouseInputParser;
import mapGeneration.MapGenerator;
import render.Main;
import render.TextureGenerator;

public class MapVisual implements MouseWheelListener, MouseMotionListener, MouseListener {
	// Fields
	private MapGenerator map;
	private long initTime;
	private double scale = 1;
	private TextureGenerator[][] textures = new TextureGenerator[Constants.XSIZE][Constants.YSIZE];
	private int lastX = -1;
	private int lastY = -1;
	private int shiftX;
	private int shiftY;
	private boolean hidden = true;

	// Constructor
	public MapVisual(MapGenerator map, long initTime, JPanel panel) {
		this.map = map;

		panel.addMouseMotionListener(this);
		panel.addMouseWheelListener(this);
		panel.addMouseListener(this);
		this.initTime = initTime;
		for (int i = 0; i < textures.length; i++) {
			for (int j = 0; j < textures[0].length; j++) {
				if (map.hasVisited(i, j)) {
					textures[i][j] = new TextureGenerator(map.getRoom(i, j), i * (j) + i + j + initTime,
							0 + TextureGenerator.calcWidth(scale) * i, +TextureGenerator.calcHeight(scale) * j, scale);

				}
			}
		}

	}

	// Draws the map
	public void draw(Graphics2D g) {
		g.setColor(new Color(212 / 6, 175 / 6, 55 / 6));
		g.fillRect(0, 0, (int) Main.WIDTH + 1, (int) Main.HEIGHT + 1);
		for (int i = 0; i < textures.length; i++) {
			for (int j = 0; j < textures[0].length; j++) {
				if (textures[i][j] != null) {
					textures[i][j].draw(g);

				}
			}
		}
	}

	// Sets hidden
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	// Returns hidden
	public boolean getHidden() {
		return hidden;
	}

	@Override
	// Makes the map move in response to mouse drag
	public void mouseDragged(MouseEvent e) {
		for (int i = 0; i < textures.length; i++) {
			for (int j = 0; j < textures[0].length; j++) {
				if (textures[i][j] != null) {
					textures[i][j].translate((int) MouseInputParser.getX() - lastX,
							(int) MouseInputParser.getY() - lastY);

				}
			}
		}
		shiftX += (int) MouseInputParser.getX() - lastX;
		shiftY += (int) MouseInputParser.getY() - lastY;
		lastX = (int) MouseInputParser.getX();
		lastY = (int) MouseInputParser.getY();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	// Scales the map
	public void mouseWheelMoved(MouseWheelEvent e) {
		scale += -1 * 0.01 * e.getScrollAmount() * e.getPreciseWheelRotation();
		if (scale < 0.09) {
			scale = 0.09;
		}
		for (int i = 0; i < textures.length; i++) {
			for (int j = 0; j < textures[0].length; j++) {
				if (map.hasVisited(i, j)) {
					textures[i][j] = new TextureGenerator(map.getRoom(i, j), initTime + (i * j),
							shiftX + TextureGenerator.calcWidth(scale) * i,
							shiftY + TextureGenerator.calcHeight(scale) * j, scale);

				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	// Sets last x and y to begin drag
	public void mousePressed(MouseEvent e) {
		if (lastX == -1 || lastY == -1) {
			lastX = (int) MouseInputParser.getX();
			lastY = (int) MouseInputParser.getY();
		}

	}

	@Override
	// Drops the map into place
	public void mouseReleased(MouseEvent e) {
		lastX = -1;
		lastY = -1;
		for (int i = 0; i < textures.length; i++) {
			for (int j = 0; j < textures[0].length; j++) {
				if (map.hasVisited(i, j)) {
					textures[i][j] = new TextureGenerator(map.getRoom(i, j), initTime + (i * j),
							shiftX + TextureGenerator.calcWidth(scale) * i,
							shiftY + TextureGenerator.calcHeight(scale) * j, scale);

				}
			}
		}

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
