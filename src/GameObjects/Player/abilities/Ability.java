//Author: Armaan Gomes   
//Date: 5/20/22
//Rev: 01
//Notes: Abilities for the player in Monolith
package GameObjects.Player.abilities;

import java.awt.Graphics2D;

public abstract class Ability implements Runnable {
	
	//Fields
	private double cooldown;
	private double duration;
	private long startTime;
	private boolean isActive;
	private Thread thread;
	//Constructor
	public Ability(double duration, double cooldown) 
	{
		this.cooldown=cooldown;
		this.duration=duration;
	}
	
	//Initializes the ability
	public abstract void  init();
	//Runs the ability
	public abstract void runAbility();
	//Ends the abiltiy
	public abstract void end();
	//Draws the ability
	public abstract void draw(Graphics2D g);
	//Returns the cooldown for this ability
	public double getCooldown() {
		return cooldown;
	}

	//Returns the duration of this ability
	public double getDuration() {
		return duration;
	}

	//Returns the last start time of this ability
	public long getStartTime() {
		return startTime;
	}

	//Starts this ability
	public void start() 
	{
		if (thread == null&&System.currentTimeMillis()-startTime>(cooldown+duration)*1000) {

	         thread = new Thread (this, ""+System.currentTimeMillis());
	         thread.start ();
	         
	      }
	}
	//Returns true if this ability is active
	public boolean isActive() {
		return isActive;
	}


	@Override
	//Runs this ability
	public void run() {
		init();
		this.isActive=true;
		startTime=System.currentTimeMillis();
		System.out.println(startTime+" "+duration+" "+cooldown);
		do 
		{
			runAbility();
			try {
				thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}while(System.currentTimeMillis()-startTime<duration*1000);
		end();
		isActive=false;
		thread=null;
		
		
	}

}
