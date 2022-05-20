//Author: Armaan Gomes
//Date: 5/9/22
//Rev: 01
//Notes: Stores an image and has helpfull methods to utilize that image 
package general;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import GameObjects.elementalDamage.StatusEffect;

public class ImageSystem {
	//Fields

	private Image picture;
	private AffineTransform transform;
	private int width;
	private int height;
	private int x;
	private int y;
	private double scaleX=1.0;
	private double scaleY=1.0;

	//Constructor
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
	



	//Returns the image that this imageSystem represents
	public Image getPicture() {
		return picture;
	}


	//Returns the scaled width of this image
	public int getWidth() {
		return (int)(width*scaleX);
	}


	//Returns the scaled height of this image
	public int getHeight() {
		return (int)(height*scaleY);
	}


	//Returns the center of this image
	public int getX() {
		return x;
	}


	//Returns the center of this image
	public int getY() {
		return y;
	}
	//Sets the scale of this image
	public void setScale(double scaleX,double scaleY) 
	{
		this.scaleX=scaleX;
		this.scaleY=scaleY;
		transform.scale(scaleX, scaleY);
		
	}
	//Returns the scale of this image
	public double getScaleX() 
	{
		return scaleX;
	}
	//Returns the scale of this image
	public double getScaleY() 
	{
		return scaleY;
	}

	//Moves this images by x,y
	public void move(int x, int y)
	{
		this.x+=x;
		this.y+=y;
	    transform.translate(x, y);
	}
	public AffineTransform getTransform() 
	{
		return transform;
	}

	
	//Sets the rotation of this image
	public void setRotation(double radians) 
	{
		transform.setToTranslation(x - width/2.0, y - height/2.0);
		transform.scale(scaleX, scaleY);
		transform.rotate(radians+Math.PI/2,width/2,height/2);
		
	}
	//Sets the rotation of this image around a given center
	public void setRotation(double radians,double centerX,double centerY) 
	{
		transform.setToTranslation(centerX, centerY);
		transform.rotate(radians+Math.PI/2,centerX,centerY);
		
	}
	//Draws this image
	public void drawImage(Graphics2D g)
	 {

			
		g.drawImage(picture, transform, null);


	    
	  
	 }






}
