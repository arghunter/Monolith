/*
 * Author: Adithya
 * Revs: 01
 * Date: 5/9/22
 * Notes:A weapon super class for damaging enemies
 */
package GameObjects.Player.items.weapons;

import java.awt.Graphics2D;
import java.util.ArrayList;

import GameObjects.Player.Player;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.mobs.Mob;

public abstract class Weapon extends Item{
	private int damage;
	private int range;
	private int attackSpeed;//In attacks per minute
	private double attackDelay;
	private double currentAttackDelay;
	
	public Weapon(String name,int tier,int damage,int range, int attackSpeed) 
	{
		super(name,ItemType.WEAPON,tier);
		this.damage=damage;
		this.range=range;
		this.attackSpeed=attackSpeed;
		this.attackDelay= Math.round(60*1000.0/attackSpeed);
		
		this.currentAttackDelay=attackDelay;
	}
	
	public abstract void primaryFire(ArrayList<Mob> mobs,Player player);
	public abstract void secondaryFire(ArrayList<Mob> mobs,Player player);
	public abstract void tertiaryFire(ArrayList<Mob> mobs, Player player);

	public int getDamage() {
		return damage;
	}

	public int getRange() {
		return range;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public double getAttackDelay() {
		return attackDelay;
	}

	public double getCurrentAttackDelay() {
		return currentAttackDelay;
	}
	public abstract void drawWeapon(Player p,Graphics2D g);
	public void setCurrentAttackDelay(double currentAttackDelay) {
		this.currentAttackDelay = currentAttackDelay;
	}
	
	
	
	
}
