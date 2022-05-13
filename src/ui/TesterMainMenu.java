//Author: Armaan Gomes 
//Date: 5/12/22
//Rev: 01
// Notes: Temporary tester for the main menu. Mostly just random stuff
package ui;

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

public class TesterMainMenu extends JPanel implements ActionListener {
	//Fields
	private Player thePlayer = new Player(300, 300, 64, 64, this, this);
	private PlayerInputParser input;
	private Timer clock = new Timer(30, this);
	private double ratioX = 1;
	private double ratioY = 1;
	private SaveSystem save;
	private MainMenu menu;
	public static final double WIDTH = 2560.0;
	public static final double HEIGHT = 1377.0;
	//Constructor
	public TesterMainMenu() {

		JFrame w = new JFrame("Tester");

		w.setSize(800, 500);
		try {
			save = new SaveSystem(this, this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Save system broken");
		}
		w.addWindowListener(save);
		thePlayer = save.loadSave();
		this.initInput(w);
		ratioX = super.getWidth() / WIDTH;
		ratioY = super.getHeight() / HEIGHT;

		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);

		menu = new MainMenu(thePlayer, this, input);

		w.setResizable(true);
		w.setVisible(true);

		clock.start();

	}


	//Gets the JPanel's location on Screen
	public int getXOnScreen() {
		return (int) this.getLocationOnScreen().getX();
	}
	//Gets the Jpanel's location on screen
	public int getYOnScreen() {
		return (int) this.getLocationOnScreen().getY();
	}
	//Draws everything
	public void paintComponent(Graphics graphics) {

		ratioX = super.getWidth() / 2560.0;
		ratioY = super.getHeight() / 1377.0;
		input.setRatio(ratioX, ratioY);
		Graphics2D g = (Graphics2D) graphics;

		super.paintComponent(g);
		save.save(thePlayer);
		g.scale(ratioX, ratioY);
		menu.draw(g, getXOnScreen(), getYOnScreen());
		input.updatePlayerAngle(thePlayer);

	}

	//Initializes input
	public void initInput(JFrame frame) {
		this.input = new PlayerInputParser(frame, this);
	}
	//Redraws the menu
	public void actionPerformed(ActionEvent e) {

		repaint();
	}
	//Init
	public static void main(String[] args) {

		TesterMainMenu gb = new TesterMainMenu();

	}

}