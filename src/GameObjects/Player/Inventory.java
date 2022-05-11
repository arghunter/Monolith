package GameObjects.Player;

import java.util.ArrayList; 



import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.armor.Armor;
import GameObjects.Player.items.armor.BattleSuitSet;
import GameObjects.Player.items.blueprints.Blueprint;
import GameObjects.Player.items.consumables.Buff;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;
import GameObjects.Player.items.weapons.MeleeWeapon;

import GameObjects.Player.items.weapons.Weapon;
import skills.StatType;

public class Inventory {
	private Item[] arsenal;// For all consumables weapons and armor gets emptied on death
	private ArrayList<Item> storage;// For all blueprints materials and stuff that the player cannot access in the middle of a fight
	private int equipped=4;

//	public Inventory(String saveData) {
//
//	}

	public Inventory(Player player) {
		arsenal = new Item[16];
		storage = new ArrayList<Item>();
		arsenal[0]=(new Armor("Baklava",0,ItemType.HELMET,10,25,25,BattleSuitSet.NONE));
		arsenal[1]=(new Armor("Baklava",0,ItemType.CHESTPLATE,15,25,50,BattleSuitSet.NONE));
		arsenal[2]=(new Armor("Baklava",0,ItemType.LEGGINGS,15,25,50,BattleSuitSet.NONE));
		arsenal[3]=(new Armor("Baklava",0,ItemType.BOOTS,10,25,25,BattleSuitSet.NONE));
		arsenal[4]=(new MeleeWeapon("Rusty Sword",0,50, 20, 30,10/18.0*Math.PI));
		StatType[] buffTypes= {StatType.HEALTH,StatType.REGEN};
		int[] buffs= {1000,500};
		arsenal[5]=new Consumable("Baklava",0,10,64,new Buff(buffTypes,buffs,10,player.getStatTypes(),player.getBuffs()));
		equipped=4;
	}

	public Item[] getArsenal() {
		return arsenal;
	}

	public ArrayList<Item> getStorage() {
		return storage;
	}

	public Item getEquippedItem() {
		return arsenal[equipped];
	}

	public void addToArsenal(Item item) throws ArsenalFullException {
		if (item.getType() == ItemType.WEAPON || item.getType() == ItemType.CONSUMABLE) {
			for (int i = 4; i < arsenal.length; i++) {
				if (arsenal[i] == null) {
					arsenal[i] = item;
					storage.remove(item);
					break;
				}
				if (i == arsenal.length - 1) {
					throw new ArsenalFullException("Your Arsenal is full");
				}
			}
		} else if (item.getType() != ItemType.MATERIAL || item.getType() != ItemType.BLUEPRINT) {
			addToArsenal((Armor) item);
		}
	}

	public int removeFromArsenal(Item item) {
		for (int i = 0; i < arsenal.length; i++) {
			// checks if the memory references are the same and are thus the same object;
			if (item == arsenal[i]) {
				storage.add(item);
				arsenal[i] = null;
				return i;
			}

		}
		return -1;
	}

	public void removeFromArsenal(int arsenalIndex) {
		storage.add(arsenal[arsenalIndex]);
		arsenal[arsenalIndex] = null;
	}

	public void removeFromStorage(Item item) {
		storage.remove(storage.indexOf(item));
	}

	public void swapStoragetoArsenal(Item storageItem, Item arsenalItem) {
		if (storageItem.getType() == ItemType.MATERIAL || storageItem.getType() == ItemType.BLUEPRINT) {
			return;
		}
		int emptyPos = removeFromArsenal(arsenalItem);
		if (emptyPos != -1 && emptyPos < 4) {
			if (storageItem.getType() == ItemType.BOOTS || storageItem.getType() == ItemType.CHESTPLATE
					|| storageItem.getType() == ItemType.LEGGINGS || storageItem.getType() == ItemType.HELMET) {
				addToArsenal((Armor) storageItem);
			} else {
				if (storageItem.getType() == ItemType.WEAPON) {
					Item tempItem = storageItem;
					storageItem = arsenalItem;
					arsenalItem = tempItem;
				} else if (storageItem.getType() == ItemType.CONSUMABLE) {
					Consumable consumableStorage = (Consumable) storageItem;
					for (int i = 0; i < arsenal.length; i++) {
						if (arsenal[i].equals(storageItem)) {
							long extra = ((Consumable) arsenal[i]).add(consumableStorage);
							if (extra != 0) {
								consumableStorage.setCount(extra);
							} else {
								removeFromStorage(storageItem);
							}
						}
					}
				}

			}
		}

	}

	public void addToArsenal(Armor armor) {
		int pos = -1;
		if (armor.getType() == ItemType.HELMET) {
			pos = 0;

		} else if (armor.getType() == ItemType.CHESTPLATE) {
			pos = 1;
		} else if (armor.getType() == ItemType.LEGGINGS) {
			pos = 2;
		} else if (armor.getType() == ItemType.BOOTS) {
			pos = 3;
		}
		if (pos != -1 && arsenal[pos] != null) {
			storage.add(arsenal[pos]);
			storage.remove(armor);
			arsenal[pos] = armor;
		}
	}

	public void addToStorage(Item item) {
		if (item.getType() == ItemType.BLUEPRINT || item.getType() == ItemType.CONSUMABLE
				|| item.getType() == ItemType.MATERIAL) {
			Item sameItem=null;
			long consumableExtra=0;
			for(int i=0;i<storage.size();i++) 
			{
				if(storage.get(i)!=null&&storage.get(i).equals(item)) 
				{
					sameItem=storage.get(i);
					if(item.getType()==ItemType.CONSUMABLE) 
					{
						if(((Consumable)sameItem).getCount()<((Consumable)sameItem).getMaxStack()) 
						{
							
							Consumable sameConsumable=(Consumable) sameItem;
							Consumable consItem=(Consumable)item;
							
							consumableExtra=sameConsumable.add(consItem);
							if(consumableExtra==0) 
							{
								return;
							}else 
							{
								item=new Consumable(consItem.getName(),consItem.getTier(),consumableExtra,consItem.getMaxStack(),consItem.getBuff());
								
							}
						}

					}else 
					{
						break;
					}
				}
			}
			if(sameItem==null) 
			{
				storage.add(item);
				
			}else if(item.getType()==ItemType.MATERIAL) 
			{
				Material sameMaterial=(Material) sameItem;
				sameMaterial.add((Material) item);
			}else if(item.getType()==ItemType.BLUEPRINT) 
			{
				Blueprint sameBlueprint=(Blueprint) sameItem;
				sameBlueprint.add((Blueprint) item);
			}else {
				storage.add(item);
				
			}

		} else {
			storage.add(item);
			
		}
	}
	public boolean contains(Item item) 
	{
		for(int i=0;i<arsenal.length;i++) 
		{
			if(arsenal[i].equals(item)) 
			{
				return true;
			}
		}
		for(int i=0;i<storage.size();i++) 
		{
			if(storage.get(i).equals(item)) 
			{
				return true;
				
			}
		}
		return false;
		
		
	}
	public ArrayList<Item> searchStorage(String searchTerm) 
	{
		searchTerm=searchTerm.toLowerCase();
		ArrayList<Item> selection=new ArrayList<Item>();
		for(int i=0;i<storage.size();i++) 
		{
			if(storage.get(i).getName().toLowerCase().contains(searchTerm)) 
			{
				selection.add(storage.get(i));
			}
		}
		return selection;
	}
	public ArrayList<Item> searchStorage(ItemType type) 
	{
		
		ArrayList<Item> selection=new ArrayList<Item>();
		
		for(int i=0;i<storage.size();i++) 
		{
			if(storage.get(i).getType()==type||(type==ItemType.ARMOR&&(storage.get(i).getType()==ItemType.BOOTS||storage.get(i).getType()==ItemType.CHESTPLATE||storage.get(i).getType()==ItemType.LEGGINGS||storage.get(i).getType()==ItemType.HELMET))) 
			{
				selection.add(storage.get(i));
			}
		}
		return selection;
	}
	public Armor getHelemet() 
	{
		return (Armor)arsenal[0];
	}
	public Armor getChestplate() 
	{
		return (Armor) arsenal[1];
	}
	public Armor getLeggings() 
	{
		return (Armor) arsenal[2];
	}
	public Armor getBoots() 
	{
		return (Armor) arsenal[3];
	}
	public int getEquipped() 
	{
		return equipped;
	}
	public void updateArsenal() 
	{
		for(int i=0;i<arsenal.length;i++) 
		{
			if(arsenal[i]!=null&&arsenal[i].getType()==ItemType.CONSUMABLE) 
			{
				if(((Consumable)arsenal[i]).getCount()<=0) 
				{
					arsenal[i]=null;
				}
			}
		}
	}
	public void setEquipped(int equipped) 
	{
		updateArsenal();
		if(equipped>15||equipped<0) 
		{
			throw new IllegalArgumentException("Out of arsenal bounds");
			
		}else 
		{
			this.equipped=equipped;
		}
	}
	public double getAttackSpeed() 
	{
		if(arsenal[equipped].getType()==ItemType.WEAPON) 
		{
			Weapon equippedWeapon=(Weapon)arsenal[equipped];
			return equippedWeapon.getAttackSpeed();
		}else 
		{
			return 60;
			
		}
	}
	public double getArmor() 
	{
		return ((Armor)arsenal[0]).getArmor()+((Armor)arsenal[1]).getArmor()+((Armor)arsenal[2]).getArmor()+((Armor)arsenal[3]).getArmor();
	}
	public double getShields() 
	{
		return ((Armor)arsenal[0]).getShields()+((Armor)arsenal[1]).getShields()+((Armor)arsenal[2]).getShields()+((Armor)arsenal[3]).getShields();
	}
	public double getHealth() 
	{
		return ((Armor)arsenal[0]).getHealth()+((Armor)arsenal[1]).getHealth()+((Armor)arsenal[2]).getHealth()+((Armor)arsenal[3]).getHealth();
	}

}
