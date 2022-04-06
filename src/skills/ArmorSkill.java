package skills;

import java.awt.Graphics;

public class ArmorSkill extends GenericSkill {
	private int armor;

	private int modifiedArmor;

	public ArmorSkill(StatType type,String name, int armor,int tier) {
		super(type,name,tier);
		this.armor=armor;
		this.modifiedArmor=armor*tier;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int apply(int armor) {
		return (int)(armor*(1+modifiedArmor/100.0)+0.5);
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public int getArmor() {
		return armor;
	}

	public int getModifiedArmor() {
		return modifiedArmor;
	}

	@Override
	public void apply(StatType[] valueTypes, int[] values) {
		// TODO Auto-generated method stub
		
	}

}
