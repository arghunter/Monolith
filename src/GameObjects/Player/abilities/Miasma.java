//Author: Armaan Gomes   
//Date: 5/20/22
//Rev: 01
//Notes: An ability that leaves behind a large toxic cloud
package GameObjects.Player.abilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import input.MouseInputParser;
import render.Adventure;

public class Miasma extends Ability {
	// Fields
	private Point center;
	private long lastTick;

	// Constructor
	public Miasma() {
		super(10, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	// Decides center of toxic cloud based on mouse
	public void init() {
		center = new Point((int) MouseInputParser.getX(), (int) MouseInputParser.getY());

		lastTick = System.currentTimeMillis() - 1000;

	}

	@Override
	// Deals damage over time
	public void runAbility() {
		if (center != null) {
			long numTimes = (System.currentTimeMillis() - lastTick) / 1500;
			for (long i = 0; i < numTimes; i++) {
				try {
					for (Mob m : Adventure.getMobs()) {

						if (Math.sqrt(Math.pow((m.getX() - center.getX()), 2)
								+ Math.pow((m.getY() - center.getY()), 2)) < 200) {
							new Damage(30, StatusEffect.TOXIN, 5, m, Adventure.getPlayer(), Adventure.getMobs());
						}

					}
				} catch (Exception e) {

				}

				lastTick = System.currentTimeMillis();
			}
		}

	}

	@Override
	// Makes the center go away
	public void end() {
		center = null;

	}

	@Override
	// Draws the toxic cloud
	public void draw(Graphics2D g) {
		if (super.isActive() && center != null) {

			g.setColor(new Color(27, 200, 12, 75));
			g.fillOval((int) center.getX() - 200, (int) center.getY() - 200, 400, 400);

		}
	}

}
