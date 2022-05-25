//Author: Armaan Gomes   
//Date: 5/20/22
//Rev: 01
//Notes:
package GameObjects.Player.abilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import render.Adventure;

public class MeteorShower extends Ability {
	private ArrayList<Point> firePuddles=new ArrayList<Point>();
	private long lastTick;

	public MeteorShower() {
		super(5, 40);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		int count=0;
		for (Mob m : Adventure.getMobs()) {
			if (Math.sqrt(Math.pow((m.getX() - Adventure.getPlayer().getX()), 2)
					+ Math.pow((m.getY() - Adventure.getPlayer().getY()), 2)) < 600) {
				new Damage(100, StatusEffect.FIRE, 5, m, Adventure.getPlayer(), Adventure.getMobs());
				firePuddles.add(new Point(m.getX(),m.getY()));
				count++;
			}
			if(count>=15) 
			{
				break;
			}
		}
		
		lastTick=System.currentTimeMillis()-1000;

		
	}

	@Override
	public void runAbility() {
		long numTimes=(System.currentTimeMillis()-lastTick)/500;
		for(long i=0;i<numTimes;i++) 
		{
			try 
			{
				for (Mob m : Adventure.getMobs()) {
					for(Point p:firePuddles) 
					{
						if (Math.sqrt(Math.pow((m.getX() - p.getX()), 2)
								+ Math.pow((m.getY() - p.getY()), 2)) < 100) {
							new Damage(50, StatusEffect.NONE, 5, m, Adventure.getPlayer(), Adventure.getMobs());
						}
					}

				}
			}catch(Exception e) 
			{

			}

			lastTick=System.currentTimeMillis();
		}
		
	}

	@Override
	public void end() {
		firePuddles=new ArrayList<Point>();
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(super.isActive()) {
		for(Point p: firePuddles) 
		{
			 
			

			g.setColor(new Color((int)(226),(int)(88), (int)(34),(int)( 75)));
			g.fillOval((int)p.getX()-100, (int)p.getY()-100, 200, 200);


			
		}
		}
	}
	

}
