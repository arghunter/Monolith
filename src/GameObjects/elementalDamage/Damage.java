package GameObjects.elementalDamage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
import GameObjects.mobs.Mob;

public class Damage implements ActionListener {
	private int damage;
	private StatusEffect effect;
	private double duration;
	private long start=System.currentTimeMillis();
	private Mob enemy;
	private Player source;
	private int tickDamage;
	private ArrayList<Mob> mobs;
	private Timer timer=new Timer(1000,this);
	private int mobDist;
	public Damage(int damage,StatusEffect effect,double duration,Mob enemy,Player source,ArrayList<Mob> mobs) 
	{
		this.damage=damage;
		this.effect=effect;
		this.duration=duration;
		this.enemy=enemy;
		this.source=source;
		this.mobs=mobs;
		mobDist=enemy.getDist();
		switch(effect) 
		{
		case FIRE:
			tickDamage=damage/3;
			break;
		case LIGHTNING:
			tickDamage=damage/4;
			break;
		case ROT:
			tickDamage=damage/2;
			break;
		case TOXIN:
			tickDamage=damage/5;
		}
		tick();
		timer.start();
		
		
		
	}
	
	public void tick() 
	{
		
		if(damage!=0) {
			start=System.currentTimeMillis();
			(enemy).takeDamage(source,damage);
			
			if(effect==StatusEffect.FIRE) 
			{
				damage=0;
				return;
			}else if(effect==StatusEffect.BLAST) 
			{
				enemy.setCoordsMove(enemy.getX()+(int)((Math.random()*300)-150), enemy.getY()+(int)((Math.random()*300)-150));
			}
			damage=0;
		}
		
		switch(effect) 
		{
		case FROST:
			enemy.setDist(0);
			break;
		case FIRE:
			enemy.takeDamageIgnoreArmor(source,tickDamage);
			break;
		case LIGHTNING:
			enemy.setCurrentMovementDelay(500);
			enemy.takeDamage(source,tickDamage);
			for(int i=0;i<mobs.size();i++) 
			{
				if((mobs.get(i)!=null)&&(mobs.get(i)!=enemy)) 
				{
					if(euclidDist(enemy.getX(), enemy.getY(), mobs.get(i).getX(), mobs.get(i).getY())<80&&tickDamage!=0) 
					{
						Damage dmg=new Damage(tickDamage,StatusEffect.LIGHTNING,duration/4,mobs.get(i),source,mobs);
					}
				}
			}
			break;
		case ROT:
			enemy.setDist(1);
			enemy.takeDamage(source,tickDamage);
			break;
		case TOXIN:
			enemy.takeDamage(source, tickDamage/2);
			enemy.takeDamageIgnoreArmor(source, tickDamage/2);
			break;

		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(System.currentTimeMillis()-start>duration*1000) 
		{
			timer.stop();
			enemy.setDist(mobDist);
		}else 
		{
			tick();
		}
		
		
	}
	//Distance calculations
	private static double euclidDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	

}
