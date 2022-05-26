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
	// Key fields
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean wPressed = false;
	private boolean aPressed = false;
	private boolean sPressed = false;
	private boolean dPressed = false;
	private boolean escapePressed = false;
	private boolean enterPressed = false;
	private boolean onePressed = false;
	private boolean twoPressed = false;
	private boolean threePressed = false;
	private boolean fourPressed = false;

	// Constructor
	public KeyboardInput(Component component) {
		component.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	// Stores important keys as boolean variables
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			aPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			dPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			escapePressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			enterPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_1) {
			onePressed = true;

		} else if (e.getKeyCode() == KeyEvent.VK_2) {
			twoPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_3) {
			threePressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_4) {
			fourPressed = true;
		}
	}

	@Override
	// Stores important keys as boolean variables
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			aPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			dPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			escapePressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			enterPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_1) {
			onePressed = false;

		} else if (e.getKeyCode() == KeyEvent.VK_2) {
			twoPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_3) {
			threePressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_4) {
			fourPressed = false;
		}
	}
	//Returns if the key is pressed
	public boolean isOnePressed() {
		return onePressed;
	}
	//Returns if the key is pressed
	public boolean isTwoPressed() {
		return twoPressed;
	}
	//Returns if the key is pressed
	public boolean isThreePressed() {
		return threePressed;
	}
	//Returns if the key is pressed
	public boolean isFourPressed() {
		return fourPressed;
	}
	//Returns if the key is pressed
	public boolean isEnterPressed() {
		return enterPressed;
	}
	//Returns if the key is pressed
	public boolean isEscapePressed() {
		return escapePressed;

	}
	//Returns if the key is pressed
	public boolean isLeftPressed() {
		return leftPressed;
	}
	//Returns if the key is pressed
	public boolean isRightPressed() {
		return rightPressed;
	}
	//Returns if the key is pressed
	public boolean isUpPressed() {
		return upPressed;
	}
	//Returns if the key is pressed
	public boolean isDownPressed() {
		return downPressed;
	}
	//Returns if the key is pressed
	public boolean iswPressed() {
		return wPressed;
	}
	//Returns if the key is pressed
	public boolean isaPressed() {
		return aPressed;
	}
	//Returns if the key is pressed
	public boolean issPressed() {
		return sPressed;
	}
	//Returns if the key is pressed
	public boolean isdPressed() {
		return dPressed;
	}

}
