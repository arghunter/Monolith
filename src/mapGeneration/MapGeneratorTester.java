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
		g.fillRect(0,0,16*44,16*36);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 32, 32);
	}
	
	public static void main(String[] args) {
		MapGenerator op=new MapGenerator();
		MapGeneratorTester gb = new MapGeneratorTester();
	}
}
