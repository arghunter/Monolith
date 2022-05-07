package GameObjects.Player.items;

import java.awt.Graphics2D;

import general.AnimationSystem;

public abstract class Item {
	private String name;
	private long id;
	private ItemType type;
	private int tier;
	public Item(String name,ItemType type, int tier) 
	{
		this.name=name;
		this.id=System.currentTimeMillis();
		this.type=type;
		
				
		
	}
	public long getId() 
	{
		return id;
		
	}
	public int getTier() 
	{
		return tier;
	}
	public String getName() 
	{
		return name;
	}
	public ItemType getType() {
		return type;
	}
	public boolean equals(Item item) 
	{
		return (item.getName().equals(this.name)&&item.getType()==this.getType());
	}
	public boolean isIdentical(Item item) 
	{
		return equals(item)&&item.getId()==this.id;
	}
	
	

}
