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
		System.out.println(arr);
		angle = angle - Math.PI/2;
		ArrayList<Point> pointArr = new ArrayList<Point>();
		for(int i = 0; i < arr.size(); i++) {
			if(i == 0) {
				pointArr.add(new Point(arr.get(i).x+p.x,arr.get(i).y+p.y));
			} else {
				pointArr.add(new Point(arr.get(i).x+pointArr.get(pointArr.size()-1).x,arr.get(i).y+pointArr.get(pointArr.size()-1).y));
			}
		}
		System.out.println(pointArr);
		ArrayList<Point> newArr = new ArrayList<Point>();
		for(int i = 0; i < arr.size(); i++) {
			int height = pointArr.get(i).y-p.y;
			int width = pointArr.get(i).x-p.x;
			int x =(int) ((height*Math.cos(angle) - width*Math.sin(angle)) + p.x);
			int y =(int) ((height*Math.sin(angle) + width*Math.cos(angle)) + p.y);
			newArr.add(new Point(x,y));
		}
		System.out.println(newArr);
		ArrayList<Point> diffArr = new ArrayList<Point>();
		for(int i = 0; i < arr.size(); i++) {
			if(i == 0) {
				diffArr.add(new Point(newArr.get(i).x - p.x,newArr.get(i).y - p.y));
			} else {
				diffArr.add(new Point(newArr.get(i).x - newArr.get(i-1).x,newArr.get(i).y - newArr.get(i-1).y));
			}
		}
		this.arr = diffArr;
	}
}
