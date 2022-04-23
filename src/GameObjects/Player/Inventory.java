package GameObjects.Player;

import java.util.ArrayList;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.armor.Armor;
import GameObjects.Player.items.consumables.Consumable;


public class Inventory {
	Item[] arsenal;// For all consumables weapons and armor gets emptied on death
	ArrayList<Item> storage;//For all blueprints materials and stuff that the layer cannot access in the middle of a fight
	
	public Inventory(String saveData) 
	{
		
	}
	
	public Inventory() 
	{
		arsenal=new Item[16];
		storage=new ArrayList<Item>();
	}
	
	public void addToArsenal(Item item) throws ArsenalFullException 
	{
		if(item.getType()==ItemType.WEAPON||item.getType()==ItemType.CONSUMABLE) 
		{
			for(int i=4;i<arsenal.length;i++ ) 
			{
				if(arsenal[i]==null) 
				{
					arsenal[i]=item;
					storage.remove(item);
					break;
				}
				if(i==arsenal.length-1) 
				{
					throw new ArsenalFullException("Your Arsenal is full");
				}
			}
		}else if(item.getType()!=ItemType.MATERIAL||item.getType()!=ItemType.BLUEPRINT) 
		{
			addToArsenal((Armor) item);
		}
	}
	public int removeFromArsenal(Item item) 
	{
		for(int i=0;i<arsenal.length;i++) 
		{
			//checks if the memory references are the same and are thus the same object;
			if(item==arsenal[i]) 
			{
				storage.add(item);
				arsenal[i]=null;
				return i;
			}
			
		}
		return -1;
	}
	public void removeFromArsenal(int arsenalIndex) 
	{
		storage.add(arsenal[arsenalIndex]);
		arsenal[arsenalIndex]=null;
	}
	
	public void removeFromStorage(Item item) 
	{
		storage.remove(storage.indexOf(item));
	}
	
	public void swapStoragetoArsenal(Item storageItem, Item arsenalItem) 
	{
		int emptyPos=removeFromArsenal(arsenalItem);
		if(emptyPos!=-1&&emptyPos<4) 
		{
			if(storageItem.getType()==ItemType.BOOTS||storageItem.getType()==ItemType.CHESTPLATE||storageItem.getType()==ItemType.LEGGINGS||storageItem.getType()==ItemType.HELMET) 
			{
				addToArsenal((Armor) storageItem);
			}else 
			{
				if(storageItem.getType()==ItemType.WEAPON) 
				{
					Item tempItem=storageItem;
					storageItem=arsenalItem;
					arsenalItem=tempItem;
				}else if(storageItem.getType()==ItemType.CONSUMABLE) 
				{
					Consumable consumableStorage=(Consumable) storageItem;
					for(int i=0;i<arsenal.length;i++) 
					{
						if(arsenal[i].equals(storageItem)) 
						{
							long extra=((Consumable)arsenal[i]).add(consumableStorage);
							if(extra!=0) 
							{
								consumableStorage.setCount(extra);
							}else 
							{
								removeFromStorage(storageItem);
							}
						}
					}
				}

			}
		}

		
	}
	public void addToArsenal(Armor armor) 
	{
		int pos=-1;
		if(armor.getType()==ItemType.HELMET) 
		{
			pos=0;
			
		}else if(armor.getType()==ItemType.CHESTPLATE) 
		{
			pos=1;
		}else if(armor.getType()==ItemType.LEGGINGS) 
		{
			pos=2;
		}else if(armor.getType()==ItemType.BOOTS) 
		{
			pos=3;
		}
		if( pos!=-1&&arsenal[pos]!=null) 
		{
			storage.add(arsenal[pos]);
			storage.remove(armor);
			arsenal[pos]=armor;
		}
	}
	public void addToStorage(Item item) 
	{
		
	}
	
	
	

}