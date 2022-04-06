package skills;

import java.awt.Graphics;

public class ShieldSkill extends GenericSkill {

	private int shield;
	private int modifiedSheild;

	public ShieldSkill(StatType type,String name,int shield,int tier) {
		super(type, name, tier);
		this.shield=shield;
		
		this.modifiedSheild=shield*(tier);
	}

	@Override
	public int apply(int shield) {
		
		return (int)(shield*(1+modifiedSheild/100.0)+0.5);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(StatType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}

}
