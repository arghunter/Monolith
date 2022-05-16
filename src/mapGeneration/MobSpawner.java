//Author: Peter Ferolito
//Date: 5/5/22
//Notes: Randomly spawns mobs based on player's level and difficulty of the mobs

package mapGeneration;

import general.Constants;
import java.util.Random;

public class MobSpawner {
	private int[][] defaultProbs = {{90,10,0},{80,18,2},{70,25,5},{60,30,10},{50,35,15}};
	private int[] levels =         {0        , 2       , 5       , 8        , 13      };
	//Levels                          0-1        2-4      5-7        8-12       13+
	private int numMobs=Constants.NUMMOBS;
	private Random randNums = new Random(Constants.FIXEDSEED);
	
	public MobSpawner(){
		
	}
	
	//Spawns mobs using the default probabilities
	public int[] generateMobs(int playerLevel) {
		int mobRatio=0;
		for(int i=0;i<levels.length-1;i++) {
			if(playerLevel<levels[i+1]) {
				break;
			}
			mobRatio++;
		}
		
		//Minimum and maximum mobs that can spawn
		int minMobs=playerLevel/5+5;
		int maxMobs=playerLevel/2+6;
		
		int numMobsToSpawn=minMobs+randNums.nextInt(maxMobs-minMobs+1);
		
		int[] toReturn=new int[numMobsToSpawn];
		
		//Create the mobs to spawn
		for(int i=0;i<numMobsToSpawn;i++) {
			int mobToSpawn=randNums.nextInt(100);
			int actualMob=0;
			for(int j=0;j<numMobs;j++) {
				mobToSpawn-=defaultProbs[mobRatio][j];
				if(mobToSpawn<0) {
					actualMob=j;
					break;
				}
			}
			toReturn[i]=actualMob;
			
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
			for(int i=0;i<levels.length-1;i++) {
				if(playerLevel<levels[i+1]) {
					break;
				}
				mobRatio++;
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
