//Author: Peter Ferolito
//Date: 5/12/22
//Notes: Randomly generates items based on player level

package mapGeneration;

import GameObjects.Player.items.*;
import GameObjects.mobs.Mob;

import java.util.Random;

public class ItemGeneration {
	static int[] typeProbs = {40,20,20,20};
	//Material, weapon, armor, consumables
	static int[][] tierProbs = {{35,25,15,10,5,3,2,2,1,1,1}, //0-3
			           {30,30,15,10,5,3,2,2,1,1,1},   //4-7
			           {25,30,20,10,5,3,2,2,1,1,1},   //8-11
			           {15,35,20,12,6,4,2,2,1,1,2},   //12-15
			           {10,35,20,12,8,5,3,3,1,1,2},   //16-19
			           {5,30,25,14,10,5,3,3,2,1,2},   //20-23
			           {2,23,25,20,12,5,3,3,2,1,4},   //24-27
			           {2,15,20,30,15,5,3,3,2,1,4},   //28-31
			           {2,10,15,35,20,5,3,3,2,1,4},   //32-33
			           {2,5,10,35,25,7,4,4,2,1,5},    //36-39
					   {2,2,10,30,30,10,4,4,2,1,5}};   //40+
	
	Random randNums=new Random(0);
	

	
	public void getItem(int playerLevel) {
		//return createItem(playerLevel*=2);
	}
	
	public static Item getItem(int playerLevel,int mobLevel) {
		return null;
		
	}
	

	
	
}
