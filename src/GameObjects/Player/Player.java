//Author: Armaan Gomes
//Date: 5/10/22
//Rev: 02
//Notes: A player in monolith
package GameObjects.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import GameObjects.Direction;
import GameObjects.MovingObject;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.weapons.MeleeWeapon;
import GameObjects.Player.items.weapons.Weapon;
import GameObjects.mobs.Mob;
import GameObjects.Player.items.consumables.Consumable;
import general.ImageSystem;
import skills.*;
import ui.PlayerUI;

public class Player extends MovingObject {
	// Fields
	SkillTree skills;
	private static StatType[] statTypes = { StatType.ACCURACY, StatType.ARMOR, StatType.ATTACKSPEED, StatType.HEALTH,
			StatType.POWER, StatType.REGEN, StatType.SHIELD, StatType.SPEED, StatType.STRENGTH, StatType.XP };
	private int[] stats = { 10, 25, 60, 100, 10, 30, 10000, 0, 10, 100 };
	private int[] buffs = new int[10];

	private int currentLevel = 0;
	private int currentXP = 0;
	private int xpToNextLevel = 1000;
	private PlayerUI ui;
	private Inventory inventory;
	private int currentShields;
	private boolean isDead = false;
	private long lastRegen;
	private ArrayList<Mob> mobs = new ArrayList<>();
	private ActionListener game;

	//Constructor
	public Player(int x, int y, int width, int height, ActionListener game, JPanel panel) {
		// Just going to use the helmet image for player
		super(x, y, 20, width, height, "DefaultHelmet", 1);

		inventory = new Inventory(this);
		super.dist = 2;
		stats[3] = (int) inventory.getHealth() + 100;
		stats[1] = (int) inventory.getArmor();
		stats[6] = (int) inventory.getShields();
		skills = new SkillTree(stats, statTypes);
		health = stats[3] + buffs[3];
		currentShields = stats[6];
		ui = new PlayerUI(this, panel);
		lastRegen = System.currentTimeMillis();
		this.game = game;

	}
	//Save data constructor
	public Player(int x, int y, int id, int width, int height, ActionListener game, JPanel panel, String saveData) {
		// Just going to use the helmet image for player
		super(x, y, 20, width, height, "DefaultHelmet", 1);

		inventory = new Inventory(this);
		stats[3] = (int) inventory.getHealth();
		stats[1] = (int) inventory.getArmor();
		stats[6] = (int) inventory.getShields();
		skills = new SkillTree(saveData, stats, statTypes);
		health = stats[3];
		currentShields = stats[6];
		lastRegen = System.currentTimeMillis();

		ui = new PlayerUI(this, panel);
		this.game = game;

	}

	// Alters the players health and shield values based on damage dealt and armor
	public void takeDamage(int damage) {
		if (!isDead) {
			damage = (int) ((2 * Math.log(stats[1] * Math.log(stats[1]))) + 0.5);
			if (currentShields > 0) {
				currentShields = Math.max(0, currentShields - damage);
			} else {
				health = Math.max(0, health - damage);
			}
			if (health <= 0) {
				isDead = true;
			}
			lastRegen = System.currentTimeMillis() + 1000;

		}

	}
	//Regens health and shields
	private void regen() {
		if (!isDead) {
			if (System.currentTimeMillis() - lastRegen >= 1000) {
				if (currentShields < stats[6] + buffs[6]) {
					currentShields += stats[5] + buffs[5];

				} else if (health < stats[3] + buffs[3]) {
					health += (stats[5] + buffs[5]) / 4;

				}

				lastRegen = System.currentTimeMillis();

			}
			if (currentShields > stats[6] + buffs[6]) {
				currentShields = stats[6] + buffs[6];
			}

			if (health > stats[3] + buffs[3]) {
				health = stats[3] + buffs[3];
			}
		}

	}
	//Returns the skilltree
	public SkillTree getSkills() {
		return skills;
	}
	//Returns the buff array
	public int[] getBuffs() {
		return buffs;
	}
	//Returns the stat types
	public StatType[] getStatTypes() {
		return statTypes;
	}
	//Returns the weapon
	public Weapon getWeapon() {
		Item i = inventory.getEquippedItem();
		if (i instanceof Weapon) {
			return (Weapon) i;
		} else {
			return (Weapon) new MeleeWeapon("Stick", 1, 10, 100, 10, Math.PI / 4);
		}
	}
	//Returns stats without buffs
	public int[] getStats() {
		return stats;
	}
	//Returns current health
	public int getCurrentHealth() {
		return health;
	}
	//Returns inventory
	public Inventory getInventory() {
		return inventory;
	}
	//Returns current shields
	public int getCurrentShields() {
		return currentShields;
	}
	//Returns isDead
	public boolean isDead() {
		return isDead;
	}
	//Returns current XP
	public int getXP() {
		return currentXP;
	}
	//Returns current level
	public int getLevel() {
		return currentLevel;
	}
	//Updates the ui
	public void updateUI() {
		ui.update();
	}

	@Override
	//Moves the player
	public void move(Direction direction) {
		if (!isDead) {
			super.move(direction);
		}
	}

	@Override
	//Updates the player angle
	public void updateAngle(double pointX, double pointY) {
		if (!isDead) {
			super.updateAngle(pointX, pointY);
		}
	}
	//Adds xp to the player and skilltree
	public void addXP(int xp) {
		if (!isDead) {
			skills.addXP(xp / 2);
			currentXP += (xp + 1) / 2;
			while (currentXP >= xpToNextLevel) {
				currentLevel++;
				currentXP -= xpToNextLevel;
				xpToNextLevel += currentLevel;

				game.actionPerformed(new ActionEvent(this, 88891, "LevelUp"));
			}
		}

	}
	//Uses the currently held item
	public void useItem(Graphics2D g) {
		if (!isDead) {
			Item item = inventory.getEquippedItem();
			if (item == null) {
				Weapon weapon = new MeleeWeapon("Fist", 1, 10, 100, 3, 300);
				weapon.primaryFire(mobs, this);
				return;
			}
			if (item.getType() == ItemType.WEAPON) {
				Weapon weapon = (Weapon) item;
				weapon.primaryFire(mobs, this);
			} else if (item.getType() == ItemType.CONSUMABLE) {
				Consumable consumable = (Consumable) item;
				consumable.consume();
			}
		}

	}
	//Sets the current mobs that are on screen
	public void setMobs(ArrayList<Mob> mobs) {
		this.mobs = mobs;
	}

	@Override
	//TODO to string part done
	public String toString() {

		return skills.toString();
	}
	//Renders the currently held weapon
	public void renderWeapon(Graphics2D g) {

		this.getWeapon().drawWeapon(this, g);

	}

	@Override
	//Renders the player
	public void render(Graphics2D g) {
		ui.draw(g);

		// System.out.println(new Inventory(inventory.toString(),this));

		renderWeapon(g);

		super.getImage().drawAnimation(g);
		regen();
		super.setMovementDelay(stats[7] + buffs[7]);

	}

}
