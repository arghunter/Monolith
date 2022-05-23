package GameObjects.mobs;

import java.awt.Graphics2D;

public interface RangedMob {
	
	public abstract void fireProjectile();
	public abstract void renderProjectiles(Graphics2D g);

}
