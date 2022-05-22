package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LogisticProjectile extends Projectile{
	String[][] room;
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return (int)(500*(1/(1+Math.pow(Math.E, -x/50.0))));
		}
	};
	public LogisticProjectile(int x, int y,int speed) {
		super(new FuncPath(p,-150).getPath(y),x,y,speed,new ImageIcon("imgs/Baklava/Baklava0.png").getImage());
		this.room = room;
	}
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
