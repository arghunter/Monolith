package mapGeneration;
import java.util.LinkedList;
import java.util.Random;

public class MazeGenerator {
	private Random randomNums=new Random(0);
	private final double branchingAmount=0.1; //Higher=more branches
	
	public MazeGenerator() {
		
	}
	
	public char[][] generate(int size) {
		//Right, Down, Left, Up
		int[] adjX={1,0,-1,0};
		int[] adjY={0,1,0,-1};
		
		LinkedList<Integer[]> stack=new LinkedList<Integer[]>();
		Integer[] start={1,1};
		stack.add(start);
		
		boolean[][] visited=new boolean[size][size];
		char[][] maze=new char[2*size+1][2*size+1];
		
		for(int i=0;i<2*size+1;i++) {
			for(int j=0;j<2*size+1;j++) {
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
			if(randomNums.nextFloat()<branchingAmount) {
				current=stack.remove(randomNums.nextInt(stack.size()));
			}else {
				current=stack.remove(stack.size()-1);
			}
			
			int numUnvisited=0;
			for(int i=0;i<4;i++) {
				if(current[0]+adjY[i]>=0 && current[0]+adjY[i]<size && current[1]+adjX[i]>=0 && current[1]+adjX[i]<size) {
					if(!visited[current[0]+adjY[i]][current[1]+adjX[i]]) {
						numUnvisited++;
					}
				}
			}
			if(numUnvisited>0) {
				Integer[][] unvisitedNeighbors=new Integer[numUnvisited][2];
				int unvisitedLeft=numUnvisited;
				for(int i=0;i<4;i++) {
					if(current[0]+adjY[i]>=0 && current[0]+adjY[i]<size && current[1]+adjX[i]>=0 && current[1]+adjX[i]<size) {
						if(!visited[current[0]+adjY[i]][current[1]+adjX[i]]) {
							unvisitedNeighbors[unvisitedLeft-1][0]=current[0]+adjY[i];
							unvisitedNeighbors[unvisitedLeft-1][1]=current[1]+adjX[i];
							unvisitedLeft--;
						}
					}
				}
				Integer[] newCurrent=unvisitedNeighbors[randomNums.nextInt(numUnvisited)];
				System.out.println(1+maze[current[0]+newCurrent[0]][1+current[1]+newCurrent[1]]);
				maze[1+current[0]+newCurrent[0]][1+current[1]+newCurrent[1]]=' ';
				System.out.println(maze[1+current[0]+newCurrent[0]][1+current[1]+newCurrent[1]]);
				System.out.println("boo");
				stack.add(current);
				stack.add(newCurrent);
				visited[newCurrent[0]][newCurrent[1]]=true;
			}
		}
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				System.out.print((visited[i][j]?1:0));
			}
			System.out.println();
		}
		return maze;
	}
	
	public void drawMaze(char[][] maze) {
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[i].length;j++) {
				if(maze[i][j]=='#') {
					
				}
			}
		}
	}
	
	public static void main(String args[]) {
		MazeGenerator mazeMaker=new MazeGenerator();
		mazeMaker.generate(20);
	}
}
