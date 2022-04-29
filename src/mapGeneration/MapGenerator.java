package mapGeneration;

import java.awt.*;
import javax.swing.*;
import general.ImageSystem;
import general.FileInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MapGenerator {
	public static final int XSIZE=20;
	public static final int YSIZE=15;
	
	public static final int ROOMSIZEX=18;
	public static final int ROOMSIZEY=14;
	
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
	
	private int[] numRooms = new int[16];
	
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
			FileInput input = new FileInput("Rooms"+(i<10?"0":"")+i+".txt");
			int numRooms=Integer.parseInt(input.next());
			maxRooms=(numRooms>maxRooms?numRooms:maxRooms);
			
		}
		rooms=new int[16][maxRooms][ROOMSIZEY][ROOMSIZEX];
		for(int i=0;i<16;i++) {
			FileInput input = new FileInput("Rooms"+(i<10?"0":"")+i+".txt");
			for(int j=0;j<numRooms[i];j++) {
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
		
	}
	
	public void getRoom(int x,int y) {
		
	}
}
