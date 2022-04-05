package skills;

import java.awt.Graphics;

public class ShieldSkill extends GenericSkill {

	private int shield;
	private int modifiedSheild;

	public ShieldSkill(SkillType type,String name,int shield,int tier) {
		super(type, name, tier);
		this.shield=shield;
		this.tier=tier;
		this.modifiedSheild=shield*(int)(tier/2.0+0.5);
	}

	@Override
	public int apply(int sheild) {
		
		return (int)(sheild*(1+modifiedSheild/100.0)+0.5);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
