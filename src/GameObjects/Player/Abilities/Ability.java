package GameObjects.Player.Abilities;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;

public abstract class Ability extends Item{
	public Ability(String name, ItemType type, int tier) {
		super(name, type, tier);
		// TODO Auto-generated constructor stub
	}
	int[] stats;
	int[] equippedAbilities;
	public abstract int[] getStats();
	public abstract boolean isAbilityEquipped(Inventory inventory);
	public abstract int[] getBoostedStats(int[] stats);
}
