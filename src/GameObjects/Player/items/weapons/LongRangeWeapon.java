//Authors: Adithya Giri
//Revs: 01
//Date: 5/10/22
//Notes: A Meleewepon that takes a range, and can damage enemies

package GameObjects.Player.items.weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

import GameObjects.Player.Player;
import GameObjects.Projectiles.Projectile;
import GameObjects.Projectiles.StraightProjectile;
import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import general.AudioPlayer;
import general.ImageSystem;
import render.Adventure;

public class LongRangeWeapon extends Weapon implements ActionListener {

	// Fields
	private double randAngle;
	private int attackWidth;
	private Graphics2D graphic;
	private ImageSystem img;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private int speed;

	// Constructors
	public LongRangeWeapon(String name, int tier, int damage, int range, int attackSpeed, StatusEffect effect,
			double duration, double statusChance, int attackWidth, double randAngle, int speed) {
		super(name, tier, damage, range, attackSpeed, effect, duration, statusChance);
		this.attackWidth = attackWidth;
		this.randAngle = randAngle;
		this.speed=speed;
		img = new ImageSystem(0, 0,
				(new ImageIcon("imgs/" + name.replace(" ", "") + "/" + name.replace(" ", "") + 0 + ".png").getImage()));

	}
	public LongRangeWeapon(LongRangeWeapon lw) 
	{
		super(lw);
		this.attackWidth=lw.getAttackWidth();
		this.randAngle=lw.getRandAngle();
		this.speed=lw.speed;
		img = new ImageSystem(0, 0,
				(new ImageIcon("imgs/" + super.getName().replace(" ", "") + "/" + super.getName().replace(" ", "") + 0 + ".png").getImage()));

		
	}

	public double getRandAngle() {
		return randAngle;
	}
	public int getAttackWidth() {
		return attackWidth;
	}
	public int getSpeed() {
		return speed;
	}
	@Override
	// Draws this weapon
	public void drawWeapon(Player player, Graphics2D g) {
		// System.out.println("hi");
		g.setColor(new Color(255, 0, 0, 50));
		img.move((int) (player.getX() - (img.getX()) + ((img.getHeight()+5) * Math.cos(player.getAngle()))),
				(int) (player.getY() - (img.getY() - ((img.getHeight()+5) * Math.sin(player.getAngle())))));
		img.setRotation(player.getAngle());

		graphic = g;
		Polygon attackRect = this.rotate(new Rectangle(player.getX() - attackWidth / 2,
				player.getY() - super.getRange(), attackWidth, super.getRange()),
				new Point(player.getX(), player.getY()), (player.getAngle() + Math.PI));
		g.fill(attackRect);
		img.drawImage(g);
		synchronized(projectiles) 
		{
			try 
			{
				for (Projectile p : projectiles) {

					try 
					{
						p.draw(g);
						if (p.getImage() == null) {
							projectiles.remove(p);
							continue;
						}
					}catch(Exception e) 
					{
						
					}


				}
			}catch(Exception e) 
			{
				
			}
			
		}

	}

	// Distance calculations
	private double euclidDist(int x1, int y1, int x2, int y2) {
		// System.out.println(x1 + " " +y1 + " mob");
		// System.out.println(x2+ " " +y2 + " player");
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	@Override
	// Primary fire of this weapon
	// Use sin and cos to write a rotate rect method
	public void primaryFire(ArrayList<Mob> mobs, Player player) {
		if (canFire()) {
			new AudioPlayer("Gun_0001", AudioPlayer.ONE_TIME);
			synchronized(projectiles) {
			projectiles.add(new StraightProjectile(
					player.getX() - 2 * (int) (img.getHeight() * Math.cos(player.getAngle() + Math.PI)),
					player.getY() - 2 * (int) (img.getHeight() * Math.sin(player.getAngle() + Math.PI)), speed,
					new ImageIcon("imgs/" + super.getName().replace(" ", "") + "/" + super.getName().replace(" ", "")
							+ "Projectile0.png").getImage(),super.getRange()));

			projectiles.get(projectiles.size() - 1).addActionListener(this);
			projectiles.get(projectiles.size() - 1).rotate(player.getAngle());
			}
		}
	}
	public void clearAllProjectiles() 
	{
		projectiles=new ArrayList<Projectile>();
	}

	public Polygon rotate(Rectangle r, Point p, double angle) {
		int[] currX = new int[4];
		int[] currY = new int[4];
		currX[0] = r.x;
		currY[0] = r.y;
		currX[1] = r.x + r.width;
		currY[1] = r.y;
		currX[3] = r.x;
		currY[3] = r.y + r.height;
		currX[2] = r.x + r.width;
		currY[2] = r.y + r.height;
		for (int i = 0; i < 4; i++) {
			int height = currY[i] - p.y;
			int width = currX[i] - p.x;
			currX[i] = (int) ((height * Math.cos(angle) - width * Math.sin(angle)) + p.x);
			currY[i] = (int) ((height * Math.sin(angle) + width * Math.cos(angle)) + p.y);
		}
		return new Polygon(currX, currY, 4);
	}

	public String toString() {
		String s = "(Item:" + super.getName() + "/" + super.getTier() + "/" + super.getType() + "/" + getDamage() + "/"
				+ getRange() + "/" + getAttackSpeed() + "/" + getEffect() + "/" + getDuration() + "/" + getChance()
				+ "/" + attackWidth + "/" + randAngle + "/" + speed + "/LongRangeWeapon";
		return s;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			Mob m = (Mob) e.getSource();
			ArrayList<Mob> mobs = Adventure.getMobs();
			synchronized (mobs) {
				Damage dmg;
				if (Math.random() <= getChance()) {
					dmg = new Damage((int) (super.getDamage() * (Math.log10(Adventure.getPlayer().getStats()[4]) + 1)),
							getEffect(), getDuration(), m, Adventure.getPlayer(), mobs);

				} else {
					dmg = new Damage((int) (super.getDamage() * (Math.log10(Adventure.getPlayer().getStats()[4]) + 1)),
							StatusEffect.NONE, 0, m, Adventure.getPlayer(), mobs);

				}

			}
		} catch (Exception ex) {

		}

	}

}