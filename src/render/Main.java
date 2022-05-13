package render;

import java.awt.Container;

import javax.swing.*;

import input.PlayerInputParser;

import java.awt.*;

public class Main extends JPanel {
	Adventure adventureMode;
	
	public Main(){
		adventureMode=new Adventure();
		JFrame w = new JFrame("Monolith");
		w.setSize(1600,1000);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);
		w.setResizable(true);
		w.setVisible(true);
	}
	
	public static void main(String args[]) {
		Main theGame = new Main();
	}
	
	public void paintComponent(Graphics graphic) {
		Graphics2D g=(Graphics2D)graphic;
		super.paintComponent(g);
		adventureMode.draw(g);
		
	}
	
	public void initInput(JFrame frame) {
		//this.input = new PlayerInputParser(frame,this);
	}
}
