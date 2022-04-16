import java.awt.Component;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class InputParser {
	private KeyboardInputParser keyboard;
	private MouseInputParser mouse;
	public InputParser(Component component)
	{
		keyboard=new KeyboardInputParser(component);
		mouse=new MouseInputParser(component);
	}
	
	public double getMouseX()
	{
		return mouse.getX();
	}
	public double getMouseY() 
	{
		return mouse.getY();
	}
	public boolean getMBPressed(int MB) 
	{
		return mouse.isMBDown(MB);
	}
	
	public void updatePlayerPosAndAngle(Player player) 
	{
		keyboard.updatePlayerPos(player);
		mouse.updatePlayerAngle(player);
		
	}
	

}
