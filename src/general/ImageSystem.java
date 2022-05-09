package general;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class ImageSystem {
	private String name;
	private Image picture;
	private AffineTransform transform;
	private int width;
	private int height;
	private int x;
	private int y;
	private double scaleX=1.0;
	private double scaleY=1.0;
	
	public ImageSystem(int x,int y,Image pic) 
	{
		picture=pic;
		this.width = picture.getWidth(null);
	    this.height = picture.getHeight(null);
	    this.transform=new AffineTransform();
	    this.x=x;
	    this.y=y;
	    transform.translate(x - width/2, y - height/2);
	   
	}
	

	
	public String getName() {
		return name;
	}



	public Image getPicture() {
		return picture;
	}



	public int getWidth() {
		return (int)(width*scaleX);
	}



	public int getHeight() {
		return (int)(height*scaleY);
	}



	public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}

	public void setScale(double scaleX,double scaleY) 
	{
		this.scaleX=scaleX;
		this.scaleY=scaleY;
		transform.scale(scaleX, scaleY);
		
	}
	public double getScaleX() 
	{
		return scaleX;
	}
	public double getScaleY() 
	{
		return scaleY;
	}

	public void move(int x, int y)
	{
		this.x+=x;
		this.y+=y;
	    transform.translate(x, y);
	}
	
	public void setRotation(double radians) 
	{
		transform.setToTranslation(x - width/2.0, y - height/2.0);
		transform.scale(scaleX, scaleY);
		transform.rotate(radians+Math.PI/2,width/2,height/2);
		
	}
	public void setRotation(double radians,double centerX,double centerY) 
	{
		transform.setToTranslation(centerX, centerY);
		transform.rotate(radians+Math.PI/2,centerX,centerY);
		
	}
	public void drawImage(Graphics2D g)
	 {
	    g.drawImage(picture, transform, null);
	    
	  
	 }

}
