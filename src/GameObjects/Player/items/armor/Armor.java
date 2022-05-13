//Author: Adithya Giri
//Date: 5/11/22
//Rev: 01
//Notes: A representation of an armor item
package GameObjects.Player.items.armor;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public class Armor extends Item {
	//Fields
	private int armor;
	private int shields;
	private int health;
	private BattleSuitSet set;
	//Constructor
	public Armor(String name, int tier, ItemType type, int armor, int shields, int health, BattleSuitSet set) {
		super(name, type, tier);
		if (!(type == ItemType.BOOTS || type == ItemType.CHESTPLATE || type == ItemType.HELMET
				|| type == ItemType.LEGGINGS)) {
			throw new IllegalArgumentException("Must be of an armor type");
		}
		this.armor = armor;
		this.shields = shields;
		this.health = health;
		this.set = set;

	}
	//Returns the armor value
	public int getArmor() {
		return armor;
	}
	//Returns the shield boost
	public int getShields() {
		return shields;
	}
	//Returns the health
	public int getHealth() {
		return health;
	}
	//Returns the battle suit set
	public BattleSuitSet getSet() {
		return set;
	}
	//Returns the string representation for parsing
	public String toString() {
		return "(Item:" + super.getName() + "/" + super.getType() + "/" + super.getTier() + "/" + armor + "/" + shields
				+ "/" + health + "/" + set;
	}

}
