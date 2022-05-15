//Author: Peter Ferolito 
//Date: 5/12/22
//Rev: 01
//Notes: A zombie mob
package GameObjects.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Zombie extends Mob {
	//Fields
	//speed, damage, health, armor, attackspeed,attack range
	public static final int[] stats={10,10,10,0,60,90};
	//Constructor
	public Zombie(int x,int y,int width,int height) {
		super(x,y,stats[0],stats,width,height,"Zombie",17);
	}
	


	@Override
	//Renders this zombie
	public void render(Graphics2D g) {
		super.getImage().drawAnimation(g);
		
	}
}