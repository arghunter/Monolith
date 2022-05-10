package GameObjects.Player.items.weapons;

import GameObjects.Player.Player;
import GameObjects.mobs.Mob;

public class MeleeWeapon extends Weapon {
	private double sweepAngle;
	public MeleeWeapon(String name,int tier,int damage,int range,int attackSpeed,double sweepAngle) {
		super(name,tier,damage,range,attackSpeed);
		this.sweepAngle=sweepAngle;
	}
//	public MeleeWeapon(String saveData) 
//	{
//		super(saveData.split("MeleeWeapon:")[1].split("/")[0],Integer.parseInt(saveData.split("MeleeWeapon:")[1].split("/")[1]),Integer.parseInt(saveData.split("MeleeWeapon:")[1].split("/")[2]),Integer.parseInt(saveData.split("MeleeWeapon:")[1].split("/")[3]));
//		this.sweepAngle=Double.parseDouble(saveData.split("MeleeWeapon:")[1].split("/")[4]);
//	}


	public double euclidDist(int x1, int x2, int y1, int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	@Override
	public void primaryFire(Mob[] mobs, Player player) {
		for(Mob m : mobs) {
			if(m!=null&&this.euclidDist(m.getCenterX(), m.getCenterY(), player.getCenterX(), player.getCenterY()) < super.getRange()) {
				double hyp = this.euclidDist(m.getCenterX(), m.getCenterY(), player.getCenterX(), player.getCenterY());
				int xDist = m.getCenterX()-player.getCenterX();
				int yDist = player.getCenterY()-m.getCenterY();
				double sinAngle = Math.asin(yDist/hyp);
				double cosAngle = Math.asin(xDist/hyp);
				double sin = yDist/hyp;
				double cos = xDist/hyp;
				double trueAngle = 0;
				if(sin > 0 && cos > 0) {
					trueAngle = sinAngle;
				} else if (sin > 0 && cos < 0) {
					trueAngle = sinAngle+Math.PI/2;
				} else if (sin < 0 && cos < 0) {
					trueAngle = sinAngle - Math.PI/2;
				} else if(sin < 0 && cos > 0) {
					trueAngle = sinAngle;
				}
				if(trueAngle > (Math.PI/2 - sweepAngle/2) && trueAngle < (Math.PI/2+sweepAngle)) {
					m.takeDamage((int)(super.getDamage()*(Math.log10(player.getStats()[4]+player.getStats()[8])+1)));
				}
			}
		}
		
	}
	



	@Override
	public void secondaryFire(Mob[] mobs, Player player) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void tertiaryFire(Mob[] mobs, Player player) {
		// TODO Auto-generated method stub
		
	}

//	public String toString() 
//	{
//		String s="MeleeWeapon:"+getName()+"/"+"/"+getDamage()+"/"+getRange()+"/"+getAttackSpeed()+"/"+sweepAngle;
//		return s;
//		
//		
//	}

}
