package skills;

import java.awt.Graphics;

public class HealthSkill extends GenericSkill{
	private int health;
	private int modifiedHealth;

	public HealthSkill(StatType type,String name,int health,int tier) {
		super(type, name, tier);
		this.health=health;
		
		this.modifiedHealth=health*(tier);
	}

	@Override
	public int apply(int health) {
		System.out.println((1.0+modifiedHealth/100.0));
		return (int)(health*(1.0+modifiedHealth/100.0)+0.5);
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
