package GameObjects.mobs;

import java.awt.Graphics2D;

public class PottedPlant extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
		public static final int[] stats={20,1,5,0,40,60};
		//Constructor
		public PottedPlant(int x,int y) {
			super(x,y,stats[0],stats,64,64,"PottedPlant",1);
			super.dist=0;
		}
		


		@Override
		//Renders this object
		public void render(Graphics2D g) {
			super.render(g);
			super.getImage().drawAnimation(g);
			
		}

}
