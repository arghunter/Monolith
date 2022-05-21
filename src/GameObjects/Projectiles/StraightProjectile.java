package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StraightProjectile extends Projectile{
	String[][] room;
	final static FunctionImplementer p = new FunctionImplementer() {
		@Override
		public int doFunc(int x) {
			return (int) Math.random() * 100;
		}
	};
	public StraightProjectile(int x, int y, String[][] room) {
		super(room,new FuncPath(p,0).getPath(y),x,y,new ImageIcon("imgs/Baklava/Baklava0.png").getImage());
		this.room = room;
	}
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
