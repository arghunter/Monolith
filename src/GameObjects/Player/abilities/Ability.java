package GameObjects.Player.abilities;

import java.awt.Graphics2D;

public abstract class Ability implements Runnable {
	private double cooldown;
	private double duration;
	private long startTime;
	private boolean isActive;
	private Thread thread;
	public Ability(double duration, double cooldown) 
	{
		this.cooldown=cooldown;
		this.duration=duration;
	}
	
	
	public abstract void  init(); 
	public abstract void runAbility();
	public abstract void end();
	public abstract void draw(Graphics2D g);

	public double getCooldown() {
		return cooldown;
	}


	public double getDuration() {
		return duration;
	}


	public long getStartTime() {
		return startTime;
	}


	public void start() 
	{
		if (thread == null&&System.currentTimeMillis()-startTime>(cooldown+duration)*1000) {

	         thread = new Thread (this, ""+System.currentTimeMillis());
	         thread.start ();
	         
	      }
	}
	
	public boolean isActive() {
		return isActive;
	}


	@Override
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}while(System.currentTimeMillis()-startTime<duration*1000);
		end();
		isActive=false;
		thread=null;
		
		
	}

}
