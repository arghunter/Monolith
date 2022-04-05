package skills;

import java.awt.Graphics;

public class AttackSpeedSkill extends GenericSkill{
	private int attackSpeed;

	private int modifiedAttackSpeed;

	public AttackSpeedSkill(SkillType type,String name, int attackSpeed,int tier) {
		super(type, name, tier);
		this.attackSpeed=attackSpeed;
		this.tier=tier;
		this.modifiedAttackSpeed=attackSpeed*(int)(tier/2.0+0.5);
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

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
