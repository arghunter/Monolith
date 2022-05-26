//Author: Peter Ferolito  
//Date: 5/16/22
//Rev: 01
//Notes: IceDrake mob
package GameObjects.mobs;

import java.awt.Graphics2D;

public class IceDrake extends Mob {
	// speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats = { 15, 160, 2000, 200, 40, 500 };
	public static final int xpDropped = 600;

	// Constructor
	public IceDrake(int x, int y) {
		super(x, y, stats[0], stats, 640, 560, "IceDrake", 1, xpDropped);
	}

	@Override
	// Renders this object
	public void render(Graphics2D g) {
		super.render(g);
		super.getImage().drawAnimation(g);

	}
}
