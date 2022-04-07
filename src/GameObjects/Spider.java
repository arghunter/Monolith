package GameObjects;

import java.awt.Graphics;
import java.util.Random;

public class Spider extends Mob {
	//speed, damage, health, armor, attackspeed
	public static int[] stats={10,2,10,0,2000};
	
	public Spider(int x,int y,int id,int width,int height,String imageName) {
		super(x,y,stats[0],stats,id,width,height,imageName);
	}
	
	public void move(Direction moveDirection) {
		super.move(moveDirection);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}