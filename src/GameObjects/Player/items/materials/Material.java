package GameObjects.Player.items.materials;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public class Material extends Item {

	double count;
	public Material(String name,int tier,double count) {
		super(name, ItemType.MATERIAL,tier);
		this.count=Math.ceil(count);
		
	}
	
	public void add(Material material) 
	{
		count+=Math.round(material.getCount());
		
	}

	public Material consume(Material material) 
	{
		if(!this.equals(material)) 
		{
			return material;
		}
		count-=Math.round(material.getCount());
		if(count<=0) 
		{
			material.setCount(-count);
			return material;
		}
		return null;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

}
