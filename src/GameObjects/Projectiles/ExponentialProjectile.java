/*
 * Author: Adithya Giri
 * Revs: 01
 * Date: 5/20/22
 * Notes: A exponetial projectile that uses the function 2^x/100 to make an arrow move in an exponential path. 
 */
package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ExponentialProjectile extends Projectile{
	//Function implementer that maps x to y, uses exponential function
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return (int)(Math.pow(2,x/200.0));
		}
	};
	// Construct the projectile using the function implementer p
	public ExponentialProjectile(int x, int y,int speed,String img,int range) {
		super(new FuncPath(p,0).getPath(y,speed),x,y,speed,img,range);
	}
	//Draw the projectile
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
