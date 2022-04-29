package mapGeneration;

import java.awt.*;

import javax.swing.*;

//import general.ImageSystem;

public class MapGeneratorTester extends JPanel {

	//private ImageSystem image=new ImageSystem(0,0,new ImageIcon("StoneBig.png").getImage());
	
	public MapGeneratorTester() { 
		JFrame w = new JFrame("Maze");
		w.setSize(600,600);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);
		w.setResizable(true);
		w.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,576,448);
	}
	
	public static void main(String[] args) {
		MapGeneratorTester gb = new MapGeneratorTester();
	}
}
