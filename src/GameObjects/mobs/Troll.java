package GameObjects.mobs;

import java.awt.Graphics2D;

public class Troll extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats={50,76,1000,50,20,100};
	//Constructor
	public Troll(int x,int y) {
		super(x,y,stats[0],stats,128,128,"Troll",1);
	}
	


	@Override
	//Renders this object
	public void render(Graphics2D g) {
		super.render(g);
		super.getImage().drawAnimation(g);
		
	}
}
