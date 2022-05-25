package GameObjects.Player.abilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import render.Adventure;

public class Miasma extends Ability {
	Point center;
	private long lastTick;
	public Miasma() {
		super(10, 1);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		int count=0;
		for (Mob m : Adventure.getMobs()) {
			if (Math.sqrt(Math.pow((m.getX() - Adventure.getPlayer().getX()), 2)
					+ Math.pow((m.getY() - Adventure.getPlayer().getY()), 2)) < 600) {
				new Damage(10, StatusEffect.TOXIN, 5, m, Adventure.getPlayer(), Adventure.getMobs());
				center=new Point(m.getX(),m.getY());
				count++;
			}
			if(count>=1) 
			{
				break;
			}
		}
		
		lastTick=System.currentTimeMillis()-1000;

		
	}

	@Override
	public void runAbility() {
		if(center!=null) 
		{
			long numTimes=(System.currentTimeMillis()-lastTick)/2000;
			for(long i=0;i<numTimes;i++) 
			{
				try 
				{
					for (Mob m : Adventure.getMobs()) {
						
						
						if (Math.sqrt(Math.pow((m.getX() - center.getX()), 2)
								+ Math.pow((m.getY() - center.getY()), 2)) < 200) {
							new Damage(40, StatusEffect.TOXIN, 5, m, Adventure.getPlayer(), Adventure.getMobs());
						}
					

				}
				}catch(Exception e) 
				{

				}

				lastTick=System.currentTimeMillis();
			}
		}
		
		
	}

	@Override
	public void end() {
		center=null;
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(super.isActive()&&center!=null) {
		
			 
			

			g.setColor(new Color(27,200,12,75));
			g.fillOval((int)center.getX()-200, (int)center.getY()-200, 400, 400);


			
		
		}
	}

}
