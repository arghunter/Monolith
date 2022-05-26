//Author: Peter Ferolito
//Date: 5/18/22
//Notes: Reads the items out of the files

package mapGeneration;

import skills.StatType;
import GameObjects.Player.*;
import GameObjects.elementalDamage.*;
import java.awt.*;

import javax.swing.*;
import general.ImageSystem;
import general.FileInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import general.Constants;
import GameObjects.Player.items.*;
import GameObjects.Player.items.armor.*;
import GameObjects.Player.items.blueprints.*;
import GameObjects.Player.items.consumables.*;
import GameObjects.Player.items.materials.*;
import GameObjects.Player.items.weapons.*;

public class ItemReader {
	public MeleeWeapon[][] meleeWeapons;
	public Consumable[][] consumables;
	public Material[][] materials;
	public LongRangeWeapon[][] longRangeWeapons;
	public Armor[][] armor;
	private Player player;
	
	public ItemReader(Player player) {
		for(int i=0;i<11;i++) {
			
		}
		try{
			readItems(player);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readItems(Player player) throws FileNotFoundException{
		FileInput input;
		input=new FileInput("ItemsAndMobs/Armor.txt");
		armor=new Armor[11][];
		for(int i=0;i<11;i++) {
			int tempNum=Integer.parseInt(input.next());
			armor[i]=new Armor[tempNum]; 
			for(int j=0;j<tempNum;j++) {
				String item=input.next();
				item=item.replace('_',' ');
				String[] splitItem=item.split("~",-1);
				ItemType IT=ItemType.HELMET;
				if(splitItem[2].equals("CHESTPLATE")) {
					IT=ItemType.CHESTPLATE;
				}else if(splitItem[2].equals("LEGGINGS")) {
					IT=ItemType.LEGGINGS;
				}else if(splitItem[2].equals("BOOTS")) {
					IT=ItemType.BOOTS;
				}
				armor[i][j]=new Armor(splitItem[0],Integer.parseInt(splitItem[1]),IT,Integer.parseInt(splitItem[3]),Integer.parseInt(splitItem[4]),Integer.parseInt(splitItem[5]),BattleSuitSet.NONE);
			}
		}
		
		input=new FileInput("ItemsAndMobs/Consumables.txt");
		consumables=new Consumable[11][];
		for(int i=0;i<11;i++) {
			int tempNum=Integer.parseInt(input.next());
			consumables[i]=new Consumable[tempNum];
			for(int j=0;j<tempNum;j++) {
				String item=input.next();
				item=item.replace('_',' ');
				String[] splitItem=item.split("~",-1);
				int numBuffs=(splitItem.length-6)/2;
				StatType[] theBuffs=new StatType[numBuffs];
				int[] theValues=new int[numBuffs];
				for(int k=5;k<5+numBuffs;k++) {
					if(splitItem[k].equals("SPEED")) {
						theBuffs[k-5]=StatType.SPEED;
					}else if(splitItem[k].equals("STRENGTH")) {
						theBuffs[k-5]=StatType.STRENGTH;
					}else if(splitItem[k].equals("ACCURACY")) {
						theBuffs[k-5]=StatType.ACCURACY;
					}else if(splitItem[k].equals("HEALTH")) {
						theBuffs[k-5]=StatType.HEALTH;
					}else if(splitItem[k].equals("REGEN")) {
						theBuffs[k-5]=StatType.REGEN;
					}else if(splitItem[k].equals("SHIELD")) {
						theBuffs[k-5]=StatType.SHIELD;
					}else if(splitItem[k].equals("ARMOR")) {
						theBuffs[k-5]=StatType.ARMOR;
					}else if(splitItem[k].equals("ATTACKSPEED")) {
						theBuffs[k-5]=StatType.ATTACKSPEED;
					}else if(splitItem[k].equals("POWER")) {
						theBuffs[k-5]=StatType.POWER;
					}else if(splitItem[k].equals("XP")) {
						theBuffs[k-5]=StatType.XP;
					}else if(splitItem[k].equals("MULTIPLE")) {
						theBuffs[k-5]=StatType.MULTIPLE;
					}else {
						theBuffs[k-5]=StatType.HEALTH;
					}
					theValues[k-5]=Integer.parseInt(splitItem[k+numBuffs]);
				}
				consumables[i][j]=new Consumable(splitItem[0],Integer.parseInt(splitItem[1]),Integer.parseInt(splitItem[2]),Integer.parseInt(splitItem[3]),new Buff(theBuffs,theValues,Integer.parseInt(splitItem[splitItem.length-1]),player.getStatTypes(),player.getStats()));
			}
		}
		
		input=new FileInput("ItemsAndMobs/Materials.txt");
		materials=new Material[11][];
		for(int i=0;i<11;i++) {
			int tempNum=Integer.parseInt(input.next());
			materials[i]=new Material[tempNum];
			for(int j=0;j<tempNum;j++) {
				String item=input.next();
				item=item.replace("_"," ");
				materials[i][j]=new Material(item.substring(0,item.indexOf('~')),Integer.parseInt(item.substring(item.indexOf('~')+1,item.indexOf('~',item.indexOf('~')+1))),Integer.parseInt(item.substring(item.indexOf('~',item.indexOf('~')+1)+1)));
			}
		}		

		input=new FileInput("ItemsAndMobs/MeleeWeapons.txt");
		meleeWeapons=new MeleeWeapon[11][];
		for(int i=0;i<11;i++) {
			int tempNum=Integer.parseInt(input.next());
			meleeWeapons[i]=new MeleeWeapon[tempNum];
			for(int j=0;j<tempNum;j++) {
				String item=input.next();
				item=item.replace("_"," ");
				int[] positions=new int[8];
				positions[0]=item.indexOf('~');
				for(int k=1;k<8;k++) {
					positions[k]=item.indexOf('~', positions[k-1]+1);
				}
				StatusEffect effect=StatusEffect.NONE;
				String ef=item.substring(positions[4]+1,positions[5]);
				if(ef.equals("NONE")) {
					effect=StatusEffect.NONE;
				}else if(ef.equals("FIRE")) {
					effect=StatusEffect.FIRE;
				}else if(ef.equals("FROST")) {
					effect=StatusEffect.FROST;
				}else if(ef.equals("LIGHTNING")) {
					effect=StatusEffect.LIGHTNING;
				}else if(ef.equals("ROT")) {
					effect=StatusEffect.ROT;
				}else if(ef.equals("TOXIN")) {
					effect=StatusEffect.TOXIN;
				}else if(ef.equals("BLAST")) {
					effect=StatusEffect.BLAST;
				}else if(ef.equals("CORROSION")) {
					effect=StatusEffect.CORROSION;
				}else if(ef.equals("GAS")) {
					effect=StatusEffect.GAS;
				}else if(ef.equals("VIRAL")) {
					effect=StatusEffect.VIRAL;
				}
				meleeWeapons[i][j]=new MeleeWeapon(item.substring(0,positions[0]),Integer.parseInt(item.substring(positions[0]+1,positions[1])),Integer.parseInt(item.substring(positions[1]+1,positions[2])),Integer.parseInt(item.substring(positions[2]+1,positions[3])),Integer.parseInt(item.substring(positions[3]+1,positions[4])),effect,Integer.parseInt(item.substring(positions[5]+1,positions[6])),Integer.parseInt(item.substring(positions[6]+1,positions[7])),Double.parseDouble(item.substring(positions[7]+1)));
			}
		} 
	}
}
