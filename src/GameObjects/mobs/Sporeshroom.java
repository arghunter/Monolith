package GameObjects.mobs;

import java.awt.Graphics2D;

public class Sporeshroom extends Mob implements RangedMob {
	// speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats = { 20, 38, 250, 5, 20, 500 };

	// Constructor
	public Sporeshroom(int x,int y) {
				super(x,y,stats[0],stats,64,64,"Sporeshroom",7);
				super.dist=0;
			}

	@Override
	// Renders this object
	public void render(Graphics2D g) {
		super.render(g);
		super.getImage().drawAnimation(g);

	}

	@Override
	public void fireProjectile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderProjectiles(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
