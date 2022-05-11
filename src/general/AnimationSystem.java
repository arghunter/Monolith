//Author: Armaan Gomes
//Date: 5/9/22
//Rev: 01
//Notes: A system that animates a series of images
package general;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class AnimationSystem {
	// Fields
	private long delay;
	private long lastFrame = System.currentTimeMillis();
	private int frameNumber;
	private int numFrames;
	private ImageSystem[] pics;

	// Constructor
	public AnimationSystem(int x, int y, long delay, String name, int numFrames) {
		this.delay = delay;
		this.numFrames = numFrames;
		pics = new ImageSystem[numFrames];
		for (int i = 0; i < pics.length; i++) {
			pics[i] = new ImageSystem(x, y, new ImageIcon("imgs/" + name + "/" + name + i + ".png").getImage());
		}
		frameNumber = (int) (Math.random() * numFrames);
	}

	// Moves the animation by x,y
	public void move(int x, int y) {
		for (int i = 0; i < pics.length; i++) {
			pics[i].move(x, y);
		}

	}

	// Sets the Rotation around an images center
	public void setRotation(double radians) {
		for (int i = 0; i < pics.length; i++) {
			pics[i].setRotation(radians);
		}
	}

	// Sets the rotation around a given center
	public void setRotation(double radians, double centerX, double centerY) {
		for (int i = 0; i < pics.length; i++) {
			pics[i].setRotation(radians, centerX, centerY);
		}
	}

	// Draws the current frame of the animation
	public void drawAnimation(Graphics2D g) {
		if (System.currentTimeMillis() - this.lastFrame >= this.delay && numFrames > 1) {
			frameNumber++;
			if (frameNumber >= numFrames) {
				frameNumber = 0;
			}

		}
		pics[frameNumber].drawImage(g);

	}

}
