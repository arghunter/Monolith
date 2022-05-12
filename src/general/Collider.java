package general;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import GameObjects.Player.Player;
public class Collider {
	ArrayList<Rectangle> objects = new ArrayList<>();
	int x, y;
	int prevX, prevY;
	Player player;
	public Collider(String[][] grid, Player player) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j].equals("11")) {
					int posX = 32 * j;
					int posY = 32 * i;
					objects.add(new Rectangle(posX,posY,33,33));
				}
			}
		}
		this.player = player;
	}
	public void checkCollides(Rectangle playerRect,Graphics2D g){
		g.drawRect(playerRect.x, playerRect.y, playerRect.width, playerRect.height);
		for(Rectangle r : objects) {
			//.out.println(r+"    " + playerRect);
			g.drawRect(r.x, r.y, r.width, r.height);
			if(r.intersects(playerRect)) {
				System.out.println("colliding");
				player.restorePrevPosition();
			}
		}
	}
}