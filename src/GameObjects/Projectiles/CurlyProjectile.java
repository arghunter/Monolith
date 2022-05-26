/*
 * Author: Adithya Giri
 * Revs: 01
 * Date: 5/20/22
 * Notes: A curly projectile that uses the sine function to make an arrow move up and down. 
 */
package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class CurlyProjectile extends Projectile{
	String[][] room;
	//Function implementer that maps x to y, uses sine function
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return (int)(Math.sin(x/100.0) * 100);
		}
	};
	// Construct the projectile using the function implementer p
	public CurlyProjectile(int x, int y,int speed,String img,int range) {
		super(new FuncPath(p,0).getPath(y,speed),x,y,speed,img,range);
	}
	//Draw the projectile
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
