import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import GameObjects.*;
import GameObjects.Player.Player;


import java.util.ArrayList;
import java.util.LinkedList;
//import java.util.*;

public class Tester extends JPanel implements ActionListener {
	private Player thePlayer=new Player(300,300,1,64,64);
	private Spider theSpider=new Spider(600,600,2,64,64);
	private Spider secondSpider=new Spider(1000,1000,3,64,64);
	private Zombie theZombie=new Zombie(1200, 1200, 4, 64,64);
	private Mob[] mobList = new Mob[1000];
	private InputParser input;
	
	int numMobs=3;
	
	public Tester() {
		Timer clock=new Timer(1,this);
		clock.start();
		mobList[0]=theSpider;
		mobList[1]=secondSpider;
		mobList[2]=theZombie;
	}
	
	public void paintComponent(Graphics graphics)
	{
		setBackground(Color.WHITE);
		Graphics2D g=(Graphics2D)graphics;
		super.paintComponent(g);
		input.updatePlayerPosAndAngle(thePlayer);
		thePlayer.render(g);

		for(int i=0;i<numMobs;i++) {
			mobList[i].render(g);
			mobList[i].update(thePlayer.getX(),thePlayer.getY());
		}
	}
	public void initInput(JFrame frame) 
	{
		this.input=new InputParser(frame);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		for(int i=0;i<numMobs;i++) {
			int curX=mobList[i].getX();
			int curY=mobList[i].getY();
			int diffX=curX-thePlayer.getX();
			int diffY=curY-thePlayer.getY();
			Direction toMove=Direction.NORTH;
			if(1.5*Math.abs(diffX)<Math.abs(diffY)) {
				//Move up down
				if(diffY>0) {
					toMove=Direction.NORTH;
				}else {
					toMove=Direction.SOUTH;
				}
			}else if(1.5*Math.abs(diffY)<Math.abs(diffX)) {
				//Move left right
				if(diffX>0) {
					toMove=Direction.WEST;
				}else {
					toMove=Direction.EAST;
				}
			}else {
				//Move diagonal
				if(diffX>0 && diffY>0) {
					toMove=Direction.NORTHWEST;
				}else if(diffX<=0 && diffY>0) {
					toMove=Direction.NORTHEAST;
				}else if(diffX>0 && diffY<=0) {
					toMove=Direction.SOUTHWEST;
				}else if(diffX<=0 && diffY<=0) {
					toMove=Direction.SOUTHEAST;
				}
			}
			//System.out.println(mobList[i].getX()+" "+mobList[i].getY());
			mobList[i].move(toMove);
		}
		
		
		
		repaint();
	}
	
	
	
	
	
	public static void main(String[] args) {
	    JFrame w = new JFrame("Tester");
	    w.setSize(600, 600);
	    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container c = w.getContentPane();
	    Tester gb = new Tester();
		Point[] points={new Point(100,100),new Point(200,200)};
	    Button hi=new Button(points,(Graphics2D)gb.getGraphics());
	    gb.add(hi);
		gb.initInput(w);	    
	    c.add(gb);
	    w.setResizable(true);
	    w.setVisible(true);
	}

	
}