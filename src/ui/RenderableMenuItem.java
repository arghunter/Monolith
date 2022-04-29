package ui;

import java.awt.Point;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import GameObjects.Player.items.Item;
import general.ImageSystem;

public class RenderableMenuItem implements ActionListener {
	
	ImageSystem image;
	Item item;
	Button button;
	private int x;
	private int y;
	
	public RenderableMenuItem(Item item,int x, int y,JPanel panel) 
	{
		this.item=item;
		this.x=x;
		this.y=y;

		image=new ImageSystem(x+5,y+5,new ImageIcon("imgs/"+item.getName()+"/"+item.getName()+0+".png").getImage());
		if(image.getWidth()==-1) 
		{
			throw new IllegalArgumentException("Image not found");
		}
		x-=image.getWidth()/2;
		y-=image.getHeight()/2;
		Point[] points= {new Point(x,y),new Point(x+image.getWidth()+10,y),new Point(x+image.getWidth()+10,y+image.getHeight()+25),new Point(x,y+image.getHeight()+25)};
		button=new Button(points,new Color(0f,0f,1f,1f));
		panel.add(button);
		button.addActionListener(this);
		
		
	}
	public void draw(Graphics2D g,int JPanelX, int JPanelY) 
	{
		button.draw(g, JPanelX, JPanelY);
		image.drawImage(g);
		g.drawString(item.getName(),x+5 ,image.getHeight()+image.getY()+10);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Here");
	}

}
