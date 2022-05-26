//Author: Adithya Giri
//Date: 5/11/22
//Rev: 01
//Notes: A material in Monolisth
package GameObjects.Player.items.materials;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public class Material extends Item {
	// Fields
	double count;

	// Constructor
	public Material(String name, int tier, double count) {
		super(name, ItemType.MATERIAL, tier);
		this.count = Math.ceil(count);

	}

	public Material(Material m) {
		super(m.getName(), ItemType.MATERIAL, m.getTier());
		this.count = m.getCount();
	}

	// Adds a material to this one
	public void add(Material material) {
		count += Math.round(material.getCount());

	}

	// Consumes this material
	public Material consume(Material material) {
		if (!this.equals(material)) {
			return material;
		}
		count -= Math.round(material.getCount());
		if (count <= 0) {
			material.setCount(-count);
			return material;
		}
		return null;
	}

	// Returns Count 
	public double getCount() {
		return count;
	}

	// Sets the count
	public void setCount(double count) {
		this.count = count;
	}

	// to string for save data parsing
	public String toString() {
		return "(Item:" + super.getName() + "/" + super.getTier() + "/" + super.getType() + "/" + count;
	}

}
