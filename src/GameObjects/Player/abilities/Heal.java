package GameObjects.Player.abilities;

import java.awt.Graphics2D;

import render.Adventure;

public class Heal extends Ability {

	public Heal() {
		super(0, 10);
		
	}

	@Override
	public void init() {
		Adventure.getPlayer().heal(50*(Adventure.getPlayer().getStats()[6]/100+1));
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
