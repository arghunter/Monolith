package general;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import input.PlayerInputParser;
import render.GameStatus;
import render.Main;
import ui.SkillSelectionMenu;
import GameObjects.Player.Player;

public class PlayerManager implements ActionListener {
	private Player player;
	private PlayerInputParser input;
	private Timer timer=new Timer(3,this);
	private SkillSelectionMenu skillSelectionMenu;
	private JPanel panel;
	private Graphics2D g;
	private int skillsNeeded=0;
	public PlayerManager(JPanel panel) 
	{
		this.panel=panel;
	}
	public void setPlayer(Player p,PlayerInputParser input) 
	{
		player=p;
		this.input=input;
		timer=new Timer(3,this);
		timer.start();
	}
	public void draw(Graphics2D g,int JPanelX,int JPanelY) 
	{
		
		if(skillSelectionMenu!=null) 
		{
//			Main.status=GameStatus.PAUSED;
			skillSelectionMenu.render(g, JPanelX, JPanelY);
		}else if(skillsNeeded>0) 
		{
			g.setColor(new Color(200,200,200));
			
			g.drawString("Press Enter To Level Up ("+skillsNeeded+")", JPanelX, JPanelY);
			
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
		if (e.getSource()==player&&e.getActionCommand().equals("LevelUp")) {
			skillsNeeded++;
			
		}
		if(skillsNeeded>0) 
		{
			if(input.isEnterPressed()) 
			{
				if(skillsNeeded>0) 
				{
					if (skillSelectionMenu == null || !this.skillSelectionMenu.isActive()) {
						skillSelectionMenu = null;
						skillSelectionMenu = new SkillSelectionMenu(player.getSkills(),840, panel);
						skillsNeeded--;

					}
				}
			}
		}
		
		

		
	}

}
