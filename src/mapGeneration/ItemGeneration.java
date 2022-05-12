//Author: Peter Ferolito
//Date: 5/12/22
//Notes: Randomly generates items based on player level

package mapGeneration;

import java.util.Random;

public class ItemGeneration {
	int[] typeProbs = {40,20,20,20};
	//Material, weapon, armor, consumables
	int[][] tierProbs = {{35,25,15,10,5,3,2,2,1,1,1}, //0-1
			           {30,30,15,10,5,3,2,2,1,1,1},   //2-3
			           {25,30,20,10,5,3,2,2,1,1,1},   //4-5
			           {15,35,20,12,6,4,2,2,1,1,2},   //6-7
			           {10,35,20,12,8,5,3,3,1,1,2},   //8-9
			           {5,30,25,14,10,5,3,3,2,1,2},   //10-11
			           {2,23,25,20,12,5,3,3,2,1,4},   //12-13
			           {2,15,20,30,15,5,3,3,2,1,4},   //14-15
			           {2,10,15,35,20,5,3,3,2,1,4},   //16-17
			           {2,5,10,35,25,7,4,4,2,1,5},   //18-19
					   {2,2,10,30,30,10,4,4,2,1,5}};   //20+
	
	Random randNums=new Random(0);
	
	public ItemGeneration() {
		
	}
	
	public void getItem(int playerLevel) {
		//lol void
	}
}
