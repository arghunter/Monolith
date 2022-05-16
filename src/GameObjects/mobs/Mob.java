//Author: Peter Ferolito 
//Date: 5/12/22
//Rev: 01
//Notes: An implementation of a mbo
package GameObjects.mobs;

import java.awt.Graphics;
import java.util.Arrays;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
import GameObjects.Direction;

public abstract class Mob extends MovingObject {
	//Fields
	// speed, damage, health, armor, attackspeed, attack range
	private int[] baseStats = new int[6];
	private int[] stats=new int[6];
	private double lastAttack = System.currentTimeMillis();
	private int playerLevel=1;
	//Constructors
	public Mob(int x, int y, int movementDelay, int[] stats, int width, int height, String name, int numFrames) {
		super(x, y, movementDelay, width, height, name, numFrames,stats[2]);
		this.baseStats = stats;
	}
	//Sets this mobs stats
	public void setStat(int statNum, int newValue) {
		stats[statNum] = newValue;
	}
	//Sets all Stats
	public void setAllStats(int[] stats) {
		if (stats.length == 6) {
			this.stats = stats;
		}
	}
	//Returns the health of this mob
	public int getHealth() {
		return health;
	}
	
	public void takeDamage(Player player,int damage) 
	{
		damage = damage/(int) ((0.5 * Math.log(stats[1] * Math.log(stats[1]))) + 0.5)+1;
		health-=damage;
		super.setCurrentMovementDelay(damage*10<5000? damage*10:5000);
		if(health<0) 
		{
			super.setDead(true);
			player.addXP(playerLevel*10);
		}
	}
	
	//Makes this mob complete its next action, either moving or attacking the player.
	public int action(Player player) {
		playerLevel=player.getLevel();
		updateAngle(player.getX(),player.getY());
		for(int i=1;i<4;i++) 
		{
			stats[i]=baseStats[i]*playerLevel/5;
		}
		stats[0]=baseStats[0];
		stats[4]=baseStats[4];
		stats[5]=baseStats[5];
		System.out.println(this+" "+Arrays.toString(stats)+" "+playerLevel);
		int curX = this.getX();
		int curY = this.getY();

		int diffX = curX - player.getX();
		int diffY = curY - player.getY();

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
