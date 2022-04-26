package input;

import java.awt.Component;
import java.awt.MouseInfo;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class MouseInputParser {
	MouseInput input;

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

	public boolean isMBDown(int MB) {
		if (MB < 3) {
			return input.getButtonStates()[0];
		}
		return false;

	}<<<<<<<HEAD=======

	public void setRatio(double ratioX, double ratioY) {
		this.ratioX = ratioX;
		this.ratioY = ratioY;
		// System.out.println(ratioX+" "+ratioY);
	}

	public static double getRatioX() {
		return ratioX;
	}

	public static double getRatioY() 
	{
		return ratioY;
	}>>>>>>>buggyStuff

}
