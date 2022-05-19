package render;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;

import general.AnimationSystem;
import general.Constants;
import general.ImageSystem;

public class TextureGenerator {
	private ImageSystem[][] imgs;
	private double scale=1;
	public TextureGenerator(String[][] room,long seed,int x,int y,double scale) 
	{
		int[][] roomCopy=new int[room.length+2][room[0].length+2];

		for(int i=0;i<room.length;i++) 
		{
			for(int j=0;j<room[0].length;j++) 
			{
				if(room[i][j]!=null) 
				{
					roomCopy[i+1][j+1]=Integer.parseInt(room[i][j]);

				}else 
				{
					roomCopy[i+1][j+1]=0;

				}
			}
			
		}
		this.scale=scale;
		roomCopy[0][0]=11;
		roomCopy[roomCopy.length-1][roomCopy[0].length-1]=11;
		roomCopy[0][roomCopy[0].length-1]=11;
		roomCopy[roomCopy.length-1][0]=11;
		for(int i=1;i<roomCopy.length-1;i++) 
		{
			roomCopy[i][0]=roomCopy[i][1];
			roomCopy[i][roomCopy[0].length-1]=roomCopy[i][roomCopy[0].length-2];
		}
		for(int i=1;i<roomCopy[0].length-1;i++) 
		{
			roomCopy[0][i]=roomCopy[1][i];
			roomCopy[roomCopy.length-1][i]=roomCopy[roomCopy.length-2][i];
		}
		imgs=new ImageSystem[room.length+2][room[0].length+2];
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
			if(contrast(i1,i2)>23) 
			{
				break;
				
			}else 
			{
				wall=rng.nextInt(5)+100;
				floor=rng.nextInt(21)+200;
			}
		}

		for(int i=0;i<roomCopy.length;i++) 
		{
			for(int j=0;j<roomCopy[0].length;j++) 
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
		
		
		for(int i=0;i<room.length+2;i++) 
		{
			for(int j=0;j<room[0].length+2;j++) 
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
				imgs[i][j]=new ImageSystem(x+((int)(j*32*(scale)))-(int)(16*scale),(y)+((int)(i*32*scale))-(int)(16*scale),new ImageIcon("imgs/Textures/" +type+ "/" +(roomCopy[i][j]%100) +".png").getImage());
				imgs[i][j].setScale(scale, scale);

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
					int r1 = (rgb1 >> 16)% 256 ;
                    int g1 = (rgb1 >> 8) % 256;
                    int b1 = (rgb1)% 256;
                    int r2 = (rgb2 >> 16) %256;
                    int g2 = (rgb2 >> 8) % 256;
                    int b2 = (rgb2)% 256;
                    diff+=Math.abs(r1-r2);
                    diff+=Math.abs(b2-b1);
                    diff+=Math.abs(g2-g1);
				}
			}
			return diff/(img1.getHeight()*img2.getWidth()*3);
		}
		
	}
	public static int calcHeight(double scale) 
	{
		return (int)(32*scale*(Constants.ROOMSIZEY+2));
	}
	public static int calcWidth(double scale) 
	{
		return (int)(32*scale*(Constants.ROOMSIZEX+2));
	}
	public void translate(int x,int y) 
	{
		for(int i=0;i<imgs.length;i++) 
		{
			for(int j=0;j<imgs[0].length;j++)
			{
				imgs[i][j].move((int)(x/scale), (int)(y/scale));
			}
		}
	}

}
