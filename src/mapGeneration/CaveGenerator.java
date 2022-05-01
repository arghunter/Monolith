package mapGeneration;

import java.util.Random;

public class CaveGenerator {
	char[][] map=new char[30][50];
	Random randomNums = new Random(0);
	
	public CaveGenerator() {
		for(int i=0;i<30;i++) {
			for(int j=0;j<50;j++) {
				map[i][j]='#';
			}
		}
		
		
	}
	
	public void walk() {
		int curX=25;
		int curY=15;
		
	}
}
