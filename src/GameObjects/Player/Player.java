package GameObjects.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import GameObjects.MovingObject;
import skills.*;
import ui.ImageSystem;

public class Player extends MovingObject {
	SkillTree skills;
	private static StatType[] statTypes = { StatType.ACCURACY, StatType.ARMOR, StatType.ATTACKSPEED, StatType.HEALTH,
			StatType.POWER, StatType.REGEN, StatType.SHIELD, StatType.SPEED, StatType.STRENGTH,StatType.XP };
	private int[] stats = { 10, 25, 60, 100, 10, 1, 100, 15, 10,100 };
	private int currentHealth;
	private int currentShields;
	private boolean isDead = false;
	private long lastRegen;

	// Note the speed will come from skill tree
	public Player(int x, int y, int id, int width, int height) {
		// Just going to use the helmet image for player
		super(x, y, 20, id, width, height, "DefaultHelmet",1);
		skills = new SkillTree(stats, statTypes);
		currentHealth = stats[3];
		currentShields = stats[6];
		lastRegen=System.currentTimeMillis();

	}

	// alters the players health and shield values based on damage dealt and armor
	public void takeDamage(int damage) {
		damage = (int) ((2 * Math.log(stats[1] * Math.log(stats[1]))) + 0.5);
		if (currentShields > 0) {
			currentShields -= damage;
		} else {
			currentHealth -= damage;
		}
		if (currentHealth <= 0) {
			isDead = true;
		}
	}

	private void regen() {
		if (System.currentTimeMillis() - lastRegen >= 1000) {
			if (currentShields < stats[6]) {
				currentShields += stats[5];
				if (currentShields > stats[6]) {
					currentShields = stats[6];
				}
			}
			if (currentHealth < stats[3]) {
				currentHealth += stats[5] / 2;
				if (currentHealth > stats[3]) {
					currentHealth = stats[3];
				}
			}
			lastRegen = System.currentTimeMillis();

		}
	}

	public SkillTree getSkills() {
		return skills;
	}

	public static StatType[] getStatTypes() {
		return statTypes;
	}

	public int[] getStats() {
		return stats;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getCurrentShields() {
		return currentShields;
	}

	public boolean isDead() {
		return isDead;
	}


	
	@Override
	public void render(Graphics2D g) {
		// super.refillLastPos(g);
		super.getImage().drawAnimation(g);
		regen();
		super.setMovementDelay(stats[7]);

	}

}
