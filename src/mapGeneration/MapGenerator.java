package mapGeneration;

import java.awt.*;
import javax.swing.*;
import general.ImageSystem;

public class MapGenerator extends JPanel {
	private ImageSystem image;
	
	public MapGenerator() {
		JFrame w = new JFrame("Maze");
		w.setSize(600,600);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(new MazeTester());
		w.setResizable(true);
		w.setVisible(true);
		this.image=new ImageSystem(0,0,new ImageIcon("StoneBig.png").getImage());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public static void main(String[] args) {
		
	}
}
