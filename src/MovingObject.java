public abstract class MovingObject extends GameObject {
	
	//Fields
	private int x;
	private int y;
	
	public MovingObject(int startX,int startY,int id) {
		super(id);
		setCoords(startX,startY);
	}
	
	//Sets the x and y coordinates to the passed values
	public void setCoords(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	//Returns the current x coordinate
	public int getX() {
		return x;
	}
	
	//Returns the current y coordinate
	public int getY() {
		return y;
	}
	
	// 8 1 2
	// 7 + 3
	// 6 5 4
	//Given a direction and velocity, moves velocity units in the direction
	//Scales to prevent strafing from being faster
	public void move(int direction,int velocity) {
		if(direction%2==0) {
			velocity=(int)(velocity/1.414213562);
		}
		
		int newX=getX();
		int newY=getY();
		if(direction==1 || direction==2 || direction==8) {
			newY--;
		}else if(direction==4 || direction==5 || direction==6) {
			newY++;
		}
		
		if(direction==2 || direction==3 || direction==4) {
			newX++;
		}else if(direction==8 || direction==7 || direction==6) {
			newX--;
		}
		setCoords(newX,newY);
	}
	
}
