//Author: Peter Ferolito   
//Date: 5/20/22
//Rev: 01
//Notes: A ranged Hobgoblin archer
package GameObjects.mobs;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import GameObjects.Direction;
import GameObjects.Player.Player;
import GameObjects.Projectiles.CurlyProjectile;
import GameObjects.Projectiles.ExponentialProjectile;
import GameObjects.Projectiles.LogisticProjectile;
import GameObjects.Projectiles.Projectile;
import GameObjects.Projectiles.StraightProjectile;
import general.AudioPlayer;

public class Hobgoblin extends Mob implements RangedMob, ActionListener {
	// speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats = { 20, 35, 250, 10, 20, 500 };
	public static final int xpDropped = 12;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	int type = (int) (Math.random() * 3);

	// Constructor
	public Hobgoblin(int x, int y) {
		super(x, y, stats[0], stats, 64, 64, "Hobgoblin", 1, xpDropped);
		super.dist = 1;
	}

	@Override
	// Renders this object
	public void render(Graphics2D g) {
		super.render(g);
		renderProjectiles(g);
		super.getImage().drawAnimation(g);

	}

	@Override
	// Fires a projectile
	public void fireProjectile() {
		switch (type) {
		case 0:
			projectiles.add(new StraightProjectile(this.getX() - 2 * (int) (64 * Math.cos(this.getAngle() + Math.PI)),
					this.getY() - 2 * (int) (64 * Math.sin(this.getAngle() + Math.PI)), 2, "Arrow", stats[5]));
			break;
		case 1:
			projectiles.add(new CurlyProjectile(this.getX() - 2 * (int) (64 * Math.cos(this.getAngle() + Math.PI)),
					this.getY() - 2 * (int) (64 * Math.sin(this.getAngle() + Math.PI)), 2, "Arrow", stats[5]));
			break;
		case 2:
			projectiles.add(new LogisticProjectile(this.getX() - 2 * (int) (64 * Math.cos(this.getAngle() + Math.PI)),
					this.getY() - 2 * (int) (64 * Math.sin(this.getAngle() + Math.PI)), 2, "Arrow", stats[5]));
			break;
		}
		projectiles.get(projectiles.size() - 1).addActionListener(this);
		projectiles.get(projectiles.size() - 1).rotate(this.getAngle());

	}

	@Override
	// Renders all projectiles this object has fired
	public void renderProjectiles(Graphics2D g) {
		try {
			for (Projectile p : projectiles) {
				try {
					if (p.getImage() == null) {
						projectiles.remove(p);
						continue;
					}
					p.draw(g);

				} catch (Exception e) {

				}

			}
		} catch (Exception e) {

		}
	}

	@Override
	// Overridden action from mob to account for firing projectiles
	public void action(Player player) {
		playerLevel = player.getLevel();
		updateAngle(player.getX(), player.getY());
		for (int i = 1; i < 4; i++) {
			super.stats[i] = stats[i] * playerLevel / 5;
		}
		super.stats[0] = stats[0];
		super.stats[4] = stats[4];
		super.stats[5] = stats[5];
		int curX = this.getX();
		int curY = this.getY();

		int diffX = curX - player.getX();
		int diffY = curY - player.getY();

		if ((diffX) * (diffX) + (diffY) * (diffY) < stats[5] * stats[5]) {
			if (System.currentTimeMillis() - lastAttack > 60000.0 / stats[4]) {
				fireProjectile();
				lastAttack = System.currentTimeMillis();
				if (System.currentTimeMillis() - lastSound > 3000 + Math.random() * 2000) {
					new AudioPlayer("monster_000" + sound, AudioPlayer.ONE_TIME);

				}

			}

		} else {
			Direction toMove = Direction.NORTH;
			if (1.5 * Math.abs(diffX) < Math.abs(diffY)) {
				// Move up down
				if (diffY > 0) {
					toMove = Direction.NORTH;
				} else {
					toMove = Direction.SOUTH;
				}
			} else if (1.5 * Math.abs(diffY) < Math.abs(diffX)) {
				// Move left right
				if (diffX > 0) {
					toMove = Direction.WEST;
				} else {
					toMove = Direction.EAST;
				}
			} else {
				// Move diagonal
				if (diffX > 0 && diffY > 0) {
					toMove = Direction.NORTHWEST;
				} else if (diffX <= 0 && diffY > 0) {
					toMove = Direction.NORTHEAST;
				} else if (diffX > 0 && diffY <= 0) {
					toMove = Direction.SOUTHWEST;
				} else if (diffX <= 0 && diffY <= 0) {
					toMove = Direction.SOUTHEAST;
				}
			}
			if (diffX * diffX + diffY * diffY >= (stats[5] * stats[5])) {
				this.move(toMove);
			}
		}
	}

	@Override
	// Damage calculations of projectile hit;
	public void actionPerformed(ActionEvent e) {
		try {
			Player p = (Player) e.getSource();
			p.takeDamage(stats[1]);
		} catch (Exception ex) {

		}
	}
}
