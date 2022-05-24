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
	public static final int[] stats={20,200,2000,300,30,90};
	public static final int xpDropped=400;
	//Constructor
	public Balkrada(int x,int y) {
		super(x,y,stats[0],stats,96,187,"Balkrada",22,xpDropped);
	}
	


	@Override
	//Renders this object
	public void render(Graphics2D g) {
		super.render(g);
		super.getImage().drawAnimation(g);
		
	}
}