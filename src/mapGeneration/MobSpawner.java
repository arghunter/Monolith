//Author: Peter Ferolito
//Date: 5/5/22
//Notes: Randomly spawns mobs based on player's level and difficulty of the mobs

package mapGeneration;

import java.util.ArrayList;
import general.Constants;
import java.util.Random;

public class MobSpawner {
	//Spider,zombie,goblin,orc,hobgoblin,shadow,armorplant,speedplant,healthplant,damageplant,pottedplant,cactus,sporeshroom,troll,balkrada,icedrake
	private int[][] defaultProbs = {{90,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{80,18,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{70,25,5,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{60,30,10,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{50,35,12,3,0,0,0,0,0,0,0,0,0,0,0,0},
									{40,35,15,7,3,0,0,0,0,0,0,0,0,0,0,0},
									{35,30,15,10,5,5,0,0,0,0,0,0,0,0,0,0}};
	private int[][] gardenProbs = {{0,0,0,0,0,0,10,10,10,10,59,1,0,0,0,0},
								   {0,0,0,0,0,0,14,14,14,14,40,4,0,0,0,0},
								   {0,0,0,0,0,0,16,16,16,16,20,10,6,0,0,0},
								   {0,0,0,0,0,0,19,19,19,19,4,10,10,0,0,0},
								   {0,0,0,0,0,0,19,19,19,19,0,14,10,0,0,0},
								   {0,0,0,0,0,0,17,17,17,17,0,17,15,0,0,0},
								   {0,0,0,0,0,0,15,15,15,15,0,20,20,0,0,0}};
	private int[][] bossProbs = {{0,15,20,15,10,10,0,0,0,0,0,15,15,0,0,0},
								 {0,10,25,15,10,10,0,0,0,0,0,15,15,0,0,0},
								 {0,5,25,20,10,10,0,0,0,0,0,15,15,0,0,0},
								 {0,0,20,30,15,5,0,0,0,0,0,15,15,0,0,0},
								 {0,0,15,35,15,5,0,0,0,0,0,15,15,0,0,0},
								 {0,0,5,40,20,0,0,0,0,0,0,15,15,3,2,0},
								 {0,0,0,40,20,0,0,0,0,0,0,12,12,7,7,2}};
	private int[] cost= {1,2,4,6,8,4,1,1,1,1,1,2,3,60,70,80};
	private int numMobs=Constants.NUMMOBS;
	private Random randNums = new Random();
	
	public MobSpawner(){
		
	}
	
	//Spawns mobs using the default probabilities
	public ArrayList<Integer> generateMobs(int playerLevel) {
		int[][] toUse=defaultProbs;
		if(playerLevel<6) {
			if(randNums.nextInt(playerLevel)%playerLevel==0) {
				System.out.println("g");
				toUse=gardenProbs;
			}else {
				System.out.println("d");

				toUse=defaultProbs;
			}
		}else if(playerLevel>20) {
			if(randNums.nextInt(playerLevel/4)%(playerLevel/4)==0) {
				System.out.println("d");

				toUse=defaultProbs;
			}else {
				System.out.println("b");

				toUse=bossProbs;
			}
		}
		int mobRatio=0;
		mobRatio=playerLevel/4;
		if(mobRatio>=toUse.length) {
			mobRatio=toUse.length-1;
		}
		//Minimum and maximum mobs that can spawn
		int minMobs=playerLevel/3+5;
		int maxMobs=playerLevel*2+6;
		
		int numMobsToSpawn=minMobs+randNums.nextInt(maxMobs-minMobs+1);
		
		ArrayList<Integer> toReturn=new ArrayList<Integer>();
		
		//Create the mobs to spawn
		int i=0;
		while(numMobsToSpawn>=0) {
			int mobToSpawn=randNums.nextInt(100);
			int actualMob=0;
			System.out.println(mobToSpawn);
			for(int j=0;j<numMobs;j++) {
				mobToSpawn-=toUse[mobRatio][j];
				if(mobToSpawn<0) {
					actualMob=j;
					break;
				}
			}
			toReturn.add(actualMob);
			numMobsToSpawn-=cost[actualMob];
			System.out.println(actualMob);
			i++;
		}
		return toReturn;
	}
	
	//Spawns mobs using the passed probabilities
	public int[] generateMobs(int playerLevel,int[] probs) {
		int[] toUse;
		
		//Checks if passed probs are valid
		if(probs.length!=numMobs || sum(probs)!=100) {
			toUse=probs;
		}else {
			int mobRatio=0;
			mobRatio=playerLevel/4;
			if(mobRatio>=defaultProbs.length) {
				mobRatio=defaultProbs.length-1;
			}
			toUse=defaultProbs[mobRatio];
		}
		
		//Minimum and maximum mobs that can spawn
		int minMobs=2*playerLevel/1+1;
		int maxMobs=2*playerLevel/1+2;
		
		int numMobsToSpawn=minMobs+randNums.nextInt(maxMobs-minMobs+1);
		
		int[] toReturn=new int[numMobsToSpawn];
		
		//Create the mobs to spawn
		for(int i=0;i<numMobsToSpawn;i++) {
			int mobToSpawn=randNums.nextInt(100);
			int actualMob=0;
			for(int j=0;j<numMobs;j++) {
				mobToSpawn-=toUse[j];
				if(mobToSpawn<0) {
					actualMob=j;
					break;
				}
			}
			toReturn[i]=actualMob;
			
		}
		return toReturn;
	}
	
	//Finds the sum of the elements of an array
	private int sum(int[] x) {
		int sumOfElements=0;
		for(int i=0;i<x.length;i++) {
			sumOfElements+=x[i];
		}
		return sumOfElements;
	}
}
