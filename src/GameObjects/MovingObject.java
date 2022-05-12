package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import general.AnimationSystem;
import general.ImageSystem;



public abstract class MovingObject extends GameObject {

	// Fields
	private int x;
	private int y;
	private int pastX;
	private int pastY;
	private double movementDelay;// In milliseconds Time between subsequent movements
	private double lastMovement;
	private double currentMovementDelay;// Changes based on dirrection
	protected int health;
	private boolean isDead=false;
	private AnimationSystem image;
	private double angle;
	protected int dist=1;

	public MovingObject(int startX, int startY, int movementDelay, int id, int width, int height, String name, int numFrames) {
		super(id, width, height);
		setMovementDelay(movementDelay);
		setCoords(startX, startY);
		this.image =new AnimationSystem(x,y,movementDelay*6,name,numFrames);
		System.out.println(this.image);
		this.health = Integer.MAX_VALUE;
	}
	public MovingObject(int startX, int startY, int movementDelay, int id, int width, int height, String name, int numFrames,int health) {
		super(id, width, height);
		setMovementDelay(movementDelay);
		setCoords(startX, startY);
		this.image =new AnimationSystem(x,y,movementDelay*6,name,numFrames);
		System.out.println(this.image);
		this.health = health;
	}
	public void takeDamage(int damage) {
		health -= damage;
		if(health<=0) 
		{
			this.setDead(true);
		}
	}
	// Sets the x and y coordinates to the passed values
	public void setCoords(int x, int y) {
		this.pastX = this.x;
		this.pastY = this.y;
		this.x = x;
		this.y = y;

	}

	public void setMovementDelay(double movementDelay) {
		this.movementDelay = movementDelay;
		this.currentMovementDelay = movementDelay;
	}
	public void restorePrevPosition() {
		image.move(pastX-x,pastY-y);
		this.setCoords(pastX, pastY);
	}
	// Returns the current x coordinate
	public int getX() {
		return x;
	}
	//Finish
	public Rectangle getRect() {
		return new Rectangle(x-getWidth()/2,y-getHeight()/2,getWidth(),getHeight());
	}
	// Returns a refrence to the AnimationSystem in this class.
	public AnimationSystem getImage() {
		return image;
	}

	// Returns the current y coordinate
	public int getY() {
		return y;
	}
	
	public int getCenterX() {
		return x+(getWidth()/2);
	}
	public int getCenterY() {
		return y+(getHeight()/2);
	}
	public void refillLastPos(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(pastX, pastY, getWidth(), getHeight());
		this.pastX = x;
		this.pastY = y;

	}

	public void updateAngle(double pointX, double pointY) {
		double angle = Math.atan((double) ((pointY - getY()) / (double) (pointX - getX())));
		if (pointX < getX()) {
			angle += Math.PI;
		}
		this.angle=angle;
		getImage().setRotation(angle);
	}

	public double getAngle() {
		return angle;
	}

	// Given a direction 1 unit in the direction
	// Scales to prevent strafing from being faster
	public void move(Direction direction) {
		int n=dist;
		if (direction == Direction.NORTHEAST || direction == Direction.SOUTHWEST || direction == Direction.SOUTHEAST
				|| direction == Direction.NORTHWEST) {
			// Magic number
			this.currentMovementDelay = (this.movementDelay * 1.05);
		} else {
			this.currentMovementDelay = this.movementDelay;
		}
		if (System.currentTimeMillis() - lastMovement < this.currentMovementDelay) {
			return;
		}

		int newX = getX();
		int newY = getY();
		if (direction == Direction.NORTH || direction == Direction.NORTHEAST || direction == Direction.NORTHWEST) {
			newY-=n;
			image.move(0, -n);
		} else if (direction == Direction.SOUTHWEST || direction == Direction.SOUTHEAST
				|| direction == Direction.SOUTH) {
			newY+=n;
			image.move(0, n);
		}

		if (direction == Direction.NORTHEAST || direction == Direction.SOUTHEAST || direction == Direction.EAST) {
			newX+=n;
			image.move(n, 0);
		} else if (direction == Direction.SOUTHWEST || direction == Direction.NORTHWEST
				|| direction == Direction.WEST) {
			newX-=n;
			image.move(-n, 0);
		}
		setCoords(newX, newY);
		lastMovement = System.currentTimeMillis();

	}
	
	public void setCoordsMove(int x,int y) {
		image.move(x-this.x,y-this.y);
		this.x=x;
		this.y=y;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

}
