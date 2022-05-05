package mapGeneration;

import general.Constants;
import java.util.Random;

public class MobSpawner {
	private int[][] defaultProbs = {{90,10,0},{80,18,2},{70,25,5},{60,30,10},{50,35,15}};
	private int[] levels =         {0        , 2       , 5       , 8        , 13      };
	//Levels                          0-1        2-4      5-7        8-12       13+
	
	private int numMobs=Constants.NUMMOBS;
	private Random randNums = new Random(0);
	
	public MobSpawner(){
		
	}
	
	public int generateMobs(int playerLevel) {
		int mobRatio=0;
		for(int i=0;i<levels.length-1;i++) {
			if(playerLevel<levels[i+1]) {
				break;
			}
			mobRatio++;
		}
		int minMobs=playerLevel/5+1;
		int maxMobs=playerLevel/2+2;
		int numMobsToSpawn=minMobs+randNums.nextInt(maxMobs-minMobs+1);
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
			
		}
		return 0;
	}
	
	
}
