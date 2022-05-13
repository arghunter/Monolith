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
	SkillTree skills;
	private static StatType[] statTypes = { StatType.ACCURACY, StatType.ARMOR, StatType.ATTACKSPEED, StatType.HEALTH,
			StatType.POWER, StatType.REGEN, StatType.SHIELD, StatType.SPEED, StatType.STRENGTH, StatType.XP };
	private int[] stats = { 10, 25, 60, 100, 10, 30, 100, 0, 10, 100 };
	private int[] buffs =new int[10];

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

	// Note the speed will come from skill tree
	public Player(int x, int y, int id, int width, int height, ActionListener game, JPanel panel) {
		// Just going to use the helmet image for player
		super(x, y, 20, id, width, height, "DefaultHelmet", 1);

		inventory = new Inventory(this);
		super.dist=2;
		stats[3] = (int) inventory.getHealth()+100;
		stats[1] = (int) inventory.getArmor();
		stats[6] = (int) inventory.getShields();
		skills = new SkillTree(stats, statTypes);
		health = stats[3]+buffs[3];
		currentShields = stats[6];
		ui = new PlayerUI(this, panel);
		lastRegen = System.currentTimeMillis();
		this.game = game;

	}

	public Player(int x, int y, int id, int width, int height, ActionListener game, JPanel panel, String saveData) {
		// Just going to use the helmet image for player
		super(x, y, 20, id, width, height, "DefaultHelmet", 1);

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

	// alters the players health and shield values based on damage dealt and armor
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
			lastRegen = System.currentTimeMillis()+1000;

		}

	}

	private void regen() {
//		System.out.println(stats[3]);
		if (!isDead) {
			if (System.currentTimeMillis() - lastRegen >= 1000) {
				if (currentShields < stats[6]+buffs[6]) {
					currentShields += stats[5]+buffs[5];

				} else if (health < stats[3]+buffs[3]) {
					health += (stats[5]+buffs[5]) / 4;

				}

				lastRegen = System.currentTimeMillis();

			}
			if (currentShields > stats[6]+buffs[6]) {
				currentShields = stats[6]+buffs[6];
			}

			if (health > stats[3]+buffs[3]) {
				health = stats[3]+buffs[3];
			}
		}

	}

	public SkillTree getSkills() {
		return skills;
	}
	public int[] getBuffs() 
	{
		return buffs;
	}

	public StatType[] getStatTypes() {
		return statTypes;
	}

	public Weapon getWeapon() {
		Item i = inventory.getEquippedItem();
		if(i instanceof Weapon) {
			return (Weapon)i;
		} else {
			return (Weapon) new MeleeWeapon("Stick",1,10,100,10,Math.PI/4);
		}
	}
	public int[] getStats() {
		return stats;
	}

	public int getCurrentHealth() {
		return health;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public int getCurrentShields() {
		return currentShields;
	}

	public boolean isDead() {
		return isDead;
	}

	public int getXP() {
		return currentXP;
	}

	public int getLevel() {
		return currentLevel;
	}
	public void updateUI() 
	{
		ui.update();
	}

	@Override
	public void move(Direction direction) {
		if (!isDead) {
			super.move(direction);
		}
	}

	@Override
	public void updateAngle(double pointX, double pointY) {
		if (!isDead) {
			super.updateAngle(pointX, pointY);
		}
	}

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

	public void useItem(Graphics2D g) {
		if (!isDead) {
			Item item = inventory.getEquippedItem();
			if (item == null) {
				Weapon weapon=new MeleeWeapon("Fist",1,10,100,3,300);
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

	public void setMobs(ArrayList<Mob> mobs) {
		this.mobs = mobs;
	}

	@Override
	public String toString() {

		return skills.toString();
	}
	public void renderWeapon(Graphics2D g) {

		this.getWeapon().drawWeapon(this,g);

	}
	@Override
	public void render(Graphics2D g) {
		ui.draw(g);

		System.out.println(new Inventory(inventory.toString(),this));

		renderWeapon(g);

		super.getImage().drawAnimation(g);
		regen();
		super.setMovementDelay(stats[7]+buffs[7]);

	}

}
