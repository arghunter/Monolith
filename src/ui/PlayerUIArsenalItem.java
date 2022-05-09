package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import general.Constants;
import general.ImageSystem;
import input.MouseInputParser;

public class PlayerUIArsenalItem {
	private Item item;
	private ImageSystem image;
	private int x;
	private int y;
	public PlayerUIArsenalItem(Item item,int x,int y) 
	{
		this.item=item;

		this.x=x;
		this.y=y;
		BufferedImage img;
		ImageIcon iconImg=null;
		if(item!=null) 
		{
			iconImg=(new ImageIcon("imgs/"+item.getName().replace(" ", "")+"/"+item.getName().replace(" ", "")+0+".png"));

			if(iconImg.getIconWidth()==-1) 
			{
				throw new IllegalArgumentException("Image not found "+ item.getName());
			}
			img=new BufferedImage(iconImg.getIconWidth(),iconImg.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
		}else 
		{
			img=new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
		}
	
		Graphics2D g=img.createGraphics();
		g.setColor(new Color(0.2f,0.2f,0.2f,0.2f));
		g.fillRect(0,0,img.getWidth(),img.getHeight());

		if(item!=null) 
		{
			g.drawImage(iconImg.getImage(), 0, 0, null);
		}
		
		image=new ImageSystem(x+5,y+5,img); 
		image.move(image.getWidth()/2, image.getHeight()/2);
		image.setScale(32.0/image.getWidth(), 32.0/image.getHeight());
		

		
	}
	
	public void draw(Graphics2D g) 
	{
		image.drawImage(g);
	}
	

}
