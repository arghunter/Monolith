package GameObjects.Player.items.blueprints;

import java.util.ArrayList;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.materials.Material;

public class Blueprint extends Item {
	Item[]components;
	Item product;
	double count;
	public Blueprint(String name, long id,double count,Item[] components,Item product) {
		super(name, id, ItemType.BLUEPRINT);
		this.components=components;
		this.product=product;
		this.count=Math.round(count);
		// TODO Auto-generated constructor stub
	}
	
	public void add(Blueprint blueprint) 
	{
		count+=Math.round(blueprint.getCount());
		
	}


	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}
	public Item construct(ArrayList<Item> storage) 
	{
		for(int i=0;i<components.length;i++) 
		{
			for(int j=0;j<storage.size();i++) 
			{
				//TODO bluepprint stuff
			}
		}
		return product;
		
	}
	

}
