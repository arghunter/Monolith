package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StraightProjectile extends Projectile{
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return 0;
		}
	};

	public StraightProjectile(int x, int y,int speed,String img,int range) {
		super(new FuncPath(p,0).getPath(y,speed),x,y,speed,img,range);
	}
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
