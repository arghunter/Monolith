package GameObjects.Projectiles;

import java.awt.Point;
import java.util.ArrayList;

public class Path {
	ArrayList<Point> arr;
	int iter = 0;
	public Path(ArrayList<Point> arr) {
		this.arr = arr;
	}
	public Point getNextPoint() {
		Point p = arr.get(iter);
		iter++;
		return p;
	}
	public boolean isFinished() {
		if(iter == arr.size()) {
			return true;
		}
		return false;
	}
}
