package GameObjects.elementalDamage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private Timer timer=new Timer(1000,this);
	public Damage(int damage,StatusEffect effect,double duration,Mob enemy,Player source) 
	{
		this.damage=damage;
		this.effect=effect;
		this.duration=duration;
		this.enemy=enemy;
		this.source=source;
		switch(effect) 
		{
		case FIRE:
			tickDamage=damage/3;
		}
		tick();
		timer.start();
		
		
		
	}
	
	public void tick() 
	{
		
		if(damage!=0) {
			start=System.currentTimeMillis();
			(enemy).takeDamage(source,damage);
			damage=0;
			if(effect==StatusEffect.FIRE) 
			{
				return;
			}
		}
		
		switch(effect) 
		{
		case FROST:
			enemy.setCurrentMovementDelay(2000);
			break;
		case FIRE:
			enemy.takeDamageIgnoreArmor(source,tickDamage);
			break;
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(System.currentTimeMillis()-start>duration*1000) 
		{
			timer.stop();
		}else 
		{
			tick();
		}
		
		
	}
	

}
