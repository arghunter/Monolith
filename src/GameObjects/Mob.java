package GameObjects;

import java.awt.Graphics;

public abstract class Mob extends MovingObject {
	//Health, 
	private static int[] stats=new int[5];
	
	public Mob(int x,int y,int movementDelay, int[] stats,int id,int width, int height,String imageName) {
		super(x,y,movementDelay,id,width,height,imageName);
	}
	
	public void setStat(int statNum,int newValue) {
		stats[statNum]=newValue;
	}
	
	public void setAllStats(int[] stats) {
		if(stats.length==5) {
			Mob.stats=stats;
		}
	}
	public void update(double pointX, double pointY) {
		updateAngle(pointX, pointY);
	}


}
