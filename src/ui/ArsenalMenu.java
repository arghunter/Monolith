package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import GameObjects.Player.Inventory;
import input.MouseInputParser;
import render.Tester;

public class ArsenalMenu {

	private ArsenalMenuItem[] arsenalItems=new ArsenalMenuItem[16];
	private boolean hidden=false;

	public ArsenalMenu(Inventory inventory, JPanel panel) {
		int count=0;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],541,532,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(541,532,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],807,532,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(807,532,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],807,798,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(807,798,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],541,798,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(541,798,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			System.out.println(inventory.getArsenal()[count].getName());
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],275,266,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(275,266,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],541,266,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(541,266,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],807,266,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(807,266,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],1073,266,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(1073,266,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],1073,532,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(1073,532,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],1073,798,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(1073,798,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],1073,1064,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(1073,1064,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],807,1064,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(807,1064,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],541,1064,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(541,1064,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],275,1064,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(275,1064,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],275,798,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(275,798,panel,inventory);

		}
		count++;
		if(inventory.getArsenal()[count]!=null) 
		{
			arsenalItems[count]=new ArsenalMenuItem(inventory.getArsenal()[count],275,532,panel,inventory);
		}else 
		{
			arsenalItems[count]=new ArsenalMenuItem(275,532,panel,inventory);

		}



	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public void draw(Graphics2D g, int JPanelX,int JPanelY) 
	{
		if(!hidden) 
		{
			g.drawImage(createGradient(), 0, 0, null);
			Font text=null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			boolean tripped=false;
		
			for(int i=0;i<arsenalItems.length;i++) 
			{


				if(arsenalItems[i]!=null)
				arsenalItems[i].draw(g, JPanelX, JPanelY);
			}	
		}




	}
	private static BufferedImage createGradient() {
	    int width = (int) Tester.WIDTH;
	    int height = (int) Tester.HEIGHT;
	    
	    BufferedImage img = new
	        BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = img.createGraphics();

	    Color[] colors = {new Color((212)/3,(175)/3,(55)/3), new Color(212/6,175/6,55/6) };
	    float[] ratio = { 0.0f, 0.6f };
	    Point center=new Point((int)MouseInputParser.getX(),(int)MouseInputParser.getY());
	    RadialGradientPaint gradient =new RadialGradientPaint(center, 0.25f * width, ratio, colors);
	    g.setPaint(gradient);
	    g.fillRect(0, 0, width, height);
	    g.dispose();

	    return img;
	}

}
