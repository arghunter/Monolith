package GameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Zombie extends Mob {
	//speed, damage, health, armor, attackspeed
	public static int[] stats={40,2,10,0,2000};
	
	public Zombie(int x,int y,int id,int width,int height) {
		super(x,y,stats[0],stats,id,width,height,"zombie.png");
	}
	
	public void move(Direction moveDirection) {
		super.move(moveDirection);
	}

	@Override
	public void render(Graphics2D g) {
		super.getImage().drawImage(g);
		
	}
}