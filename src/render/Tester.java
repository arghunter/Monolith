package render;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import GameObjects.*;
import GameObjects.Player.Player;
<<<<<<< Updated upstream
import GameObjects.Player.items.materials.Material;
=======
import GameObjects.Player.items.weapons.MeleeWeapon;
>>>>>>> Stashed changes
import GameObjects.mobs.Mob;
import GameObjects.mobs.Spider;
import GameObjects.mobs.Zombie;
import GameObjects.mobs.Balkrada;
import general.SaveSystem;
import input.InputParser;
import skills.SkillTreeRenderMode;
import ui.Button;
import ui.RenderableMenuItem;
import ui.SkillSelectionMenu;

import java.util.ArrayList;
import java.util.LinkedList;
//import java.util.*;

public class Tester extends JPanel implements ActionListener {
<<<<<<< Updated upstream
	private Player thePlayer = new Player(300, 300, 1, 64, 64);
	private Spider theSpider = new Spider(600, 600, 2, 64, 64);
	private Spider secondSpider = new Spider(1000, 1000, 3, 64, 64);
	private Zombie theZombie = new Zombie(1200, 1200, 4, 64, 64);
	private Mob[] mobList = new Mob[1000];
=======
	private Player thePlayer=new Player(300,300,1,64,64);
	private Spider theSpider=new Spider(600,600,2,64,64);
	private Spider secondSpider=new Spider(1000,1000,3,64,64);
	private Zombie theZombie=new Zombie(1200, 1200, 4, 64,64);
	private ArrayList<Mob> mobList = new ArrayList<>();
>>>>>>> Stashed changes
	private InputParser input;

	private JFrame frame;
	private SkillSelectionMenu skillSelectionMenu;
<<<<<<< Updated upstream
	private int numMobs = 3;
	private Timer clock = new Timer(10, this);
	private long lastSkillShown = System.currentTimeMillis();
	private long startTime = System.currentTimeMillis();
	private double ratioX = 1;
	private double ratioY = 1;
	private Button zombieButton;
	private SaveSystem save;
	private RenderableMenuItem item;

	public Tester() {

		mobList[0] = theSpider;
		mobList[1] = secondSpider;
		mobList[2] = theZombie;
		Material steel=new Material("Spider",100);
		item=new RenderableMenuItem(steel,900,800,this);
		Point[] points = { new Point(238, 108), new Point(162, 108), new Point(108, 162), new Point(108, 238),
				new Point(162, 292), new Point(238, 292), new Point(292, 238), new Point(292, 162) };

=======
	private int numMobs=3;
	private Timer clock=new Timer(10,this);
	private long lastSkillShown=System.currentTimeMillis();
	private long startTime=System.currentTimeMillis();
	private MeleeWeapon weapon = new MeleeWeapon();
	//private SaveSystem save;
	
	public Tester() {
		
		skillSelectionMenu=new SkillSelectionMenu(thePlayer.getSkills(),120,this);
		mobList.add(theSpider);
		mobList.add(secondSpider);
		mobList.add(theZombie);
		Point[] points={new Point(238,108),new Point(162,108),new Point(108,162),new Point(108,238),new Point(162,292),new Point(238,292),new Point(292,238),new Point(292,162)};
		hi=new Button(points,new Color(200,150,100),"Zombie");
		this.buttonSize(hi);
		this.setLayout(null);
		this.add(hi);
>>>>>>> Stashed changes
		JFrame w = new JFrame("Tester");

		w.setSize(800, 500);
		try {
			save = new SaveSystem();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Save system broken");
		}
		w.addWindowListener(save);
		thePlayer = save.loadSave();
		this.initInput(w);
		this.frame = w;
		ratioX = super.getWidth() / 2560.0;
		ratioY = super.getHeight() / 1377.0;

		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);
<<<<<<< Updated upstream

		w.setResizable(true);
		w.setVisible(true);

		zombieButton = new Button(points, new Color(200, 150, 100), "Zombie");
		this.add(zombieButton);
		zombieButton.addActionListener(this);



		clock.start();

=======
		this.initInput(w);
	    w.setResizable(true);
	    w.setVisible(true);
	    this.frame=w;
	    hi.addActionListener(this);
	    thePlayer.setMeleeWeapon(null);
//	    try {
//			this.save=new SaveSystem();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    w.addWindowListener(save);
//	    thePlayer = new Player(0,0,0,0,0);
//	    save.save(thePlayer);
//	    thePlayer=save.loadSave();
	    
	    clock.start();
		
		
>>>>>>> Stashed changes
	}

	public void buttonSize(Button button) {
		button.setBounds(button.getX(), button.getY(), (int) button.getPreferredSize().getWidth(),
				(int) button.getPreferredSize().getHeight());

	}

	public int getXOnScreen() {
		return (int) this.getLocationOnScreen().getX();
	}

	public int getYOnScreen() {
		return (int) this.getLocationOnScreen().getY();
	}

	public void paintComponent(Graphics graphics) {

		ratioX = super.getWidth() / 2560.0;
		ratioY = super.getHeight() / 1377.0;
		input.setRatio(ratioX, ratioY);
		Graphics2D g = (Graphics2D) graphics;

		super.paintComponent(g);
		save.save(thePlayer);
		
		g.scale(ratioX, ratioY);

		setBackground(Color.WHITE);
		
		if (skillSelectionMenu != null) {
			skillSelectionMenu.render(g, getXOnScreen(), getYOnScreen());
		}
		zombieButton.draw(g, this.getXOnScreen(), this.getYOnScreen());
		input.updatePlayerPosAndAngle(thePlayer);
		thePlayer.render(g);
<<<<<<< Updated upstream

		for (int i = 0; i < numMobs; i++) {
			mobList[i].render(g);
			mobList[i].update(thePlayer.getX(), thePlayer.getY());
=======
		if(input.getMBPressed(1)) {
			
		}
		for(int i=0;i<mobList.size();i++) {
			mobList.get(i).render(g);
			mobList.get(i).update(thePlayer.getX(),thePlayer.getY());
			if(mobList.get(i).) {
				
			}
>>>>>>> Stashed changes
		}
		item.draw(g, this.getXOnScreen(), this.getYOnScreen());
	}

	public void initInput(JFrame frame) {
		this.input = new InputParser(frame);
	}

	public void actionPerformed(ActionEvent e) {
<<<<<<< Updated upstream
		ratioX = super.getWidth() / 2560.0;
		ratioY = super.getHeight() / 1377.0;
		input.setRatio(ratioX, ratioY);

		if (zombieButton.isClicked(e)) {
			
			this.mobList[numMobs] = new Balkrada((int) (Math.random() * 2560), (int) (Math.random() * 1377), 4, 64, 64);
=======
		
		if(hi.isClicked(e)) 
		{
			mobList.add(new Zombie((int)(Math.random()*1500),(int)(Math.random()*1500), 4, 64,64));
>>>>>>> Stashed changes
			numMobs++;

		}
		if (this.skillSelectionMenu != null && this.skillSelectionMenu.isActive()) {
			lastSkillShown = System.currentTimeMillis();
		}


<<<<<<< Updated upstream
		if (System.currentTimeMillis() - lastSkillShown > 1200) {
			if (skillSelectionMenu == null || !this.skillSelectionMenu.isActive()) {
				skillSelectionMenu = null;
				skillSelectionMenu = new SkillSelectionMenu(thePlayer.getSkills(),
						(int) (System.currentTimeMillis() - startTime) / 10, this);

=======
		
		for(int i=0;i<mobList.size();i++) {
			int curX=mobList.get(i).getX();
			int curY=mobList.get(i).getY();
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
			if(diffX*diffX+diffY*diffY>=64*64) {
				mobList.get(i).move(toMove);
>>>>>>> Stashed changes
			}
			lastSkillShown = System.currentTimeMillis();
		}
		for (int i = 0; i < numMobs; i++) {
			mobList[i].action(thePlayer.getX(), thePlayer.getY());
		}

		repaint();
	}

	public static void main(String[] args) {

		Tester gb = new Tester();

	}

}