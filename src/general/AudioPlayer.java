package general;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer implements Runnable {
	private Thread thread;
	private String name;
	private Clip c;
	private int type;
	public static final int LOOPING=2;
	public static final int ONE_TIME=1;
	
	public AudioPlayer(String name,int type) 
	{
		this.name=name;
		
		start();
	}
	private void stop() 
	{
		
	}
	private void start() {
		if (thread == null) {
			thread = new Thread(this, "" + System.currentTimeMillis());
			thread.start();
		}
	}
	public  void playSound() {
	    try {
	        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("audio/"+name+".wav"));
	        c = AudioSystem.getClip();
	        System.out.println(audio.getFormat());
	        c.open(audio);
	        c.start();
	        
	    } catch(Exception ex) {
	        System.out.println("Are you sure the file is there");
	        ex.printStackTrace();
	    }
	}


	@Override
	public void run() {
		while(true) 
		{
			if(c==null||(!c.isActive()&&type==AudioPlayer.LOOPING)) 
			{
				playSound();
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
