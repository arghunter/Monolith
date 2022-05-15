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

import GameObjects.Player.Player;
import GameObjects.mobs.Mob;

public class MeleeWeapon extends Weapon {
	
	//Fields
	private double sweepAngle;
	private Graphics2D graphic;
	//Constructors
	public MeleeWeapon(String name,int tier,int damage,int range,int attackSpeed,double sweepAngle) {
		super(name,tier,damage,range,attackSpeed);
		this.sweepAngle=sweepAngle;
	}

	@Override
	//Draws this weapon 
	public void drawWeapon(Player player, Graphics2D g) {
		//System.out.println("hi");
		g.setColor(new Color(255,0,0,50));
		graphic = g;
		g.fillArc((int)player.getX()-super.getRange(), (int)player.getY()-super.getRange(), super.getRange()*2, super.getRange()*2, (int)((-player.getAngle()-sweepAngle/2)*180/Math.PI), (int)(sweepAngle * 180/Math.PI));
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
		for(Mob m : mobs) {
			if(graphic!=null) {
			}
			if(m!=null&&this.euclidDist(m.getX(), m.getY(), player.getX(), player.getY()) < super.getRange()) {
				double playerAngle = -player.getAngle();
				Arc2D.Double attackArc = new Arc2D.Double((int)player.getX()-super.getRange(), (int)player.getY()-super.getRange(), super.getRange()*2, super.getRange()*2, (int)((-player.getAngle()-sweepAngle/2)*180/Math.PI), (int)(sweepAngle * 180/Math.PI),Arc2D.PIE);
				if(attackArc.intersects(m.getRect().getX(),m.getRect().getY(),m.getRect().width,m.getRect().height)) {
					System.out.println("damageDone " + (int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)));
					m.takeDamage((int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)));
				}
			}
		}
		
	}
	



	@Override
	public void secondaryFire(ArrayList<Mob> mobs, Player player) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void tertiaryFire(ArrayList<Mob> mobs, Player player) {
		// TODO Auto-generated method stub
		
	}
	//toString for save data parsing
	public String toString() 
	{
		String s="(Item:"+super.getName()+"/"+super.getTier()+"/"+super.getType()+"/"+getDamage()+"/"+getRange()+"/"+getAttackSpeed()+"/"+sweepAngle;
		return s;
		
		
	}

}
