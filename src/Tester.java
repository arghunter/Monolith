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
	int numMobs=3;
	
	public Tester() {
		Timer clock=new Timer(1,this);
		clock.start();
		mobList[0]=theSpider;
		mobList[1]=secondSpider;
		mobList[2]=theZombie;
	}
	
	public void paintComponent(Graphics g)
	{
		setBackground(Color.WHITE);
		super.paintComponent(g);
		thePlayer.update(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
		thePlayer.render(g);

		for(int i=0;i<numMobs;i++) {
			mobList[i].render(g);
			mobList[i].update(thePlayer.getX(),thePlayer.getY());
		}
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
			System.out.println(mobList[i].getX()+" "+mobList[i].getY());
			mobList[i].move(toMove);
		}
		
		
		int leftComp=(leftPressed?1:0);
		int rightComp=(rightPressed?1:0);
		int upComp=(upPressed?1:0);
		int downComp=(downPressed?1:0);
		if(leftComp-rightComp==0) {
			if(upComp-downComp==1) {
				thePlayer.move(Direction.NORTH);
			}else if(upComp-downComp==-1) {
				thePlayer.move(Direction.SOUTH);
			}else {
				//Don't move
			}
		}else if(leftComp-rightComp==1) {
			if(upComp-downComp==1) {
				thePlayer.move(Direction.NORTHWEST);
			}else if(upComp-downComp==-1) {
				thePlayer.move(Direction.SOUTHWEST);
			}else {
				thePlayer.move(Direction.WEST);
			}
		}else if(leftComp-rightComp==-1) {
			if(upComp-downComp==1) {
				thePlayer.move(Direction.NORTHEAST);
			}else if(upComp-downComp==-1) {
				thePlayer.move(Direction.SOUTHEAST);
			}else {
				thePlayer.move(Direction.EAST);
			}
		}
		repaint();
	}
	
	boolean leftPressed = false;
	boolean rightPressed = false;
	boolean upPressed = false;
	boolean downPressed = false;
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			leftPressed=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rightPressed=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			upPressed=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			downPressed=true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			leftPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rightPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			upPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			downPressed=false;
		}
	}
	
	
	public static void main(String[] args) {
	    JFrame w = new JFrame("Tester");
	    w.setSize(600, 600);
	    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container c = w.getContentPane();
	    Tester gb = new Tester();
	    MouseInput mouse=new MouseInput(w);
	   
	    
	    w.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				//ignore
			}

			@Override
			public void keyPressed(KeyEvent e) {
				gb.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				gb.keyReleased(e);
			}
	    	
	    });
	    c.add(gb);
	    w.setResizable(true);
	    w.setVisible(true);
	}

	
}