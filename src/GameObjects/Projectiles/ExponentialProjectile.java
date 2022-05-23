package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ExponentialProjectile extends Projectile{
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return (int)(Math.pow(2,x/200.0));
		}
	};
	public ExponentialProjectile(int x, int y,int speed,int range) {
		super(new FuncPath(p,0).getPath(y,speed),x,y,speed,new ImageIcon("imgs/Baklava/Baklava0.png").getImage(),range);
	}
	public ExponentialProjectile(int x, int y,int speed,Image img,int range) {
		super(new FuncPath(p,0).getPath(y,speed),x,y,speed,img,range);
	}
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
