package ui;
import java.awt.event.MouseWheelListener;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.*;
import java.awt.image.BufferedImage;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.blueprints.Blueprint;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;
import GameObjects.Player.items.weapons.MeleeWeapon;
import input.InputParser;
import mapGeneration.MazeGenerator;
import render.Tester;

public class PetersMazeTester extends JPanel implements MouseWheelListener,ActionListener{
	
	private static char[][] maze;
	Material steel=new Material("Spider",0,100);
	private RenderableMenuItem item=new RenderableMenuItem(steel,900,200,this);
	private Timer clock = new Timer(10, this);
	private InputParser input=new InputParser(this);
	Inventory inventory=new Inventory();
	InventoryMenu menu;
	public PetersMazeTester() {
		this.addMouseWheelListener(this);
		clock.start();

		inventory.addToStorage(new Consumable("Baklava",0,10,64));
		inventory.addToStorage(new Consumable("Baklava",0,10,64));
		inventory.addToStorage(new Consumable("Baklava",0,50,64));
		inventory.addToStorage(new Consumable("Baklava",0,50,64));
		inventory.addToStorage(new Consumable("Baklava",0,50,64));

		Item[] it={new Consumable("Baklava",0,10,64),new Consumable("Spider",0,10,64)};
		inventory.addToStorage(new Blueprint("Baklava",0,1,it,new Consumable("Baklava",0,10,64),inventory));
		inventory.addToStorage(new Material("Crystal",0,100000));

		menu=new InventoryMenu(inventory,this);
	}
	
	public void printMaze(char[][] maze) {
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[i].length;j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
	
	public void paintComponent(Graphics gr) {
		Graphics2D g=(Graphics2D) gr;
		double ratioX = super.getWidth() / 2560.0;
		double ratioY = super.getHeight() / 1377.0;
		input.setRatio(ratioX, ratioY);
		g.scale(ratioX, ratioY);
		super.paintComponent(g);

		
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[i].length;j++) {
				if(maze[i][j]=='#') {
					g.fillRect(20+15*j+(int)(Tester.WIDTH/2)-350,20+15*i+(int)(Tester.HEIGHT/2)-300,15,15);
				}
			}
		}
		item.draw((Graphics2D)g, (int)this.getLocationOnScreen().getX(), (int)this.getLocationOnScreen().getY());
		menu.draw((Graphics2D)g, (int)this.getLocationOnScreen().getX(), (int)this.getLocationOnScreen().getY());

	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

			item.translate(0, 4*(int)(e.getUnitsToScroll()));
		repaint();
		
	}
	public static void generateMaze(int sizeX,int sizeY) {
		MazeGenerator generator=new MazeGenerator(0);
		maze=generator.generate(sizeX,sizeY);
	}
	
	
	
	public static void main(String[] args) {
		
		generateMaze(20,15);
		JFrame w = new JFrame("Maze");
		w.setSize(1600,1000);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(new PetersMazeTester());
		w.setResizable(true);
		w.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
}
