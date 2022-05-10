package GameObjects.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Balkrada extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats={20,20,50,5,40,100};
	
	public Balkrada(int x,int y,int id,int width,int height) {
		super(x,y,stats[0],stats,id,width,height,"Balkrada",22);
	}
	


	@Override
	public void render(Graphics2D g) {
		super.getImage().drawAnimation(g);
		
	}
}