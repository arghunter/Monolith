package input;

import java.awt.Component;
import java.awt.MouseInfo;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class MouseInputParser {
	MouseInput input;
	private static double ratioX = 1;
	private static double ratioY = 1;

	public MouseInputParser(Component component) {

		this.input = new MouseInput(component);
	}

	public static double getX() {

		return MouseInfo.getPointerInfo().getLocation().getX() / ratioX;
	}

	public static double getY() {
		return (MouseInfo.getPointerInfo().getLocation().getY() / ratioY);
	}

	public void updatePlayerAngle(Player player) {
		player.updateAngle(getX(), getY());
	}

	public static boolean isMBDown(int MB) {
		if (MB < 3) {
			return MouseInput.getButtonStates()[MB];
		}
		return false;

	}

	public void setRatio(double ratioX, double ratioY) {
		this.ratioX = ratioX;
		this.ratioY = ratioY;
		// System.out.println(ratioX+" "+ratioY);
	}

	public static double getRatioX() {
		return ratioX;
	}

	public static double getRatioY() {
		return ratioY;
	}

}
