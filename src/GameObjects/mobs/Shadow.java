//Author: Peter Ferolito   
//Date: 5/20/22
//Rev: 01
//Notes: An implementation of a shadow mob that looks like the player
package GameObjects.mobs;

import java.awt.Graphics2D;

public class Shadow extends Mob {
	// speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats = { 20, 5, 250, 5, 20, 60 };
	public static final int xpDropped = 1;

	// Constructor
	public Shadow(int x, int y) {
		super(x, y, stats[0], stats, 64, 64, "Shadow", 1, xpDropped);
	}

	@Override
	// Renders this object
	public void render(Graphics2D g) {
		super.render(g);
		super.getImage().drawAnimation(g);

	}

}
