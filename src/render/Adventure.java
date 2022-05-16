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

public class Adventure implements ActionListener {
	private Player player;

	private JFrame frame;
	private Timer clock=new Timer(10, this);
	
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
	
	//Whether the game is paused and mobs move
	private boolean paused=false;
	
	
	
	public Adventure(Player player) {
		this.player=player;
	
	}
	

	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	
	public void draw(Graphics2D g) {

		curRoom=mapGenerator.getRoom(curRoomX, curRoomY);
		Collider collider = new Collider(curRoom);
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
				mobList[curRoomY][curRoomX].get(i).update(player.getX(), player.getY());
			}
		}

		
		collider.checkCollides(player.getRect(),player);
	}
	
	
}
