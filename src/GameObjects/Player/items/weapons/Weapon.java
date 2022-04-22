package GameObjects.Player.items.weapons;

import GameObjects.Mob;
import GameObjects.Player.Player;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public abstract class Weapon extends Item{
	private int damage;
	private int range;
	private double attackSpeed;//In attacks per second
	private double attackDelay;
	private double currentAttackDelay;
	
	public Weapon(String name,long id,int damage,int range, double attackSpeed) 
	{
		super(name,id,ItemType.WEAPON);
		this.damage=damage;
		this.range=range;
		this.attackSpeed=attackSpeed;
		this.attackDelay=1000/attackSpeed;
		this.currentAttackDelay=attackDelay;
	}
	
	public abstract void primaryFire(Mob[] mobs,Player player);
	public abstract void secondaryFire(Mob[] mobs,Player player);
	public abstract void tertiaryFire(Mob[]mobs, Player player);

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

	public void setCurrentAttackDelay(double currentAttackDelay) {
		this.currentAttackDelay = currentAttackDelay;
	}
	
	
	
	
}
