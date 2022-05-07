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
	Polygon shape;
	RenderableMenuItem[] arsenalItems;

	public ArsenalMenu(Inventory inventory, JPanel panel) {
		Point[] shapeCoords = { new Point(1792, 689), new Point(1280, 1201), new Point(768, 689),new Point(1280, 177) };
		shape = new Polygon();
		for (int i = 0; i < shapeCoords.length; i++) {
			shape.addPoint(shapeCoords[i].x, shapeCoords[i].y);
		}
		arsenalItems = new RenderableMenuItem[inventory.getArsenal().length];
		arsenalItems[0]=new RenderableMenuItem(inventory.getHelemet(),shapeCoords[0].x-128,shapeCoords[0].y-128,panel);
		arsenalItems[1]=new RenderableMenuItem(inventory.getChestplate(),shapeCoords[1].x-128,shapeCoords[1].y-128,panel);
		arsenalItems[2]=new RenderableMenuItem(inventory.getLeggings(),shapeCoords[2].x-128,shapeCoords[2].y-128,panel);
		arsenalItems[3]=new RenderableMenuItem(inventory.getBoots(),shapeCoords[3].x-128,shapeCoords[3].y-128,panel);

//		for(int i=0;i<shapeCoords.length;i++) 
//		{
//			
//		}

	}
	public void draw(Graphics2D g, int JPanelX,int JPanelY) 
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
		g.draw(shape);
		for(int i=0;i<arsenalItems.length;i++) 
		{


			if(arsenalItems[i]!=null)
			arsenalItems[i].draw(g, JPanelX, JPanelY);
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
