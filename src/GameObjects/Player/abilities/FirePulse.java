package GameObjects.Player.abilities;

import java.awt.Color;
import java.awt.Graphics2D;

import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import render.Adventure;

public class FirePulse extends Ability {
	private long lastTick;
	public FirePulse() {
		super(6, 8);
	}

	@Override
	public void init() {
		lastTick=System.currentTimeMillis()-1000;
	}

	@Override
	public void runAbility() {
		long numTimes=(System.currentTimeMillis()-lastTick)/2000;
		
		for(long i=0;i<numTimes;i++) 
		{
			
			for (Mob m : Adventure.getMobs()) {
				if (Math.sqrt(Math.pow((m.getX() - Adventure.getPlayer().getX()), 2)
						+ Math.pow((m.getY() - Adventure.getPlayer().getY()), 2)) < 300) {
					new Damage(40, StatusEffect.BLAST, 5, m, Adventure.getPlayer(), Adventure.getMobs());
					new Damage(20, StatusEffect.FIRE, 5, m, Adventure.getPlayer(), Adventure.getMobs());

				}
			}
			lastTick=System.currentTimeMillis();
		}
		
	}

	@Override
	public void end() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(super.isActive()) 
		{
			try 
			{
				g.setColor(new Color((int)(226*(System.currentTimeMillis()-lastTick)/1000.0),(int)( 88*(System.currentTimeMillis()-lastTick)/1000.0), (int)(34*(System.currentTimeMillis()-lastTick)/1000.0),(int)( 75*(System.currentTimeMillis()-lastTick)/1000.0)));

				g.fillOval(Adventure.getPlayer().getX()-300, Adventure.getPlayer().getY()-300, 600, 600);

			}catch(Exception e)
			{
				
			}
		}


		
	}

}
