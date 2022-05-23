//Author: Peter Ferolito  
//Date: 5/16/22
//Rev: 01
//Notes: IceDrake mob
package GameObjects.mobs;

import java.awt.Graphics2D;

public class IceDrake extends Mob {
	//speed, damage, health, armor, attackspeed, attack range
		public static final int[] stats={20,20,500,5,40,100};
		//Constructor
		public IceDrake(int x,int y) {
			super(x,y,stats[0],stats,640,560,"IceDrake",1);
		}
		


		@Override
		//Renders this object
		public void render(Graphics2D g) {
			super.render(g);
			super.getImage().drawAnimation(g);
			
		}
}
