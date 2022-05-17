//Author: Peter Ferolito  
//Date: 5/12/22
//Rev: 01
//Notes: Balkrada mob
package GameObjects.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Balkrada extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats={20,20,500,5,40,100};
	//Constructor
	public Balkrada(int x,int y,int width,int height) {
		super(x,y,stats[0],stats,width,height,"Balkrada",22);
	}
	


	@Override
	//Renders this object
	public void render(Graphics2D g) {
		super.render(g);
		super.getImage().drawAnimation(g);
		
	}
}