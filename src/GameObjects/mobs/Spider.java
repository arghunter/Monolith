package GameObjects.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Spider extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats={20,4,10,0,120,64};
	
	public Spider(int x,int y,int id,int width,int height) {
		super(x,y,stats[0],stats,id,width,height,"Spider",3);
	}
	


	@Override
	public void render(Graphics2D g) {
		super.getImage().drawAnimation(g);
		
	}
}