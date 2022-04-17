package mapGeneration;
import java.util.LinkedList;
import java.util.Random;

public class MazeGenerator {
	private Random randomNums=new Random(5);
	
	public MazeGenerator() {
		
	}
	
	public void generate(int size) {
		//Right, Down, Left, Up
		int adjX[]={1,0,-1,0};
		int adjY[]={0,1,0,-1};
		
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
		
		while(stack.size()>0) {
			Integer[] current=stack.remove(randomNums.nextInt(stack.size()));
			
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
				maze[current[0]+newCurrent[0]][current[1]+newCurrent[1]]=' ';
				stack.add(current);
				stack.add(newCurrent);
				visited[newCurrent[0]][newCurrent[1]]=true;
			}
		}
		for(int i=0;i<2*size+1;i++) {
			for(int j=0;j<2*size+1;j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String args[]) {
		MazeGenerator mazeMaker=new MazeGenerator();
		mazeMaker.generate(20);
	}
}
