package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import input.PlayerInputParser;
import render.GameStatus;
import render.Main;
import GameObjects.Player.Player;

public class PlayerManager implements ActionListener {
	private Player player;
	private PlayerInputParser input;
	private Timer timer=new Timer(3,this);
	public PlayerManager() 
	{
		
	}
	public void setPlayer(Player p,PlayerInputParser input) 
	{
		player=p;
		this.input=input;
		timer=new Timer(3,this);
		timer.start();
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

		
	}

}
