package GameObjects.mobs;

import java.awt.Graphics;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
import GameObjects.Direction;

public abstract class Mob extends MovingObject {
	// speed, damage, health, armor, attackspeed, attack range
	private int[] stats = new int[6];
	private double lastAttack = System.currentTimeMillis();

	public Mob(int x, int y, int movementDelay, int[] stats, int id, int width, int height, String name,
			int numFrames) {
		super(x, y, movementDelay, id, width, height, name, numFrames);
		this.stats = stats;
	}

	public void setStat(int statNum, int newValue) {
		stats[statNum] = newValue;
	}

	public void setAllStats(int[] stats) {
		if (stats.length == 6) {
			this.stats = stats;
		}
	}

	public void update(double pointX, double pointY) {
		updateAngle(pointX, pointY);
	}

	public int action(Player player) {
		int curX = this.getX();
		int curY = this.getY();

		int diffX = curX - player.getCenterX();
		int diffY = curY - player.getCenterY();

		if ((diffX) * (diffX) + (diffY) * (diffY) < stats[5] * stats[5]) {
			if (System.currentTimeMillis() - lastAttack > 60000.0 / stats[4]) {
				player.takeDamage(stats[1]);
				lastAttack=System.currentTimeMillis();
			}

		} else {
			Direction toMove = Direction.NORTH;
			if (1.5 * Math.abs(diffX) < Math.abs(diffY)) {
				// Move up down
				if (diffY > 0) {
					toMove = Direction.NORTH;
				} else {
					toMove = Direction.SOUTH;
				}
			} else if (1.5 * Math.abs(diffY) < Math.abs(diffX)) {
				// Move left right
				if (diffX > 0) {
					toMove = Direction.WEST;
				} else {
					toMove = Direction.EAST;
				}
			} else {
				// Move diagonal
				if (diffX > 0 && diffY > 0) {
					toMove = Direction.NORTHWEST;
				} else if (diffX <= 0 && diffY > 0) {
					toMove = Direction.NORTHEAST;
				} else if (diffX > 0 && diffY <= 0) {
					toMove = Direction.SOUTHWEST;
				} else if (diffX <= 0 && diffY <= 0) {
					toMove = Direction.SOUTHEAST;
				}
			}
			if (diffX * diffX + diffY * diffY >= (stats[5] * stats[5])) {
				this.move(toMove);
			}
		}
		return 0;
	}

}
