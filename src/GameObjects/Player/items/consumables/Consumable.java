//Author: Adithya Giri
//Date: 5/11/22
//Rev: 01
//Notes: A consumable that buffs the player
package GameObjects.Player.items.consumables;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public class Consumable extends Item {
	//Fields
	private long count;
	private long maxStack;
	private Buff buff;
	//Constructor
	public Consumable(String name,int tier,long count,long maxStack,Buff buff) {
		super(name, ItemType.CONSUMABLE,tier);
		this.maxStack=maxStack;
		this.add(count);
		this.buff=buff;
		
	}
	public Consumable(Consumable c) 
	{
		super(c.getName(),ItemType.CONSUMABLE,c.getTier());
		this.maxStack=c.getMaxStack();
		this.add(c.getCount());
		this.buff=new Buff(c.buff);
	}
	//Adds a number to the count of this consumable
	private long add(long num) 
	{
		
		if((double)count+num<=maxStack) 
		{
			count+=num;
			
			return 0;
		}else 
		{
			long extra=(long) ((count+(double)num)-maxStack);
			count=maxStack;
			return extra;
			
		}
		
	}
	//Adds a consumable to this consumable
	public long add(Consumable consumable) 
	{
		return add(consumable.getCount());
	}
	//Subtracts a count from this consumable
	public void substract(long num) 
	{
		count-=num;
		if(count<0) 
		{
			count=0;
		}
	}
	//Sets the count of this consumable
	public void setCount(long num) 
	{
		count=0;
		add(num);
	}
	//Consumes one of these consumables
	public void consume() 
	{
		if(count>0) 
		{
			new Buff(buff).start();
			this.count--;
		}

		
	}
	//Returns the buff that this consumable delivers
	public Buff getBuff() 
	{
		return buff;
	}


	//Returns the count
	public long getCount() {
		return count;
	}
	//Returns the maxStack
	public long getMaxStack() {
		return maxStack;
	}
	@Override
	//ToString conversion for save data parsing
	public String toString() 
	{
		String s="(Item:"+super.getName()+"/"+super.getType()+"/"+super.getTier()+"/"+count+"/"+maxStack+"/"+buff;
		
		
		return s;
	}

}
