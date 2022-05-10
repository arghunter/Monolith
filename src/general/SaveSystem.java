//Author: Armaan Gomes
//Date: 5/9/22
//Rev: 02
//Notes: A save system for restoring the player after shutdown
package general;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JPanel;

import GameObjects.Player.Player;

public class SaveSystem implements WindowListener {
	//Fields
	FileInput input;
	PrintWriter output;
	String save="";
	Player savedPlayer;
	//Constructor
	public SaveSystem(ActionListener game,JPanel panel) throws FileNotFoundException 
	{
		
		input = new FileInput("save.txt");
		try {
			savedPlayer=new Player(300,300,1,64,64,game,panel,input.next());
		}catch( Exception e) 
		{
			savedPlayer=new Player(300,300,1,64,64,game,panel);
		}
		
		output=new PrintWriter("save.txt");
		
	}
	//Saves the current player data
	public void save(Player player) 
	{
		
		save=player.toString();

	}
	//Writes the save to a file
	public void writeSave() 
	{
		output.println(save);
	}
	//Loads the save
	public Player loadSave() {
		
		return savedPlayer;
	}

	@Override

	public void windowOpened(WindowEvent e) {
		
		
	}

	@Override
	//Writes the save when the window is closing
	public void windowClosing(WindowEvent e) {

		writeSave();
		output.close();
		
	
	}

	@Override
	public void windowClosed(WindowEvent e) {

		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
