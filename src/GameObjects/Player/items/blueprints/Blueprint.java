//Author: Adithya Giri
//Date: 5/11/22
//Rev: 01
//Notes: A blueprint that is used to construct items
package GameObjects.Player.items.blueprints;

import java.util.ArrayList;
import java.util.Arrays;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;

public class Blueprint extends Item {
	//Fields
	private Item[] components;
	private Item product;
	private double count;
	private Inventory inventory;
	//Constructor
	public Blueprint(String name, int tier, double count, Item[] components, Item product, Inventory inventory) {
		super(name, ItemType.BLUEPRINT, tier);
		this.components = components;
		this.product = product;
		this.count = Math.round(count);
		this.inventory = inventory;

	}
	public Blueprint(Blueprint bp) 
	{
		super(bp.getName(),ItemType.BLUEPRINT,bp.getTier());
		this.components=new Item[bp.getComponents().length];
		for(int i=0;i<bp.getComponents().length;i++) 
		{
			components[i]=bp.getComponents()[i];
			
		}
		product=bp.product;
		inventory=bp.inventory;
	}
	//Add the blueprint to this one
	public void add(Blueprint blueprint) { 
		count += Math.round(blueprint.getCount());

	}
	//Returns the count
	public double getCount() {
		return count;
	}
	//Sets the count
	public void setCount(double count) {
		this.count = count;
	}
	//Returns the components required to construct this blueprint
	public Item[] getComponents() {
		return components;
	}
	//Returns the product of this blueprint
	public Item getProduct() {
		return product;
	}
	//Constructs this blueprint
	public void construct() throws MissingResourcesException {
		System.out.println("here");
		boolean[] requirementsFulfilled = new boolean[components.length];
		ArrayList<Item> storage = inventory.getStorage();
		for (int i = 0; i < components.length; i++) {

			if (components[i].getType() == ItemType.CONSUMABLE || components[i].getType() == ItemType.MATERIAL) {
				double count = 0;
				for (int j = storage.size() - 1; j >= 0; j--) {
					if (storage.get(j).equals(components[i])) {
						if (components[i].getType() == ItemType.CONSUMABLE) {
							count += ((Consumable) storage.get(i)).getCount();
							if (count > ((((Consumable) components[i]).getCount()))) {
								requirementsFulfilled[i] = true;
								break;
							}
						} else {
							count += ((Material) storage.get(j)).getCount();
							if (count > ((((Material) components[i]).getCount()))) {
								requirementsFulfilled[i] = true;
								break;
							}
						}
					}
				}

			} else {
				for (int j = 0; j < storage.size(); j++) {
					if (storage.get(i).equals(components[i])) {
						requirementsFulfilled[i] = true;
						break;
					}
				}
			}

			if (!requirementsFulfilled[i]) {
				throw new MissingResourcesException("You are missing the resources required to construct this item");
			}

		}
		for (int i = 0; i < components.length; i++) {
			for (int j = storage.size() - 1; j >= 0; j--) {
				if (storage.get(j).equals(components[i])) {
					if (components[i].getType() == ItemType.CONSUMABLE) {
						Consumable consumableComp = (Consumable) components[i];
						Consumable consumableStorage = (Consumable) storage.get(j);
						if (consumableStorage.getCount() >= consumableComp.getCount()) {
							consumableStorage.substract(consumableComp.getCount());
							if (consumableStorage.getCount() == 0) {
								inventory.removeFromStorage(consumableStorage);
							}
						} else {
							continue;
						}
					} else if (components[i].getType() == ItemType.MATERIAL) {
						Material materialComp = (Material) components[i];
						Material materialStorage = (Material) storage.get(j);
						if (materialStorage.getCount() >= materialComp.getCount()) {
							Material extra = materialStorage.consume(materialComp);
							if (extra != null) {
								System.err.println("Massive error in blueprint useage");
							}
							if (materialStorage.getCount() == 0) {
								inventory.removeFromStorage(materialStorage);
							}
						} else {
							continue;
						}
					} else {
						inventory.removeFromStorage(storage.get(i));
					}
					break;
				}
			}
		}

		inventory.addToStorage(product);
		this.count--;
		if (count <= 0) {
			inventory.removeFromStorage(this);
		}

	}

	@Override
	public String toString() {
		return ("(Item:" + super.getName() + "/" + super.getType() + "/" + super.getTier() + "/" + count + "/"
				+ "[components-++" + Arrays.toString(components).replace("Item:", "I:").replace("/", ";~;").replace(",","~;~") + "product-++"
				+ product.toString().replace("Item:", "I:").replace("/", ";~~;") + "count-++" + count + "]");
	}

}
