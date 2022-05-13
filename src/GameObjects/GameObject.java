//Author: Armaan Gomes  
//Date: 5/12/22
//Rev: 01
//Notes: A basic game object in the game

package GameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GameObject {
	// Fields

	private int width;
	private int height;

	// Constructor
	public GameObject(int width, int height) {

		this.width = width;
		this.height = height;

	}

	// Returns width
	public int getWidth() {
		return width;
	}

	// Returns height
	public int getHeight() {
		return height;
	}
	//Abstract render
	public abstract void render(Graphics2D g);

}
