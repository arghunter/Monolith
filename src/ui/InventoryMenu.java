package ui;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;
import GameObjects.Player.items.weapons.MeleeWeapon;
import input.MouseInputParser;
import render.Tester;

public class InventoryMenu implements MouseWheelListener,ActionListener {
	Inventory inventory;
	ArrayList<RenderableMenuItem> items;
	RenderableMenuItem selectedItem;
	public InventoryMenu(Inventory inventory,JPanel panel) 
	{
		
		this.inventory=inventory;

		
		panel.addMouseWheelListener(this);
		items= new ArrayList<RenderableMenuItem>();
		for(int i=0;i<inventory.getStorage().size();i++) 
		{
			if(inventory.getStorage().get(i)!=null)
				items.add(new RenderableMenuItem(inventory.getStorage().get(i),266*(i%6)+275,((i/6)+1)*266,panel));
			items.get(i).addActionListener(this);
		}
		System.out.println(inventory.getStorage());
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(int i=0;i<items.size();i++) 
		{
			items.get(i).translate(0, 24*(int)(e.getUnitsToScroll()));
		}
	}
	public void draw(Graphics2D g, int JPanelX,int JPanelY) 
	{
		g.drawImage(createGradient(), 0, 0, null);
		for(int i=0;i<items.size();i++) 
		{
			items.get(i).draw(g, JPanelX, JPanelY);
		}
	}
	private static BufferedImage createGradient() {
	    int width = (int) Tester.WIDTH;
	    int height = (int) Tester.HEIGHT;
	    
	    BufferedImage img = new
	        BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = img.createGraphics();

	    Color[] colors = {new Color((212)/3,(175)/3,(55)/3), new Color(212/6,175/6,55/6) };
	    float[] ratio = { 0.0f, 0.5f };
	    Point center=new Point((int)MouseInputParser.getX(),(int)MouseInputParser.getY());
	    RadialGradientPaint gradient =new RadialGradientPaint(center, 0.15f * width, ratio, colors);
	    g.setPaint(gradient);
	    g.fillRect(0, 0, width, height);
	    g.dispose();

	    return img;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(selectedItem!=null) 
		{
			selectedItem.setIsSelected(false);
		}
		selectedItem=(RenderableMenuItem) e.getSource();
		selectedItem.setIsSelected(true);
		
		
	}

}
