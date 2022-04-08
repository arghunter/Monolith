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
	private int[] stats= {10,25,60,100,10,1,100,15,10};
	private int currentHealth;
	private int currentShields;
	private boolean isDead=false;;
	
	
	//Note the speed will come from skill tree
	public Player(int x, int y, int id,int width,int height) {
		//Just going to use the helmet image for player
		super(x, y,15,id,width,height,"DefaultHelmet.png");
		skills=new SkillTree(stats,statTypes);
		currentHealth=stats[3];
		currentShields=stats[6];
		
		
	}
	public void update(double mouseX, double mouseY) 
	{
		double angle=Math.atan((double)((mouseY-super.getY())/(double)(mouseX-super.getX())));
		if(mouseX<super.getX()) {
			angle+=Math.PI;
		}
		super.getImage().setRotation(angle);
		
	}
	//alters the players health and shield values based on damage dealt and armor
	public void takeDamage(int damage) 
	{
		damage=(int)((2*Math.log(stats[1]*Math.log(stats[1])))+0.5);
		if(currentShields>0) 
		{
			currentShields-=damage;
		}else 
		{
			currentHealth-=damage;
		}
		if(currentHealth<=0) 
		{
			isDead=true;
		}
	}

	public SkillTree getSkills() {
		return skills;
	}
	public static StatType[] getStatTypes() {
		return statTypes;
	}
	public int[] getStats() {
		return stats;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public int getCurrentShields() {
		return currentShields;
	}
	public boolean isDead() {
		return isDead;
	}
	@Override
	public void render(Graphics g) {
		//super.refillLastPos(g);
		super.getImage().drawImage(g);
		
	} 

	


	

}
