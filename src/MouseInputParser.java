import java.awt.MouseInfo;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class MouseInputParser {
	MouseInput input;

	public MouseInputParser(JFrame frame) {
		
		this.input = new MouseInput(frame);
	}
	public int getX() 
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getX();
	}
	public int getY() 
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getY();
	}
	public void updatePlayerAngle(Player player) 
	{
		player.updateAngle(getX(), getY());
	}
	
	

}
