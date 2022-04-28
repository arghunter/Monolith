package mapGeneration;

import java.awt.*;
import javax.swing.*;
import general.ImageSystem;
import general.SaveSystem;

public class MapGenerator {
	public static final int XSIZE=20;
	public static final int YSIZE=15;
	
	ImageSystem image;
	private char[][] maze;
	
	private char[][] roomsStraights;
	
	public MapGenerator() {
		generateMap();
	}
	
	public void generateMap() {
		MazeGenerator generator=new MazeGenerator(0);
		maze=generator.generate(XSIZE,YSIZE);
	}
	
	public void getRoom(int x,int y) {
		
	}
}
