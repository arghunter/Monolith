package GameObjects.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Zombie extends Mob {
	//speed, damage, health, armor, attackspeed,attack range
	public static int[] stats={40,2,10,0,2000,40};
	
	public Zombie(int x,int y,int id,int width,int height) {
		super(x,y,stats[0],stats,id,width,height,"Zombie",17);
	}
	
	public void move(Direction moveDirection) {
		super.move(moveDirection);
	}

	@Override
	public void render(Graphics2D g) {
		super.getImage().drawAnimation(g);
		
	}
}