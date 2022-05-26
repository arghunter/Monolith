/*
 * Author: Adithya Giri
 * Revs: 01
 * Date: 5/20/22
 * Notes: A logistic projectile that uses the sigmoid function to make an arrow move up and straight again. 
 */
package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LogisticProjectile extends Projectile{
	String[][] room;
	//Function implementer that maps x to y, uses sigmoid function
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return (int)(100*(1/(1+Math.pow(Math.E, -x/50.0))));
		}
	};
	// Construct the projectile using the function implementer p
	public LogisticProjectile(int x, int y,int speed,String img,int range) {
		super(new FuncPath(p,-150).getPath(y,speed),x,y,speed,img,range);
	}
	//Draw the projectile
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
