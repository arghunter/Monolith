//Author: Armaan Gomes 
//Date: 5/20/22
//Rev: 01
//Notes: Complete player input functionality

package input;

import java.awt.Component;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import GameObjects.Player.Player;
import GameObjects.mobs.Mob;
import general.Collider;

public class PlayerInputParser {

	// Fields
	private KeyboardInputParser keyboard;
	private MouseInputParser mouse;
	private double lastCoolDown = System.currentTimeMillis();
	private Graphics2D graphic;
	private Collider collider;

	// Constructor
	public PlayerInputParser(JFrame frame, Component component) {
		keyboard = new KeyboardInputParser(frame);
		mouse = new MouseInputParser(component);
	}
	//Sets the graphics
	public void setGraphics(Graphics2D g) {
		graphic = g;
	}

	// Returns the scaled mouse x
	public static double getMouseX() {
		return MouseInputParser.getX();
	}

	// Returns the scaled mouse y
	public static double getMouseY() {
		return MouseInputParser.getY();
	}

	// Returns the state of the given mouse button
	public static boolean getMBPressed(int MB) {
		return MouseInputParser.isMBDown(MB);
	}
	//Sets the room
	public void setRoom(String[][] room, int x, int y) {
		collider = new Collider(room, x, y);
	}

	// Updates the player
	public void updatePlayer(Player player) {
		keyboard.updatePlayerPos(player, collider);
		mouse.updatePlayerAngle(player);
		if (MouseInputParser.isMBDown(0) && System.currentTimeMillis() - lastCoolDown > 200) {
			player.useItem(graphic);
			lastCoolDown = System.currentTimeMillis();

		}
		if (keyboard.isOnePressed()) {
			player.useAbility(0);
		}
		if (keyboard.isTwoPressed()) {
			player.useAbility(1);
		}
		if (keyboard.isThreePressed()) {
			player.useAbility(2);
		}
		if (keyboard.isFourPressed()) {
			player.useAbility(3);
		}

	}
	//Updates only the player's angle
	public void updatePlayerAngle(Player player) {
		mouse.updatePlayerAngle(player);
	}

	// Sets the scaling ratio
	public void setRatio(double ratioX, double ratioY) {
		mouse.setRatio(ratioX, ratioY);
	}

	// Return the state of the escape key
	public boolean isEscapePressed() {
		return keyboard.isEscapePressed();
	}
	//Returns if the key is pressed
	public boolean isEnterPressed() {

		return keyboard.isEnterPressed();

	}

}
