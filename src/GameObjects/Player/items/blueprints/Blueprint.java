package GameObjects.Player.items.blueprints;

import java.util.ArrayList;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;

public class Blueprint extends Item {
	Item[] components;
	Item product;
	double count;

	public Blueprint(String name, double count, Item[] components, Item product) {
		super(name, ItemType.BLUEPRINT);
		this.components = components;
		this.product = product;
		this.count = Math.round(count);

	}

	public void add(Blueprint blueprint) {
		count += Math.round(blueprint.getCount());

	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}
	public Item[] getComponents() 
	{
		return components;
	}
	public Item getProduct() 
	{
		return product;
	}


	public Item construct(Inventory inventory) throws MissingResourcesException {
		boolean [] requirementsFulfilled=new boolean[components.length];
		ArrayList<Item> storage=inventory.getStorage();
		for (int i = 0; i < components.length; i++) {

			if(components[i].getType()==ItemType.CONSUMABLE||components[i].getType()==ItemType.MATERIAL) 
			{
				double count=0;
				for (int j = 0; j < storage.size(); i++) {
					if(storage.get(i).equals(components[i])) 
					{
						if(components[i].getType()==ItemType.CONSUMABLE) 
						{
							count+=((Consumable) storage.get(i)).getCount();
							if(count>((((Consumable) components[i]).getCount()))) 
							{
								requirementsFulfilled[i]=true;
								break;
							}
						}else 
						{
							count+=((Material) storage.get(i)).getCount();
							if(count>((((Material) components[i]).getCount()))) 
							{
								requirementsFulfilled[i]=true;
								break;
							}
						}
					}
				}
				
			}else 
			{
				for(int j=0;j<storage.size();j++) 
				{
					if(storage.get(i).equals(components[i])) 
					{
						requirementsFulfilled[i]=true;
						break;
					}
				}
			}
			
			if(!requirementsFulfilled[i]) 
			{
				throw new MissingResourcesException("You are missing the resources required to construct this item");
			}

		}
		for(int i=0;i<components.length;i++) 
		{
			for(int j=0;j<storage.size();j++) 
			{
				if(storage.get(j).equals(components[i])) 
				{
					if(components[i].getType()==ItemType.CONSUMABLE) 
					{
						Consumable consumableComp=(Consumable)components[i];
						Consumable consumableStorage=(Consumable)storage.get(j);
						if(consumableStorage.getCount()>=consumableComp.getCount()) 
						{
							consumableStorage.substract(consumableComp.getCount());
							if(consumableStorage.getCount()==0) 
							{
								inventory.removeFromStorage(consumableStorage);
							}
						}
					}else if(components[i].getType()==ItemType.MATERIAL) 
					{
						Material materialComp=(Material) components[i];
						Material materialStorage=(Material) storage.get(i);
						if(materialStorage.getCount()>=materialComp.getCount()) 
						{
							Material extra=materialStorage.consume(materialComp);
							if(extra!=null) 
							{
								System.err.println("Massive error in blueprint useage");
							}
							if(materialStorage.getCount()==0) 
							{
								inventory.removeFromStorage(materialStorage);
							}
						}
					}else 
					{
						inventory.removeFromStorage(storage.get(i));
					}
					break;
				}
			}
		}
		
		return product;

	}

}
