//Author: Peter Ferolito
//Date: 5/11/22
//Notes: The engine for Adventure

package render;

import GameObjects.Player.Player;

import input.PlayerInputParser;
import mapGeneration.MapGenerator;
import mapGeneration.MobSpawner;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import GameObjects.*;
import GameObjects.Player.Player;
import GameObjects.Player.items.materials.Material;
import GameObjects.mobs.*;
import general.SaveSystem;
import input.PlayerInputParser;

import ui.Button;
import ui.RenderableMenuItem;
import ui.SkillSelectionMenu;

import java.util.ArrayList;
import java.util.LinkedList;
import general.Constants;

public class Adventure implements ActionListener {
	private Player thePlayer;
	private PlayerInputParser input;

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
	private Mob[][][] mobList=new Mob[Constants.YSIZE][Constants.XSIZE][Constants.MAXMOBS];
	
	//Whether the game is paused and mobs move
	private boolean paused=false;
	
	private JPanel panel;
	
	public Adventure(Player player,JPanel panel) {
		thePlayer=player;
		this.panel=panel;
	}
	public Adventure() 
	{
//		thePlayer=new Player(300, 300, 1, 64, 64,this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	
	public void draw(Graphics2D g) {
		
	}
}
