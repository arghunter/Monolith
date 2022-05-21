package GameObjects.mobs;

import java.awt.Graphics2D;

public class Shadow extends Mob{
	//speed, damage, health, armor, attackspeed, attack range
		public static final int[] stats={20,38,250,5,20,100};
		//Constructor
		public Shadow(int x,int y) {
			super(x,y,stats[0],stats,64,64,"Shadow",1);
		}
		


		@Override
		//Renders this object
		public void render(Graphics2D g) {
			super.render(g);
			super.getImage().drawAnimation(g);
			
		}

}
