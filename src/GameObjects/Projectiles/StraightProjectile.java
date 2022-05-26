/*
 * Author: Adithya Giri
 * Revs: 01
 * Date: 5/20/22
 * Notes: A straight projectile that uses the function y = 0 to make an arrow perfectly straight. 
 */
package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StraightProjectile extends Projectile{
	//Function implementer that maps x to y, uses function y = 0
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return 0;
		}
	};
	// Construct the projectile using the function implementer p
	public StraightProjectile(int x, int y,int speed,String img,int range) {
		super(new FuncPath(p,0).getPath(y,speed),x,y,speed,img,range);
	}
	//Draw the projectile
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
