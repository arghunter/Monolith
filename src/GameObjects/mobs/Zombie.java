package GameObjects.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Zombie extends Mob {
	//speed, damage, health, armor, attackspeed,attack range
	public static final int[] stats={40,10,10,0,60,90};
	
	public Zombie(int x,int y,int id,int width,int height) {
		super(x,y,stats[0],stats,id,width,height,"Zombie",17);
	}
	


	@Override
	public void render(Graphics2D g) {
		super.getImage().drawAnimation(g);
		
	}
}