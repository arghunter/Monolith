package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LogisticProjectile extends Projectile{
	String[][] room;
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return (int)(100*(1/(1+Math.pow(Math.E, -x/50.0))));
		}
	};
	public LogisticProjectile(int x, int y,int speed,int range) {
		super(new FuncPath(p,-150).getPath(y,speed),x,y,speed,new ImageIcon("imgs/Baklava/Baklava0.png").getImage(), range);
	}
	public LogisticProjectile(int x, int y,int speed,Image img,int range) {
		super(new FuncPath(p,-150).getPath(y,speed),x,y,speed,img,range);
	}
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
