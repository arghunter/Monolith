//Author: Armaan Gomes
//Date: 5/12/22
//Rev: 01
// Notes: A event to represent a button click
package ui;

import java.awt.event.ActionEvent;

public class ButtonClickedEvent extends ActionEvent {

	// Constructor
	public ButtonClickedEvent(Object source, String command) {
		super(source, 88888, command);
	}

}
