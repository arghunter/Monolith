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
		int tx=x;
		int ty=y;
		tx-=image.getWidth()/2;
		ty-=image.getHeight()/2;
		Point[] points= {new Point(tx,ty),new Point(tx+image.getWidth()+10,ty),new Point(tx+image.getWidth()+10,ty+image.getHeight()+50),new Point(tx,ty+image.getHeight()+50)};
		button=new Button(points,new Color(0f,0f,1f,1f));
		panel.add(button);
		button.addActionListener(this);
		
		
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
		g.setColor(Color.YELLOW);
		g.setFont(text.deriveFont(20f));
		g.drawString(item.getName(),x-image.getWidth()/2+2 ,image.getHeight()+image.getY()+5);
		if(button.isHovering()) 
		{
			g.setFont(text.deriveFont(60f));
			g.drawString(item.getName(),1400,600);
			g.drawString(""+item.getType(),1400,660);
			
			
		}
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Here");
	}

}
