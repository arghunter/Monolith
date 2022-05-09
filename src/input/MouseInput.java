package input;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import GameObjects.Player.Player;


public class MouseInput implements MouseListener {
	private Component component;
	private static boolean[] buttonStates=new boolean[3];
	Player currentPlayer;
	public MouseInput(Component component) 
	{
		this.component=component;
		this.component.addMouseListener(this);
		
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		

		
		

	}
	public void setPlayer(Player player) {
		this.currentPlayer = player;
	}
	@Override
	public void mousePressed(MouseEvent e) {
	

		if(e.getButton()==MouseEvent.BUTTON1) 
		{
			buttonStates[0]=true;
		}else if(e.getButton()==MouseEvent.BUTTON2) 
		{
			buttonStates[1]=true;
		}else if(e.getButton()==MouseEvent.BUTTON3) 
		{
			buttonStates[2]=true;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) 
		{
			buttonStates[0]=false;
		}else if(e.getButton()==MouseEvent.BUTTON2) 
		{
			buttonStates[1]=false;
		}else if(e.getButton()==MouseEvent.BUTTON3) 
		{
			buttonStates[2]=false;
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static boolean[] getButtonStates() 
	{
		return buttonStates;
	}
	
	
	
	

}
