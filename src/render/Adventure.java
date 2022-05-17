//Author: Peter Ferolito
//Date: 5/11/22
//Notes: The engine for Adventure

package render;

import input.PlayerInputParser;
import mapGeneration.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import GameObjects.*;
import GameObjects.mobs.*;
import GameObjects.Player.*;
import GameObjects.Player.items.*;
import GameObjects.Player.items.armor.*;
import GameObjects.Player.items.blueprints.*;
import GameObjects.Player.items.consumables.*;
import GameObjects.Player.items.materials.*;
import GameObjects.Player.items.weapons.*;
import general.SaveSystem;
import input.*;

import ui.*;

import java.util.ArrayList;
import java.util.LinkedList;

import general.Collider;
import general.Constants;

public class Adventure {
	private Player player;

	
	//Coordinates of the current room
	private int curRoomX=0;
	private int curRoomY=0;
	
	//Time since the last spawning of mobs (in actions)
	private int[][] timeSinceLastSpawn=new int[Constants.YSIZE][Constants.XSIZE];
	
	
	//Spawners and generators
	//Use mobSpawner to get random mobs to spawn
	//Use mapGenerator to get rooms of the map
	private MobSpawner mobSpawner=new MobSpawner();
	private MapGenerator mapGenerator=new MapGenerator();

	
	//The current room the player is in
	private String[][] curRoom;
	
	//Stores the current list of mobs in the room
	private ArrayList<Mob>[][] mobList=new ArrayList[Constants.YSIZE][Constants.XSIZE];
	

	
	private PauseMenu pauseMenu;
	private PlayerInputParser input;
	//Time
	private int t=0;
	
	//Current background color
	Color bgColor=Color.WHITE;
	
	
	public Adventure(Player player,PlayerInputParser input,JPanel panel) {
		Main.status=GameStatus.RUNNING;
		this.player=player;
		pauseMenu=new PauseMenu(player,panel,input);
		this.input=input;
		pauseMenu.setHidden(true);
		for(int i=0;i<Constants.YSIZE;i++) {
			for(int j=0;j<Constants.XSIZE;j++) {
				timeSinceLastSpawn[i][j]=-5000;
				mobList[i][j]=new ArrayList<Mob>();
			}
		}
		player.setMobs(mobList[curRoomY][curRoomX]);
		
	}
	

	
	public void actions() {
		t++;
		if(t>2000000000) {
			t-=2000000000;
			for(int i=0;i<Constants.YSIZE;i++) {
				for(int j=0;j<Constants.XSIZE;j++) {
					timeSinceLastSpawn[i][j]-=2000000000;
				}
			}
		}
		
		//Check for death of mobs
		if(!(mobList[curRoomY][curRoomX]==null)) {
			for(int i=0;i<mobList[curRoomY][curRoomX].size();i++) {
				if(mobList[curRoomY][curRoomX].get(i).isDead()) {
					mobList[curRoomY][curRoomX].remove(i);
				}
			}
		}
		
		//If the player is off the map, move the player and change the room
		if(player.getX()>=mapGenerator.getRoomSizeX()*32 && (curRoomX+1<mapGenerator.getXSize())) {
			curRoomX++;
			player.setCoordsMove(player.getX()-32*(mapGenerator.getRoomSizeX()-1), player.getY());
			changeRoom();
		}else if(player.getX()<=0 && (curRoomX-1>=0)) {
			curRoomX--;
			player.setCoordsMove(player.getX()+32*(mapGenerator.getRoomSizeX()-1), player.getY());
			changeRoom();
		}else if(player.getY()>=mapGenerator.getRoomSizeY()*32 && (curRoomY+1<mapGenerator.getYSize())) {
			curRoomY++;
			player.setCoordsMove(player.getX(),player.getY()-32*(mapGenerator.getRoomSizeY()-1));
			changeRoom();
		}else if(player.getY()<=0 && (curRoomY-1>=0)) {
			curRoomY--;
			player.setCoordsMove(player.getX(),player.getY()+32*(mapGenerator.getRoomSizeY()-1));
			changeRoom();
		}
		
		//Spawn new mobs
		if(!(mobList[curRoomY][curRoomX]==null)) {
			if(t-timeSinceLastSpawn[curRoomY][curRoomX]>5000) {
				int[] n=mobSpawner.generateMobs(player.getLevel());
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
				resetMobSpawnTime();
			}
			for(int i=0;i<mobList[curRoomY][curRoomX].size();i++) {
				mobList[curRoomY][curRoomX].get(i).action(player);
			}
		}
	}
	
	private void resetMobSpawnTime() {
		timeSinceLastSpawn[curRoomY][curRoomX]=t;
	}

	
	public void changeRoom() {
		player.setMobs(mobList[curRoomY][curRoomX]);
	}
	
	//Sets the background color
	public void setBackground(Color c) {
		bgColor=c;
	}
	
	//Paint the background
	public void paintBackground(Graphics2D g) {
		g.setColor(bgColor);
		g.fillRect(0,0,(int)Main.WIDTH+1,(int)Main.HEIGHT+1);
	}
	
	
	//Render the player and the mobs
	public void draw(Graphics2D g,int JPanelX,int JPanelY) {

		if(Main.status==GameStatus.RUNNING) {

			this.actions();
			curRoom=mapGenerator.getRoom(curRoomX, curRoomY);
			Collider collider = new Collider(curRoom);
			paintBackground(g);
			if(!(curRoom==null)) {
				for(int i=0;i<mapGenerator.getRoomSizeY();i++) {
					for(int j=0;j<mapGenerator.getRoomSizeX();j++) {
						g.setColor(Color.WHITE);
						if(curRoom[i][j].equals("11")) {
							g.setColor(Color.BLACK);
						}else if(curRoom[i][j].equals("22")) {
							g.setColor(Color.RED);
						}
						g.fillRect(j*32,i*32,32,32);
					}
				}
			}
		
			for(int i=0;i<mobList[curRoomY][curRoomX].size();i++) {
				if(!(curRoom==null)) {
					mobList[curRoomY][curRoomX].get(i).render(g);
				}
			}
			player.render(g);
			collider.checkCollides(player.getRect(),player);
			if(input.isEscapePressed()) 
			{
				Main.status=GameStatus.PAUSED;
				pauseMenu.setHidden(false);
			}
			if(pauseMenu.getStatus()==GameStatus.RUNNING) 
			{
				Main.status=GameStatus.RUNNING;
			}
		}
		pauseMenu.draw(g, JPanelX, JPanelY);

	}
	
	
}
