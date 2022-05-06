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
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import GameObjects.MovingObject;
import general.ImageSystem;
import skills.*;
import java.io.Serializable;
public class Player extends MovingObject{
	SkillTree skills;
	private static StatType[] statTypes = { StatType.ACCURACY, StatType.ARMOR, StatType.ATTACKSPEED, StatType.HEALTH,
			StatType.POWER, StatType.REGEN, StatType.SHIELD, StatType.SPEED, StatType.STRENGTH,StatType.XP };
	private int[] stats = { 10, 25, 60, 100, 10, 30, 100, 0, 10,100 };
	
	private int currentLevel=0;
	private int currentXP=0;
	private int xpToNextLevel=1;
	
	private Inventory inventory;
	private int currentShields;
	private boolean isDead = false;
	private long lastRegen;
	private ActionListener game;
	

	// Note the speed will come from skill tree
	public Player(int x, int y, int id, int width, int height,ActionListener game) {
		// Just going to use the helmet image for player
		super(x, y, 20, id, width, height, "DefaultHelmet",1);
	
		inventory=new Inventory();
		
		stats[3]=(int)inventory.getHealth();
		stats[1]=(int)inventory.getArmor();
		stats[6]=(int) inventory.getShields();
		skills = new SkillTree(stats, statTypes);
		health = stats[3];
		currentShields = stats[6];
		lastRegen=System.currentTimeMillis();
		this.game=game;
		
		

	}
	public Player(int x, int y, int id, int width, int height,ActionListener game, String saveData) {
		// Just going to use the helmet image for player
		super(x, y, 20, id, width, height, "DefaultHelmet",1);
		skills = new SkillTree(saveData,stats, statTypes);
		health = stats[3];
		currentShields = stats[6];
		lastRegen=System.currentTimeMillis();
		this.game=game;


	}

	// alters the players health and shield values based on damage dealt and armor
	public void takeDamage(int damage) {
		damage = (int) ((2 * Math.log(stats[1] * Math.log(stats[1]))) + 0.5);
		if (currentShields > 0) {
			currentShields -= damage;
		} else {
			health -= damage;
		}
		if (health <= 0) {
			isDead = true;
		}
	}

	private void regen() {
		if (System.currentTimeMillis() - lastRegen >= 60000) {
			if (currentShields < stats[6]) {
				currentShields += stats[5];
				if (currentShields > stats[6]) {
					currentShields = stats[6];
				}
			}
			if (health < stats[3]) {
				health += stats[5] / 2;
				if (health > stats[3]) {
					health = stats[3];
				}
			}
			lastRegen = System.currentTimeMillis();

		}
	}

	public SkillTree getSkills() {
		return skills;
	}

	public StatType[] getStatTypes() {
		return statTypes;
	}


	public int[] getStats() {
		return stats;
	}

	public int getCurrentHealth() {
		return health;
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
	
	public void addXP(int xp) {
		skills.addXP(xp/2);
		currentXP+=(xp+1)/2;
		while(currentXP>=xpToNextLevel) {
			currentLevel++;
			currentXP-=xpToNextLevel;
			xpToNextLevel+=currentLevel;
			
			game.actionPerformed(new ActionEvent(this,88891,"LevelUp"));
		}
	}
	
	public void leveledUp() {
		//Armaan put code here
	}
	
	@Override
	public String toString() {
		
		return skills.toString();
	}

	
	@Override
	public void render(Graphics2D g) {
		// super.refillLastPos(g);
		super.getImage().drawAnimation(g);
		regen();
		super.setMovementDelay(stats[7]);

	}

}
