package GameObjects;
import java.awt.event.ActionListener;




//Enums


public abstract class MovingObject extends GameObject  {
	
	//Fields
	private int x;
	private int y;
	private double movementDelay;// In milliseconds Time between subsequent movements
	private double lastMovement;
	private double currentMovementDelay;// Changes based on dirrection
	
	
	
	public MovingObject(int startX,int startY,int movementDelay,int id) {
		super(id);
		setMovementDelay(movementDelay);
		
		setCoords(startX,startY);
	}
	
	//Sets the x and y coordinates to the passed values
	public void setCoords(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public void setMovementDelay(double movementDelay) 
	{
		this.movementDelay=movementDelay;
		this.currentMovementDelay=movementDelay;
	}
	
	//Returns the current x coordinate
	public int getX() {
		return x;
	}
	
	//Returns the current y coordinate
	public int getY() {
		return y;
	}
	

	//Given a direction 1 unit in the direction
	//Scales to prevent strafing from being faster
	public void move(Direction direction) {
		
		if(System.currentTimeMillis()-lastMovement<this.currentMovementDelay) {
			return;
		}
		if(direction==Direction.NORTHEAST||direction==Direction.SOUTHWEST||direction==Direction.SOUTHEAST||direction==Direction.NORTHWEST) {
			//Magic number
			this.currentMovementDelay=(this.movementDelay*1.05);
		}else {
			this.currentMovementDelay=this.movementDelay;
		}
		int newX=getX();
		int newY=getY();
		if(direction==Direction.NORTH || direction==Direction.NORTHEAST || direction==Direction.NORTHWEST) {
			newY--;
		}else if(direction==Direction.SOUTHWEST || direction==Direction.SOUTHEAST || direction==Direction.SOUTH) {
			newY++;
		}
		
		if(direction==Direction.NORTHEAST || direction==Direction.SOUTHEAST || direction==Direction.EAST) {
			newX++;
		}else if(direction==Direction.SOUTHWEST || direction==Direction.NORTHWEST || direction==Direction.WEST) {
			newX--;
		}
		setCoords(newX,newY);
		lastMovement=System.currentTimeMillis();
		
	}
	
}
