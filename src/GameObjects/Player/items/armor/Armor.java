package GameObjects.Player.items.armor;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public class Armor extends Item {
	private int armor;
	private int shields;
	private int health;
	private BattleSuitSet set;
	public Armor(String name,int tier, ItemType type,int armor,int shields, int health,BattleSuitSet set) {
		super(name, type,tier);
		if(!(type==ItemType.BOOTS||type==ItemType.CHESTPLATE||type==ItemType.HELMET||type==ItemType.LEGGINGS)) 
		{
			throw new IllegalArgumentException("Must be of an armor type");
		}
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
	public String toString() 
	{
		return "(Item:"+super.getName()+"/"+super.getType()+"/"+super.getTier()+"/"+armor+"/"+shields+"/"+health+"/"+set;
	}

}
