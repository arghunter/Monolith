//Author: Armaan Gomes
//Date: 5/20/22
//Rev: 01
//Notes: A system that plays audio
package general;

import java.io.File; 

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer implements Runnable {
	//Fields
	private Thread thread;
	private String name;
	private Clip c;
	private int type;
	public static final int LOOPING=2;
	public static final int ONE_TIME=1;
	//Constructor
	public AudioPlayer(String name,int type) 
	{
		this.name=name;
		
		start();
	}
	//Returns the audio source of this audio player
	public Clip getClip() 
	{
		return c; 
	}
	//Starts this audio player
	private void start() {
		if (thread == null) {
			thread = new Thread(this, "" + System.currentTimeMillis());
			thread.start();
		}
	}
	//Plays a sound
	public void playSound() {
	    try {
	        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("audio/"+name+".wav"));
	        c = AudioSystem.getClip();
	        c.open(audio);
	        c.start();
	        
	    } catch(Exception ex) {
	        System.out.println("Are you sure the file is there");
	        ex.printStackTrace();
	    }
	}


	@Override
	//Runs the looping
	public void run() {
		while(true) 
		{
			if(c==null||(!c.isActive()&&type==AudioPlayer.LOOPING)) 
			{
				playSound();
			}
			if(type==AudioPlayer.ONE_TIME) 
			{
				break;
			}
			try {
				thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	

}
