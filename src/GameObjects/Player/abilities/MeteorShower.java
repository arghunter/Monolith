//Author: Armaan Gomes   
//Date: 5/20/22
//Rev: 01
//Notes: An ability that drops meteors on 13 mobs near the player and leave puddles of fire behind
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
	// Fields
	private ArrayList<Point> firePuddles = new ArrayList<Point>();
	private long lastTick;

	// Constructor
	public MeteorShower() {
		super(5, 40);
	}

	@Override
	// Deals a truckload of damage to 13 mobs within 600 pixels of the player
	public void init() {
		int count = 0;
		for (Mob m : Adventure.getMobs()) {
			if (Math.sqrt(Math.pow((m.getX() - Adventure.getPlayer().getX()), 2)
					+ Math.pow((m.getY() - Adventure.getPlayer().getY()), 2)) < 600) {
				new Damage(100, StatusEffect.FIRE, 5, m, Adventure.getPlayer(), Adventure.getMobs());
				firePuddles.add(new Point(m.getX(), m.getY()));
				count++;
			}
			if (count >= 13) {
				break;
			}
		}

		lastTick = System.currentTimeMillis() - 1000;

	}

	@Override
	// Makes the fire puddles deal damage
	public void runAbility() {
		long numTimes = (System.currentTimeMillis() - lastTick) / 500;
		for (long i = 0; i < numTimes; i++) {
			try {
				for (Mob m : Adventure.getMobs()) {
					for (Point p : firePuddles) {
						if (Math.sqrt(Math.pow((m.getX() - p.getX()), 2) + Math.pow((m.getY() - p.getY()), 2)) < 100) {
							new Damage(50, StatusEffect.NONE, 5, m, Adventure.getPlayer(), Adventure.getMobs());
						}
					}

				}
			} catch (Exception e) {

			}

			lastTick = System.currentTimeMillis();
		}

	}

	@Override
	// Clears all fire puddles
	public void end() {
		firePuddles = new ArrayList<Point>();

	}

	@Override
	// Draws all fire puddles
	public void draw(Graphics2D g) {
		if (super.isActive()) {
			for (Point p : firePuddles) {

				g.setColor(new Color((int) (226), (int) (88), (int) (34), (int) (75)));
				g.fillOval((int) p.getX() - 100, (int) p.getY() - 100, 200, 200);

			}
		}
	}

}
