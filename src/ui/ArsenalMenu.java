package ui;

import java.awt.Color;
import GameObjects.Player.items.ItemType;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import GameObjects.Player.ArsenalFullException;
import GameObjects.Player.Inventory;
import input.MouseInputParser;

import render.Tester;

public class ArsenalMenu implements MouseWheelListener,ActionListener{
	private Inventory inventory;
	private ArsenalMenuItem[] arsenalItems=new ArsenalMenuItem[16];
	private boolean hidden=false;
	private ArrayList<RenderableMenuItem> items;
	private RenderableMenuItem selectedItem;
	private JPanel panel;

	public ArsenalMenu(Inventory inventory, JPanel panel) {
		panel.addMouseWheelListener(this);
		this.panel=panel;
		int count=0;
		this.inventory=inventory;
	
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
		for(int i=0;i<arsenalItems.length;i++) 
		{
			arsenalItems[i].addActionListener(this);
		}
		items=new ArrayList<RenderableMenuItem>();
		for(int i=0;i<inventory.getStorage().size();i++)
		{
			if(inventory.getStorage().get(i)!=null&&(inventory.getStorage().get(i).getType()==ItemType.WEAPON||(inventory.getStorage().get(i).getType()==ItemType.CHESTPLATE)||(inventory.getStorage().get(i).getType()==ItemType.BOOTS)||(inventory.getStorage().get(i).getType()==ItemType.LEGGINGS||(inventory.getStorage().get(i).getType()==ItemType.HELMET||(inventory.getStorage().get(i).getType()==ItemType.CONSUMABLE))))) 
			{
				
				items.add(new RenderableMenuItem(inventory.getStorage().get(i),1400+(i%2*266),266+(i/2)*266,panel));
				items.get(items.size()-1).addActionListener(this);
			}
			
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

			
		
			for(int i=0;i<arsenalItems.length;i++) 
			{


				if(arsenalItems[i]!=null)
				arsenalItems[i].draw(g, JPanelX, JPanelY);
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
			for(int i=0;i<arsenalItems.length;i++) 
			{
				if(selectedItem!=null) 
				{
					if(arsenalItems[i].isHovering()&&!(arsenalItems[i].getItem()==null)) 
					{
						tripped=true;
						selectedItem.setIsSelected(false);
					}
				}
			}
			if(!tripped&&selectedItem!=null) 
			{
				selectedItem.setIsSelected(true);
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
	public void update() 
	{
		System.out.println("HEre");
		for(int i=0;i<items.size();i++) 
		{
			items.get(i).dispose();
		}

		int count=0;
		for(int i=0;i<arsenalItems.length;i++) 
		{
			arsenalItems[i].dispose();
		}
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
		for(int i=0;i<arsenalItems.length;i++) 
		{
			arsenalItems[i].addActionListener(this);
		}
		items=new ArrayList<RenderableMenuItem>();
		for(int i=0;i<inventory.getStorage().size();i++)
		{
			if(inventory.getStorage().get(i)!=null&&(inventory.getStorage().get(i).getType()==ItemType.WEAPON||(inventory.getStorage().get(i).getType()==ItemType.CHESTPLATE)||(inventory.getStorage().get(i).getType()==ItemType.BOOTS)||(inventory.getStorage().get(i).getType()==ItemType.LEGGINGS||(inventory.getStorage().get(i).getType()==ItemType.HELMET||(inventory.getStorage().get(i).getType()==ItemType.CONSUMABLE))))) 
			{
				
				items.add(new RenderableMenuItem(inventory.getStorage().get(i),1400+(i%2*266),266+(i/2)*266,panel));
				items.get(items.size()-1).addActionListener(this);
			}
			
		}


	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(!hidden) 
		{
			for(int i=0;i<items.size();i++) 
			{
				items.get(i).translate(0, 24*(int)(e.getWheelRotation()));
			}
		}

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!hidden) 
		{
			System.out.println("ugliugy "+(e.getActionCommand().equals("ItemClicked")&&!items.contains(e.getSource())));
			if(e.getActionCommand().equals("ItemClicked")&&!items.contains(e.getSource())) 
			{
				if(selectedItem!=null) 
				{
					selectedItem.setIsSelected(false);
				}
				selectedItem=(RenderableMenuItem) e.getSource();
				selectedItem.setIsSelected(true);
				System.out.println(selectedItem.isSelected());
				
			}
			if(e.getActionCommand().equals("ItemClicked")&&items.contains(e.getSource())) 
			{

				if(selectedItem==null&&((RenderableMenuItem)e.getSource()).getItem()!=null) 
				{
					try {
						inventory.addToArsenal(((RenderableMenuItem)e.getSource()).getItem());
					} catch (ArsenalFullException e1) {
						//ArsenalFULL
					}
					
					
					update();
				}else if(!(selectedItem==null)&&((RenderableMenuItem)e.getSource()).getItem()!=null) 
				{
				
					inventory.removeFromArsenal(selectedItem.getItem());
					
					try {
						inventory.addToArsenal(((RenderableMenuItem)e.getSource()).getItem());
					} catch (ArsenalFullException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					update();
				}
			}
		}
		
	}

}
