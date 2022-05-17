package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import GameObjects.Player.Player;
import general.Constants;
import input.PlayerInputParser;
import render.GameStatus;
import render.Main;

public class PauseMenu implements ActionListener {
	
	private Button resume;
	private boolean hidden=false;
	Player player;
	PlayerInputParser input;
	public PauseMenu(Player player,JPanel panel,PlayerInputParser input) 
	{
		Point[] menuPoints = { new Point(0, 66), new Point(0, 266), new Point(400, 266), new Point(400, 66) };
		resume= new Button(menuPoints, new Color((212) / 2, (175) / 2, (55) / 2, 0), "Resume");
		resume.setFontColor(Constants.TEXTCOLOR);
		panel.add(resume);
		resume.addActionListener(this);
		this.player=player;
		this.input=input;

	}
	public void draw(Graphics2D g,int JPanelX,int JPanelY) 
	{
		if(!hidden) 
		{
			resume.draw(g, JPanelX, JPanelY);
			
			if(input.isEscapePressed()) 
			{
				this.hidden=true;
				Main.status=GameStatus.RUNNING;
			}
			
		}
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
		if(!hidden) 
		{
			Main.status=GameStatus.PAUSED;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	}
	

}
