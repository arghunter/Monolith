package render;


import javax.swing.*;

import GameObjects.Player.Player;
import general.AudioPlayer;
import general.PlayerManager;
import general.Playlist;
import general.SaveSystem;
import input.PlayerInputParser;
import ui.MainMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel implements ActionListener {
	static MainMenu menu;
	PlayerInputParser input;
	private SaveSystem save;
	private Player player;
	private PlayerManager manager;
	private Timer timer=new Timer(10,this);
	private double ratioX=1;
	private double ratioY=1;
	public static final double WIDTH= 2560.0;
	public static final double HEIGHT= 1377.0;
	public static GameStatus status=GameStatus.MAIN_MENU;
	public Main(){
		
		JFrame w = new JFrame("Monolith");
		w.setSize(1600,1000);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(this);
		w.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		w.setResizable(true);
		
		initInput(w,this);
		manager=new PlayerManager(this);
		try {
			save = new SaveSystem(this);
			player = save.loadSave();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Save system broken");
			player=new Player(300,300,64,64,this);
		}
		w.addWindowListener(save);
		manager.setPlayer(player,input);
		menu=new MainMenu(player,this,input);
		timer.start();
		w.setVisible(true);
		String[] songs={"Duel","DragonAwakened","Echoes","DragonCastle","EndlessStorm"};
		new Playlist(songs);
		
	}

	
	public void paintComponent(Graphics graphic) {
		Graphics2D g=(Graphics2D)graphic;
		super.paintComponent(g);
		ratioX = super.getWidth() / 2560.0;
		ratioY = super.getHeight() / 1377.0;
		input.setRatio(ratioX, ratioY);
		g.scale(ratioX, ratioY);
		menu.draw(g,getXOnScreen(), getYOnScreen());
		input.setGraphics(g);
		manager.draw(g, getXOnScreen(), getYOnScreen());
//		System.out.println(player.getInventory());
		
	}
	public int getXOnScreen() {
		return (int) this.getLocationOnScreen().getX();
	}

	public int getYOnScreen() {
		return (int) this.getLocationOnScreen().getY();
	}
	
	public void initInput(JFrame frame,Component component) {
		this.input = new PlayerInputParser(frame,component);
	}
	public static void setStatus(GameStatus status) 
	{
		if(Main.status==GameStatus.RUNNING&&status==GameStatus.MAIN_MENU) 
		{
			menu.backToMain();
		}
		Main.status=status;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		save.save(player);
		repaint();
	

		
		
	}

	public static void main(String args[]) {
		Main theGame = new Main();

	}
}
