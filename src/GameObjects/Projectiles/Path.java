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
		if(iter>=arr.size()) 
		{
			iter--;
		}
		return p;
	}
	public boolean isFinished() {
		if(iter == arr.size()) {
			return true;
		}
		return false;
	}
	//Implement later
	public void rotate(Point p, double angle) {
		ArrayList<Point> newArr = new ArrayList<Point>();
		for(int i = 0; i < arr.size(); i++) {
			int height = arr.get(i).x-p.y;
			int width = arr.get(i).x-p.x;
			int x =(int) ((height*Math.cos(angle) - width*Math.sin(angle)) + p.x);
			int y =(int) ((height*Math.sin(angle) + width*Math.cos(angle)) + p.y);
			newArr.add(new Point(x,y));
		}
		this.arr = newArr;
	}
}
