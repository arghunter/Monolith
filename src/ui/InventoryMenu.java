package ui;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;
import GameObjects.Player.items.weapons.MeleeWeapon;
import general.Constants;
import input.MouseInputParser;
import render.Tester;

public class InventoryMenu implements MouseWheelListener,ActionListener {
	Inventory inventory;
	ArrayList<RenderableMenuItem> items;
	RenderableMenuItem selectedItem;
	private JPanel panel;
	private Button all;
	private Button consumables;
	private Button blueprints;
	private Button armor;
	private Button weapons;
	private Button materials;
	private boolean hidden=false;
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	String titleString="Inventory / All";
	public InventoryMenu(Inventory inventory,JPanel panel) 
	{
		
		this.inventory=inventory;

		this.panel=panel;
		panel.addMouseWheelListener(this);
		items= new ArrayList<RenderableMenuItem>();
		for(int i=0;i<inventory.getStorage().size();i++) 
		{
			System.out.println(inventory.getStorage().get(i));
			if(inventory.getStorage().get(i)!=null)
				items.add(new RenderableMenuItem(inventory.getStorage().get(i),266*(i%6)+275,((i/6)+1)*266,panel));
			items.get(i).addActionListener(this);
			
		}
		Point[] points= {new Point(0,266),new Point(260,266),new Point(260,316),new Point(0,316)};
		all=new Button(points,new Color(0.4f,0.4f,0.4f,0f),"All");
		panel.add(all);
		all.setFontColor(Constants.textColor);
		all.addActionListener(this);
		for(int i=0;i<4;i++) 
		{
			points[i].y+=50;
		}
		consumables=new Button(points,new Color(0.4f,0.4f,0.4f,0f),"Consumables");
		panel.add(consumables);
		consumables.setFontColor(Constants.textColor);
		consumables.addActionListener(this);

		for(int i=0;i<4;i++) 
		{
			points[i].y+=50;
		}
		blueprints=new Button(points,new Color(0.4f,0.4f,0.4f,0f),"Blueprints");
		panel.add(blueprints);
		blueprints.setFontColor(Constants.textColor);
		blueprints.addActionListener(this);

		for(int i=0;i<4;i++) 
		{
			points[i].y+=50;
		}
		armor=new Button(points,new Color(0.4f,0.4f,0.4f,0f),"Armor");
		panel.add(armor);
		armor.setFontColor(Constants.textColor);
		armor.addActionListener(this);
		for(int i=0;i<4;i++) 
		{
			points[i].y+=50;
		}
		weapons=new Button(points,new Color(0.4f,0.4f,0.4f,0f),"Weapons");
		panel.add(weapons);
		weapons.setFontColor(Constants.textColor);
		weapons.addActionListener(this);
		for(int i=0;i<4;i++) 
		{
			points[i].y+=50;
		}
		materials=new Button(points,new Color(0.4f,0.4f,0.4f,0f),"Materials");
		panel.add(materials);
		materials.setFontColor(Constants.textColor);
		materials.addActionListener(this);
		for(int i=0;i<4;i++) 
		{
			points[i].y+=50;
		}
		
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(!hidden) 
		{
			for(int i=0;i<items.size();i++) 
			{
				items.get(i).translate(0, 24*(int)(e.getUnitsToScroll()));
			}
		}

	}
	public void draw(Graphics2D g, int JPanelX,int JPanelY) 
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

			boolean tripped=false;
			for(int i=0;i<items.size();i++) 
			{
				if(selectedItem!=null) 
				{
					if(items.get(i).isHovering()) 
					{
						tripped=true;
						selectedItem.setIsSelected(false);
					}
				}

				
				items.get(i).draw(g, JPanelX, JPanelY);
			}
			if(!tripped&&selectedItem!=null) 
			{
				selectedItem.setIsSelected(true);
			}
			
			g.setColor(new Color(212/6,175/6,55/6));
			g.fillRect(0, 0, 1865, 266);
			g.fillRect(0,0, 275, 1377);
			all.draw(g, JPanelX, JPanelY);
			blueprints.draw(g, JPanelX, JPanelY);

			materials.draw(g, JPanelX, JPanelY);

			armor.draw(g, JPanelX, JPanelY);

			weapons.draw(g, JPanelX, JPanelY);
			consumables.draw(g, JPanelX, JPanelY);
			g.setColor(Constants.textColor);
			g.setFont(text.deriveFont(120f));
			g.drawString(titleString, 275, 175);
		}
		


	}
	private static BufferedImage createGradient(int JPanelX,int JPanelY) {
	    int width = (int) Tester.WIDTH;
	    int height = (int) Tester.HEIGHT;
	    
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
	public void update() 
	{
		for(int i=0;i<items.size();i++) 
		{
			items.get(i).dispose();
		}
		items= new ArrayList<RenderableMenuItem>();
		for(int i=0;i<inventory.getStorage().size();i++) 
		{
			if(inventory.getStorage().get(i)!=null) 
			{
				items.add(new RenderableMenuItem(inventory.getStorage().get(i),266*(i%6)+275,((i/6)+1)*266,panel));
				if(selectedItem!=null&&items.get(items.size()-1).getItem()==selectedItem.getItem()) 
				{
					selectedItem=items.get(items.size()-1);
				}
			}
			items.get(i).addActionListener(this);
		}
		if(!items.contains(selectedItem)) 
		{
			selectedItem=null;
		}
	}
	public void update(ItemType type) 
	{
		for(int i=0;i<items.size();i++) 
		{
			items.get(i).dispose();
		}
		ArrayList<Item> selection=inventory.searchStorage(type);
		items= new ArrayList<RenderableMenuItem>();
		for(int i=0;i<selection.size();i++) 
		{
			if(selection.get(i)!=null) 
			{
				items.add(new RenderableMenuItem(selection.get(i),266*(i%6)+275,((i/6)+1)*266,panel));
				if(selectedItem!=null&&items.get(items.size()-1).getItem()==selectedItem.getItem()) 
				{
					selectedItem=items.get(items.size()-1);
				}
			}
			items.get(i).addActionListener(this);
		}
		if(!items.contains(selectedItem)) 
		{
			selectedItem=null;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!hidden) 
		{
			if(e.getActionCommand().equals("ItemClicked")) 
			{
				if(selectedItem!=null) 
				{
					selectedItem.setIsSelected(false);
				}
				selectedItem=(RenderableMenuItem) e.getSource();
				selectedItem.setIsSelected(true);
			}else if(e.getActionCommand().equals("UpdateInventory")) 
			{
				this.update();
			}else if(e.getSource()==all) 
			{
				this.update();
				titleString="Inventory / All";
			}else if(e.getSource()==blueprints) 
			{
				this.update(ItemType.BLUEPRINT);
				titleString="Inventory / Blueprints";
			}else if(e.getSource()==armor) 
			{
				//notes broken
				this.update(ItemType.ARMOR);
				titleString="Inventory / Armor";
			}else if(e.getSource()==weapons) 
			{
				this.update(ItemType.WEAPON);
				titleString="Inventory / Weapons";
			}else if(e.getSource()==materials) 
			{
				this.update(ItemType.MATERIAL);
				titleString="Inventory / Materials";
			}else if(e.getSource()==consumables) 
			{
				this.update(ItemType.CONSUMABLE);
				titleString="Inventory / Consumables";
			}
		}


		
		
	}

}
