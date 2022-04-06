package skills;

import java.awt.Graphics;

public class RegenSkill extends GenericSkill {
	private int regen;
	private int modifiedRegen;
	

	public RegenSkill(SkillType type,String name, int regen,int tier) {
		super(type,name,tier);
		this.regen=regen;
		this.tier=tier;
		this.modifiedRegen=regen*(tier);
	}

	@Override
	public int apply(int regen) {
		
		return (int)(regen*(1+modifiedRegen/100.0)+0.5);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(SkillType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}

}