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
import general.Collider;
import general.Constants;
import general.SaveSystem;
import input.PlayerInputParser;
import ui.Button;
import ui.RenderableMenuItem;
import ui.SkillSelectionMenu;

import java.util.ArrayList;
import java.util.LinkedList;

//import general.ImageSystem;

public class MobSpawnerTester extends JPanel implements ActionListener {
	private Player thePlayer = new Player(300, 300, 64, 64, this,this);
	private PlayerInputParser input;

	private JFrame frame;
	private Timer clock=new Timer(10, this);
	private MapGenerator op=new MapGenerator();
	private int curRoomX=0;
	private int curRoomY=0;
	MobSpawner test=new MobSpawner();
	
	private int[][] timeSinceLastSpawn=new int[Constants.YSIZE][Constants.XSIZE];

	private ArrayList<Mob>[][] mobList=new ArrayList[Constants.YSIZE][Constants.XSIZE];
	
	private String[][] curRoom;
	//private ImageSystem image=new ImageSystem(0,0,new ImageIcon("StoneBig.png").getImage());
	
	private int t=0;
	
	public MobSpawnerTester() {
		JFrame w = new JFrame("No");
		w.setSize(1600,1000);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);
		w.setResizable(true);
		w.setVisible(true);
		this.initInput(w);
		this.frame = w;
		thePlayer.addXP(100000);
		System.out.println(thePlayer.getLevel());
		for(int i=0;i<Constants.YSIZE;i++) {
			for(int j=0;j<Constants.XSIZE;j++) {
				timeSinceLastSpawn[i][j]=-5000;
				mobList[i][j]=new ArrayList<Mob>();
			}
		}
		thePlayer.setMobs(mobList[curRoomY][curRoomX]);

		clock.start();

	}
	
	public void actionPerformed(ActionEvent e) {
		if(!(mobList[curRoomY][curRoomX]==null)) {
			for(int i=0;i<mobList[curRoomY][curRoomX].size();i++) {
				if(mobList[curRoomY][curRoomX].get(i).isDead()) {
					mobList[curRoomY][curRoomX].remove(i);
				}
			}
		}
		t++;
		if(thePlayer.getX()>=op.getRoomSizeX()*32 && (curRoomX+1<op.getXSize())) {
			curRoomX++;
			thePlayer.setCoordsMove(thePlayer.getX()-32*(op.getRoomSizeX()-3), thePlayer.getY());
			changeRoom();
		}else if(thePlayer.getX()<=0 && (curRoomX-1>=0)) {
			curRoomX--;
			thePlayer.setCoordsMove(thePlayer.getX()+32*(op.getRoomSizeX()-3), thePlayer.getY());
			changeRoom();
		}else if(thePlayer.getY()>=op.getRoomSizeY()*32 && (curRoomY+1<op.getYSize())) {
			curRoomY++;
			thePlayer.setCoordsMove(thePlayer.getX(),thePlayer.getY()-32*(op.getRoomSizeY()-3));
			changeRoom();
		}else if(thePlayer.getY()<=0 && (curRoomY-1>=0)) {
			curRoomY--;
			thePlayer.setCoordsMove(thePlayer.getX(),thePlayer.getY()+32*(op.getRoomSizeY()-3));
			changeRoom();
		}
		if(!(mobList[curRoomY][curRoomX]==null)) {
			if(t-timeSinceLastSpawn[curRoomY][curRoomX]>5000) {
				int[] n=test.generateMobs(thePlayer.getLevel());
				for(int i=0;i<n.length;i++) {
					System.out.print(n[i]+" ");
					if(n[i]==0) {
						mobList[curRoomY][curRoomX].add(new Zombie((int) (Math.random() * 1200), (int) (Math.random() * 1100), 64, 64));
					}else if(n[i]==1) {
						mobList[curRoomY][curRoomX].add(new Spider((int) (Math.random() * 1200), (int) (Math.random() * 1100), 64, 64));
					}else if(n[i]==2) {
						mobList[curRoomY][curRoomX].add(new Balkrada((int) (Math.random() * 1200), (int) (Math.random() * 1100), 96, 187));
					}
				}
			}
			resetMobSpawnTime();
		}
		if(!(mobList[curRoomY][curRoomX]==null)) {
			for(int i=0;i<mobList[curRoomY][curRoomX].size();i++) {
				mobList[curRoomY][curRoomX].get(i).action(thePlayer);
			}
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
		input.setGraphics(graphic);
		input.updatePlayer(thePlayer);
		curRoom=op.getRoom(curRoomX, curRoomY);
		Collider collider = new Collider(curRoom);
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
		for(int i=0;i<mobList[curRoomY][curRoomX].size();i++) {
			if(!(curRoom==null)) {
				mobList[curRoomY][curRoomX].get(i).render(graphic);
				collider.checkCollides(mobList[curRoomY][curRoomX].get(i).getRect(),mobList[curRoomY][curRoomX].get(i));
			}
		}
		thePlayer.render(graphic);
		collider.checkCollides(thePlayer.getRect(),thePlayer);
		graphic.fillRect(thePlayer.getX(),thePlayer.getY(),64,64);
	}
	
	private void changeRoom() {
		thePlayer.setMobs(mobList[curRoomY][curRoomX]);
	}
	
	private void resetMobSpawnTime() {
		timeSinceLastSpawn[curRoomY][curRoomX]=t;
	}
	
	public void initInput(JFrame frame) {
		this.input = new PlayerInputParser(frame,this);
	}
	
	public static void main(String[] args) {
		MobSpawnerTester gb = new MobSpawnerTester();
	}
}
