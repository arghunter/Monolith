package GameObjects.Player.items;

import java.awt.Graphics2D;

import general.AnimationSystem;

public abstract class Item {
	String name;
	long id;
	ItemType type;
	
	public Item(String name, long id,ItemType type) 
	{
		this.name=name;
		this.id=id;
		this.type=type;
		
				
		
	}
	public long getId() 
	{
		return id;
		
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
