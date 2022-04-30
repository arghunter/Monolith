package GameObjects.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Balkrada extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats={20,5,50,5,3000,100};
	
	public Balkrada(int x,int y,int id,int width,int height) {
		super(x,y,stats[0],stats,id,width,height,"Balkrada",11);
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