package general;

import java.util.Random;

public class Playlist implements Runnable {
	private Thread thread;
	private String[] songs;
	private AudioPlayer audio;
	private Random rng=new Random();
	public Playlist(String[] songNames) 
	{
		this.songs=songNames;
		start();
	}
	public void start() 
	{
		if (thread == null) {
	         thread = new Thread (this, ""+System.currentTimeMillis());
	         thread.start ();
	      }
	}
	@Override
	public void run() {
		while(true) 
		{
			if(audio==null||!audio.getClip().isActive()) 
			{
				int i=rng.nextInt(songs.length);
				audio=new AudioPlayer(songs[i],AudioPlayer.ONE_TIME);
			}
			try {
				thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
