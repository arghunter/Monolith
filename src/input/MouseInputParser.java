//Author: Armaan Gomes
//Date: 5/8/22
//Rev: 01
//Notes: High level mouse input functionality such as updateing the play angle and scaling ratios

package input;

import java.awt.Component;
import java.awt.MouseInfo;

import javax.swing.JFrame;

import GameObjects.Player.Player;
import GameObjects.mobs.Mob;

public class MouseInputParser {
	// Fields
	MouseInput input;
	private static double ratioX = 1;
	private static double ratioY = 1;

	// Constructor
	public MouseInputParser(Component component) {

		this.input = new MouseInput(component);
	}

	// returns the scaled position of the mouse
	public static double getX() {

		return MouseInfo.getPointerInfo().getLocation().getX() / ratioX;
	}

	// returns the scaled position of the mouse
	public static double getY() {
		return (MouseInfo.getPointerInfo().getLocation().getY() / ratioY);
	}

	// updates the player's angle so that it faces the mouse
	public void updatePlayerAngle(Player player) {
		player.updateAngle(getX(), getY());

	}

	// Returns if the given mouse Button is pressed
	public static boolean isMBDown(int MB) {
		if (MB < 3) {
			return MouseInput.getButtonStates()[MB];
		}
		return false;

	}

	// Sets the scaling ratio
	public void setRatio(double ratioX, double ratioY) {
		this.ratioX = ratioX;
		this.ratioY = ratioY;
	}

	// Returns the scaling ratio for x values
	public static double getRatioX() {
		return ratioX;
	}

	// Returns the scaling ratio for y values
	public static double getRatioY() {
		return ratioY;
	}

}
