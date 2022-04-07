import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import GameObjects.Direction;
import GameObjects.Player;

import java.util.ArrayList;
import java.util.LinkedList;
//import java.util.*;

public class Tester extends JPanel implements ActionListener {
	private Player thePlayer=new Player(100,100,1,20,20);

	
	public Tester() {
		Timer clock=new Timer(1,this);
		clock.start();
	}
	
	public void paintComponent(Graphics g)
	{
		setBackground(Color.WHITE);
		super.paintComponent(g);
		thePlayer.render(g);

	}
	
	public void actionPerformed(ActionEvent e) {

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