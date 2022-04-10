import javax.swing.JFrame;

import GameObjects.Player.Player;

public class InputParser {
	private KeyboardInputParser keyboard;
	private MouseInputParser mouse;
	public InputParser(JFrame frame)
	{
		keyboard=new KeyboardInputParser(frame);
		mouse=new MouseInputParser(frame);
	}
	
	public double getMouseX()
	{
		return mouse.getX();
	}
	public double getMouseY() 
	{
		return mouse.getY();
	}
	
	public void updatePlayerPosAndAngle(Player player) 
	{
		keyboard.updatePlayerPos(player);
		mouse.updatePlayerAngle(player);
		
	}
	

}
