//Author: Adithya Giri
//Date: 5/11/22
//Rev: 01
//Notes: An abstract item for the inventory
package GameObjects.Player.items;

import java.awt.Graphics2D;

import general.AnimationSystem;

public abstract class Item {
	// Fields
	private String name;
	private long id;
	private ItemType type;
	private int tier;

	// Constructor
	public Item(String name, ItemType type, int tier) {
		this.name = name;
		this.id = System.currentTimeMillis();
		this.type = type;

	}

	// Returns the id
	public long getId() {
		return id;

	}

	// Returns the tier of this item
	public int getTier() {
		return tier;
	}

	// Returns the name of this item
	public String getName() {
		return name;
	}

	// Returns the type of this item
	public ItemType getType() {
		return type;
	}

	// Checks if two items are the same.
	public boolean equals(Item item) {
		return (item.getName().equals(this.name) && item.getType() == this.getType());
	}

	// Checks if two items are identical
	public boolean isIdentical(Item item) {
		return equals(item) && item.getId() == this.id;
	}

}
