package general;

import java.awt.Rectangle;
import java.util.ArrayList;
public abstract class Collider {
	ArrayList<Rectangle> objects = new ArrayList<>();
	int x, y;
	int prevX, prevY;
	public abstract void move(int x, int y);
	public Collider(String[][] grid) {
		String[][] deepCopy = new String[grid.length][grid[0].length];
		int rectWidth = 32, rectHeight = 32;
		int rectType = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j].equals("11")) {
					if(j != 0 && grid[i][j-1].equals("11") && rectType != 2) {
						rectWidth+=32;
						rectType = 1;
					}
					if (i != 0 && grid[i][j-1].equals("11") && rectType != 1) {
						rectHeight += 32;
						rectType = 2;
					}
				} else {
					if(j == grid[i].length-1 || !grid[i][j+1].equals("11")) {
						int currX = j*32;
						int currY = i * 32; 
						objects.add(new Rectangle(currX - rectWidth,currY - rectHeight,rectWidth,rectHeight));
						rectWidth = 32;
						rectHeight = 32;
						rectType = 0;
					}
				}
			}
		}
	}
	public String[][] deepCopy(String[][] grid) {
		String[][] deepCopy = new String[grid.length][grid[0].length];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				deepCopy[i][j] = grid[i][j];
			}
		}
		return deepCopy;
	}
	public void checkCollides(Rectangle playerRect){
		for(Rectangle r : objects) {
			if(r.intersects(playerRect)) {
				x=prevX;
				y=prevY;
			}
		}
	}
}