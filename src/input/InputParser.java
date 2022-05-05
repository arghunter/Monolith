package input;

import java.awt.Component;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class InputParser {
	private KeyboardInputParser keyboard;
	private MouseInputParser mouse;

	public InputParser(Component component) {
		keyboard = new KeyboardInputParser(component);
		mouse = new MouseInputParser(component);
	}

	public double getMouseX() {
		return mouse.getX();
	}

	public double getMouseY() {
		return mouse.getY();
	}
<<<<<<< Updated upstream

	public boolean getMBPressed(int MB) {
=======
	public boolean getMBPressed(int MB) 
	{
		
>>>>>>> Stashed changes
		return mouse.isMBDown(MB);
	}

	public void updatePlayerPosAndAngle(Player player) {
		keyboard.updatePlayerPos(player);
		mouse.updatePlayerAngle(player);

	}

	public void setRatio(double ratioX, double ratioY) {
		mouse.setRatio(ratioX, ratioY);
	}

}
