package GameObjects.mobs;

import java.awt.Graphics2D;

public class DamagePlant extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
		public static final int[] stats={20,5,10,0,30,60};
		public static final int xpDropped=1;
		//Constructor
		public DamagePlant(int x,int y) {
			super(x,y,stats[0],stats,64,64,"DamagePlant",1,xpDropped);
			super.dist=0;
		}
		


		@Override
		//Renders this object
		public void render(Graphics2D g) {
			super.render(g);
			super.getImage().drawAnimation(g);
			
		}
}
