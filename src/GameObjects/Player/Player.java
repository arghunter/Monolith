package GameObjects.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import GameObjects.ImageSystem;
import GameObjects.MovingObject;
import skills.*;

public class Player extends MovingObject {
	private SkillTree skills;
	private static StatType[] statTypes= {StatType.ACCURACY,StatType.ARMOR,StatType.ATTACKSPEED,StatType.HEALTH,StatType.POWER,StatType.REGEN,StatType.SHIELD,StatType.SPEED,StatType.STRENGTH};
	private static int[] baseStats= {10,25,60,100,10,1,100,15,10};
	
	
	
	//Note the speed will come from skill tree
	public Player(int x, int y, int id,int width,int height) {
		//Just going to use the helmet image for player
		super(x, y,baseStats[7],id,width,height,"DefaultHelmet.gif");
		skills=new SkillTree(baseStats,statTypes);
		
		
	}
	public void update(double mouseX, double mouseY) 
	{
		double angle=Math.atan(Math.abs((mouseY-super.getY())/(double)(mouseY-super.getX())));
		super.getImage().setRotation(angle);
		
	}

	@Override
	public void render(Graphics g) {
		//super.refillLastPos(g);
		super.getImage().drawImage(g);
		
	} 

	


	

}
