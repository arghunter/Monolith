package GameObjects.mobs;

import java.awt.Graphics;

import GameObjects.MovingObject;
import GameObjects.Direction;

public abstract class Mob extends MovingObject {
	//speed, damage, health, armor, attackspeed, attack range
	private static int[] stats=new int[6];
	
	public Mob(int x,int y,int movementDelay, int[] stats,int id,int width, int height,String name,int numFrames) {
		super(x,y,movementDelay,id,width,height,name,numFrames,stats[2]);
	}
	
	public void setStat(int statNum,int newValue) {
		stats[statNum]=newValue;
	}
	
	public void setAllStats(int[] stats) {
		if(stats.length==6) {
			Mob.stats=stats;
		}
	}
	public int getHealth() {
		return health;
	}
	public void update(double pointX, double pointY) {
		updateAngle(pointX, pointY);
	}

	public int action(int playerX,int playerY,int[] stats) {
		int curX=this.getX();
		int curY=this.getY();
		int diffX=curX-playerX;
		int diffY=curY-playerY;
		int damage=0;
		if((diffX)*(diffX)+(diffY)*(diffY)<stats[5]*stats[5]) {
			//ATTACK
		}else {
			Direction toMove=Direction.NORTH;
			if(1.5*Math.abs(diffX)<Math.abs(diffY)) {
				//Move up down
				if(diffY>0) {
					toMove=Direction.NORTH;
				}else {
					toMove=Direction.SOUTH;
				}
			}else if(1.5*Math.abs(diffY)<Math.abs(diffX)) {
				//Move left right
				if(diffX>0) {
					toMove=Direction.WEST;
				}else {
					toMove=Direction.EAST;
				}
			}else {
				//Move diagonal
				if(diffX>0 && diffY>0) {
					toMove=Direction.NORTHWEST;
				}else if(diffX<=0 && diffY>0) {
					toMove=Direction.NORTHEAST;
				}else if(diffX>0 && diffY<=0) {
					toMove=Direction.SOUTHWEST;
				}else if(diffX<=0 && diffY<=0) {
					toMove=Direction.SOUTHEAST;
				}
			}
			if(diffX*diffX+diffY*diffY>=(stats[5]*stats[5])) {
				this.move(toMove);
			}
		}
		return 0;
	}

	public abstract int action(int playerX,int playerY);
}
