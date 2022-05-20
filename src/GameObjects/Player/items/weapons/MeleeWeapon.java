//Authors: Adithya Giri
//Revs: 01
//Date: 5/10/22
//Notes: A Meleewepon that takes a range, and can damage enemies
 
package GameObjects.Player.items.weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

import GameObjects.Player.Player;
import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import general.ImageSystem;

public class MeleeWeapon extends Weapon {
	
	//Fields
	private double sweepAngle;
	private Graphics2D graphic;
	private ImageSystem img;
	//Constructors
	public MeleeWeapon(String name,int tier,int damage,int range,int attackSpeed,StatusEffect effect,double duration ,double statusChance,double sweepAngle) {
		super(name,tier,damage,range,attackSpeed, effect, duration, statusChance);
		this.sweepAngle=sweepAngle;
		img=new ImageSystem(0,0,(new ImageIcon("imgs/"+name.replace(" ", "")+"/"+name.replace(" ", "")+0+".png").getImage()));

	}

	@Override
	//Draws this weapon 
	public void drawWeapon(Player player, Graphics2D g) {
		g.setColor(new Color(255,0,0,50));
		double angle=player.getAngle()-sweepAngle/2+sweepAngle*(9*(System.currentTimeMillis()-super.getLastAttack()))/super.getCurrentAttackDelay();
		if((System.currentTimeMillis()-super.getLastAttack())/super.getCurrentAttackDelay()>0.1)

		{
			
			angle=player.getAngle()+sweepAngle-(2*sweepAngle*(((System.currentTimeMillis()-super.getLastAttack()))/super.getCurrentAttackDelay()));
		}
		if(angle>player.getAngle()+sweepAngle/2) 
		{
			angle=player.getAngle()+sweepAngle/2;
		}
		if(angle<player.getAngle()-sweepAngle/2) 
		{
			angle=player.getAngle()-sweepAngle/2;
		}

		img.move((int)(player.getX()-(img.getX())+64*Math.cos(angle)), (int)(player.getY()-(img.getY()-64*Math.sin(angle))));
		img.setRotation(angle);
		
		graphic = g;
		
		g.fillArc((int)player.getX()-super.getRange(), (int)player.getY()-super.getRange(), super.getRange()*2, super.getRange()*2, (int)((-player.getAngle()-sweepAngle/2)*180/Math.PI), (int)(sweepAngle * 180/Math.PI));
		img.drawImage(g);
	}
	//Distance calculations
	private double euclidDist(int x1, int y1, int x2, int y2) {
		//System.out.println(x1 + " " +y1 + " mob");
		//System.out.println(x2+ " " +y2 + " player");
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	@Override
	//Primary fire of this weapon
	public void primaryFire(ArrayList<Mob> mobs, Player player) {
		if(canFire()) 
		{
			int totalStatus=0;
			for(Mob m : mobs) {
				if(graphic!=null) {
				}
				if(m!=null&&this.euclidDist(m.getX(), m.getY(), player.getX(), player.getY()) < super.getRange()) {
					Arc2D.Double attackArc = new Arc2D.Double((int)player.getX()-super.getRange(), (int)player.getY()-super.getRange(), super.getRange()*2, super.getRange()*2, (int)((-player.getAngle()-sweepAngle/2)*180/Math.PI), (int)(sweepAngle * 180/Math.PI),Arc2D.PIE);
					if(attackArc.intersects(m.getRect().getX(),m.getRect().getY(),m.getRect().width,m.getRect().height)) {
						System.out.println("damageDone " + (int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)));
						synchronized(mobs) 
						{
							if(totalStatus<3&&Math.random()<=getChance()) 
							{
								Damage dmg=new Damage((int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)),getEffect(),getDuration(),m,player,mobs);
								totalStatus++;
							}else 
							{
								Damage dmg=new Damage((int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)),StatusEffect.NONE,0,m,player,(mobs));

							}
						}

//						m.takeDamage(player,(int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)));
					}
				}
			}
		}
		
		
	}
	



	
	//toString for save data parsing
	public String toString() 
	{
		String s="(Item:"+super.getName()+"/"+super.getTier()+"/"+super.getType()+"/"+getDamage()+"/"+getRange()+"/"+getAttackSpeed()+"/"+getEffect()+"/"+getDuration()+"/"+getChance()+"/"+sweepAngle+"/MeleeWeapon";
		return s;
		
		
	}

}
