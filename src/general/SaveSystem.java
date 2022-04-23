package general;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import GameObjects.Player.Player;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
public class SaveSystem implements WindowListener {
	String save="";
	Player savedPlayer;
	public void save(Player player) 
	{
		try {
			FileOutputStream out = new FileOutputStream("save.txt");
			ObjectOutputStream output = new ObjectOutputStream(out);
			output.writeObject(player);
			output.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public Player loadSave() {
		try {
			FileInputStream in = new FileInputStream("save.txt");
			ObjectInputStream input = new ObjectInputStream(in);
			Player player = (Player)input.readObject();
			input.close();
			in.close();
			return player;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		loadSave();
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
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
