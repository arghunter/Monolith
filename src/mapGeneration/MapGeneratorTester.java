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
import input.PlayerInputParser;
import ui.Button;
import ui.RenderableMenuItem;
import ui.SkillSelectionMenu;

import java.util.ArrayList;
import java.util.LinkedList;

//import general.ImageSystem;

public class MapGeneratorTester extends JPanel implements ActionListener {
	private Player thePlayer = new Player(300, 300, 1, 64, 64,this,this);
	private PlayerInputParser input;

	private JFrame frame;
	private Timer clock=new Timer(10, this);
	private MapGenerator op=new MapGenerator();
	private int curRoomX=0;
	private int curRoomY=0;
	private int timeSinceLastSpawn=10000;
	private String[] mobList= {"Spider","Zombie","Balkrada"}; //REMOVE AFTER TESTING
	MobSpawner test=new MobSpawner();
	
	private String[][] curRoom;
	//private ImageSystem image=new ImageSystem(0,0,new ImageIcon("StoneBig.png").getImage());
	
	public MapGeneratorTester() {
		JFrame w = new JFrame("Maze");
		w.setSize(1600,1000);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);
		w.setResizable(true);
		w.setVisible(true);
		this.initInput(w);
		this.frame = w;
		thePlayer.addXP(1000000);
		System.out.println(thePlayer.getLevel());
		clock.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		timeSinceLastSpawn++;
		if(thePlayer.getCenterX()>=op.getRoomSizeX()*32 && (curRoomX+1<op.getXSize())) {
			curRoomX++;
			thePlayer.setCoordsMove(thePlayer.getX()-32*(op.getRoomSizeX()-3), thePlayer.getY());
			changeRoom();
		}else if(thePlayer.getCenterX()<=0 && (curRoomX-1>=0)) {
			curRoomX--;
			thePlayer.setCoordsMove(thePlayer.getX()+32*(op.getRoomSizeX()-3), thePlayer.getY());
			changeRoom();
		}else if(thePlayer.getCenterY()>=op.getRoomSizeY()*32 && (curRoomY+1<op.getYSize())) {
			curRoomY++;
			thePlayer.setCoordsMove(thePlayer.getX(),thePlayer.getY()-32*(op.getRoomSizeY()-3));
			changeRoom();
		}else if(thePlayer.getCenterY()<=0 && (curRoomY-1>=0)) {
			curRoomY--;
			thePlayer.setCoordsMove(thePlayer.getX(),thePlayer.getY()+32*(op.getRoomSizeY()-3));
			changeRoom();
		}
		if(timeSinceLastSpawn>10000) {
			int[] n=test.generateMobs(thePlayer.getLevel());
			for(int i=0;i<n.length;i++) {
				System.out.print(mobList[n[i]]+" ");
			}
			System.out.println();
			resetMobSpawnTime();
		}
		//System.out.println(curRoomX+" "+curRoomY);
		repaint();
	}
	
	public void paintComponent(Graphics graphics) {
		Graphics2D graphic=(Graphics2D) graphics;
		
		double ratioX = super.getWidth() / 2560.0;
		double ratioY = super.getHeight() / 1377.0;
		input.setRatio(ratioX, ratioY);
		graphic.scale(ratioX, ratioY);
		
		super.paintComponent(graphic);
		
		setBackground(Color.WHITE);
		
		input.updatePlayer(thePlayer);
		curRoom=op.getRoom(curRoomX, curRoomY);
		
		if(!(curRoom==null)) {
			for(int i=0;i<op.getRoomSizeY();i++) {
				for(int j=0;j<op.getRoomSizeX();j++) {
					graphic.setColor(Color.WHITE);
					if(curRoom[i][j].equals("11")) {
						graphic.setColor(Color.BLACK);
					}else if(curRoom[i][j].equals("22")) {
						graphic.setColor(Color.RED);
					}
					graphic.fillRect(j*32,i*32,32,32);
				}
			}
		}
		
		thePlayer.render(graphic);
		
	}
	
	private void changeRoom() {
		timeSinceLastSpawn=100000;
	}
	
	private void resetMobSpawnTime() {
		timeSinceLastSpawn=0;
	}
	
	public void initInput(JFrame frame) {
		this.input = new PlayerInputParser(frame,this);
	}
	
	public static void main(String[] args) {
		MapGeneratorTester gb = new MapGeneratorTester();
	}
}
