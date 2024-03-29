//Author: Peter Ferolito 
//Date: 5/11/22
//Rev:01
//Notes: The engine for Adventure

package render;

import input.PlayerInputParser;
import java.util.Random;
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

public class Adventure implements Runnable {
	private static Player player;

	// Coordinates of the current room
	private static int curRoomX = 0;
	private static int curRoomY = 0;

	// Time since the last spawning of mobs (in actions)
	private int[][] timeSinceLastSpawn = new int[Constants.YSIZE][Constants.XSIZE];

	// Spawners and generators
	// Use mobSpawner to get random mobs to spawn
	// Use mapGenerator to get rooms of the map
	private MobSpawner mobSpawner = new MobSpawner();
	private MapGenerator mapGenerator = new MapGenerator();

	// The current room the player is in
	private String[][] curRoom;

	// Stores the current list of mobs in the room
	private static ArrayList<Mob>[][] mobList = new ArrayList[Constants.YSIZE][Constants.XSIZE];

	private PauseMenu pauseMenu;
	private PlayerInputParser input;
	// Time
	private int t = 0;
	private TextureGenerator texture;
	private Thread thread;
	private Collider collider;
	private int topLeftCornerX = ((int) Main.WIDTH - (Constants.ROOMSIZEX * 32)) / 2;
	private int topLeftCornerY = ((int) Main.HEIGHT - (Constants.ROOMSIZEY * 32)) / 2;
	private long initTime = System.currentTimeMillis();
	private Graphics2D g;
	private Random rand = new Random();
	//Constructor
	public Adventure(Player player, PlayerInputParser input, JPanel panel) {
		Main.status = GameStatus.RUNNING;
		this.player = player;
		this.input = input;
		curRoom = mapGenerator.getRoom(curRoomX, curRoomY);
		input.setRoom(curRoom, topLeftCornerX, topLeftCornerY);
		for (int i = 0; i < Constants.YSIZE; i++) {
			for (int j = 0; j < Constants.XSIZE; j++) {
				timeSinceLastSpawn[i][j] = -5000;
				mobList[i][j] = new ArrayList<Mob>();
			}
		}
		player.setMobs(mobList[curRoomY][curRoomX]);
		texture = new TextureGenerator(curRoom, curRoomX * (curRoomY) + curRoomX + curRoomY + initTime, topLeftCornerX,
				topLeftCornerY, 1);

		mapGenerator.visitRoom(curRoomX, curRoomY);
		pauseMenu = new PauseMenu(player, panel, initTime, mapGenerator);
		start();

	}
	//Returns the topleftCornerX
	public int getTLCX() {
		return topLeftCornerX;
	}
	//Returns the topLeftCornerY
	public int getTLCY() {
		return topLeftCornerY;
	}
	//Returns the current room
	public String[][] getRoom() {
		return curRoom;
	}
	//All of the room management code
	public synchronized void actions() {



		t++;
		if (t > 20000) {
			t -= 20000;
			for (int i = 0; i < Constants.YSIZE; i++) {
				for (int j = 0; j < Constants.XSIZE; j++) {
					timeSinceLastSpawn[i][j] -= 20000;
				}
			}
		}

		// Check for death of mobs
		if (!(mobList[curRoomY][curRoomX] == null)) {
			for (int i = 0; i < mobList[curRoomY][curRoomX].size(); i++) {
				if (mobList[curRoomY][curRoomX].get(i).isDead()) {
					mobList[curRoomY][curRoomX].remove(i);
				}
			}
		}
		synchronized (player) {
			// If the player is off the map, move the player and change the room
			if ((player.getX() > topLeftCornerX + Constants.ROOMSIZEX * 32) && (curRoomX + 1 < Constants.ROOMSIZEX)) {
				curRoomX++;
				player.setCoordsMove(player.getX() - 32 * (Constants.ROOMSIZEX - 1), player.getY());
				changeRoom();
			} else if ((player.getX() < topLeftCornerX) && (curRoomX - 1 >= 0)) {
				curRoomX--;
				player.setCoordsMove(player.getX() + 32 * (Constants.ROOMSIZEX - 1), player.getY());
				changeRoom();
			} else if ((player.getY() > topLeftCornerY + Constants.ROOMSIZEY * 32)
					&& (curRoomY + 1 < Constants.ROOMSIZEY)) {
				curRoomY++;
				player.setCoordsMove(player.getX(), player.getY() - 32 * (Constants.ROOMSIZEY - 1));
				changeRoom();
			} else if (player.getY() < topLeftCornerY && (curRoomY - 1 >= 0)) {
				curRoomY--;
				player.setCoordsMove(player.getX(), player.getY() + 32 * (Constants.ROOMSIZEY - 1));
				changeRoom();
			}
		}
	}
	//Resets mob spawn time
	private void resetMobSpawnTime() {
		timeSinceLastSpawn[curRoomY][curRoomX] = t;
	}
	//All of the code required to change rooms
	public void changeRoom() {
		synchronized (mobList) {
			player.setMobs(mobList[curRoomY][curRoomX]);
		}
		player.getInventory().clearAllProjectiles();
		player.clearAbilities();
		curRoom = mapGenerator.getRoom(curRoomX, curRoomY);
		texture = new TextureGenerator(curRoom, curRoomX * (curRoomY) + curRoomX + curRoomY + initTime, topLeftCornerX,
				topLeftCornerY, 1);
		input.setRoom(curRoom, topLeftCornerX, topLeftCornerY);
		collider = new Collider(curRoom, topLeftCornerX, topLeftCornerY);
		mapGenerator.visitRoom(curRoomX, curRoomY);

	}



	// Paint the background
	public void paintBackground(Graphics2D g) {
		g.setColor(new Color(212 / 6, 175 / 6, 55 / 6));
		g.fillRect(0, 0, (int) Main.WIDTH + 1, (int) Main.HEIGHT + 1);
	}

	// Render the player and the mobs
	public void draw(Graphics2D g, int JPanelX, int JPanelY) {

		if (Main.status == GameStatus.RUNNING) {

			paintBackground(g);
			// Spawn new mobs

			if (texture != null) {
				texture.draw(g);
			}

			for (int i = 0; i < mobList[curRoomY][curRoomX].size(); i++) {
				if (!(mobList[curRoomY][curRoomX] == null)) {
					mobList[curRoomY][curRoomX].get(i).render(g);
				}
			}

			player.render(g);

		}
		if (input.isEscapePressed()) {
			if (Main.status == GameStatus.RUNNING) {
				Main.status = GameStatus.PAUSED;
			} else if (Main.status == GameStatus.PAUSED) {
				Main.status = GameStatus.RUNNING;
			}
		}
		pauseMenu.draw(g, JPanelX, JPanelY);
	}

	//Retuns the currentMobs
	public static ArrayList<Mob> getMobs() {
		return Adventure.mobList[curRoomY][curRoomX];
	}
	//Returns the current player
	public static Player getPlayer() {
		return player;
	}
	//Starts Adventure
	public void start() {
		if (thread == null) {
			thread = new Thread(this, "" + System.currentTimeMillis());
			thread.start();
		}
	}

	@Override
	//Runs adventure and spawns mobs
	public void run() {
		while (!player.isDead()) {
			if (Main.status == GameStatus.RUNNING) {
				this.actions();
				curRoom = mapGenerator.getRoom(curRoomX, curRoomY);
				if (collider == null) {
					collider = new Collider(curRoom, topLeftCornerX, topLeftCornerY);

				}

				// Spawn new mobs
				if (!(mobList[curRoomY][curRoomX] == null) && !(curRoomX == 0 && curRoomY == 0)) {
					if (t - timeSinceLastSpawn[curRoomY][curRoomX] > 5000) {
						ArrayList<Integer> n = mobSpawner.generateMobs(player.getLevel());
						for (int i = 0; i < n.size(); i++) {
							if (n.get(i) == 0) {
								mobList[curRoomY][curRoomX].add(new Spider(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 1) {
								mobList[curRoomY][curRoomX].add(new Zombie(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 2) {
								mobList[curRoomY][curRoomX].add(new Goblin(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 3) {
								mobList[curRoomY][curRoomX].add(new Orc(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 4) {
								mobList[curRoomY][curRoomX].add(new Hobgoblin(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 5) {
								mobList[curRoomY][curRoomX].add(new Shadow(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 6) {
								mobList[curRoomY][curRoomX].add(new ArmorPlant(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 7) {
								mobList[curRoomY][curRoomX].add(new SpeedPlant(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 8) {
								mobList[curRoomY][curRoomX].add(new HealthPlant(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 9) {
								mobList[curRoomY][curRoomX].add(new DamagePlant(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 10) {
								mobList[curRoomY][curRoomX].add(new PottedPlant(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 11) {
								mobList[curRoomY][curRoomX].add(new Cactus(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 12) {
								mobList[curRoomY][curRoomX].add(new Sporeshroom(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 13) {
								mobList[curRoomY][curRoomX].add(new Troll(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 14) {
								mobList[curRoomY][curRoomX].add(new Balkrada(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							} else if (n.get(i) == 15) {
								mobList[curRoomY][curRoomX].add(new IceDrake(
										topLeftCornerX + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEX - 4)),
										topLeftCornerY + 64 + 32 * (int) (Math.random() * (Constants.ROOMSIZEY - 4))));
							}
							if (collider.isColliding(
									mobList[curRoomY][curRoomX].get(mobList[curRoomY][curRoomX].size() - 1).getRect(),
									mobList[curRoomY][curRoomX].get(mobList[curRoomY][curRoomX].size() - 1))) {
								mobList[curRoomY][curRoomX].remove(mobList[curRoomY][curRoomX].size() - 1);
							}
						}
						resetMobSpawnTime();
					}
					for (int i = 0; i < mobList[curRoomY][curRoomX].size(); i++) {
						mobList[curRoomY][curRoomX].get(i).action(player);
					}
				}

				for (int i = 0; i < mobList[curRoomY][curRoomX].size(); i++) {
					if (!(mobList[curRoomY][curRoomX] == null)) {
						collider.checkCollides(mobList[curRoomY][curRoomX].get(i).getRect(),
								mobList[curRoomY][curRoomX].get(i));

					}
				}

				try {
					thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (player.isDead()) {
			curRoomX = 0;
			curRoomY = 0;
		}

	}

}
