/*
 * Author: Adithya Giri
 * Revs: 01
 * Date: 5/13/22
 * Notes: A class that can check if the player collided with the walls, and prevents
 * the player from moving if he is colliding.
 */
package general;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
public class Collider {
	ArrayList<Rectangle> objects = new ArrayList<>();
	int x, y;
	int prevX, prevY;
	MovingObject object;
	public Collider(String[][] grid) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j].equals("11")) {
					int posX = 32 * j;
					int posY = 32 * i;
					objects.add(new Rectangle(posX,posY,33,33));
				}
			}
		}
	}
	public void checkCollides(Rectangle playerRect,Graphics2D g, MovingObject object){
		g.drawRect(playerRect.x, playerRect.y, playerRect.width, playerRect.height);
		for(Rectangle r : objects) {
			//.out.println(r+"    " + playerRect);
			g.drawRect(r.x, r.y, r.width, r.height);
			if(r.intersects(playerRect)) {
				System.out.println("colliding");
				object.restorePrevPosition();
			}
		}
	}
}