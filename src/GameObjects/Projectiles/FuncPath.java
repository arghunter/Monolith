package GameObjects.Projectiles;

import java.awt.Point;
import java.util.ArrayList;

public class FuncPath {
	FunctionImplementer func;
	int startX;
	public FuncPath(FunctionImplementer func, int startX) {
		this.func = func;
		this.startX = startX;
	}
	public Path getPath(int length) {
		ArrayList<Point> path = new ArrayList<>();
		int startVal = func.doFunc(startX);
		for(int i = 0; i < length; i++) {
			path.add(new Point(i,func.doFunc(startX+i)));
		}
		return new Path(path);
	}
}
