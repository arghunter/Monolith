package GameObjects.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Spider extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats={20,2,10,0,2000,64};
	
	public Spider(int x,int y,int id,int width,int height) {
		super(x,y,stats[0],stats,id,width,height,"Spider",3);
	}
	
	public int action(int playerX,int playerY) {
		super.action(playerX,playerY,stats);
		return 0;
	}

	@Override
	public void render(Graphics2D g) {
		super.getImage().drawAnimation(g);
		
	}
}