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
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

import GameObjects.Player.Player;
import GameObjects.elementalDamage.Damage;
import GameObjects.elementalDamage.StatusEffect;
import GameObjects.mobs.Mob;
import general.ImageSystem;

public class LongRangeWeapon extends Weapon {
	
	//Fields
	private double randAngle;
	private int attackWidth;
	private Graphics2D graphic;
	private ImageSystem img;
	//Constructors
	public LongRangeWeapon(String name,int tier,int damage,int range,int attackSpeed,StatusEffect effect,double duration ,double statusChance,int attackWidth, double randAngle) {
		super(name,tier,damage,range,attackSpeed, effect, duration,statusChance);
		this.attackWidth = attackWidth;
		this.randAngle = randAngle;
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
		Polygon attackRect = this.rotate(new Rectangle(player.getX() - attackWidth/2,player.getY() - super.getRange(),attackWidth,super.getRange()),new Point(player.getX(),player.getY()),(player.getAngle()+Math.PI));
		g.fill(attackRect);
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
	//Use sin and cos to write a rotate rect method
	public void primaryFire(ArrayList<Mob> mobs, Player player) {
		if(canFire()) 
		{
			int totalStatus=0;
			for(Mob m : mobs) {
				if(graphic!=null) {
				}
				if(m!=null&&this.euclidDist(m.getX(), m.getY(), player.getX(), player.getY()) < super.getRange()) {
					Polygon attackRect = this.rotate(new Rectangle(player.getX() - attackWidth/2,player.getY() - super.getRange(),attackWidth,super.getRange()),new Point(player.getX(),player.getY()),(player.getAngle()+Math.PI) + Math.random()*randAngle);
					
					if(attackRect.intersects(m.getRect().getX(),m.getRect().getY(),m.getRect().width,m.getRect().height)) {
						
						System.out.println("damageDone " + (int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)));
						synchronized(mobs) 
						{
							if(totalStatus<2&&Math.random()<=getChance()) 
							{
								Damage dmg= new Damage((int)(super.getDamage()*(Math.log10(player.getStats()[4])+1)),getEffect(),getDuration(),m,player,mobs);
								totalStatus++;
							}else 
							{
								Damage dmg= new Damage((int)(super.getDamage()*(Math.log10(player.getStats()[4])+1)),StatusEffect.NONE,0,m,player,mobs);

							}
						}

					}
				}
			}
		}
	}


	
	//toString for save data parsing
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
		for(int i = 0; i < 4; i++) {
			int height = currY[i]-p.y;
			int width = currX[i]-p.x;
			currX[i] =(int) ((height*Math.cos(angle) - width*Math.sin(angle)) + p.x);
			currY[i] =(int) ((height*Math.sin(angle) + width*Math.cos(angle)) + p.y);
		}
		return new Polygon(currX,currY,4);
	}
	public String toString() 
	{
		String s="(Item:"+super.getName()+"/"+super.getTier()+"/"+super.getType()+"/"+getDamage()+"/"+getRange()+"/"+getAttackSpeed()+"/"+getEffect()+"/"+getDuration()+"/"+getChance()+"/"+attackWidth+"/"+randAngle+"/LongRangeWeapon";
		return s;
		
		
	}

}