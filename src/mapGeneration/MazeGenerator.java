//Author: Peter Ferolito
//Date: 4/17/22
//Notes: Generates a maze using a modified recursive backtracker
//       Credits for original algorithm: Wikipedia Article, Maze Generation Algorithms

package mapGeneration;
import java.util.LinkedList;
import java.util.Random;

public class MazeGenerator {
	private Random randomNums=new Random();  //Random number generator
	private double branchingAmount=0.5;      //Higher=more branches
	
	public MazeGenerator() {
			}
	
	public MazeGenerator(int seed) {
		randomNums=new Random(seed);
	}
	
	public MazeGenerator(double branchingAmount) {
		this.branchingAmount=branchingAmount;
	}
	
	public MazeGenerator(int seed,double branchingAmount) {
		randomNums=new Random(seed);
		this.branchingAmount=branchingAmount;
	}
	
	//Generates a maze using a modified recursive backtracker
	//Uses an iterative approach to avoid possible stack overflow
	//branchingAmount adjusts whether the method pops off the front or the middle of the stack more frequently
	//Note that the stack is actually a linked list to allow for popping off the middle
	public char[][] generate(int xSize,int ySize) {
		//Right, Down, Left, Up
		int[] adjX={1,0,-1,0};
		int[] adjY={0,1,0,-1};
		
		LinkedList<Integer[]> stack=new LinkedList<Integer[]>();
		Integer[] start={1,1};
		stack.add(start);
		
		boolean[][] visited=new boolean[ySize][xSize];
		char[][] maze=new char[2*ySize+1][2*xSize+1];
		
		//Create the initial maze
		for(int i=0;i<2*ySize+1;i++) {
			for(int j=0;j<2*xSize+1;j++) {
				if(i%2==0 || j%2==0) {
					maze[i][j]='#';
				}else {
					maze[i][j]=' ';
				}
			}
		}
		
		visited[start[0]][start[1]]=true;
		
		while(stack.size()>0) {
			Integer[] current;
			
			//Decide where to start the next path
			if(randomNums.nextFloat()<branchingAmount) {
				current=stack.remove(randomNums.nextInt(stack.size()));
			}else {
				current=stack.remove(stack.size()-1);
			}
			
			int numUnvisited=0;
			for(int i=0;i<4;i++) {
				if(current[0]+adjY[i]>=0 && current[0]+adjY[i]<ySize && current[1]+adjX[i]>=0 && current[1]+adjX[i]<xSize) {
					if(!visited[current[0]+adjY[i]][current[1]+adjX[i]]) {
						numUnvisited++;
					}
				}
			}
			if(numUnvisited>0) {
				Integer[][] unvisitedNeighbors=new Integer[numUnvisited][2];
				int unvisitedLeft=numUnvisited;
				for(int i=0;i<4;i++) {
					if(current[0]+adjY[i]>=0 && current[0]+adjY[i]<ySize && current[1]+adjX[i]>=0 && current[1]+adjX[i]<xSize) {
						if(!visited[current[0]+adjY[i]][current[1]+adjX[i]]) {
							unvisitedNeighbors[unvisitedLeft-1][0]=current[0]+adjY[i];
							unvisitedNeighbors[unvisitedLeft-1][1]=current[1]+adjX[i];
							unvisitedLeft--;
						}
					}
				}
				Integer[] newCurrent=unvisitedNeighbors[randomNums.nextInt(numUnvisited)];
				maze[1+current[0]+newCurrent[0]][1+current[1]+newCurrent[1]]=' ';
				stack.add(current);
				stack.add(newCurrent);
				visited[newCurrent[0]][newCurrent[1]]=true;
			}
		}
		return maze;
	}
	
	//Returns the current value of branchingAmount
	public double getBranchingAmount() {
		return branchingAmount;
	}
	
	//Sets the value of branchingAmount to the passed value
	public void setBranchingAmount(double branchingAmount) {
		this.branchingAmount=branchingAmount;
	}
}
