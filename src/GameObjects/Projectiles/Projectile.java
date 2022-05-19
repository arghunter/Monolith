package GameObjects.Projectiles;

import java.awt.Point;
import java.util.ArrayList;

import GameObjects.mobs.Mob;
import general.Collider;

public class Projectile {
	Collider collider;
	Path path;
	ArrayList<Mob> entities;
	public Projectile(String[][] room, Path path) {
		collider = new Collider(room);
		path = new Path(new ArrayList<Point>());
	}
	public void draw() {
		
	}
	
}
