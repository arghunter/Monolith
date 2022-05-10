//Author: Armaan Gomes
//Date: 5/9/22
//Rev: 01
//Notes: Complete player input functionality

package input;

import java.awt.Component;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class PlayerInputParser {
	
	//Fields
	private KeyboardInputParser keyboard;
	private MouseInputParser mouse;
	private double lastCoolDown=System.currentTimeMillis();
	//Constructor
	public PlayerInputParser(JFrame frame,Component component) {
		keyboard = new KeyboardInputParser(frame);
		mouse = new MouseInputParser(component);
	}
	//Returns the scaled mouse x
	public static double getMouseX() {
		return MouseInputParser.getX();
	}
	//Returns the scaled mouse y
	public static double getMouseY() {
		return MouseInputParser.getY();
	}
	//Returns the state of the given mouse button
	public static boolean getMBPressed(int MB) {
		return MouseInputParser.isMBDown(MB);
	}
	//Updates the player
	public void updatePlayer(Player player) {
		keyboard.updatePlayerPos(player);
		mouse.updatePlayerAngle(player);
		if(MouseInputParser.isMBDown(0)&&System.currentTimeMillis()-lastCoolDown>100) 
		{
			player.useItem();
			lastCoolDown=System.currentTimeMillis();
			
		}

	}
	//Only updates the player's angle
	public void updatePlayerAngle(Player player) 
	{
		mouse.updatePlayerAngle(player);

	}
	//Sets the scaling ratio
	public void setRatio(double ratioX, double ratioY) {
		mouse.setRatio(ratioX, ratioY);
	}
	//Return the state of the escape key
	public boolean isEscapePressed() 
	{
		return keyboard.isEscapePressed();
	}

}
