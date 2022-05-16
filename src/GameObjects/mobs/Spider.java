//Author: Peter Ferolito 
//Date: 5/12/22
//Rev: 01
//Notes: A Spider mob
package GameObjects.mobs;

import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.util.Random;

import GameObjects.Direction;

public class Spider extends Mob {
	//Fields
	//speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats={5,10,50,0,120,20};
	//Constructors
	public Spider(int x,int y,int width,int height) {
		super(x,y,stats[0],stats,width,height,"Spider",3);
	}
	


	@Override
	//Renders the spider
	public void render(Graphics2D g) {
		super.render(g);

		super.getImage().drawAnimation(g);
		
	}
}