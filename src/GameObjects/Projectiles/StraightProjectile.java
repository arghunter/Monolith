package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StraightProjectile {
	String[][] room;
	Projectile projectile;
	public StraightProjectile(int x, int y) {
		FunctionImplementer p = new FunctionImplementer() {
			@Override
			public int doFunc(int x) {
				return (int) Math.random() * 10;
			}
		};
		FuncPath funcPath = new FuncPath(p, 0);
		Projectile projectile= new Projectile(room,funcPath.getPath(y),x,y,new ImageIcon("imgs/Baklava0.png").getImage());
		this.projectile = projectile;
	}
	public void setRoom(String[][] room) {
		this.room = room;
	}
	public void draw(Graphics2D g) {
		projectile.draw(g);
	}
}
