package mapGeneration;

import java.awt.*;
import javax.swing.*;
import general.ImageSystem;
import general.SaveSystem;

public class MapGenerator {
	ImageSystem image;
	private char[][] maze;
	
	private char[][] roomsStraights;
	
	public MapGenerator() {
		
	}
	
	public void generateMap(int size) {
		MazeGenerator generator=new MazeGenerator(0);
		maze=generator.generate(size);
	}
	
	public void getRoom(int x,int y) {
		
	}
}
