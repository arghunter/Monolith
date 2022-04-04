import processing.core.PApplet;

public class Player extends MovingObject {
	
	//Note the speed will come from skill tree
	public Player(int x, int y, int id,PApplet p) {
		super(x, y,5 ,id,p);
		
	} 

	@Override
	public void draw() {
		
		super.getPApplet().ellipse(getX(), getY(), getX(), getY());
		
	}


	

}
