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
import GameObjects.Projectiles.Projectile;
import GameObjects.Projectiles.StraightProjectile;
import general.SaveSystem;
import input.*;

import ui.*;
import ui.MapVisual;

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
	

	private StraightProjectile projectile;
	private PauseMenu pauseMenu;
	private PlayerInputParser input;
	//Time
	private int t=0;
	private TextureGenerator texture;
	
	private int topLeftCornerX=((int)Main.WIDTH-(Constants.ROOMSIZEX*32))/2;
	private int topLeftCornerY=((int)Main.HEIGHT-(Constants.ROOMSIZEY*32))/2;
	private long initTime=System.currentTimeMillis();
	//Current background color
	Color bgColor=Color.WHITE;
	
	
	public Adventure(Player player,PlayerInputParser input,JPanel panel) {
		Main.status=GameStatus.RUNNING;
		this.player=player;
		this.input=input;
		for(int i=0;i<Constants.YSIZE;i++) {
			for(int j=0;j<Constants.XSIZE;j++) {
				timeSinceLastSpawn[i][j]=-5000;
				mobList[i][j]=new ArrayList<Mob>();
			}
		}
		player.setMobs(mobList[curRoomY][curRoomX]);
		curRoom=mapGenerator.getRoom(curRoomX, curRoomY);
		texture=new TextureGenerator(curRoom,curRoomX*(curRoomY)+curRoomX+curRoomY+initTime,topLeftCornerX,topLeftCornerY,1);
		input.setRoom(curRoom,topLeftCornerX,topLeftCornerY);
		mapGenerator.visitRoom(curRoomX, curRoomY);
		pauseMenu=new PauseMenu(player,panel,initTime,mapGenerator);
		System.out.println(curRoom);
		projectile = new StraightProjectile(player.getX(),player.getY());
		projectile.setRoom(curRoom);
		System.out.println("hi");
	}
	
	public int getTLCX() {
		return topLeftCornerX;
	}
	
	public int getTLCY() {
		return topLeftCornerY;
	}
	public String[][] getRoom() {
		return curRoom;
	}
	public void actions() {

		topLeftCornerX=(int)Main.WIDTH/2-(Constants.ROOMSIZEX*16);
		topLeftCornerY=(int)Main.HEIGHT/2-(Constants.ROOMSIZEY*16);
	
		
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
		if((player.getX()>topLeftCornerX+Constants.ROOMSIZEX*32) && (curRoomX+1<Constants.ROOMSIZEX)) {
			curRoomX++;
			player.setCoordsMove(player.getX()-32*(Constants.ROOMSIZEX-1), player.getY());
			changeRoom();
		}else if((player.getX()<topLeftCornerX) && (curRoomX-1>=0)) {
			curRoomX--;
			player.setCoordsMove(player.getX()+32*(Constants.ROOMSIZEX-1), player.getY());
			changeRoom();
		}else if((player.getY()>topLeftCornerY+Constants.ROOMSIZEY*32) && (curRoomY+1<Constants.ROOMSIZEY)) {
			curRoomY++;
			player.setCoordsMove(player.getX(),player.getY()-32*(Constants.ROOMSIZEY-1));
			changeRoom();
		}else if(player.getY()<topLeftCornerY && (curRoomY-1>=0)) {
			curRoomY--;
			player.setCoordsMove(player.getX(),player.getY()+32*(Constants.ROOMSIZEY-1));
			changeRoom();
		}
	}
	
	private void resetMobSpawnTime() {
		timeSinceLastSpawn[curRoomY][curRoomX]=t;
	}

	
	public void changeRoom() {
		player.setMobs(mobList[curRoomY][curRoomX]);
		curRoom=mapGenerator.getRoom(curRoomX, curRoomY);
		texture=new TextureGenerator(curRoom,curRoomX*(curRoomY)+curRoomX+curRoomY+initTime,topLeftCornerX,topLeftCornerY,1);
		input.setRoom(curRoom,topLeftCornerX,topLeftCornerY);
		
		mapGenerator.visitRoom(curRoomX, curRoomY);
		
	}
	
	//Sets the background color
	public void setBackground(Color c) {
		bgColor=c;
	}
	
	//Paint the background
	public void paintBackground(Graphics2D g) {
		g.setColor(new Color(212/6,175/6,55/6) );
		g.fillRect(0,0,(int)Main.WIDTH+1,(int)Main.HEIGHT+1);
	}
	
	
	//Render the player and the mobs
	public void draw(Graphics2D g,int JPanelX,int JPanelY) {
		if(Main.status==GameStatus.RUNNING) {

			
			this.actions();
			curRoom=mapGenerator.getRoom(curRoomX, curRoomY);
			Collider collider = new Collider(curRoom,topLeftCornerX,topLeftCornerY);
			paintBackground(g);
			projectile.setRoom(curRoom);
			//Spawn new mobs
			if(!(mobList[curRoomY][curRoomX]==null)) {
				if(t-timeSinceLastSpawn[curRoomY][curRoomX]>5000) {
					int[] n=mobSpawner.generateMobs(player.getLevel());
					for(int i=0;i<n.length;i++) {
						System.out.print(n[i]+" ");
						if(n[i]==0) {
							mobList[curRoomY][curRoomX].add(new Zombie(topLeftCornerX+64+32*(int) (Math.random() * (Constants.ROOMSIZEX-4)), topLeftCornerY+64+32*(int) (Math.random() * (Constants.ROOMSIZEY-4)), 64, 64));
						}else if(n[i]==1) {
							mobList[curRoomY][curRoomX].add(new Spider(topLeftCornerX+64+32*(int) (Math.random() * (Constants.ROOMSIZEX-4)), topLeftCornerY+64+32*(int) (Math.random() * (Constants.ROOMSIZEY-4)), 64, 64));
						}else if(n[i]==2) {
							mobList[curRoomY][curRoomX].add(new Balkrada(topLeftCornerX+64+32*(int) (Math.random() * (Constants.ROOMSIZEX-4)), topLeftCornerY+64+32*(int) (Math.random() * (Constants.ROOMSIZEY-4)), 96, 187));
						}
						if(collider.isColliding(mobList[curRoomY][curRoomX].get(mobList[curRoomY][curRoomX].size()-1).getRect(),mobList[curRoomY][curRoomX].get(mobList[curRoomY][curRoomX].size()-1))){
							mobList[curRoomY][curRoomX].remove(mobList[curRoomY][curRoomX].size()-1);
						}
					}
					resetMobSpawnTime();
				}
				for(int i=0;i<mobList[curRoomY][curRoomX].size();i++) {
					mobList[curRoomY][curRoomX].get(i).action(player);
				}
			}
			projectile.draw(g);
//			System.out.println("Here");
			if(texture!=null) 
			{
				texture.draw(g);
			}
//			if(!(curRoom==null)) {
//				for(int i=0;i<mapGenerator.getRoomSizeY();i++) {
//					for(int j=0;j<mapGenerator.getRoomSizeX();j++) {
//						g.setColor(Color.WHITE);
//						if(curRoom[i][j].equals("11")) {
//							g.setColor(Color.BLACK);
//						}else if(curRoom[i][j].equals("22")) {
//							g.setColor(Color.RED);
//						}
//						g.fillRect(topLeftCornerX+j*32,topLeftCornerY+i*32,32,32);
//					}
//				}
//			}
			for(int i=0;i<mobList[curRoomY][curRoomX].size();i++) {
				if(!(mobList[curRoomY][curRoomX]==null)) {
					collider.checkCollides(mobList[curRoomY][curRoomX].get(i).getRect(),mobList[curRoomY][curRoomX].get(i));
					mobList[curRoomY][curRoomX].get(i).render(g);
				}
			}
//			collider.checkCollides(player.getRect(),player);
			

			if(input.isEscapePressed()) 
			{
				if(Main.status==GameStatus.RUNNING)
					Main.status=GameStatus.PAUSED;
				else if(Main.status==GameStatus.RUNNING) 
				{
					Main.status=GameStatus.PAUSED;
				}
			}
			 
			
			
			
			player.render(g);

		}
		pauseMenu.draw(g, JPanelX, JPanelY);

	}
	
	
}
