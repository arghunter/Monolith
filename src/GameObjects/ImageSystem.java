package GameObjects;

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
	
	public ImageSystem(int x,int y,Image pic) 
	{
		picture=pic;
		this.width = picture.getWidth(null);
	    this.height = picture.getHeight(null);
	    this.transform=new AffineTransform();
	    transform.translate(x - width/2, y - height/2);
	}
	
	public void move(int x, int y)
	{
	    transform.translate(x, y);
	}
	
	public void rotate(double radians) 
	{
		transform.rotate(radians, width/2, height/2);
	}
	public void drawImage(Graphics g)
	 {
	    ((Graphics2D)g).drawImage(picture, transform, null);
	    
	  
	 }

}
