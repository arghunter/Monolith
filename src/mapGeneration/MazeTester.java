package mapGeneration;

import java.awt.*;
import javax.swing.*;

public class MazeTester extends JPanel{
	
	private static char[][] maze;
	
	public MazeTester() {
		
	}
	
	public void printMaze(char[][] maze) {
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[i].length;j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze.length;j++) {
				if(maze[i][j]=='#') {
					g.fillRect(10*j,10*i,15,15);
				}
			}
		}
	}
	
	public static void generateMaze(int size) {
		MazeGenerator generator=new MazeGenerator();
		maze=generator.generate(size);
	}
	
	public static void main(String[] args) {
		
		generateMaze(20);
		JFrame w = new JFrame("Maze");
		w.setSize(600,600);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(new MazeTester());
		w.setResizable(true);
		w.setVisible(true);
	}
}
