package skills;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class RegenSkill extends GenericSkill {
	private int regen;
	private int modifiedRegen;
	

	public RegenSkill(StatType type,String name, int regen,int tier,boolean isActive) {
		super(type,name,tier,isActive);
		this.regen=regen;
		
		this.modifiedRegen=regen*(tier);
	}

	@Override
	public int apply(int regen) {
		
		return (int)(regen*(1+modifiedRegen/100.0)+0.5);
	}

	@Override
	public void render(Graphics2D g,int x,int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(StatType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}

}
