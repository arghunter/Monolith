package GameObjects.mobs;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import GameObjects.Player.Player;
import GameObjects.Projectiles.Projectile;
import GameObjects.Projectiles.StraightProjectile;
import general.AudioPlayer;

public class Sporeshroom extends Mob implements RangedMob,ActionListener {
	// speed, damage, health, armor, attackspeed, attack range
	public static final int[] stats = { 20, 100, 250, 5, 12, 500 };
	private ArrayList<Projectile> projectiles=new ArrayList<Projectile>();
	// Constructor
	public Sporeshroom(int x,int y) {
				super(x,y,stats[0],stats,64,64,"Sporeshroom",7);
				super.dist=0;
			}

	@Override
	// Renders this object
	public void render(Graphics2D g) {
		super.render(g);
		renderProjectiles(g);
		super.getImage().drawAnimation(g);

	}

	@Override
	public void fireProjectile() {
		projectiles.add(new StraightProjectile(this.getX()- 2 * (int) (super.getImage().getHeight() * Math.cos(this.getAngle() + Math.PI)),this.getY()- 2 * (int) (super.getImage().getHeight() * Math.sin(this.getAngle() + Math.PI)),6,new ImageIcon("imgs/Spore/Spore0.png").getImage(),stats[5]));
		projectiles.get(projectiles.size() - 1).addActionListener(this);
		projectiles.get(projectiles.size() - 1).rotate(this.getAngle());
	}

	@Override
	public void renderProjectiles(Graphics2D g) {
		try 
		{
			for(Projectile p:projectiles) 
			{
				try 
				{
					if(p.getImage()==null) 
					{
						projectiles.remove(p);
						continue;
					}
					p.draw(g);
					
				}catch(Exception e) 
				{
					
				}

			}
		}catch(Exception e) 
		{
			
		}


	}
	@Override 
	public void action(Player player) 
	{
		playerLevel = player.getLevel();
		updateAngle(player.getX(), player.getY());
		for (int i = 1; i < 4; i++) {
			super.stats[i] = stats[i] * playerLevel / 5;
		}
		super.stats[0] =stats[0];
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
				if(System.currentTimeMillis()-lastSound>3000+Math.random()*2000) 
				{
					new AudioPlayer("monster_000"+sound,AudioPlayer.ONE_TIME);

				}

			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try 
		{
			Player p=(Player)e.getSource();
			p.takeDamage(stats[1]);
		}catch (Exception ex) 
		{
			
		}
		
	}

}
