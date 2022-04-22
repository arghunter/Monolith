package GameObjects.Player;

import java.util.ArrayList;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.armor.Armor;


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
	public void removeFromArsenal(Item item) 
	{
		for(int i=0;i<arsenal.length;i++) 
		{
			//checks if the memory references are the same and are thus the same object;
			if(item==arsenal[i]) 
			{
				storage.add(item);
				arsenal[i]=null;
			}
			
		}
	}
	public void removeFromArsenal(int arsenalIndex) 
	{
		storage.add(arsenal[arsenalIndex]);
		arsenal[arsenalIndex]=null;
	}
	
	public void destroyItem(Item item) 
	{
		storage.remove(storage.indexOf(item));
	}
	public void swapStoragetoArsenal(Item storageItem, Item arsenalItem) 
	{
		
		//TODO inplement this thing
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
	
	
	

}
