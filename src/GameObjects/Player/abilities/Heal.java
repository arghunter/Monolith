//Author: Armaan Gomes   
//Date: 5/20/22
//Rev: 01
//Notes: Heals the player
package GameObjects.Player.abilities;

import java.awt.Graphics2D;

import render.Adventure;

public class Heal extends Ability {

	// Constructor
	public Heal() {
		super(0, 10);

	}

	@Override
	// Heals the player
	public void init() {
		Adventure.getPlayer().heal(50 * (Adventure.getPlayer().getStats()[6] / 100 + 1));
	}

	@Override
	public void runAbility() {
		// TODO Auto-generated method stub

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
