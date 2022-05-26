//Author: Armaan Gomes   
//Date: 5/20/22
//Rev: 01
//Notes: An ability that deals damage over time in a radius and then deals a portion of that damage in a larger radius
package GameObjects.Player.abilities;

import java.awt.Color;
import java.awt.Graphics2D;

import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import render.Adventure;

public class ShadowFall extends Ability {
	//Fields
	private long lastTick;
	private int damageTotal;
	//Constructor
	public ShadowFall() {
		super(15, 45);
	}

	@Override
	//Init
	public void init() {
		lastTick = System.currentTimeMillis() - 1000;
	}

	@Override
	//Deals damage over time
	public void runAbility() {
		long numTimes = (System.currentTimeMillis() - lastTick) / 200;
		for (long i = 0; i < numTimes; i++) {
			try {
				for (Mob m : Adventure.getMobs()) {
					if (Math.sqrt(Math.pow((m.getX() - Adventure.getPlayer().getX()), 2)
							+ Math.pow((m.getY() - Adventure.getPlayer().getY()), 2)) < 200) {
						new Damage(40, StatusEffect.NONE, 5, m, Adventure.getPlayer(), Adventure.getMobs());
						damageTotal += 40;
					}
				}
				lastTick = System.currentTimeMillis();
			} catch (Exception e) {

			}

		}

	}

	@Override
	//Deals a portion of the total damage in a 300px radius
	public void end() {
		for (Mob m : Adventure.getMobs()) {
			if (Math.sqrt(Math.pow((m.getX() - Adventure.getPlayer().getX()), 2)
					+ Math.pow((m.getY() - Adventure.getPlayer().getY()), 2)) < 300) {
				new Damage(damageTotal / 2, StatusEffect.ROT, 5, m, Adventure.getPlayer(), Adventure.getMobs());
			}
		}
	}

	@Override
	//Draws the death radius
	public void draw(Graphics2D g) {
		if (super.isActive()) {
			try {
				g.setColor(new Color(0, 0, 0, 50));
				g.fillOval(Adventure.getPlayer().getX() - 300, Adventure.getPlayer().getY() - 300, 600, 600);
				g.setColor(new Color(0, 0, 0, 88));

				g.fillOval(Adventure.getPlayer().getX() - 200, Adventure.getPlayer().getY() - 200, 400, 400);
			} catch (Exception e) {

			}
		}

	}

}
