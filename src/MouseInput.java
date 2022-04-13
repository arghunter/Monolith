import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;


public class MouseInput implements MouseListener {
	private JFrame frame;
	public MouseInput(JFrame frame) 
	{
		this.frame=frame;
		this.frame.addMouseListener(this);
	
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		if(e.getButton()==MouseEvent.BUTTON1) 
//		{
//			System.out.println("MB1C");
//		}
		
		

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		if(e.getButton()==MouseEvent.BUTTON1) 
//		{
//			System.out.println("MB1P");
//		}

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
//		if(e.getButton()==MouseEvent.BUTTON1) 
//		{
//			System.out.println("MB1R");
//		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
