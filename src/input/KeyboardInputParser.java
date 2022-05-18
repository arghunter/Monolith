//Author: Peter Ferolito
//Date: 5/9/22
//Rev: 01
//Notes: Uses KeyboardInput to model higher level player functionality like player movement
package input;

import java.awt.Component;

import javax.swing.JFrame;

import GameObjects.Direction;
import GameObjects.Player.Player;
import general.Collider;

public class KeyboardInputParser {
	// Fields
	KeyboardInput input;

	// Constructor
	public KeyboardInputParser(Component component) {
		this.input = new KeyboardInput(component);
	}

	// Updates the player position based on the keys currently presses
	public void updatePlayerPos(Player player,String[][] room) {
		int leftComp = (input.isLeftPressed() || input.isaPressed() ? 1 : 0);
		int rightComp = (input.isRightPressed() || input.isdPressed() ? 1 : 0);
		int upComp = (input.isUpPressed() || input.iswPressed() ? 1 : 0);
		int downComp = (input.isDownPressed() || input.issPressed() ? 1 : 0);
		if (leftComp - rightComp == 0) {
			if (upComp - downComp == 1) {
				player.move(Direction.NORTH);
			} else if (upComp - downComp == -1) {
				player.move(Direction.SOUTH);
			} else {
				// Don't move
			}
		} else if (leftComp - rightComp == 1) {
			if (upComp - downComp == 1) {
				player.move(Direction.NORTHWEST);
			} else if (upComp - downComp == -1) {
				player.move(Direction.SOUTHWEST);
			} else {
				player.move(Direction.WEST);
			}
		} else if (leftComp - rightComp == -1) {
			if (upComp - downComp == 1) {
				player.move(Direction.NORTHEAST);
			} else if (upComp - downComp == -1) {
				player.move(Direction.SOUTHEAST);
			} else {
				player.move(Direction.EAST);
			}
		}
		Collider c = new Collider(room);
		c.checkCollides(player.getRect(), player);
	}

	// Returns true if the escape key is pressed
	public boolean isEscapePressed() {
		return input.isEscapePressed();
	}

	public boolean isEnterPressed() {

		return input.isEnterPressed();

	}

}
