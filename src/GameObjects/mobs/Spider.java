package GameObjects.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Spider extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats={20,10,10,0,120,20};
	
	public Spider(int x,int y,int width,int height) {
		super(x,y,stats[0],stats,width,height,"Spider",3);
	}
	


	@Override
	public void render(Graphics2D g) {
		super.getImage().drawAnimation(g);
		
	}
}