//Author: Armaan Gomes   
//Date: 5/20/22
//Rev: 01
//Notes: A fire ball
package GameObjects.Player.abilities;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import GameObjects.Player.Player;
import GameObjects.Projectiles.Projectile;
import GameObjects.Projectiles.StraightProjectile;
import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import render.Adventure;

public class FireBall extends Ability implements ActionListener {
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public FireBall() {
		super(0, 10);

	}

	@Override
	public void init() {
		Player player = Adventure.getPlayer();
		projectiles.add(new StraightProjectile(player.getX(), player.getY(), 8, ("Fireball"), 300));
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
					new Damage(50, StatusEffect.FIRE, 5, m, Adventure.getPlayer(), Adventure.getMobs());
				}
			}
		} catch (ClassCastException ex) {
			System.out.println("Here");
		}

	}

}
