//Authors: Adithya Giri
//Revs: 01
//Date: 5/10/22
//Notes: A Meleewepon that takes a range, and can damage enemies
 
package GameObjects.Player.items.weapons;

import java.awt.Graphics2D;
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
		g.fillArc(player.getCenterX(), player.getCenterY() , super.getRange(), super.getRange(), (int)((-player.getAngle()-sweepAngle/2)*Math.PI/180), (int)(sweepAngle * Math.PI/180));
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
			if(m!=null&&this.euclidDist(m.getCenterX(), m.getCenterY(), player.getCenterX(), player.getCenterY()) < super.getRange()) {
				double hyp = this.euclidDist(m.getCenterX(), m.getCenterY(), player.getCenterX(), player.getCenterY());
				int xDist = Math.abs(player.getCenterX()-m.getCenterX());
				int yDist = Math.abs(player.getCenterY()-m.getCenterY());
				double sinAngle = Math.asin(yDist/hyp);
				double xDiff=m.getCenterX()-player.getCenterX();
				double yDiff=m.getCenterY()-player.getCenterY();
				double trueAngle = 0;
				if(xDiff >= 0 && yDiff >= 0) {
					trueAngle = sinAngle;
				} else if (xDiff <= 0 && yDiff >= 0) {
					trueAngle = Math.PI - sinAngle;
				} else if (xDiff <= 0 && yDiff <= 0) {
					trueAngle = Math.PI + sinAngle;
				} else if(xDiff >= 0 && yDiff <= 0) {
					trueAngle = sinAngle;
				}
				System.out.println("inside");
				System.out.println(-player.getAngle() + " " + trueAngle);
				if(trueAngle > (player.getAngle() - sweepAngle/2) && trueAngle < (player.getAngle()+sweepAngle/2)) {
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
