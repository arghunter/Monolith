
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyboardInput implements KeyListener {
	private JFrame frame;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean wPressed = false;
	private boolean aPressed = false;
	private boolean sPressed = false;
	private boolean dPressed = false;

	public KeyboardInput(JFrame frame) 
	{
		this.frame=frame;
		this.frame.addKeyListener(this);
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
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
		}
	}
	
	@Override
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
