package render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;

import general.AnimationSystem;
import general.ImageSystem;

public class TextureGenerator {
	ImageSystem[][] imgs;
	public TextureGenerator(String[][] room,long seed) 
	{
		int[][] roomCopy=new int[room.length][room[0].length];
		for(int i=0;i<room.length;i++) 
		{
			for(int j=0;j<room[0].length;j++) 
			{
				if(room[i][j]!=null) 
				{
					roomCopy[i][j]=Integer.parseInt(room[i][j]);

				}else 
				{
					roomCopy[i][j]=0;

				}
			}
		}
		imgs=new ImageSystem[room.length][room[0].length];
		Random rng=new Random(seed);
		int wall=rng.nextInt(5)+100;
		int floor=rng.nextInt(21)+200;
		int hazard=rng.nextInt(5)+300;
		while(true) 
		{
			
			BufferedImage i1=new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
			i1.createGraphics().drawImage(new ImageIcon("imgs/Textures/" +"Wall"+ "/" +(wall%100) +".png").getImage(), 0, 0,null);
			BufferedImage i2=new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
			i2.createGraphics().drawImage(new ImageIcon("imgs/Textures/" +"Floor"+ "/" +(floor%100) +".png").getImage(), 0, 0, null);
			System.out.println(contrast(i1,i2));
			if(contrast(i1,i2)>20) 
			{
				break;
				
			}else 
			{
				wall=rng.nextInt(5)+100;
				floor=rng.nextInt(21)+200;
			}
		}
		for(int i=0;i<room.length;i++) 
		{
			for(int j=0;j<room[0].length;j++) 
			{
				
				if(roomCopy[i][j]==11) 
				{
					floodFill(roomCopy,i,j,wall);
					
					
					
				}else if(roomCopy[i][j]==22) 
				{
					floodFill(roomCopy,i,j,hazard);
					hazard=rng.nextInt(5)+300;

				}else if(roomCopy[i][j]==0) 
				{
					floodFill(roomCopy,i,j,floor);
					
				}
				
			}
		}
		
		for(int i=0;i<room.length;i++) 
		{
			for(int j=0;j<room[0].length;j++) 
			{
				String type="";
				
				if(roomCopy[i][j]>=300) 
				{
					type="Hazard";
				}else if(roomCopy[i][j]>=200) 
				{
					type="Floor";
				}else if(roomCopy[i][j]>=100) 
				{
					type="Wall";
				}
//				System.out.println("imgs/Textures/" +type+ "/" +(roomCopy[i][j]%100) +".png");
				imgs[i][j]=new ImageSystem(2560/2-32*23+(j*32)+16,(int)Main.HEIGHT/2-32*18+(i*32)+16,new ImageIcon("imgs/Textures/" +type+ "/" +(roomCopy[i][j]%100) +".png").getImage());
//				System.out.println(imgs[i][j].getWidth());
			}
		}
		
		
	}

	public  void draw(Graphics2D g) {
		for(int i=0;i<imgs.length;i++) 
		{
			for(int j=0;j<imgs[0].length;j++) 
			{
				imgs[i][j].drawImage(g);
			}
		}

	}

	private static void floodFillRecurse(int array[][], int x, int y, int oldType, int newType) {
// Base cases
		if (x < 0 || x >= array.length || y < 0 || y >= array[0].length)
			return;
		if (array[x][y] != oldType)
			return;

		array[x][y] = newType;

		floodFillRecurse(array, x + 1, y, oldType, newType);
		floodFillRecurse(array, x - 1, y, oldType, newType);
		floodFillRecurse(array, x, y + 1, oldType, newType);
		floodFillRecurse(array, x, y - 1, oldType, newType);
	}

	private static void floodFill(int array[][], int x, int y, int newC) {
		int prevC = array[x][y];
		if (prevC == newC)
			return;
		floodFillRecurse(array, x, y, prevC, newC);
	}
	
	private static double contrast(BufferedImage img1,BufferedImage img2) 
	{
		if(img1.getHeight()!=img2.getHeight()&&img1.getWidth()!=img2.getWidth()) 
		{
			return -1;
		}else 
		{
			double diff=0;
			for(int i=0;i<img1.getWidth();i++) 
			{
				for(int j=0;j<img1.getHeight();j++) 
				{
					int rgb1=img1.getRGB(i, j);
					int rgb2=img2.getRGB(i, j);
					//Right shift
					int r1 = (rgb1 >> 16)& 0xff ;
                    int g1 = (rgb1 >> 8) & 0xff;
                    int b1 = (rgb1)& 0xff;
                    int r2 = (rgb2 >> 16) & 0xff;
                    int g2 = (rgb2 >> 8) & 0xff;
                    int b2 = (rgb2)& 0xff;
                    diff+=Math.abs(r1-r2);
                    diff+=Math.abs(b2-b1);
                    diff+=Math.abs(g2-g1);
				}
			}
			return diff/(img1.getHeight()*img2.getWidth()*3);
		}
		
	}

}
