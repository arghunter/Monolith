package GameObjects;

import java.util.Random;

public class Spider extends Mob {
	//speed, damage, health, armor, attackspeed
	public static int[] stats={10,2,10,0,2000};
	
	public Spider(int x,int y,int id) {
		super(x,y,stats[0],id,stats);
	}
	
	public void move() {
		Random rm=new Random();
		Direction possDirect[]= {Direction.NORTH};
		super.move(possDirect[rm.nextInt(8)]);
	}
}