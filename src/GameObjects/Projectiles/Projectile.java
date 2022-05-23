package GameObjects.Projectiles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
import GameObjects.mobs.Mob;
import general.Collider;
import general.Constants;
import general.ImageSystem;
import render.Adventure;
import render.Main;
import render.TextureGenerator;

public class Projectile  {
	private Path path;
	private ImageSystem img;
	private int speed;
	private double lastFired=System.currentTimeMillis();
    private ActionListener[] actionListeners=new ActionListener[0];
    private Polygon bounds;
    private double angle;
    private int range;
    private int orgX;
    private int orgY;

	public Projectile(Path path, int x, int y,int speed, Image picture,int range) {
		this.path = path;
		img = new ImageSystem(x,y,picture);
		this.speed=speed;
		this.range=range;
		this.orgX=x;
		this.orgY=y;
	}
	public void moveToNext() {
		int numTimes=(int)((System.currentTimeMillis()-lastFired)/speed);
		lastFired=System.currentTimeMillis();
		for(int j=0;j<numTimes;j++) 
		{
			
			Point p = path.getNextPoint();
			img.setRotation(0);
			img.move(p.x,p.y);
			img.setRotation(angle);
			if(Math.sqrt(Math.pow((this.getX()-orgX),2)+Math.pow((this.getY()-orgY),2))>range-2*img.getHeight()||this.getX()< ((int) Main.WIDTH - (Constants.ROOMSIZEX * 32)) / 2+64||this.getY()<((int) Main.HEIGHT - (Constants.ROOMSIZEY * 32)) / 2+64||this.getX()> ((int) Main.WIDTH - (Constants.ROOMSIZEX * 32)) / 2+TextureGenerator.calcWidth(1)-64||this.getY()>((int) Main.HEIGHT - (Constants.ROOMSIZEY * 32)) / 2+TextureGenerator.calcHeight(1)-64) 
			{
				this.img=null;
				return;
			}
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
			if(this.collidingWithPlayer(Adventure.getPlayer())) 
			{
				for(ActionListener a:actionListeners) 
				{
					a.actionPerformed(new ActionEvent(Adventure.getPlayer(),453798,"Hit"));
				}
				this.img=null;
				return;
			}
		}

	}
	public Polygon getBounds() {
		int x = img.getX();
		int y = img.getY();
		int width = img.getWidth();
		int height = img.getHeight();
		return rotateBounds(new Rectangle(x-width/2,y-height/2,width,height),new Point(getX(),getY()),angle);
	}
	public Polygon rotateBounds(Rectangle r, Point p, double angle) {
		int[] currX = new int[4];
		int[] currY = new int[4];
		currX[0] = r.x;
		currY[0] = r.y;
		currX[1] = r.x + r.width;
		currY[1] = r.y;
		currX[3] = r.x;
		currY[3] = r.y + r.height;
		currX[2] = r.x + r.width;
		currY[2] = r.y + r.height;
		for (int i = 0; i < 4; i++) {
			int height = currY[i] - p.y;
			int width = currX[i] - p.x;
			currX[i] = (int) ((height * Math.cos(angle) - width * Math.sin(angle)) + p.x);
			currY[i] = (int) ((height * Math.sin(angle) + width * Math.cos(angle)) + p.y);
		}
		return new Polygon(currX, currY, 4);
	}
	
	public void draw(Graphics2D g) {
		if(img!=null) 
		{
			img.drawImage(g);
			moveToNext();
		}

	}
	public void rotate(double radians) 
	{
		path.rotate(new Point(Adventure.getPlayer().getX(),Adventure.getPlayer().getY()), radians);
		img.setRotation(radians);
		this.angle=radians;
		
	}
	public boolean collidingWithPlayer(Player player) {
		return getBounds().intersects(((Rectangle2D)player.getRect()));

	}
	public boolean collidingWithMob(Mob m) 
	{
		return getBounds().intersects((Rectangle2D)m.getRect());
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
