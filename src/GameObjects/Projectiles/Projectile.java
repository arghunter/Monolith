package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
import GameObjects.mobs.Mob;
import general.Collider;
import general.ImageSystem;

public class Projectile implements Runnable {
	private Path path;
	private ImageSystem img;
	private int speed;
	private Thread thread;
	public Projectile(Path path, int x, int y,int speed, Image picture) {
		this.path = path;
		img = new ImageSystem(x,y,picture);
		this.speed=speed;
		start();
	}
	public void moveToNext() {
		Point p = path.getNextPoint();
		img.move(p.x, p.y);
	}
	public Rectangle getBoundingRect() {
		int x = img.getX();
		int y = img.getY();
		int width = img.getWidth();
		int height = img.getHeight();
		return new Rectangle(x-width/2,y-height/2,width,height);
	}
	
	public void draw(Graphics2D g) {
		img.drawImage(g);
	}
	public boolean collidingWithPlayer(Player player) {
		return player.getRect().intersects(getBoundingRect());

	}
	public boolean collidingWithMob(Mob m) 
	{
		return m.getRect().intersects(getBoundingRect());
	}
	public int getX() {
		return img.getX();
	}
	public int getY() {
		return img.getY();
	}
	public void start() 
	{
		if (thread == null) {
	         thread = new Thread (this, ""+System.currentTimeMillis());
	         thread.start ();
	      }
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) 
		{
			moveToNext();
			try {
				thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
