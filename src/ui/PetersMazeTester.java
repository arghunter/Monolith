package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;


import javax.swing.JFrame;
import javax.swing.JPanel;

import GameObjects.Player.items.materials.Material;
import mapGeneration.MazeGenerator;
import render.Tester;

public class PetersMazeTester extends JPanel{
	
	private static char[][] maze;
	Material steel=new Material("Spider",100);
	private RenderableMenuItem item=new RenderableMenuItem(steel,900,800,this);

	public PetersMazeTester() {
		
	}
	
	public void printMaze(char[][] maze) {
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[i].length;j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage img=createGradient();
		g.drawImage(img, 0, 0, null);
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[i].length;j++) {
				if(maze[i][j]=='#') {
					g.fillRect(20+15*j+(int)(Tester.WIDTH/2)-350,20+15*i+(int)(Tester.HEIGHT/2)-300,15,15);
				}
			}
		}
		item.draw((Graphics2D)g, (int)this.getLocationOnScreen().getX(), (int)this.getLocationOnScreen().getY());

	}
	
	
	public static void generateMaze(int sizeX,int sizeY) {
		MazeGenerator generator=new MazeGenerator(0);
		maze=generator.generate(sizeX,sizeY);
	}
	
	private static BufferedImage createGradient() {
	    int width = (int) Tester.WIDTH;
	    int height = (int) Tester.HEIGHT;

	    BufferedImage img = new
	        BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = img.createGraphics();
	    Color[] colors = {new Color((212)/3,(175)/3,(55)/3), new Color(212/6,175/6,55/6) };
	    float[] ratio = { 0.0f, 0.5f };
	    Point center = new Point((int)(0.5f * width),(int) (0.1f * height));

	    RadialGradientPaint p =
	        new RadialGradientPaint(center, 0.4f * width, ratio, colors);
	    g.setPaint(p);
	    g.fillRect(0, 0, width, height);
	    g.dispose();

	    return img;
	}
	
	public static void main(String[] args) {
		
		generateMaze(20,15);
		JFrame w = new JFrame("Maze");
		w.setSize(600,600);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(new PetersMazeTester());
		w.setResizable(true);
		w.setVisible(true);
	}
}
