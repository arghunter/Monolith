package GameObjects.Player;

import java.util.ArrayList;



import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.armor.Armor;
import GameObjects.Player.items.blueprints.Blueprint;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;

public class Inventory {
	Item[] arsenal;// For all consumables weapons and armor gets emptied on death
	ArrayList<Item> storage;// For all blueprints materials and stuff that the layer cannot access in the
							// middle of a fight

	public Inventory(String saveData) {

	}

	public Inventory() {
		arsenal = new Item[16];
		storage = new ArrayList<Item>();
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
				if(storage.get(i).equals(item)) 
				{
					sameItem=storage.get(i);
					if(item.getType()==ItemType.CONSUMABLE) 
					{
						Consumable sameConsumable=(Consumable) sameItem;
						Consumable consItem=(Consumable)item;
						consumableExtra=sameConsumable.add(consItem);
						consItem.setCount(consumableExtra);
						if(consItem.getCount()==0) 
						{
							break;
						}
					}else 
					{
						break;
					}
				}
			}
			if(sameItem==null) 
			{
				storage.add(sameItem);
				
			}else if(item.getType()==ItemType.MATERIAL) 
			{
				Material sameMaterial=(Material) sameItem;
				sameMaterial.add((Material) item);
			}else if(item.getType()==ItemType.BLUEPRINT) 
			{
				Blueprint sameBlueprint=(Blueprint) sameItem;
				sameBlueprint.add((Blueprint) item);
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

}
