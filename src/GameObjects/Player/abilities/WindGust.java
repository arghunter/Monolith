package GameObjects.Player.abilities;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import GameObjects.Player.Player;
import GameObjects.Projectiles.Projectile;
import GameObjects.Projectiles.StraightProjectile;
import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import render.Adventure;

public class WindGust extends Ability implements ActionListener {
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public WindGust() {
		super(0, 5);
	}



	@Override
	public void init() {
		Player player = Adventure.getPlayer();
		projectiles.add(new StraightProjectile(player.getX(), player.getY(), 20, ("Tornado"), 300));
		projectiles.get(projectiles.size() - 1).addActionListener(this);
		projectiles.get(projectiles.size() - 1).rotate(player.getAngle());
	}

	@Override
	public void runAbility() {

	}

	@Override
	public void end() {

	}

	@Override
	public void draw(Graphics2D g) {

		synchronized (projectiles) {
			try {
				for (Projectile p : projectiles) {

					try {
						if (p.getImage() == null) {
							projectiles.remove(p);
							continue;
						}
					} catch (Exception e) {

					}

				}
			} catch (Exception e) {
				
			}


		}
		for(Projectile p:projectiles) 
		{
	

				p.draw(g);
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Mob hit = (Mob) e.getSource();
			for (Mob m : Adventure.getMobs()) {
				if (Math.sqrt(Math.pow((m.getX() - hit.getX()), 2)
						+ Math.pow((m.getY() - hit.getY()), 2)) < 100) {
					new Damage((int)(100-Math.sqrt(Math.pow((m.getX() - hit.getX()), 2)
							+ Math.pow((m.getY() - hit.getY()), 2))), StatusEffect.BLAST, 5, m, Adventure.getPlayer(), Adventure.getMobs());
				}
			}
		} catch (ClassCastException ex) {
			System.out.println("Here");
		}

	}
}
