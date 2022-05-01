package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;


import javax.swing.JFrame;
import javax.swing.JPanel;

import mapGeneration.MazeGenerator;

public class PetersMazeTester extends JPanel{
	
	private static char[][] maze;
	
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
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[i].length;j++) {
				if(maze[i][j]=='#') {
					g.fillRect(20+15*j,20+15*i,15,15);
				}
			}
		}
	}
	
	public static void generateMaze(int sizeX,int sizeY) {
		MazeGenerator generator=new MazeGenerator(0);
		maze=generator.generate(sizeX,sizeY);
	}
	
	private static BufferedImage createTestImage() {
	    int w = 1024;
	    int h = 768;

	    BufferedImage img = new
	        BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = img.createGraphics();
	    Color[] colors = { Color.red, Color.green, Color.blue };
	    float[] dist = {0.0f, 0.5f, 1.0f };
	    Point2D center = new Point2D.Float(0.5f * w, 0.5f * h);

	    RadialGradientPaint p =
	        new RadialGradientPaint(center, 0.5f * w, dist, colors);
	    g.setPaint(p);
	    g.fillRect(0, 0, w, h);
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
