//Author: Peter Ferolito
//Date: 5/9/22
//Rev: 02
//Notes: Basic keyboard input functionality
package input;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyboardInput implements KeyListener {
	//Key fields
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean wPressed = false;
	private boolean aPressed = false;
	private boolean sPressed = false;
	private boolean dPressed = false;
	private boolean escapePressed=false;
	private boolean enterPressed=false;
	
	//Constructor
	public KeyboardInput(Component component) 
	{
		component.addKeyListener(this);
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	//Stores important keys as boolean variables
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			leftPressed=true;
		}else
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rightPressed=true;
		}else
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			upPressed=true;
		}else
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			downPressed=true;
		}else
		if(e.getKeyCode()==KeyEvent.VK_W) {
			wPressed=true;
		}else
		if(e.getKeyCode()==KeyEvent.VK_A) {
			aPressed=true;
		}else
		if(e.getKeyCode()==KeyEvent.VK_S) {
			sPressed=true;
		}else
		if(e.getKeyCode()==KeyEvent.VK_D) {
			dPressed=true;
		}else if(e.getKeyCode()==KeyEvent.VK_ESCAPE) 
		{
			escapePressed=true;
		}else if(e.getKeyCode()==KeyEvent.VK_ENTER) 
		{
			enterPressed=true;
		}
	}
	
	@Override
	//Stores important keys as boolean variables
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			leftPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rightPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			upPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			downPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_W) {
			wPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_A) {
			aPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_S) {
			sPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_D) {
			dPressed=false;
		} 
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) 
		{
			escapePressed=false;
		}else if(e.getKeyCode()==KeyEvent.VK_ENTER) 
		{
			enterPressed=false;
		}
	}
	







	public boolean isEnterPressed() {
		return enterPressed;
	}



	public boolean isEscapePressed() {
		return escapePressed;
		
	}



	public boolean isLeftPressed() {
		return leftPressed;
	}



	public boolean isRightPressed() {
		return rightPressed;
	}



	public boolean isUpPressed() {
		return upPressed;
	}



	public boolean isDownPressed() {
		return downPressed;
	}



	public boolean iswPressed() {
		return wPressed;
	}



	public boolean isaPressed() {
		return aPressed;
	}



	public boolean issPressed() {
		return sPressed;
	}



	public boolean isdPressed() {
		return dPressed;
	}

	
	

}
