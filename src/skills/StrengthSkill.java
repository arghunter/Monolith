package skills;

import java.awt.Graphics;

public class StrengthSkill extends GenericSkill {

	private int strength;
	private int modifiedStrength;

	public StrengthSkill(StatType type,String name,int strength, int tier,boolean isActive) {
		super(type, name, tier,isActive);
		this.strength=strength;
		
		this.modifiedStrength=strength*(tier);
	}

	@Override
	public int apply(int damage) {
	
		return (int)(damage*0.88*Math.log(1.0+modifiedStrength/12.0)*Math.log(1.0+modifiedStrength/12.0)+0.5) ;
	}

	@Override
	public void render(Graphics g,int x,int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(StatType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}

}
