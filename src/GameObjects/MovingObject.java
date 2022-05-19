//Author: Peter Ferolito 
//Date: 5/12/22
//Rev: 01
//Notes: A extension of game object that is built for movement.
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
	private double currentMovementDelay;// Changes based on direction

	protected int health;
	private boolean isDead=false;
	private AnimationSystem image;
	private double angle;
	protected int dist=2;
	//Constructor
	public MovingObject(int startX, int startY, int movementDelay, int width, int height, String name, int numFrames) {
		super(width, height);
		setMovementDelay(movementDelay);
		setCoords(startX, startY);
		this.image =new AnimationSystem(x,y,movementDelay*6,name);
		System.out.println(this.image);
		this.health = Integer.MAX_VALUE;
	}
	//Alternate constructor
	public MovingObject(int startX, int startY, int movementDelay, int width, int height, String name, int numFrames, int health) {
		super(width, height);
		setMovementDelay(movementDelay);
		setCoords(startX, startY);
		this.image =new AnimationSystem(x,y,movementDelay*6,name);
		System.out.println(this.image);
		this.health = health;
	}
	//Take damage
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
	public void setWithoutPrev(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//Sets the movement delay high delay low speed
	public void setMovementDelay(double movementDelay) {
		this.movementDelay = movementDelay;
		this.currentMovementDelay = movementDelay;
	}
	//Restores the previous position
	public void restorePrevPosition() {
		image.move(pastX-x,pastY-y);
		this.setWithoutPrev(pastX, pastY);
	}
	// Returns the current x coordinate
	public int getX() {
		return x;
	}
	//Returns the bounding rectangle of this object
	public Rectangle getRect() {
		return new Rectangle(x-getWidth()/2,y-getHeight()/2,getWidth(),getHeight());
	}
	// Returns a reference to the AnimationSystem in this class.
	public AnimationSystem getImage() {
		return image;
	}

	// Returns the current y coordinate
	public int getY() {
		return y;
	}


	//Updates this angle so that it faces 
	public void updateAngle(double pointX, double pointY) {
		double angle = Math.atan((double) ((pointY - getY()) / (double) (pointX - getX())));
		if (pointX < getX()) {
			angle += Math.PI;
		}
		this.angle=angle;
		getImage().setRotation(angle);
	}
	//Returns the angle that this object is facing
	public double getAngle() {
		return angle;
	}

	// Given a direction 1 unit in the direction
	// Scales to prevent strafing from being faster
	public void move(Direction direction) {
		int n=dist;
		if (System.currentTimeMillis() - lastMovement < this.currentMovementDelay) {
			return;
		}
		if (direction == Direction.NORTHEAST || direction == Direction.SOUTHWEST || direction == Direction.SOUTHEAST
				|| direction == Direction.NORTHWEST) {
			// Magic number
			this.currentMovementDelay = (this.movementDelay * 1.05);
		} else {
			this.currentMovementDelay = this.movementDelay;
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
	//Sets this objects coordinates
	public void setCoordsMove(int x,int y) {
		image.move(x-this.x,y-this.y);
		this.x=x;
		this.y=y;
	}
	//Returns isDead
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	public void setCurrentMovementDelay(double currentMovementDelay) {
		this.currentMovementDelay = currentMovementDelay;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}

}
