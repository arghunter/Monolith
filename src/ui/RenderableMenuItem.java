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
		image=new ImageSystem(x+10,y+10,new ImageIcon("imgs/"+item.getName()+"/"+item.getName()+0+".png").getImage());
		if(image.getWidth()==-1) 
		{
			throw new IllegalArgumentException("Image not found");
		}
		Point[] points= {new Point(x,y),new Point(x+image.getWidth()+20,y),new Point(x+image.getWidth()+20,y+image.getHeight()+220),new Point(x,y+image.getHeight()+220)};
		button=new Button(points,new Color(0f,0f,0f,0f));
		panel.add(button);
		button.addActionListener(this);
		
		
	}
	public void draw(Graphics2D g,int JPanelX, int JPanelY) 
	{
		button.draw(g, JPanelX, JPanelY);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
