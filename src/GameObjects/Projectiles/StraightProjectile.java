package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StraightProjectile extends Projectile{
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return x+1;
		}
	};
	public StraightProjectile(int x, int y,int speed) {
		super(new FuncPath(p,0).getPath(y),x,y,speed,new ImageIcon("imgs/Baklava/Baklava0.png").getImage());
	}
	public StraightProjectile(int x, int y,int speed,Image img) {
		super(new FuncPath(p,0).getPath(y),x,y,speed,img);
	}
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
