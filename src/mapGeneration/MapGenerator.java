package mapGeneration;

import java.awt.*;
import javax.swing.*;
import general.ImageSystem;
import general.FileInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class MapGenerator {
	public static final int XSIZE=20;
	public static final int YSIZE=15;
	
	public static final int ROOMSIZEX=46;
	public static final int ROOMSIZEY=36;
	
	//Each room is stored in the form ABCD where ABCD are digits
	//AB represents the type of room: for each exit, add the corresponding number
	//CD represents the room number in the corresponding array
	//
	// +-- 1 --+
	// |       |
	// 8       2
	// |       |
	// +-- 4 --+
	// 
	
	ImageSystem image;
	private char[][] maze;
	
	private int[][][][] rooms;
	
	//VERY IMPORTANT THIS IS THE ACTUAL MAP
	private int[][] map = new int[YSIZE][XSIZE];
	
	private int[] numRooms = new int[16];
	
	private Random randomNums = new Random(0);
	
	public MapGenerator() {
		generateMaze();
		try {
			readRooms();
		} catch(Exception e) {
			System.out.println("Can't read rooms from files");
			e.printStackTrace();
		}
		generateMap();
	}
	
	private void readRooms() throws FileNotFoundException {
		int maxRooms=-1;
		for(int i=0;i<16;i++) {
			System.out.println("Rooms/Rooms"+(i<10?"0":"")+i+".txt");
			FileInput input = new FileInput("Rooms/Rooms"+(i<10?"0":"")+i+".txt");
			numRooms[i]=Integer.parseInt(input.next());
			maxRooms=(numRooms[i]>maxRooms?numRooms[i]:maxRooms);
			
		}
		rooms=new int[16][maxRooms][ROOMSIZEY][ROOMSIZEX];
		for(int i=0;i<16;i++) {
			FileInput input = new FileInput("Rooms/Rooms"+(i<10?"0":"")+i+".txt");
			input.next();
			for(int j=0;j<numRooms[i];j++) {
				input.next();
				for(int k=0;k<ROOMSIZEY;k++) {
					String s=input.next();
					for(int m=0;m<ROOMSIZEX;m++) {
						rooms[i][j][k][m]=Integer.parseInt(s.substring(m*2,m*2+2));
					}
				}
			}
		}
	}
	
	private void generateMaze() {
		MazeGenerator generator=new MazeGenerator(0);
		maze=generator.generate(XSIZE,YSIZE);
	}
	
	private void generateMap() {
		for(int i=0;i<YSIZE;i++) {
			for(int j=0;j<XSIZE;j++) {
				int[] adjY = {-1,0,1,0};
				int[] adjX = {0,1,0,-1};
				int roomType=0;
				int powTwo=1;
				/*for(int k=0;k<maze.length;k++) {
					for(int m=0;m<maze[0].length;m++) {
						System.out.print(maze[k][m]);
					}
					System.out.println();
				}*/
				for(int k=0;k<4;k++) {
					if(maze[1+2*i+adjY[k]][1+2*j+adjX[k]]==' ') {
						roomType+=powTwo;
					}
					powTwo*=2;
				}
				if(numRooms[roomType]==0) {
					roomType=0;
				}
				int randRoom=randomNums.nextInt(0,numRooms[roomType]);
				String output=(roomType<10?"0"+roomType:""+roomType)+(randRoom<10?"0"+randRoom:""+randRoom);
				map[i][j]=Integer.parseInt(output);
			}
		}
	}
	
	private void readRoom() {
		
	}
	
	public void getRoom(int x,int y) {
		
	}
}
