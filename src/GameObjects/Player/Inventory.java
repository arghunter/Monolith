//Author: Adithya Giri
//Date: 5/11/22
//Rev: 01
//Notes: A working inventory that stores items for the Player
package GameObjects.Player;

import java.util.ArrayList;
import java.util.Arrays;

import GameObjects.Player.items.Item;
import GameObjects.Player.items.ItemType;
import GameObjects.Player.items.armor.Armor;
import GameObjects.Player.items.armor.BattleSuitSet;
import GameObjects.Player.items.blueprints.Blueprint;
import GameObjects.Player.items.consumables.Buff;
import GameObjects.Player.items.consumables.Consumable;
import GameObjects.Player.items.materials.Material;
import GameObjects.Player.items.weapons.LongRangeWeapon;
import GameObjects.Player.items.weapons.MeleeWeapon;

import GameObjects.Player.items.weapons.Weapon;
import GameObjects.elementalDamage.StatusEffect;
import skills.StatType;

public class Inventory {
	// Fiedls
	private Item[] arsenal;// For all consumables weapons and armor gets emptied on death
	private ArrayList<Item> storage;// For all blueprints materials and stuff that the player cannot access in the
									// middle of a fight
	private int equipped = 4;// Equipped item

	// Default constuctor. Forms an inventory with some items
	public Inventory(Player player) {
		arsenal = new Item[16];
		storage = new ArrayList<Item>();
		arsenal[0] = (new Armor("PrismarineHelm", 0, ItemType.HELMET, 100, 205, 205, BattleSuitSet.PRISMARINE));
		arsenal[1] = (new Armor("PrismarineChestplate", 0, ItemType.CHESTPLATE, 100, 205, 250, BattleSuitSet.PRISMARINE));
		arsenal[2] = (new Armor("PrismarineLeggings", 0, ItemType.LEGGINGS, 100, 205, 205, BattleSuitSet.PRISMARINE));
		arsenal[3] = (new Armor("PrismarineBoots", 0, ItemType.BOOTS, 100, 100, 100, BattleSuitSet.PRISMARINE));

		arsenal[4] = (new MeleeWeapon("Lightning Sword", 0, 10, 110, 120, StatusEffect.LIGHTNING, 5, 0.5,
				10 / 18.0 * Math.PI));
		arsenal[5] = (new MeleeWeapon("Rot Sword", 0, 10, 110, 120, StatusEffect.ROT, 5, 0.5, 10 / 18.0 * Math.PI));
		arsenal[6] = (new MeleeWeapon("Rusty Sword", 0, 10, 110, 120, StatusEffect.BLAST, 5, 0.5, 10 / 18.0 * Math.PI));
		arsenal[8] = (new MeleeWeapon("Corrosive Sword", 0, 10, 110, 120, StatusEffect.CORROSION, 5, 0.5,
				10 / 18.0 * Math.PI));
		arsenal[9] = (new MeleeWeapon("Gas Sword", 0, 10, 110, 120, StatusEffect.GAS, 5, 0.5, 10 / 18.0 * Math.PI));
		arsenal[10] = (new MeleeWeapon("Viral Sword", 0, 10, 110, 120, StatusEffect.VIRAL, 5, 0.5,
				10 / 18.0 * Math.PI));
		arsenal[11] = (new MeleeWeapon("Frost Sword", 0, 10, 110, 120, StatusEffect.FROST, 5, 0.5,
				10 / 18.0 * Math.PI));
		arsenal[12] = (new MeleeWeapon("Fire Sword", 0, 10, 110, 120, StatusEffect.FIRE, 5, 0.5, 10 / 18.0 * Math.PI));
		arsenal[13] = (new MeleeWeapon("Toxic Sword", 0, 10, 110, 120, StatusEffect.TOXIN, 5, 0.5,
				10 / 18.0 * Math.PI));
		arsenal[15] = (new LongRangeWeapon("Hobgoblin Bow", 0, 10, 800, 60, StatusEffect.NONE, 0, 0.0, 25, 1, 50));








		this.addToStorage(new Armor("Baklava", 0, ItemType.HELMET, 0, 0, 0, BattleSuitSet.EMERALD));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new LongRangeWeapon("Long Bow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100));
		this.addToStorage(new Material("ToxicCrystal",0,100));
		this.addToStorage(new Material("GasCrystal",0,100));
		this.addToStorage(new Material("ViralCrystal",0,100));
		this.addToStorage(new Material("CorrosiveCrystal",0,100));
		this.addToStorage(new Material("RottingCrystal",0,100));
		this.addToStorage(new Material("LightningCrystal",0,100));
		this.addToStorage(new Material("FrostCrystal",0,100));
		this.addToStorage(new Material("FireCrystal",0,100));
		
		Item[] fireBowComp= {new LongRangeWeapon("LongBow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100),new Material("FireCrystal",0,1)};
		Item fireBow= new LongRangeWeapon("FireBow", 6, 80, 1000, 30, StatusEffect.FIRE, 6, 0.6, 25, 0.1, 100);
		this.addToStorage(new Blueprint("FireBow",0,100,fireBowComp,fireBow,this));
		Item[] frostBowComp= {new LongRangeWeapon("LongBow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100),new Material("FrostCrystal",0,1)};
		Item frostBow= new LongRangeWeapon("FrostBow", 6, 80, 1000, 30, StatusEffect.FROST, 6, 0.6, 25, 0.1, 100);
		this.addToStorage(new Blueprint("FrostBow",0,100,frostBowComp,frostBow,this));
		Item[] lightningBowComp= {new LongRangeWeapon("LongBow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100),new Material("LightningCrystal",0,1)};
		Item lightningBow= new LongRangeWeapon("LightningBow", 6, 80, 1000, 30, StatusEffect.LIGHTNING, 6, 0.6, 25, 0.1, 100);
		this.addToStorage(new Blueprint("LightningBow",0,100,lightningBowComp,lightningBow,this));
		Item[] rotBowComp= {new LongRangeWeapon("LongBow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100),new Material("RottingCrystal",0,1)};
		Item rotBow= new LongRangeWeapon("RotBow", 6, 80, 1000, 30, StatusEffect.ROT, 6, 0.6, 25, 0.1, 100);
		this.addToStorage(new Blueprint("RotBow",0,100,rotBowComp,rotBow,this));
		Item[] corrosiveBowComp= {new LongRangeWeapon("LongBow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100),new Material("CorrosiveCrystal",0,1)};
		Item corrosiveBow= new LongRangeWeapon("CorrosiveBow", 6, 80, 1000, 30, StatusEffect.CORROSION, 6, 0.6, 25, 0.1, 100);
		this.addToStorage(new Blueprint("FireBow",0,100,corrosiveBowComp,corrosiveBow,this));
		Item[] viralBowComp= {new LongRangeWeapon("LongBow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100),new Material("ViralCrystal",0,1)};
		Item viralBow= new LongRangeWeapon("ViralBow", 6, 80, 1000, 30, StatusEffect.VIRAL, 6, 0.6, 25, 0.1, 100);
		this.addToStorage(new Blueprint("ViralBow",0,100,viralBowComp,viralBow,this));
		Item[] gasBowComp= {new LongRangeWeapon("LongBow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100),new Material("GasCrystal",0,1)};
		Item gasBow= new LongRangeWeapon("GasBow", 6, 80, 1000, 30, StatusEffect.GAS, 6, 0.6, 25, 0.1, 100);
		this.addToStorage(new Blueprint("GasBow",0,100,gasBowComp,gasBow,this));
		Item[] toxicBowComp= {new LongRangeWeapon("LongBow", 6, 80, 1000, 30, StatusEffect.NONE, 6, 0.6, 25, 0.1, 100),new Material("ToxicCrystal",0,1)};
		Item toxicBow= new LongRangeWeapon("ToxicBow", 6, 80, 1000, 30, StatusEffect.TOXIN, 6, 0.6, 25, 0.1, 100);
		this.addToStorage(new Blueprint("ToxicBow",0,100,toxicBowComp,toxicBow,this));


		Item[] it = { new Material("Crystal", 0, 100) };
		this.addToStorage(new Material("Crystal", 0, 100000));
		StatType[] buffTypes = { StatType.HEALTH, StatType.REGEN };
		int[] buffs = { 1000, 500 };

		this.addToStorage(new Consumable("Baklava", 0, 10, 64,
				new Buff(buffTypes, buffs, 10, player.getStatTypes(), player.getBuffs())));
		this.addToStorage(new Consumable("Baklava", 0, 50, 64,
				new Buff(buffTypes, buffs, 10, player.getStatTypes(), player.getBuffs())));
		this.addToStorage(new Consumable("Baklava", 0, 50, 64,
				new Buff(buffTypes, buffs, 10, player.getStatTypes(), player.getBuffs())));
		this.addToStorage(new Consumable("Baklava", 0, 50, 64,
				new Buff(buffTypes, buffs, 10, player.getStatTypes(), player.getBuffs())));
		this.addToStorage(new Blueprint("Baklava", 0, 10, it, new Consumable("Baklava", 0, 15, 64,
				new Buff(buffTypes, buffs, 10, player.getStatTypes(), player.getBuffs())), this));

		this.addToStorage(
				new MeleeWeapon("Rusty Sword", 0, 200, 100, 120, StatusEffect.NONE, 0, 0.0, 10 / 18.0 * Math.PI));
		this.addToStorage(
				new MeleeWeapon("Night Harvester", 0, 500, 170, 20, StatusEffect.ROT, 10, 0.8, 12 / 18.0 * Math.PI));
		this.addToStorage(
				new MeleeWeapon("Silver Rapier", 0, 80, 120, 240, StatusEffect.FROST, 3, 0.4, 8 / 18.0 * Math.PI));
		this.addToStorage(
				new MeleeWeapon("Baklava", 0, 5000, 200, 100, StatusEffect.TOXIN, 10, 1, 36 / 18.0 * Math.PI));

		equipped = 4;
	}

	// Constructor that uses save data
	public Inventory(String saveData, Player player) {
		arsenal = new Item[16];
		storage = new ArrayList<Item>();

		String[] parts = saveData.split("`~`");

		String[] arsenalItems = (parts[1].substring(9, parts[1].length() - 8)).split("Item:");
		for (int i = 1; i < arsenalItems.length; i++) {
			if (arsenalItems[i].contains(",")) {
				arsenal[i - 1] = parseItem(arsenalItems[i].substring(0, arsenalItems[i].indexOf(",")), player);
			} else {
				arsenal[i - 1] = parseItem(arsenalItems[i].substring(0, arsenalItems[i].length() - 1), player);
			}

		}
		String[] storageItems = (parts[2].substring(1, parts[2].length() - 10)).split("Item:");
		for (int i = 0; i < storageItems.length; i++) {
			if (storageItems[i].contains(","))
				storage.add(parseItem(storageItems[i].substring(0, storageItems[i].indexOf(",")), player));
			else
				storage.add(parseItem(storageItems[i], player));

		}
		equipped = Integer.parseInt(parts[3].replace("]", ""));
	}

	// Parses a string, and returns an item
	public Item parseItem(String s, Player player) {
		String[] parts = s.split("/");

		if (s.contains("BLUEPRINT")) {
			String[] sehments = parts[4].split("-++");
			String[] comps = sehments[1].substring(sehments[1].indexOf("[") + 1, sehments[1].indexOf("]")).split("~;~");
			Item[] components = new Item[comps.length];
			for (int i = 0; i < comps.length; i++) {
				components[i] = parseItem((comps[i].substring(comps[i].indexOf(':') + 1).replace(";~;", "/")), player);

			}
			String product = (sehments[2].substring(sehments[2].indexOf(":") + 1, sehments[2].indexOf("count")))
					.replace(";~~;", "/");
			Item resultItem = parseItem(product, player);
			return new Blueprint(parts[0], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), components,
					resultItem, this);

		} else if (s.contains("HELMET") || s.contains("CHESTPLATE") || s.contains("LEGGINGS") || s.contains("BOOTS")) {

			return new Armor(parts[0], Integer.parseInt(parts[2]), ItemType.valueOf(parts[1]),
					Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
					BattleSuitSet.valueOf(parts[6]));
		} else if (s.contains("MATERIAL")) {
			return new Material(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[3]));
		} else if (s.contains("WEAPON")) {
			if (s.contains("MeleeWeapon")) {
				System.out.println(Arrays.toString(parts));
				return new MeleeWeapon(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[3]),
						Integer.parseInt(parts[4]), (int) Double.parseDouble(parts[5]), StatusEffect.valueOf(parts[6]),
						(int) Double.parseDouble(parts[7]), Double.parseDouble(parts[8]),
						Double.parseDouble(parts[9].replace(",(", "")));

			} else if (s.contains("LongRangeWeapon")) {
				return new LongRangeWeapon(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[3]),
						Integer.parseInt(parts[4]), (int) Double.parseDouble(parts[5]), StatusEffect.valueOf(parts[6]),
						(int) Double.parseDouble(parts[7]), Double.parseDouble(parts[8]),
						Integer.parseInt(parts[9].replace(",(", "")), Double.parseDouble(parts[10].replace(",(", "")),
						Integer.parseInt(parts[11].replace(",(", "")));
			}

		} else if (s.contains("CONSUMABLE")) {
			String[] buffParts = parts[5].split("=");
			String[] buffTypes = (buffParts[1].substring(buffParts[1].indexOf('[') + 1, buffParts[1].lastIndexOf(']')))
					.split(";~;");
			String[] buffValues = (buffParts[2].substring(buffParts[1].indexOf('[') + 1, buffParts[1].lastIndexOf(']')))
					.replace("]", "").split(";~;");
			StatType[] types = new StatType[buffTypes.length];
			int[] values = new int[buffTypes.length];
			for (int i = 0; i < buffTypes.length; i++) {
				types[i] = StatType.valueOf(buffTypes[i]);
				values[i] = Integer.parseInt(buffValues[i]);
			}
			return new Consumable(parts[0], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
					Integer.parseInt(parts[4]),
					new Buff(types, values, Integer.parseInt(buffParts[buffParts.length - 1].replace("]", "")),
							player.getStatTypes(), player.getBuffs()));

		}

		return null;

	}

	// Returns the arsenal
	public Item[] getArsenal() {
		return arsenal;
	}

	public void clearAllProjectiles() {
		for (int i = 0; i < arsenal.length; i++) {
			if (arsenal[i] != null && arsenal[i].getClass() == LongRangeWeapon.class) {
				((LongRangeWeapon) arsenal[i]).clearAllProjectiles();
			}
		}
	}

	// Returns the storage
	public ArrayList<Item> getStorage() {
		return storage;
	}

	// Returns the equipped item
	public Item getEquippedItem() {
		return arsenal[equipped];
	}

	// Adds an item to the arsenal
	public void addToArsenal(Item item) throws ArsenalFullException {
		if (item.getType() == ItemType.WEAPON || item.getType() == ItemType.CONSUMABLE) {
			for (int i = 4; i < arsenal.length; i++) {
				if (arsenal[i] == null) {
					arsenal[i] = item;
					storage.remove(item);
					break;
				}
				if (i == arsenal.length - 1) {
					throw new ArsenalFullException("Your Arsenal is full");
				}
			}
		} else if (item.getType() != ItemType.MATERIAL || item.getType() != ItemType.BLUEPRINT) {
			addToArsenal((Armor) item);
		}
	}

	// Removes an item from the arsenal
	public int removeFromArsenal(Item item) {
		for (int i = 0; i < arsenal.length; i++) {
			// checks if the memory references are the same and are thus the same object;
			if (item == arsenal[i]) {

				arsenal[i] = null;
				return i;
			}

		}
		return -1;
	}

	// Removes an item from the arsenal by index
	public void removeFromArsenal(int arsenalIndex) {

		arsenal[arsenalIndex] = null;
	}

	// Removes an item from storage
	public void removeFromStorage(Item item) {
		storage.remove(storage.indexOf(item));
	}

	// Adds an item to storage in the given positon
	public boolean addToArsenal(Item item, int pos) {
		if (item.getType() == ItemType.WEAPON || item.getType() == ItemType.CONSUMABLE) {

			arsenal[pos] = item;
			storage.remove(item);
			return true;
		} else if (item.getType() != ItemType.MATERIAL || item.getType() != ItemType.BLUEPRINT) {
			addToArsenal((Armor) item);
			return false;
		}
		return false;
	}

	// Adds an armor item to its respective position in the arsenal
	public void addToArsenal(Armor armor) {
		int pos = -1;
		if (armor.getType() == ItemType.HELMET) {
			pos = 0;

		} else if (armor.getType() == ItemType.CHESTPLATE) {
			pos = 1;
		} else if (armor.getType() == ItemType.LEGGINGS) {
			pos = 2;
		} else if (armor.getType() == ItemType.BOOTS) {
			pos = 3;
		}
		if (pos != -1 && arsenal[pos] != null) {
			storage.remove(armor);
			storage.add(arsenal[pos]);
			arsenal[pos] = armor;

		}
	}

	// Adds an item to the storage
	public void addToStorage(Item item) {
		if (item.getType() == ItemType.BLUEPRINT || item.getType() == ItemType.CONSUMABLE
				|| item.getType() == ItemType.MATERIAL) {
			Item sameItem = null;
			long consumableExtra = 0;
			for (int i = 0; i < storage.size(); i++) {
				if (storage.get(i) != null && storage.get(i).equals(item)) {
					sameItem = storage.get(i);
					if (item.getType() == ItemType.CONSUMABLE) {
						if (((Consumable) sameItem).getCount() < ((Consumable) sameItem).getMaxStack()) {

							Consumable sameConsumable = (Consumable) sameItem;
							Consumable consItem = (Consumable) item;

							consumableExtra = sameConsumable.add(consItem);
							if (consumableExtra == 0) {
								return;
							} else {
								item = new Consumable(consItem.getName(), consItem.getTier(), consumableExtra,
										consItem.getMaxStack(), consItem.getBuff());

							}
						}

					} else {
						break;
					}
				}
			}
			if (sameItem == null) {
				storage.add(item);

			} else if (item.getType() == ItemType.MATERIAL) {
				Material sameMaterial = (Material) sameItem;
				sameMaterial.add((Material) item);
			} else if (item.getType() == ItemType.BLUEPRINT) {
				Blueprint sameBlueprint = (Blueprint) sameItem;
				sameBlueprint.add((Blueprint) item);
			} else {
				storage.add(item);

			}

		} else {
			storage.add(item);

		}
	}

	// Checks if the inventory contains the same item as the passed in item
	public boolean contains(Item item) {
		for (int i = 0; i < arsenal.length; i++) {
			if (arsenal[i].equals(item)) {
				return true;
			}
		}
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i).equals(item)) {
				return true;

			}
		}
		return false;

	}

	// Searches the storage for a specific item by string name
	public ArrayList<Item> searchStorage(String searchTerm) {
		searchTerm = searchTerm.toLowerCase();
		ArrayList<Item> selection = new ArrayList<Item>();
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i).getName().toLowerCase().contains(searchTerm)) {
				selection.add(storage.get(i));
			}
		}
		return selection;
	}

	// Searchs the storage for a specific item by item type
	public ArrayList<Item> searchStorage(ItemType type) {

		ArrayList<Item> selection = new ArrayList<Item>();

		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i) != null && (storage.get(i) != null && storage.get(i).getType() == type
					|| (type == ItemType.ARMOR && (storage.get(i).getType() == ItemType.BOOTS
							|| storage.get(i).getType() == ItemType.CHESTPLATE
							|| storage.get(i).getType() == ItemType.LEGGINGS
							|| storage.get(i).getType() == ItemType.HELMET)))) {
				selection.add(storage.get(i));
			}
		}
		return selection;
	}

	// Returns the helmet
	public Armor getHelemet() {
		return (Armor) arsenal[0];
	}

	// Returns the chestplate
	public Armor getChestplate() {
		return (Armor) arsenal[1];
	}

	// Returns the leggings
	public Armor getLeggings() {
		return (Armor) arsenal[2];
	}

	// Returns the boots
	public Armor getBoots() {
		return (Armor) arsenal[3];
	}

	// Returns the position of the equipped item
	public int getEquipped() {
		return equipped;
	}

	// Updates the arsenal
	public void updateArsenal() {
		for (int i = 0; i < arsenal.length; i++) {
			if (arsenal[i] != null && arsenal[i].getType() == ItemType.CONSUMABLE) {
				if (((Consumable) arsenal[i]).getCount() <= 0) {
					arsenal[i] = null;
				}
			}
		}
	}

	// Sets the equipped item
	public void setEquipped(int equipped) {
		updateArsenal();
		if (equipped > 15 || equipped < 0) {
			throw new IllegalArgumentException("Out of arsenal bounds");

		} else {
			this.equipped = equipped;
		}
	}

	// Returns total armor
	public double getArmor() {
		return ((Armor) arsenal[0]).getArmor() + ((Armor) arsenal[1]).getArmor() + ((Armor) arsenal[2]).getArmor()
				+ ((Armor) arsenal[3]).getArmor();
	}

	// Returns total shields
	public double getShields() {
		return ((Armor) arsenal[0]).getShields() + ((Armor) arsenal[1]).getShields() + ((Armor) arsenal[2]).getShields()
				+ ((Armor) arsenal[3]).getShields();
	}

	// Returns total health
	public double getHealth() {
		return ((Armor) arsenal[0]).getHealth() + ((Armor) arsenal[1]).getHealth() + ((Armor) arsenal[2]).getHealth()
				+ ((Armor) arsenal[3]).getHealth();
	}

	@Override
	// String parsing for inventory
	public String toString() {
		return ("Inventory `~`arsenal=" + Arrays.toString(arsenal) + ",storage`~`" + storage + ",equipped`~`" + equipped
				+ "]").replace(" ", "");
	}

}
