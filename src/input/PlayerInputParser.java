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

	public static double getMouseX() {
		return MouseInputParser.getX();
	}

	public static double getMouseY() {
		return MouseInputParser.getY();
	}

	public static boolean getMBPressed(int MB) {
		return MouseInputParser.isMBDown(MB);
	}

	public void updatePlayer(Player player) {
		keyboard.updatePlayerPos(player);
		mouse.updatePlayerAngle(player);
		if(MouseInputParser.isMBDown(0)) 
		{
			player.useItem();
			
		}

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
