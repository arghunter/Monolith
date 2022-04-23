package GameObjects.Player.items.armor;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public class Armor extends Item {
	private int armor;
	private int shields;
	private int health;
	private BattleSuitSet set;
	public Armor(String name, long id, ItemType type,int armor,int shields, int health,BattleSuitSet set) {
		super(name, id, type);
		this.armor=armor;
		this.shields=shields;
		this.health=health;
		this.set=set;
		
	}
	public int getArmor() {
		return armor;
	}
	public int getShields() {
		return shields;
	}
	public int getHealth() {
		return health;
	}
	public BattleSuitSet getSet() {
		return set;
	}

}