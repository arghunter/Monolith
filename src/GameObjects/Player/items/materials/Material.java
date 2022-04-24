package GameObjects.Player.items.materials;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public class Material extends Item {

	double count;
	public Material(String name, long id, ItemType type,double count) {
		super(name, id, type);
		this.count=Math.ceil(count);
		
	}
	
	public void add(Material material) 
	{
		count+=Math.round(material.getCount());
		
	}
	public void consume(double num) 
	{
		count-=Math.round(num);
	}
	public void consume(Material material) 
	{
		count-=Math.round(material.getCount());
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

}
