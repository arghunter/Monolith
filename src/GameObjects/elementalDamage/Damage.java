package GameObjects.elementalDamage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import GameObjects.MovingObject;
import GameObjects.Player.Player;
import GameObjects.mobs.Mob;

public class Damage implements Runnable {
	private int damage;
	private StatusEffect effect;
	private double duration;
	private long start=System.currentTimeMillis();
	private Mob enemy;
	private Player source;
	private int tickDamage;
	private ArrayList<Mob> mobs;
//	private Timer timer=new Timer(1000,this);
	Thread thread;
	private int mobDist;
	private int mobArmor;
	private int chain=0;
	public Damage(int damage,StatusEffect effect,double duration,Mob enemy,Player source,ArrayList<Mob> mobs) 
	{
		this.damage=damage;
		this.effect=effect;
		this.duration=duration;
		this.enemy=enemy;
		this.source=source;
		this.mobs=mobs;

		mobDist=enemy.getDist();
		mobArmor=enemy.getStat(3);
		if(effect!=StatusEffect.NONE)
		enemy.getImage().setStatus(effect, duration);
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
			break;
		case CORROSION:
			tickDamage=damage/10;
			break;
		case GAS:
			tickDamage=damage/3;
		case VIRAL:
			tickDamage=3*damage/4;
		}
		tick();
		start();
//		timer.start();
		
		
		
	}
	private Damage(int damage,StatusEffect effect,double duration,Mob enemy,Player source,ArrayList<Mob> mobs,int chain) 
	{
		this.damage=damage;
		this.effect=effect;
		this.duration=duration;
		this.enemy=enemy;
		this.source=source;
		this.mobs=mobs;
		mobDist=enemy.getDist();
		mobArmor=enemy.getStat(3);
		enemy.getImage().setStatus(effect, duration);
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
			break;
		case CORROSION:
			tickDamage=damage/10;
			break;
		case GAS:
			tickDamage=damage/3;
		case VIRAL:
			tickDamage=3*damage/4;
		}
		tick();
//		timer.start();
		start();
		this.chain=chain;
		
		
		
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
				int x=enemy.getX()+(int)((Math.random()*50*damage/8)-25*damage/8);
				int y= enemy.getY()+(int)((Math.random()*50*damage/8)-25*damage/8);
				while(euclidDist(enemy.getX(),enemy.getY(),source.getX(),source.getY())>euclidDist(x,y,source.getX(),source.getY())) 
				{
					x=enemy.getX()+(int)((Math.random()*50*damage/8)-25*damage/8);
					y= enemy.getY()+(int)((Math.random()*50*damage/8)-25*damage/8);
				}
				enemy.setCoordsMove(x,y);
			}else if(effect==StatusEffect.VIRAL) 
			{

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
					if(chain<5&&euclidDist(enemy.getX(), enemy.getY(), mobs.get(i).getX(), mobs.get(i).getY())<80&&tickDamage!=0) 
					{
						Damage dmg=new Damage(tickDamage/2,StatusEffect.LIGHTNING,duration/4,mobs.get(i),source,mobs,chain+1);
					}
				}
			}
			break;
		case ROT:
			enemy.setDist(Math.min(mobDist, 1));
			enemy.takeDamage(source,tickDamage);
			break;
		case TOXIN:
			enemy.takeDamage(source, tickDamage/2);
			enemy.takeDamageIgnoreArmor(source, tickDamage/2);
			break;
		case CORROSION:
			enemy.setStat(3, enemy.getStat(3)/4);
			enemy.takeDamage(source, damage);
			break;
		case GAS:
			enemy.takeDamage(source,tickDamage);
			enemy.setDist(0);
			chain=0;
			for(int i=0;i<mobs.size();i++) 
			{
				if((mobs.get(i)!=null)&&(mobs.get(i)!=enemy)) 
				{
					if(euclidDist(enemy.getX(), enemy.getY(), mobs.get(i).getX(), mobs.get(i).getY())<200&&tickDamage!=0) 
					{
						Damage dmg=new Damage(tickDamage,StatusEffect.NONE,0,mobs.get(i),source,mobs);
						chain++;
					}
					if(chain>20) 
					{
						break;
					}
				}
			}
			break;
		case VIRAL:
			enemy.takeDamage(source,tickDamage);
			for(int i=0;i<mobs.size();i++) 
			{
				if((mobs.get(i)!=null)&&(mobs.get(i)!=enemy)) 
				{
					if(chain<3&&euclidDist(enemy.getX(), enemy.getY(), mobs.get(i).getX(), mobs.get(i).getY())<200&&tickDamage>=0&&Math.random()*4<(1-(double)euclidDist(enemy.getX(), enemy.getY(), mobs.get(i).getX(), mobs.get(i).getY())/200)) 
					{
						Damage dmg=new Damage((int)(tickDamage-euclidDist(enemy.getX(),enemy.getY(),source.getX(),source.getY())/50),Math.random()<0.8?StatusEffect.VIRAL:StatusEffect.NONE,duration,mobs.get(i),source,mobs,chain+1);
						break;
					}
				}
			}

		}
		
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
		for(int i=0;i<=duration;i++) 
		{
			try {
				tick();
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
		enemy.setDist(mobDist);
		enemy.setStat(3, mobArmor);

		
	}


	//Distance calculations
	private static double euclidDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}

	

}
