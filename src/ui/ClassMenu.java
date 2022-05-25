package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import GameObjects.Player.Classes;
import GameObjects.Player.Player;
import GameObjects.Player.abilities.Ability;
import general.Constants;
import input.MouseInputParser;
import render.Main;

public class ClassMenu implements ActionListener {
	private Button[] classButtons=new Button[3];
	private boolean hidden=true;
	private Player player;
	
	public ClassMenu(JPanel panel,Player player) 
	{
		this.player=player;
		Point[] points= {new Point(275,275),new Point(675,275),new Point(675,325),new Point(275,325)};
		for(int i=0;i<classButtons.length;i++) 
		{
			Classes classType=null;
			switch(i) 
			{
			case 0:
				classType=Classes.WARRIOR;
				break;
			case 1:
				classType=Classes.MAGE;
				break;
			case 2:
				classType=Classes.PYROMANIAC;
				break;
				
				
			}
			classButtons[i]=new Button(points,new Color(0f,0f,0f,0f),""+classType);
			panel.add(classButtons[i]);
			classButtons[i].addActionListener(this);
			classButtons[i].setFontColor(Constants.TEXTCOLOR);
			classButtons[i].setHoverEffectsOn(false);
			for(Point p:points) 
			{
				if((i+1)%5==0) 
				{
					p.x+=(-400*5);p.y+= 50;
					
				}else 
				{
					p.x+=400;
				}
			}
			
		}
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public void draw(Graphics2D g,int JPanelX, int JPanelY) 
	{
		if(!hidden) 
		{
			g.drawImage(createGradient(JPanelX,JPanelY), 0, 0, null);
			Font text=null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			for(int i=0;i<classButtons.length;i++) 
			{
				classButtons[i].draw(g, JPanelX, JPanelY);
				
				
					if(classButtons[i].isHovering()) 
					{
						Ability[] abilities=null;
						Classes classType=null;

						switch(i) 
						{

						case 0:
							classType=Classes.WARRIOR;
							break;
						case 1:
							classType=Classes.MAGE;
							break;
						case 2:
							classType=Classes.PYROMANIAC;
							break;
						}
						abilities=Player.getClassAbilities(classType);
						g.setFont(text.deriveFont(60f));
						FontMetrics metrics=g.getFontMetrics();
						g.drawString(""+classType,2180-metrics.stringWidth(""+classType)/2,200);
						g.setFont(text.deriveFont(35f));
						metrics=g.getFontMetrics();
						g.drawString(""+abilities[0].getClass().toString().substring(abilities[0].getClass().toString().lastIndexOf('.')+1),2180-metrics.stringWidth(""+abilities[0].getClass().toString().substring(abilities[0].getClass().toString().lastIndexOf('.')+1))/2,250);
						g.drawString(""+abilities[1].getClass().toString().substring(abilities[1].getClass().toString().lastIndexOf('.')+1),2180-metrics.stringWidth(""+abilities[1].getClass().toString().substring(abilities[1].getClass().toString().lastIndexOf('.')+1))/2,300);
						g.drawString(""+abilities[2].getClass().toString().substring(abilities[2].getClass().toString().lastIndexOf('.')+1),2180-metrics.stringWidth(""+abilities[2].getClass().toString().substring(abilities[2].getClass().toString().lastIndexOf('.')+1))/2,350);
						g.drawString(""+abilities[3].getClass().toString().substring(abilities[3].getClass().toString().lastIndexOf('.')+1),2180-metrics.stringWidth(""+abilities[3].getClass().toString().substring(abilities[3].getClass().toString().lastIndexOf('.')+1))/2,400);

					}
			}

			
			g.setColor(new Color(212/6,175/6,55/6));
			g.fillRect(0, 0, 1865, 266);
			g.fillRect(0,0, 275, 1377);
			g.setFont(text.deriveFont(120f));
			g.setColor(Constants.TEXTCOLOR);
			g.drawString("Classes   Current: "+player.getClassType(), 275, 175);

		}
	}
	//Draws a gradient with bounds centered around the player mouse
		private static BufferedImage createGradient(int JPanelX,int JPanelY) {
		    int width = (int) Main.WIDTH;
		    int height = (int) Main.HEIGHT;
		    
		    BufferedImage img = new
		        BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		    Graphics2D g = img.createGraphics();

		    Color[] colors = {new Color((212)/3,(175)/3,(55)/3), new Color(212/6,175/6,55/6) };
		    float[] ratio = { 0.0f, 0.6f };
		    Point center=new Point((int)(MouseInputParser.getX()-JPanelX/MouseInputParser.getRatioX()),(int)(MouseInputParser.getY()-JPanelY/MouseInputParser.getRatioX()));
		    if(MouseInputParser.getX()<275-JPanelX/MouseInputParser.getRatioX()||MouseInputParser.getY()-JPanelY/MouseInputParser.getRatioX()<266) 
		    {
		    	center=new Point(-200,-200);
		    }
		    RadialGradientPaint gradient =new RadialGradientPaint(center, 0.25f * width, ratio, colors);
		    g.setPaint(gradient);
		    g.fillRect(0, 0, width, height);
		    g.dispose();

		    return img;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!hidden) 
		{
			
			for(int i=0;i<classButtons.length;i++) 
			{
				if(e.getSource().hashCode()==classButtons[i].hashCode()) 
				{
					switch(i) 
					{
					case 0:
						player.setClass(Classes.WARRIOR);
						break;
					case 1:
						player.setClass(Classes.MAGE);
						break;
					case 2:
						player.setClass(Classes.PYROMANIAC);
						break;
					}
				}
			}
		}
		
		
	}
	
	

}
