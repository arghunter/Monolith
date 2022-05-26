//Author: Peter Ferolito
//Date: 4/28/22
//Notes: Generates the terrain for the game
//       Uses MazeGenerator to make a maze, then adds rooms in to make the game more interesting

package mapGeneration;

import java.awt.*;

import javax.swing.*;
import general.ImageSystem;
import general.FileInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import general.Constants;

public class MapGenerator {
	// Number of rooms in each direction
	public static final int XSIZE = Constants.XSIZE;
	public static final int YSIZE = Constants.YSIZE;

	// Number of tiles in each room
	public static final int ROOMSIZEX = Constants.ROOMSIZEX;
	public static final int ROOMSIZEY = Constants.ROOMSIZEY;

	// Each room is stored in the form ABCD where ABCD are digits
	// AB represents the type of room: for each exit, add the corresponding number
	// CD represents the room number in the corresponding array
	//
	// +-- 1 --+
	// | |
	// 8 2
	// | |
	// +-- 4 --+
	//

	// The generated maze
	private char[][] maze;

	// The list of all possible rooms
	private String[][][][] rooms;

	// VERY IMPORTANT THIS IS THE ACTUAL MAP
	private String[][] map = new String[YSIZE][XSIZE];
	private boolean[][] visits = new boolean[YSIZE][XSIZE];
	// The number of rooms of each type
	private int[] numRooms = new int[16];

	// Random number generator
	private Random randomNums = new Random(Constants.FIXEDSEED);

	public MapGenerator() {
		// Generate the maze
		generateMaze();

		// Read rooms
		try {
			readRooms();
		} catch (Exception e) {
			System.out.println("Can't read rooms from files");
			e.printStackTrace();
		}

		// Generate the map
		generateMap();
	}

	// Read the rooms into the array rooms
	private void readRooms() throws FileNotFoundException {
		int maxRooms = -1; // Stores the maximum number of rooms of a type

		for (int i = 0; i < 16; i++) {
			// Read the number of rooms from each file
			FileInput input = new FileInput("Rooms/Rooms" + (i < 10 ? "0" : "") + i + ".txt");
			numRooms[i] = Integer.parseInt(input.next());
			maxRooms = (numRooms[i] > maxRooms ? numRooms[i] : maxRooms);
		}

		rooms = new String[16][maxRooms][ROOMSIZEY][ROOMSIZEX];

		for (int i = 0; i < 16; i++) {
			FileInput input = new FileInput("Rooms/Rooms" + (i < 10 ? "0" : "") + i + ".txt");
			input.next();

			for (int j = 0; j < numRooms[i]; j++) {

				input.next();

				for (int k = 0; k < ROOMSIZEY; k++) {

					// Read in the line
					String s = input.next();

					for (int m = 0; m < ROOMSIZEX; m++) {
						// Read in the tile
						rooms[i][j][k][m] = s.substring(m * 2, m * 2 + 2);
					} 
				}
			}
		}
	}

	// Uses MazeGenerator to generate a maze
	private void generateMaze() {
		MazeGenerator generator = new MazeGenerator(Constants.FIXEDSEED);
		maze = generator.generate(XSIZE, YSIZE);
	}

	// Generate the actual map
	private void generateMap() {
		for (int i = 0; i < YSIZE; i++) {
			for (int j = 0; j < XSIZE; j++) {
				int[] adjY = { -1, 0, 1, 0 };
				int[] adjX = { 0, 1, 0, -1 };

				// Stores the exits
				int roomType = 0;
				int powTwo = 1;
				for (int k = 0; k < 4; k++) {
					// If there is an exit, add to roomType
					if (maze[1 + 2 * i + adjY[k]][1 + 2 * j + adjX[k]] == ' ') {
						roomType += powTwo;
					}
					powTwo <<= 1;
				}

				// Choose a random room and add it to the map
				int randRoom = randomNums.nextInt(numRooms[roomType]);
				String output = (roomType < 10 ? "0" + roomType : "" + roomType)
						+ (randRoom < 10 ? "0" + randRoom : "" + randRoom);
				map[i][j] = output;
			}
		}
	}

	// Returns the size of the room in the x direction
	public int getRoomSizeX() {
		return ROOMSIZEX;
	}
	//Visits the given room
	public void visitRoom(int x, int y) {
		visits[y][x] = true;
	}
	//Returns true if the player has visited the given room
	public boolean hasVisited(int x, int y) {
		return visits[y][x];
	}

	// Returns the size of the room in the y direction
	public int getRoomSizeY() {
		return ROOMSIZEY;
	}

	// Returns the number of rooms in the x direction
	public int getXSize() {
		return XSIZE;
	}

	// Returns the number of rooms in the y direction
	public int getYSize() {
		return YSIZE;
	}

	// Returns the corresponding room from the array of rooms
	private String[][] readRoom(String input) {
		return rooms[Integer.parseInt(input.substring(0, 2))][Integer.parseInt(input.substring(2, 4))];
	}

	// Returns an array with the tiles in the corresponding room
	public String[][] getRoom(int x, int y) {
		return readRoom(map[y][x]);
	}
}
