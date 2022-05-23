package GameObjects.mobs;

import java.awt.Graphics2D;

public class Cactus extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
		public static final int[] stats={20,100,5,0,60,60};
		//Constructor
		public Cactus(int x,int y) {
			super(x,y,stats[0],stats,64,64,"Cactus",1);
			super.dist=0;
		}
		


		@Override
		//Renders this object
		public void render(Graphics2D g) {
			super.render(g);
			super.getImage().drawAnimation(g);
			
		}
}
