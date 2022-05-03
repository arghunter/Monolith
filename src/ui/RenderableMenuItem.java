package ui;

import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.blueprints.Blueprint;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;
import general.ImageSystem;
import input.MouseInputParser;

public class RenderableMenuItem implements ActionListener {
	
	ImageSystem image;
	Item item;
	Button button;
	private int x;
	private int y;
	private ActionListener[] actionListeners;
	private Button[] itemButtons=new Button[0];
	private boolean isSelected=false;
	private boolean isHovering=false;
	public RenderableMenuItem(Item item,int x, int y,JPanel panel) 
	{
		this.item=item;
		this.x=x;
		this.y=y;
		ImageIcon iconImg=(new ImageIcon("imgs/"+item.getName()+"/"+item.getName()+0+".png"));
		actionListeners=new ActionListener[0];
		if(iconImg.getIconWidth()==-1) 
		{
			throw new IllegalArgumentException("Image not found");
		}
		BufferedImage img=new BufferedImage(iconImg.getIconWidth(),iconImg.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=img.createGraphics();
		int xShift=0;
		Point[] itemPoints= {new Point(50+xShift,0),new Point(350+xShift,0),new Point(400+xShift,25),new Point(350+xShift,50),new Point(50+xShift,50),new Point(0+xShift,25)};
		if(item.getType()==ItemType.BLUEPRINT) 
		{
		    Color[] colors = { new Color(212/6,175/6,55/6),new Color((212*2)/5,(175*2)/5,(55*2)/5) };
		    float[] ratio = { 0.2f, 0.8f };
		    Point center=new Point((int)MouseInputParser.getX(),(int)MouseInputParser.getY());
		    RadialGradientPaint gradient =new RadialGradientPaint(center, 0.8f * img.getWidth(), ratio, colors);
		    g.setPaint(gradient);
		    for(int i=1;i<8;i++) 
		    {
		    
		    	g.draw(new Line2D.Double(0,i*img.getHeight()/8,img.getWidth(),i*img.getHeight()/8));
		    }
		    for(int i=1;i<8;i++) 
		    {
		    	g.draw(new Line2D.Double(i*img.getWidth()/8,0,i*img.getWidth()/8,img.getHeight()));
		    }
		    itemButtons=new Button[2];
		    int iShiftX=1980;
		    int iShiftY=750;
		    for( int i=0;i<itemButtons.length;i++) 
		    {
		    	
		    	Point[] tempPoints=new Point[itemPoints.length];
		    	for(int j=0;j<tempPoints.length;j++) 
		    	{
		    		tempPoints[j]=new Point(itemPoints[j].x+iShiftX,itemPoints[j].y+iShiftY);
		    	}
		    	itemButtons[i]=new Button(tempPoints,new Color((212)/4,(175)/4,(55)/4,50));
		    	itemButtons[i].setOutlineColor(new Color((120*4)/2,(113*4)/2,(96*4)/2,80));
		    	if(i==0) 
		    	{
		    		itemButtons[i].setText("Construct Item");
		    	}else if( i==1) 
		    	{
		    		itemButtons[i].setText("Sell Blueprint");
		    	}
		    	itemButtons[i].setFontColor(new Color((212*4)/5,(175*4)/5,(55*4)/5));
		    	panel.add(itemButtons[i]);
		    	itemButtons[i].addActionListener(this);
		    	
		    	iShiftY+=150;
		    }
		    //Construct Button Discard/Sell button
		}
		g.drawImage(iconImg.getImage(), 0, 0, null);
		image=new ImageSystem(x+5,y+5,img);

		image.move(image.getWidth()/2, image.getHeight()/2);
		int tx=x;
		int ty=y;

		Point[] points= {new Point(tx,ty),new Point(tx+image.getWidth()+10,ty),new Point(tx+image.getWidth()+10,ty+image.getHeight()+10),new Point(tx,ty+image.getHeight()+10)};
		button=new Button(points,new Color(0f,0f,0f,0f));
		panel.add(button);
		button.addActionListener(this);
		button.setHoverEffectsOn(false);
		//button.setOutlineColor(new Color(0.8f,0.8f,0.8f,0.5f));
		
	}
	public void draw(Graphics2D g,int JPanelX, int JPanelY) 
	{
		button.draw(g, JPanelX, JPanelY);
		this.isHovering=button.isHovering();
		image.drawImage(g);
		Font text=null;
		try {
			text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		if(item.getType()==ItemType.CONSUMABLE) 
		{
			g.setFont(text.deriveFont(30f));
			g.setColor(new Color((212*4)/5,(175*4)/5,(55*4)/5));
			Consumable consumable=(Consumable) item;
			String count="x"+consumable.getCount();
			FontMetrics metrics=g.getFontMetrics();
			g.drawString(count, image.getWidth()+x-metrics.stringWidth(count), y+metrics.getHeight());
		}else if(item.getType()==ItemType.MATERIAL) 
		{
			g.setFont(text.deriveFont(30f));
			g.setColor(new Color((212*4)/5,(175*4)/5,(55*4)/5));
			Material material=(Material) item;
			String count="x"+material.getCount();
			FontMetrics metrics=g.getFontMetrics();
			g.drawString(count.substring(0,count.indexOf('.')), image.getWidth()+x-metrics.stringWidth(count.substring(0,count.indexOf('.'))), y+metrics.getHeight());
		}
		if(button.isHovering()||this.isSelected) 
		{
			g.setColor(new Color(0.4f,0.4f,0.4f,0.5f));
			g.fillRect(x+2,y-1+11*image.getHeight()/12,image.getWidth()+6,image.getHeight()/12+10);
			g.setColor(new Color((212*4)/5,(175*4)/5,(55*4)/5));
			g.setFont(text.deriveFont(30f));
			g.drawString(item.getName(),x+5,11*image.getHeight()/12+25+y);			
			g.setFont(text.deriveFont(60f));
			FontMetrics metrics=g.getFontMetrics();
			g.drawString(item.getName(),2180-metrics.stringWidth(item.getName())/2,200);
			g.setFont(text.deriveFont(35f));
			metrics=g.getFontMetrics();
			g.drawString(""+item.getType(),2180-metrics.stringWidth(""+item.getType())/2,250);
			for(int i=0;i<itemButtons.length;i++) 
			{
				itemButtons[i].draw(g, JPanelX, JPanelY);
			}
			if(item.getType()==ItemType.BLUEPRINT) 
			{
				Blueprint blueprint=(Blueprint) item;
				g.drawString("Components:",2180-metrics.stringWidth("Components:")/2,400);

				for(int i=0;i<blueprint.getComponents().length;i++) 
				{
					g.setFont(text.deriveFont(30f));
					metrics=g.getFontMetrics();
					String compString=""+blueprint.getComponents()[i].getName();
					if(blueprint.getComponents()[i].getType()==ItemType.CONSUMABLE) 
					{
						compString+=" x"+((Consumable) blueprint.getComponents()[i]).getCount();
					}else if(blueprint.getComponents()[i].getType()==ItemType.MATERIAL) 
					{
						compString+=" x"+((Material) blueprint.getComponents()[i]).getCount();
					}else 
					{
						compString += " x1";
					}
					g.drawString(compString,2180-metrics.stringWidth(compString)/2,400+40*(i+1));
				}
			}
			
			
		}
		
	}
	public void translate(int x,int y) 
	{
		button.translate(x, y);
		image.move(x, y);
		this.x+=x;
		this.y+=y;
	}
	public boolean isHovering() 
	{
		return isHovering;
	}
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
	public boolean isSelected() 
	{
		return isSelected;
	}
	public void setIsSelected(boolean isSelected) 
	{
		this.isSelected=isSelected;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(button.isClicked(e)) 
		{
			for(int i=0;i<actionListeners.length;i++) 
			{
				actionListeners[i].actionPerformed(new ActionEvent(this,88889,"ItemClicked"));
			}
		}
		
	}

}
