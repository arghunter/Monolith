package general;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import GameObjects.Player.Player;
import input.PlayerInputParser;
import render.GameStatus;
import render.Main;
import ui.SkillSelectionMenu;

public class PlayerManager implements ActionListener {
	private Player player;
	private PlayerInputParser input;
	private Timer timer=new Timer(10,this);
	private SkillSelectionMenu skillSelectionMenu;
	private JPanel panel;
	private Graphics2D g;
	private int skillsNeeded=0;
	private Timer colorChanger=new Timer(400,this);
	private Color textColor=new Color(200,200,200);
	public PlayerManager(JPanel panel) 
	{
		this.panel=panel;
	}
	public void setPlayer(Player p,PlayerInputParser input) 
	{
		player=p;
		this.input=input;
		timer.start();
		colorChanger.start();
	}
	public void draw(Graphics2D g,int JPanelX,int JPanelY) 
	{
		if(skillSelectionMenu!=null&&skillSelectionMenu.isActive()) 
		{
			skillSelectionMenu.render(g,JPanelX,JPanelY);
		}else if(player.getSkillsNeeded()>0) 
		{
			g.setColor(textColor);
			Font text=null;
			try {
				text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Exo_2/static/Exo2-Medium.ttf"));
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			g.setFont(text.deriveFont(30f));
			g.drawString("Press Enter To Learn New Skills("+player.getSkillsNeeded()+")", JPanelX, JPanelY);
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(input!=null) 
		{
			if(Main.status==GameStatus.RUNNING) 
			{
				input.updatePlayer(player);
			}else if(Main.status==GameStatus.MAIN_MENU) 
			{
				input.updatePlayerAngle(player);
			}
		}

		if(player.getSkillsNeeded()>0) 
		{
			if(input.isEnterPressed()) 
			{
				if(player.getSkillsNeeded()>0) 
				{
					if (skillSelectionMenu == null || !this.skillSelectionMenu.isActive()) {
						skillSelectionMenu = null;
						skillSelectionMenu = new SkillSelectionMenu(player.getSkills(),840, panel);
						player.levelUP();

					}
				}
			}
		}
		if(e.getSource()==colorChanger)
		{
			Random rng=new Random();

			textColor=new Color(rng.nextInt(256),rng.nextInt(256),rng.nextInt(256));
		}
		
		

		
	}

}
