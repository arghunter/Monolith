package input;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;


public class MouseInput implements MouseListener {
	private Component component;
	private boolean[] buttonStates=new boolean[3];
	public MouseInput(Component component) 
	{
		this.component=component;
		this.component.addMouseListener(this);
	
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		

		
		

	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) 
		{
			buttonStates[0]=true;
			System.out.println("clicked");
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
	public boolean[] getButtonStates() 
	{
		return buttonStates;
	}
	
	
	
	

}
