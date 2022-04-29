package mapGeneration;

import java.awt.*;
import javax.swing.*;
import general.ImageSystem;
import general.SaveSystem;

public class MapGenerator {
	public static final int XSIZE=20;
	public static final int YSIZE=15;
	
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
	
	private char[][][][] rooms;
	
	
	public MapGenerator() {
		generateMaze();
		generateMap();
		for(int i=0;i<16;i++) {
		}
		
	}
	
	public void generateMaze() {
		MazeGenerator generator=new MazeGenerator(0);
		maze=generator.generate(XSIZE,YSIZE);
	}
	
	public void generateMap() {
		
	}
	
	public void getRoom(int x,int y) {
		
	}
}
