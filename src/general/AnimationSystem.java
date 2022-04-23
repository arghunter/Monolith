package general;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class AnimationSystem {
	private long delay;
	private long lastFrame=System.currentTimeMillis();
	private String name;
	private int frameNumber=0;
	private int numFrames;
	private ImageSystem[] pics;
	private int x;
	private int y;
	
	public AnimationSystem(int x,int y,long delay,String name,int numFrames) 
	{
		this.delay=delay;
		this.name=name;
		this.numFrames=numFrames;
		this.x=x;
		this.y=y;
		pics= new ImageSystem[numFrames];
		for(int i=0;i<pics.length;i++) 
		{
			pics[i]=new ImageSystem(x,y,new ImageIcon("imgs/"+name+"/"+name+i+".png").getImage());
		}
	}
	public void move(int x,int y) 
	{
		for(int i=0;i<pics.length;i++) 
		{
			pics[i].move(x, y);
		}
		this.x+=x;
		this.y+=y;
	}
	public void setRotation(double radians) 
	{
		for(int i=0;i<pics.length;i++) 
		{
			pics[i].setRotation(radians);
		}
	}
	public void setRotation(double radians,double centerX,double centerY) 
	{
		for(int i=0;i<pics.length;i++) 
		{
			pics[i].setRotation(radians,centerX,centerY);
		}
	}
	
	public void drawAnimation(Graphics2D g) 
	{
		if(System.currentTimeMillis()-this.lastFrame>=this.delay&&numFrames>1) 
		{
			frameNumber++;
			if(frameNumber>=numFrames) 
			{
				frameNumber=0;
			}
		}
	    pics[frameNumber].drawImage(g);

		
	}
	



	

}
