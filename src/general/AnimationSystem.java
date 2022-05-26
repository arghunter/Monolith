//Author: Armaan Gomes
//Date: 5/9/22
//Rev: 01
//Notes: A system that animates a series of images
package general;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import GameObjects.elementalDamage.StatusEffect;

public class AnimationSystem implements Runnable {
	// Fields
	private long delay;
	private long lastFrame = System.currentTimeMillis();
	private int frameNumber;
	private int numFrames;
	private ImageSystem[] pics;
	private StatusEffect status = StatusEffect.FROST;

	private Thread thread;
	private long endTime;
	private BufferedImage statusImage;

	// Constructor
	public AnimationSystem(int x, int y, long delay, String name) {
		this.delay = delay;
		numFrames = 0;
		while (true) {
			if (new ImageIcon("imgs/" + name + "/" + name + numFrames + ".png").getImage().getHeight(null) == -1) {
				break;
			} else {
				numFrames++;
			}
		}
		pics = new ImageSystem[numFrames];
		for (int i = 0; i < pics.length; i++) {
			pics[i] = new ImageSystem(x, y, new ImageIcon("imgs/" + name + "/" + name + i + ".png").getImage());
		}
		frameNumber = (int) (Math.random() * numFrames);
		start();
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

	// Adds particle effects based on elemental damage
	public void setStatus(StatusEffect status, double duration) {
		this.status = status;
		endTime = (long) (System.currentTimeMillis() + duration * 1000);

		statusImage = new BufferedImage(pics[0].getWidth(), pics[0].getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = statusImage.createGraphics();

		try {
			switch (status) {
			case FROST:
				g.setColor(new Color(185, 205, 226, 75));
				g.fillRect(0, 0, statusImage.getWidth(), statusImage.getHeight());
				break;
			case FIRE:
				g.setColor(new Color(226, 88, 34, 75));
				g.fillOval(0, 0, statusImage.getWidth(), statusImage.getHeight());
				break;
			case LIGHTNING:
				g.setColor(new Color(253, 208, 35, 75));
				g.fillOval(0, 0, statusImage.getWidth(), statusImage.getHeight());
				break;
			case ROT:
				g.setColor(new Color(23, 0, 0, 75));
				g.fillOval(0, 0, statusImage.getWidth(), statusImage.getHeight());
				g.setColor(new Color(50, 0, 0));
				for (int i = 0; i < 10; i++) {
					g.fillOval((int) (Math.random() * statusImage.getWidth()),
							(int) (Math.random() * statusImage.getHeight()), 5, 5);

				}
				break;
			case TOXIN:
				g.setColor(new Color(27, 200, 12, 75));
				g.fillOval(0, 0, statusImage.getWidth(), statusImage.getHeight());
				g.setColor(new Color(27, 200, 12, 100));
				for (int i = 0; i < 10; i++) {
					g.fillOval((int) (Math.random() * statusImage.getWidth()),
							(int) (Math.random() * statusImage.getHeight()), 5, 5);

				}
				break;
			case CORROSION:
				g.setColor(new Color(167, 52, 21, 75));
				g.fillOval(0, 0, statusImage.getWidth(), statusImage.getHeight());
				break;
			case GAS:
				g.setColor(new Color(41, 156, 105, 75));
				g.fillOval(0, 0, statusImage.getWidth(), statusImage.getHeight());
			case VIRAL:
				g.setColor(new Color(234, 127, 195, 75));
				g.fillOval(0, 0, statusImage.getWidth(), statusImage.getHeight());

			}
		} catch (Exception e) {

		}

	}

	// Returns the x of this animation system
	public int getX() {
		return pics[0].getX();
	}

	// Returns the y of this animation system
	public int getY() {
		return pics[0].getY();
	}

	// Returns the width of this animation
	public int getWidth() {
		return pics[0].getWidth();
	}

	// Returns the height of this animation
	public int getHeight() {
		return pics[0].getHeight();
	}

	// Draws the current frame of the animation and status effect
	public void drawAnimation(Graphics2D g) {

		pics[frameNumber].drawImage(g);
		if (statusImage != null && status != StatusEffect.NONE) {
			g.drawImage(statusImage, pics[0].getTransform(), null);
			switch (status) {
			case LIGHTNING:
				g.setColor(new Color(253, 208, 35, 50));
				g.fillOval(pics[0].getX() - 80, pics[0].getY() - 80, 160, 160);
				break;
			case GAS:
				g.setColor(new Color(41, 156, 105, 50));
				g.fillOval(pics[0].getX() - 200, pics[0].getY() - 200, 400, 400);

			}
		}

	}

	// Starts this animation
	public void start() {
		if (thread == null) {
			thread = new Thread(this, "" + System.currentTimeMillis());
			thread.start();
		}
	}

	@Override
	// Runs this animation
	public void run() {
		while (true) {

			if (System.currentTimeMillis() - this.lastFrame >= this.delay && numFrames > 1) {
				frameNumber++;
				if (frameNumber >= numFrames) {
					frameNumber = 0;
				}

			}
			if (System.currentTimeMillis() > endTime) {
				status = StatusEffect.NONE;
			}
			try {
				thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
