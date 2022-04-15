import java.util.LinkedList;
import java.util.Random;

public class MazeGenerator {
	private Random randomNums=new Random(5);
	
	public MazeGenerator() {
		
	}
	
	public void generate(int size) {
		int adjX[]={1,0,-1,0};
		int adjY[]={0,1,0,-1};
		
		LinkedList<Integer[]> stack=new LinkedList<Integer[]>();
		Integer[] start={0,0};
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
	}
}
