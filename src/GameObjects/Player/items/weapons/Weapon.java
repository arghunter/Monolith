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
import GameObjects.mobs.Mob;

public abstract class Weapon extends Item{
	
	//Fields
	private int damage;
	private int range;
	private int attackSpeed;//In attacks per minute
	private double attackDelay;
	private double currentAttackDelay;
	private double lastAttack=0;
	//Constructor
	public Weapon(String name,int tier,int damage,int range, int attackSpeed) 
	{
		super(name,ItemType.WEAPON,tier);
		this.damage=damage;
		this.range=range;
		this.attackSpeed=attackSpeed;
		this.attackDelay= Math.round(60*1000.0/attackSpeed);
		
		this.currentAttackDelay=attackDelay;
	}
	//Abstract fire methods
	public abstract void primaryFire(ArrayList<Mob> mobs,Player player);

	//Returns the damage
	public int getDamage() {
		return damage;
	}
	//Returns the range
	public int getRange() {
		return range;
	}
	//Retursn the attack speed
	public double getAttackSpeed() {
		return attackSpeed;
	}
	//Returns the attack delay
	public double getAttackDelay() {
		return attackDelay;
	}
	//Returns the currentAttack delay
	public double getCurrentAttackDelay() {
		return currentAttackDelay;
	}
	//Draws this weapon
	public abstract void drawWeapon(Player p,Graphics2D g);
	//Sets the current attack delay
	public void setCurrentAttackDelay(double currentAttackDelay) {
		this.currentAttackDelay = currentAttackDelay;
	}
	public boolean canFire() 
	{
		boolean canFire=System.currentTimeMillis()-lastAttack>currentAttackDelay;
		if(canFire) 
		{
			lastAttack=System.currentTimeMillis();
		}
		return canFire;
	}
	public double getLastAttack() {
		return lastAttack;
	}
	
	
	
	
}
