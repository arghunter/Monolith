package ui;

import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
		image.move(image.getWidth()/2, image.getHeight()/2);
		int tx=x;
		int ty=y;

		Point[] points= {new Point(tx,ty),new Point(tx+image.getWidth()+10,ty),new Point(tx+image.getWidth()+10,ty+image.getHeight()+10),new Point(tx,ty+image.getHeight()+10)};
		button=new Button(points,new Color(0.8f,0.8f,0.8f,0.08f));
		panel.add(button);
		button.addActionListener(this);
		button.setHoverEffectsOn(false);
		button.setOutlineColor(new Color(0.8f,0.8f,0.8f,0.8f));
		
	}
	public void draw(Graphics2D g,int JPanelX, int JPanelY) 
	{
		button.draw(g, JPanelX, JPanelY);
		image.drawImage(g);
		Font text=null;
		try {
			text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Black.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		if(button.isHovering()) 
		{
			g.setColor(new Color(0.8f,0.8f,0.8f,0.8f));
			g.fillRect(x+3,y+11*image.getHeight()/12,image.getWidth()+6,image.getHeight()/12+10);
			g.setColor(Color.DARK_GRAY);
			g.setFont(text.deriveFont(10f));
			g.drawString(item.getName(),x+5,11*image.getHeight()/12+10+y);			
			g.setFont(text.deriveFont(60f));
			g.drawString(item.getName(),1400,600);
			g.drawString(""+item.getType(),1400,660);
			
			
		}
		
	}
	public void translate(int x,int y) 
	{
		button.translate(x, y);
		image.move(x, y);
		this.x+=x;
		this.y+=y;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Here");
	}

}
