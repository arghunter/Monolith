package GameObjects;

import java.awt.Graphics;
import java.util.Random;

public class Spider extends Mob {
	//speed, damage, health, armor, attackspeed
	public static int[] stats={10,2,10,0,2000};
	
	public Spider(int x,int y,int id,int width,int height,String imageName) {
		super(x,y,stats[0],stats,id,width,height,imageName);
	}
	
	public void move() {
		Random rm=new Random();
		Direction possDirect[]= {Direction.NORTH};
		super.move(possDirect[rm.nextInt(8)]);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}