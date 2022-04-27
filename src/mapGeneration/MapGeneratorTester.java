package mapGeneration;

import java.awt.*;

import javax.swing.*;

import general.ImageSystem;

public class MapGeneratorTester extends JPanel {
	private ImageSystem image=new ImageSystem(0,0,new ImageIcon("StoneBig.png").getImage());
	
	public MapGeneratorTester() { 

	}	
	
	public static void main(String[] args) {
		JFrame w = new JFrame("Maze");
		w.setSize(600,600);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(new MapGeneratorTester());
		w.setResizable(true);
		w.setVisible(true);
	}
}
