package mapGeneration;

import java.awt.*; 
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import GameObjects.*;
import GameObjects.Player.Player;
import GameObjects.Player.items.materials.Material;
import GameObjects.mobs.Mob;
import GameObjects.mobs.Spider;
import GameObjects.mobs.Zombie;
import GameObjects.mobs.Balkrada;
import general.SaveSystem;
import input.InputParser;
import ui.Button;
import ui.RenderableMenuItem;
import ui.SkillSelectionMenu;

import java.util.ArrayList;
import java.util.LinkedList;

//import general.ImageSystem;

public class MapVisual extends JPanel {

	private JFrame frame;
	private MapGenerator op=new MapGenerator();
	
	private String[][] curRoom;
	//private ImageSystem image=new ImageSystem(0,0,new ImageIcon("StoneBig.png").getImage());
	
	public MapVisual() {
		JFrame w = new JFrame("Maze");
		w.setSize(1600,1000);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);
		w.setResizable(true);
		w.setVisible(true);
		this.frame = w;
		repaint();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int m=0;m<op.getYSize();m++) {
			for(int n=0;n<op.getXSize();n++) {
				curRoom=op.getRoom(n,m);
				if(!(curRoom==null)) {
					for(int i=0;i<op.getRoomSizeY();i++) {
						for(int j=0;j<op.getRoomSizeX();j++) {
							g.setColor(Color.WHITE);
							if(curRoom[i][j].equals("11")) {
								g.setColor(Color.BLACK);
							}else if(curRoom[i][j].equals("22")) {
								g.setColor(Color.RED);
							}
							g.fillRect(op.getRoomSizeX()*2*n+j*2,op.getRoomSizeY()*2*m+i*2,2,2);
						}
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		MapVisual gb = new MapVisual();
	}
}
