package GameObjects.Player.Abilities;

import GameObjects.Player.Inventory;
import GameObjects.Player.items.ItemType;

public class SpeedAbility extends Ability{

	public SpeedAbility(String name, ItemType type, int tier) {
		super(name, type, tier);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] getStats() {
		return null;
	}

	@Override
	public boolean isAbilityEquipped(Inventory inventory) {
		if(inventory.getEquippedItem() instanceof SpeedAbility) {
			return true;
		}
		return false;
	}

	@Override
	public int[] getBoostedStats(int[] stats) {
		stats[7] += super.getTier()*20;
		return stats;
	}
}
