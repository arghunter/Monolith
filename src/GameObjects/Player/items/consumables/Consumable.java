package GameObjects.Player.items.consumables;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public class Consumable extends Item {
	long count;
	long maxStack;
	public Consumable(String name,long count,long maxStack) {
		super(name, ItemType.CONSUMABLE);
		this.maxStack=maxStack;
		this.add(count);
		
	}
	
	private long add(long num) 
	{
		if((double)count+num<maxStack) 
		{
			count+=maxStack;
			return 0;
		}else 
		{
			long extra=(long) (maxStack-((double)count+num));
			count=maxStack;
			return extra;
			
		}
		
	}
	
	public long add(Consumable consumable) 
	{
		return add(consumable.getCount());
	}
	public void substract(long num) 
	{
		count-=num;
		if(count<0) 
		{
			count=0;
		}
	}
	public void setCount(long num) 
	{
		count=0;
		add(num);
	}



	public long getCount() {
		return count;
	}

	public long getMaxStack() {
		return maxStack;
	}
	

}
