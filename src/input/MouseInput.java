//Author: Armaan Gomes
//Date: 5/8/22
//Rev: 01
//Notes: Basic mouse input functionality. Important: the passed in component needs to be focused for this mouse listener to work
package input;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import GameObjects.Player.Player;
import GameObjects.Player.items.weapons.MeleeWeapon;
import GameObjects.mobs.Mob;

public class MouseInput implements MouseListener {
	// Fields
	private Component component;

	private static boolean[] buttonStates = new boolean[3];

	// Constructor

	public MouseInput(Component component) {
		this.component = component;
		this.component.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	// Updates buttonStates based on which mouse button was pressed.
	public void mousePressed(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {
			buttonStates[0] = true;
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			buttonStates[1] = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			buttonStates[2] = true;
		}

	}

	@Override
	// Updates buttonStates based on which mouse button was released.
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			buttonStates[0] = false;
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			buttonStates[1] = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			buttonStates[2] = false;
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

	// Returns the state of the buttons
	public static boolean[] getButtonStates() {
		return buttonStates;
	}

}
