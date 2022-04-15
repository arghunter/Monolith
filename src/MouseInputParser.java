import java.awt.MouseInfo;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class MouseInputParser {
	MouseInput input;

	public MouseInputParser(JFrame frame) {
		
		this.input = new MouseInput(frame);
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
	
	

}
