package input;
import java.awt.*;
import java.awt.MouseInfo;

import javax.swing.JFrame;

import GameObjects.Player.Player;

public class MouseInputParser {
	MouseInput input;
	private static double ratioX=1;
	private static double ratioY=1;
	
	public MouseInputParser(Component component) {
		
		this.input = new MouseInput(component);
		
	}
	public static double getX() 
	{
		System.out.println(MouseInfo.getPointerInfo().getLocation().getX()*ratioX+" X");

		return MouseInfo.getPointerInfo().getLocation().getX()/ratioX;
	}
	public static double getY() 
	{
		System.out.println(MouseInfo.getPointerInfo().getLocation().getY()*ratioY+" y");
		return (MouseInfo.getPointerInfo().getLocation().getY()/ratioY);
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
	public void setRatio(double ratioX,double ratioY) 
	{
		this.ratioX=ratioX;
		this.ratioY=ratioY;
		//System.out.println(ratioX+" "+ratioY);
	}
	
	

}
