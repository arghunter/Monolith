package render;

import java.awt.*;  

import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import GameObjects.*;
import GameObjects.Player.Player;
import GameObjects.Player.items.materials.Material;
import GameObjects.mobs.Mob;
import GameObjects.mobs.Spider;
import GameObjects.mobs.Zombie;
import GameObjects.mobs.Balkrada;
import general.SaveSystem;
import input.PlayerInputParser;

import ui.Button;
import ui.RenderableMenuItem;
import ui.SkillSelectionMenu;

import java.util.ArrayList;
import java.util.LinkedList;
//import java.util.*;

public class Tester extends JPanel implements ActionListener {
	private Player thePlayer = new Player(300, 300, 1, 64, 64,this,this);
	private Spider theSpider = new Spider(600, 600, 2, 64, 64);
	private Spider secondSpider = new Spider(1000, 1000, 3, 64, 64);
	private Zombie theZombie = new Zombie(1200, 1200, 4, 64, 64);
	private Mob[] mobList = new Mob[1000];
	private PlayerInputParser input;

	private JFrame frame;
	private SkillSelectionMenu skillSelectionMenu;
	private int numMobs = 3;
	private Timer clock = new Timer(10, this);
	private double ratioX = 1;
	private double ratioY = 1;
	private Button zombieButton;
	private SaveSystem save;
	private RenderableMenuItem item;
	public static final double WIDTH=2560.0;
	public static final double HEIGHT=1377.0;

	public Tester() {

		mobList[0] = theSpider;
		mobList[1] = secondSpider;
		mobList[2] = theZombie;
		Point[] points = { new Point(238, 108), new Point(162, 108), new Point(108, 162), new Point(108, 238),
				new Point(162, 292), new Point(238, 292), new Point(292, 238), new Point(292, 162) };

		JFrame w = new JFrame("Tester");

		w.setSize(800, 500);
		try {
			save = new SaveSystem(this,this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Save system broken");
		}
		w.addWindowListener(save);
		thePlayer = save.loadSave();
		
		this.frame = w;
		ratioX = super.getWidth() / WIDTH;
		ratioY = super.getHeight() / HEIGHT;

		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);
		this.initInput(w,this);

		w.setResizable(true);
		w.setVisible(true);

		zombieButton = new Button(points, new Color(200, 150, 100), "Zombie");
		this.add(zombieButton);
		zombieButton.addActionListener(this);



		clock.start();

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
		//zombieButton.draw(g, this.getXOnScreen(), this.getYOnScreen());
		input.updatePlayer(thePlayer);
		input.updateMobs(mobList);
		thePlayer.render(g);

		for (int i = 0; i < numMobs; i++) {
			if(!mobList[i].isDead()) {
				mobList[i].render(g);
			}
			mobList[i].update(thePlayer.getX(), thePlayer.getY());
		}
	}

	public void initInput(JFrame frame,Component component) {
		this.input = new PlayerInputParser(frame,component);
	}

	public void actionPerformed(ActionEvent e) {
		ratioX = super.getWidth() / 2560.0;
		ratioY = super.getHeight() / 1377.0;
		input.setRatio(ratioX, ratioY);
		thePlayer.addXP(1);
		if (zombieButton.isClicked(e)) {
			
			this.mobList[numMobs] = new Balkrada((int) (Math.random() * 2560), (int) (Math.random() * 1377), 4, 64, 64);
			numMobs++;

		}



		if (e.getSource()==thePlayer&&e.getActionCommand().equals("LevelUp")) {
			if (skillSelectionMenu == null || !this.skillSelectionMenu.isActive()) {
				skillSelectionMenu = null;
				skillSelectionMenu = new SkillSelectionMenu(thePlayer.getSkills(),
						840, this);

			}
			
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