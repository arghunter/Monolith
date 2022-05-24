//Author: Peter Ferolito   
//Date: 5/20/22
//Rev: 01
//Notes: An interface for ranged mobs
package GameObjects.mobs;

import java.awt.Graphics2D;

public interface RangedMob {
	//Fires  a projectile
	public abstract void fireProjectile();
	//Renders all projectiles
	public abstract void renderProjectiles(Graphics2D g);

}
