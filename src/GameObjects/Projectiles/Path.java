package GameObjects.Projectiles;

import java.awt.Point;
import java.util.ArrayList;
/*
 * Author: Adithya Giri
 * Revs: 01
 * Date: 5/20/22
 * Notes: A set of points, with useful functions like rotate, that allow you to manipulate the whole path. 
 * Each point contains the amount the image needs to be offset.
 */
public class Path {
	ArrayList<Point> arr;
	int iter = 0;
	//Constructs a new point
	public Path(ArrayList<Point> arr) {
		this.arr = arr;
	}
	//Gets the next point in the projectiles path.
	public Point getNextPoint() {
		
		Point p = arr.get(iter);
		iter++;
		if(iter>=arr.size()) 
		{
			iter--;
		}
		return p;
	}
	//Returns true if the path is completed
	public boolean isFinished() {
		if(iter == arr.size()) {
			return true;
		}
		return false;
	}
	//Rotates a path around a given point to accurately move the projectile.
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
