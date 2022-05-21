package GameObjects.mobs;

import java.awt.Graphics2D;

public class Hobgoblin extends Mob implements RangedMob {
	//speed, damage, health, armor, attackspeed, attack range
		public static final int[] stats={20,38,250,5,20,500};
		//Constructor
		public Hobgoblin(int x,int y) {
			super(x,y,stats[0],stats,64,64,"Hobgoblin",1);
			super.dist=1;
		}
		


		@Override
		//Renders this object
		public void render(Graphics2D g) {
			super.render(g);
			super.getImage().drawAnimation(g);
			
		}



		@Override
		public void fireProjectile() {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void renderProjectiles(Graphics2D g) {
			// TODO Auto-generated method stub
			
		}
}
