//Authors: Adithya Giri
//Revs: 01
//Date: 5/10/22
//Notes: A Meleewepon that takes a range, and can damage enemies
 
package GameObjects.Player.items.weapons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

import GameObjects.Player.Player;
import GameObjects.mobs.Mob;
import general.ImageSystem;

public class LongRangeWeapon extends Weapon {
	
	//Fields
	private double sweepAngle;
	private int attackWidth;
	private Graphics2D graphic;
	private ImageSystem img;
	//Constructors
	public LongRangeWeapon(String name,int tier,int damage,int range,int attackSpeed,int attackWidth, double randAngle) {
		super(name,tier,damage,range,attackSpeed);
		this.attackWidth = attackWidth;
		img=new ImageSystem(0,0,(new ImageIcon("imgs/"+name.replace(" ", "")+"/"+name.replace(" ", "")+0+".png").getImage()));

	}

	@Override
	//Draws this weapon 
	public void drawWeapon(Player player, Graphics2D g) {
		//System.out.println("hi");
		g.setColor(new Color(255,0,0,50));
		img.move((int)(player.getX()-(img.getX())+64*Math.cos(player.getAngle())), (int)(player.getY()-(img.getY()-64*Math.sin(player.getAngle()))));
		img.setRotation(player.getAngle());
		
		graphic = g;
		
		g.fillRect(player.getX() - attackWidth/2,player.getY() - super.getRange(),attackWidth,super.getRange());
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
			for(Mob m : mobs) {
				if(graphic!=null) {
				}
				if(m!=null&&this.euclidDist(m.getX(), m.getY(), player.getX(), player.getY()) < super.getRange()) {
					Rectangle attackRect = new Rectangle(player.getX() - attackWidth/2,player.getY() - super.getRange(),attackWidth,super.getRange());
					if(attackRect.intersects(m.getRect().getX(),m.getRect().getY(),m.getRect().width,m.getRect().height)) {
						System.out.println("damageDone " + (int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)));
						m.takeDamage((int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)));
					}
				}
			}
		}
		
		
	}
	



	
	//toString for save data parsing
	public String toString() 
	{
		String s="(Item:"+super.getName()+"/"+super.getTier()+"/"+super.getType()+"/"+getDamage()+"/"+getRange()+"/"+getAttackSpeed()+"/"+sweepAngle;
		return s;
		
		
	}

}