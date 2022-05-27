//Author: Peter Ferolito 
//Date: 5/12/22
//Rev: 01
//Notes: An implementation of a mob
package GameObjects.mobs;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
import GameObjects.Player.items.Item;
import general.AudioPlayer;
import general.Constants;
import mapGeneration.ItemGeneration;
import GameObjects.Direction;

public abstract class Mob extends MovingObject {
	// Fields
	// speed, damage, health, armor, attackspeed, attack range
	private int[] baseStats = new int[6];
	protected int[] stats = new int[6];
	protected double lastAttack = System.currentTimeMillis();
	protected int playerLevel = 1;
	private int damageNumber = 0;
	private static final int DMG_DURATION = 500;
	private long dmgTime = 0;
	private int xpDropped = 1;
	protected int sound;
	protected long lastSound = System.currentTimeMillis();

	// Constructors
	public Mob(int x, int y, int movementDelay, int[] stats, int width, int height, String name, int numFrames,
			int xp) {
		super(x, y, movementDelay, width, height, name, numFrames, stats[2]);
		this.baseStats = stats;
		Random rng = new Random();
		sound = rng.nextInt(10) + 1;
		xpDropped = xp;
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

	// Makes this mob take damage
	public void takeDamage(Player player, int damage) {
		damage = (int) (damage / Math.log(18 + stats[4]) + damage * 0.2);
		health -= damage;

		if (health < 0 && !super.isDead()) {
			super.setDead(true);
			player.addXP(xpDropped);
			Item item = ItemGeneration.getItem(player, playerLevel, playerLevel / 5);
			try {
				System.out.println(item);
				player.getInventory().addToStorage(item);
			} catch(Exception e) {
				
			}
		}
		this.damageNumber = damage;
		this.dmgTime = System.currentTimeMillis();
	}

	// Makes this mob take damagew withoug accounting for armor
	public void takeDamageIgnoreArmor(Player player, int damage) {
		health -= damage;

		if (health < 0 && !super.isDead()) {
			super.setDead(true);
			player.addXP((playerLevel + 8) * 8);
			Item item = ItemGeneration.getItem(player, playerLevel, playerLevel / 5);
			if (item != null) {
				try {
					player.getInventory().addToStorage(item);
				} catch(Exception e) {
					
				}
			}
		}
		this.damageNumber = damage;
		this.dmgTime = System.currentTimeMillis();
	}

	// Makes this mob complete its next action, either moving or attacking the
	// player.
	public void action(Player player) {

		playerLevel = player.getLevel();
		updateAngle(player.getX(), player.getY());
		for (int i = 1; i < 4; i++) {
			stats[i] = baseStats[i]+(int)Math.log(baseStats[i]) * (playerLevel+1) / 4;
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
				if (System.currentTimeMillis() - lastSound > 3000 + Math.random() * 2000) {
					new AudioPlayer("monster_000" + sound, AudioPlayer.ONE_TIME);

				}

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

	}

	// Renders this mob
	public void render(Graphics2D g) {
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
