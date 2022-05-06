package render;

import java.awt.Container;

import javax.swing.*;

public class Main extends JPanel {
	public Main(){
		JFrame w = new JFrame("Monolith");
		w.setSize(1600,1000);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);
		w.setResizable(true);
		w.setVisible(true);
		Adventure adventureMode=new Adventure();
	}
	
	public static void main(String args[]) {
		Main theGame = new Main();
	}
}
