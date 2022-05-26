package GameObjects.Projectiles;
/*
 * Author: Adithya Giri
 * Revs: 01
 * Date: 5/20/22
 * Notes: Takes a function implementer, and generates a path from it.
 */
import java.awt.Point;
import java.util.ArrayList;

public class FuncPath {
	FunctionImplementer func;
	int startX;
	//Takes a function implementer, and constructs the object.
	public FuncPath(FunctionImplementer func, int startX) {
		this.func = func;
		this.startX = startX;
	}
	//Gets the path using the given function implementer.
	public Path getPath(int length,int speed) {
		ArrayList<Point> path = new ArrayList<>();
		int startVal = func.doFunc(startX);
		for(int i = 1; i < length; i += speed) {
			path.add(new Point((int)speed,func.doFunc(startX+i)-func.doFunc(startX+i-1)));
		}
		return new Path(path);
	}
}
