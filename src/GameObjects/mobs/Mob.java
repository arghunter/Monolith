//Author: Peter Ferolito 
//Date: 5/12/22
//Rev: 01
//Notes: An implementation of a mbo
package GameObjects.mobs;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
import GameObjects.Player.items.Item;
import general.Constants;
import mapGeneration.ItemGeneration;
import GameObjects.Direction;

public abstract class Mob extends MovingObject {
	// Fields
	// speed, damage, health, armor, attackspeed, attack range
	private int[] baseStats = new int[6];
	private int[] stats = new int[6];
	private double lastAttack = System.currentTimeMillis();
	private int playerLevel = 1;
	private int damageNumber = 0;
	private static final int DMG_DURATION = 500;
	private long dmgTime = 0;

	// Constructors
	public Mob(int x, int y, int movementDelay, int[] stats, int width, int height, String name, int numFrames) {
		super(x, y, movementDelay, width, height, name, numFrames, stats[2]);
		this.baseStats = stats;
	}

	// Sets this mobs stats
	public void setStat(int statNum, int newValue) {
		stats[statNum] = newValue;
	}

	// Returns the value of the given stat
	public int getStat(int statNum) {
		return stats[statNum];
	}

	// Sets all Stats
	public void setAllStats(int[] stats) {
		if (stats.length == 6) {
			this.stats = stats;
		}
	}

	// Returns the health of this mob
	public int getHealth() {
		return health;
	}

	public synchronized void takeDamage(Player player, int damage) {
		damage = damage / ((int) ((0.5 * Math.log(stats[1] * Math.log(stats[1]))) + 0.5) + 1) + 1;
		health -= damage;

		if (health < 0) {
			super.setDead(true);
			player.addXP((playerLevel + 8) * 8);
//			Item item = ItemGeneration.getItem(player, playerLevel, playerLevel / 5);
//			if (item != null) {
//				player.getInventory().addToStorage(item);
//			}
		}
		this.damageNumber = damage;
		this.dmgTime = System.currentTimeMillis();
	}

	public synchronized void takeDamageIgnoreArmor(Player player, int damage) {
		health -= damage;

		if (health < 0) {
			super.setDead(true);
			player.addXP((playerLevel + 8) * 8);
			Item item = ItemGeneration.getItem(player, playerLevel, playerLevel / 5);
			if (item != null) {
				player.getInventory().addToStorage(item);
			}
		}
		this.damageNumber = damage;
		this.dmgTime = System.currentTimeMillis();
	}

	// Makes this mob complete its next action, either moving or attacking the
	// player.
	public int action(Player player) {
		playerLevel = player.getLevel();
		updateAngle(player.getX(), player.getY());
		for (int i = 1; i < 4; i++) {
			stats[i] = baseStats[i] * playerLevel / 5;
		}
		stats[0] = baseStats[0];
		stats[4] = baseStats[4];
		stats[5] = baseStats[5];
		int curX = this.getX();
		int curY = this.getY();

		int diffX = curX - player.getX();
		int diffY = curY - player.getY();

		if ((diffX) * (diffX) + (diffY) * (diffY) < stats[5] * stats[5]) {
			if (System.currentTimeMillis() - lastAttack > 60000.0 / stats[4]) {
				player.takeDamage(stats[1]);
				lastAttack = System.currentTimeMillis();
			}

		} else {
			Direction toMove = Direction.NORTH;
			if (1.5 * Math.abs(diffX) < Math.abs(diffY)) {
				// Move up down
				if (diffY > 0) {
					toMove = Direction.NORTH;
				} else {
					toMove = Direction.SOUTH;
				}
			} else if (1.5 * Math.abs(diffY) < Math.abs(diffX)) {
				// Move left right
				if (diffX > 0) {
					toMove = Direction.WEST;
				} else {
					toMove = Direction.EAST;
				}
			} else {
				// Move diagonal
				if (diffX > 0 && diffY > 0) {
					toMove = Direction.NORTHWEST;
				} else if (diffX <= 0 && diffY > 0) {
					toMove = Direction.NORTHEAST;
				} else if (diffX > 0 && diffY <= 0) {
					toMove = Direction.SOUTHWEST;
				} else if (diffX <= 0 && diffY <= 0) {
					toMove = Direction.SOUTHEAST;
				}
			}
			if (diffX * diffX + diffY * diffY >= (stats[5] * stats[5])) {
				this.move(toMove);
			}
		}
		return 0;
	}

	public synchronized void render(Graphics2D g) {
		if (System.currentTimeMillis() - dmgTime < DMG_DURATION && this.damageNumber > 0) {
			g.setColor(Constants.TEXTCOLOR);
			Font text = null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

			g.setFont(text.deriveFont(30f));

			for (int i = 0; i < 1; i++) {
				try {
					g.drawString("" + Integer.toString(damageNumber), this.getX(),
							this.getY() - (System.currentTimeMillis() - dmgTime) / 6);

				} catch (Exception e) {
					i--;
				}
			}

		}
	}

}
