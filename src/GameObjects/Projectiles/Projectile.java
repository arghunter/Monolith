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

public class Projectile {
	Collider collider;
	Path path;
	ArrayList<Mob> entities;
	ImageSystem img;
	public Projectile(String[][] room, Path path, int x, int y, Image picture) {
		collider = new Collider(room);
		path = new Path(new ArrayList<Point>());
		img = new ImageSystem(x,y,picture);
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
		if(player.getRect().intersects(getBoundingRect())) {
			return true;
		}
		return false;
	}
}
