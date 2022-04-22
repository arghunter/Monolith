package GameObjects.Player.items.weapons;

import GameObjects.Mob;
import GameObjects.Player.Player;

public abstract class MeleeWeapon extends Weapon {
	private double sweepAngle;
	public MeleeWeapon(int damage,int range,double attackSpeed,double sweepAngle) {
		super(damage,range,attackSpeed);
	}

	public double euclidDist(int x1, int x2, int y1, int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	@Override
	public void primaryFire(Mob[] mobs, Player player) {
		for(Mob m : mobs) {
			if(this.euclidDist(m.getCenterX(), m.getCenterY(), player.getCenterX(), player.getCenterY()) < super.getRange()) {
				m.doDamage(super.getDamage());
			}
		}
		
	}

}
