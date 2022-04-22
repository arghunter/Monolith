package GameObjects.Player.items.weapons;

import java.awt.Graphics2D;

import GameObjects.Mob;
import GameObjects.Player.Player;

public class RustySword extends MeleeWeapon{

	public RustySword(int damage, int range, double attackSpeed, double sweepAngle) {
		super(damage, range, attackSpeed, sweepAngle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void secondaryFire(Mob[] mobs, Player player) {
		// TODO Block code here
		
	}

	@Override
	public void tertiaryFire(Mob[] mobs, Player player) {
		// TODO cleave here
		
	}
	public void render(Graphics2D g) 
	{
		
	}

}
