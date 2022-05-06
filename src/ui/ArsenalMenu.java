package ui;

import java.awt.Point;
import java.awt.Polygon;

public class ArsenalMenu {
	Polygon shape;
	public ArsenalMenu() 
	{
		Point[] shapeCoords={new Point(1476,727),new Point(1446,799),new Point(1391,854),new Point(1319,884),new Point(1241,884),new Point(1169,854),new Point(1114,799),new Point(1084,727),new Point(1084,649),new Point(1114,577),new Point(1169,522),new Point(1241,492),new Point(1319,492),new Point(1391,522),new Point(1446,577),new Point(1476,649)};
		shape=new Polygon();
		for(int i=0;i<shapeCoords.length;i++) 
		{
			shape.addPoint(shapeCoords[i].x, shapeCoords[i].y);
		}
	}

}
