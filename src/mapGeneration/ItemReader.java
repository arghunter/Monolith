package mapGeneration;

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
	
	public ItemReader() {
		for(int i=0;i<11;i++) {
			
		}
		try{
			readItems();
		}catch (Exception e) {
			System.out.println("Can't read rooms from file");
		}
	}
	
	private void readItems() throws FileNotFoundException{
		FileInput input=new FileInput("ItemsAndMobs/Consumables.txt");
		consumables=new Consumable[10][];
		for(int i=0;i<10;i++) {
			int tempNum=Integer.parseInt(input.next());
			consumables[i]=new Consumable[tempNum];
		}
		
		input=new FileInput("ItemsAndMobs/Materials.txt");
		for(int i=0;i<10;i++) {
			
		}
		
		input=new FileInput("ItemsAndMobs/MeleeWeapons.txt");
		
		meleeWeapons=new MeleeWeapon[10][];
		for(int i=0;i<10;i++) {
			int tempNum=Integer.parseInt(input.next());
			meleeWeapons[i]=new MeleeWeapon[tempNum];
			for(int j=0;j<tempNum;j++) {
				String item=input.next();
				item.replace("_"," ");
				int[] positions=new int[5];
				positions[0]=item.indexOf('~');
				for(int k=1;k<5;k++) {
					positions[k]=item.indexOf('~', positions[k-1]+1);
				}
				//meleeWeapons[i][j]=new MeleeWeapon();
			}
		}
	}
}
