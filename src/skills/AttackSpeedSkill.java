package skills;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class AttackSpeedSkill extends GenericSkill{
	private int attackSpeed;

	private int modifiedAttackSpeed;

	public AttackSpeedSkill(StatType type,String name, int attackSpeed,int tier,boolean isActive) {
		super(type, name, tier,isActive);
		this.attackSpeed=attackSpeed;
		
		this.modifiedAttackSpeed=attackSpeed*tier;
	}

	@Override
	//Input in rpm
	public int apply(int attackSpeed) {
		int newAttackSpeed=(int)(attackSpeed*(modifiedAttackSpeed/100.0+1)+0.5);
		if(newAttackSpeed>2.5*attackSpeed) 
		{
			newAttackSpeed=(int)(2.5*attackSpeed+0.5);
		}
		
		
		return newAttackSpeed;
	}


	public int getAttackSpeed() {
		return attackSpeed;
	}

	public int getModifiedAttackSpeed() {
		return modifiedAttackSpeed;
	}

	@Override
	public void apply(StatType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
