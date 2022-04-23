package GameObjects.Player.items.weapons;

import java.awt.Graphics2D;

import GameObjects.Player.Player;
import GameObjects.mobs.Mob;

public class RustySword extends MeleeWeapon{

	public RustySword() {
		super("Rusty Sword",System.currentTimeMillis(),50, 30, 0.5,10/18.0*Math.PI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void secondaryFire(Mob[] mobs, Player player) {
		super.setCurrentAttackDelay(1300);
		//TODO parry
		
		
	}

	@Override
	public void tertiaryFire(Mob[] mobs, Player player) {
		// TODO nothing here this sword is too bad
		
	}
	public void render(Graphics2D g) 
	{
		
	}

}
