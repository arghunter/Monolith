package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
import GameObjects.mobs.Mob;
import general.Collider;
import general.ImageSystem;
import render.Adventure;

public class Projectile  {
	private Path path;
	private ImageSystem img;
	private int speed;
	private double lastFired=System.currentTimeMillis();
    private ActionListener[] actionListeners=new ActionListener[0];

	public Projectile(Path path, int x, int y,int speed, Image picture) {
		this.path = path;
		img = new ImageSystem(x,y,picture);
		this.speed=speed;
	}
	public void moveToNext() {
		int numTimes=(int)((System.currentTimeMillis()-lastFired)/speed);
		lastFired=System.currentTimeMillis();
		for(int j=0;j<numTimes;j++) 
		{
			
			Point p = path.getNextPoint();
			img.move(p.x, p.y);
			for(Mob m:Adventure.getMobs()) 
			{
				if(this.collidingWithMob(m)) 
				{
					for(ActionListener a:actionListeners) 
					{
						a.actionPerformed(new ActionEvent(m,453798,"Hit"));
					}
					this.img=null;
					return;
				}
			}
		}

	}
	public Rectangle getBoundingRect() {
		int x = img.getX();
		int y = img.getY();
		int width = img.getWidth();
		int height = img.getHeight();
		return new Rectangle(x-width/2,y-height/2,width,height);
	}
	
	public void draw(Graphics2D g) {
		if(img!=null) 
		{
			img.drawImage(g);
			moveToNext();
		}

	}
	public boolean collidingWithPlayer(Player player) {
		return player.getRect().intersects(getBoundingRect());

	}
	public boolean collidingWithMob(Mob m) 
	{
		return m.getRect().intersects(getBoundingRect());
	}
	public int getX() {
		return img.getX();
	}
	public int getY() {
		return img.getY();
	}
	public ImageSystem getImage() 
	{
		return img;
	}
    //Adds an action listener to this object
    public void addActionListener(ActionListener listener) 
    {
    	ActionListener[] temp=new ActionListener[actionListeners.length+1];
    	for(int i=0;i<actionListeners.length;i++) 
    	{
    		temp[i]=actionListeners[i];
    	}
    	temp[actionListeners.length]=listener;
    	actionListeners=temp;
    }


}
