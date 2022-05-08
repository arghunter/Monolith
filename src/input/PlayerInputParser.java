package input;

import java.awt.Component;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class PlayerInputParser {
	private KeyboardInputParser keyboard;
	private MouseInputParser mouse;

	public PlayerInputParser(Component component) {
		keyboard = new KeyboardInputParser(component);
		mouse = new MouseInputParser(component);
	}

	public double getMouseX() {
		return mouse.getX();
	}

	public double getMouseY() {
		return mouse.getY();
	}

	public boolean getMBPressed(int MB) {
		return mouse.isMBDown(MB);
	}

	public void updatePlayerPosAndAngle(Player player) {
		keyboard.updatePlayerPos(player);
		mouse.updatePlayerAngle(player);

	}
	public void updatePlayerAngle(Player player) 
	{
		mouse.updatePlayerAngle(player);

	}

	public void setRatio(double ratioX, double ratioY) {
		mouse.setRatio(ratioX, ratioY);
	}
	public boolean isEscapePressed() 
	{
		return keyboard.isEscapePressed();
	}

}
