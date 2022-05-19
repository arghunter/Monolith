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
	public MeleeWeapon[] meleeWeapons;
	
	public ItemReader() {
		try{
			readItems();
		}catch (Exception e) {
			System.out.println("Can't read rooms from file");
		}
	}
	
	private void readItems() throws FileNotFoundException{
		
	}
}
