package GameObjects.Player;

import GameObjects.Mob;

public class ShortRangeWeapon extends Weapon {
	int damageRadius = 0;
	Player player ;
	int damage;
	public ShortRangeWeapon(int damageRadius, Player player, int damage) {
		this.damageRadius = damageRadius;
		this.player = player;
		this.damage = damage;
	}
	public void doDamage(Mob[] arr) {
		for(Mob m : arr) {
			if(this.euclidDist(m.getCenterX(), m.getCenterY(), player.getCenterX(), player.getCenterY()) < damageRadius) {
				m.doDamage(damage);
			}
		}
	}
	public double euclidDist(int x1, int x2, int y1, int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
}
