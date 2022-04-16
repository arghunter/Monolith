import java.awt.Component;
import java.awt.MouseInfo;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class MouseInputParser {
	MouseInput input;

	public MouseInputParser(Component component) {
		
		this.input = new MouseInput(component);
	}
	public static double getX() 
	{
		return MouseInfo.getPointerInfo().getLocation().getX();
	}
	public static double getY() 
	{
		return MouseInfo.getPointerInfo().getLocation().getY();
	}
	public void updatePlayerAngle(Player player) 
	{
		player.updateAngle(getX(), getY());
	}
	public boolean isMBDown(int MB) 
	{
		if(MB<3) 
		{
			return input.getButtonStates()[0];
		}
		return false;
		
	}
	
	

}
