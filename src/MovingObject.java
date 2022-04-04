import java.awt.event.ActionListener;


import processing.core.PApplet;

//Enums
enum Direction
{
	NORTH,
	SOUTH,
	EAST,
	WEST,
	NORTHWEST,
	SOUTHWEST,
	SOUTHEAST,
	NORTHEAST
	
	
}

public abstract class MovingObject extends GameObject  {


	
	//Fields
	private int x;
	private int y;
	private int movementDelay;// In milliseconds Time between subsequent movements
	private long lastMovement;
	private int currentMovementDelay;// Changes based on dirrection
	
	
	
	public MovingObject(int startX,int startY,int movementDelay,int id,PApplet p) {
		super(id,p);
		setMovementDelay(movementDelay);
		
		setCoords(startX,startY);
	}
	
	//Sets the x and y coordinates to the passed values
	public void setCoords(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public void setMovementDelay(int movementDelay) 
	{
		this.movementDelay=movementDelay;
		this.currentMovementDelay=currentMovementDelay;
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
			this.currentMovementDelay=(int)(this.movementDelay*1.414213562);
		}else 
		{
			this.currentMovementDelay=this.movementDelay;
		}
		
		int newX=getX();
		int newY=getY();
		if(direction==Direction.NORTH || direction==Direction.NORTHEAST || direction==Direction.NORTHWEST) {
			newY--;
		}else if(direction==Direction.SOUTHWEST || direction==Direction.SOUTHEAST || direction==Direction.SOUTH) {
			newY++;
		}
		
		if(direction==Direction.NORTHEAST || direction==Direction.NORTHWEST || direction==Direction.EAST) {
			newX++;
		}else if(direction==Direction.SOUTHWEST || direction==Direction.NORTHWEST || direction==Direction.WEST) {
			newX--;
		}
		setCoords(newX,newY);
		lastMovement=System.currentTimeMillis();
		
	}
	
}
