//Author: Adithya 
//Revs: 01
//Date: 5/9/22
//Notes:A weapon super class for damaging enemies

package GameObjects.Player.items.weapons;

import java.awt.Graphics2D;
import java.util.ArrayList;

import GameObjects.Player.Player;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;

public abstract class Weapon extends Item {

	// Fields
	private int damage;
	private int range;
	private int attackSpeed;// In attacks per minute
	private double attackDelay;
	private double currentAttackDelay;
	private double lastAttack = 0;
	private StatusEffect effect;
	private double duration;
	private double chance;

	// Constructor
	public Weapon(String name, int tier, int damage, int range, int attackSpeed, StatusEffect effect, double duration,
			double chance) {
		super(name, ItemType.WEAPON, tier);
		this.damage = damage;
		this.range = range;
		this.attackSpeed = attackSpeed;
		this.attackDelay = Math.round(60 * 1000.0 / attackSpeed);
		this.effect = effect;
		this.duration = duration;
		this.chance = chance;
		this.currentAttackDelay = attackDelay;
	}

	public Weapon(Weapon w) {
		super(w.getName(), ItemType.WEAPON, w.getTier());
		this.damage = w.getDamage();
		this.range = w.getRange();
		this.attackSpeed = w.getAttackSpeed();
		this.attackDelay = Math.round(60 * 1000.0 / attackSpeed);
		this.effect = w.getEffect();
		this.duration = w.getDuration();
		this.chance = w.getChance();
		this.currentAttackDelay = attackDelay;

	}// Returns the status effect

	public StatusEffect getEffect() {
		return effect;
	}

	// Returns the duration of that status
	public double getDuration() {
		return duration;
	}

	// Returns the chance of that status effect
	public double getChance() {
		return chance;
	}

	// Abstract fire methods
	public abstract void primaryFire(ArrayList<Mob> mobs, Player player);

	// Returns the damage
	public int getDamage() {
		return damage;
	}

	// Returns the range
	public int getRange() {
		return range;
	}

	// Retursn the attack speed
	public int getAttackSpeed() {
		return attackSpeed;
	}

	// Returns the attack delay
	public double getAttackDelay() {
		return attackDelay;
	}

	// Returns the currentAttack delay
	public double getCurrentAttackDelay() {
		return currentAttackDelay;
	}

	// Draws this weapon
	public abstract void drawWeapon(Player p, Graphics2D g);

	// Sets the current attack delay
	public void setCurrentAttackDelay(double currentAttackDelay) {
		this.currentAttackDelay = currentAttackDelay;
	}

	// Returns true if this weapon can fire
	public boolean canFire() {
		boolean canFire = System.currentTimeMillis() - lastAttack > currentAttackDelay;
		if (canFire) {
			lastAttack = System.currentTimeMillis();
		}
		return canFire;
	}

	// Returns the time of the last attack
	public double getLastAttack() {
		return lastAttack;
	}

}
