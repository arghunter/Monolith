package GameObjects.mobs;

import java.awt.Graphics2D;

public class HealthPlant extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
		public static final int[] stats={20,1,5000,5,40,60};
		//Constructor
		public HealthPlant(int x,int y) {
			super(x,y,stats[0],stats,64,64,"HealthPlant",1);
			super.dist=0;
		}
		


		@Override
		//Renders this object
		public void render(Graphics2D g) {
			super.render(g);
			super.getImage().drawAnimation(g);
			
		}
}
