package GameObjects.Player.abilities;

import java.awt.Graphics2D;

import render.Adventure;

public class Dodge extends Ability {

	public Dodge() {
		super(0.1, 7);
		
	}

	@Override
	public void init() {
		Adventure.getPlayer().setDist(15);
		
	}

	@Override
	public void runAbility() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		Adventure.getPlayer().setDist(3);

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	

}
