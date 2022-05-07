package ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import GameObjects.Player.Player;
import general.Constants;

public class MainMenu implements ActionListener {
	Player player;
	Button[] menuButtons=new Button[3];//0=storageButton, 1=arsenalButton, 2==skillDisplayButton
	Button[] gameModeButtons=new Button[2];//0=adventureButton 1==survivalButton
	Button settingsMenu;
	InventoryMenu inventoryMenu;
	ArsenalMenu arsenalMenu;
	SkillDisplayMenu skillMenu;
	public MainMenu(Player player,JPanel panel) 
	{
		this.player=player;
		Point[] menuPoints= {new Point(0,266),new Point(0,466),new Point(400,266),new Point(400,466)};
		for(int i=0;i<menuButtons.length;i++) 
		{
			String text="";
			switch(i) 
			{
			case 0:
				text="Inventory";
				break;
			case 1:
				text="Arsenal";
				break;
			case 2: 
				text="Skills";
				
			}
			menuButtons[i]=new Button(menuPoints,new Color(0f,0f,0f,0f),text);
			menuButtons[i].setFontColor(Constants.textColor);
			for(int j=0;j<menuPoints.length;j++) 
			{
				menuPoints[j].y+=200;
			}
		}
		Point[] gamePoints= {new Point(2160,266),new Point(2160,466),new Point(2160,266),new Point(2160,466)};
		for(int i=0;i<gameModeButtons.length;i++) 
		{
			String text="";
			switch(i) 
			{
			case 0:
				text="Adventure";
				break;
			case 1:
				text="Survival";
			}
			gameModeButtons[i]=new Button(gamePoints,new Color(0f,0f,0f,0f),text);
			gameModeButtons[i].setFontColor(Constants.textColor);
			for(int j=0;j<gamePoints.length;j++) 
			{
				gamePoints[j].y+=200;
			}
		}
		inventoryMenu=new InventoryMenu(player.getInventory(),panel);
		arsenalMenu=new ArsenalMenu(player.getInventory(),panel);
		skillMenu=new SkillDisplayMenu(player.getSkills(),panel);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
