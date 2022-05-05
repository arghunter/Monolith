package GameObjects.Player.items.consumables;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public class Consumable extends Item {
	long count;
	long maxStack;
	Buff buff;
	public Consumable(String name,int tier,long count,long maxStack,Buff buff) {
		super(name, ItemType.CONSUMABLE,tier);
		this.maxStack=maxStack;
		this.add(count);
		this.buff=buff;
		
	}
	
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
	
	public long add(Consumable consumable) 
	{
		System.out.println(this.getName()+" "+this.count+" "+consumable.getName()+""+consumable.count);
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
	public Buff getBuff() 
	{
		return buff;
	}



	public long getCount() {
		return count;
	}

	public long getMaxStack() {
		return maxStack;
	}
	public void use() 
	{
		buff.start();
		this.count--;
	}
	

}
