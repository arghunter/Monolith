//Author: Peter Ferolito   
//Date: 5/20/22
//Rev: 01
//Notes: A high health plant mob
package GameObjects.mobs;

import java.awt.Graphics2D;

public class HealthPlant extends Mob {
	// speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats = { 20, 1, 500, 0, 30, 60 };
	public static final int xpDropped = 1;

	// Constructor
	public HealthPlant(int x, int y) {
		super(x, y, stats[0], stats, 64, 64, "HealthPlant", 1, xpDropped);
		super.dist = 0;
	}

	@Override
	// Renders this object
	public void render(Graphics2D g) {
		super.render(g);
		super.getImage().drawAnimation(g);

	}
}
