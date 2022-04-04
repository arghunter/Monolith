import processing.core.PApplet;

public class Player extends MovingObject {

	public Player(int x, int y, int id,PApplet p) {
		super(x, y, id,p);
		
	}

	@Override
	public void draw() {
		
		super.getPApplet().ellipse(getX(), getY(), getX(), getY());
		
	}


	

}
